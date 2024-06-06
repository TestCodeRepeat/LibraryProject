package com.flyingobjex

import com.flyingobjex.model.Book

data class LibraryState(
    val books: List<Book>
)

class Library {

    val libraryRepository = LibraryRepository()

    val books = libraryRepository.books
    val authors = libraryRepository.authors

    fun searchByBookTitle(text:String):List<Book>{
        val searchText = text.lowercase()
        return libraryRepository.libraryState.value.books.filter { it.title.lowercase().contains(searchText) }
    }

    fun searchByAuthorName(text: String): List<Book> {
        val searchText = text.lowercase()
        val byLastName =
            libraryRepository.libraryState.value.books.filter { it.authors.any { it.lastName.lowercase().contains(searchText) } }
        val byFirstName =
            libraryRepository.libraryState.value.books.filter { it.authors.any { it.firstName.lowercase().contains(searchText) } }
        return byLastName + byFirstName
    }



}