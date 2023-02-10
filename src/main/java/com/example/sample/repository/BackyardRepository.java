package com.example.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sample.entity.Sweets;

import jakarta.transaction.Transactional;

@Transactional //INSERT,UPDATE,DELETE時に必要
@Repository
public interface BackyardRepository extends JpaRepository<Sweets, String> {

	/**
	 *  課題5-1 在庫補充用処理
	 */
	@Modifying  //INSERT,UPDATE,DELETE時に必要
	@Query("UPDATE Sweets SET stock = (stock + :stock) WHERE id = :id")
	void addStock(@Param("stock") int stock, @Param("id") String id);


	/**
	 *  課題5-2 新規商品追加用処理
	*/
	//SELECT文にDISTINCTをつけると、重複する内容を削除して取得できる
	@Query("SELECT DISTINCT kind FROM Sweets")
	List<String> getKindList();

	//MAX(カラム名)で、テーブルの中で最大の値を取得できる
	@Query("SELECT MAX(id) FROM Sweets")
	String getMaxId();

	//Insertでテーブルに新規レコード追加
	@Modifying //INSERT,UPDATE,DELETE時に必要
	@Query("INSERT INTO Sweets(id, item, kind, stock) VALUES (:id, :item, :kind, :stock)")
	void insertItem(@Param("id") String id, @Param("item") String item, @Param("kind") String kind, @Param("stock") int stock);
	
	
	/**
	 *  課題5-3 商品削除用処理
	*/
	@Modifying //INSERT,UPDATE,DELETE時に必要
	@Query("DELETE FROM Sweets  WHERE id = :id")
	void deleteItem(@Param("id") String id);
	
}
