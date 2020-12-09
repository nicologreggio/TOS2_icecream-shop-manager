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
            if(itemsOrdered.size()>30){
                throw new TakeAwayBillException("Limite 30 ordini superato.");
            } 
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

            if(total>50){
                total-=discount(total, 10);
            }

            if(total<10){
                total+=0.5;
            }

            return total;
        }

    private double discount(double amnt, double p){
        return amnt*p/100;
    }
}
