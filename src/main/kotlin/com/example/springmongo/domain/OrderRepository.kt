package com.example.springmongo.domain

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface OrderRepository: MongoRepository<Order, String> {
    fun findByOrderIdAndUserId(orderId: String, userId: String): Optional<Order>
    fun findByOrderId(orderId: String): Optional<Order>
}