package services;

import models.*;
import utils.*;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final String studentFile= "data/students.txt";
    private final String scoreFile= "data/scores.txt";

    // adding a new student in files
    public void addStudent(Student student){
        FileUtils.writeLine(studentFile, student.toString());
        System.out.println("Student added successfully !");
    }

    // adding a new score in files
    public void addScore(Score score){
        FileUtils.writeLine(scoreFile, score.toString());
        System.out.println("Score added successfully !");
    }

    // view report card using the student Id
    public void viewReportCard(String studentId){
        List<String> studentLines= FileUtils.readAllLines(studentFile);
        List<String> scoreLines= FileUtils.readAllLines(scoreFile);

        Student foundStudent= null;
        List<Score> studentScores= new ArrayList<>();

        // finding the student from our record that is present in the text files;
        for(String line: studentLines){
            Student student= Student.fromString(line); // converting String into Student Object
            if(student.getId().equalsIgnoreCase(studentId)){
                foundStudent= student;
                break;
            }
        }

        if(foundStudent== null){
            System.out.println("Student not found !");
            return;
        }

        // getting the scores
        for(String line: scoreLines){
            Score score= Score.fromString(line);
            if(score != null && score.getStudentId().equalsIgnoreCase(studentId)){
                studentScores.add(score);
            }
        }

        // print report card
        System.out.println("\n Report Card: ");
        System.out.println("ID: " + foundStudent.getId());
        System.out.println("Name: " + foundStudent.getName());
        System.out.println("Class: " + foundStudent.getStudentClass());
        System.out.println("\nSubject-wise scores:");

        int total = 0;
        for(Score score: studentScores){
            System.out.println(" " + score.getSubject() + ": " + score.getMarks());
            total+= score.getMarks();
        }

        if(!studentScores.isEmpty()){
            double average= total/ (double) studentScores.size();
            System.out.printf("\nAverage Marks: %.2f%n", average);
            System.out.println("Grade: " + getGrade(average));
        }else{
            System.out.println("No scores found for this student.");
        }
    }

    // getting grades based on the average 
    public String getGrade(double avg){
        if(avg>=90) return "A+";
        else if(avg>= 80) return "A";
        else if(avg>= 70) return "B";
        else if(avg>= 60) return "C";
        else if(avg>= 50) return "D";
        else return "F";
    }

    public void deleteStudent(String studentId) {
        List<String> studentLines = FileUtils.readAllLines(studentFile);
        List<String> scoreLines = FileUtils.readAllLines(scoreFile);

        // Filter out student
        studentLines.removeIf(line -> {
            Student student = Student.fromString(line);
            return student != null && student.getId().equalsIgnoreCase(studentId);
        });

        // Filter out scores
        scoreLines.removeIf(line -> {
            Score score = Score.fromString(line);
            return score != null && score.getStudentId().equalsIgnoreCase(studentId);
        });

        // Save back filtered lists
        FileUtils.writeAllLines(studentFile, studentLines);
        FileUtils.writeAllLines(scoreFile, scoreLines);

        System.out.println("Student and their scores deleted successfully.");
    }


    public void updateStudent(String studentId, String newName, String newClass) {
        List<String> studentLines = FileUtils.readAllLines(studentFile);
        boolean updated = false;

        for (int i = 0; i < studentLines.size(); i++) {
            Student student = Student.fromString(studentLines.get(i));
            if (student != null && student.getId().equalsIgnoreCase(studentId)) {
                student.setName(newName);
                student.setStudentClass(newClass);
                studentLines.set(i, student.toString());
                updated = true;
                break;
            }
        }

        if (updated) {
            FileUtils.writeAllLines(studentFile, studentLines);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
    
    public void updateScore(String studentId, String subject, int newMarks) {
        List<String> scoreLines = FileUtils.readAllLines(scoreFile);
        boolean updated = false;

        // Loop through each score line to find the matching studentId and subject
        for (int i = 0; i < scoreLines.size(); i++) {
            Score score = Score.fromString(scoreLines.get(i));
            if (score != null && score.getStudentId().equalsIgnoreCase(studentId) && score.getSubject().equalsIgnoreCase(subject)) {
                // Update the score
                score.setMarks(newMarks);
                scoreLines.set(i, score.toString());
                updated = true;
                break;
            }
        }

        // If a score was updated, save the changes back to the file
        if (updated) {
            FileUtils.writeAllLines(scoreFile, scoreLines);
            System.out.println("Score updated successfully.");
        } else {
            System.out.println("Score not found for the given student and subject.");
        }
    }


}
