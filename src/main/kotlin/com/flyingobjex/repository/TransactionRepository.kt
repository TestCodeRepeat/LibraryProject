package com.flyingobjex.repository

import com.flyingobjex.model.CheckOutRequest
import kotlinx.coroutines.flow.MutableStateFlow

class TransactionRepository {

    private val transactionState: MutableStateFlow<TransactionsState> =
        MutableStateFlow(TransactionsState(transactions = emptyList()))

    fun saveRequest(request: CheckOutRequest): CheckOutRequest {
        val updated = listOf(request) + transactionState.value.transactions
        transactionState.value = TransactionsState(transactions = updated)
        return request
    }

    fun updateRequest(request: CheckOutRequest): CheckOutRequest {
        val removed = transactionState.value.transactions.filterNot { it.requestId == request.requestId }
        val updated = removed + listOf(request)
        transactionState.value = TransactionsState(transactions = updated)
        return request
    }
}