package com.week1.crudspringbootmongodb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.week1.crudspringbootmongodb.model.BookModel;
import com.week1.crudspringbootmongodb.repository.BookRepository;

public class BookServiceTest {
	
	@InjectMocks
	BookServiceImpl bookServiceImpl;
	
	@Mock
	BookRepository bookRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	BookModel BOOK_1 = new BookModel("book1", "Memoirs of a Geisha", "Arthur Golden");
	BookModel BOOK_2 = new BookModel("book2", "Lazy Bones", "Sherlock Holmes");
	BookModel BOOK_3 = new BookModel("book3", "The Witch Of Portobello", "Paulo Coelho");
	@Test
	public void getAllBooksTest() throws Exception {
		List<BookModel> bookModels = new ArrayList<>(Arrays.asList(BOOK_1,BOOK_2,BOOK_3));
		Mockito.when(bookRepository.findAll()).thenReturn(bookModels);
		
		List<BookModel> testBooks = bookServiceImpl.getAllBookDetails();
		
		Assertions.assertNotNull(testBooks);
		Assertions.assertEquals(3, testBooks.size());
	}
	
	@Test
	public void getBookByIdTest() throws Exception {
		Mockito.when(bookRepository.findById("book1")).thenReturn(Optional.of(BOOK_1));
		
		BookModel testBook = bookServiceImpl.getBookById("book1");
		
		Assertions.assertEquals("Memoirs of a Geisha", testBook.getTitle());
		Assertions.assertEquals("Arthur Golden", testBook.getAuthor());
	}
	
	@Test
	public void saveBookTest() throws Exception {
		Mockito.when(bookRepository.save(BOOK_2)).thenReturn(BOOK_2);
		
		BookModel testBook = bookServiceImpl.saveBook(BOOK_2);
		Assertions.assertEquals("Lazy Bones", testBook.getTitle());
		Assertions.assertEquals("Sherlock Holmes", testBook.getAuthor());
	}
}
