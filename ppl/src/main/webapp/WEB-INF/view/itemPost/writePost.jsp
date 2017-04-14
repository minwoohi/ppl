<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/ppl/static/css/popup.css"/> 
<link rel="stylesheet" type="text/css" href="/ppl/static/css/common_layout.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#registButton").click(function() {
			$("#writeForm").attr({
				"method":"post",
				"action":"/ppl/itemPost/writePost"
			});
			$("#writeForm").submit(); 
		});
	});
</script>
</head>
<body>
	<form id="writeForm" enctype="multipart/form-data">
		<input type="hidden" name="itemId" value="${param.itemId }" />
		<div class="postFile">
					<input id="upload-name" value="" disabled="disabled" />
					<label for="post">파일 선택</label>
					<input type="file" name="post" id="post" accept=".gif, .jpg, .png"/>
				</div>
		<input width="50px" type="button" id="registButton" value="등록" />
	</form>
</body>
</html>