////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Exception{

    private static final long serialVersionUID = 1L;

    TakeAwayBillException(String err) {
        super(err);
    }
}
