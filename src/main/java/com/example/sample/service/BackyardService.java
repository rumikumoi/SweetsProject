package com.example.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.dto.BackyardData;
import com.example.sample.entity.Sweets;
import com.example.sample.repository.SweetsRepository;

@Service
public class BackyardService {
	
	@Autowired
	SweetsRepository repository;	

	/**
	 * 商品一覧取得
	 * @return List<Sweets>
	 */
	public List<Sweets> getSweetsList() {
		List<Sweets> list = repository.findAll();
		return list;
	}
	
	/**
	 * 在庫数補充処理
	 * @param id sweetsテーブル更新時の条件
	 * @param addStock 増量分
	 * @return 更新後のメッセージを返却
	 */
	public String addStock(String id, int addStock) {
		String msg = "在庫数を補充しました";
		
		List<BackyardData> addStockList = new ArrayList<BackyardData>();
		
		if (addStock > 0) {
			BackyardData backyard = new BackyardData();
			backyard.setId(id);
			backyard.setAddStock(addStock);
			addStockList.add(backyard);
			
			//repositoryでDB更新
			repository.addStock(addStock, id);
		}
		
		return msg;
	}


	/**
	 * 新規追加処理
	 * @param sweets 追加するためのデータ
	 * @return 更新後のメッセージを返却
	 */
	public String insertItem(Sweets sweets) {
		String msg = "商品を新規追加しました";
		
		//repositoryでDB更新
//		repository.insertItem(sweets);
		
		return msg;
	}

	/**
	 * 削除処理
	 * @param id sweetsテーブルからデータ削除時の条件
	 * @return 更新後のメッセージを返却
	 */
	public String deleatItem(String id) {
		String msg = "商品を削除しました";
		
		if (id != null) {
			BackyardData backyard = new BackyardData();
			backyard.setId(id);
			
			repository.deleteStock(id);
		}
		
		
		//repositoryでDB更新
		repository.deleteStock(id);
		
		return msg;
	}


}
