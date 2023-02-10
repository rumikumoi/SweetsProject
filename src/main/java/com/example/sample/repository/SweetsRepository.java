package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.entity.Sweets;

@Transactional
@Repository
public interface SweetsRepository extends JpaRepository<Sweets, String> {
	@Query("SELECT MAX(id) FROM Sweets")
	String getMaxID();
	
	@Modifying  // UPDATE,DELETE時に必要
	@Query("UPDATE Sweets SET stock = (stock - :stock) where id = :id")
	void updateStock(@Param("stock") int stock, @Param("id") String id);
	
}
