////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class IcecreamShopTakeAwayBill implements TakeAwayBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) 
        throws TakeAwayBillException{
            double total=0;
            for(MenuItem i:itemsOrdered){
                total+=i.getPrice();
            }

            return total;
        }
}
