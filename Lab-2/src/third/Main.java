package third;

import java.util.*;

class Main {
    public static List<Group> groups = new LinkedList<>() {{
        this.add(new Group("Group1", 5));
        this.add(new Group("Group2", 8));
        this.add(new Group("Group3", 3));
    }};

    public static void main(String[] args) throws InterruptedException {
        Journal journal = new Journal(groups);
        List<Teacher> teachers = createTeachers(journal);

        teachers.forEach(Thread::start);
        for (Teacher teacher : teachers) {
            teacher.join();
        }

        journal.printJournal();
        journal.printFinalGrades();
    }

    private static List<Teacher> createTeachers(Journal journal) {
        return new ArrayList<>() {{
            this.add(new Teacher("Alex", journal));
            this.add(new Teacher("Assistant Kate", journal));
            this.add(new Teacher("Assistant Mike", journal));
            this.add(new Teacher("Assistant Bob", journal));
        }};
    }
}
