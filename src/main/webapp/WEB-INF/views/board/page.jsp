<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../include/header.jsp"%>

<script src="/resources/js/util.js"></script>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/page.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var data = {
			bno : $('#bno')
			,cno : 0

			,pno : $('#pno')
			,maxPage : $('#maxPage')
			,pageSize : $('#pageSize')
			,naviSize : $('#naviSize')
			
			,comm_pno : $('#comm_pno')
			,comm_maxPage : $('#comm_maxPage')
			,comm_pageSize : $('#comm_pageSize')
			,comm_naviSize : $('#comm_naviSize')

			,keyWord : $('#keyWord')
			
			,original_fname :  $('#original_fname')
			,upload_fname :   $('#upload_fname')
			
			,sendMsg : $('#sendMsg')
			
			,content : $('#content') 
			,writer : $('#writer')
			
			
			,contentMax : 400
			,writerMax : 80
	}
// 			alert(JSON.stringify(data)); // data 값 확인	
	
	init(data);  // page.js : 초기화 
	
	
});

</script>

<style>
.active {
 	font-weight: bolder;
 	font-size: large;
} 
.novisual{
	visibility: hidden;
}

.positionRight{
	float: right;
}

.positionLeft{
	float: left;
}

.hiddenText{
	display: none;
}

#maxContent{
	float: right;
}
.no_border{
	border: none;
}

.box-footer{
	width: 100%;
	margin: 0 auto;
}

#comm_list th{
	display:  inline-block;  
	text-align: center;
}

#comm_list td{
 	display:  inline-block;  
	text-align: center;
	
	white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    position: relative;
    float: left;
    
    

}

#comm_list th:NTH-CHILD(1) {
	width: 10%;
}
#comm_list th:NTH-CHILD(2) {
	width: 60%;
}
#comm_list th:NTH-CHILD(3) {
	width: 10%;
}
#comm_list th:NTH-CHILD(4) {
	width: 20%;
}

#comm_list td:NTH-CHILD(1) {
	width: 10%;
}
#comm_list  td:NTH-CHILD(2) {
	text-align: left;
	width: 60%;
}
#comm_list  td:NTH-CHILD(3) {
	width: 10%;
}
#comm_list  td:NTH-CHILD(4) {
	width: 20%;
}
</style>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h1>${boardVO.bno}번 글 보기<small>-내용을 보세요-</small></h1>
				</div>
				<!-- /.box-header -->
				<form role="form" method="post">
					
					<!-- conVO list parameters  -->
					<input type='hidden' id="keyWord" name='keyWord' value="${conVO.keyWord}">
					<input type='hidden' id="pno" name='pno' value="${conVO.pno}">
					<input type='hidden'  id="pageSize" name='pageSize' value="${conVO.pageSize}">
					<input type='hidden' id="naviSize" name='naviSize' value="${conVO.naviSize}">
					<input type='hidden' id="maxPage" name='maxPage' value="${conVO.maxPage}">
					
					
					<!-- conVO comm_list parameters  -->
					<input type='hidden' id="comm_pno" name='comm_pno' value="${conVO.comm_pno}">
					<input type='hidden'  id="comm_pageSize" name='comm_pageSize' value="${conVO.comm_pageSize}">
					<input type='hidden' id="comm_naviSize" name='comm_naviSize' value="${conVO.comm_naviSize}">
					<input type='hidden' id="comm_maxPage" name='comm_maxPage' value="${conVO.comm_maxPage}">
					
					
					<!-- boardVO parameters  -->
					<input type='hidden' id='sendMsg' name='sendMsg' value="${sendMsg}">
					<input type='hidden' id="bno" name='bno' value="${boardVO.bno}">
					<input type='hidden' name='title' value="${boardVO.title}">
					<input type='hidden' name='content' value="${boardVO.content}">
					<input type='hidden' name='writer' value="${boardVO.writer}">
					<input type='hidden' name='ref_bno' value="${boardVO.ref_bno}">
					<input type='hidden' name='reply_bno' value="${boardVO.reply_bno}">
					<input type='hidden' name='reply_lv' value="${boardVO.reply_lv}">
					
				</form>

				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">첨부파일</label>
						<c:choose>
							 <c:when test="${boardVO.uploadVOs eq '[]' }">
							   <div class="input-group">
			    						<span class="input-group-addon">첨부파일이 없습니다.</span>
	       				     	</div>
							 </c:when>
							 <c:otherwise>
							    <c:forEach var="file" items="${boardVO.uploadVOs}" varStatus="status">
									<div style="width: 100%;" class="input-group">
										<a style="width: 60%;" class="input-group-addon"
											href="/downloadFile?upload_fname=${file.upload_fname}&original_fname=${file.original_fname}">
											<c:out value='${file.original_fname}' />
										</a>
			    						<span class="input-group-addon" style="width:5%;">SIZE</span>
			         					<input title='fsize' type="text"  class="form-control" value="	<c:out value='${file.fsize}' /> bytes" readonly="readonly">
			       					    <span class="input-group-addon" style="width: 5%;">TYPE</span>
			          					<input title='ftype' type="text" class="form-control" value="	<c:out value='${file.ftype}' />" readonly="readonly">
		       				     	</div>
							    </c:forEach>
							 </c:otherwise>
						</c:choose>
						
					</div>
						<div class="form-group">
						<label for="exampleInputEmail1">BNO</label>
						<input title="bno" type="text" name='bno' class="form-control" value="${boardVO.bno}" readonly="readonly">
					</div>
				
				
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label>
						<input title="title" type="text" name='title' class="form-control" value="${boardVO.title}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea title="content" class="form-control" name="content" rows="20" readonly="readonly">${ boardVO.content.replaceAll('<', '&#x003C;') }</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label>
						<input title="writer" type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<div class="text-center">
						<button type="button" class="btn btn-success">Reply</button>
						<button type="submit" class="btn btn-warning">Modify</button>
						<button type="button" class="btn btn-danger">REMOVE</button>
						<button type="button" class="btn btn-primary">GO LIST</button>
					</div>
				</div>

			</div>
			<!-- /.box -->
			
				<div class="box-footer"> <!-- 댓글 새로 입력하기  -->
				<h2>댓글<small>-댓글로 소통하세요-</small></h2> 
					<form method="post">
						<input type="hidden" name="bno" value="${boardVO.bno}">
						<div>
							<div class="form-group">
								<label for="exampleInputEmail1">글내용</label> <br>
								<textarea  title='comm_content' id="content" class='content_box' name="content" placeholder="내용" required="required" style='resize:none; width:70%;  overflow: visible;'></textarea>
								<span id="contentLength"></span>
							</div>
						</div>
						<div>
							<div class="form-group">
								<label for="exampleInputEmail1">작성자</label> <br>
								<input title='comm_writer' type="text" id="writer" class="writer_box" name="writer" placeholder="이름" required="required">
								<input type="submit" style='display: none;' />
								<span id="writerLength"></span>
							</div>
						</div>
						<button id="comm_write" type="button" class="btn">작성</button>
					</form>
				</div>
				<div class="btn-group">
			                  <button type="button" class="btn btn-default" disabled="disabled" >CommentSize</button>
			                  <button type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false">
			                    <span class="caret"></span>
			                    <span class="sr-only">Toggle Dropdown</span>
	               			  </button>
			                  <ul class="dropdown-menu" role="menu">
			                    <li><a class="pageSize">5</a></li>
			                    <li><a class="pageSize">10</a></li>
			                    <li><a class="pageSize">15</a></li>
		                	  </ul>
		                </div>
				<div id="comment" class="box-footer">
					<span id="maxContent">전체 댓글 0:개</span>
						<table id="comm_list" class="table table-hover">
						</table>
					<div class="text-center">
						<ul class="pagination">

						</ul>
					</div>
				</div>
				
				
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.	 -->
</section>
<!-- /.content -->
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
