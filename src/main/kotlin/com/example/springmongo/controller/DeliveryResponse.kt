package com.example.springmongo.controller

import com.example.springmongo.common.Step

data class DeliveryResponse(
    val orderId: String,
    val userId: String,
    val deliveryId: String,
    val step: Step,
)