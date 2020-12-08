////////////////////////////////////////////////////////////////////
// Nicolo Greggio 1193398 
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class MenuItem {
    public enum ItemType{
        GELATO, BUDINO, BEVANDA;
    }

    private ItemType itemType;
    private String name;
    private double price;

    public MenuItem(ItemType itemType, String name, double price){
        this.itemType=itemType;
        this.name=name;
        this.price=price;
    }

    public ItemType getItemType(){
        return itemType;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
}
