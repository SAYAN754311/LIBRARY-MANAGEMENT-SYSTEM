package com.library.dao;

import com.library.database.DatabaseConnection;
import com.library.model.IssueRecord;
import java.util.*;

public class IssueDAO {
    private final DatabaseConnection.Store store = DatabaseConnection.getStore();
    public IssueRecord insert(IssueRecord issue) { store.issues.put(issue.id(), issue); DatabaseConnection.save(); return issue; }
    public Optional<IssueRecord> findById(int id) { return Optional.ofNullable(store.issues.get(id)); }
    public List<IssueRecord> findAll() { return new ArrayList<>(store.issues.values()); }
    public List<IssueRecord> active() { return findAll().stream().filter(IssueRecord::active).toList(); }
    public void update(IssueRecord issue) { store.issues.put(issue.id(),issue); DatabaseConnection.save(); }
    public int nextId() { return store.nextIssueId++; }
}