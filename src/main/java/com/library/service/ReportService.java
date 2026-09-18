package com.library.service;
import com.library.model.IssueRecord;
import java.util.*;
public class ReportService {
    private final BookService books=new BookService(); private final MemberService members=new MemberService(); private final IssueService issues=new IssueService();
    public String summary(){int titles=books.list().size();int total=books.list().stream().mapToInt(b->b.totalCopies()).sum();int available=books.list().stream().mapToInt(b->b.availableCopies()).sum();int memberCount=members.list().size();double fines=issues.all().stream().mapToDouble(IssueRecord::fine).sum();return String.format(Locale.US,"--- Library Summary ---%nBook titles : %d%nTotal copies: %d%nAvailable   : %d%nMembers     : %d%nFines       : %.2f%n",titles,total,available,memberCount,fines);}
}