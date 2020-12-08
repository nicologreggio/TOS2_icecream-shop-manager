////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class User {
    private static int nextID=0;
    
    private String firstName, lastName;
    private int age, id;

    public User(String first, String last, int age){
        this.firstName=first;
        this.lastName=last;
        this.age=age;
        this.id=++nextID;
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

    public int getId(){
        return id;
    }

    public static int getNextId(){
        return nextID+1;
    }
}
