package com.example.Library.Controller;

import com.example.Library.DTO.BookDTO;
import com.example.Library.Entity.Book;
import com.example.Library.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy) {
        return ResponseEntity.ok(bookService.getBooks(page, size, sortBy));
    }

    @PostMapping("/add")
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDto) {
        Book savedBook = bookService.saveBook(bookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/library/{libraryId}")
    public List<Book> getBooksByLibrary(@PathVariable Long libraryId) {
        return bookService.getBooksByLibrary(libraryId);
    }

    @GetMapping("/year/{year}")
    public List<Book> getBooksByYear(@PathVariable int year) {
        return bookService.getBooksByYear(year);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Book>> searchBooks(@RequestParam String title) {
//        List<Book> results = bookService.findBooksByCriteria(title);
//        return ResponseEntity.ok(results);
//    }
}


