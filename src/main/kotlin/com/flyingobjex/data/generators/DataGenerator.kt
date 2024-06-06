package com.flyingobjex.data.generators

import com.flyingobjex.model.Author
import java.util.UUID
import kotlin.random.Random


/**
 * Made a quick sketch of random data generator for discussion purposes only.
 * In the past I've found it extremely valuable to be able to quickly generate
 * a large quantity of well-formed data for development & integration testing
 * purposes.
 *
 *
 */
object DataGenerator {

    fun generateAuthors(count: Int): List<Author> {
        return List(count) {
            Author(
                authorId = UUID.randomUUID().toString(),
                firstName = firstNames.random(),
                lastName = lastNames.random()
            )
        }
    }

    fun generateUserName():String{
        return "${firstNames.random()} ${lastNames.random()}"

    }

}

private val firstNames = listOf(
    "Amy", "John", "Peter", "Emily", "Michael", "Sarah",
    "David", "Laura", "Daniel", "Emma", "James", "Olivia",
    "William", "Sophia", "Logan", "Isabella", "Benjamin",
    "Mia", "Lucas", "Charlotte", "Henry", "Amelia", "Alexander",
    "Evelyn", "Jacob", "Harper", "Jack", "Abigail", "Owen",
    "Avery", "Elijah", "Ella", "Mason", "Grace", "Ethan",
    "Victoria", "Sebastian", "Chloe", "Aiden", "Zoey", "Samuel",
    "Penelope", "Matthew", "Riley", "Joseph", "Lily", "Leo",
    "Lillian", "Jackson", "Natalie"
)

private val lastNames = listOf(
    "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis",
    "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas",
    "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
    "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee",
    "Walker", "Hall", "Allen", "Young", "Hernandez", "King",
    "Wright", "Lopez", "Hill", "Scott", "Green", "Adams",
    "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez",
    "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans",
    "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers",
    "Reed", "Cook", "Morgan", "Bell"
)


// List of random words for generating book titles
val words = listOf(
    "Adventures",
    "Guide",
    "Mystery",
    "Secret",
    "Chronicles",
    "Tales",
    "Journey",
    "Saga",
    "Story",
    "Memoir",
    "Quest",
    "Legend",
    "Manual",
    "Handbook",
    "Compendium",
    "Novel",
    "Narrative",
    "Volume",
    "Collection",
    "Account",
    "Diary",
    "Journal",
    "Encyclopedia",
    "Reference",
    "Introduction",
    "Masterpiece",
    "Creation",
    "Work",
    "Opus",
    "Piece",
    "Script",
    "Manuscript",
    "Text",
    "Thesis",
    "Treatise",
    "Monograph",
    "Discourse",
    "Book",
    "Pamphlet",
    "Publication",
    "Anthology",
    "Essays",
    "Short Stories",
    "Novellas",
    "Fiction",
    "Non-Fiction",
    "Biography",
    "Autobiography",
    "Memoirs",
    "Articles",
    "Papers",
    "Reports",
    "Studies",
    "Research",
    "Findings",
    "Proceedings",
    "Transactions",
    "Abstracts",
    "Reviews",
    "Critique",
    "Analysis",
    "Synopsis",
    "Outline",
    "Sketch",
    "Plot",
    "Framework",
    "Plan",
    "Blueprint",
    "Design",
    "Layout",
    "Diagram",
    "Illustration",
    "Map",
    "Chart",
    "Graph",
    "Table",
    "Figure",
    "Graphic",
    "Image",
    "Picture",
    "Photo",
    "Snapshot",
    "Portrait",
    "Painting",
    "Drawing",
    "Sketch",
    "Diagram",
    "Scheme",
    "Model",
    "Pattern",
    "Prototype",
    "Template",
    "Sample",
    "Specimen",
    "Exemplar",
    "Example",
    "Case Study"
)

// Function to generate random book titles
fun generateRandomBookTitles(count: Int): List<String> {
    val randomBookTitles = mutableListOf<String>()
    for (i in 0 until count) {
        val title = buildString {
            append(words.random())
            append(" ")
            append(words.random())
            if (Random.nextBoolean()) {
                append(" of ")
                append(words.random())
            }
            if (Random.nextBoolean()) {
                append(": ")
                append(words.random())
            }
        }
        randomBookTitles.add(title)
    }
    return randomBookTitles
}

