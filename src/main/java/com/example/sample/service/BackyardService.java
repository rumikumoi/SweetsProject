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

	/**
	 * 課題5-1.在庫数補充処理
	 * @param id sweetsテーブル更新時の条件
	 * @param addStock 増量分
	 * @return 更新後のメッセージを返却
	 */
	public String addStock(String id, int addStock) {
		String msg;

		if (addStock > 0) {		
			//repositoryでDB更新
			repository.addStock(addStock, id);
			msg = "在庫数を補充しました";
		} else {
			msg = "在庫は0以上を指定してください";
		}

		return msg;
	}

	/**
	 * 課題5-2.新規追加処理
	 * @param sweets 追加するデータ
	 * @return 更新後のメッセージを返却
	 */
	public String insertItem(Sweets sweets) {
		String msg = "";

		if (sweets.getStock() > 0) {
			/** 
			 * repositor.saveで更新できるがSQLの練習のためinsertItemメソッドを利用すること
			 */
			// sweets.setId(getNextId());
			// repository.save(sweets);
			repository.insertItem(getNextId(), sweets.getItem(), sweets.getKind(), sweets.getStock());
			
			msg = "商品を新規追加しました";
		} else {
			msg = "在庫は0以上を指定してください";
		}
		
		return msg;
	}

	// sweetsテーブルにある種類の重複データを削除して返却
	public List<String> getKindList() {
		return repository.getKindList();
	}
	
	// idの最大値を計算し返却
	public String getNextId() {
		String maxId = repository.getMaxId();
		// 現在のidの最大値を取得し、数値に変換して1を加算する
		int nextId = Integer.parseInt(maxId) + 1;
		// Idは3桁のためnextIdが3桁になるよう0埋めして文字列にする
		return String.format("%03d", nextId);
	}


	/**
	 * 課題5-3.削除処理
	 * @param id sweetsテーブルからデータ削除時の条件
	 * @return 更新後のメッセージを返却
	 */
	public String deleatItem(String id) {
		
		/** 
		 * repositor.deleteByIdで削除できるがSQLの練習のためdeleteItemメソッドを利用すること
		 */
		//repository.deleteById(id);
		repository.deleteItem(id);
		String msg = "商品を削除しました";
		
		return msg;
	}


}
