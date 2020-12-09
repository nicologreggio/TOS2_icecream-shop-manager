////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MenuItemTest {
    private final static double DELTA=1e-4;
    
    @Test
    public void testMenuGetters(){
        MenuItem.ItemType[] types={
            MenuItem.ItemType.BEVANDA, 
            MenuItem.ItemType.GELATO, 
            MenuItem.ItemType.BUDINO};
        String[] names={"acqua", "cioccolato", "biancaneve"};
        double[] prices={4, 1.5, 3.5};
        MenuItem a=new MenuItem(types[0], names[0], prices[0]);
        MenuItem b=new MenuItem(types[1], names[1], prices[1]);
        MenuItem c=new MenuItem(types[2], names[2], prices[2]);
        
        assertEquals(types[0], a.getItemType());
        assertEquals(types[1], b.getItemType());
        assertEquals(types[2], c.getItemType());

        assertEquals(names[0], a.getName());
        assertEquals(names[1], b.getName());
        assertEquals(names[2], c.getName());

        assertEquals(prices[0], a.getPrice(), DELTA);
        assertEquals(prices[1], b.getPrice(), DELTA);
        assertEquals(prices[2], c.getPrice(), DELTA);
    }
}
