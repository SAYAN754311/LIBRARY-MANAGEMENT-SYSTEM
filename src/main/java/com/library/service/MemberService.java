package com.library.service;
import com.library.dao.MemberDAO;
import com.library.model.Member;
import java.util.*;
public class MemberService {
    private final MemberDAO dao=new MemberDAO();
    public List<Member> list(){return dao.findAll();} public List<Member> search(String term){return dao.search(term);}
    public Member add(String name,String email,String phone){return dao.insert(name,email,phone);}
    public void update(int id,String name,String email,String phone){require(id);dao.update(new Member(id,name,email,phone));}
    public void delete(int id){require(id);dao.delete(id);}
    public Member require(int id){return dao.findById(id).orElseThrow(()->new IllegalArgumentException("Member not found."));}
}