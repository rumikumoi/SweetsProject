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
		
		//repositoryでDB更新
		
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
		
		return msg;
	}

	/**
	 * 削除処理
	 * @param id sweetsテーブルからデータ削除時の条件
	 * @return 更新後のメッセージを返却
	 */
	public String deleatItem(String id) {
		String msg = "商品を削除しました";
		
		//repositoryでDB更新
		
		return msg;
	}


}
