////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
    @Test
    public void testUserGetters(){
        String first="Nicolò", last="Greggio"; 
        int age=21;
        int expectedId=User.getNextId();
        User u=new User(first, last, age);
        assertEquals(first, u.getFirstName());
        assertEquals(last, u.getLastName());
        assertEquals(age, u.getAge());
        assertEquals(expectedId, u.getId());
    }
}
