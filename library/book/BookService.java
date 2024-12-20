package io.mobile.library.book;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookService {
    private static Book setBook(ResultSet rs) throws SQLException {
        String bookId = rs.getString("book_id");
        String title = rs.getString("title");
        String publishYear = rs.getString("publish_year");

        return new Book(bookId, title, publishYear);
    }

    public static List<Book> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        List<Book> bookList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM book";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Book book = setBook(rs);
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException ignored) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException _) {
                }
            }
        }
    }

    public static List<Book> selectByTitle(final String title) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM book WHERE title = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, title);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Book book = setBook(rs); // 가정: setLoan(ResultSet rs)가 Loan 객체를 생성
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
        return books;
    }

    public static Book selectById(final String bookId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM book WHERE book_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, bookId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                Book book = setBook(rs);
                return book;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int insert (final String bookId, final String title, final String publishYear) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM book WHERE book_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, bookId);
            rs = psmtQuery.executeQuery();

            if (!rs.next()) {
                String insertStatement = "INSERT INTO book(book_id, title, publish_year) " +
                        "VALUES(?, ?, ?)";
                psmtUpdate = conn.prepareStatement(insertStatement);
                psmtUpdate.setString(1, bookId);
                psmtUpdate.setString(2, title);
                psmtUpdate.setString(3, publishYear);

                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int deleteById(String bookId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM book WHERE book_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, bookId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String deleteStatement =
                        "DELETE FROM book WHERE book_id = ?";
                psmtUpdate = conn.prepareStatement(deleteStatement);
                psmtUpdate.setString(1, bookId);
                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int update(String bookId, String title, String publishYear) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM book WHERE book_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, bookId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String updateStatement =
                        "UPDATE book " +
                                "   SET title = ?, publish_year = ? " +
                                "   WHERE book_id = ?";
                psmtUpdate = conn.prepareStatement(updateStatement);
                psmtUpdate.setString(1, title);
                psmtUpdate.setString(2, publishYear);
                psmtUpdate.setString(3, bookId);
                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

}
