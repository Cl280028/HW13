package hw13;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class StudentGradeManager extends JFrame {
    private JButton checkButton, loadButton, saveButton, addButton, findButton, updateButton, deleteButton, listButton;
    private JTextField nameField, gradeField;
    private JComboBox<String> subjectComboBox;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File file;
    private Map<String, Student> students;
    private String[] subjects = {"Math", "Science", "History", "English", "Art"};

    public StudentGradeManager() {
        super("Student Grade Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new FlowLayout());

        students = new HashMap<>();
        fileChooser = new JFileChooser();
        
        nameField = new JTextField(10);
        gradeField = new JTextField(10);
        subjectComboBox = new JComboBox<>(subjects);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Subject:"));
        add(subjectComboBox);
        add(new JLabel("Grade:"));
        add(gradeField);

        addButton = new JButton("Add/Update Grade");
        addButton.addActionListener(this::addOrUpdateGrade);
        add(addButton);

        findButton = new JButton("Find Grade");
        findButton.addActionListener(this::findGrade);
        add(findButton);

        deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(this::deleteStudent);
        add(deleteButton);

        listButton = new JButton("List Students");
        listButton.addActionListener(this::listStudents);
        add(listButton);

        textArea = new JTextArea(15, 50);
        add(new JScrollPane(textArea));

        setVisible(true);
    }

    private void addOrUpdateGrade(ActionEvent e) {
        String name = nameField.getText();
        String subject = (String) subjectComboBox.getSelectedItem();
        String grade = gradeField.getText();
        students.computeIfAbsent(name, Student::new).setGrade(subject, grade);
        textArea.setText("Grade added/updated for " + name + " in " + subject);
    }

    private void findGrade(ActionEvent e) {
        String name = nameField.getText();
        String subject = (String) subjectComboBox.getSelectedItem();
        Student student = students.get(name);
        if (student != null) {
            String grade = student.getGrade(subject);
            textArea.setText("Found: " + name + " - " + subject + ": " + grade);
        } else {
            textArea.setText("Student not found: " + name);
        }
    }

    private void deleteStudent(ActionEvent e) {
        String name = nameField.getText();
        if (students.remove(name) != null) {
            textArea.setText("Student deleted: " + name);
        } else {
            textArea.setText("Student not found: " + name);
        }
    }

    private void listStudents(ActionEvent e) {
        StringBuilder builder = new StringBuilder("All Students:\n");
        students.values().forEach(student -> {
            builder.append(student.getName()).append(":\n");
            for (String subject : subjects) {
                builder.append("  ").append(subject).append(": ").append(student.getGrade(subject)).append("\n");
            }
        });
        textArea.setText(builder.toString());
    }

    public static void main(String[] args) {
        new StudentGradeManager();
    }
}

