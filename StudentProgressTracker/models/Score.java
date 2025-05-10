package models;

public class Score {
    private String studentId;
    private String subject;
    private int marks;

    public Score(String studentId, String subject, int marks){
        this.studentId= studentId;
        this.subject= subject;
        this.marks= marks;
    }

    public String getStudentId(){
        return studentId;
    }
    public String getSubject(){
        return subject;
    }
    public void setMarks(int newMarks){
        this.marks= newMarks;
    }
    public int getMarks(){
        return marks;
    }
    public String toString(){
        return studentId + "," + subject + "," + marks;
    }
    // this method is used to read data form text files and convert them into an Oject
    public static Score fromString(String data){
        String[] parts= data.split(",");
        if(parts.length!= 3) return null;
        return new Score(parts[0], parts[1], Integer.parseInt(parts[2]));
    }
}
