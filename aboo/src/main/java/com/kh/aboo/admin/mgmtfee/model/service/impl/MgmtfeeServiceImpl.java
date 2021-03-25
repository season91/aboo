package com.kh.aboo.admin.mgmtfee.model.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.aboo.admin.mgmtfee.model.repository.MgmtfeeRepository;
import com.kh.aboo.admin.mgmtfee.model.service.MgmtfeeService;
import com.kh.aboo.admin.mgmtfee.model.vo.Mgmtfee;
import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;
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
					value = (int) cell.getNumericCellValue() + "";
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
			commandMap.put(i + "행", list);
		}

		return commandMap;
	}

	// 아영 : 읽은 엑셀파일을 mgmtfee vo에 넣고 DB에 추가한다.
	@Override
	public List<Mgmtfee> insertMgmtfee(Map<String, Object> commandMap) {
		List<Mgmtfee> mgmtfeeList = new ArrayList<>();
		Mgmtfee mgmtfee = null;
		for (String key : commandMap.keySet()) {
			// System.out.println(commandMap.get(key));
			List<String> list = (List<String>) commandMap.get(key);
			//System.out.println("동호수:" + list.get(0)+"d"+list.get(1)+"h");
			//System.out.println("일반관리비:"+list.get(2));
			
			// 동호수로 세대관리번호 조회해온다.
			String generationInfo = list.get(0)+"d"+list.get(1)+"h";
			Generation generation = mgmtfeeRepository.selectGenerationIdx(generationInfo);
			
			mgmtfee = Mgmtfee.builder()
					.generationIdx(generation.getGenerationIdx())
					.gnrlMgmtFee(list.get(2))
					.cleanFee(list.get(3))
					.elvtrMnfee(list.get(4))
					.genElctr(list.get(5))
					.comonElctr(list.get(6))
					.genWater(list.get(7))
					.sewer(list.get(8))
					.expenses(list.get(9))
					.genReduction(list.get(10))
					.periodPayment(list.get(11))
					.dueDate(Date.valueOf(list.get(12)))
					.mgmtStartDate(Date.valueOf(list.get(13)))
					.mgmtEndDate(Date.valueOf(list.get(14)))
					.mgmtWriteDate(Date.valueOf(list.get(15)))
					.build();
			System.out.println(mgmtfee.toString());

			mgmtfeeRepository.insertMgmtfee(mgmtfee);
			mgmtfeeList.add(mgmtfee);
		}
		
		
		return mgmtfeeList;
	}


}