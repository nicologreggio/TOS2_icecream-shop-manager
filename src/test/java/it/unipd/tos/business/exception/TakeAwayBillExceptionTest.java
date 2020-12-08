////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TakeAwayBillExceptionTest {
    @Test
    public void testBillExceptionMessage(){
        TakeAwayBillException e=new TakeAwayBillException("error");
        assertEquals("error", e.getErrorMessage());
    }
}
