/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import DB.DataBase;
import Users.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author simonnewham
 */
public class Convenor extends User{
    
    
    public Convenor(String u,String p, String r){
        
        super(u,p,r);
       
    }
    
    public static void importMarks(String fileName) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String currentLine;
        while ((currentLine = br.readLine()) != null){
            String[] lineParts = currentLine.split(" ");
            String studentName = lineParts[0];
            String testMark = lineParts[1];
            String testName = lineParts[2];
            
        ArrayList<User> users = DB.DataBase.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUser().equals(studentName)){
                Student currentStudent = (Student) users.get(i);
                currentStudent.addMark(testName,Integer.parseInt(testMark));
            }
        }           
        }
        
    }
    
    public static void editMarks(String studentName, String markInfo, int newMark){
        ArrayList<User> users = DB.DataBase.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUser().equals(studentName)){
                Student currentStudent = (Student) users.get(i);
                currentStudent.editMark(markInfo,newMark);
            }
        }
        
    }
    
}
