package com.flyingobjex.model

/**
 * @param bookId - unique identifier for book (in case of multiple copies)
 */
data class Book(
    val isbn: String,
    val bookId:String,
    val title: String,
    val authors: List<Author>,
    val bookType: BookType,
    val bookStatus: BookStatus
)