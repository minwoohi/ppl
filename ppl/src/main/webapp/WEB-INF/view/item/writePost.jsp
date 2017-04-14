<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#registButton").click(function() {
			$("#writeForm").attr({
				"method":"post",
				"action":"/ppl/item/writePost"				
			});
			$("#writeForm").submit(); 
		});
	});
</script>
</head>
<body>
	<form id="writeForm" enctype="multipart/form-data">
		<input type="hidden" name="itemId" value="${param.itemId }" />
		<input type="file" name="itemPost" accept=".gif, .jpg, .png"/><br/><br/>
		<input type="button" id="registButton" value="등록" />
	</form>
</body>
</html>