/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;


import Users.*;
import DB.*;

/**
 *
 * @author simonnewham
 */
public class FillDB {
    
    //static DataBase DB= new DataBase();
    
    public static void init_UserDB(DataBase DB){
        
        Student A = new Student("Simon", "123", "S");
        Student B = new Student("Campbell", "123", "S");
        Student C = new Student("Courtney", "123", "S");
        Convenor D = new Convenor("Aslam", "123", "CC");
        Lecturer E = new Lecturer("Edwin", "123", "L", "CSC3003S");
        
        DB.users.add(A);
        DB.users.add(B);
        DB.users.add(C);
        DB.users.add(D);
        DB.users.add(E);
       
    }
    
    public void init_CourseDB(){
        
    }
}
