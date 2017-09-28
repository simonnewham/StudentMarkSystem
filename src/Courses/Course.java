/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Courses;

/**
 *
 * @author simonnewham
 */
public class Course {
    
    String code;
    String year;
    String convenor;
    
    public Course(String c, String y, String cc){
        code=c;
        year=y;
        convenor=cc;
    }

    public String getCode() {
        return code;
    }

    public String getYear() {
        return year;
    }

    public String getConvenor() {
        return convenor;
    }
}
