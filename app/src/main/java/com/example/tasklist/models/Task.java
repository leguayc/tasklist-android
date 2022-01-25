package com.example.tasklist.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Task implements Serializable {

    private String title;
    private Date date;
    private boolean isDone;

    public Task(String t, Date d) {
        this.title = t;
        this.date = d;
        this.isDone = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone && Objects.equals(title, task.title) && Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, isDone);
    }

    public Date getDate() {
        return date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", isDone=" + isDone +
                '}';
    }

    public String getTitle() {
        return title;
    }
}
