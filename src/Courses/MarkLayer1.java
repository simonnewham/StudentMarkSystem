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
public class MarkLayer1 {
    
    String coursename;
    String stunum;
    int classM;
    String finalM;
    String exam;
    
    public MarkLayer1(String s, String c, int CM, String FM, String EM){
        
        coursename=c;
        stunum=s;
        classM=CM;
        finalM=FM;
        exam=EM;
    
    }

    public String getCoursename() {
        return coursename;
    }

    public String getStunum() {
        return stunum;
    }

    public int getClassM() {
        return classM;
    }

    public String getFinalM() {
        return finalM;
    }

    public String getExam() {
        return exam;
    }
    
    public String toString(){
        return stunum+","+coursename;
    }
    
    
}
