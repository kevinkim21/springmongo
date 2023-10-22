package com.example.springmongo.controller

data class DeliveryRequest(
    val orderId: String,
    val userId: String,
    val address: String,
)