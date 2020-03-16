package com.example.mygithub2

import com.squareup.moshi.Json

data class userGit(
    val login:String,
    @Json(name = "avatar_url")
    val imgSrc: String
)