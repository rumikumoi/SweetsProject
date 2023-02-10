package com.example.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.entity.Sweets;

@Transactional
@Repository
public interface BackyardRepository extends JpaRepository<Sweets, String> {
	@Query("SELECT MAX(id) FROM Sweets")
	String getMaxID();

	@Modifying
	@Query("UPDATE Sweets SET stock = (stock + :stock) where id = :id")
	void addStock(@Param("stock") int stock, @Param("id") String id);
	
	@Modifying
	@Query("INSERT INTO Sweets (id, item, kind, stock) VALUES ( :id, :item, :kind, :stock)")
	void InsertItem(@Param("id") String id, @Param("item") String item, @Param("kind") String kind, @Param("stock") int stock);
	
	@Modifying
	@Query("DELETE FROM Sweets where id = :id")
	void deleteItem(@Param("id") String id);
	
	@Query("SELECT DISTINCT kind FROM Sweets")
	List<String> getKindList();
}
