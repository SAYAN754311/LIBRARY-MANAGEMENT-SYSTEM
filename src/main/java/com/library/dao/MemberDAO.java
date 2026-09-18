package com.library.dao;

import com.library.database.DatabaseConnection;
import com.library.model.Member;
import java.util.*;

public class MemberDAO {
    private final DatabaseConnection.Store store = DatabaseConnection.getStore();
    public List<Member> findAll() { return new ArrayList<>(store.members.values()); }
    public Optional<Member> findById(int id) { return Optional.ofNullable(store.members.get(id)); }
    public List<Member> search(String term) { String q=term.toLowerCase(); return findAll().stream().filter(m -> (m.name()+" "+m.email()+" "+m.phone()).toLowerCase().contains(q)).toList(); }
    public Member insert(String name,String email,String phone) { int id=store.nextMemberId++; Member m=new Member(id,name,email,phone); store.members.put(id,m); DatabaseConnection.save(); return m; }
    public void update(Member m) { store.members.put(m.id(),m); DatabaseConnection.save(); }
    public void delete(int id) { store.members.remove(id); DatabaseConnection.save(); }
}