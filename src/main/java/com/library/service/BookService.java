package com.library.service;
import com.library.dao.BookDAO;
import com.library.model.Book;
import java.util.*;
public class BookService {
    private final BookDAO dao=new BookDAO();
    public List<Book> list(){return dao.findAll();}
    public List<Book> search(String term){return dao.search(term);}
    public Book add(String isbn,String title,String author,String category,int copies){if(copies<1)throw new IllegalArgumentException("Copies must be at least 1.");return dao.insert(isbn,title,author,category,copies);}
    public void update(int id,String isbn,String title,String author,String category,int copies){Book b=require(id);dao.update(b.withDetails(isbn,title,author,category,copies));}
    public void delete(int id){Book b=require(id);if(b.availableCopies()!=b.totalCopies())throw new IllegalStateException("Cannot delete a book with active issues.");dao.delete(id);}
    public Book require(int id){return dao.findById(id).orElseThrow(()->new IllegalArgumentException("Book not found."));}
    public void changeAvailability(int id,int delta){Book b=require(id);int n=b.availableCopies()+delta;if(n<0||n>b.totalCopies())throw new IllegalStateException("Invalid availability change.");dao.update(b.withAvailableCopies(n));}
}