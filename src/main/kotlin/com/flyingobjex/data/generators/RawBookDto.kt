package com.flyingobjex.data.generators

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BooksImportDto(
    @SerialName("books")
    val books: List<BookImportDto>
)

@Serializable
data class BookImportDto(
    @SerialName("authors")
    val authors: List<String>,
    @SerialName("categories")
    val categories: List<String>,
    @SerialName("isbn")
    val isbn: String? = null,
    @SerialName("longDescription")
    val longDescription: String? = null,
    @SerialName("pageCount")
    val pageCount: Int,
    @SerialName("publishedDate")
    val publishedDate: PublishedDate? = null,
    @SerialName("shortDescription")
    val shortDescription: String? = null,
    @SerialName("status")
    val status: String,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String? = null,
    @SerialName("title")
    val title: String
)

@Serializable
data class PublishedDate(
    @SerialName("publishedDate")
    val publishedDate: String
)