/*
 * Class responsible for keeping track of the user currently logged on to the system
 */
package Users;


/**
 *CSC3003S CAPSTONE
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class CurrentUser {
    
    static String username;
    static String course;
    static String role;
    
    public static void setUserName(String u){
        
        username = u;   
    }
    
    public static void setUserRole(String r){
        
        role = r;   
    }
    
    public static String getUserName(){
        
        return username;   
    }
    
     public static String getUserRole(){
        
        return role;   
    }
    
    
}
