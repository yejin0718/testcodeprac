package com.prac.testcode.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	// @BeforeAll //테스트 시작전 한번만 실행
	@BeforeEach //각 테스트 시작전에 한번씩 실행
	public void 데이터준비(){
		String title = "junit5";
		String author = "메타코딩";
		Book book = Book.builder()
			.title(title)
			.author(author)
			.build();
		bookRepository.save(book);
	}//트랜잭션 종료 됐다면 말이 안됨
	//1. 데이터준비() + 1책등록(T) , 데이터 준비 +2번 기능 (T) ...

	//1.책 등록
	@Test
	public void 책등록_test(){
		//given (데이터 준비)
		String title = "junit5";
		String author = "메타코딩";
		Book book = Book.builder()
			.title(title)
			.author(author)
			.build();

		//when (테스트 실행)
		Book bookPS = bookRepository.save(book); // ps -> 영구적으로 저장되었다

		//then (검증) 검증하려면 return값 필요
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());

	}//트랜잿션 종료(저장된 데이터를 초기화함)

	//2.책 목록보기
	@Test
	public void 책목록보기_test(){
		//given
		String title = "junit5";
		String author = "메타코딩";

		//when
		List<Book> books = bookRepository.findAll();

		//then
		assertEquals(title, books.get(0).getTitle());
		assertEquals(author, books.get(0).getAuthor());
	}//트랜잿션 종료(저장된 데이터를 초기화함)

	//3.책 한건 보기
	@Sql("classpath:db/tableInit.sql")
	@Test
	public void 책한건보기_test(){
		//given
		String title = "junit5";
		String author = "메타코딩";

		//when
		Optional<Book> bookPS = bookRepository.findById(1L);

		//then
		assertEquals(title, bookPS.get().getTitle());
		assertEquals(author, bookPS.get().getAuthor());
	}//트랜잿션 종료(저장된 데이터를 초기화함)

	//4.책 수정
	@Sql("classpath:db/tableInit.sql")
	@Test
	public void 책수정_test(){
		//given
		Long id = 1L;
		String title = "junit5";
		String author = "메타코딩";
		Book book = new Book(id, title, author);

		//when
		Book bookPs = bookRepository.save(book);

		// bookRepository.findAll().stream()
		// 	.forEach((b) -> {
		// 		System.out.println(b.getId());
		// 		System.out.println(b.getTitle());
		// 		System.out.println(b.getAuthor());
		// 		System.out.println("============");
		// 	});

		//then
		assertEquals(id, bookPs.getId());
		assertEquals(title, bookPs.getTitle());
		assertEquals(author, bookPs.getAuthor());

	}

	//5.책 삭제
	@Sql("classpath:db/tableInit.sql")
	@Test
	public void 책삭제_test(){
		//given
		Long id = 1L;

		//when
		bookRepository.deleteById(id);

		//then
		Optional<Book> bookPS = bookRepository.findById(id);
		// assertArrayEquals(bookPS.isEmpty());
	}

}
