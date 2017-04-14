<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/common/common_header.jsp" />  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
function goBack() {
    history.back();
}
</script>

</head>
<body>

<!-- Top menu -->
<div class="w3-top">
  <div class="w3-white w3-xlarge" style="max-width:1200px;margin:auto">
    <div class="w3-button w3-padding-16 w3-left" onclick="goBack()">Back</div>
    <a href="/ppl/user/signOut"><div class="w3-right w3-padding-16">LogOut</div></a>
    <div class="w3-center w3-padding-16">Comment Detail</div>
  </div>
</div>

<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">


		<h1>${comment.userId }</h1>
		<hr/>
			<p>${comment.comment }</p> 
			<span style="color:#A6A4A4">${comment.date }</span> 
	<hr/>
		<c:choose>
			<c:when test="${comment.userId == sessionScope._USER_.userId }">
				<a href="/ppl/comment/edit?commentId=${comment.commentId }" class="greyA">수정</a>&nbsp;
				<a href="/ppl/comment/delete?commentId=${ comment.commentId }" class="greyA">삭제</a>&nbsp;
			</c:when>
		</c:choose>
		<a href="/ppl/item/detail?itemId=${comment.itemId }" class="greyA">목록으로 돌아가기</a>&nbsp;
		<a href="/ppl/comment/write?itemId=${comment.itemId }" class="greyA">새 댓글쓰기</a>&nbsp;
</div>
</body>
</html>