package com.liveasy.loads.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liveasy.loads.exception.ResourceNotFoundException;
import com.liveasy.loads.Dao.loadDto;
import com.liveasy.loads.entity.Loads;
import com.liveasy.loads.loadsRepository.LoadsRepo;
import com.liveasy.loads.ui.RequestModel;

@Service
public class loadServiceImpl implements loadService{

	

	@Override
	public void deleteLoadById(String id) {
		// TODO Auto-generated method stub
		Loads load=this.lrep.findByShipperId(id);
		if (load==null) {
			throw new ResourceNotFoundException("Load not found...Invalid id");
		}
		this.lrep.delete(load);
	}









	@Override
	public loadDto updateLoad(loadDto load, String id) {
		Loads l=this.lrep.findByShipperId(id);
		if (l==null) {
			throw new ResourceNotFoundException("Load not found...Invalid id");
		}
		l.setLoadingPoint(load.getLoadingPoint());
		l.setUnloadingPoint(load.getUnloadingPoint());
		l.setProductType(load.getTruckType());
		l.setNoOfTrucks(load.getNoOfTrucks());
		l.setComment(load.getComment());
		l.setWeight(load.getWeight());
		l.setDate(load.getDate());
		//l.setShipperId(load.getShipperId());
		this.lrep.save(l);
		return this.modelMapper.map(l, loadDto.class);
	}




	




	@Override
	public List<loadDto> getAllLoads() {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Loads> list = lrep.findAll();
		List<loadDto> dtos = new ArrayList<loadDto>();
		for (Loads l : list) {
			dtos.add(modelMapper.map(l, loadDto.class));
		}
		return dtos;
	
	}

	


	@Override
	public Loads getLoadById(String id) {
		// TODO Auto-generated method stub
		Loads l = lrep.findByShipperId(id);
		if (l == null) {
			throw new ResourceNotFoundException("load not found...Invalid Id");
		}

		return l;
		//return null;
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LoadsRepo lrep;
	
	@Override
	public loadDto addLoad(loadDto loaddto) {
		
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Loads loads = modelMapper.map(loaddto, Loads.class);
		//StringBuffer buffer = new StringBuffer(userDto.getPassword());
		//userEntity.setEncryptedPassword(buffer.reverse().toString());
		Loads tempload = lrep.save(loads);

		return modelMapper.map(tempload, loadDto.class);
		
		
		//loaddto.setShipperId(UUID.randomUUID().toString());
		//Loads loads=this.modelMapper.map(loaddto, Loads.class);
		//loads.setShipperId(UUID.randomUUID().toString());
		//Loads addedLoad=this.lrep.save(loads);
		//Loads addedLoad=this.lrep.save(loads);
		//return this.modelMapper.map(addedLoad, loadDto.class);
	}



		
}
