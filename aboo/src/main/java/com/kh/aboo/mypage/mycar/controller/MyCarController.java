package com.kh.aboo.mypage.mycar.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.aboo.admin.car.model.vo.Car;
import com.kh.aboo.common.code.Configcode;
import com.kh.aboo.mypage.mycar.model.service.MyCarService;
import com.kh.aboo.user.generation.model.vo.Generation;

@Controller
public class MyCarController {
	private final MyCarService myCarService;
	
	public MyCarController(MyCarService myCarService) {
		this.myCarService = myCarService;
	}

	// 내가등록한 차량 확인
	@GetMapping("/mypage/mycar")
	public void myCar(@SessionAttribute(name = "generation", required = false) Generation generation, Model model) {
		String generationIdx = generation.getGenerationIdx();
		// generationIdx로 등록된 차량 정보 가져온다
		
		List<Car> carList = myCarService.selectCarByGenerationIdx(generationIdx);
		System.out.println(carList);
		model.addAttribute("carList",carList);

	}

	//QR코드 다운받기
	@GetMapping("/mypage/mycarqrdownload")
	public ResponseEntity<FileSystemResource> myQRDownload(String path) {
		// 파일명만 나오게 잘라준다. path : QR경로이다.
		String fileName = path.replace(Configcode.QRCODE_PATH.desc, "");
		// 실제 저장된 곳으로 지정한다.
		String originPath = Configcode.QRCODE_PATH.desc;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName, Charset.forName("UTF-8"))
				.build());
		
		FileSystemResource resources = new FileSystemResource(originPath+fileName);
		return ResponseEntity.ok().headers(headers).body(resources);
		
	}

}
