public class Student extends Person {
    private int semester;
    private Department department;

    public Student(String id, String name, String phone, String email,
                   Department department, int semester) {
        super(id, name, phone, email);
        setSemester(semester);
        this.department = department;
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }

    @Override
    public String getDescription() {
        return "Student " + getName() + " is studying in " +
                (department == null ? "Unknown Department" : department.getName());
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        if (semester < 1 || semester > 20) {
            throw new IllegalArgumentException("Semester must be between 1 and 20.");
        }
        this.semester = semester;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
