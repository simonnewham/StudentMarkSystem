/*
 * Class is super user for admin, convenor and admin
 */
package Users;

/**
 * Class to grant login 
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class User {
    
    private String username;
    private String password;
    private String role;
    private String first;
    private String last;
    private String email; 
    
    public User(String u,String f, String l,  String e, String p, String r){
        username=u;
        password=p;   
        role=r;
        email =e;
        first=f;
        last =l;
       
    }
    
    public String getUsername() {
        return username;
    }

    

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    } 
    
    public String getRole(){
        return role;
    }
}
