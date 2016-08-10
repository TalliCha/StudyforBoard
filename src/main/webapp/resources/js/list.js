
	/* 초기화 */
	function init(data) {
		
		// keyWord 값 예외 처리 '.' = ' '  처리
		if(data.keyWord.val() != '.'){
			$('#search').val( data.keyWord.val() );
		}
		
		addDeleteMSG(data); // util.js :  에러 존재시 경고
		
		getMaxPage(data); // max 값 가져오기
		
		// current_pno > max_pno 일떄 
		makeNavi(data);
		
		getList(data); // 최초 페이지 로딩
		pageMoveListener(); // 페이지 이동 A 태그에 이벤트 설정
		changeURL(data); // URL 변경
		
		add_listener(data); // 이벤트 연결
		
	}
	
	/* 이벤트 연결 */
	function add_listener(data) {
		
		pageListener(data); // 페이지 클릭 처리
		
		listSizeListener(data); // 페이지갯수 선택
		
		btn_controller(data); // 버튼 컨트롤러
		
	}	
	
	/* ajax로 값 가져오기 */

	/*글 리스트 가져오기*/
	function getList(data) {
		$.ajax({
			async: false
			,method : 'post'
			,url : "/board/getList"
			,data : {
				'pno' :  data.pno.val()
				,'pageSize' : data.pageSize.val()
				,'naviSize' : data.naviSize.val()
				,'keyWord' : data.keyWord.val()
				,'category' : data.category.val()
				,'maxPage' : data.maxPage.val()
			}
			,dataType : 'json'
			,success : function(getData) {
				
				addList(getData);
				
//				var getList = JSON.stringify(getData);
//				alert(getList);
				
			}
		});
	}/*end:function  */
	
	/*전체 글수 가져오기*/
	function getMaxPage(data) {
		$.ajax({
			async: false
			,method : 'post'
			,url : "/board/getMaxPage"
			,data : {
				'keyWord' : data.keyWord.val()
				,'category' : data.category.val()
			}
			,dataType : 'json'
			,success : function(getData) {
				data.maxPage.val( getData.maxPage ) ;
				
				$('#maxContent').html("전체 글수 "+data.maxPage.val()+":개");
				
			}
		});
	}/*end:function  */	


	/*이벤트 연결 하기*/
	
	/*버튼 클릭 페이지 이동 동작*/
	function btn_controller(data) {
		var formObj = $("form[role='form']");

		console.log(formObj);
		
		$(".btn-primary").click(function() {
			formObj.attr("method", "post");
			formObj.attr("action", "/board/write"); // write 페이지로 이동
			formObj.submit();
		});
	}
	
	/*페이지 클릭 동장*/
	function pageListener(data) {
		$('.navi').click(function() {
			data.pno.val( $(this).attr('id') ) ;
			
			init(data);
		
			return false;
		});
	}
	
	/*보여줄 페이지 사이즈 조절 동장*/
	function listSizeListener(data) {
		$('.pageSize').click(function() {
				data.pno.val( 1 );
				data.pageSize.val( $(this).html() );	
				init(data);
		});
	}
	
	/*카테고리 동작*/
	function categoryListener(data) {
		$('#category').change(function() {
			data.keyWord.val( $('#search').val() );
			data.category= $(this).children("option:selected");
			
			data.pno.val( 1 );
			init(data);
		});
	}
	
	/*검색 동작*/
	function searchListener(data) {
		$('#search').keyup(function() {
			data.keyWord.val( $('#search').val() );
			data.category= $("#category").children("option:selected");
			data.pno.val( 1 );
			init(data);
			
		});
	}
	
	function pageMoveListener() {
		$(".pageMove").click(function() {
			var formObj = $("form[role='form']");
			formObj.attr("method", "post");
			formObj.attr("action", $(this).attr("href") ); // write 페이지로 이동
			formObj.submit();
			return false;
		});
	}
	
	/* view 만들기 */

	/*게시글 리스트 만들기*/
	function addList(list) {
		
		$('#boardList').empty();
		
		var th = "<tr>" + "<th>글번호</th>" + "<th>제목</th>"
				+ "<th>작성자</th>" + "<th>작성일</th>"
				+ "<th>조회수</th>" + "</tr>";

		$('#boardList').append(th);

		for ( var idx in list) {

			var linkNm;
			var titleTab="";
			
			var upload_file = "";
			
			for(var i = 0 ; i < list[idx].reply_lv ; i++){
				if(i == list[idx].reply_lv - 1 ){
					titleTab += "<span class='glyphicon glyphicon-share-alt'></span>"; // 띄워쓰기 넣기
				}else{
					titleTab += "<span class='glyphicon glyphicon-share-alt novisual'></span>"; // 띄워쓰기 넣기
				}
			}
			
//			alert( list[idx].parent_bno );
			
//			 삭제 표시 정책 변경에 따른 주석 처리 : 자식글에 원글이 삭제 되었습니다 표시.
			if(list[idx].parent_bno == 0){
				titleTab += "<span class='text-red' style='font-size: x-small;'> --원글이 삭제된 답변입니다-- </span><br>";
			}
			
			if(list[idx].upload_file > 0){
				upload_file += "<span class='glyphicon glyphicon-floppy-disk'></span>x"+list[idx].upload_file;
			}
			 
			if(list[idx].count_cno==0){
				linkNm = "<a class='pageMove' href='/board/page?bno=" + list[idx].bno + "'>"
				+ titleTab+"<span class='titleOverLength'>"+list[idx].title.replace(/</gi,'&#x003C;')+"</span>"+upload_file+ "</a>";
				
			}else{
				linkNm = "<a class='pageMove' href='/board/page?bno=" + list[idx].bno + "'>"
				+ titleTab+"<span class='titleOverLength'> "+list[idx].title.replace(/</gi,'&#x003C;') +"</span>"+"<span class='label label-warning pull-right'>"+ list[idx].count_cno + "</span>" +upload_file+ "</a>";
				
			}
			
			
			var row = "<tr>" + "<td>" + list[idx].rno + "</td>" + "<td title='"+list[idx].title+"'>"	
					+ linkNm + "</td>" + "<td title='"+list[idx].writer+"'>" + list[idx].writer.replace(/</gi,'&#x003C;') + "</td>"
					+ "<td>" + list[idx].regdate + "</td>"
					+ "<td><span class='badge bg-red'>" + list[idx].viewcnt
					+ "</span></td>" + "</tr>";

			$('#boardList').append(row);
		}/*end:for  */
	}/*end:function  */

	/*페이징 만들기*/
	function makeNavi(data) {
		$('.pagination').empty();
		
		var row = "";
		
		var end_pno = Math.ceil(data.maxPage.val() / data.pageSize.val());	

		if( data.pno.val() > end_pno ){
			data.pno.val( end_pno );
			changeURL(data);
		}else if( data.pno.val() <= 0 ){
			data.pno.val( 1 );
			changeURL(data);
		}
		
		var navi_start = Number(data.pno.val()) - ( (Number(data.pno.val())-1 ) % Number(data.naviSize.val()) );
		
		var prev = navi_start - Number(data.naviSize.val());
		var prev50 = navi_start - 50;
		var next = navi_start + Number(data.naviSize.val());
		var next50 = navi_start + 50;
		
		
//		alert( 'maxPage:'+ data.maxPage.val() );
//		alert(  'pno:'+data.pno.val() );
//		alert(  'naviSize:'+data.naviSize.val() );
//		alert(  'navi_start:'+ navi_start );
//		alert( 'end_pno:' + end_pno);
//		
//		alert(  'prev:'+ prev );
//		alert(  'next:'+ next );
		
		
		if (prev > 0) {
			row = row + "<li><a id='1' class='navi'>1...</a></li>";
		}
		if (prev50 > 0) {
			row = row + "<li><a id='"+prev50+"' class='navi'><span class='glyphicon glyphicon-backward' aria-hidden='true'></span></a></li>";
		}
		if (prev > 0) {
			row = row + "<li><a id='"+prev+"' class='navi'><span class='glyphicon glyphicon-chevron-left' aria-hidden='true'></span></a></li>";
		}

		for (var i = 0; i < data.naviSize.val(); i++) {
			if (navi_start > end_pno) {
				break;
			}
			row = row + "<li><a id='"+ navi_start +"' class='navi'>" + navi_start + "</a></li>";
			navi_start++;
			
		}/* end:for  */
		
		if (next <= end_pno) {
			row = row + "<li><a  id='"+next+"' class='navi'><span class='glyphicon glyphicon-chevron-right' aria-hidden='true'></span></a></li>";
		} /* end:if  */
		
		if (next50 <= end_pno) {
			row = row + "<li><a  id='"+next50+"' class='navi'><span class='glyphicon glyphicon-forward' aria-hidden='true'></span></a></li>";
		} /* end:if  */
		
		if (next <= end_pno) {
			row = row + "<li><a  id='"+end_pno+"' class='navi'>..."+end_pno+"</a></li>";
		} /* end:if  */

		$('.pagination').append(row);
		
		pageSet(data);
	
		
	}/* end: function  */
	
	
	
	/* 추 가 기능 */	
	
	/*페이지 URL 변경*/
	function changeURL(data) {
		var url = "board?pno="+data.pno.val()+"&keyWord="+ $('#search').val();
		history.pushState("", "BoardList", url);
	}
	
	/*페이지 선택*/
	function pageSet(data) {
		$('.navi').removeClass('active');
		$('#' + data.pno.val()).addClass('active');
	}/*end:function  */