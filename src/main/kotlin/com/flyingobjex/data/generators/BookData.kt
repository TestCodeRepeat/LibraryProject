package com.flyingobjex.data.generators
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class BookData(
    @SerialName("books")
    val books: List<Book>
)

@Serializable
data class Book(
    @SerialName("authors")
    val authors: List<String>,
    @SerialName("categories")
    val categories: List<String>,
    @SerialName("isbn")
    val isbn: String?,
    @SerialName("longDescription")
    val longDescription: String?,
    @SerialName("pageCount")
    val pageCount: Int,
    @SerialName("publishedDate")
    val publishedDate: PublishedDate?,
    @SerialName("shortDescription")
    val shortDescription: String?,
    @SerialName("status")
    val status: String,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerialName("title")
    val title: String
)

@Serializable
data class PublishedDateD(
    @SerialName("publishedDate")
    val publishedDate: String
)