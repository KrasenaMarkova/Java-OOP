package working_with_abstraction_lab01.studentsystem03;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> repo;

    public StudentSystem() {
        this.repo = new HashMap<>();
    }

    public Map<String, Student> getRepo() {
        return this.repo;
    }

    public void ParseCommand(String[] args) {

        String command = args[0];
        String name = args[1];

        if (command.equals("Create")) {
            int age = Integer.parseInt(args[2]);
            double grade = Double.parseDouble(args[3]);

            repo.putIfAbsent(name, new Student(name, age, grade));
            return;
        }

        Student student = repo.get(name);
        if (student != null) {
            System.out.println(student.toString());
        }
    }
}
