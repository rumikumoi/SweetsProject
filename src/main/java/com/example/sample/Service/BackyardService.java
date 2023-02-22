package com.example.sample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Sweets;
import com.example.sample.repository.SweetsRepository;

@Service
public class BackyardService {
	
	@Autowired
	SweetsRepository repository;

	public List<Sweets> getSweetsList() {
		List<Sweets>list = repository.findAll();
		return list;
	}
	
	public String addStock(String id, int addStock) {
		String msg = "在庫数を補充しました";
		
		//repositoryでDB更新
		repository.addStock(addStock, id);
		
		return msg;
	}
	
	public String insertItem(Sweets sweets) {
		String msg = "商品を新規追加しました";
		
		repository.insertItem( );
		
		return msg;
	}
	
	public String itemDelete(String id) {
		String msg = "商品を削除しました";
		
		repository.itemDelete( id );
		
		return msg;
	}


}
