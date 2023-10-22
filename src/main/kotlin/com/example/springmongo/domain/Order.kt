package com.example.springmongo.domain

import com.example.springmongo.common.Step
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import java.time.LocalDateTime

data class Order(
    @Id
    val orderId: String,
    @Indexed
    val userId: String,
    val steps: List<OrderStep>? = null,
)

data class OrderStep(
    val step: Step,
    val data: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)