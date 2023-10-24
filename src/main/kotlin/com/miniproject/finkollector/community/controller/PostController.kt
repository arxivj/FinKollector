package com.miniproject.finkollector.community.controller

import com.miniproject.finkollector.community.model.Post
import com.miniproject.finkollector.community.service.PostService
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@RestController
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

    @GetMapping
    @Operation(
        summary = "[모든 게시물 조회]",
        description = "등록된 모든 게시물 조회",
        responses = [
            ApiResponse(responseCode = "200", description = "조회 성공",
                content = [Content(schema = Schema(implementation = Post::class))]),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun getAllPosts(): List<Post> = postService.getAllPosts()

    @PostMapping
    @Operation(
        summary = "[새로운 게시물 생성?]",
        description = "새로운 게시물을 등록? 작성하는 메서드",
        responses = [
            ApiResponse(responseCode = "200", description = "게시물 작성 성공",
                content = [Content(schema = Schema(implementation = Post::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun createNewPost(@RequestBody post: Post): Post = postService.createPost(post)
}
