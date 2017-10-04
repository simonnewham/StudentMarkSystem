/*
*Class is super user and is inherited by the student class 
*/
package Users;

public class Person {
    
    private String username;
    private String first;
    private String last;
    //private String email; 
    
    public Person(String u,String f, String l){
        
        username=u;
       // email =e;
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

   
    
}
