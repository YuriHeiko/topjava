package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;

public interface MealService {
    MealTo findOne(User user, Integer id);

    Collection<MealTo> findMany(User user);

    MealTo save(User user, MealTo meal);

    boolean deleteOne(User user, Integer id);
}
