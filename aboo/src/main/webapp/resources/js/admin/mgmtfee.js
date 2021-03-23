(()=>{
	document.querySelector('.btn-search-vehicleNumber').addEventListener('click',()=>{
		let keyword = document.querySelector('.mgmtfee-keyword');
		console.dir('관리비번호 검색');
		console.dir(keyword.value);
	});
})();