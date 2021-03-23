(()=>{
	/* 체크된 박스 개수 */
	function checkCnt() {
		let checkboxes = document.querySelectorAll('.vehicle');
		let arr = [];
    	checkboxes.forEach((e)=>{
  		  if(e.checked == true){
			arr.push(e);
  		  }
      	})
		return arr;
	};
	
	/* 체크박스 전체 선택 해제*/
	document.querySelector('.checkAll').addEventListener('click',()=>{
		let allvehicle = document.querySelector('.allvehicle');
        let checkboxes = document.querySelectorAll('.vehicle');
        
        if(allvehicle.checked == true){
           checkboxes.forEach((e)=>{
               e.checked = true;
            })
        } else if(allvehicle.checked == false){
           checkboxes.forEach((e)=>{
               e.checked = false;
            })
        }
	});
	
	/* 삭제버튼 누를시 체크개수 카운트해 모달창에 건수 넣어주기.*/
	document.querySelector('.vehicleDelete').addEventListener('click',()=>{
		let checkboxes = checkCnt();
		console.dir(checkboxes.length);
   	 	document.getElementById('result').innerText = checkboxes.length;
	});
    
	/* 등록모달청에서 등록하기*/
	document.querySelector('.btn-add-vehicle').addEventListener('click',()=>{
		console.dir('여기오나?');
	});
	
	/* 수정모달청에서 수정하기*/
	document.querySelector('.vehicleModify').addEventListener('click',()=>{
		let inpBuilding = document.querySelector('#modify-generation-bulding');
		let inpNumber = document.querySelector('#modify-generation-number');
		
		let arr = checkCnt();
		if(arr.length > 1) {
			inpBuilding.placeholder = '하나만 선택하세요.';
			inpNumber.placeholder = '하나만 선택하세요.';
		} else {
  			let parentElement = arr[0].parentElement.parentElement.parentElement.parentElement;
			let tdElement = parentElement.children;
			inpBuilding.placeholder = tdElement[1].outerText;
			inpNumber.placeholder = tdElement[2].outerText;
		}
		
	});
	
	document.querySelector('.btn-search-vehicleNumber').addEventListener('click',()=>{
		let keyword = document.querySelector('.vehicle-keyword');
		console.dir(keyword.value);
		console.dir("차량버노로 검색 test!!!");
	});
	
	document.querySelector('.btn-search-generationIdx').addEventListener('click',()=>{
		let keyword = document.querySelector('.generation-keyword');
		console.dir(keyword.value);
		console.dir("세대정보로 검색!!!");
	});
	
})();  