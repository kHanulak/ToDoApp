package com.example.todo;

import java.util.Objects;

public class TodoItem {
    private final int id;
    private String description;
    private boolean done;

    public TodoItem(int id, String description) {
        this.id = id;
        this.description = description;
        this.done = false;
    }

    public int getId() { return id; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() { return done; }

    public void markDone() { this.done = true; }

    public String toString() {
        return String.format("[%d] %s (done: %b)", id, description, done);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoItem)) return false;
        TodoItem that = (TodoItem) o;
        return id == that.id;
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}