package com.ct271.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ct271.entity.Configure;
import com.ct271.repository.IConfigureRepo;

@Service
public class ConfigureService implements IConfigureService{

	@Autowired
	private IConfigureRepo iConfigureRepo;
	
	@Override
	public Configure addConfigure(Configure configure) {
		return iConfigureRepo.save(configure);
	}

}
