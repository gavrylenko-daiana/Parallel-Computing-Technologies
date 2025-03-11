package third;

import java.util.Random;

class Teacher extends Thread {
    private final String name;
    private final Journal journal;
    private final Random random = new Random();

    public Teacher(String name, Journal journal) {
        this.name = name;
        this.journal = journal;
    }

    public String getTeacherName() {
        return name;
    }

    @Override
    public void run() {
        for (int week = 1; week <= Journal.TILL_WEEK; week++) {
            for (Group group : journal.getGroups()) {
                for (Student student : group.getStudents()) {
                    int grade = random.nextInt(101);
                    journal.addRecord(grade, student, this, week);
                }
            }
        }
    }
}
