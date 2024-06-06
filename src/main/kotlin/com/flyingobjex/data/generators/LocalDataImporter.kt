package com.flyingobjex.data.generators

import com.flyingobjex.model.Author
import com.flyingobjex.model.Book
import com.flyingobjex.model.BookStatus
import com.flyingobjex.model.BookType
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID
import kotlin.random.Random
import kotlinx.serialization.json.Json

object LocalDataImporter {

    fun importDataSource(): List<Book> {
        val path = Paths.get("src/main/resources/books.json")
        val data = Files.readString(path)
        val importedBooks = Json.decodeFromString<BooksImportDto>(data)
        val newBooks = importedBooks.books.map { book ->
            Book(
                isbn = book.isbn ?: "na", // if missing ISBN we just assign our own for now
                bookId = UUID.randomUUID().toString(),
                title = book.title,
                authors = mapAuthors(book),
                bookType = randomBookType(),
                bookStatus = BookStatus.AVAILABLE
            )
        }
        return newBooks
    }

    /**
     * We're randomly assigning a book type based on a 20% chance of being a reference book
     */
    fun randomBookType(): BookType {
        return if (Random.nextInt(10) < 2) BookType.REFERENCE else BookType.GENERAL
    }

    private fun mapAuthors(book: BookImportDto) = book.authors.map { author ->
        val names = author.split(" ")
        val firstName = if (names.size > 2) {
            names.take(2).joinToString(" ")
        } else {
            names.first()
        }
        Author(
            authorId = UUID.randomUUID().toString(),
            firstName = firstName,
            lastName = names.last()
        )
    }
}