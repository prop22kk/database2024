package io.mobile.library.loan;


import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanService {
    private static Loan setLoan(ResultSet rs) throws SQLException {
        String loanId = rs.getString("loan_id");
        String memberId = rs.getString("member_id");
        String bookId = rs.getString("book_id");
        Date loanDate = rs.getDate("loan_date");
        Date return_date = rs.getDate("return_date");
        boolean status = rs.getBoolean("status");

        return new Loan(loanId, memberId, bookId, loanDate, status, return_date);
    }

    public static List<Loan> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        List<Loan> loanList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM loan";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Loan loan = setLoan(rs);
                loanList.add(loan);
            }
            return loanList;
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

    public static List<Loan> selectByMemberId(String memberId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        List<Loan> loans = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM loan WHERE member_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, memberId);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Loan loan = setLoan(rs);
                loans.add(loan);
            }

        } catch (SQLException e) {
            e.printStackTrace();

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
        return loans;
    }



    public static int insert (String loanId, String memberId, String bookId, Date loanDate, Date returnDate, boolean status) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM loan WHERE loan_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, bookId);
            rs = psmtQuery.executeQuery();

            if (!rs.next()) {
                String insertStatement = "INSERT INTO loan(loan_id, member_id, book_id, loan_date, return_date, status) " +
                        "VALUES(?, ?, ?, ?, ?, ?)";
                psmtUpdate = conn.prepareStatement(insertStatement);
                psmtUpdate.setString(1, loanId);
                psmtUpdate.setString(2, memberId);
                psmtUpdate.setString(3, bookId);
                psmtUpdate.setDate(4, new java.sql.Date (loanDate.getTime()));
                psmtUpdate.setDate(5, new java.sql.Date (returnDate.getTime()));
                psmtUpdate.setBoolean(6, status);

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

    public static Loan selectById(String loanId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM loan WHERE loan_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, loanId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                Loan loan = setLoan(rs);
                return loan;
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

    public static int deleteById(String loanId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM loan WHERE loan_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, loanId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String deleteStatement =
                        "DELETE FROM loan WHERE loan_id = ?";
                psmtUpdate = conn.prepareStatement(deleteStatement);
                psmtUpdate.setString(1, loanId);
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

    public static int update(String loanId, String memberId, String bookId, Date loanDate, Date returnDate, boolean status) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM loan WHERE loan_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, loanId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String updateStatement =
                        "UPDATE loan " +
                                "   SET loan_id = ?, member_id = ?, book_id = ?, loan_date = ?, return_date = ?, status = ? " +
                                "   WHERE loan_id = ?";
                psmtUpdate = conn.prepareStatement(updateStatement);
                psmtUpdate.setString(1, loanId);
                psmtUpdate.setString(2, memberId);
                psmtUpdate.setString(3, bookId);
                psmtUpdate.setDate(4, new java.sql.Date (loanDate.getTime()));
                psmtUpdate.setDate(5, new java.sql.Date (returnDate.getTime()));
                psmtUpdate.setBoolean(6, status);
                psmtUpdate.setString(7, loanId);
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