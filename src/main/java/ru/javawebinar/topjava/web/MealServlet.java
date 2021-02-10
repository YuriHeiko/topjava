package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final MealService mealService = new MealServiceImpl();
    private final String DELETE = "delete";
    private final String EDIT = "edit";
    private final String CREATE = "create";
    private final String INSERT_OR_EDIT = "meal.jsp";
    private final String LIST = "meals.jsp";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        String forward = LIST;

        if (DELETE.equalsIgnoreCase(action)) {
            int id = getId(request);
            mealService.deleteOne(User.DEFAULT_USER, id);
            response.sendRedirect("meals");
            return;
        }

        if (EDIT.equalsIgnoreCase(action)) {
            forward = INSERT_OR_EDIT;
            int id = getId(request);
            MealTo meal = mealService.findOne(User.DEFAULT_USER, id);
            request.setAttribute("meal", meal);
        } else if (CREATE.equalsIgnoreCase(action)) {
            forward = INSERT_OR_EDIT;
        }

        if (LIST.equals(forward)) {
            request.setAttribute("meals", mealService.findMany(User.DEFAULT_USER));
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("redirect to meals post");
        req.setCharacterEncoding("UTF-8");

        String rawId = req.getParameter("id");
        Integer id = (rawId == null || rawId.isBlank()) ? null : Integer.parseInt(rawId);
        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(req.getParameter("dateTime"), FORMATTER);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        mealService.save(User.DEFAULT_USER, new MealTo(id, dateTime, description, calories));

        resp.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String id = request.getParameter("id");
        return id == null ? -1 : Integer.parseInt(id);
    }
}
