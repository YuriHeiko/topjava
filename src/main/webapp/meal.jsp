<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <link rel="stylesheet" type="text/css" href="style.css"/>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${meal == null ? "Create" : "Edit"}</h2>
<form action="meals" method="POST" id="meal-form">
    <input id="id" name="id" value="${meal.id}" hidden>
    <label>
        Date:
        <input type="datetime-local" name="dateTime" value="${meal.dateTime}"/>
    </label>
    <label>
        Description:
        <input type="text" name="description" value="${meal.description}"/>
    </label>
    <label>
        Calories:
        <input type="number" name="calories" value="${meal.calories}"/>
    </label>
    <div>
        <button type="submit">Save</button>
        <button type="reset" onclick="window.history.back()">Cancel</button>
    </div>
</form>
</body>
</html>
