# databases-2024
## 도서-대출시스템

```
CREATE TABLE member (
	member_id VARCHAR(20) NOT NULL,
	name VARCHAR(10) NOT NULL,
	address VARCHAR(50),
	phone_number VARCHAR(20),
	join_date DATE NOT NULL,
	primary key (member_id)
);


CREATE TABLE book (
	book_id VARCHAR(20) NOT NULL,
	title VARCHAR(100) NOT NULL,
	publish_year VARCHAR(10),
	primary key (book_id)
);



CREATE TABLE loan (
	loan_id VARCHAR(20) NOT NULL,
	member_id VARCHAR(20) NOT NULL,
	book_id VARCHAR(20) NOT NULL,
	loan_date DATE NOT NULL,
	return_date DATE,
	status BOOLEAN NOT NULL,
	primary key (loan_id),
	FOREIGN KEY (member_id) REFERENCES member(member_id),
	FOREIGN KEY (book_id) REFERENCES book(book_id)
);

	
CREATE TABLE author(
	book_id VARCHAR(20),
	author VARCHAR(50),
	PRIMARY KEY (book_id, author),
	FOREIGN KEY (book_id) REFERENCES book(book_id)
	
);


INSERT INTO member(member_id, name, address, phone_number, join_date)
VALUES ('sleepy', '홍길동', '대전', '000-0000-0000', '2022-09-09');

INSERT INTO member(member_id, name, address, phone_number, join_date)
VALUES ('candy', '소영', '대전', '111-1111-1111', '2022-12-12');

INSERT INTO member(member_id, name, address, phone_number, join_date)
VALUES ('home','지영', '함흥', null, '2023-06-11');

SELECT *
	FROM MEMBER
	
INSERT INTO author(book_id, author)
VALUES ('a001','author1');

INSERT INTO author(book_id, author)
VALUES ('a001','author2');

INSERT INTO author(book_id, author)
VALUES ('a002','author3');

INSERT INTO author(book_id, author)
VALUES ('a003','author4');


INSERT INTO book(book_id, title, publish_year)
VALUES ('a001','title1', 2009);

INSERT INTO book(book_id, title, publish_year)
VALUES ('a002','title2', 2019);

INSERT INTO book(book_id, title, publish_year)
VALUES ('a003','title3', null);

SELECT *
	FROM book;

INSERT INTO loan(loan_id, member_id, book_id, loan_date, return_date, status)
VALUES ('1003','sleepy', 'a001', '2024-03-03', '2024-03-24', false);

INSERT INTO loan(loan_id, member_id, book_id, loan_date, return_date, status)
VALUES ('1002','sleepy', 'a002', '2024-11-11', null, true);

SELECT *
	FROM loan;

```
