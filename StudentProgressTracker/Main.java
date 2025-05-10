import models.*;
import services.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();

        while (true) {
            System.out.println("\n==== Student Progress Tracker ====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Score");
            System.out.println("3. View Report Card");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student Info");
            System.out.println("6. Update Subject Score");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Add Student
                    System.out.print("Enter Student ID: ");
                    String studentID = sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String studentName = sc.nextLine();
                    System.out.print("Enter Student Class: ");
                    String studentClass = sc.nextLine();

                    Student student = new Student(studentID, studentName, studentClass);
                    studentService.addStudent(student);
                    break;

                case 2:
                    // Add multiple subject scores
                    System.out.print("Enter Student ID: ");
                    String scoreStudentId = sc.nextLine();

                    while (true) {
                        System.out.print("Enter Subject: ");
                        String subject = sc.nextLine();

                        System.out.print("Enter Marks: ");
                        int marks = sc.nextInt();
                        sc.nextLine(); // consume newline

                        Score score = new Score(scoreStudentId, subject, marks);
                        studentService.addScore(score);

                        System.out.print("Do you want to add another subject? (y/n): ");
                        String choiceMore = sc.nextLine();
                        if (!choiceMore.equalsIgnoreCase("y")) {
                            break;
                        }
                    }
                    break;

                case 3:
                    // View Report Card
                    System.out.print("Enter Student ID to view Report Card: ");
                    String reportStudentId = sc.nextLine();
                    studentService.viewReportCard(reportStudentId);
                    break;

                case 4:
                    // Delete Student
                    System.out.print("Enter Student ID to delete: ");
                    String deleteId = sc.nextLine();
                    studentService.deleteStudent(deleteId);
                    break;

                case 5:
                    // Update Student Info
                    System.out.print("Enter Student ID to update: ");
                    String updateId = sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Class: ");
                    String newClass = sc.nextLine();
                    studentService.updateStudent(updateId, newName, newClass);
                    break;

                case 6:
                    // Update Subject Score
                    System.out.print("Enter Student ID to update score: ");
                    String updateScoreStudentId = sc.nextLine();
                    System.out.print("Enter Subject to update: ");
                    String updateSubject = sc.nextLine();
                    System.out.print("Enter New Marks: ");
                    int newMarks = sc.nextInt();
                    sc.nextLine(); // consume newline
                    studentService.updateScore(updateScoreStudentId, updateSubject, newMarks);
                    break;

                case 7:
                    // Exit
                    System.out.println("Exiting the application. Goodbye!!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        }
    }
}
