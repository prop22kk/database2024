package io.mobile.library.book;

public class Book {

    private String bookId;
    private String title;
    private String publishYear;

    public Book(final String bookId, final String title, final String publishYear) {
        this.bookId = bookId;
        this.title = title;
        this.publishYear = publishYear;
    }
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }
    public String getPublishYear() {
        return publishYear;
    }

    @Override
    public String toString() {
        return "Book [book_id=" + bookId +
                ", title=" + title + ", publisher=" + publishYear;
    }
}
