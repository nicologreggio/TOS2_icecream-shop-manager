////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TakeAwayBillExceptionTest {
    @Test(expected=TakeAwayBillException.class)
    public void testBillExceptionMessage() throws TakeAwayBillException{
        TakeAwayBillException e=new TakeAwayBillException("error");
        assertEquals("error", e.getMessage());
        throw e;
    }
}
