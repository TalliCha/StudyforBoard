<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>

<script src="/resources/js/util.js"></script>
<script src="/resources/js/list.js"></script>

<style>
.active {
 	font-weight: bolder;
 	font-size: large;
} 
.novisual{
	visibility: hidden;
}
#maxContent{
	float: right;
}

.box-body{
	width: 100%;
	margin: 0 auto;
}

#boardList th{
	display:  inline-block;  
	text-align: center;
}

#boardList td{
 	display:  inline-block;  
	text-align: center;
	
	white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    position: relative;
    float: left;
    
    height: 40px;

}
#boardList th:NTH-CHILD(1) {
	width: 10%;
}
#boardList th:NTH-CHILD(2) {
	width: 50%;
}
#boardList th:NTH-CHILD(3) {
	width: 10%;
}
#boardList th:NTH-CHILD(4) {
	width: 20%;
}
#boardList th:NTH-CHILD(5) {
	width: 10%;
}

#boardList td:NTH-CHILD(1) {
	width: 10%;
}
#boardList  td:NTH-CHILD(2) {
	text-align: left;
	width: 50%;
}
#boardList  td:NTH-CHILD(3) {
	width: 10%;
}
#boardList  td:NTH-CHILD(4) {
	width: 20%;
}
#boardList  td:NTH-CHILD(5) {
	width: 10%;
}

.category{
 	display:  inline;
 	width: 15%;
}

#category{
 	width: 100%;
}

.search{
   display:  inline; 
	width: 84%; 
}

#search{
 	width: 100%;
}

.titleOverLength{
	display:  inline-block;  
	width: 90%;
	white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;

}

</style>

<script>
	$(document).ready(function() {
		
		// 필수 변수
		var data = {
				pno : $('#pno')
				,keyWord : $('#keyWord')
				,category : $("#category option:selected")
				,naviSize : $('#naviSize')
				,pageSize : $('#pageSize')
				,maxPage : $('#maxPage')
				
				,sendMsg : $('#sendMsg')
		}
// 		alert(JSON.stringify(data)); // data 값 확인
		
		init(data); // list.js : 초기화
		searchListener(data); // list.js: 연결 - 검색 처리후 페이지 로딩
		categoryListener(data); // list.js: 연결 - category 선택시 페이지 로딩
		
		$('#excel_down').click(function() {
			var loc = 	"/excelDownload?"+"pno="+data.pno.val()+"&keyWord="+data.keyWord.val()+"&category="+data.category.val()+"&maxPage="+data.maxPage.val();
// 			alert(loc);
			location.href= loc ; 

		});
		
	});
	
</script>

<!-- Main content -->
<section class="content">
	<div class="row">	
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<form role="form">
				<input type='hidden' id="pno" name='pno' value="${conVO.pno}">
				<input type='hidden' id="keyWord" name='keyWord' value="${conVO.keyWord}">
				<input type='hidden'  id="pageSize" name='pageSize' value="${conVO.pageSize}">
				<input type='hidden' id="naviSize" name='naviSize' value="${conVO.naviSize}">
				<input type='hidden' id="maxPage" name='maxPage' value="${conVO.maxPage}">
				
				<input type='hidden' id='sendMsg' name='sendMsg' value="${sendMsg}">
				<div class="box">
					<div class="box-header with-border">
				 		<div class="btn-group">
			                  <button type="button" class="btn btn-default" disabled="disabled" >ListSize</button>
			                  <button type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false">
			                    <span class="caret"></span>
			                    <span class="sr-only">Toggle Dropdown</span>
	               			  </button>
			                  <ul class="dropdown-menu" role="menu">
			                    <li><a class="pageSize">10</a></li>
			                    <li><a class="pageSize">15</a></li>
			                    <li><a class="pageSize">20</a></li>
		                	  </ul>
		                </div>
		                <br>
						<h1>계층형 게시판<small>-글과 답변을 다세요-</small></h1>  <span id="maxContent">전체 글수 0:개</span>
						<a id="excel_down" href="#" >엑셀 다운로드</a>
					</div>
					<div class="box-body">
						<div class="text-center">
							<div class="form-inline">
								 <div class="category form-group">
									<select  id="category" name="category" class="form-control ">
										<option value="title" selected="selected"> 제목
										<option value="content"> 내용
										<option value="writer"> 작성자
				              	    </select>
			              	    </div>
			              	    <div class="search form-group">
									<input id="search" type="search" value="" class="form-control" placeholder="Search..." >
								</div>
							</div>
						</div>
							<table id="boardList" class="table table-bordered">
							</table>
	
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<div class="text-center">
							<ul class="pagination">
	
							</ul>
							
						</div>
						<div class="text-center">
							<button type="button" class="btn btn-primary">Write</button>
						</div>
					</div>
					<!-- /.box-footer-->
				</div>
			</form>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
