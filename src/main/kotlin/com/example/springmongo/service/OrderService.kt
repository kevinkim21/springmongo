package com.example.springmongo.service

import com.example.springmongo.common.Step
import com.example.springmongo.controller.*
import com.example.springmongo.domain.Order
import com.example.springmongo.domain.OrderRepository
import com.example.springmongo.domain.OrderStep
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val mongoTemplate: MongoTemplate
) {
    fun createOrder(request: OrderRequest): OrderResponse {
        val orderId = UUID.randomUUID().toString()
        val initialSteps = listOf(
            OrderStep(step = Step.TRY_ORDER, data = "orderId: $orderId, request: $request")
        )
        val order = Order(orderId = orderId, userId = request.userId, steps = initialSteps)
        processOrderStep(order, Step.DONE_ORDER, "orderId: $orderId, request: $request")
        return OrderResponse(orderId, request.userId, Step.DONE_ORDER)
    }

    fun addPayment(request: PaymentRequest): PaymentResponse {
        val order = orderRepository.findByOrderIdAndUserId(request.orderId, request.userId)
            .orElseThrow { NoSuchElementException("Order not found with orderId: ${request.orderId} and userId: ${request.userId}") }
        val paymentId = UUID.randomUUID().toString()
        val orderAfterTryPayment = processOrderStep(order, Step.TRY_PAYMENT, "paymentId: $paymentId, request: $request")
        processOrderStep(orderAfterTryPayment, Step.DONE_PAYMENT, "paymentId: $paymentId, request: $request")
        return PaymentResponse(request.orderId, request.userId, paymentId, Step.DONE_PAYMENT)
    }

    fun addDelivery(request: DeliveryRequest): DeliveryResponse {
        val order = orderRepository.findByOrderIdAndUserId(request.orderId, request.userId)
            .orElseThrow { NoSuchElementException("Order not found with orderId: ${request.orderId} and userId: ${request.userId}") }
        val deliveryId = UUID.randomUUID().toString()
        val orderAfterTryDelivery = processOrderStep(order, Step.TRY_DELIVERY, "deliveryId: $deliveryId, request: $request")
        processOrderStep(orderAfterTryDelivery, Step.DONE_DELIVERY, "deliveryId: $deliveryId, request: $request")
        return DeliveryResponse(orderId = request.orderId, userId = request.userId, deliveryId = deliveryId, step=Step.DONE_DELIVERY)
    }

    private fun processOrderStep(order: Order, step: Step, data: String): Order {
        val updatedSteps = order.steps?.toMutableList()?.apply { add(OrderStep(step, data)) }
        return orderRepository.save(order.copy(steps = updatedSteps))
    }

    fun getOrder(orderId: String): Optional<Order> {
        return orderRepository.findByOrderId(orderId)
    }
}