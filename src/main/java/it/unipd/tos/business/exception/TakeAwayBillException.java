////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Exception{
    private String errMessage;

    TakeAwayBillException(String err){
        this.errMessage=err;
    }

    public String getErrorMessage(){
        return errMessage;
    }
}
