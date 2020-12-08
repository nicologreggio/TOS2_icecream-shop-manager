////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
    @Test
    public void testGetters(){
        String first="Nicolò", last="Greggio"; 
        int age=21;
        User u=new User(first, last, age);
        assertEquals(first, u.getFirstName());
        assertEquals(last, u.getLastName());
        assertEquals(age, u.getAge());
        assertEquals(first+" "+last+", age: "+age, u.getDetails());
    }
}
