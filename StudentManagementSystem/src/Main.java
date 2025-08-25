import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        sms.loadStudentsFromFile();

        System.out.println("Welcome to the Student Management System!");

        int choice;
        do {
            displayMenu();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    displayAllStudents();
                    break;
                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
            System.out.println();
        } while (choice != 6);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("   Student Management System Menu");
        System.out.println("----------------------------------------");
        System.out.println("1. Add a new student");
        System.out.println("2. Edit an existing student");
        System.out.println("3. Remove a student");
        System.out.println("4. Search for a student by roll number");
        System.out.println("5. Display all students");
        System.out.println("6. Exit");
        System.out.println("----------------------------------------");
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");

        String name = "";
        do {
            System.out.print("Enter student name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
            }
        } while (name.isEmpty());

        int rollNumber = getIntInput("Enter roll number: ");

        String grade = "";
        do {
            System.out.print("Enter student grade: ");
            grade = scanner.nextLine().trim();
            if (grade.isEmpty()) {
                System.out.println("Grade cannot be empty. Please try again.");
            }
        } while (grade.isEmpty());

        Student newStudent = new Student(name, rollNumber, grade);
        sms.addStudent(newStudent);
    }

    private static void editStudent() {
        System.out.println("\n--- Edit Student ---");
        int rollNumber = getIntInput("Enter roll number of student to edit: ");
        Student student = sms.findStudent(rollNumber);

        if (student != null) {
            System.out.println("Student found. What would you like to edit?");

            System.out.print("Enter new name (press Enter to keep current: '" + student.getName() + "'): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                student.setName(newName);
            }

            System.out.print("Enter new grade (press Enter to keep current: '" + student.getGrade() + "'): ");
            String newGrade = scanner.nextLine().trim();
            if (!newGrade.isEmpty()) {
                student.setGrade(newGrade);
            }

            sms.saveStudentsToFile();
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        int rollNumber = getIntInput("Enter roll number of student to remove: ");
        sms.removeStudent(rollNumber);
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        int rollNumber = getIntInput("Enter roll number to search: ");
        Student foundStudent = sms.findStudent(rollNumber);
        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void displayAllStudents() {
        System.out.println("\n--- All Students ---");
        sms.displayAllStudents();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}