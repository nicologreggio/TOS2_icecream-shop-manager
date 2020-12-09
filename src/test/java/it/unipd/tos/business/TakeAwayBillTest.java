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
    private static MenuItem icecream;
    private static MenuItem pudding;
    private static MenuItem drink;
    private static User user;
    private ArrayList<MenuItem> orderList;
    
    @BeforeClass
    public static void initialSetup(){
        shop=new IcecreamShopTakeAwayBill();        
        icecream=new MenuItem(MenuItem.ItemType.GELATO, "banana", 1.5);
        drink=new MenuItem(MenuItem.ItemType.BEVANDA, "acqua", 4.0);
        pudding=new MenuItem(MenuItem.ItemType.BUDINO, "pinguino", 3.5);
        user=new User("Nicolo", "Greggio", 21);
    }

    @Before
    public void beforeEachTest(){
        orderList=new ArrayList<>();
        orderList.add(icecream);
        orderList.add(pudding);
        orderList.add(drink);
    }

    @Test
    public void testSimpleOrder() throws TakeAwayBillException{
        orderList.add(pudding);
        assertEquals(12.5, shop.getOrderPrice(orderList, user), DELTA);
    }

    @Test
    public void testDiscountMoreThan5IceCreams() throws TakeAwayBillException{
        for(int i=0; i<5; ++i){
            orderList.add(icecream);
        }
        assertEquals(15.75, shop.getOrderPrice(orderList, user), DELTA);
        orderList.add(new MenuItem(MenuItem.ItemType.GELATO, "panna", 1.2));
        assertEquals(17.1, shop.getOrderPrice(orderList, user), DELTA);
    }

    @Test
    public void testDiscountOver50Euros() throws TakeAwayBillException{
        orderList.add(new MenuItem(MenuItem.ItemType.BEVANDA, "super", 51));
        assertEquals(54, shop.getOrderPrice(orderList, user), DELTA);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testErrOnMoreThan30Items() throws TakeAwayBillException{
        for(int i=0; i<28; ++i){
            orderList.add(pudding);
        }
        try{
            shop.getOrderPrice(orderList, user);
        }
        catch(TakeAwayBillException e){
            assertEquals("Limite 30 ordini superato.", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCommisionOnLessThan10Euros() throws TakeAwayBillException{
        assertEquals(9.5, shop.getOrderPrice(orderList, user), DELTA);
    }
}
