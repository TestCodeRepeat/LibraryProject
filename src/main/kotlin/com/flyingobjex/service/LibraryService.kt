package com.flyingobjex.service

import com.flyingobjex.model.Book
import com.flyingobjex.model.BookStatus
import com.flyingobjex.model.BookType
import com.flyingobjex.model.CheckOutRequest
import com.flyingobjex.model.RequestStatus
import com.flyingobjex.repository.BooksRepository
import com.flyingobjex.model.user.LibraryUser
import com.flyingobjex.model.user.LibraryUserStatus
import com.flyingobjex.repository.TransactionRepository
import com.flyingobjex.repository.UserRepository
import java.util.UUID


class LibraryService {

    private val booksRepository = BooksRepository()
    private val userRepository = UserRepository()
    private val transactionRepository = TransactionRepository()

    /**
     * Convenience accessors
     */
    val allBooks = booksRepository.allBooks
    val allAuthors = booksRepository.authors

    fun availableBooks(): List<Book> = booksRepository.getAvailableBooks()
    fun checkedOutBooks(): List<Book> = booksRepository.getCheckOutBooks()
    fun getBook(bookId: String): Book = booksRepository.getBook(bookId)

    fun registerUser(user: LibraryUser): LibraryUser = userRepository.registerUser(user)

    /**
     * Public
     */
    fun searchByBookTitle(text: String): List<Book> = booksRepository.searchByBookTitle(text)
    fun searchByAuthorName(text: String): List<Book> = booksRepository.searchByAuthorName(text)
    fun searchByIsbnNumber(isbnNumber: String): Book = booksRepository.searchByIsbnNumber(isbnNumber)
    fun requestCheckOut(bookId: String, user: LibraryUser): CheckOutRequest {
        val book = booksRepository.getBook(bookId)
        val request = transactionRepository.saveRequest(createBookRequest(book, user))
        return if (checkIfAuthorized(user, book)) {
            transactionRepository.updateRequest(request.copy(requestStatus = RequestStatus.DENIED))
        } else {
            booksRepository.updateBook(book.copy(bookStatus = BookStatus.BORROWED))
            transactionRepository.updateRequest(request.copy(requestStatus = RequestStatus.COMPLETED))
        }
    }

    /**
     * Private
     */
    private fun checkIfAuthorized(user: LibraryUser, book: Book) =
        user.status != LibraryUserStatus.ACTIVE || book.bookStatus != BookStatus.AVAILABLE || book.bookType == BookType.REFERENCE

    private fun createBookRequest(
        book: Book,
        user: LibraryUser
    ) = CheckOutRequest(
        requestId = UUID.randomUUID().toString(),
        bookId = book.bookId,
        bookTitle = book.title,
        userId = user.id,
        userName = user.name,
        requestStatus = RequestStatus.INITIATED
    )

    /**
     * Convenience method to initialize the library with some data
     */
    fun initWithHelperData(): LibraryUser {
        val user = userRepository.generateAnonUser()
        userRepository.registerUser(user)
        return user
    }

    /**
     * Convenience method for quick access of available book
     */
    fun getFirstAvailableBook(): Book {
        return booksRepository.booksState.value.books.first { it.bookStatus == BookStatus.AVAILABLE && it.bookType == BookType.GENERAL }
    }

}