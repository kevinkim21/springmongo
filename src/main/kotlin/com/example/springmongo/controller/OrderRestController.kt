package com.example.springmongo.controller

import com.example.springmongo.domain.Order
import com.example.springmongo.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class OrderRestController(
    private val orderService: OrderService
) {

    @PostMapping("/orders")
    fun createOrder(orderRequest: OrderRequest): OrderResponse {
        return orderService.createOrder(orderRequest)
    }

    @PostMapping("/payments")
    fun addPayment(paymentRequest: PaymentRequest): PaymentResponse {
        return orderService.addPayment(paymentRequest)
    }

    @PostMapping("/deliveries")
    fun addDelivery(deliveryRequest: DeliveryRequest): DeliveryResponse {
        return orderService.addDelivery(deliveryRequest)
    }

    @GetMapping("/orders/{orderId}")
    fun getOrder(@PathVariable orderId: String): Optional<Order> {
        return orderService.getOrder(orderId)
    }
}