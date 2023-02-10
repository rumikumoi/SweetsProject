package com.example.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Sweets;
import com.example.sample.repository.SweetsRepository;

@Service
public class BackyardService {
	
	@Autowired
	SweetsRepository repository;	
	
	public String getMaxID() {
		return repository.getMaxID();
	}

	public List<Sweets> getSweetsList() {
		return repository.findAll();
	}
	
	
	public void addStock(String id, int stock) {
		repository.addStock(stock, id);
	}


	public void insertItem(String id,String item, String kind, int stock) {
		repository.InsertItem(id, item, kind, stock);
	}


	public void deleatItem(String id) {
		repository.deleteItem(id);
	}


}
