
package Courses;

/**
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 * Class used to temporarily store lectures with setters and getters
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
