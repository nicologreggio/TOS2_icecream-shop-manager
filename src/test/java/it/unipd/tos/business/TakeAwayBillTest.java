////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
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

    @Test
    public void testShopTime(){
        assertEquals(LocalTime.now().withNano(0), shop.getTime().withNano(0));
        LocalTime t=LocalTime.of(18,15);
        shop.setCustomTime(t);
        assertEquals(t, shop.getTime());
        shop.turnCustomTimeOff();
        assertEquals(LocalTime.now().withNano(0), shop.getTime().withNano(0));
    }

    @Test
    public void testGiveawayConditions() throws TakeAwayBillException{
        User u=new User("Davide", "Cesare", 17);

        shop.setCustomTime(LocalTime.of(17,30));
        assertFalse(shop.giveawayApplies(u));
        assertFalse(shop.giveawayApplies(user));
        
        shop.setCustomTime(LocalTime.of(18,1));
        assertTrue(shop.giveawayApplies(u));
        assertFalse(shop.giveawayApplies(user));

        shop.setCustomTime(LocalTime.of(18,59));
        assertTrue(shop.giveawayApplies(u));
        assertFalse(shop.giveawayApplies(user));

        shop.setCustomTime(LocalTime.of(19,00));
        assertFalse(shop.giveawayApplies(u));
        assertFalse(shop.giveawayApplies(user));

        shop.turnCustomTimeOff();
    }

    @Test
    public void test10GiveawaysUnderage() throws TakeAwayBillException{
        User[] u={
            new User("Miles", "House", 3),
            new User("Max", "Russell", 10),
            new User("Alessandro", "Vianello", 8),
            new User("Brigitte", "Kemp", 9),
            new User("Darren", "Cunningham", 9),
            new User("Reyes", "Garcia", 11),
            new User("Chuck", "Vasquez", 9),
            new User("Spencer", "Garrett", 10),
            new User("John", "Park", 3),
            new User("Dionne", "Bernard", 16),
            new User("Davide", "Cesare", 17)
        };

        shop.setCustomTime(LocalTime.of(18,40));
        shop.setRandomSeed(44);

        orderList.add(pudding);
        double price=12.5;

        assertEquals(0, shop.getOrderPrice(orderList, u[0]), DELTA);//true
        assertEquals(price, shop.getOrderPrice(orderList, user), DELTA);//no
        assertEquals(0, shop.getOrderPrice(orderList, u[1]), DELTA);//true
        assertEquals(0, shop.getOrderPrice(orderList, u[2]), DELTA);//true
        assertEquals(0, shop.getOrderPrice(orderList, u[3]), DELTA);//true
        assertEquals(0, shop.getOrderPrice(orderList, u[4]), DELTA);//true
        assertEquals(price, shop.getOrderPrice(orderList, u[2]), DELTA);
        assertEquals(price, shop.getOrderPrice(orderList, u[5]), DELTA);//false
        assertEquals(0, shop.getOrderPrice(orderList, u[5]), DELTA);//true
        assertEquals(0, shop.getOrderPrice(orderList, u[6]), DELTA);//true
        assertEquals(0, shop.getOrderPrice(orderList, u[7]), DELTA);//true
        assertEquals(0, shop.getOrderPrice(orderList, u[8]), DELTA);//true
        assertEquals(0, shop.getOrderPrice(orderList, u[9]), DELTA);//true
        assertEquals(price, shop.getOrderPrice(orderList, u[10]), DELTA);//out


        shop.turnCustomTimeOff();
        shop.resetRandom();
        shop.resetGiveaway();
    }
}
