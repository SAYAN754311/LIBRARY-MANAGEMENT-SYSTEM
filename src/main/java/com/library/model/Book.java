package com.library.model;
import java.io.Serializable;
public record Book(int id, String isbn, String title, String author, String category, int totalCopies, int availableCopies) implements Serializable {
    public Book { if (id < 1 || totalCopies < 0 || availableCopies < 0 || availableCopies > totalCopies) throw new IllegalArgumentException("Invalid book values"); }
    public Book withDetails(String newIsbn, String newTitle, String newAuthor, String newCategory, int newTotalCopies) { int issued = totalCopies - availableCopies; if (newTotalCopies < issued) throw new IllegalArgumentException("Total copies cannot be less than copies currently issued"); return new Book(id,newIsbn,newTitle,newAuthor,newCategory,newTotalCopies,newTotalCopies-issued); }
    public Book withAvailableCopies(int value) { return new Book(id,isbn,title,author,category,totalCopies,value); }
}