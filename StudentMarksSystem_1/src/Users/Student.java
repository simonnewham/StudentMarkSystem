/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Courses.*;
import java.util.ArrayList;

/**
 *
 * @author simonnewham
 */
public class Student extends User {
    
    private String stuNum;
    private ArrayList<Course> courses_registered;
    private ArrayList<String> courses = new ArrayList<String>();
    private ArrayList marks = new ArrayList();
    
    public Student(String u,String p, String r){
        
        super(u,p,r);
       
    }
    
    public ArrayList<Course> getCourses(){
        
        return courses_registered;
    }
    
    public void addMark(String markInfo, int mark ){
        ArrayList markSet = new ArrayList();
        markSet.add(markInfo);
        markSet.add(mark);
        marks.add(markSet);
        System.out.println(this.getUser());
        System.out.println(marks);
    }
    
    public void editMark(String markInfo, int newMark){
        for (int i = 0; i < marks.size(); i++) {
            
            ArrayList markSet = (ArrayList) marks.get(i);
            
            if (markSet.get(0).equals(markInfo) == true){
               ((ArrayList) marks.get(i)).set(1, newMark);
            }
        }
    }
}
