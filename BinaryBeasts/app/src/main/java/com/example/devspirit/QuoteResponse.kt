package com.example.devspirit

data class QuoteResponse(
    val slip: Slip
)

data class Slip(
    val advice: String
)
