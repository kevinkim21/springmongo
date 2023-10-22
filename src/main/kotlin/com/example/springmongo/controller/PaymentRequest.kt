package com.example.springmongo.controller

data class PaymentRequest(
    val orderId: String,
    val userId: String,
    val amount: Double,
    val paymentType: String
)