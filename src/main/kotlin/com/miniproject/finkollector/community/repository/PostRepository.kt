package com.miniproject.finkollector.community.repository

import com.miniproject.finkollector.community.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long>