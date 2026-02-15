
# Spring Boot RESTful API — Practical Questions

This repository contains my solutions for “Spring Boot RESTful API — Practical Questions” (Modules 1–3). Each question is implemented as its own Spring Boot project focused on REST controllers only, using Spring Web. I verified all endpoints via Postman, and I included testing screenshots under each module folder.

## Contents

- question1-library-api/ — Library Book Management API
- question2-student-api/ — Student Registration API
- question3-restaurant-api/ — Restaurant Menu API
- question4-ecommerce-api/ — E‑Commerce Product API
- question5-task-api/ — Task Management API
- bonus-userprofile-api/ — User Profile API with response wrapper

## Prerequisites

- JDK 21
- Maven Wrapper (included: `mvnw`, `mvnw.cmd`)
- Postman or curl for testing

## How to Run

Run each project independently from its folder:

Windows (PowerShell or CMD):

```
cd question1-library-api\question1-library-api
.\mvnw.cmd spring-boot:run
```

macOS/Linux:

```
cd question1-library-api/question1-library-api
./mvnw spring-boot:run
```

Repeat for each module:

- `question2-student-api/question2-student-api`
- `question3-restaurant-api/question3-restaurant-api`
- `question4-ecommerce-api/question4-ecommerce-api`
- `question5-task-api/question5-task-api`
- `bonus-userprofile-api/bonus-userprofile-api`

Default port: 8080 (if you run multiple at once, change `server.port` as needed).

---

## Question 1: Library Book Management API

- Base path: `/api/books`
- Model: Book(id: Long, title: String, author: String, isbn: String, publicationYear: int)
- Controller key endpoints:
  - GET `/api/books` — list all
  - GET `/api/books/{id}` — get by id
  - GET `/api/books/search?title={title}` — search by title
  - POST `/api/books` — add book
  - DELETE `/api/books/{id}` — delete
- Status codes used: 200, 201, 204, 404

Example with curl:

```
curl http://localhost:8080/api/books
curl http://localhost:8080/api/books/1
curl "http://localhost:8080/api/books/search?title=Clean"
```

Screenshots:

- question1-library-api/Q1TestingScreenshoot/AddedNewBook.png
- question1-library-api/Q1TestingScreenshoot/DeletedBook.png
- question1-library-api/Q1TestingScreenshoot/GetAllBooks.png
- question1-library-api/Q1TestingScreenshoot/getbyid.png
- question1-library-api/Q1TestingScreenshoot/searchBookByTitle.png

---

## Question 2: Student Registration API

- Base path: `/api/students`
- Model: Student(studentId: Long, firstName: String, lastName: String, email: String, major: String, gpa: Double)
- Controller key endpoints:
  - GET `/api/students` — list all
  - GET `/api/students/{studentId}` — get by id
  - GET `/api/students/major/{major}` — filter by major (path)
  - GET `/api/students/filter?gpa={minGpa}` — filter by GPA ≥ min
  - POST `/api/students` — register student
  - PUT `/api/students/{studentId}` — update student
- Test scenarios: 5+ sample students, filter by “Computer Science”, GPA ≥ 3.5

Example:

```
curl http://localhost:8080/api/students
curl http://localhost:8080/api/students/1
curl http://localhost:8080/api/students/major/Computer%20Science
curl "http://localhost:8080/api/students/filter?gpa=3.5"
```

Screenshots:

- question2-student-api/Q2TestingScreenshoots/PostStudent.png
- question2-student-api/Q2TestingScreenshoots/getAllStudents.png
- question2-student-api/Q2TestingScreenshoots/getByFilter.png
- question2-student-api/Q2TestingScreenshoots/getByMajor.png
- question2-student-api/Q2TestingScreenshoots/putStudent.png

---

## Question 3: Restaurant Menu API

- Base path: `/api/menu`
- Model: MenuItem(id: Long, name: String, description: String, price: Double, category: String, available: boolean)
- Controller key endpoints:
  - GET `/api/menu` — list all
  - GET `/api/menu/{id}` — get by id
  - GET `/api/menu/category/{category}` — by category
  - GET `/api/menu/available?available=true|false` — by availability
  - GET `/api/menu/search?name={name}` — search by name
  - POST `/api/menu` — add item
  - PUT `/api/menu/{id}/availability` — toggle availability
  - DELETE `/api/menu/{id}` — delete item
- Challenge satisfied: 8+ seeded items across categories

Example:

```
curl "http://localhost:8080/api/menu/available?available=true"
curl "http://localhost:8080/api/menu/category/Dessert"
```

Screenshots:

- question3-restaurant-api/Q3TestingScreenshoots/addedItem.png
- question3-restaurant-api/Q3TestingScreenshoots/deleteItem.png
- question3-restaurant-api/Q3TestingScreenshoots/getAllItems.png
- question3-restaurant-api/Q3TestingScreenshoots/getAvailableItems.png
- question3-restaurant-api/Q3TestingScreenshoots/getByCategory.png
- question3-restaurant-api/Q3TestingScreenshoots/getItemById.png
- question3-restaurant-api/Q3TestingScreenshoots/searchByName.png
- question3-restaurant-api/Q3TestingScreenshoots/toggleAvailable.png

---

## Question 4: E‑Commerce Product API

- Base path: `/api/products`
- Model: Product(productId: Long, name: String, description: String, price: Double, category: String, stockQuantity: int, brand: String)
- Controller key endpoints:
  - GET `/api/products?page={page}&limit={limit}` — pagination
  - GET `/api/products/{productId}` — details
  - GET `/api/products/category/{category}` — by category
  - GET `/api/products/brand/{brand}` — by brand
  - GET `/api/products/search?keyword={keyword}` — search in name/description
  - GET `/api/products/price-range?min={min}&max={max}` — by price range
  - GET `/api/products/in-stock` — stockQuantity > 0
  - POST `/api/products` — add product
  - PUT `/api/products/{productId}` — update product
  - PATCH `/api/products/{productId}/stock?quantity={quantity}` — update stock
  - DELETE `/api/products/{productId}` — delete product
- Testing: 10+ products across categories, brands, prices

Example:

```
curl "http://localhost:8080/api/products?page=0&limit=5"
curl "http://localhost:8080/api/products/search?keyword=laptop"
curl "http://localhost:8080/api/products/price-range?min=100&max=500"
```

Screenshots:

- question4-ecommerce-api/Q4TestingScreenshoots/addedNewProduct.png
- question4-ecommerce-api/Q4TestingScreenshoots/deletedProduct.png
- question4-ecommerce-api/Q4TestingScreenshoots/getAllproducts.png
- question4-ecommerce-api/Q4TestingScreenshoots/getByBrand.png
- question4-ecommerce-api/Q4TestingScreenshoots/getByCategory.png
- question4-ecommerce-api/Q4TestingScreenshoots/getProductById.png
- question4-ecommerce-api/Q4TestingScreenshoots/getRange.png
- question4-ecommerce-api/Q4TestingScreenshoots/getStock.png
- question4-ecommerce-api/Q4TestingScreenshoots/searchByKeyword.png
- question4-ecommerce-api/Q4TestingScreenshoots/updatedProduct.png
- question4-ecommerce-api/Q4TestingScreenshoots/updatedStockQuantity.png

---

## Question 5: Task Management API

- Base path: `/api/tasks`
- Model: Task(taskId: Long, title: String, description: String, completed: boolean, priority: "LOW|MEDIUM|HIGH", dueDate: "YYYY-MM-DD")
- Controller key endpoints:
  - GET `/api/tasks` — list all
  - GET `/api/tasks/{taskId}` — get by id
  - GET `/api/tasks/status?completed={true|false}` — by completion status
  - GET `/api/tasks/priority/{priority}` — by priority
  - POST `/api/tasks` — create task
  - PUT `/api/tasks/{taskId}` — update task
  - PATCH `/api/tasks/{taskId}/complete` — mark completed
  - DELETE `/api/tasks/{taskId}` — delete

Example:

```
curl "http://localhost:8080/api/tasks/status?completed=false"
curl "http://localhost:8080/api/tasks/priority/HIGH"
```

Screenshots:

- question5-task-api/Q5TestingScreenshoots/getAllTasks.png
- question5-task-api/Q5TestingScreenshoots/getTaskById.png
- question5-task-api/Q5TestingScreenshoots/getTaskByPriority.png
- question5-task-api/Q5TestingScreenshoots/getTasksByCompletionStatus.png
- question5-task-api/Q5TestingScreenshoots/taskAdded.png
- question5-task-api/Q5TestingScreenshoots/updatedTask.png
- question5-task-api/Q5TestingScreenshoots/MarkedTaskCompleted.png
- question5-task-api/Q5TestingScreenshoots/deleteTask.png

---

## Bonus: User Profile API (with Response Wrapper)

- Base path: `/api/userprofiles`
- Model: UserProfile(userId: Long, username: String, email: String, fullName: String, age: int, country: String, bio: String, active: boolean)
- Response wrapper: `ApiResponse<T>` with fields `success`, `message`, `data`
- Controller key endpoints:
  - GET `/api/userprofiles` — list all
  - GET `/api/userprofiles/{userId}` — get by id
  - POST `/api/userprofiles` — create
  - PUT `/api/userprofiles/{userId}` — update
  - DELETE `/api/userprofiles/{userId}` — delete
  - PATCH `/api/userprofiles/{userId}/toggle` — activate/deactivate
  - GET `/api/userprofiles/search/username?username={username}` — search username
  - GET `/api/userprofiles/search/country?country={country}` — search country
  - GET `/api/userprofiles/search/age?min={min}&max={max}` — search age range

Example:

```
curl http://localhost:8080/api/userprofiles
curl "http://localhost:8080/api/userprofiles/search/username?username=elie"
```

Screenshots:

- bonus-userprofile-api/bonusQuestionTestingScreenshoots/addedNewUser.png
- bonus-userprofile-api/bonusQuestionTestingScreenshoots/getAllUsers.png
- bonus-userprofile-api/bonusQuestionTestingScreenshoots/getUserById.png
- bonus-userprofile-api/bonusQuestionTestingScreenshoots/searchAgeByRange.png
- bonus-userprofile-api/bonusQuestionTestingScreenshoots/searchByCountry.png
- bonus-userprofile-api/bonusQuestionTestingScreenshoots/searchUser.png
- bonus-userprofile-api/bonusQuestionTestingScreenshoots/userDeactivated.png
- bonus-userprofile-api/bonusQuestionTestingScreenshoots/userProfileDeleted.png
- bonus-userprofile-api/bonusQuestionTestingScreenshoots/userUpdated.png

---

## Notes

- All modules are intentionally controller-only to align with the assignment (no service/repository layers).
- HTTP status codes are used appropriately across create/update/delete and not-found scenarios.
- Seeded in-memory lists provide sample data for quick testing without a database.

---

## Folder Map

```
question1-library-api/
  └─ Q1TestingScreenshoot/...
question2-student-api/
  └─ Q2TestingScreenshoots/...
question3-restaurant-api/
  └─ Q3TestingScreenshoots/...
question4-ecommerce-api/
  └─ Q4TestingScreenshoots/...
question5-task-api/
  └─ Q5TestingScreenshoots/...
bonus-userprofile-api/
  └─ bonusQuestionTestingScreenshoots/...
```

