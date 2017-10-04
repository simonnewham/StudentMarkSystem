/*
 * Class used to display the marks in fixed colums in the viewMarksT table
 * Each row is a MarkLayer1 object that is loaded into the table
 */
package Courses;

/**
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class MarkLayer1 {
    
    String coursename;
    String stunum;
    int classM;
    String finalM;
    String exam;
    
    /*
     * Constructor used to load all the variables 
    */
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
