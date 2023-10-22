package com.example.springmongo.controller

import com.example.springmongo.common.Step

data class OrderRequest(
    val goodsId: String,
    val goodsName: String,
    val goodsPrice: Double,
    val quantity: Int = 1,
    val userId: String,
)
