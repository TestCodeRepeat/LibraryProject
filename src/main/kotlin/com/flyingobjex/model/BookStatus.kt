package com.flyingobjex.model

/**
 * Available - user has checked book out of library
 * Borrowed - user has checked book out of library
 * Returned - user has returned book to library but it has not
 * been placed back on the shelf
 */
enum class BookStatus {
    AVAILABLE,
    BORROWED,
    RETURNED
}
