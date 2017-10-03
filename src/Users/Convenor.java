/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Users.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author simonnewham
 */
public class Convenor extends User{
    public String convenorSubject;
    
    
    public Convenor(String u,String f, String l,  String e, String p, String r){
        
        super(u, f, l, e, p, r);
       
    }
    
    public static void importMarks(String course, String fileName, String assessName) throws FileNotFoundException, IOException, SQLException{
        
        String currentcourse = course;
        String currentcourselower = currentcourse.toLowerCase();
        
        Scanner s = new Scanner(new File(fileName));
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        String insertColumn =   "ALTER TABLE users." + currentcourselower + "_marks ADD "+ assessName +" INT";
                    
        myStatement.executeUpdate(insertColumn);        
        while (s.hasNext()){
            String tempString = s.next();
            String[] lineParts = tempString.split(";");
            String studentName = lineParts[0];
            String testMarkString = lineParts[1];
            int testMark = Integer.parseInt(testMarkString);
            
            String insertMark = "UPDATE users." + currentcourselower + "_marks SET " + assessName + "='" + testMarkString + "' WHERE studentname='" + studentName + "'";
            myStatement.executeUpdate(insertMark);    
        }
        myConn.close();
        
    }
    
    public static void editMarks(String course,String studentName, String markInfo, String newMark) throws SQLException{
        String currentcourse = course;
        String currentcourselower = currentcourse.toLowerCase();
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        
        String editMark = "UPDATE users." + currentcourselower + "_marks SET " + markInfo + "='" + newMark + "' WHERE studentname='" + studentName + "'";
        myStatement.executeUpdate(editMark);
        myConn.close();
    }
    
}
