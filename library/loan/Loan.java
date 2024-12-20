package io.mobile.library.loan;

import java.util.Date;

public class Loan {

    private String loanId;
    private String memberId;
    private String bookId;
    private Date loanDate;
    private Date returnDate;
    private boolean status;

    public Loan(String loanId, String memberId, String bookId, Date loanDate, boolean status, Date return_date) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.status = status;
        this.returnDate = return_date;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public boolean getStatus() {
        return status;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId='" + loanId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", loanDate=" + loanDate +
                ", return_date=" + returnDate +
                ", status=" + status +
                '}';
    }
}
