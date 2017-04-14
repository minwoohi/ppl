<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/ppl/static/css/popup.css"/> 
<link rel="stylesheet" type="text/css" href="/ppl/static/css/common_layout.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		
		$("#writeForm").find("input[type=button]").click(function(){
			
			$("#writeForm").attr({
				"action": "/ppl/drama/write",
				"method": "post"
			});
			$("#writeForm").submit();
			
		});
		
		var fileTarget = $('.postFile #post');
		fileTarget.on('change', function() {
			if(window.FileReader){
				var filename = $(this)[0].files[0].name;
			}
			else {
				var filename = $(this).val().split('/').pop().split('\\').pop();
			}
			$(this).siblings('#upload-name').val(filename);
		});
		
	});
</script>
</head>
<body>
<div class="cover">
	<form id="writeForm" enctype="multipart/form-data"> 
		<div class="contents">
			<input type="hidden" name="dramaId" value="${param.dramaId }"/>
			<div class="name">
				<input type="text" name="dramaTitle" placeholder="드라마 명을 입력하세요." /><br/>
			</div>
			<div class="name">	
				<input type="text" name="dramaProducer" placeholder="연출자명을 입력하세요" /><br/>
			</div>
			<div class="name">	
				<input type="text" name="dramaWriter" placeholder="작가명 입력하세요" /><br/>
			</div>
			<div class="name">	
				<input type="text" name="dramaScreeningTime" placeholder="편성정보를 입력하세요" /><br/>
			</div>
			<div class="name">	
				<input type="text" name="dramaGenre" placeholder="장르를 입력하세요" /><br/>
			</div>
			<div class="name">	
				<input type="text" name="dramaActor" placeholder="출연진을 입력하세요" /><br/>
			</div>
			<div class="postFile">
				<input id="upload-name" value="" disabled="disabled" />
				<label for="post">파일 선택</label>
				<input type="file" name="dramaPost" id="post" accept=".gif, .jpg, .png"/><br/>
			</div>	
				<input type="button" value="등록" id="registButton" />
		</div>
	</form>
</div>	
</body>
</html>