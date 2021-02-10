package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;

public class InMemoryMealsRepositoryImpl implements MealsRepository {
    private final InMemoryDb db = new InMemoryDb();

    @Override
    public Meal findOne(User user, Integer id) {
        return db.findOne(user, id);
    }

    @Override
    public Collection<Meal> findMany(User user) {
        return db.findAllByUser(user);
    }

    @Override
    public Meal save(User user, Meal meal) {
        return db.save(user, meal);
    }

    @Override
    public boolean deleteOne(User user, Integer id) {
        return db.deleteOne(user, id) != null;
    }
}
