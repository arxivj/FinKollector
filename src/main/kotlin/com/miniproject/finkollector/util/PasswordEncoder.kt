package com.miniproject.finkollector.util

import org.apache.commons.codec.binary.Hex
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PasswordEncoder {

    private const val ITERATIONS = 10000
    private const val KEY_LENGTH = 256

    /**
     * 난수를 기반으로 Salt 생성
     */
    fun generateSalt(): String {
        val random = SecureRandom()
        val bytes = ByteArray(20)
        random.nextBytes(bytes)
        return Hex.encodeHexString(bytes)
    }

    /**
     * Password와 Salt를 사용하여 해시된 비밀번호 생성
     */
    fun hashPassword(password: String, salt: String): String {
        val chars = password.toCharArray()
        val bytes = salt.toByteArray()
        val spec = PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH)

        return try {
            generateHashedPassword(spec)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException, is InvalidKeySpecException ->
                    throw PasswordHashingException("비밀번호 해싱 중 오류 발생", e)
                else -> throw e
            }
        }
    }

    private fun generateHashedPassword(spec: PBEKeySpec): String {
        val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val hash = skf.generateSecret(spec).encoded
        return Base64.getEncoder().encodeToString(hash)
    }

    class PasswordHashingException(message: String, cause: Throwable) : RuntimeException(message, cause)
}
