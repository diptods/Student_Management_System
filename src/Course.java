public class Course {
    private final String courseId;
    private String title;
    private double credit;
    private Instructor instructor;

    public Course(String courseId, String title, double credit, Instructor instructor){
        if(courseId == null || courseId.isBlank()){
            throw new IllegalArgumentException("Course Id Cannot be empty");
        }
        if(title == null ||title.isBlank()){
            throw new IllegalArgumentException("Course Title Cannot be empty");
        }
        if(credit <= 0 || credit > 10){
            throw new IllegalArgumentException("Course credit must between 0 to 10");
        }
        this.courseId = courseId.trim();
        this.title = title.trim();
        this.credit = credit;
        this.instructor = instructor;

    }

    //all getter method
    public String getCourseId(){
        return courseId;
    }

    public String getTitle(){
        return title;
    }

    public double getCredit(){
        return credit;
    }

    public Instructor getInstructor(){
        return instructor;
    }

    //seter methods
    public void setTitle(String title){
        if(title == null ||title.isBlank()){
            throw new IllegalArgumentException("Course Title Cannot be empty");
        }
        this.title = title.trim();
    }

    public void setCredit(double credit) {
        if(credit <= 0 || credit > 10){
            throw new IllegalArgumentException("Course credit must between 0 to 10");
        }
        this.credit = credit;
    }

    public void setInstructor(Instructor instructor) {

        this.instructor = instructor;
    }

    @Override
    public String toString(){
        String teacher = instructor == null ? "Not assigned" : instructor.getName();

        return courseId + " | " + title + " | Credit: " +  credit + " | Instructor: " + teacher;
    }
}
