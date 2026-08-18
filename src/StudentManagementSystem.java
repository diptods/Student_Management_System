import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class StudentManagementSystem {
    private final List<Student> students = new ArrayList<>();
    private final List<Instructor> instructors = new ArrayList<>();
    private final List<Department> departments = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Enrollment> enrollments = new ArrayList<>();
    private final DataManager dataManager = new DataManager();

    public StudentManagementSystem() {
        loadData();
    }

    public List<Student> getStudents() { return students; }
    public List<Instructor> getInstructors() { return instructors; }
    public List<Department> getDepartments() { return departments; }
    public List<Course> getCourses() { return courses; }
    public List<Enrollment> getEnrollments() { return enrollments; }

    public Student findStudent(String id) {
        for (Student s : students)
            if (s.getId().equalsIgnoreCase(id)) return s;
        return null;
    }

    public Instructor findInstructor(String id) {
        for (Instructor i : instructors)
            if (i.getId().equalsIgnoreCase(id)) return i;
        return null;
    }

    public Department findDepartment(String id) {
        for (Department d : departments)
            if (d.getDepartmentId().equalsIgnoreCase(id)) return d;
        return null;
    }

    public Course findCourse(String id) {
        for (Course c : courses)
            if (c.getCourseId().equalsIgnoreCase(id)) return c;
        return null;
    }

    public boolean addDepartment(Department department) {
        if (findDepartment(department.getDepartmentId()) != null) return false;
        departments.add(department);
        saveData();
        return true;
    }

    public boolean addStudent(Student student) {
        if (findStudent(student.getId()) != null) return false;
        students.add(student);
        saveData();
        return true;
    }

    public boolean updateStudent(String id, String name, String phone,
                                 String email, int semester, String deptId) {
        Student s = findStudent(id);
        Department d = findDepartment(deptId);
        if (s == null || d == null) return false;

        s.setName(name);
        s.setPhone(phone);
        s.setEmail(email);
        s.setSemester(semester);
        s.setDepartment(d);
        saveData();
        return true;
    }

    public boolean deleteStudent(String id) {
        Student s = findStudent(id);
        if (s == null) return false;

        for (Enrollment e : enrollments) {
            if (e.getStudent() == s) return false;
        }

        students.remove(s);
        saveData();
        return true;
    }

    public boolean addInstructor(Instructor instructor) {
        if (findInstructor(instructor.getId()) != null) return false;
        instructors.add(instructor);
        saveData();
        return true;
    }

    public boolean addCourse(Course course) {
        if (findCourse(course.getCourseId()) != null) return false;
        courses.add(course);
        saveData();
        return true;
    }

    public Enrollment findEnrollment(String studentId, String courseId) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId().equalsIgnoreCase(studentId)
                    && e.getCourse().getCourseId().equalsIgnoreCase(courseId)) {
                return e;
            }
        }
        return null;
    }

    public boolean enrollStudent(String studentId, String courseId) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseId);

        if (student == null || course == null) return false;
        if (findEnrollment(studentId, courseId) != null) return false;

        String recordId = "ENR" + String.format("%03d", enrollments.size() + 1);
        enrollments.add(new Enrollment(recordId, student, course));
        saveData();
        return true;
    }

    public List<Enrollment> getStudentEnrollments(String studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId().equalsIgnoreCase(studentId)) result.add(e);
        }
        return result;
    }

    public boolean recordMark(String studentId, String courseId, double mark) {
        Enrollment e = findEnrollment(studentId, courseId);
        if (e == null) return false;
        e.setMark(mark);
        saveData();
        return true;
    }

    public double calculateGPA(String studentId) {
        double totalPoints = 0;
        double totalCredits = 0;

        for (Enrollment e : getStudentEnrollments(studentId)) {
            if (e.getMark() >= 0) {
                double credit = e.getCourse().getCredit();
                totalPoints += e.getGradePoint() * credit;
                totalCredits += credit;
            }
        }

        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }

    public synchronized void saveData() {
        dataManager.saveDepartments(departments);
        dataManager.saveStudents(students);
        dataManager.saveInstructors(instructors);
        dataManager.saveCourses(courses);
        dataManager.saveEnrollments(enrollments);
    }

    public void exportTranscriptsAsync() {
        Thread exportThread = new Thread(() -> {
            Path exportDir = Paths.get("exports");
            try {
                Files.createDirectories(exportDir);
                System.out.println("\n[Export Thread] Starting transcript export in background...");

                List<Student> studentList;
                synchronized (this) {
                    studentList = new ArrayList<>(this.students);
                }

                if (studentList.isEmpty()) {
                    System.out.println("[Export Thread] No students to export.");
                    return;
                }

                for (Student s : studentList) {
                    Path filePath = exportDir.resolve(s.getId() + "_transcript.txt");
                    StringBuilder sb = new StringBuilder();
                    sb.append("==================================================\n");
                    sb.append("                 STUDENT TRANSCRIPT\n");
                    sb.append("==================================================\n");
                    sb.append("ID: ").append(s.getId()).append("\n");
                    sb.append("Name: ").append(s.getName()).append("\n");
                    sb.append("Department: ").append(s.getDepartment() == null ? "N/A" : s.getDepartment().getName()).append("\n");
                    sb.append("Semester: ").append(s.getSemester()).append("\n");
                    sb.append("--------------------------------------------------\n");

                    List<Enrollment> records = getStudentEnrollments(s.getId());
                    if (records.isEmpty()) {
                        sb.append("No courses enrolled.\n");
                    } else {
                        for (Enrollment e : records) {
                            sb.append(e.toString()).append("\n");
                        }
                    }
                    sb.append(String.format("GPA: %.2f%n", calculateGPA(s.getId())));
                    sb.append("==================================================\n");

                    Files.writeString(filePath, sb.toString());
                    Thread.sleep(400); // Simulate background work
                }

                System.out.printf("%n[Export Thread] Successfully exported %d transcript(s) to '%s/' folder!%nChoose an option: ",
                        studentList.size(), exportDir.getFileName());
            } catch (IOException e) {
                System.out.println("\n[Export Thread] File error during export: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("\n[Export Thread] Export operation was interrupted.");
            }
        }, "TranscriptExporter-Thread");

        exportThread.start();
    }

    private void loadData() {
        departments.addAll(dataManager.loadDepartments());

        for (String[] p : dataManager.loadStudentRows()) {
            if (p.length >= 6) {
                Department d = findDepartment(p[4]);
                try {
                    students.add(new Student(p[0], p[1], p[2], p[3], d, Integer.parseInt(p[5])));
                } catch (Exception ignored) {}
            }
        }

        for (String[] p : dataManager.loadInstructorRows()) {
            if (p.length >= 6) {
                Department d = findDepartment(p[4]);
                try {
                    instructors.add(new Instructor(p[0], p[1], p[2], p[3], d, p[5]));
                } catch (Exception ignored) {}
            }
        }

        for (String[] p : dataManager.loadCourseRows()) {
            if (p.length >= 4) {
                Instructor i = findInstructor(p[3]);
                try {
                    courses.add(new Course(p[0], p[1], Double.parseDouble(p[2]), i));
                } catch (Exception ignored) {}
            }
        }

        for (String[] p : dataManager.loadEnrollmentRows()) {
            if (p.length >= 4) {
                Student s = findStudent(p[1]);
                Course c = findCourse(p[2]);
                if (s != null && c != null) {
                    try {
                        Enrollment e = new Enrollment(p[0], s, c);
                        double mark = Double.parseDouble(p[3]);
                        if (mark >= 0) e.setMark(mark);
                        enrollments.add(e);
                    } catch (Exception ignored) {}
                }
            }
        }
    }
}
