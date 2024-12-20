package io.mobile.library.author;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {

    private static Author setAuthor(ResultSet rs) throws SQLException {
        String bookId = rs.getString("book_id");
        String author = rs.getString("author");
        return new Author(bookId, author);
    }

    public static List<Author> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        List<Author> authorList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM author";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Author author = setAuthor(rs);
                authorList.add(author);
            }
            return authorList;
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

    public static int insert(String bookId, String author) {
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String insertStatement = "INSERT INTO author (book_id, author) VALUES (?, ?)";
            psmtUpdate = conn.prepareStatement(insertStatement);
            psmtUpdate.setString(1, bookId);
            psmtUpdate.setString(2, author);

            return psmtUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (psmtUpdate != null) {
                try {
                    psmtUpdate.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    public static int delete(String bookId, String author) {
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String deleteStatement = "DELETE FROM author WHERE book_id = ? AND author = ?";
            psmtUpdate = conn.prepareStatement(deleteStatement);
            psmtUpdate.setString(1, bookId);
            psmtUpdate.setString(2, author);

            return psmtUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (psmtUpdate != null) {
                try {
                    psmtUpdate.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    public static int update(String oldBookId, String oldAuthor, String newBookId, String newAuthor) {
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

            String updateStatement = "UPDATE author SET book_id = ?, author = ? WHERE book_id = ? AND author = ?";

            psmtUpdate = conn.prepareStatement(updateStatement);
            psmtUpdate.setString(1, newBookId);  // 새 책 번호
            psmtUpdate.setString(2, newAuthor);  // 새 저자 이름
            psmtUpdate.setString(3, oldBookId);  // 기존 책 번호
            psmtUpdate.setString(4, oldAuthor);  // 기존 저자 이름

            return psmtUpdate.executeUpdate(); // 수정된 행 수 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // 오류 발생 시 0 반환
        } finally {
            if (psmtUpdate != null) {
                try {
                    psmtUpdate.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }


    public static Author select(String bookId, String author) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        String query = "SELECT * FROM author WHERE book_id = ? AND author = ?";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, bookId);  // book_id
            psmtQuery.setString(2, author);  // author
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                return setAuthor(rs);
            } else {
                return null;  // 결과가 없으면 null 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;  // 예외 발생 시 null 반환
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
                } catch (SQLException ignored) {
                }
            }
        }
    }


}
