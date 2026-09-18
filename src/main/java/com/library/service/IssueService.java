package com.library.service;
import com.library.dao.IssueDAO;
import com.library.model.IssueRecord;
import com.library.util.FineCalculator;
import java.time.LocalDate;
import java.util.*;
public class IssueService {
    public static final int LOAN_DAYS=14; public static final double DAILY_FINE=5.0;
    private final IssueDAO dao=new IssueDAO(); private final BookService books=new BookService(); private final MemberService members=new MemberService();
    public IssueRecord issue(int bookId,int memberId){books.require(bookId);members.require(memberId);if(books.require(bookId).availableCopies()<1)throw new IllegalStateException("No available copies.");boolean already=dao.active().stream().anyMatch(i->i.bookId()==bookId&&i.memberId()==memberId);if(already)throw new IllegalStateException("Member already has this book on loan.");LocalDate issueDate=LocalDate.now();IssueRecord r=new IssueRecord(dao.nextId(),bookId,memberId,issueDate,issueDate.plusDays(LOAN_DAYS),null,0);books.changeAvailability(bookId,-1);return dao.insert(r);}
    public IssueRecord returnBook(int issueId){IssueRecord r=dao.findById(issueId).orElseThrow(()->new IllegalArgumentException("Issue not found."));if(!r.active())throw new IllegalStateException("This issue has already been returned.");LocalDate date=LocalDate.now();double fine=FineCalculator.calculate(r.dueDate(),date,DAILY_FINE);IssueRecord returned=r.returned(date,fine);dao.update(returned);books.changeAvailability(r.bookId(),1);return returned;}
    public List<IssueRecord> active(){return dao.active();} public List<IssueRecord> all(){return dao.findAll();}
}