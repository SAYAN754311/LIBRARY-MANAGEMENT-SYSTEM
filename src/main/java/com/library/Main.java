package com.library;

import com.library.model.*;
import com.library.service.*;
import com.library.util.InputValidator;
import java.util.*;

public class Main {
    private final Scanner scanner=new Scanner(System.in); private final AuthService auth=new AuthService(); private final BookService books=new BookService(); private final MemberService members=new MemberService(); private final IssueService issues=new IssueService(); private final ReportService reports=new ReportService();
    public static void main(String[] args){new Main().run();}
    private void run(){
        banner(); if(!login()) return;
        while(true){
            System.out.println("\n1. Book Management\n2. Member Management\n3. Issue / Return\n4. Reports\n5. Exit");
            String choice=prompt("Choose: ");
            try { switch(choice){case "1"->bookMenu();case "2"->memberMenu();case "3"->issueMenu();case "4"->reportMenu();case "5"-> {System.out.println("Goodbye.");return;}default->System.out.println("Invalid choice.");} } catch(Exception e){System.out.println("Error: "+e.getMessage());}
        }
    }
    private void banner(){System.out.println("========================================\n       LIBRARY MANAGEMENT SYSTEM\n========================================");}
    private boolean login(){for(int attempts=0;attempts<3;attempts++){String u=prompt("Username: ");String p=prompt("Password: ");if(auth.authenticate(u,p)){System.out.println("Login successful.");return true;}System.out.println("Invalid credentials.");}System.out.println("Too many failed attempts. Exiting.");return false;}
    private void bookMenu(){while(true){System.out.println("\n--- Book Management ---\n1. Add  2. Update  3. Delete  4. Search  5. List All  6. Back");String c=prompt("Choose: ");try{switch(c){case "1"->addBook();case "2"->updateBook();case "3"->deleteBook();case "4"->searchBooks();case "5"->listBooks();case "6"-> {return;}default->System.out.println("Invalid choice.");}}catch(Exception e){System.out.println("Error: "+e.getMessage());}}}
    private void addBook(){String isbn=prompt("ISBN: "),title=InputValidator.required(prompt("Title: "),"Title"),author=InputValidator.required(prompt("Author: "),"Author"),category=InputValidator.required(prompt("Category: "),"Category");int copies=InputValidator.positiveInt(prompt("Copies: "),"Copies");System.out.println("Added book ID: "+books.add(isbn,title,author,category,copies).id());}
    private void updateBook(){int id=InputValidator.positiveInt(prompt("Book ID: "),"Book ID");Book b=books.require(id);String isbn=promptDefault("ISBN",b.isbn()),title=promptDefault("Title",b.title()),author=promptDefault("Author",b.author()),category=promptDefault("Category",b.category());int copies=InputValidator.nonNegativeInt(promptDefault("Total copies",String.valueOf(b.totalCopies())),"Total copies");books.update(id,isbn,title,author,category,copies);System.out.println("Book updated.");}
    private void deleteBook(){int id=InputValidator.positiveInt(prompt("Book ID: "),"Book ID");books.delete(id);System.out.println("Book deleted.");}
    private void searchBooks(){String q=prompt("Search: ");printBooks(books.search(q));}
    private void listBooks(){printBooks(books.list());}
    private void printBooks(List<Book> list){if(list.isEmpty()){System.out.println("No books found.");return;}list.stream().sorted(Comparator.comparingInt(Book::id)).forEach(b->System.out.printf(Locale.US,"%d | %s | %s | %s | %d/%d available%n",b.id(),b.title(),b.author(),b.category(),b.availableCopies(),b.totalCopies()));}
    private void memberMenu(){while(true){System.out.println("\n--- Member Management ---\n1. Add  2. Update  3. Delete  4. Search  5. List All  6. Back");String c=prompt("Choose: ");try{switch(c){case "1"->addMember();case "2"->updateMember();case "3"->deleteMember();case "4"->searchMembers();case "5"->listMembers();case "6"->{return;}default->System.out.println("Invalid choice.");}}catch(Exception e){System.out.println("Error: "+e.getMessage());}}}
    private void addMember(){String n=InputValidator.required(prompt("Name: "),"Name"),e=prompt("Email: "),p=prompt("Phone: ");System.out.println("Added member ID: "+members.add(n,e,p).id());}
    private void updateMember(){int id=InputValidator.positiveInt(prompt("Member ID: "),"Member ID");Member m=members.require(id);members.update(id,promptDefault("Name",m.name()),promptDefault("Email",m.email()),promptDefault("Phone",m.phone()));System.out.println("Member updated.");}
    private void deleteMember(){int id=InputValidator.positiveInt(prompt("Member ID: "),"Member ID");members.delete(id);System.out.println("Member deleted.");}
    private void searchMembers(){printMembers(members.search(prompt("Search: ")));}
    private void listMembers(){printMembers(members.list());}
    private void printMembers(List<Member> list){if(list.isEmpty()){System.out.println("No members found.");return;}list.stream().sorted(Comparator.comparingInt(Member::id)).forEach(m->System.out.printf("%d | %s | %s | %s%n",m.id(),m.name(),m.email(),m.phone()));}
    private void issueMenu(){while(true){System.out.println("\n--- Issue / Return ---\n1. Issue Book  2. Return Book  3. Active Loans  4. Back");String c=prompt("Choose: ");try{switch(c){case "1"->issueBook();case "2"->returnBook();case "3"->activeLoans();case "4"->{return;}default->System.out.println("Invalid choice.");}}catch(Exception e){System.out.println("Error: "+e.getMessage());}}}
    private void issueBook(){int bid=InputValidator.positiveInt(prompt("Book ID: "),"Book ID"),mid=InputValidator.positiveInt(prompt("Member ID: "),"Member ID");IssueRecord r=issues.issue(bid,mid);System.out.printf("Issued successfully. Issue ID: %d, due date: %s%n",r.id(),r.dueDate());}
    private void returnBook(){int id=InputValidator.positiveInt(prompt("Issue ID: "),"Issue ID");IssueRecord r=issues.returnBook(id);System.out.printf(Locale.US,"Returned successfully. Fine: %.2f%n",r.fine());}
    private void activeLoans(){System.out.println("\n--- Active Loans ---");List<IssueRecord> active=issues.active();for(IssueRecord r:active){Book b=books.require(r.bookId());Member m=members.require(r.memberId());System.out.printf("%d | %s | %s | issued %s | due %s%n",r.id(),b.title(),m.name(),r.issueDate(),r.dueDate());}if(active.isEmpty())System.out.println("No active loans.");}
    private void reportMenu(){System.out.println("\n"+reports.summary());}
    private String prompt(String label){System.out.print(label);return scanner.nextLine().trim();}
    private String promptDefault(String label,String value){String s=prompt(label+" ["+value+"]: ");return s.isBlank()?value:s;}
}