package io.mobile.library.member;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberService {

    // ResultSet에서 Member 객체를 생성하는 메서드
    private static Member setMember(ResultSet rs) throws SQLException {
        String memberId = rs.getString("member_id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String phoneNumber = rs.getString("phone_number");
        Date joinDate = rs.getDate("join_date");

        return new Member(memberId, name, address, phoneNumber, joinDate);
    }

    // 모든 Member 정보를 조회하는 메서드
    public static List<Member> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        List<Member> memberList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            // Member 테이블에서 모든 데이터를 조회하는 SQL 쿼리
            String query = "SELECT * FROM member";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();

            // ResultSet에서 Member 객체 생성 후 리스트에 추가
            while (rs.next()) {
                Member member = setMember(rs);
                memberList.add(member);
            }
            return memberList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 자원 해제
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

    public static Member selectById(final String memberId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM member WHERE member_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, memberId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                // 조회된 Member 정보 반환
                Member member = setMember(rs);
                return member;
            } else {
                // member_id가 존재하지 않으면 null 반환
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            // 자원 해제
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

    public static int insert(String memberId, String name, String address, String phoneNumber, Date joinDate) {
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "INSERT INTO member (member_id, name, address, phone_number, join_date) " +
                    "VALUES (?, ?, ?, ?, ?)";

            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, memberId);    // member_id
            psmtQuery.setString(2, name);         // name
            psmtQuery.setString(3, address);      // address
            psmtQuery.setString(4, phoneNumber);  // phone_number
            psmtQuery.setDate(5, new java.sql.Date(joinDate.getTime()));     // join_date

            // 쿼리 실행 후 성공적으로 추가된 행의 수 반환
            return psmtQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // 에러 발생 시 0 반환
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int update(String memberId, String name, String address, String phoneNumber, Date joinDate) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM member WHERE member_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, memberId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String updateStatement =
                        "UPDATE member " +
                                "   SET member_id = ?, name = ?, address = ?, phone_number = ?, join_date = ? " +
                                "   WHERE member_id = ?";
                psmtUpdate = conn.prepareStatement(updateStatement);
                psmtUpdate.setString(1, memberId);
                psmtUpdate.setString(2, name);
                psmtUpdate.setString(3, address);
                psmtUpdate.setString(4, phoneNumber);
                psmtUpdate.setDate(5, new java.sql.Date (joinDate.getTime()));
                psmtUpdate.setString(6, memberId);
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

    public static int deleteById(String memberId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM member WHERE member_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, memberId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String deleteStatement =
                        "DELETE FROM member WHERE member_id = ?";
                psmtUpdate = conn.prepareStatement(deleteStatement);
                psmtUpdate.setString(1, memberId);
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