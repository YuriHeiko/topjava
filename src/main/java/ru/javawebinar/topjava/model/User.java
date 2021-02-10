package ru.javawebinar.topjava.model;

public class User {
    public static final User DEFAULT_USER = new User(0);
    public static final int CALORIES_PER_DAY = 2000;
    private final int id;

    public User(int id) {
        this.id = id;
    }
}
