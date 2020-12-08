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
            int icecreamsCounter=0;
            MenuItem cheaperIcecream=null;
            for(MenuItem i:itemsOrdered){
                total+=i.getPrice();
                if(i.getItemType()==MenuItem.ItemType.GELATO){
                    ++icecreamsCounter;
                    if(cheaperIcecream==null || 
                    i.getPrice()<cheaperIcecream.getPrice()){
                        cheaperIcecream=i;
                    }
                }
            }

            if(icecreamsCounter>5){
                total-=discount(cheaperIcecream.getPrice(), 50);
            }

            return total;
        }

    private double discount(double amnt, double p){
        return amnt*p/100;
    }
}
