/*
 * Class used as a temporary storage when an admin staff clicks on a course via the viewCourse screen.
 * The class stores the course and nofities the system that a row was clicked on before loading the courseOption view for that course.
 */
package Courses;

/**
 * CAPSTONE PROJECT CSC3003S
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class CourseClicked {
    
    static String course;
    static Boolean clicked=false;
    
    /*
    * Mehtod is called once a row is clicked on
    */
    public static void setCourseClicked(String c){
        
        course =c;
        clicked =true;
        
    }
    /*
     * Method called to get the current course that is being viewed and edited by the admin staff
    */
    public static String getCourse() {
        return course;
    }

    public static Boolean getClicked() {
        return clicked;
    }
    /*
     * Metod called once the adim staff leaves the courseOption view
    */
    public static void Clear(){
        course=null;
        clicked=false;
    }
    
}
