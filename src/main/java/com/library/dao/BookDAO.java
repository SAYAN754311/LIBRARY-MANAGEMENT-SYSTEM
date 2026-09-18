package com.library.dao;

import com.library.database.DatabaseConnection;
import com.library.model.Book;
import java.util.*;

public class BookDAO {
    private final DatabaseConnection.Store store = DatabaseConnection.getStore();
    public List<Book> findAll() { return new ArrayList<>(store.books.values()); }
    public Optional<Book> findById(int id) { return Optional.ofNullable(store.books.get(id)); }
    public List<Book> search(String term) { String q = term.toLowerCase(); return findAll().stream().filter(b -> (b.title()+" "+b.author()+" "+b.category()+" "+b.isbn()).toLowerCase().contains(q)).toList(); }
    public Book insert(String isbn, String title, String author, String category, int copies) { int id = store.nextBookId++; Book b = new Book(id,isbn,title,author,category,copies,copies); store.books.put(id,b); DatabaseConnection.save(); return b; }
    public void update(Book b) { store.books.put(b.id(), b); DatabaseConnection.save(); }
    public void delete(int id) { store.books.remove(id); DatabaseConnection.save(); }
}