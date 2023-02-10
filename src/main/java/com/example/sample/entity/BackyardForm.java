package com.example.sample.entity;

import java.util.List;

public class BackyardForm extends Sweets{
	private int addStock;
	private List<String> kindList;
	
	public void setAddStock(int addStock) {
		this.addStock = addStock;
	}

	public int getAddStock() {
		return addStock;
	}
	
	public void setKindList(List<String> kindList) {
		this.kindList = kindList;
	}	
	
	public List<String> getKindList() {
		return kindList;
	}
	
}
