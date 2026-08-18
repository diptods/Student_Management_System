import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentManagementSystem system = new StudentManagementSystem();

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM - JAVA OOP");
        System.out.println("==================================================");

        // Start background Auto-Save Daemon Thread (runs every 30 seconds)
        AutoSaveTask autoSaveTask = new AutoSaveTask(system, 30);
        Thread autoSaveThread = new Thread(autoSaveTask, "AutoSave-Daemon-Thread");
        autoSaveThread.setDaemon(true);
        autoSaveThread.start();

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> addDepartment();
                    case "2" -> viewDepartments();
                    case "3" -> addStudent();
                    case "4" -> viewStudents();
                    case "5" -> searchStudent();
                    case "6" -> updateStudent();
                    case "7" -> deleteStudent();
                    case "8" -> enrollStudent();
                    case "9" -> addInstructor();
                    case "10" -> viewInstructors();
                    case "11" -> addCourse();
                    case "12" -> viewCourses();
                    case "13" -> recordMarks();
                    case "14" -> viewTranscript();
                    case "15" -> viewCourseEnrollments();
                    case "16" -> {
                        system.saveData();
                        System.out.println("All data saved successfully.");
                    }
                    case "17" -> system.exportTranscriptsAsync();
                    case "0" -> {
                        system.saveData();
                        System.out.println("Data saved. Program closed. Thanks");
                        return;
                    }
                    default ->
                        System.out.println("Invalid menu option.");
                }
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n---------------- MAIN MENU ----------------");
        System.out.println(" 1. Add Department        2. View Departments");
        System.out.println(" 3. Add Student           4. View Students");
        System.out.println(" 5. Search Student        6. Update Student");
        System.out.println(" 7. Delete Student        8. Enroll Student in Course ");
        System.out.println(" 9. Add Instructor       10. View Instructors");
        System.out.println("11. Add Course           12. View Courses");
        System.out.println("13. Record Marks         14. View Transcript");
        System.out.println("15. View Enrollments     16. Save Data");
        System.out.println("17. Export Transcripts (Async Thread)");
        System.out.println(" 0. Save & Exit");
        System.out.print("Choose an option: ");
    }

    private static void addDepartment() {
        System.out.print("Department ID: ");
        String id = sc.nextLine();
        System.out.print("Department name: ");
        String name = sc.nextLine();

        boolean success = system.addDepartment(new Department(id, name));
        System.out.println(success ? "Department added successfully." : "Department ID already exists.");

    }

    private static void viewDepartments() {
        System.out.println("\n--- DEPARTMENTS ---");
        if (system.getDepartments().isEmpty()) {
            System.out.println("No departments found.");
            return;
        }
        for (Department d : system.getDepartments()){
            System.out.println(d);
        }
        return;
    }

    private static void addStudent() {
        System.out.print("Student ID: ");
        String id = sc.nextLine();
        System.out.print("Student name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Department ID: ");
        String deptId = sc.nextLine();
        System.out.print("Semester: ");
        int semester = Integer.parseInt(sc.nextLine());

        Department department = system.findDepartment(deptId);
        if (department == null) {
            System.out.println("Department not found. Add the department first.");
            return;
        }

        Student student = new Student(id, name, phone, email, department, semester);
        System.out.println(system.addStudent(student)
                ? "Student added successfully."
                : "Student ID already exists.");
    }

    private static void viewStudents() {
        System.out.println("\n--- STUDENTS ---");
        if (system.getStudents().isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : system.getStudents()) {
            String dept = s.getDepartment() == null ? "N/A" : s.getDepartment().getName();
            System.out.println(s + " | Dept: " + dept + " | Semester: " + s.getSemester());
        }
    }

    private static void searchStudent() {
        System.out.print("Student ID: ");
        Student s = system.findStudent(sc.nextLine());

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student found:");
        System.out.println(s);
        System.out.println(s.getDescription());
        System.out.printf("Current GPA: %.2f%n", system.calculateGPA(s.getId()));
    }

    private static void updateStudent() {
        System.out.print("Student ID to update: ");
        String id = sc.nextLine();

        if (system.findStudent(id) == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("New name: ");
        String name = sc.nextLine();
        System.out.print("New phone: ");
        String phone = sc.nextLine();
        System.out.print("New email: ");
        String email = sc.nextLine();
        System.out.print("New department ID: ");
        String dept = sc.nextLine();
        System.out.print("New semester: ");
        int semester = Integer.parseInt(sc.nextLine());

        System.out.println(system.updateStudent(id, name, phone, email, semester, dept)
                ? "Student updated."
                : "Update failed. Check department ID.");
    }

    private static void deleteStudent() {
        System.out.print("Student ID: ");
        String id = sc.nextLine();

        System.out.println(system.deleteStudent(id)
                ? "Student deleted."
                : "Student cannot be deleted. It may not exist or may have enrollments.");
    }

    private static void addInstructor() {
        System.out.print("Instructor ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Department ID: ");
        String deptId = sc.nextLine();
        System.out.print("Designation: ");
        String designation = sc.nextLine();

        Department d = system.findDepartment(deptId);
        if (d == null) {
            System.out.println("Department not found.");
            return;
        }

        Instructor instructor = new Instructor(id, name, phone, email, d, designation);
        System.out.println(system.addInstructor(instructor)
                ? "Instructor added."
                : "Instructor ID already exists.");
    }

    private static void viewInstructors() {
        System.out.println("\n--- INSTRUCTORS ---");
        for (Instructor i : system.getInstructors()) {
            String dept = i.getDepartment() == null ? "N/A" : i.getDepartment().getName();
            System.out.println(i + " | " + i.getDesignation() + " | Dept: " + dept);
        }
    }

    private static void addCourse() {
        System.out.print("Course ID: ");
        String id = sc.nextLine();
        System.out.print("Course title: ");
        String title = sc.nextLine();
        System.out.print("Credit: ");
        double credit = Double.parseDouble(sc.nextLine());
        System.out.print("Instructor ID (leave blank for none): ");
        String instructorId = sc.nextLine();

        Instructor instructor = instructorId.isBlank() ? null : system.findInstructor(instructorId);
        if (!instructorId.isBlank() && instructor == null) {
            System.out.println("Instructor not found.");
            return;
        }

        Course course = new Course(id, title, credit, instructor);
        System.out.println(system.addCourse(course)
                ? "Course added."
                : "Course ID already exists.");
    }

    private static void viewCourses() {
        System.out.println("\n--- COURSES ---");
        for (Course c : system.getCourses()) System.out.println(c);
    }

    private static void enrollStudent() {
        System.out.print("Student ID: ");
        String studentId = sc.nextLine();
        System.out.print("Course ID: ");
        String courseId = sc.nextLine();

        System.out.println(system.enrollStudent(studentId, courseId)
                ? "Student enrolled successfully."
                : "Enrollment failed. Check IDs or duplicate enrollment.");
    }

    private static void recordMarks() {
        System.out.print("Student ID: ");
        String studentId = sc.nextLine();

        List<Enrollment> records = system.getStudentEnrollments(studentId);
        if (records.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        for (Enrollment e : records) System.out.println(e);

        System.out.print("Course ID: ");
        String courseId = sc.nextLine();
        System.out.print("Mark (0-100): ");
        double mark = Double.parseDouble(sc.nextLine());

        System.out.println(system.recordMark(studentId, courseId, mark)
                ? "Mark recorded. Grade: " +
                system.findEnrollment(studentId, courseId).getGrade()
                : "Could not record mark.");
    }

    private static void viewTranscript() {
        System.out.print("Student ID: ");
        String studentId = sc.nextLine();
        Student s = system.findStudent(studentId);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("                 STUDENT TRANSCRIPT");
        System.out.println("==================================================");
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Department: " +
                (s.getDepartment() == null ? "N/A" : s.getDepartment().getName()));
        System.out.println("Semester: " + s.getSemester());
        System.out.println("--------------------------------------------------");

        List<Enrollment> records = system.getStudentEnrollments(studentId);
        if (records.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (Enrollment e : records) {
                System.out.println(e);
            }
        }

        System.out.printf("GPA: %.2f%n", system.calculateGPA(studentId));
        System.out.println("==================================================");
    }

    private static void viewCourseEnrollments() {
        System.out.print("Course ID: ");
        String courseId = sc.nextLine();
        Course course = system.findCourse(courseId);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("\n--- COURSE ENROLLMENTS: " + course.getTitle() + " ---");
        boolean found = false;

        for (Enrollment e : system.getEnrollments()) {
            if (e.getCourse() == course) {
                System.out.println(e);
                found = true;
            }
        }

        if (!found) System.out.println("No students enrolled.");
    }
}