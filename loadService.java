package com.liveasy.loads.service;

import java.util.List;

import com.liveasy.loads.Dao.loadDto;
import com.liveasy.loads.entity.Loads;
import com.liveasy.loads.ui.RequestModel;

public interface loadService {
	loadDto addLoad(loadDto loaddto);
	Loads getLoadById(String id);
	List<loadDto> getAllLoads();
	loadDto updateLoad(loadDto load,String id);
	void deleteLoadById(String id);
	

}
