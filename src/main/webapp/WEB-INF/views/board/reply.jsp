<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<script src="/resources/js/util.js"></script>
<script src="/resources/js/common.js"></script>	
<script src="/resources/js/reply.js"></script>	

<script type="text/javascript">
	$(document).ready(function() {
		var data={
			bno : $('#bno')	
			,pno : $('#pno')
			,keyWord : $('#keyWord')
			,pageSize : $('#pageSize')
			,naviSize : $('#naviSize')
			
			,original_fname :  $('#original_fname')
			,upload_fname :   $('#upload_fname')
			
			,sendMsg : $('#sendMsg')
			
			,titleMax : 80
			,contentMax : 4000
			,writerMax : 80
		}
		
		init(data); // reply.js : 초기화
		
		$('#add_file').click(function() {
			var addFile = "<input type='file' class='uploadFile btn btn-default' name='uploadFile' class='form-control'>";
			$('.fileform').append(addFile);
			
			add_upload_Validation(); // common.js : 파일 첨부 용량 체크
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
					<h1>답변 달기<small>-답변을 달아보세요.-</small></h1>
				</div>
				<!-- /.box-header -->

				<form role="form" method="post" enctype="multipart/form-data" action="/board/replycreate">
					<input type='hidden' id="bno" name='bno' value="${boardVO.bno}">
					<input type='hidden' id="pno" name='pno' value="${conVO.pno}">
					<input type='hidden' id="keyWord" name='keyWord' value="${conVO.keyWord}">
					<input type='hidden'  id="pageSize" name='pageSize' value="${conVO.pageSize}">
					<input type='hidden' id="naviSize" name='naviSize' value="${conVO.naviSize}">
					
					<input type='hidden' id='sendMsg' name='sendMsg' value="${sendMsg}">
				
					<div class="box-body">
					
						<div class="fileform form-group">
							<label for="exampleInputEmail1">첨부파일</label>
							<input type="file" class="uploadFile btn btn-default" name='uploadFile' class="form-control">
<!-- 							<s>추가파일</button> -->
						</div>
							<button id="add_file" class="btn btn-primary btn-xs">+</button>
						
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> 
							<input type="text" id="title" name='title' class="form-control" required="required" autofocus="autofocus" placeholder="제목" value="${boardVO.title}">
							<span id="titleLength"></span>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea id="content" class="form-control" name="content" required="required" rows="20" placeholder="글 내용"></textarea>	
							<span id="contentLength"></span>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label> 
							<input type="text" id="writer" name="writer" class="form-control"  required="required" placeholder="작성자">
							<span id="writerLength"></span>
						</div>
					</div>
					<!-- /.box-body -->
					<input type='hidden' name='ref_bno' value="${boardVO.ref_bno}">
					<input type='hidden' name='reply_bno' value="${boardVO.reply_bno}">
					<input type='hidden' name='reply_lv' value="${boardVO.reply_lv}">				
					<div class="box-footer">
						<div class="text-center">
							<button type="submit" class="btn btn-primary">Submit</button>
							<button type="button" class="btn btn-warning">CANCEL</button>
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
