package com.flyingobjex.repository

import com.flyingobjex.data.generators.LocalDataImporter.importDataSource
import com.flyingobjex.model.Author
import com.flyingobjex.model.Book
import com.flyingobjex.model.BookStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BooksRepository {

    val allBooks: List<Book> = importDataSource()
    val authors = distinctAuthorsFromBooks()
    private val _booksState: MutableStateFlow<BooksState> = MutableStateFlow(initState())
    val booksState = _booksState.asStateFlow()

    private fun distinctAuthorsFromBooks(): List<Author> =
        allBooks
            .flatMap { it.authors }
            .distinctBy { it.firstName to it.lastName }
            .sortedBy { it.lastName }


    fun initState(): BooksState {
        return BooksState(books = allBooks)
    }

    fun getAvailableBooks(): List<Book> = _booksState.value.books.filter { it.bookStatus == BookStatus.AVAILABLE }
    fun getCheckOutBooks(): List<Book> = _booksState.value.books.filter { it.bookStatus == BookStatus.BORROWED }

    fun searchByBookTitle(text: String): List<Book> {
        val searchText = text.lowercase()
        return _booksState.value.books.filter { it.title.lowercase().contains(searchText) }
    }

    fun searchByAuthorName(text: String): List<Book> {
        val searchText = text.lowercase()
        val byLastName =
            _booksState.value.books.filter { it.authors.any { it.lastName.lowercase().contains(searchText) } }
        val byFirstName =
            _booksState.value.books.filter { it.authors.any { it.firstName.lowercase().contains(searchText) } }
        return byLastName + byFirstName
    }

    fun updateBook(book: Book): Book {
        val removed = _booksState.value.books.filterNot { it.bookId == book.bookId }
        val updated = removed + listOf(book)
        _booksState.value = BooksState(books = updated)
        return book
    }

    fun getBook(bookId: String): Book {
        return _booksState.value.books.firstOrNull { it.bookId == bookId } ?: throw Exception("Book not found")
    }

    /**
     * currently just leaving this as a string search
     * with a mind to standardize our import of how we handle
     * isbn numbers later
     */
    fun searchByIsbnNumber(isbn: String): Book {
        return _booksState.value.books.firstOrNull { it.isbn.contains(isbn) } ?: throw Exception("Book not found")
    }
}