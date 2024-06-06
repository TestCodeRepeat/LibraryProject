# Project Overview
This project is a simple Library service that allows users to search for books by author, title, or ISBN. 
Users can also borrow books and the library owner can see how many books are being borrowed. 
The library service also prevents users from borrowing reference books.
It is created within a Ktor server application, however we have not added a
controller or http routes in this example yet.

Key folders for source code are found in `/src/main/kotlin/com`

`/data/generators` - used to import dummy data

`/repository` - holds our application state (in-memory only)

`/model` - domain model with minial Author, Book, User data

`/test` - our tests are where the functionality is demonstrated


To run the tests, run in IntelliJ / IDE or run the following terminal command:
```
./gradlew test
```

## Data Import / DTOs
We have started with some book data found online & imported it into our own data model.  
This is kept in a separate package with the intent of being able to handle
imports of different data sources in the future.

## Domain Model
Our Model package only contains the minimal amount of data that seemed necessary
to fulfill the user stories, with a mind to expand as needed.

## Test-Driven & Text Based
All functionality can be previewed by running the tests in an IDE or with gradle.
Please see the UserStories test for top-level QA, as those are one-to-one match
with the User Stories requested below. The other tests were just a result of 
normal TDD development.

### Library Service
Our top-level entry point for this example, all of our functionality is available in the Public functions here
This service can then be used by Controllers or other services to provide the functionality to users.


* For th sake of this sample project, we have wrapped the entirety of the
our functionality in this service, with a mind to discuss how it could be later
split into a larger application as it grows.

We have also created a handful of convenience functions to allow for easy access to the data.

## Repositories
There are three primary Repositories used to handle data & state.
For now we are just holding state with Kotlin Flows as the simplest starting point,
with a mind to move state to a database or other storage later on.

#### BookRepository
Handles import & in-memory state of all our books

#### UserRepository
We created a simple user as a starting point for discussions

#### TransactionRepository
Most notably, we created a separate repository for transactions to handle the borrowing of books.
We intend to first capture all the requests before processing, so that we can
keep a record of all requests regardless of success or failure.  This allows us to build
both a richer recordset for analytics later on as well has handle transactions that
progress through multiple states.

### Discussion Points & Questions
- a Ktor application wrapper was added as a starting point but no controllers or routes have been added yet.  
  - Can we assume the LibraryService would be next be consumed by controllers? 
- Author Tables - how will we associate books written with similar, but slightly different author names?
- Search Matching - we've allowed for partial text matching on searches, how should we sort results?

Stories:

- As a library user, I would like to be able to find books by my favourite author, so that I know if they are available in the library.
- As a library user, I would like to be able to find books by title, so that I know if they are available in the library.
- As a library user, I would like to be able to find books by ISBN, so that I know if they are available in the library.
- As a library user, I would like to be able to borrow a book, so I can read it at home.
- As the library owner, I would like to know how many books are being borrowed, so I can see how many are outstanding.
- As a library user, I should be to prevented from borrowing reference books, so that they are always available.


