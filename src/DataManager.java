import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataManager {
    private final Path dataDir = Paths.get("data");
    private final Path departmentsFile = dataDir.resolve("departments.txt");
    private final Path studentsFile = dataDir.resolve("students.txt");
    private final Path instructorsFile = dataDir.resolve("instructors.txt");
    private final Path coursesFile = dataDir.resolve("courses.txt");
    private final Path enrollmentsFile = dataDir.resolve("enrollments.txt");

    public DataManager() {
        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            System.out.println("Could not create data directory: " + e.getMessage());
        }
    }

    private void writeLines(Path file, List<String> lines) {
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }

    private List<String[]> readRows(Path file) {
        List<String[]> rows = new ArrayList<>();
        if (!Files.exists(file)) return rows;

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) rows.add(line.split("\\|", -1));
            }
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }
        return rows;
    }

    public void saveDepartments(List<Department> departments) {
        List<String> lines = new ArrayList<>();
        for (Department d : departments) {
            lines.add(d.getDepartmentId() + "|" + d.getName());
        }
        writeLines(departmentsFile, lines);
    }

    public List<Department> loadDepartments() {
        List<Department> list = new ArrayList<>();
        for (String[] p : readRows(departmentsFile)) {
            if (p.length >= 2) list.add(new Department(p[0], p[1]));
        }
        return list;
    }

    public void saveStudents(List<Student> students) {
        List<String> lines = new ArrayList<>();
        for (Student s : students) {
            String deptId = s.getDepartment() == null ? "" : s.getDepartment().getDepartmentId();
            lines.add(String.join("|", s.getId(), s.getName(), s.getPhone(),
                    s.getEmail(), deptId, String.valueOf(s.getSemester())));
        }
        writeLines(studentsFile, lines);
    }

    public List<String[]> loadStudentRows() {
        return readRows(studentsFile);
    }

    public void saveInstructors(List<Instructor> instructors) {
        List<String> lines = new ArrayList<>();
        for (Instructor i : instructors) {
            String deptId = i.getDepartment() == null ? "" : i.getDepartment().getDepartmentId();
            lines.add(String.join("|", i.getId(), i.getName(), i.getPhone(),
                    i.getEmail(), deptId, i.getDesignation()));
        }
        writeLines(instructorsFile, lines);
    }

    public List<String[]> loadInstructorRows() {
        return readRows(instructorsFile);
    }

    public void saveCourses(List<Course> courses) {
        List<String> lines = new ArrayList<>();
        for (Course c : courses) {
            String instructorId = c.getInstructor() == null ? "" : c.getInstructor().getId();
            lines.add(String.join("|", c.getCourseId(), c.getTitle(),
                    String.valueOf(c.getCredit()), instructorId));
        }
        writeLines(coursesFile, lines);
    }

    public List<String[]> loadCourseRows() {
        return readRows(coursesFile);
    }

    public void saveEnrollments(List<Enrollment> enrollments) {
        List<String> lines = new ArrayList<>();
        for (Enrollment e : enrollments) {
            lines.add(String.join("|", e.getRecordId(), e.getStudent().getId(),
                    e.getCourse().getCourseId(), String.valueOf(e.getMark())));
        }
        writeLines(enrollmentsFile, lines);
    }

    public List<String[]> loadEnrollmentRows() {
        return readRows(enrollmentsFile);
    }
}