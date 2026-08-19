#  Title : Java Student Management System
A simple Java desktop application for managing students, departments, instructors, courses, enrollments, and academic records.

## Overview
This project is a Java-based student management application designed to manage academic records in a simple, efficient, and organized way. It allows users to add and manage departments, students, instructors, courses, and enrollments, while also tracking academic performance through marks and GPA calculation.

The system is built using object-oriented programming concepts and uses a console-based user interface for interaction.

## System Features
The system supports the following core functions:

- Add Department
- View Departments
- Add Student
- View Students
- Search Student
- Update Student
- Delete Student
- Enroll Student in Course
- Add Instructor
- View Instructors
- Add Course
- View Courses
- Record Marks
- View Transcript
- View Enrollments
- Save Data
- Export Transcripts

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

![Project UML] (data/project_uml.png)


## How to Run
1. Open the project in IntelliJ IDEA, Eclipse, VS Code or any Java IDE.
2. Compile the Java files.
3. Run the `Main` class.

Example:

```bash
javac src/*.java
java -cp src Main
```

## Notes
This project is a console-based application and stores data in text files instead of a database.

##  Limitations
- Console-based application only
- No database integration
- No graphical user interface
- No user authentication or role-based access control
- Limited advanced reporting features

## License
This project is for educational purposes.

## Conclusion
The Java Student Management System is a functional and educational project that demonstrates core Java programming concepts, including inheritance, abstraction, encapsulation, file handling, and multithreading. It is a solid foundation for managing academic records and can be extended into a more advanced student information system in the future.