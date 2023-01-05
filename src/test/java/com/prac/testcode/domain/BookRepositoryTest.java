package com.prac.testcode.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

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

	}

	//2.책 목록보기

	//3.책 한건 보기

	//4.책 수정

	//5.책 삭제
}
