import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private final List<Student> students;
    private static final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (findStudent(student.getRollNumber()) != null) {
            System.out.println("Error: Student with roll number " + student.getRollNumber() + " already exists.");
            return;
        }

        students.add(student);
        System.out.println("Student added successfully.");
        saveStudentsToFile();
    }

    public void removeStudent(int rollNumber) {
        boolean removed = students.removeIf(student -> student.getRollNumber() == rollNumber);
        if (removed) {
            System.out.println("Student with roll number " + rollNumber + " removed successfully.");
            saveStudentsToFile();
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    public Student findStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving student data.");
            e.printStackTrace();
        }
    }

    public void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String name = parts[0];
                        int rollNumber = Integer.parseInt(parts[1]);
                        String grade = parts[2];
                        students.add(new Student(name, rollNumber, grade));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line in file: " + line);
                    }
                }
            }
            System.out.println("Student data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing student data found. Starting with an empty system.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading student data.");
            e.printStackTrace();
        }
    }
}