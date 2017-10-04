/*
 * Static Class used to determine what student was clicked on in the viewMarksT table in order to display the correct viewMarks
 * table for that student. Used by viewMarksController to determine what marks to load.
 */
package Courses;

/**
 * CAPSTONE 3003S
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class StudentMarkClick {
    
    static boolean clicked=false;
    static String course;
    static String stunum;
    static String lastclick;
    static boolean backclicked =false;

    /*
    
    */
    public static void Activate(String sn, String cc, String lc){
        course=cc;
        stunum=sn;
        clicked=true;
        lastclick=lc;
        
    }

    public static String getLastclick() {
        return lastclick;
    }
    public static boolean isClicked() {
        return clicked;
    }
    
    public static void setBack(boolean b){
        backclicked=b;
        
    }
    
    public static boolean getBack(){
        return backclicked;
        
    }
    
    public static String getCourse() {
        return course;
    }

    public static String getStunum() {
        return stunum;
    }
    /*
     * Method 
    */
    public static void setClicked(boolean b) {
        clicked=b;
    }
    /*
     * Method clears the class to make sure the table doesnt load the incorrect data when not accessed through the viewMarksT table
    */
    public static void clearClicked(){
        clicked=false;
        course=null;
        stunum=null;
    }
    
    public static void setCourse(String course) {
        StudentMarkClick.course = course;
    }

    public static void setStunum(String stunum) {
        StudentMarkClick.stunum = stunum;
    }
    
}
