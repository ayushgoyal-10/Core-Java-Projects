package models;

public class Student {
    private String name;
    private String id;
    private String studentClass;
    
    public Student(String id, String name, String studentClass){
        this.id= id;
        this.name= name;
        this.studentClass= studentClass;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getStudentClass(){
        return studentClass;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    // overriding toString() method to see the object
    public String toString(){
        return id + "," + name + "," + studentClass;
    }
    // this method is used to read data form text files and convert them into an Oject
    public static Student fromString(String data){
        String[] parts= data.split(",");
        return new Student(parts[0], parts[1], parts[2]);
    }
}
