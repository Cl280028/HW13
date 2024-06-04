package hw13;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private Map<String, String> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public void setGrade(String subject, String grade) {
        grades.put(subject, grade);
    }

    public String getGrade(String subject) {
        return grades.getOrDefault(subject, "0");  // 使用 getOrDefault 来处理未找到成绩的情况
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Grades: " + grades;
    }
}

