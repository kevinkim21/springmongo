package com.example.springmongo.controller

import com.example.springmongo.common.Step

data class OrderResponse(
    val orderId: String,
    val userId: String,
    val step: Step,
)
