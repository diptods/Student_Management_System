# Project Report

## 1. Project Title
Java Student Management System

## 2. Overview
This project is a Java-based student management application designed to manage academic records in a simple, efficient, and organized way. It allows users to add and manage departments, students, instructors, courses, and enrollments, while also tracking academic performance through marks and GPA calculation.

The system is built using object-oriented programming concepts and uses a console-based user interface for interaction.

## 3. Objective
The main objective of this project is to create a practical academic management system that can:

- Manage student details
- Manage department information
- Track instructor data
- Add and view courses
- Enroll students in courses
- Record marks and calculate GPA
- Save data to local files
- Export transcripts asynchronously

## 4. Scope
The scope of the project includes:

- Student registration and updating
- Department and course management
- Instructor management
- Enrollment management
- Mark entry and grade evaluation
- Persistence using text files
- Background auto-save functionality

## 5. System Features
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

## 6. Architecture and Design
The project follows an object-oriented design with separate classes for each major entity. The design is modular and easy to understand.

### Main Classes
- Main: handles the console menu and program flow
- StudentManagementSystem: central logic and data controller
- Person: base class for student and instructor details
- Student: stores student-specific information
- Instructor: stores faculty-related information
- Department: represents academic departments
- Course: represents available courses
- Enrollment: links a student to a course and stores marks
- AcademicRecord: base class for academic records
- DataManager: handles file reading and writing
- AutoSaveTask: handles periodic background data saving

## 7. Data Storage
The project stores records in text files located in the data directory. Data is saved using pipe-delimited records, making it easy to store and read data without a database.

This includes files such as:

- departments.txt
- students.txt
- instructors.txt
- courses.txt
- enrollments.txt

## 8. GPA and Grade Evaluation
The system computes GPA from student enrollment records. Each enrollment includes a mark, which is converted into a grade and grade point. The system uses these grade points to calculate the overall GPA for a student.

## 9. Threading and Background Tasks
The project includes background thread support for:

- Auto-saving data at regular intervals
- Exporting transcripts asynchronously without interrupting the main application flow

This demonstrates Java multithreading in a practical academic system.

## 10. Strengths
- Clear object-oriented structure
- Simple and easy-to-use menu interface
- File-based persistence without external setup
- Modular class design
- GPA and enrollment tracking included
- Auto-save and async transcript export improve usability

## 11. Limitations
- Console-based application only
- No database integration
- No graphical user interface
- No user authentication or role-based access control
- Limited advanced reporting features

## 12. Conclusion
The Java Student Management System is a functional and educational project that demonstrates core Java programming concepts, including inheritance, abstraction, encapsulation, file handling, and multithreading. It is a solid foundation for managing academic records and can be extended into a more advanced student information system in the future.

## 13. Tools Used
- Java
- Object-Oriented Programming
- File I/O
- Java Threads
- Command-line interface
