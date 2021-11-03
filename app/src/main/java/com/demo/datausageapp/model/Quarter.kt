package com.demo.datausageapp.model

data class Quarter(
    val id: Int?,
    val year: Int,
    val quarterName: String,
    val volume: Float,
    val isDecrease: Boolean
)