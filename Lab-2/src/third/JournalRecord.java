package third;

class JournalRecord {
    private final Student student;
    private final int grade;
    private final Teacher teacher;
    private final int week;

    public JournalRecord(Student student, int grade, Teacher teacher, int week) {
        this.student = student;
        this.grade = grade;
        this.teacher = teacher;
        this.week = week;
    }

    public Student getStudent() {
        return student;
    }

    public int getGrade() {
        return grade;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getWeek() {
        return week;
    }
}
