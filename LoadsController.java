package com.liveasy.loads.loadsController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liveasy.loads.Dao.loadDto;
import com.liveasy.loads.entity.Loads;
import com.liveasy.loads.exception.ResourceNotFoundException;
import com.liveasy.loads.service.loadService;
import com.liveasy.loads.ui.RequestModel;
import com.liveasy.loads.ui.ResponseModel;

@RestController
@RequestMapping("/load")
public class LoadsController{
	
	@Autowired
	private loadService lservice;
	
	@Autowired
	private ModelMapper modelMapper;
	@PostMapping("/")
	public ResponseEntity<ResponseModel> addLoad(@RequestBody RequestModel request){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		loadDto loaddto = modelMapper.map(request, loadDto.class);
		loaddto.setShipperId(UUID.randomUUID().toString());
		loadDto tempUserDto = lservice.addLoad(loaddto);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(tempUserDto, ResponseModel.class));

		
		
		
		
		//return new ResponseEntity<>(this.modelMapper.map(addedLoad, ResponseModel.class), HttpStatus.CREATED);
	
	}
	@GetMapping("/")
	public ResponseEntity<List<ResponseModel>> getAllLoads(){

		//return ResponseEntity.ok(this.lservice.getAllLoads());
		List<loadDto> dtos = lservice.getAllLoads();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<ResponseModel> loads = new ArrayList<ResponseModel>();
		for (loadDto l : dtos) {
			loads.add(modelMapper.map(l, ResponseModel.class));
		}
		return ResponseEntity.ok(loads);

		
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel> getLoadById(@PathVariable String id){
		Loads load=this.lservice.getLoadById(id);
		if (load==null) {
			throw new ResourceNotFoundException("load not found");
		}
		return ResponseEntity.ok(this.modelMapper.map(load,ResponseModel.class));
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseModel> updateLoad(@RequestBody ResponseModel lod,@PathVariable String id){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		loadDto loaddto = modelMapper.map(lod, loadDto.class);
		loadDto load=this.lservice.updateLoad(loaddto, id);
		return ResponseEntity.ok(this.modelMapper.map(load, ResponseModel.class));
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable String id){
		this.lservice.deleteLoadById(id);
		return "Load dropped!";
	}
}
