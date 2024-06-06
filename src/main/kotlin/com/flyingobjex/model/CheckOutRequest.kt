package com.flyingobjex.model

import kotlinx.serialization.Serializable

@Serializable
data class CheckOutRequest(
    val requestId:String,
    val bookId: String,
    val bookTitle:String,
    val userId: String,
    val userName:String,
    val requestStatus: RequestStatus
)