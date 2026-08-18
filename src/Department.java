public class Department {
    private final String departmentId;
    private String name;

    public Department(String departmentId, String name) {
        if (departmentId == null || departmentId.isBlank()) {
            throw new IllegalArgumentException("Department ID cannot be empty.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }
        this.departmentId = departmentId.trim();
        this.name = name.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return departmentId + " | " + name;
    }
}
