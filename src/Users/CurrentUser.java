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
