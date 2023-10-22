package com.example.springmongo.service

import com.example.springmongo.controller.DeliveryRequest
import com.example.springmongo.controller.OrderRequest
import com.example.springmongo.controller.PaymentRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderServiceTest{
    @Autowired
    lateinit var orderService: OrderService
    @Test
    fun createOrder() {
        val order = orderService.createOrder(OrderRequest(
            goodsId = "t-001",
            goodsName = "테스트 상품",
            goodsPrice = 1000.0,
            userId = "u-001",
            quantity = 1,
        ))

        val payment = orderService.addPayment(PaymentRequest(
            orderId = order.orderId,
            userId = order.userId,
            amount = 1000.0,
            paymentType = "CARD",
        ))
        val delivery = orderService.addDelivery(
            DeliveryRequest(
            orderId = order.orderId,
            userId = order.userId,
            address = "서울시 강남구",
        ))
        println("order: $order")

        val result = orderService.getOrder(order.orderId)
        println("result: $result")
    }
}