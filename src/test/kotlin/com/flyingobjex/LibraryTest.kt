package com.flyingobjex

import com.flyingobjex.model.BookStatus
import com.flyingobjex.model.BookType
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class LibraryTest {


    @Test
    fun `should search by book title`() {

    }

    @Test
    fun `should search for book by author name`() {
        val library = Library()
        val books = library.searchByAuthorName("Baue")
        books.isNotEmpty() shouldBe true
    }

    @Test
    fun `some books should be of type Reference and the rest of type General`() {
        val library = Library()
        library.books.any { it.bookType == BookType.REFERENCE } shouldBe true
        library.books.any { it.bookType == BookType.GENERAL } shouldBe true
    }

    @Test
    fun `all books should start as AVAILABLE`() {
        val library = Library()
        library.books.all { it.bookStatus == BookStatus.AVAILABLE } shouldBe true
    }

    @Test
    fun `should import book data`() {
        val library = Library()
        library.books.isNotEmpty() shouldBe true
        library.authors.isNotEmpty() shouldBe true
    }
}