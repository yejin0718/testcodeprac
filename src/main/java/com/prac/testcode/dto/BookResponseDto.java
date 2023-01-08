package com.prac.testcode.dto;

import com.prac.testcode.domain.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookResponseDto {
	private Long id;
	private String title;
	private String author;

	public BookResponseDto toDto(Book bookPs){
		this.id = bookPs.getId();
		this.title = bookPs.getTitle();
		this.author = bookPs.getAuthor();
		return this;
	}
}
