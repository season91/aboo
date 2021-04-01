(()=>{
	/* 체크된 박스 개수 */
	function checkCnt() {
		let checkboxes = document.querySelectorAll('.car');
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
		console.dir('오나용?');
		let allcar = document.querySelector('.allcar');
        let checkboxes = document.querySelectorAll('.car');
        
        if(allcar.checked == true){
           checkboxes.forEach((e)=>{
               e.checked = true;
            })
        } else if(allcar.checked == false){
           checkboxes.forEach((e)=>{
               e.checked = false;
            })
        }
	});
	
	/* 삭제버튼 누를시 체크개수 카운트해 모달창에 건수 넣어주기.*/
	document.querySelector('.carDelete').addEventListener('click',()=>{
		let checkboxes = checkCnt();
		console.dir(checkboxes.length);
   	 	document.getElementById('result').innerText = checkboxes.length;
	});
	
	/* 삭제 비동기통신 */
	/* 모달창에서 승인 삭제버튼 누를시 비동기로 삭제요청 보낸다.*/
	document.querySelector('.btn-delete-car').addEventListener('click',()=>{
		let carIdx = [];
		let checkboxes = checkCnt();
		checkboxes.forEach((e)=>{
  			let trElement = e.parentElement.parentElement.parentElement.parentElement;
			let tdElement = trElement.children;
			carIdx.push(tdElement[1].outerText);
		})
		
		let headerObj = new Headers();
		headerObj.append('content-type', "application/x-www-form-urlencoded");
		
		fetch("/admin/cardelete?caridx="+carIdx,{
			method:"GET",
			header : headerObj
		}).then(response => {
			if(response.ok){
				alert('삭제에 성공했습니다.');
				return location.href="/admin/car";
			}
			throw new AsyncPageError(response.text()); 
		})
		.catch(error =>{
			error.alertMessage();
		})
	});
	
	/* 수정모달청에서 수정하기*/
	document.querySelector('.carModify').addEventListener('click',()=>{
		let carIdx = document.querySelector('#modify-car-idx');
		let carInfo = document.querySelector('#modify-car-info');
		let arr = checkCnt();
		if(arr.length > 1) {
			alert('하나만 선택하세요.');
			location.href= '/admin/car';
		} else {
  			let parentElement = arr[0].parentElement.parentElement.parentElement.parentElement;
			let tdElement = parentElement.children;
			console.dir(tdElement[1].outerText);
			console.dir(tdElement[2].outerText);
			carIdx.value = tdElement[1].outerText;
			carInfo.value = tdElement[2].outerText;
		
	};
		
		
	});

	
})();  