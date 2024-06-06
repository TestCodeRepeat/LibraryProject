package com.flyingobjex

import com.flyingobjex.model.BookStatus
import com.flyingobjex.model.BookType
import com.flyingobjex.model.RequestStatus
import com.flyingobjex.model.user.LibraryUserStatus
import com.flyingobjex.repository.UserRepository
import com.flyingobjex.service.LibraryService
import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * Tests here have been ordered with the most recent at the top.
 * New tests are added to the top of the file for readability's sake.
 */
class LibraryServiceTest {

    private var library = LibraryService()
    private var user = library.initWithHelperData()

    @BeforeTest
    fun before() {
        library = LibraryService()
        user = library.initWithHelperData()
    }

    @Test
    fun `book should be UNAVAILABLE after check out`() {
        val book = library.getFirstAvailableBook()
        library.requestCheckOut(book.bookId, user)

        val req = library.requestCheckOut(book.bookId, user)
        req.requestStatus shouldBe RequestStatus.DENIED
    }

    @Test
    fun `should register new library user`() {
        val newUser = UserRepository().generateAnonUser()
        val res = library.registerUser(newUser)
        res.status shouldBe LibraryUserStatus.ACTIVE
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