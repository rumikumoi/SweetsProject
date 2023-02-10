package com.example.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Sweets;
import com.example.sample.repository.BackyardRepository;

@Service
public class BackyardService {
	
	@Autowired
	BackyardRepository repository;
	
	public List<String> getKindList() {
		return repository.getKindList();
	}
	
	public String getMaxID() {
		return repository.getMaxID();
	}

	public List<Sweets> getSweetsList() {
		return repository.findAll();
	}
	
	
	public void addStock(String id, int stock) {
		repository.addStock(stock, id);
	}


	public void insertItem(String item, String kind, int stock) {
		String id = String.format("%03d",Integer.parseInt(getMaxID()) + 1);
		repository.InsertItem(id, item, kind, stock);
	}


	public void deleatItem(String id) {
		repository.deleteItem(id);
	}


}
