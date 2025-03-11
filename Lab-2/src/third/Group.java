package third;

import java.util.ArrayList;
import java.util.List;

class Group {
    private final String groupName;
    private final List<Student> students = new ArrayList<>();

    public Group(String groupName, int numberOfStudents) {
        this.groupName = groupName;
        createStudents(numberOfStudents);
    }

    private void createStudents(int numberOfStudents) {
        for (int i = 1; i <= numberOfStudents; i++) {
            students.add(new Student(groupName + "_Student" + i, this));
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getGroupName() {
        return groupName;
    }
}
