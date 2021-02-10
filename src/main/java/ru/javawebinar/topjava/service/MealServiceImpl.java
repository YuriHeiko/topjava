package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.InMemoryMealsRepositoryImpl;
import ru.javawebinar.topjava.repository.MealsRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.Collection;

public class MealServiceImpl implements MealService {
    private final MealsRepository mealsRepository = new InMemoryMealsRepositoryImpl();

    @Override
    public MealTo findOne(User user, Integer id) {
        return MealTo.createTo(mealsRepository.findOne(user, id));
    }

    @Override
    public Collection<MealTo> findMany(User user) {
        return MealsUtil
                .filteredByStreams(mealsRepository.findMany(user), LocalTime.MIN, LocalTime.MAX, User.CALORIES_PER_DAY);
    }

    @Override
    public MealTo save(User user, MealTo mealTo) {
        return MealTo.createTo(mealsRepository.save(user, Meal.fromTo(mealTo)));
    }

    @Override
    public boolean deleteOne(User user, Integer id) {
        return mealsRepository.deleteOne(user, id);
    }
}
