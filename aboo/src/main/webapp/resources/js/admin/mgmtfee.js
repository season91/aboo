(()=>{

	/* 체크된 박스 개수 */
	function checkCnt() {
		let checkboxes = document.querySelectorAll('.mgmtfee');
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
		let allvehicle = document.querySelector('.allmgmtfee');
        let checkboxes = document.querySelectorAll('.mgmtfee');
        console.dir("여기오낭");
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
	document.querySelector('.mgmtfeeDelete').addEventListener('click',()=>{
		let checkboxes = checkCnt();
		console.dir(checkboxes.length);
   	 	document.getElementById('result').innerText = checkboxes.length;
	});
    

	/* 모달창에서 삭제버튼 누를시 비동기로 삭제요청 보낸다.*/
	document.querySelector('.btn-delete-mgmtfee').addEventListener('click',(e)=>{
		let mgmtfeeIdx = [];
		let checkboxes = checkCnt();
		checkboxes.forEach((e)=>{
  			let trElement = e.parentElement.parentElement.parentElement.parentElement;
			let tdElement = trElement.children;
			mgmtfeeIdx.push(tdElement[1].outerText);
		})
		
		let headerObj = new Headers();
		headerObj.append('content-type', "application/x-www-form-urlencoded");
		
		fetch("/admin/mgmtfeedeletelist?mgmtfeeidx="+mgmtfeeIdx,{
			method:"GET",
			header : headerObj
		}).then(response => {
			if(response.ok){
				alert('삭제에 성공했습니다.');
				return location.href="/admin/mgmtfee";
			}
		})
		.catch(error =>{
			error.alertMessage();
		})
	});
	
})();