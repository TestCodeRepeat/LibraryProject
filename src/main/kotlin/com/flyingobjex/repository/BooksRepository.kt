package com.flyingobjex.repository

import com.flyingobjex.data.generators.LocalDataImporter.importDataSource
import com.flyingobjex.model.Author
import com.flyingobjex.model.Book
import com.flyingobjex.model.BookStatus
import kotlinx.coroutines.flow.MutableStateFlow

class BooksRepository {

    val allBooks: List<Book> = importDataSource()
    val authors = distinctAuthorsFromBooks()
    val booksState: MutableStateFlow<BooksState> = MutableStateFlow(initState())

    private fun distinctAuthorsFromBooks(): List<Author> =
        allBooks
            .flatMap { it.authors }
            .distinctBy { it.firstName to it.lastName }
            .sortedBy { it.lastName }


    fun initState(): BooksState {
        return BooksState(books = allBooks)
    }

    fun getAvailableBooks(): List<Book> = booksState.value.books.filter { it.bookStatus == BookStatus.AVAILABLE }
    fun getCheckOutBooks(): List<Book> = booksState.value.books.filter { it.bookStatus == BookStatus.BORROWED }

    fun searchByBookTitle(text: String): List<Book> {
        val searchText = text.lowercase()
        return booksState.value.books.filter { it.title.lowercase().contains(searchText) }
    }

    fun searchByAuthorName(text: String): List<Book> {
        val searchText = text.lowercase()
        val byLastName =
            booksState.value.books.filter { it.authors.any { it.lastName.lowercase().contains(searchText) } }
        val byFirstName =
            booksState.value.books.filter { it.authors.any { it.firstName.lowercase().contains(searchText) } }
        return byLastName + byFirstName
    }

    fun updateBook(book: Book): Book {
        val removed = booksState.value.books.filterNot { it.bookId == book.bookId }
        val updated = removed + listOf(book)
        booksState.value = BooksState(books = updated)
        return book
    }

    fun getBook(bookId: String): Book {
        return booksState.value.books.firstOrNull { it.bookId == bookId } ?: throw Error("Book not found")
    }
}