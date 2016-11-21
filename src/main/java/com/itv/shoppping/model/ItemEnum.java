package com.itv.shoppping.model;

/**
 * Enum of products
 * 
 * @author sushil.a.choudhary
 *
 */
public enum ItemEnum {

    ITEM_A("A", 50),
    ITEM_B("B", 30),
    ITEM_C("C", 20),
    ITEM_D("D", 15);

	private String itemName;
	private Integer price;
	
    ItemEnum(String itemName, Integer price) {
    	this.itemName = itemName;
    	this.price = price;
    }

	public String getItemName() {
		return itemName;
	}

	public Integer getPrice() {
		return price;
	}
}
