package utils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    
    public static void writeLine(String filePath, String line){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing ro file :" + filePath);
            e.printStackTrace();
        }
    }

    public static List<String> readAllLines(String filePath){
        List<String> lines= new ArrayList<>();
        File file= new File(filePath);
        // if file not exists, return empty list
        if(!file.exists()){
            return lines;
        }

        try (BufferedReader br= new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line= br.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file :"+ filePath);
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeAllLines(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
