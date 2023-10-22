package com.example.springmongo.common

enum class Step {
    TRY_ORDER,
    DONE_ORDER,
    TRY_PAYMENT,
    DONE_PAYMENT,
    TRY_DELIVERY,
    DONE_DELIVERY,
    TRY_CANCELED,
    DONE_CANCELED,
    FAIL,
}