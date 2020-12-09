////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class IcecreamShopTakeAwayBill implements TakeAwayBill {
    private final static LocalTime startGiveaway=LocalTime.of(18,00);
    private final static LocalTime endGiveaway=LocalTime.of(19,00);
    
    private LocalTime customTime;
    private boolean useCustomTime=false;
    private int giveawayCounter=10;
    private HashSet<User> giveawayUsers=new HashSet<>();
    private Random random=new Random();

    public void setRandomSeed(long l){
        random.setSeed(l);
    }

    public void resetRandom(){
        random=new Random();
    }

    public void setCustomTime(LocalTime t){
        useCustomTime=true;
        customTime=t;
    }

    public void turnCustomTimeOff(){
        useCustomTime=false;
    }

    public final LocalTime getTime(){
        return useCustomTime?customTime:LocalTime.now();
    }

    public void resetGiveaway(){
        giveawayCounter=10;
        giveawayUsers.clear();
    }

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) 
        throws TakeAwayBillException{

            double total=0;
            int icecreamsCounter=0;
            MenuItem cheaperIcecream=null;

            checkOrderSize(itemsOrdered.size());
            
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

            /* 
            * Dal requisito non è chiaro se si intende ordinazioni generali
            * e manca quindi bevande, oppure se effetivamente bisogna 
            * considerare solo gelati e budini per il calcolo dello sconto.
            * Dato che questa condizione appare strana, si è deciso di 
            * applicare lo sconto sul totale complessivo dell'ordine
            * indipendendemente dal tipo
            */
            if(total>50){
                total-=discount(total, 10);
            }

            if(total<10){
                total+=0.5;
            }

            if(giveawayApplies(user)){
                if(random.nextBoolean()){
                    total=0;
                    --giveawayCounter;
                    giveawayUsers.add(user);
                }
            }

            return total;
        }

    boolean giveawayApplies(User u){
        LocalTime now=getTime();
        boolean isTime=now.isAfter(startGiveaway) && now.isBefore(endGiveaway);
        boolean userOk=u.getAge()<18 && !giveawayUsers.contains(u);
        return  isTime && giveawayCounter>0 && userOk;
    }

    private double discount(double amnt, double p){
        return amnt*p/100;
    }

    private void checkOrderSize(int s) throws TakeAwayBillException{
        if(s>30){
            throw new TakeAwayBillException("Limite 30 ordini superato.");
        }
    }
}
