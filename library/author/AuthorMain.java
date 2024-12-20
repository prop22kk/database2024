package io.mobile.library.author;

import java.util.List;

public class AuthorMain {

    public static void main(String[] args) {
        System.out.println("전체검색");
        List<Author> authorList = AuthorService.selectAll();
        for (Author author : authorList) {
            System.out.println(author);
        }

        System.out.println("저자 정보 추가");
        if (AuthorService.insert("a002", "author00") > 0){
            Author a = AuthorService.select("a002", "author00");
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("a004 not exist !!");
            }
        }else {
            System.out.println("책 추가에 실패했습니다.");
        }

        System.out.println("저자 정보 수정");
        if (AuthorService.update("a002", "author00", "a002","author0") > 0) {
            Author a = AuthorService.select("a002", "author0");
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("not exist !!");
            }
        } else {
            System.out.println("수정에 실패했습니다.");
        }

        System.out.println("저자 정보 삭제");
        if (AuthorService.delete("a002","author0") > 0) {
            Author a = AuthorService.select("a002", "author0");
            if (a != null) {
                System.out.println(" exist !!");
            } else {
                System.out.println("정보 삭제에 성공했습니다.");
            }
        } else {
            System.out.println("정보 삭제에 실패했습니다.");
        }

    }
}
