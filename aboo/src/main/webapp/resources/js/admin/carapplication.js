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
	document.querySelector('.rejectCnt').addEventListener('click',()=>{
		let checkboxes = checkCnt();
		console.dir(checkboxes.length);
   	 	document.getElementById('result').innerText = checkboxes.length;
	});
	
	document.querySelector('.approvalCnt').addEventListener('click',()=>{
		let checkboxes = checkCnt();
		console.dir(checkboxes.length);
   	 	document.getElementById('result-cnt').innerText = checkboxes.length;
	});
	

	/* 모달창에서 차량등록 승인버튼 누를시 비동기로 승인요청 보낸다.*/
	document.querySelector('.btn-approval-car').addEventListener('click',()=>{
		let applicationIdx = [];
		let checkboxes = checkCnt();
		checkboxes.forEach((e)=>{
  			let trElement = e.parentElement.parentElement.parentElement.parentElement;
			let tdElement = trElement.children;
			applicationIdx.push(tdElement[1].outerText);
		})
		
		let headerObj = new Headers();
		headerObj.append('content-type', "application/x-www-form-urlencoded");
		
		fetch("/admin/carapplcation/approval?applicationidx="+applicationIdx,{
			method:"GET",
			header : headerObj
		}).then(response => {
			if(response.ok){
				alert('등록 신청을 승인했습니다.');
				return location.href="/admin/car/application";
			}
		})
		.catch(error =>{
			error.alertMessage();
		})
	});
	
	/* 차량등록 삭제 비동기통신 */
	/* 모달창에서 승인 삭제버튼 누를시 비동기로 삭제요청 보낸다.*/
	/* 모달창에서 차량등록 승인버튼 누를시 비동기로 승인요청 보낸다.*/
	document.querySelector('.btn-reject-car').addEventListener('click',()=>{
		let applicationIdx = [];
		let checkboxes = checkCnt();
		checkboxes.forEach((e)=>{
  			let trElement = e.parentElement.parentElement.parentElement.parentElement;
			let tdElement = trElement.children;
			applicationIdx.push(tdElement[1].outerText);
		})
		
		let headerObj = new Headers();
		headerObj.append('content-type', "application/x-www-form-urlencoded");
		
		fetch("/admin/carapplcation/reject?applicationidx="+applicationIdx,{
			method:"GET",
			header : headerObj
		}).then(response => {
			if(response.ok){
				alert('등록 신청을 반려했습니다.');
				return location.href="/admin/car/application";
			}
		})
		.catch(error =>{
			error.alertMessage();
		})
	});

	
})();  