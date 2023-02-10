package com.example.sample.entity;

import java.util.List;

public class BackyardForm extends Sweets {

	// 在庫追加用
	private int addStock;
	// 種類リスト(新規商品追加用)
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
