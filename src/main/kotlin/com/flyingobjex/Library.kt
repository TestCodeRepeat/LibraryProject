package com.flyingobjex

import com.flyingobjex.data.generators.LocalDataImporter.importDataSource
import com.flyingobjex.model.Author
import com.flyingobjex.model.Book

class Library {

    val books: List<Book>
    val authors: List<Author>

    init {
        books = importDataSource()
        authors = distinctAuthorsFromBooks()
    }

    private fun distinctAuthorsFromBooks(): List<Author> =
        books
            .flatMap { it.authors }
            .distinctBy { it.firstName to it.lastName }
            .sortedBy { it.lastName }


}