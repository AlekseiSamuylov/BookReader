package com.samuylov.projectstart.service;

import com.samuylov.projectstart.MockData;
import com.samuylov.projectstart.converter.BookConverter;
import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;
    @Spy
    private BookConverter bookConverter;

    @Test
    public void createBook() {
        final BookEntity bookDbo = new BookEntity();
        bookDbo.setName("name");
        bookDbo.setDescription("description");

        doReturn(bookDbo).when(bookRepository).save(any(BookEntity.class));

        bookService.createBook(new BookDto());

        verify(bookRepository, times(1)).save(any(BookEntity.class));
    }

    @Test
    public void getBooksList() {
        final List<BookEntity> findAllResult = new ArrayList<>();
        final BookEntity bookDbo = MockData.bookDbo();
        findAllResult.add(bookDbo);
        findAllResult.add(bookDbo);
        doReturn(findAllResult).when(bookRepository).findAll();

        final List<BookDto> booksList = bookService.getBooksList();

        verify(bookRepository, times(1)).findAll();
        assertEquals(findAllResult.size(), booksList.size());
        for (final BookDto bookDto : booksList) {
            assertEquals(bookDbo.getName(), bookDto.getName());
            assertEquals(bookDbo.getDescription(), bookDto.getDescription());
        }
    }
}
