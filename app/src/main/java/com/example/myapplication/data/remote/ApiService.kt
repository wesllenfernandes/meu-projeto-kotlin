package com.example.myapplication.data.remote

import com.example.myapplication.model.Project
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String): List<Project>
}
