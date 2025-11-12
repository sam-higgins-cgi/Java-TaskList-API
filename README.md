# Description

A small task list API built using Java SpringBoot.

# Technologies
- Java
- SpringBoot
- SpringBootTest
- Junit
- (Removed) ~~Gson~~ 
- H2
- Maven
- GitHub Actions

# End Points
|URL |TYPE | DESCRIPTION|
|---|---|---|
| `/` | GET | Returns a `String` welcome message |
| `/tasks/all` | GET | Returns a JSON `Array` of all tasks in the database |
| `/tasks/todo` | GET | Returns a JSON `Array` of all tasks in the database where is_done is `false` |
| `/tasks/done` | GET | Returns a JSON `Array` of all tasks in the database where is_done is `true` |
| `/tasks/task` | GET | Takes a `Int` parameter and Returns a JSON `Object` of task with matching `id` |
| `/tasks/add` | POST | Takes a JSON `Object` with `task_title` `String` and makes a new entry in the database |
| `/tasks/complete` | PATCH | Takes a JSON `Object` with `id` `Int` of task to mark as complete and updates the entry in the database|


