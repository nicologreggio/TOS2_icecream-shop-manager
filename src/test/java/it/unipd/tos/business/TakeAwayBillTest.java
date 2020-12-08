////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillTest {
    private final static double DELTA=1e-4;
    
    private static IcecreamShopTakeAwayBill shop;
    private static ArrayList<MenuItem> orderList;
    private static MenuItem icecream;
    private static MenuItem pudding;
    private static MenuItem drink;
    private static User user;
    
    @BeforeClass
    public static void initialSetup(){
        shop=new IcecreamShopTakeAwayBill();        
        orderList=new ArrayList<>();
        icecream=new MenuItem(MenuItem.ItemType.GELATO, "banana", 1.5);
        drink=new MenuItem(MenuItem.ItemType.BEVANDA, "acqua", 4.0);
        pudding=new MenuItem(MenuItem.ItemType.BUDINO, "pinguino", 3.5);

        orderList.add(icecream);
        orderList.add(pudding);
        orderList.add(drink);
        
        user=new User("Nicolo", "Greggio", 21);
    }


    @Test
    public void testSimpleOrder() throws TakeAwayBillException{
        assertEquals(9, shop.getOrderPrice(orderList, user), DELTA);
    }

    @Test
    public void testDiscountMoreThan5IceCreams() throws TakeAwayBillException{
        ArrayList<MenuItem> l=new ArrayList<>(orderList);
        for(int i=0; i<5; ++i){
            l.add(icecream);
        }
        assertEquals(15.75, shop.getOrderPrice(l, user), DELTA);
        l.add(new MenuItem(MenuItem.ItemType.GELATO, "panna", 1.2));
        assertEquals(17.1, shop.getOrderPrice(l, user), DELTA);
    }
}
