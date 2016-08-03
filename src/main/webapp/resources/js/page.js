function init(data) {
	
//	addUploadFile(data); // common.js : 파일 첨부 보기
	
	addDeleteMSG(data); // util.js : 삭제 메세지 경고창
	
	comm_make(data);
	
	btn_controller(data); // 기능 버튼 연결
	
	$('.content_box').attr("maxlength",data.contentMax);
	$('#writer').attr("maxlength",data.writerMax);
	
	/*  댓글 작성 하기 */
	$("#comm_write").click(function() {
		data.cno= 0;
		comm_create(data);
		comm_make(data); // 댓글 다시 그리기
		
		$("#content").focus();
		
	});
	
	listSizeListener(data);
	
}

/* 코드 줄일수 있음  하지만 나중에...*/
function validation_chk(data) {
	
	$('.content_box').keyup(function() {
		textareaChange();
		chkword( $(this) , data.contentMax );
	});
	$('.writer_box').keyup(function() {
		chkword( $(this) , data.writerMax );
	});
	
}

function comm_make(data) {
	
	comm_MaxPage(data); // max 값 가져오기
	
	// current_pno > max_pno 일떄 
	makeNavi(data);
	
	getComm_List(data); // 최초 페이지 로딩 // 뎃글 가져오기
	
	pageListener(data); // 페이징 이동 처리
	
	add_listener(data); // 이벤트 연결
	
	textareaChange(); // 글 입력창 사이즈 조절
	
}
function textareaChange() {
	$( ".content_box" ).each(function() {
		this.style.height = "1px";
		var changeHeight=(20+this.scrollHeight);
	    this.style.height = changeHeight+"px";
	    var split =(this.id).split("content");
	    var target = "tchangeLength"+split[0];
//	    alert(target);	
//	    alert(changeHeight);
//	    $("."+target).attr( 'height', ((changeHeight + 20) +"px") );
	});
}
function add_listener(data) {
	
	/*  댓글에 답변 달기 */
	$(".comm_reply").click(function() {
		data.cno = $(this).attr('id');
		
//		alert(data.cno);
		comm_reply(data);
		comm_make(data); // 댓글 다시 그리기
		$("#"+data.cno).focus();
	});
	
	/*  댓글 수정 하기 */
	
	$(".comm_modify").click(function() {
		 	data.cno = $(this).text();
			
			comm_make(data); // 댓글 다시 그리기
			comm_modify(data);
	});
	
	/*  댓글에 삭제 하기 */
	
	$(".comm_delete").click(function() {
		 if (confirm("덧글을 삭제 하시겠습니까?") == true) {
				data.cno= $(this).text();
			 	
				comm_delete(data);
				comm_make(data); // 댓글 다시 그리기
		 }
	});
	
	$(".modify_submit").click(function() {
		comm_update(data);
		comm_make(data); // 댓글 다시 그리기
});
	
	$(".modify_cancel").click(function() {
			data.cno= 0;
			comm_make(data); // 댓글 다시 그리기
	});
	
	validation_chk(data);
}

/*댓글 입력하기*/
function comm_create(data) {
	$.ajax({
		async: false
		,method : 'post'
		,url : "/board/comm_create"
		,data : {
			'bno' : data.bno.val()
			,'content' :  data.content.val()
			,'writer' : data.writer.val()
		}
		,success : function(getData) {
			
			if(!getData){
				alert("입력 실패");
			}else{
				data.content.val('');
				data.writer.val('');
			}
//			var getList = JSON.stringify(getData);
//			alert(getList);
		}
	});
}/*end:function  */

/*댓글 수정하기*/
function comm_update(data) {
	
	var content =$("#"+data.cno).val();
	
	$.ajax({
		async: false
		,method : 'post'
		,url : "/board/comm_update"
		,data : {
			'cno' :data.cno
			,'content' :  content
			,'writer' : 'dummy_data'
		}
		,success : function(getData) {
			
			if(!getData){
				alert("입력 실패");
			}else{
				data.content.val('');
				data.writer.val('');
			}
//			var getList = JSON.stringify(getData);
//			alert(getList);
		}
	});
	data.cno = 0;
}/*end:function  */

/*답변 리스트 만들기*/
function comm_reply(data) {
	$.ajax({
		async: false
		,method : 'post'
		,url : "/board/comm_replycreate"
		,data : {
			'cno' : data.cno
			,'bno' : data.bno.val()
			,'content' :  $("#content"+data.cno).val()
			,'writer' : $("#writer"+data.cno).val()
		}
		,success : function(getData) {
			if(!getData){
				alert("입력 실패");
			}else{
				$("#content"+data.cno).val('');
				$("#writer"+data.cno).val('');
			}
//			var getList = JSON.stringify(getData);
//			alert(getList);
		}
		,error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	});
	
	data.cno = 0; // 작업 종료후 cno 값 초기화
}/*end:function  */

function comm_modify(data) {
	$("#"+data.cno).removeAttr("readonly") ;
	$("#"+data.cno).focus();
}/*end:function  */

/*답변 삭제 하기*/
function comm_delete(data) {
	$.ajax({
		async: false
		,method : 'post'
		,url : "/board/comm_delete"
		,data : {
			'cno' : data.cno
		}
		,success : function(getData) {
			if(!getData){
				alert("삭제 실패");
			}else{
				alert('삭제 성공');
			}
		}
		,error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	});
}/*end:function  */

/*글 리스트 가져오기*/
function getComm_List(data) {
	$.ajax({
		async: false
		,method : 'post'
		,url : "/board/getCommentList"
		,data : {
			'comm_pno' :  data.comm_pno.val()
			,'bno' :  data.bno.val()
			,'comm_pageSize' : data.comm_pageSize.val()
			,'comm_naviSize' : data.comm_naviSize.val()
			,'comm_maxPage' : data.comm_maxPage.val()
		}
		,dataType : 'json'
		,success : function(getData) {
			
//			var getList = JSON.stringify(getData);
//			alert(getList);
			addList( data, getData );
			
		}
	});
}/*end:function  */

/*전체 글수 가져오기*/
function comm_MaxPage(data) {
	$.ajax({
		async: false
		,method : 'post'
		,url : "/board/comm_MaxPage"
		,data : {
			'bno' : data.bno.val()
		}
		,dataType : 'json'
		,success : function(getData) {
			data.comm_maxPage.val( getData.maxPage ) ;
			
			$('#maxContent').html("전체 댓글수 "+data.comm_maxPage.val()+":개");
			
		}
	});
}/*end:function  */	

function addList(data,list) {
		
	$('#comm_list').empty();

	var th = "<tr>" +"<th >댓글 번호</th>" + "<th >내용</th>" + "<th>작성자</th>"
			+ "<th>작성일</th>" + "</tr>";

	$('#comm_list').append(th);

	for ( var idx in list) {

		var content;
		var contentTab="";
		var add_btns="";
		
		var sumit_btn="<span class='modify_submit positionLeft glyphicon glyphicon-ok-sign'>"+"<label class=' hiddenText'>"+data.cno+"</label>"+"</span>";
		var cancel_btn="<span class='modify_cancel positionLeft glyphicon glyphicon-remove-sign'>"+"<label class=' hiddenText'>"+data.cno+"</label>"+"</span>";
		
		var reply_btn = "<span data-toggle='collapse' data-target='#relpy_"+list[idx].cno+"' class='positionRight glyphicon glyphicon-plus-sign'>"+"<label class=' hiddenText'>"+ list[idx].cno+"</label>"+"</span>";
		var delete_btn="<span class='comm_delete positionRight glyphicon glyphicon-minus-sign' >"+"<label class=' hiddenText'>"+ list[idx].cno+"</label>"+"</span>";
		var modify_btn="<span class='comm_modify positionRight glyphicon glyphicon-pencil'>"+"<label class=' hiddenText'>"+ list[idx].cno+"</label>"+"</span>";
		
		
		for(var i = 0 ; i < list[idx].reply_lv ; i++){
			if(i == list[idx].reply_lv - 1 ){
				contentTab += "<span class='glyphicon glyphicon-share-alt'></span>"; // 띄워쓰기 넣기
			}else{
				contentTab += "<span class='glyphicon glyphicon-share-alt novisual'></span>"; // 띄워쓰기 넣기
			}
		}
		
		if(list[idx].parent_cno == 0){
			contentTab += "<span class='text-red' style='font-size: x-small;'> --원글이 삭제된 답변입니다-- </span>";
		}
		
		content = contentTab+" <br> "+"<textarea id='"+list[idx].cno+"' class=' content_box no_border' type='text' readonly='readonly' style='resize:none; width:70%; overflow:visible; '>"+list[idx].content+"</textarea>" ;
		
		if(data.cno == list[idx].cno ){
			add_btns += sumit_btn +cancel_btn + delete_btn +modify_btn  + reply_btn;
		}else{
			add_btns += delete_btn +modify_btn  + reply_btn;
		}
		
		var  reply_box ="<div id='relpy_"+list[idx].cno+"' class='collapse'>"
									+"<form>"
									+"<div>"
										+"<div class='form-group'>"
											+"<label for='exampleInputEmail1'>글내용</label> <br>"
											+"<textarea  id='content"+list[idx].cno+"' class='content_box' name='content' placeholder='내용' required='required' style='resize:none; width:70%;  overflow: visible;'></textarea>"
											+"<span id='content"+list[idx].cno+"Length'></span>"
										+"</div>"
									+"</div>"
									+"<div>"
										+"<div class='form-group'>"
											+"<label for='exampleInputEmail1'>작성자</label> <br>"
											+"<input type='text' id='writer"+list[idx].cno+"' class='writer_box' name='writer' placeholder='이름' required='required'>"
											+"<span id='writer"+list[idx].cno+"Length'></span>"
										+"</div>"
									+"</div>"
									+"<button id="+list[idx].cno+" class='comm_reply' type='button' class='btn'>작성</button>"
								+"</form>"
								+"</div>";
		
		var row = "<tr>"+ "<td class='tchangeLength"+list[idx].cno+"' >"+ list[idx].rno + "<td class='tchangeLength"+list[idx].cno+"'>"	+ content  + add_btns + reply_box+ "</td>" + "<td class='tchangeLength"+list[idx].cno+"'>" + list[idx].writer + "</td>"
				+ "<td class='tchangeLength"+list[idx].cno+"'>" + list[idx].regdate + "</td>"	+ "</tr>";
				
		$('#comm_list').append(row);
	}/*end:for  */
}/*end:function  */

function btn_controller(data) {
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$(".btn-warning").click( function() {
		formObj.attr("method", "post");
		formObj.attr("action", "/board/modify");
		formObj.submit();
	});
	
	$(".btn-success").click( function() {
		formObj.attr("method", "post");
		formObj.attr("action", "/board/reply");
		formObj.submit();
	});
	
	$(".btn-danger").click( function() {
		 if (confirm("삭제 하시겠습니까?") == true) {
				formObj.attr("method", "post");
				formObj.attr("action", "/board/delete");
				formObj.submit();
	        } 
	});

	$(".btn-primary").click(function() {
		com_btn_cancle_action(data); // common.js : GO LIST 버튼 처리
	});
}

/*페이징 만들기*/
function makeNavi(data) {
	$('.pagination').empty();
	
	var row = "";
	
	var comm_end_pno = Math.ceil( Number( data.comm_maxPage.val() ) / Number( data.comm_pageSize.val() ));	

	if( data.comm_pno.val() > comm_end_pno ){
		data.comm_pno.val( comm_end_pno );
	}else if( data.comm_pno.val() <= 0 ){
		data.comm_pno.val( 1 );
	}
	
	var comm_navi_start = Number( data.comm_pno.val() ) - ( (data.comm_pno.val()-1 ) % Number( data.comm_naviSize.val() ) );
	var prev = comm_navi_start - Number(data.comm_naviSize.val());
	var prev50 = comm_navi_start - 50;
	var next = comm_navi_start + Number(data.comm_naviSize.val());
	var next50 = comm_navi_start + 50;


	if (prev > 0) {
		row = row + "<li><a id='1' class='navi'>1...</a></li>";
	}
	if (prev50 > 0) {
		row = row + "<li><a id='"+prev50+"' class='navi'><span class='glyphicon glyphicon-backward' aria-hidden='true'></span></a></li>";
	}
	if (prev > 0) {
		row = row + "<li><a id='"+prev+"' class='navi'><span class='glyphicon glyphicon-chevron-left' aria-hidden='true'></span></a></li>";
	}

	for (var i = 0; i < data.comm_naviSize.val(); i++) {
		if (comm_navi_start > comm_end_pno) {
			break;
		}
		row = row + "<li><a id='"+ comm_navi_start +"' class='navi'>" + comm_navi_start + "</a></li>";
		comm_navi_start++;
		
	}/* end:for  */
	
	if (next <= comm_end_pno) {
		row = row + "<li><a  id='"+next+"' class='navi'><span class='glyphicon glyphicon-chevron-right' aria-hidden='true'></span></a></li>";
	} /* end:if  */
	
	if (next50 <= comm_end_pno) {
		row = row + "<li><a  id='"+next50+"' class='navi'><span class='glyphicon glyphicon-forward' aria-hidden='true'></span></a></li>";
	} /* end:if  */
	
	if (next <= comm_end_pno) {
		row = row + "<li><a  id='"+comm_end_pno+"' class='navi'>..."+comm_end_pno+"</a></li>";
	} /* end:if  */

	$('.pagination').append(row);
	
	pageSet(data);

	
}/* end: function  */

/*페이지 클릭 동장*/
function pageListener(data) {
	$('.navi').click(function() {
		data.comm_pno.val( $(this).attr('id') ) ;
		
		comm_make(data);
	
		return false;
	});
}

/* 추 가 기능 */

/*보여줄 페이지 사이즈 조절 동장*/
function listSizeListener(data) {
	$('.pageSize').click(function() {
		data.comm_pno.val( 1 );
		data.comm_pageSize.val( $(this).html() );	
		comm_make(data);
	});
}

/*페이지 선택*/
function pageSet(data) {
	$('.navi').removeClass('active');
	$('#' + data.comm_pno.val()).addClass('active');
}/*end:function  */