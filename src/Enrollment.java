public class Enrollment extends AcademicRecord {
    private final Student student;
    private final Course course;
    private double mark;

    public Enrollment(String recordId, Student student, Course course) {
        super(recordId);
        if (student == null || course == null) {
            throw new IllegalArgumentException("Student and course are required.");
        }
        this.student = student;
        this.course = course;
        this.mark = -1;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Mark must be between 0 and 100.");
        }
        this.mark = mark;
    }

    @Override
    public String getGrade() {
        if (mark < 0) return "N/A";
        if (mark >= 80) return "A+";
        if (mark >= 75) return "A";
        if (mark >= 70) return "A-";
        if (mark >= 65) return "B+";
        if (mark >= 60) return "B";
        if (mark >= 55) return "B-";
        if (mark >= 50) return "C+";
        if (mark >= 45) return "C";
        if (mark >= 40) return "D";
        return "F";
    }

    @Override
    public double getGradePoint() {
        return switch (getGrade()) {
            case "A+" -> 4.00;
            case "A" -> 3.75;
            case "A-" -> 3.50;
            case "B+" -> 3.25;
            case "B" -> 3.00;
            case "B-" -> 2.75;
            case "C+" -> 2.50;
            case "C" -> 2.25;
            case "D" -> 2.00;
            default -> 0.00;
        };
    }

    @Override
    public String getDescription() {
        return student.getId() + " enrolled in " + course.getCourseId();
    }

    @Override
    public String toString() {
        String markText = mark < 0 ? "Not recorded" : String.format("%.2f", mark);
        return getRecordId() + " | " + student.getId() + " | " +
                course.getCourseId() + " | Mark: " + markText +
                " | Grade: " + getGrade() +
                " | GP: " + String.format("%.2f", getGradePoint());
    }
}