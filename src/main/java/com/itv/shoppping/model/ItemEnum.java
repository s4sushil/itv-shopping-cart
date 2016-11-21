package com.itv.shoppping.model;

/**
 * Enum of products
 * 
 * @author sushil.a.choudhary
 *
 */
public enum ItemEnum {

    APPLE("Apple", 60),
    ORANGE("Orange", 25);

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
