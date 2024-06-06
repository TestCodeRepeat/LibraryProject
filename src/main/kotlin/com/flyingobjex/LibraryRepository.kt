package com.flyingobjex

import com.flyingobjex.data.generators.LocalDataImporter.importDataSource
import com.flyingobjex.model.Author
import com.flyingobjex.model.Book
import kotlinx.coroutines.flow.MutableStateFlow

class LibraryRepository {

    val libraryState: MutableStateFlow<LibraryState>
    val books: List<Book>
    val authors: List<Author>

    private fun distinctAuthorsFromBooks(): List<Author> =
        books
            .flatMap { it.authors }
            .distinctBy { it.firstName to it.lastName }
            .sortedBy { it.lastName }

    init {
        books = importDataSource()
        authors = distinctAuthorsFromBooks()
        libraryState = MutableStateFlow(initState())
    }

    fun initState(): LibraryState {
        return LibraryState(books = books)
    }
}