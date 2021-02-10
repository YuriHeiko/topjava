package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDb {
    private final Map<User, ConcurrentHashMap<Integer, Meal>> repo = new ConcurrentHashMap<>();
    private final AtomicInteger idHolder = new AtomicInteger(0);

    {
        save(User.DEFAULT_USER, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        save(User.DEFAULT_USER, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(User.DEFAULT_USER, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(User.DEFAULT_USER, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(User.DEFAULT_USER, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(User.DEFAULT_USER, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(User.DEFAULT_USER, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }


    public Collection<Meal> findAllByUser(User user) {
        return repo.get(user).values();
    }

    public Meal findOne(User user, Integer id) {
        return repo.get(user).get(id);
    }

    public Meal save(User user, Meal meal) {
        ConcurrentHashMap<Integer, Meal> map = repo.computeIfAbsent(user, u -> new ConcurrentHashMap<>());

        if (meal.getId() == null) {
            meal.setId(idHolder.incrementAndGet());
        }
        map.put(meal.getId(), meal);

        return map.get(meal.getId());
    }

    public Meal deleteOne(User user, Integer id) {
        return repo.get(user).remove(id);
    }
}
