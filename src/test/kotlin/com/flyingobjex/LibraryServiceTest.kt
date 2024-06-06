package com.flyingobjex

import com.flyingobjex.model.BookStatus
import com.flyingobjex.model.BookType
import com.flyingobjex.model.CheckOutRequest
import com.flyingobjex.model.RequestStatus
import com.flyingobjex.model.user.LibraryUserStatus
import com.flyingobjex.repository.UserRepository
import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test

class LibraryServiceTest {

    var library = LibraryService()

    @BeforeTest
    fun before() {
        library = LibraryService()
    }

    @Test
    fun `book should be UNAVAILABLE after check out`() {
        val user = library.initWithHelperData()
        val book = library.availableBooks().first { it.bookStatus == BookStatus.AVAILABLE }
        library.requestCheckOut(book.bookId, user)

        val req = library.requestCheckOut(book.bookId, user)
        req.requestStatus shouldBe RequestStatus.DENIED
    }

    @Test
    fun `user should be allowed to check out AVAILABLE book`() {
        val user = library.initWithHelperData()
        val book = library.availableBooks().first { it.bookStatus == BookStatus.AVAILABLE }
        val res = library.requestCheckOut(book.bookId, user)
        res.requestStatus shouldBe RequestStatus.COMPLETED
        val updatedBook = library.getBook(book.bookId)
        updatedBook.bookStatus shouldBe BookStatus.BORROWED
    }

    @Test
    fun `should register new library user`() {
        val newUser = UserRepository().generateAnonUser()
        val res = library.registerUser(newUser)
        res.status shouldBe LibraryUserStatus.ACTIVE
    }

    @Test
    fun `should search by book title`() {
        val books = library.searchByBookTitle("Hibernate")
        books.isNotEmpty() shouldBe true
    }

    @Test
    fun `should search for book by author name`() {
        val books = library.searchByAuthorName("Baue")
        books.isNotEmpty() shouldBe true
    }

    @Test
    fun `some books should be of type Reference and the rest of type General`() {
        library.allBooks.any { it.bookType == BookType.REFERENCE } shouldBe true
        library.allBooks.any { it.bookType == BookType.GENERAL } shouldBe true
    }

    @Test
    fun `all books should start as AVAILABLE`() {
        library.allBooks.all { it.bookStatus == BookStatus.AVAILABLE } shouldBe true
    }

    @Test
    fun `should import book data`() {
        library.allBooks.isNotEmpty() shouldBe true
        library.allAuthors.isNotEmpty() shouldBe true
    }
}