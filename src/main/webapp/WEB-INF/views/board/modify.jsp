<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	
	<input type='hidden' id="fno" name='fno' value="${uploadVO.fno}">
	<input type='hidden' id="original_fname" name='original_fname' value="${uploadVO.original_fname}">
	<input type='hidden'  id="upload_fname" name='upload_fname' value="${uploadVO.upload_fname}">
	<input type='hidden'  id="fsize" name='fsize' value="${uploadVO.fsize}">
	<input type='hidden' id="ftype" name='ftype' value="${uploadVO.ftype}">
	
	<input type='hidden' id='sendMsg' name='sendMsg' value="${sendMsg}">
	
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
			<label for="exampleInputEmail1">변경파일</label>
				<input type="file" id="uploadFile" name='uploadFile' class="form-control">
		</div>
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
			<button type="submit" class="btn btn-warning">SAVE</button>
			<button type="button" class="btn btn-danger">CANCEL</button>
			<button type="button" class="btn btn-primary">GO LIST</button>
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
