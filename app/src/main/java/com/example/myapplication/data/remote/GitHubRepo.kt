package com.example.myapplication.data.remote

import com.google.gson.annotations.SerializedName

data class GitHubRepo(
    val id: Long,
    val name: String,
    val description: String?,
    @SerializedName("html_url") val htmlUrl: String?
)