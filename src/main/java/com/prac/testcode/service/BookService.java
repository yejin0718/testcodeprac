package com.prac.testcode.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.prac.testcode.domain.Book;
import com.prac.testcode.domain.BookRepository;
import com.prac.testcode.dto.BookResponseDto;
import com.prac.testcode.dto.BookSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
	private final BookRepository bookRepository;

	//1. 책등록
	@Transactional
	public BookResponseDto 책등록하기(BookSaveReqDto dto){
		Book bookPs = bookRepository.save(dto.toEntity());
		return new BookResponseDto().toDto(bookPs);
	}

	//2. 책 목록보기
	public List<BookResponseDto> 책목록보기(){
		return bookRepository.findAll().stream()
			.map(new BookResponseDto()::toDto)
			.collect(Collectors.toList());
	}

	//3. 책 한건보기

	//4. 책 삭제

	//5. 책 수정
}
