package io.mobile.library.book;





import io.mobile.sales.customer.Customer;
import io.mobile.sales.customer.CustomerService;

import java.util.List;

public class BookMain {
    public static void main(String[] args) {
        System.out.println("전체검색");
        List<Book> bookList = BookService.selectAll();
        for (Book book : bookList) {
            System.out.println(book);
        }

        System.out.println("bookId으로 검색");
        Book title1 = BookService.selectById("a001");
        if (title1 != null) {
            System.out.println(title1);
        } else {
            System.out.println("a001 not exist !!");
        }

        System.out.println("책 추가");
        if (BookService.insert("a004", "title4", "2000") > 0){
            Book a = BookService.selectById("a004");
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("a004 not exist !!");
            }
        }else {
            System.out.println("책 추가에 실패했습니다.");
        }

        System.out.println("책 수정");
        if (BookService.update("a004", "title04", "2020") > 0) {
            Book a = BookService.selectById("a004");
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("a004 not exist !!");
            }
        } else {
            System.out.println("a004 책 수정에 실패했습니다.");
        }
        
        System.out.println("책 삭제");
        if (BookService.deleteById("a004") > 0) {
            Customer grape = CustomerService.selectById("grape");
            if (grape != null) {
                System.out.println("a004 exist !!");
            } else {
                System.out.println("a004 정보 삭제에 성공했습니다.");
            }
        } else {
            System.out.println("책 정보 삭제에 실패했습니다.");
        }
    }


}