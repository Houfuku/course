# Учет студентов группы

Веб-приложение для старосты или куратора: список студентов группы с ФИО, номером группы,
телефоном и формой обучения (Очная, Заочная, Очно-заочная), фильтр по форме обучения.
Каждый пользователь видит только своих студентов.

Стек: Java 17, Spring Boot 3.5, Spring MVC, Spring Security, Spring Data JPA (Hibernate),
Thymeleaf, Bean Validation, H2 (по умолчанию) или PostgreSQL.

## Запуск

Нужны JDK 17+ и Maven (или IntelliJ IDEA - она подтянет зависимости сама).

```bash
mvn spring-boot:run
```

Откройте http://localhost:8080 - откроется страница входа. Сначала зарегистрируйтесь.

База H2 создается автоматически в файле `data/students.mv.db` рядом с проектом,
данные сохраняются между запусками.

В IntelliJ IDEA: File → Open → выбрать папку проекта (pom.xml), дождаться загрузки
зависимостей и запустить класс `StudentAccountingApplication`.

### Запуск с PostgreSQL

1. Создайте базу: `CREATE DATABASE students;`
2. Проверьте логин и пароль в `src/main/resources/application-postgres.properties`.
3. Запустите: `mvn spring-boot:run -Dspring-boot.run.profiles=postgres`

## Страницы

| Адрес | Страница |
|---|---|
| `/register` | Регистрация (логин, пароль) |
| `/login` | Вход (логин, пароль) |
| `/students` | Список студентов, фильтр `?form=FULL_TIME / EXTRAMURAL / PART_TIME`, кнопка «Добавить» |
| `/students/new` | Форма добавления |
| `/students/{id}/edit` | Форма редактирования |
| `POST /students/{id}/delete` | Удаление (кнопка в таблице) |

## Структура

```
src/main/java/com/example/students/
├── StudentAccountingApplication.java   точка входа
├── model/        User, Student, StudyForm (enum форм обучения)
├── repository/   UserRepository, StudentRepository (Spring Data JPA)
├── dto/          RegisterForm, StudentForm (поля форм + валидация)
├── service/      UserService (регистрация), StudentService (CRUD + фильтр)
├── security/     SecurityConfig, DbUserDetailsService, AuthUser
└── controller/   AuthController, StudentController
src/main/resources/
├── application.properties
├── application-postgres.properties
├── templates/    login, register, students/list, students/add, students/edit, error/404
└── static/css/style.css
```

## База данных

```
users    (id, login, password)                                        password - BCrypt-хеш
students (id, user_id, full_name, group_number, phone, study_form)    user_id → users.id
```

Таблицы создает Hibernate по классам-сущностям (`spring.jpa.hibernate.ddl-auto=update`).
