package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;

public interface MealsRepository {
    Meal findOne(User user, Integer id);

    Collection<Meal> findMany(User user);

    Meal save(User user, Meal meal);

    boolean deleteOne(User user, Integer id);
}
