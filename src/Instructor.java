public class Instructor extends Person {
    private String designation;
    private Department department;

    public Instructor(String id, String name, String phone, String email, Department department, String designation) {
        super(id, name, phone, email);
        this.department = department;
        setDesignation(designation);
    }

    @Override
    public String getRole() {
        return "INSTRUCTOR";
    }

    @Override
    public String getDescription() {
        return "Instructor " + getName() + " teaches in " +
                (department == null ? "Unknown Department" : department.getName());
    }

    // getter method
    public String getDesignation() {
        return designation;
    }
    public Department getDepartment() {
        return department;
    }

    // setter method
    public void setDesignation(String designation) {
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Designation cannot be empty.");
        }
        this.designation = designation.trim();
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
