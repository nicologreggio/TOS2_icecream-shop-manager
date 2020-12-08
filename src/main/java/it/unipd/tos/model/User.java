////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class User {
    private String firstName, lastName;
    private int age;

    public User(String first, String last, int age){
        this.firstName=first;
        this.lastName=last;
        this.age=age;
    }

    public String getDetails(){
        return firstName+" "+lastName+", age: "+age;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getAge(){
        return age;
    }
}
