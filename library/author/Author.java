package io.mobile.library.author;

public class Author {

    private String bookId;
    private String author;

    public Author(final String bookId, final String author) {
        this.bookId = bookId;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Author [book_id=" + bookId + ", author=" + author + "]";
    }
}
