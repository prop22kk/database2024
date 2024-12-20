package io.mobile.library.loan;

import io.mobile.library.book.Book;
import io.mobile.library.book.BookService;

import java.sql.Date;
import java.util.List;

public class LoanMain {
    public static void main(String[] args) {
        System.out.println("전체검색");
        List<Loan> loanList = LoanService.selectAll();
        for (Loan loan : loanList) {
            System.out.println(loan);
        }

        System.out.println("memberId로 검색");
        List<Loan> loans = LoanService.selectByMemberId("sleepy"); // List<Loan> 반환
        if (loans != null && !loans.isEmpty()) {
            for (Loan loan : loans) {
                System.out.println(loan);
            }
        } else {
            System.out.println("member1 not exist !!");
        }

        System.out.println("loanId로 검색");
        Loan aa = LoanService.selectById("1002");
        if (aa != null) {
            System.out.println(aa);
        } else {
            System.out.println("member1 not exist !!");
        }

        System.out.println("책 대출 정보 추가");
        // Date 값을 올바르게 생성하여 입력
        if (LoanService.insert("1004", "candy", "a003", Date.valueOf("2024-12-12"), Date.valueOf("2024-12-29"), true) > 0) {
            Loan a = LoanService.selectById("1004");
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("1004 not exist !!");
            }
        } else {
            System.out.println("대출 정보 추가에 실패했습니다.");
        }

        System.out.println("대출 정보 수정");
        if (LoanService.update("1004", "candy", "a003", Date.valueOf("2024-12-12"), Date.valueOf("2024-12-24"), true) > 0) {
            Loan a = LoanService.selectById("1004");
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("a004 not exist !!");
            }
        } else {
            System.out.println("1004 대출 정보 수정에 실패했습니다.");
        }

        System.out.println("대출 정보 삭제");
        if (LoanService.deleteById("1004") > 0) {
            Loan a = LoanService.selectById("1004");
            if (a != null) {
                System.out.println("1004 exist !!");
            } else {
                System.out.println("1004 정보 삭제에 성공했습니다.");
            }
        } else {
            System.out.println("책 정보 삭제에 실패했습니다.");
        }
    }
}
