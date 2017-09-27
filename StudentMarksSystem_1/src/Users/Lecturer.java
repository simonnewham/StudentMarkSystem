/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

/**
 *
 * @author simonnewham
 */
public class Lecturer extends User{
    
    private String course;
    //access rights
    //view marks
    
    public Lecturer(String u,String p, String r, String c){
        
        super(u,p,r);
        course = c;
        
    }
    
    public String getCourse(){
        
        return course;
    }
}
