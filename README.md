🌐 API Эндпоинты — Habit Tracker


🔐 Аутентификация и авторизация
GET http://localhost:8080/login — страница входа

POST http://localhost:8080/login — авторизация пользователя

GET http://localhost:8080/register — страница регистрации

POST http://localhost:8080/register — регистрация нового пользователя

GET http://localhost:8080/account — личный кабинет (текущий пользователь)


👤 Привычки пользователя (UserController)
GET http://localhost:8080/user/habits — получить привычки текущего пользователя

POST http://localhost:8080/user/habits — добавить привычку (привязывается к текущему пользователю)


📦 Привычки (HabitController)
GET http://localhost:8080/api/habits — получить все привычки

POST http://localhost:8080/api/habits — добавить новую привычку

PUT http://localhost:8080/api/habits/{id}/complete — отметить привычку как выполненную

DELETE http://localhost:8080/api/habits/{id} — удалить привычку по ID


🔁 Подпривычки (SubHabitController)
GET http://localhost:8080/api/sub-habits — получить все подпривычки

GET http://localhost:8080/api/sub-habits/habit/{habitId} — получить подпривычки по ID привычки

POST http://localhost:8080/api/sub-habits/{habitId} — добавить подпривычку к привычке

PUT http://localhost:8080/api/sub-habits/{subHabitId}/complete — отметить подпривычку выполненной

DELETE http://localhost:8080/api/sub-habits/{subHabitId} — удалить подпривычку


🛠️ Админка (AdminController)
GET http://localhost:8080/admin/users — получить список всех пользователей

GET http://localhost:8080/admin/habits — получить список всех привычек


🏠 Статические страницы (MainController)
GET http://localhost:8080/home — домашняя страница

GET http://localhost:8080/login — страница логина

GET http://localhost:8080/register — страница регистрации
