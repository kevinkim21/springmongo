package com.example.springmongo.controller

import com.example.springmongo.common.Step

data class PaymentResponse(
    val orderId: String,
    val userId: String,
    val paymentId: String,
    val step: Step,
)