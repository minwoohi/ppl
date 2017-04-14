<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/common_header.jsp" />  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	textarea {
		padding: 5px;
		margin-top: 3px;
		margin-bottom: 3px;
		border: 1px #BDBDBD;
		width: 425px;
	}
</style>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

function goBack() {
    history.back();
}

	$().ready(function(){
		
		$("#writeForm").find("input[type=button]").click(function(){
			
			$("#writeForm").attr({
				"action": "/ppl/comment/write",
				"method": "post"
			});
			$("#writeForm").submit();
			
		});
		
	});
</script>
</head>
<body>

<!-- Top menu -->
<div class="w3-top">
  <div class="w3-white w3-xlarge" style="max-width:1200px;margin:auto">
    <div class="w3-button w3-padding-16 w3-left" onclick="goBack()">Back</div>
    <a href="/ppl/user/signOut"><div class="w3-right w3-padding-16">LogOut</div></a>
    <div class="w3-center w3-padding-16">Write Comment</div>
  </div>
</div>

<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">


	<form id="writeForm" > 
		<input type="hidden" name="itemId" value="${param.itemId }"/>
		<input type="hidden" name="userId" value="${sessionScope._USER_.userId }"/>
		<textarea name="comment" placeholder="댓글을 입력하세요"></textarea><br/>
		<input type="button" value="등록" class="w3-button w3-light-grey" />
	</form>
</div>	
</body>
</html>