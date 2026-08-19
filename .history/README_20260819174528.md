# Java Student Management System

A simple Java desktop application for managing students, departments, instructors, courses, enrollments, and academic records.

## Features
- Add and view departments
- Add, update, search, and delete students
- Add instructors and courses
- Enroll students in classes
- Record marks and calculate GPA
- Save data to local text files
- Export student transcripts asynchronously
- Auto-save data in the background

## Project Structure

```text
JavaProject-StudentManagement/
├── src/
│   ├── AcademicRecord.java
│   ├── AutoSaveTask.java
│   ├── Course.java
│   ├── DataManager.java
│   ├── Department.java
│   ├── Enrollment.java
│   ├── Instructor.java
│   ├── Main.java
│   ├── Person.java
│   ├── Student.java
│   ├── StudentManagementSystem.java
│   └── data/
│       ├── courses.txt
│       ├── departments.txt
│       ├── enrollments.txt
│       ├── instructors.txt
│       └── students.txt
└── README.md
```

## How to Run
1. Open the project in IntelliJ IDEA, Eclipse, or any Java IDE.
2. Compile the Java files.
3. Run the `Main` class.

Example:

```bash
javac src/*.java
java -cp src Main
```

## Notes
This project is a console-based application and stores data in text files instead of a database.

## License
This project is for educational purposes.
