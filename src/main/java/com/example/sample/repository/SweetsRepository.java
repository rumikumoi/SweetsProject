package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sample.entity.Sweets;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface SweetsRepository extends JpaRepository<Sweets, String> {

	@Modifying//更新形（UPDATE、DELETE）のクエリを指定した場合に必要
	@Query("UPDATE Sweets SET stock = (stock - :stock) where id = :id")
	void updateStock(@Param("stock") int stock, @Param("id") String id);
	//リポジトリに付与する@Query
	//クエリは検索の要求を行うこと
	
	@Modifying  // UPDATE,DELETE時に必要
	@Query("UPDATE Sweets SET stock = (stock + :stock) where id = :id")
	void addStock(@Param("stock") int stock, @Param("id") String id);
	
	@Query("SELECT MAX(id) FROM Sweets")
	String getMaxId();
	
	@Modifying
	@Query("INSERT INTO Sweets(id, item, kind, stock) VALUES(id, item, kind, stock)")
	void insertItem(@Param("id") String id, @Param("item") String item, @Param("kind") String kind, @Param("stock") int stock);
	
	@Modifying  // UPDATE,DELETE時に必要
	@Query("DELETE FROM Sweets WHERE id = :id")
	void deleteStock(@Param("id") String id);
}

//SELECT DISTINCT kind FROM Sweets