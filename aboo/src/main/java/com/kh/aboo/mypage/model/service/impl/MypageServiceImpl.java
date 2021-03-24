package com.kh.aboo.mypage.model.service.impl;

import org.springframework.stereotype.Service;

import com.kh.aboo.mypage.model.repository.MypageRepository;
import com.kh.aboo.mypage.model.service.MypageService;

@Service
public class MypageServiceImpl implements MypageService {

	private final MypageRepository mypageRepository;
	
	public MypageServiceImpl(MypageRepository mypageRepository) {
		this.mypageRepository = mypageRepository;
	}
}
