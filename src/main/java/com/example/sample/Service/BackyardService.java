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
		String msg ;
		
		if(addStock > 0) {
			//repositoryでDB更新
			repository.addStock(addStock, id);
			msg = "在庫数を補充しました";
		}else {
			msg = "在庫は0以上を指定してください";
		}
		
		return msg;
	}
	
	public String insertItem(Sweets sweets) {
		String msg = "";
		
		if (sweets.getStock() > 0) {
			repository.insertItem(getNextId(), sweets.getItem(), sweets.getKind(), sweets.getStock() );
			
			msg = "商品を新規追加しました";
		}else {
			msg = "在庫は０以上に指定をしてください";
		}
		
		return msg;
	}
	
	public String getNextId() {
		String maxId = repository.maxId();
			
		int nextId = Integer.parseInt(maxId) + 1 ;
		
		return String.format("%03d", nextId) ;
	}
	
	
	public String itemDelete(String id) {
		String msg = "商品を削除しました";
		
		repository.itemDelete( id );
		
		return msg;
	}

	public List<String> getKindList() {
		return repository.getKindList();
	}


}
