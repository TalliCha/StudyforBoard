<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<script src="/resources/js/util.js"></script>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/page.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var data = {
			pno : $('#pno')
			,bno : $('#bno')
			,cno : 0
			
			,keyWord : $('#keyWord')
			,pageSize : $('#pageSize')
			,naviSize : $('#naviSize')
			
			,original_fname :  $('#original_fname')
			,upload_fname :   $('#upload_fname')
			
			,sendMsg : $('#sendMsg')
			
			,content : $('#content') 
			,writer : $('#writer')
			
			,maxPage : $('#maxPage')
			
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
					<input type='hidden' id="pno" name='pno' value="${conVO.pno}">
					<input type='hidden' id="keyWord" name='keyWord' value="${conVO.keyWord}">
					<input type='hidden'  id="pageSize" name='pageSize' value="${conVO.pageSize}">
					<input type='hidden' id="naviSize" name='naviSize' value="${conVO.naviSize}">
					<input type='hidden' id="maxPage" name='maxPage' value="${conVO.maxPage}">
					
					<input type='hidden' id='sendMsg' name='sendMsg' value="${sendMsg}">
					<input type='hidden' id="bno" name='bno' value="${boardVO.bno}">
					<input type='hidden' name='title' value="${boardVO.title}">
					<input type='hidden' name='content' value="${boardVO.content}">
					<input type='hidden' name='writer' value="${boardVO.writer}">
					<input type='hidden' name='ref_bno' value="${boardVO.ref_bno}">
					<input type='hidden' name='reply_bno' value="${boardVO.reply_bno}">
					<input type='hidden' name='reply_lv' value="${boardVO.reply_lv}">
					
					<input type='hidden' id="fno" name='fno' value="${uploadVO.fno}">
					<input type='hidden' id="original_fname" name='original_fname' value="${uploadVO.original_fname}">
					<input type='hidden'  id="upload_fname" name='upload_fname' value="${uploadVO.upload_fname}">
					<input type='hidden'  id="fsize" name='fsize' value="${uploadVO.fsize}">
					<input type='hidden' id="ftype" name='ftype' value="${uploadVO.ftype}">
					
				</form>

				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">첨부파일</label>
							<div class="input-group">
									<span id="upload_name" class="input-group-addon"></span>
          						    <span class="input-group-addon">SIZE</span>
             					    <input type="text" class="form-control" value="${uploadVO.fsize} bytes" readonly="readonly">
             					       <span class="input-group-addon">TYPE</span>
             					    <input type="text" class="form-control" value="${uploadVO.ftype}" readonly="readonly">
         				     </div>
					</div>
						<div class="form-group">
						<label for="exampleInputEmail1">BNO</label>
						<input type="text" name='bno' class="form-control" value="${boardVO.bno}" readonly="readonly">
					</div>
				
				
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label>
						<input type="text" name='title' class="form-control" value="${boardVO.title}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea class="form-control" name="content" rows="20" readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label>
						<input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
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
<!-- 				<a href="/comm_ExcelDownload">엑셀 다운로드</a> -->
					<form method="post">
						<input type="hidden" name="bno" value="${boardVO.bno}">
						<div>
							<div class="form-group">
								<label for="exampleInputEmail1">글내용</label> <br>
								<textarea  id="content" class='content_box' name="content" placeholder="내용" required="required" style='resize:none; width:70%;  overflow: visible;'></textarea>
								<span id="contentLength"></span>
							</div>
						</div>
						<div>
							<div class="form-group">
								<label for="exampleInputEmail1">작성자</label> <br>
								<input type="text" id="writer" class="writer_box" name="writer" placeholder="이름" required="required">
								<span id="writerLength"></span>
							</div>
						</div>
						<button id="comm_write" type="button" class="btn">작성</button>
					</form>
				</div>
				
				<div id="comment" class="box-footer">
					<span id="maxContent">전체 댓글 0:개</span>
				       <div class="table-responsive">
							<table id="comm_list" class="table table-bordered">
							</table>
						</div>
					<div class="text-center">
						<ul class="pagination">

						</ul>
					</div>
				</div>
				
				
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
