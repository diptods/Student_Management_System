public abstract class AcademicRecord {
    private final String recordId;

    public AcademicRecord(String recordId) {
        if (recordId == null || recordId.isBlank()) {
            throw new IllegalArgumentException("Record ID cannot be empty.");
        }
        this.recordId = recordId.trim();
    }

    public String getRecordId() {
        return recordId;
    }

    public abstract String getDescription();

    public abstract String getGrade();

    public abstract double getGradePoint();
}