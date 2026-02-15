package com.elie.question1_library_api.controller.library;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elie.question1_library_api.model.library.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private List<Book> books = new ArrayList<>();

    // Constructor to initialize sample data
    public BookController() {
        books.add(new Book(1L, "Clean Code", "Nshimyumuremyi Elie", "978-0132350884", 2008));
        books.add(new Book(2L, "Effective Java", "Kundwa Gaddy", "978-0134685991", 2018));
        books.add(new Book(3L, "Spring in Action", "Muhoracyeye Karry", "978-1617294945", 2018));
    }

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books); // 200
    }

    // GET book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        for (Book book : books) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book); // 200
            }
        }

        return ResponseEntity.notFound().build(); // 404
    }

    // SEARCH by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }

        return ResponseEntity.ok(result);
    }

    // ADD new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book); // 201
    }

    // DELETE book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        for (Book book : books) {
            if (book.getId().equals(id)) {
                books.remove(book);
                return ResponseEntity.noContent().build(); // 204
            }
        }

        return ResponseEntity.notFound().build(); // 404
    }

}
