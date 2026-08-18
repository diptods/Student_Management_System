public abstract class Person {
    private String id;
    private String name;
    private String phone;
    private String email;

    public Person(String id, String name, String phone, String email) {
        setId(id);
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    public abstract String getRole();

    public abstract String getDescription();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
        this.id = id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? "" : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + phone + " | " + email + " | " + getRole();
    }
}
