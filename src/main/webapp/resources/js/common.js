/**
 *  cancel 버튼 클릭시 메인 페이지로 이동
 */
function com_btn_cancle_action(data) {
		var pno = "pno="+data.pno.val();
		var keyWord = "keyWord="+data.keyWord.val();
		var pageSize = "pageSize="+data.pageSize.val();
		var naviSize = "naviSize="+data.naviSize.val();
		
		self.location = "/board?"+pno+"&"+keyWord + "&"+pageSize+"&"+naviSize;
	}


function addUploadFile(data) {
	var upload_fname = "";
	
	if(data.upload_fname.val() == ''){
		upload_fname += "첨부파일 없음";
	}else{
		upload_fname = "<a  href='/downloadFile?upload_fname="+data.upload_fname.val()+
									"&original_fname="+data.original_fname.val()+"'>"+
										data.original_fname.val()+
								"</a>";
	}
	$('#upload_name').append(upload_fname);
}

function add_upload_Validation( ) {
	$(".uploadFile").change(function() {
//		alert(1);
		var file_size = Math.ceil( this.files[0].size / 1024 / 1024 * 10 )/10;
//		alert(file_size);
		if (file_size > 10) {
			alert ('10메가가 넘는 파일은 첨부 할수 없습니다.');
			
			$(this).val('');
		}
	});
}