<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/common/common_header.jsp" />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>web-inf/view/drama/detail</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
}


$().ready(function(){
	<c:if test="${isAdminUser || isOperatorUser}">
	
	$("#edit").click(function(){
		$("#modifyForm").attr({
			"method" : "get",
			"action" : "/ppl/drama/edit"
		});
		$("#modifyForm").submit();
	});
	
	$("#delete").click(function(){
		$("#modifyForm").attr({
			"method" : "post",
			"action" : "/ppl/drama/delete"
		});
		$("#modifyForm").submit();
	});
	
	$("#registButton").click(function() {
		window.open("/ppl/item/write?dramaId=${param.dramaId}", "아이템 등록", "resizable=no, scrollbars=yes, toolbar=no, width=500px, height=500px, menubar=no");
	});
	
	</c:if>
	$("#likeBtn").click(function() {
		$.post("/ppl/like/doLike", {
			"targetId" : "${param.dramaId}",
			"userId" : "${sessionScope._USER_.userId}"
		}, function(response){
			var jsonObj = JSON.parse(response);
			
			if(jsonObj.status == "success"){
				$("#likeSpan").text(" ♥ " + jsonObj.likeCount);
			}
		});
	});
	
});
</script>
</head>
<body>

<!-- Top menu -->
<div class="w3-top">
  <div class="w3-white w3-xlarge" style="max-width:1200px;margin:auto">
    <div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>
    <a href="/ppl/user/signOut"><div class="w3-right w3-padding-16">LogOut</div></a>
    <div class="w3-center w3-padding-16">Drama Detail</div>
  </div>
</div>
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">

<c:choose>
			<c:when test="${isAdminUser || isOperatorUser }">
				<form id="modifyForm">
					<input type="hidden" name="dramaId" value="${param.dramaId }" />
					<button id="edit" class="w3-button w3-light-grey" >드라마 수정</button>
					<button id="delete" class="w3-button w3-light-grey">드라마 삭제</button>
				</form>
			</c:when>
</c:choose>
<hr/>

<div align="center" >
	<div>
		<h3>${drama.dramaTitle }</h3><br/>
		<div style="display:inline-block;">
			<img style="width:auto; height:250px; margin-right: 10px;" align="left" src="/ppl/drama/post?dramaId=${drama.dramaId}" />
	 	</div>
	 	<div align="left" style="display:inline-block; padding-left: 30px; padding-right: 30px; vertical-align: 20px;">
			  <table>
			  	<tr><td style="padding-right:10px;">연출</td><td>${drama.dramaProducer }</td></tr>
			  	<tr><td style="padding-right:10px;">작가</td><td>${drama.dramaWriter }</td></tr>
			  	<tr><td style="padding-right:10px;">편성</td><td>${drama.dramaScreeningTime }</td></tr>
			  	<tr><td style="padding-right:10px;">장르</td><td>${drama.dramaGenre }</td></tr>
			  	<tr><td style="padding-right:10px;">출연</td><td>${drama.dramaActor }</td></tr>
			  	<tr><td style="padding-right:10px;"><span id="likeSpan"> ♥  ${drama.dramaLikeCount } </span></td>
					<td><input style="margin-bottom: 10px; margin-top: 10px;" type="button" id="likeBtn" value="좋아요" class="w3-button w3-light-grey"/></td></tr>
			  </table>
		</div> 
	</div>
</div>	

<div class="list" style="padding: 30px 0 0 0 ">
	<hr/>
	<c:choose>
	<c:when test="${isAdminUser || isOperatorUser }">
		<button id="registButton" class="w3-button w3-light-grey">상품 추가</button>
	</c:when>	
	</c:choose>
	<div class="itemList" align="center">
	<table>
		<tr>
		<c:forEach items="${itemList }" var="item" varStatus="index">
			<td>
				<div align="center" style="background-color: #f1f1f1; width:200px; height: 280px; margin: 10px 10px 10px 10px;" >
					<a href="/ppl/item/detail?itemId=${item.itemId }">
					<div style="width: 200px; height: 250px; overflow: hidden; padding: 10px 10px 10px 10px; "  >
						<img align="left" src="/ppl/item/post?itemId=${item.itemId}"  style="max-width: 100%; height: auto;"/>
					</div></a>
					<div style="margin: 3px 3px 3px 3px; ">${item.itemName }</div><br/>
				<!--  
				<div style="color: #000000; font-size: 40px; font-weight: 900; height: 50px; position: relative; left: 70px;  top: -95px;">
					♥${drama.dramaLikeCount }
				</div>
				-->
				</div>
			</td>
			<c:if test="${(index.index + 1) % 5 == 0}" >
				</tr>
				<tr>
			</c:if>
		</c:forEach>
		</tr>
	</table>
	</div>
	</div>
	</div>
	</div>
</body>
</html>