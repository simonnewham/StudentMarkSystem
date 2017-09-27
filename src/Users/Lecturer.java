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
    
    public Lecturer(String u,String f, String l,  String e, String p, String r){
        
        super(u, f, l, e, p, r);
       
        
    }
    
    public String getCourse(){
        
        return course;
    }
}
