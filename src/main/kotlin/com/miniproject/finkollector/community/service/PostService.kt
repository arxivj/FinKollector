package com.miniproject.finkollector.community.service

import com.miniproject.finkollector.community.model.Post
import com.miniproject.finkollector.community.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

    fun getAllPosts(): List<Post> = postRepository.findAll()

    fun createPost(post: Post) = postRepository.save(post)
}