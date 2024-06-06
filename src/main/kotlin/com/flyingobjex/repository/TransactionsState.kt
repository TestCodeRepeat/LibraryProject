package com.flyingobjex.repository

import com.flyingobjex.model.CheckOutRequest

data class TransactionsState(
    val transactions: List<CheckOutRequest>
)