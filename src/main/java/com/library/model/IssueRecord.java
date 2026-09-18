package com.library.model;
import java.io.Serializable;
import java.time.LocalDate;
public record IssueRecord(int id,int bookId,int memberId,LocalDate issueDate,LocalDate dueDate,LocalDate returnDate,double fine) implements Serializable {
    public boolean active(){return returnDate==null;}
    public IssueRecord returned(LocalDate date,double calculatedFine){return new IssueRecord(id,bookId,memberId,issueDate,dueDate,date,calculatedFine);}
}