
	/*초기화*/
	function init(data) {
		
		$('#title').attr("maxlength",data.titleMax);
		$('#content').attr("maxlength",data.contentMax);
		$('#writer').attr("maxlength",data.writerMax);
		
		addErrMSG(data); // util.js :  에러 존재시 경고
		
		add_listener(data); // 이벤트 연결
		
	}

	/*이벤트 연결*/
	function add_listener(data) {
		
		validation_chk(data); // 글자 수 제한 체크
		
		btn_controller(data); // 버튼 컨트롤러
	
	}
	
	/* 코드 줄일수 있음  하지만 나중에...*/
	function validation_chk(data) {
		
		$('#title').keyup(function() {
			chkword( $(this) , data.titleMax );
		});
		$('#content').keyup(function() {
			chkword( $(this) , data.contentMax );
		});
		$('#writer').keyup(function() {
			chkword( $(this) , data.writerMax );
		});
		
		add_upload_Validation(); // common.js : 파일 첨부 용량 체크
		
	}
	
	/*버튼 클릭 페이지 이동 동작*/ 
	function btn_controller(data) {
		var formObj = $("form[role='form']");

		console.log(formObj);
		
		$(".btn-warning").click( function() {
			com_btn_cancle_action(data); // common.js : CANCEL 버튼 처리
		});
	
	}