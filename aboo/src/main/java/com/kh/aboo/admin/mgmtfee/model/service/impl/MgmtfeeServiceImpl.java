package com.kh.aboo.admin.mgmtfee.model.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.repository.MgmtfeeRepository;
import com.kh.aboo.admin.mgmtfee.model.service.MgmtfeeService;
import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.admin.mgmtfee.model.vo.MgmtfeeOverdue;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
import com.kh.aboo.common.util.file.FileUtil;
import com.kh.aboo.common.util.paging.Paging;
import com.kh.aboo.user.generation.model.vo.Generation;

@Service
public class MgmtfeeServiceImpl implements MgmtfeeService{
	

	private final MgmtfeeRepository mgmtfeeRepository;

	public MgmtfeeServiceImpl(MgmtfeeRepository mgmtfeeRepository) {
		this.mgmtfeeRepository = mgmtfeeRepository;
	}
	
	// 아영 : 업로드된 관리비 엑셀 읽기
	@Override
	public Map<String, Object> mgmtfeeRead(MultipartFile file) {
		// 전달받은 MultipartFile file의 타입을 확인한다. xlxs인지 xls인지
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		System.out.println(extension);
		// 1. 업로드된 엑셀파일의 확장자 확인
		Workbook workbook = null;
		if (!extension.equals("xlsx")) {
			System.out.println("예외발동한다.");
			throw new ToAlertException(ErrorCode.ER01);
		}

		try {
			if (extension.equals("xlsx")) {
				System.out.println("xlsx 이다.");
				// excel 2007 이상버전
				workbook = new XSSFWorkbook(file.getInputStream());
			} else if (extension.equals("xls")) {
				// excel 구버전
				workbook = new HSSFWorkbook(file.getInputStream());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. 시트 확인
		Sheet workSheet = workbook.getSheetAt(0);

		// 3. 행 읽기, 시트의 행수만큼
		Map<String, Object> commandMap = new HashedMap<String, Object>();

		for (int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++) {
			Row row = workSheet.getRow(i);
			// System.out.println(row);
			// 4. 셀 읽기, 행의 셀 수만큼
			List<Object> list = new ArrayList<Object>();
			int cells = row.getPhysicalNumberOfCells();
			if(cells > 2) {
				for (int j = 0; j < cells; j++) {
					
					Cell cell = row.getCell(j);
					String value = "";
					// 셀타입에 따라 읽는다.
					// System.out.println(cell.getCellType());
					switch (cell.getCellType()) {
					case FORMULA:
						value = cell.getCellFormula();
						break;
					case NUMERIC:
						value = (int) cell.getNumericCellValue()+"";
						break;
					case STRING:
						value = cell.getStringCellValue() + "";
						break;
					case BLANK:
						value = cell.getBooleanCellValue() + "";
						break;
					case ERROR:
						value = cell.getErrorCellValue() + "";
						break;
					}
					list.add(value);
				}
			} else { 
				throw new ToAlertException(ErrorCode.ER01);
			}
			
			commandMap.put(i + "행", list);
		}

		return commandMap;
	}

	// 읽은 엑셀파일을 mgmtfee vo에 넣고 DB에 추가한다.
	@Override
	public List<Mgmtfee> insertMgmtfee(Map<String, Object> commandMap,String apartmentIdx) {
		List<Mgmtfee> mgmtfeeList = new ArrayList<>();
		Mgmtfee mgmtfee;
		for (String	key : commandMap.keySet()) {
			//System.out.println(commandMap.get(key));
			List<String> list = (List<String>) commandMap.get(key);
			//System.out.println("동호수:" + list.get(0)+"d"+list.get(1)+"h");
			//System.out.println("일반관리비:"+list.get(2));
			
			// 아파트번호와 동호수로 세대관리번호 조회해온다.
			String building = list.get(0);
			String num = list.get(1);
			
			Generation generationInfo = new Generation();
			generationInfo.setApartmentIdx(apartmentIdx);;
			generationInfo.setBuilding(building);
			generationInfo.setNum(num);
			
			Generation generation = mgmtfeeRepository.selectGenerationIdx(generationInfo);

			mgmtfee = new Mgmtfee();
			mgmtfee.setApartmentIdx(apartmentIdx);
			mgmtfee.setGenerationIdx(generation.getGenerationIdx());
			mgmtfee.setGnrlMgmtFee(list.get(2));
			mgmtfee.setCleanFee(list.get(3));
			mgmtfee.setElvtrMnfee(list.get(4));
			mgmtfee.setGenElctr(list.get(5));
			mgmtfee.setComonElctr(list.get(6));
			mgmtfee.setGenWater(list.get(7));
			mgmtfee.setSewer(list.get(8));
			mgmtfee.setExpenses(list.get(9));
			mgmtfee.setGenReduction(list.get(10));
			mgmtfee.setPeriodPayment(list.get(11));
			mgmtfee.setDueDate(Date.valueOf(list.get(12)));
			mgmtfee.setMgmtStartDate(Date.valueOf(list.get(13)));
			mgmtfee.setMgmtEndDate(Date.valueOf(list.get(14)));
			mgmtfee.setMgmtWriteDate(Date.valueOf(list.get(15)));

			// 납부일 기준 이미 삽입한 내역이 있다면 이미 등록된 고지월임을 알려준다.
			int res = mgmtfeeRepository.selectMgmtfeeByGenerationIdxAndDueDate(mgmtfee);
			if (res == 0) {
				mgmtfeeRepository.insertMgmtfee(mgmtfee);
			} else {
				throw new ToAlertException(ErrorCode.SMGMT02);
			}

			mgmtfeeList.add(mgmtfee);

		}
		
		System.out.println("서비스 리스트" + mgmtfeeList);
		return mgmtfeeList;
	}

	// 세대정보 엑셀다운을 위한 리스트 동,호수 리스트받아오기.
	@Override
	public File selectGenerationList(String apartmentIdx) {
		List<String> builging = mgmtfeeRepository.selectBuildingByApartmentIdx(apartmentIdx);
		List<String> num = mgmtfeeRepository.selectNumByApartmentIdx(apartmentIdx);
		
		Map<String, Object> commandMap = new HashMap<>();
		commandMap.put("building", builging);
		commandMap.put("num", num);
		
		//세대 리스트가지고 엑셀 다운로드 양식준비한다.
		
		return mfmtgeeFormExcel(commandMap, apartmentIdx+"_관리비양식.xlsx");
	}
	
	public File mfmtgeeFormExcel(Map<String, Object> commandMap,String fileName) {
		FileUtil fileUtil = new FileUtil();
		return  fileUtil.mfmtgeeFormExcel(commandMap, fileName);
	}
	
	
	public Map<String, Object> searchMap(String apartmentIdx, String standard, String keyword){
		Map<String, Object> searchMap = new HashMap<String, Object>();
		// 페이징 처리 타입 3개
		// 1. 키워드 없는 경우 - 기본페이징
		// 2. 키워드가 관리비인경우 
		// 3. 키워드가 세대정보인경우
		// 4. 키워드가 납기일인 경우
		// 5. 키워드가 미납인경우 
		
		// 공통으로 필요한 정보. 아파트관리번호랑 검색어.
		// switch문으로 검색기준에 따라 서치타입을 정해준다.
		// 이 서치타입 기준으로 동적쿼리 분기가 나뉘게 된다.
		searchMap.put("apartmentIdx", apartmentIdx);
		searchMap.put("searchType", standard);
		searchMap.put("keyword", keyword);
		switch (standard) {
		case "generationInfo":
			// 세대정보로 검색, 101-101 이런식으로 입력이되서 동수 호수로 분리하고 세대관리번호 가져와서 넣어준다.
			Generation generation = new Generation();
			String[] generationInfo = keyword.split("-");
			generation.setApartmentIdx(apartmentIdx);
			generation.setBuilding(generationInfo[0]);
			generation.setNum(generationInfo[1]);
			System.out.println(generation);
			
			// 조회된 세대관리번호를 map에 담아준다.
			String generationIdx = selectGenerationByBuildingAndNum(generation).getGenerationIdx();
			searchMap.put("generationInfo", generationIdx);
			break;
		}
		
		return searchMap;
	}

	@Override
	public Map<String, Object> selectMgmtfeeList(int currentPage,String apartmentIdx, String standard, String keyword) {
		Map<String, Object> searchMap = searchMap(apartmentIdx,standard,keyword);
		System.out.println(searchMap);
		//페이징처리
		Paging paging = Paging.builder()
				.currentPage(currentPage)
				.blockCnt(5)
				.cntPerPage(10)
				.type("mgmtfee")
				.total(mgmtfeeRepository.selectContentCnt(searchMap))
				.build();
		System.out.println(paging.toString());
		// paing 세대조건 정보 넣을 맵
		searchMap.put("paging", paging);
		
		// 페이징정보 포함해서 페이징에 뿌려줄 리스트 받아온다.
		List<Mgmtfee> mgmtfeeList = mgmtfeeRepository.selectMgmtfeeList(searchMap);
		searchMap.put("mgmtfeeList", mgmtfeeList);
		
		// 관리비번호 기준  세대정보 가져오자.
		List<Generation> generationList = new ArrayList<>();
		for (int i = 0; i < mgmtfeeList.size(); i++) {
			String generationIdx = mgmtfeeList.get(i).getGenerationIdx();
			generationList.add(mgmtfeeRepository.selectGenerationByGenerationIdx(generationIdx));
		}
		
		searchMap.put("generationList", generationList);
		return searchMap;
	}

	@Override
	public Mgmtfee selectMgmtfeeByMgmtfeeIdx(String mgmtfeeIdx) {
		return mgmtfeeRepository.selectMgmtfeeByMgmtfeeIdx(mgmtfeeIdx);
	}

	@Override
	public Generation selectGenerationByGenerationIdx(String generationIdx) {
		return mgmtfeeRepository.selectGenerationByGenerationIdx(generationIdx);
	}

	@Override
	public Mgmtfee updateMgmtfee(Mgmtfee mgmtfee) {
		String mgmtfeeIdx = mgmtfee.getMgmtfeeIdx();
		int res = mgmtfeeRepository.updateMgmtfee(mgmtfee);
		//납부금액 업데이트

		if(res > 0) {
			mgmtfeeRepository.procedureUpdatePeriodPayment(mgmtfeeIdx);
		} else {
			throw new ToAlertException(ErrorCode.UMGMT01);
		} 
		
		return mgmtfeeRepository.selectMgmtfeeByMgmtfeeIdx(mgmtfeeIdx);
	}

	@Override
	@Scheduled(cron = "0 0 18 * * *")
	public void procedureMgmtOverDue() {
		// 연체료 계산하는 배치 메서드, 매일 18시 00분에 돈다. 시연할땐 시간 조정
		System.out.println("배치시작");
		// 납기일이 지난  전체 미납 관리비 가져온다.
		List<Mgmtfee> mgmtfeeList = mgmtfeeRepository.selectMgmtfeeListByAll();
		
		for (int i = 0; i < mgmtfeeList.size(); i++) {
			String mgmtfeeIdx = mgmtfeeList.get(i).getMgmtfeeIdx();
			// 관리비번호 프로시저로 보낸다.
			System.out.println(mgmtfeeIdx+"배치수행");
			mgmtfeeRepository.procedureMgmtOverDue(mgmtfeeIdx);
		}
		
	}

	@Override
	public int updateMgmtfeeIsDel(String mgmtfeeIdx) {
		// TODO Auto-generated method stub
		return mgmtfeeRepository.updateMgmtfeeIsDel(mgmtfeeIdx);

	}

	@Override
	public MgmtfeeOverdue selectMgmtfeeOverdue(String mgmtfeeIdx) {
		// TODO Auto-generated method stub
		return mgmtfeeRepository.selectMgmtfeeOverdue(mgmtfeeIdx);
	}

	@Override
	public int updateMgmtfeeOverdue(MgmtfeeOverdue mgmtfeeOverdue) {
		// TODO Auto-generated method stub
		return mgmtfeeRepository.updateMgmtfeeOverdue(mgmtfeeOverdue);
	}

	@Override
	public Generation selectGenerationByBuildingAndNum(Generation generation) {
		if(mgmtfeeRepository.selectGenerationByBuildingAndNum(generation) == null) {
			throw new ToAlertException(ErrorCode.SC03);
			
		}
		return mgmtfeeRepository.selectGenerationByBuildingAndNum(generation);
	}


}
