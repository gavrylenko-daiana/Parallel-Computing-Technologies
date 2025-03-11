package third;

import java.util.ArrayList;
import java.util.List;

class Student {
    private final String name;
    private final Group group;
    private final List<Integer> grades = new ArrayList<>();

    public Student(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    public synchronized void addGrade(int grade) {
        grades.add(grade);
    }

    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public Group getGroup() {
        return group;
    }
}
