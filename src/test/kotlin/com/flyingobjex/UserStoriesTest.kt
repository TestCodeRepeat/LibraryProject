package com.flyingobjex

import com.flyingobjex.model.BookStatus
import com.flyingobjex.model.BookType
import com.flyingobjex.model.RequestStatus
import com.flyingobjex.service.LibraryService
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserStoriesTest {

    private var library = LibraryService()
    private var user = library.initWithHelperData()

    @BeforeTest
    fun before() {
        library = LibraryService()
        user = library.initWithHelperData()
    }

    /**
     * As a library user, I would like to be able to find books by my
     * favourite author, so that I know if they are available in the library
     */
    @Test
    fun `should search for book by author name`() {
        val books = library.searchByAuthorName("Baue")
        books.isNotEmpty() shouldBe true
    }

    /**
     * As a library user, I would like to be able to find books by title, so that I know
     * if they are available in the library.
     */
    @Test
    fun `should search by book title`() {
        val books = library.searchByBookTitle("Hibernate")
        books.isNotEmpty() shouldBe true
    }

    /**
     * As a library user, I would like to be able to find books by ISBN, so that
     * I know if they are available in the library.
     */
    @Test
    fun `should search by isbn number`() {
        val books = library.searchByIsbnNumber("193239415X")
        books shouldNotBe null
    }

    /**
     * As a library user, I would like to be able to borrow a book, so I can read it at home.
     */
    @Test
    fun `user should be allowed to check out AVAILABLE book`() {
        val book = library.getFirstAvailableBook()
        val res = library.requestCheckOut(book.bookId, user)
        res.requestStatus shouldBe RequestStatus.COMPLETED
        val updatedBook = library.getBook(book.bookId)
        updatedBook.bookStatus shouldBe BookStatus.BORROWED
    }

    /**
     * As the library owner, I would like to know how many books are being borrowed,
     * so I can see how many are outstanding.
     */
    @Test
    fun `library owner should be able to get a checked out book count`() {
        library.checkedOutBooks().size shouldBe 0
        val book = library.getFirstAvailableBook()
        library.requestCheckOut(book.bookId, user)
        library.checkedOutBooks().size shouldBe 1
    }

    /**
     * As a library user, I should be to prevented from borrowing reference books,
     * so that they are always available.
     */
    @Test
    fun `user should NOT be able to check out REFERENCE book`() {
        val book = library.availableBooks()
            .first { it.bookStatus == BookStatus.AVAILABLE && it.bookType == BookType.REFERENCE }
        val res = library.requestCheckOut(book.bookId, user)
        res.requestStatus shouldBe RequestStatus.DENIED
    }

}