package io.mobile.library.member;

import io.mobile.library.book.Book;
import io.mobile.library.book.BookService;

import java.sql.Date;
import java.util.List;

public class MemberMain {

    public static void main(String[] args) {

        System.out.println("\n전체 회원 정보 검색하기");
        List<Member> memberList = MemberService.selectAll();
        for (Member member : memberList) {
            System.out.println(member);
        }

        System.out.println("memberId로 검색");
        Member member = MemberService.selectById("sleepy");
        if (member != null) {
            System.out.println(member);
        } else {
            System.out.println("sleepy not exist !!");
        }

        System.out.println("회원 추가");
        if (MemberService.insert("newId", "newName", "서울", "010-1234-1234", Date.valueOf("2012-03-11")) > 0){
            Member a = MemberService.selectById("newId");
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("newId not exist !!");
            }
        }else {
            System.out.println("책 추가에 실패했습니다.");
        }

        System.out.println("회원 정보 수정");
        if (MemberService.update("newId", "newName", "인천", "010-1234-1234", Date.valueOf("2012-03-11")) > 0) {
            Member a = MemberService.selectById("newId");
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("newId not exist !!");
            }
        } else {
            System.out.println("newId 정보 수정에 실패했습니다.");
        }

        System.out.println("회원 정보 삭제");
        if (MemberService.deleteById("newId") > 0) {
            Member a = MemberService.selectById("newId");
            if (a != null) {
                System.out.println("newId exist !!");
            } else {
                System.out.println("newId 정보 삭제에 성공했습니다.");
            }
        } else {
            System.out.println("newId 정보 삭제에 실패했습니다.");
        }

    }
}
