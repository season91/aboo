package com.kh.aboo.mypage.mycar.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.mypage.mycar.model.repository.MyCarRepository;
import com.kh.aboo.mypage.mycar.model.service.MyCarService;

@Service
public class MyCarServiceImpl implements MyCarService {
	private final MyCarRepository myCarRepository;
	
	public MyCarServiceImpl( MyCarRepository myCarRepository) {
		this.myCarRepository = myCarRepository;
	}

	@Override
	public List<Car>  selectCarByGenerationIdx(String generationIdx) {
		return myCarRepository.selectCarByGenerationIdx(generationIdx);
	}


}
