package third;

import java.util.ArrayList;
import java.util.List;

class Journal {
    public static final int TILL_WEEK = 3;
    private final List<JournalRecord> records = new ArrayList<>();
    private final List<Group> groups;

    public Journal(List<Group> groups) {
        this.groups = groups;
    }

    public synchronized void addRecord(int grade, Student student, Teacher teacher, int week) {
        records.add(new JournalRecord(student, grade, teacher, week));
        student.addGrade(grade);
    }

    public synchronized void printJournal() {
        for (Group group : groups) {
            System.out.println("\nGroup: " + group.getGroupName());
            for (int i = 1; i <= TILL_WEEK; i++) {
                System.out.println("Week " + i);
                for (JournalRecord record : records) {
                    if (record.getWeek() == i && record.getStudent().getGroup().equals(group)) {
                        System.out.printf("%s: %d (Graded by %s)\n",
                                record.getStudent().getName(),
                                record.getGrade(),
                                record.getTeacher().getTeacherName());
                    }
                }
                System.out.println("---------------------------");
            }
        }
    }

    public synchronized void printFinalGrades() {
        System.out.println("\nFinal Grades:");
        for (Group group : groups) {
            System.out.println("\nGroup: " + group.getGroupName());
            for (Student student : group.getStudents()) {
                System.out.printf("%s: %s\n", student.getName(), student.getGrades());
            }
        }
    }

    public List<Group> getGroups() {
        return groups;
    }
}
