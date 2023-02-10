package com.example.sample.entity;

public class BackyardForm extends Sweets{

	private int addStock;
	private String id;
	private String newItem;
	
	public void setAddStock(int addStock) {
		this.addStock = addStock;
	}
	
	public int getAddStock() {
		return addStock;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setItemName(String newItem) {
		this.newItem = newItem;
	}
	
	public String getItemName() {
		return newItem;
	}
}
