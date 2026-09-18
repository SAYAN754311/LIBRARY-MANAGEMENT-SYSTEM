package com.library.database;

import com.library.model.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public final class DatabaseConnection {
    private static final Path DATA_FILE = Paths.get("data", "library.db");
    private static Store store;
    private DatabaseConnection() {}
    public static synchronized Store getStore() { if (store == null) store = load(); return store; }
    public static synchronized void save() {
        try { Files.createDirectories(DATA_FILE.getParent()); try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(DATA_FILE))) { out.writeObject(store); } }
        catch (IOException e) { throw new IllegalStateException("Could not save local data: " + e.getMessage(), e); }
    }
    public static synchronized void reset() { store = seed(); save(); }
    private static Store load() {
        if (!Files.exists(DATA_FILE)) { Store seeded = seed(); store = seeded; save(); return seeded; }
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(DATA_FILE))) { Object value = in.readObject(); if (value instanceof Store s) return s; throw new IOException("Unexpected data format"); }
        catch (IOException | ClassNotFoundException e) { throw new IllegalStateException("Could not load local data. Delete data/library.db to reset it. " + e.getMessage(), e); }
    }
    private static Store seed() {
        Store s = new Store();
        s.users.put("admin", new User("admin", "admin123"));
        s.books.put(1, new Book(1, "9780134685991", "Effective Java", "Joshua Bloch", "Programming", 3, 3));
        s.books.put(2, new Book(2, "9780132350884", "Clean Code", "Robert C. Martin", "Programming", 2, 2));
        s.books.put(3, new Book(3, "9781491950357", "Designing Data-Intensive Applications", "Martin Kleppmann", "Technology", 2, 2));
        s.members.put(1, new Member(1, "Demo Student", "student@example.com", "9000000001"));
        s.members.put(2, new Member(2, "Demo Faculty", "faculty@example.com", "9000000002"));
        return s;
    }
    public static final class Store implements Serializable {
        public final Map<Integer, Book> books = new LinkedHashMap<>();
        public final Map<Integer, Member> members = new LinkedHashMap<>();
        public final Map<Integer, IssueRecord> issues = new LinkedHashMap<>();
        public final Map<String, User> users = new LinkedHashMap<>();
        public int nextBookId = 4, nextMemberId = 3, nextIssueId = 1;
    }
}