package io.mobile.library.member;

import java.util.Date;

public class Member {

    private String memberId;
    private String name;
    private String address;
    private String phoneNumber;
    private Date joinDate;

    public Member(final String memberId, final String name, final String address, final String phoneNumber, final Date joinDate) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.joinDate = joinDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    @Override
    public String toString() {
        return "Member [member_id=" + memberId +
                ", name=" + name +
                ", address=" + address +
                ", phone_number=" + phoneNumber +
                ", join_date=" + joinDate + "]";
    }
}
