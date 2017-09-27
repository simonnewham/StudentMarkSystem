/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

/**
 * Class to grant login 
 * @author simonnewham
 */
public class User {
    
    private String username;
    private String password;
    private String role;
    
    public User(String u,String p, String r){
        username=u;
        password=p;   
        role=r;
    }
    
    public String getUser(){
        return username;
    }
    
    public String getPass(){
        return password;
    }
    
    public String getRole(){
        return role;
    }
}
