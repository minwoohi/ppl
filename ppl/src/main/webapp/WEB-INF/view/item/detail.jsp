<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/common/common_header.jsp" />   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type= "text/css" href="/ppl/static/css/comment.css"> 
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

//Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
}


	$().ready(function() {
		var isLikeItem = $(".isLikeItem").val();
		var itemId = $(".itemId").val();
		var dramaId = $(".dramaId").val();
		var movieId = $(".movieId").val();
		var actorId = $(".actorId").val();
		
		$("#modifyButton").click(function() {
			//var itemId = $(".select:checked").val();
			window.open("/ppl/item/modify?itemId=" + itemId, "아이템 수정", "resizable=no, scrollbars=yes, toolbar=no, width=500px, height=500px, menubar=no");
		});
		
		$("#deleteButton").click(function() {
			
			$("#itemForm").attr({
				"action" : "/ppl/item/doDelete", 
				"method" : "post"
			});
			$("#itemForm").submit();
		});
		
		$("#likeButton").click(function() {
			$.post("/ppl/like/doLike", {
				"targetId" : "${param.itemId}",
				"userId" : "${sessionScope._USER_.userId}"
			}, function(response){
				var jsonObj = JSON.parse(response);
				
				if(jsonObj.status == "success"){
					$("#likeSpan").text(" ♥ " + jsonObj.likeCount);
				}
			});
		});
		
		
		
		$("#addComment").click(function(){
			
			$("#commentForm").attr({
				"method" : "get",
				"action" : "/ppl/comment/write?itemId=${param.itemId}"
			});
			$("#commentForm").submit();
			/*  window.open("/ppl/comment/write?itemId=${param.itemId}", "댓글 등록", "resizable=no, scrollbars=yes, toolbar=no, width=500px, height=500px, menubar=no");  */
		}); 
		
		$("#pic").click(function(){
			window.open("/ppl/itemPost/writePost?itemId=${param.itemId}", "사진 추가 등록", "resizable=no, scrollbars=yes, toolbar=no, width=500px, height=500px, menubar=no");
		});
		
		$("#delete").click(function(){
			
			 if( $(":checkbox[name='checkbox']:checked").length == 0 ){
				    alert("삭제할 항목을 하나이상 체크해주세요.");
				    return;
				}
			
			$("#checkbox").attr({
				"method" : "post",
				"action" : "/ppl/itemPost/delete"
			})
			$("#checkbox").submit();
		});
		
		$("#checkAll").click(function(){ // 전체 선택 버튼 클릭시
			var checked = $(this).prop("checked");
		
			if(checked){
				$(":checkbox[name=checkbox]").prop("checked", true);
			} else {
				$(":checkbox[name=checkbox]").prop("checked", false);
			}
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
    <div class="w3-center w3-padding-16">Item Detail</div>
  </div>
</div>


<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">

	<form id="itemForm">
	
	<c:if test="${isAdminUser || isOperatorUser }">
		<input type="button" value="아이템 수정" id="modifyButton" class="w3-button w3-light-grey" />
		<input type="button" value="아이템 삭제" id="deleteButton" class="w3-button w3-light-grey" />
	</c:if>
		<div align="center">
			<div>
					<h3>${itemVO.itemName}</h3><br/>
					<div style="width: 400px; height: 200px; overflow: hidden; border:1px solid #aaa; display:inline-block;" >
						<img src="/ppl/item/post?itemId=${itemVO.itemId}" 
			 				style="max-width: 100%; height: auto; align:center; " />
			 		</div>	
			 		<div style="display:inline-block; vertical-align: 50px; padding-left: 30px; padding-right: 30px;">
						<table>
						 	<input type="hidden" class="isPriceUnknown" value="${isPriceUnknown}" />
						 	<input type="hidden" class="isLikeItem" value="${isLikeItem}" />
							<input type="hidden" class="itemId" value="${itemVO.itemId }" name="itemId" /> 
							<input type="hidden" class="dramaId" value="${itemVO.dramaId }" name="dramaId" /> 
							<input type="hidden" class="movieId" value="${itemVO.movieId }" name="movieId" /> 
							<input type="hidden" class="actorId" value="${itemVO.actorId }" name="actorId" /> 
							
							<tr><td style="padding-right:10px;">Brand</td><td>${itemVO.itemBrand}</td></tr>
							<tr><td style="padding-right:10px;">Product Code</td><td>${itemVO.itemProductCode}</td></tr>
							
							<c:choose>
							<c:when test="${isPriceUnknown == true}">
							<tr><td style="padding-right:10px;">Price</td><td>가격 미정</td></tr>
							</c:when>
							<c:when test="${isPriceUnknown == false}">
							<tr><td style="padding-right:10px;">Price</td><td>${itemVO.itemPrice}원</td></tr>
							</c:when>
							</c:choose>
							<tr><td style="padding-right:10px;"><span id="likeSpan"> ♥  ${itemVO.itemLikeCount} </span></td>
							<td>
							<input type="button" value="좋아요" id="likeButton" class="w3-button w3-light-grey" /></td></tr>
						</table>
					</div>	
				</div>			
		</div>	
	</form>	
	
<br/>
	<c:choose>
		<c:when test="${isAdminUser || isOperatorUser }">
			<input type="button" value="사진 추가 등록" id="pic" class="w3-button w3-light-grey" />
			<input type="button" value="선택 사진 삭제" id="delete" class="w3-button w3-light-grey" /><br/>
		</c:when>
	</c:choose>
	
	<div align=center style="padding-top:20px;">
		<div style="background-color: #f1f1f1; width:800px; padding-top:20px; padding-bottom:20px; ">
			<p>상세 사진</p>
			
			<div align="center">
	<table>
		<tr>
		<form id="checkbox">
		<c:choose>
			<c:when test="${isAdminUser || isOperatorUser }">
				<div align="left"> <input type="checkbox" id="checkAll" /> 모두 선택</div>
			</c:when>
		</c:choose>
		<input type="hidden" name="itemId" value="${param.itemId }" />
		
		<div style="width: 300px; height: 300px; overflow: hidden">
					<img src="/ppl/item/post?itemId=${itemVO.itemId}" 
				 		style="max-width: 100%; height: auto; align:center; " />
		</div>	 
		
		<c:forEach items="${postList }" var="post" varStatus="index">
			<td>
			
			<c:choose>
				<c:when test="${isAdminUser || isOperatorUser }">
						<input type="checkbox" name="checkbox" value="${post.itemPostId }" />
				</c:when>
			</c:choose>
				<img style="margin: 30px;" src="/ppl/itemPost/itemPost?itemPostId=${post.itemPostId}" width="300px" height="300px" /><br/> <!-- 주소를 가지고 있는 서블릿 -->
			</div>
			</td>
			<c:if test="${(index.index + 1) % 1 == 0}" >
				</tr>
				<tr>
			</c:if>
		</c:forEach>
		</form>
		</tr>
	</table>
			
			
				 
		</div>
	</div>	
	
	<br/>
	<hr>
	
	<form id="commentForm">
		<input type="hidden" name="userId" value="${sessionScope._USER_.userId }"/>
		<input type="button" value="댓글 등록" id="addComment" class="w3-button w3-light-grey" /><br/>
		<input type="hidden" class="commentItemId" value="${itemVO.itemId}" name="itemId"/>
	</form>
	
	
	<table>
		<c:forEach items="${commentList }" var="comment" varStatus="index">
			
		<tr>
		  <div class="dialogbox" >
	    		<div class="body" >
		      		<div class="message">
			      		<span class="tip tip-left"></span>
						<span>
						<img src="/ppl/static/img/1491484721_photo.png">&nbsp; ${comment.userId } 
						<pre><a href="/ppl/comment/detail?commentId=${comment.commentId }" style="text-decoration:none">${comment.comment }</a></pre>
						<div style="color:#A6A4A4">${comment.date }</div>
						</span>
					</div>
				</div>
			</div>
		</tr>
		
		</c:forEach>
	
	</table>
	<form method="post" id="searchForm">
			${pager}
	</form>
	
	
	
</div>
	
</body>
</html>