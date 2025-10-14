package com.example.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoManager {
    private final List<TodoItem> items = new ArrayList<>();
    private int nextId = 1;

    public TodoItem add(String description) {
        TodoItem item = new TodoItem(nextId++, description);
        items.add(item);
        return item;
    }

    public boolean remove(int id) {
        Optional<TodoItem> found = items.stream().filter(i -> i.getId() == id).findFirst();
        return found.map(items::remove).orElse(false);
    }

    public boolean markDone(int id) {
        for (TodoItem item : items) {
            if (item.getId() == id) {
                item.markDone();
                return true;
            }
        }
        return false;
    }

    public List<TodoItem> listAll() {
        return new ArrayList<>(items);
    }
}