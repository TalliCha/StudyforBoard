<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../include/header.jsp"%>
<script src="/resources/js/util.js"></script>
<script src="/resources/js/common.js"></script>	
<script src="/resources/js/modify.js"></script>	

<script type="text/javascript">
	$(document).ready(function() {
		
		var data={
			bno : $('#bno')				
			,pno : $('#pno')
			,keyWord : $('#keyWord')
			,pageSize : $('#pageSize')
			,naviSize : $('#naviSize')
			
			,sendMsg : $('#sendMsg')
			
			,original_fname : $('#original_fname')
			,upload_fname : $('#upload_fname')
			
			,titleMax : 80
			,contentMax : 4000
		}
		
		init(data); // modify.js : 초기화
		
		var filesLength = $(".deleteFiles").length; // 현재 가져온 파일 개수
		var delete_filesLength = $(".deleteFiles:checked").length; // 삭제 예정인 파일 개수
		var files = filesLength - delete_filesLength; // 보존할 가져온 파일 개수
		var count = files + $('.uploadFile').length; // 현재 추가 예정인 파일 수
		
		if(count > 10){
			$('.uploadFile:last-child').remove();
		}
		
		$('.deleteFiles').change(function() {
			var filesLength = $(".deleteFiles").length; // 현재 가져온 파일 개수
			var delete_filesLength = $(".deleteFiles:checked").length; // 삭제 예정인 파일 개수
			var files = filesLength - delete_filesLength; // 보존할 가져온 파일 개수
			var count = files + $('.uploadFile').length; // 현재 추가 예정인 파일 수
// 			alert(count);
			for (var i = 0; i < count-10; i++) {
				$('.uploadFile:last-child').remove();
			}
		});
		
		$('#add_file').click(function() {
			
			// 삭제 일때의 개수를 파악하는 디자인으로 가면 추가 적으로 발생 하는 예외에 대한 조건 처리가 많아 짐
			
			var filesLength = $(".deleteFiles").length;
			var delete_filesLength = $(".deleteFiles:checked").length; 
			var files = filesLength - delete_filesLength;
// 			var files = filesLength;
// 			alert(files);
			var count = files + $('.uploadFile').length;
// 			alert(count);
			if (count < 10) {
				var addFile = "<input type='file' class='uploadFile btn btn-default' name='uploadFile' class='form-control'>";
				$('.fileform').append(addFile);
				add_upload_Validation(); // common.js : 파일 첨부 용량 체크
			}else{
				alert('파일 10개 넘게는 파일 첨부 추가 할 수 없습니다.');	
			}
			return false;	
		});
		
	});
	
</script>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h1>글 수정하기<small>-작성된 글을 수정하세요-</small></h1>
				</div>
				<!-- /.box-header -->
<form role="form" method="post" enctype="multipart/form-data" action="/board/update">

	<input type='hidden' id="pno" name='pno' value="${conVO.pno}">
	<input type='hidden' id="keyWord" name='keyWord' value="${conVO.keyWord}">
	<input type='hidden'  id="pageSize" name='pageSize' value="${conVO.pageSize}">
	<input type='hidden' id="naviSize" name='naviSize' value="${conVO.naviSize}">
	
	<input type='hidden' id='sendMsg' name='sendMsg' value="${sendMsg}">
	
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
								<a style="width: 50%;"class="input-group-addon"
									href="/downloadFile?upload_fname=${file.upload_fname}&original_fname=${file.original_fname}">
									<c:out value='${file.original_fname}' />
								</a>
	    						<span class="input-group-addon" style="width:5%;">SIZE</span>
	         					<input type="text"  class="form-control" value="<c:out value='${file.fsize}' /> bytes" readonly="readonly">
	       					    <span class="input-group-addon" style="width: 5%;">TYPE</span>
	          					<input type="text" class="form-control" value="	<c:out value='${file.ftype}' />" readonly="readonly">
	       					    <span class="input-group-addon" style="width: 5%;">삭제<input name="deleteFiles" class="deleteFiles" type="checkbox" value="${file.upload_fname}"></span>
       				     	</div>
					    </c:forEach>
					 </c:otherwise>
				</c:choose>
		</div>
			<div class="fileform form-group">
				<label for="exampleInputEmail1">첨부파일</label>
				<input type="file" class="uploadFile btn btn-default" name='uploadFile' class="form-control">
<!-- 			<s>추가파일</button> -->
			</div>
			<button type="button" id="add_file" class="btn btn-primary btn-xs">+</button>
		<div class="form-group">
			<label for="exampleInputEmail1">BNO</label>
			<input type="text" id="bno" name='bno' class="form-control" value="${boardVO.bno}" readonly="readonly">
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Title</label>
			<input type="text" id="title" name='title' class="form-control" required="required" value="${boardVO.title}">
			<span id="titleLength"></span>
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" id="content" name="content" rows="20" required="required"  >${boardVO.content}</textarea>
			<span id="contentLength"></span>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label>
			<input type="text" id="writer" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
		</div>
	</div>
	<!-- /.box-body -->
	<div class="box-footer">
		<div class="text-center">
			<button type="submit" id="btn_save" class="btn btn-warning">SAVE</button>
			<button type="button" id="btn_cancel" class="btn btn-danger">CANCEL</button>
			<button type="button" id="btn_golist" class="btn btn-primary">GO LIST</button>
		</div>
	</div>
</form>

			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
