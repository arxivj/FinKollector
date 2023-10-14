package com.miniproject.finkollector.member.service

import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.retry.backoff.ExponentialBackOffPolicy
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.retry.support.RetryTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class EmailSenderService(private val mailSender: JavaMailSenderImpl) {

    private val logger = LoggerFactory.getLogger(EmailSenderService::class.java)

    @Async
    fun sendVerificationEmail(email: String, code: String) {
        val retryTemplate = RetryTemplate()

        val retryPolicy = SimpleRetryPolicy(3)

        val backOffPolicy = ExponentialBackOffPolicy()
        backOffPolicy.initialInterval = 1000L
        backOffPolicy.multiplier = 2.0

        retryTemplate.setRetryPolicy(retryPolicy)
        retryTemplate.setBackOffPolicy(backOffPolicy)

        try {
            retryTemplate.execute<Void, RuntimeException> {
                val message = mailSender.createMimeMessage()
                val helper = MimeMessageHelper(message)

                helper.setFrom("admin@FinKollector.com")
                helper.setTo(email)
                helper.setSubject("Email Verification Code")
                helper.setText("Your verification code is: $code")

                mailSender.send(message)

                null
            }
            logger.info("Verification email sent to $email")
        } catch (ex: Exception) {
            logger.error("Failed to send verification email to $email after 3 attempts", ex)
        }
    }
}