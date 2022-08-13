package com.liveasy.loads.loadsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liveasy.loads.entity.Loads;

@Repository
public interface LoadsRepo extends JpaRepository<Loads, Integer>{
	public Loads findByShipperId(String shipperId);
}
