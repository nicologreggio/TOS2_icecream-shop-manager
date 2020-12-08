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
    
    private static ArrayList<MenuItem> orderList;
    private static User user;
    
    @BeforeClass
    public static void initialSetup(){
        orderList=new ArrayList<>();
        orderList.add(new MenuItem(MenuItem.ItemType.GELATO, "banana", 1.5));
        orderList.add(new MenuItem(MenuItem.ItemType.BEVANDA, "acqua", 4.0));
        orderList.add(new MenuItem(MenuItem.ItemType.BUDINO, "pinguino", 3.5));
        
        user=new User("Nicolo", "Greggio", 21);
    }


    @Test
    public void testSimpleOrder() throws TakeAwayBillException{
        IcecreamShopTakeAwayBill shop=new IcecreamShopTakeAwayBill();
        assertEquals(9, shop.getOrderPrice(orderList, user), DELTA);
    }
}
