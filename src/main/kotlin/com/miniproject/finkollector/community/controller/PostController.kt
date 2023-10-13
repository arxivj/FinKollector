package com.miniproject.finkollector.community.controller

import com.miniproject.finkollector.community.model.Post
import com.miniproject.finkollector.community.service.PostService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getAllPosts(): List<Post> = postService.getAllPosts()

    @PostMapping
    fun createNewPost(@RequestBody post: Post): Post = postService.createPost(post)
}