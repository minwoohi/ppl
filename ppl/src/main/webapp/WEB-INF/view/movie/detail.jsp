<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/common/common_header.jsp" />
<!DOCTYPE html>
<html>
<head>
<!-- meta tag로 한글로 작성된 홈페이지라는것을 웹 브라우저에 알림 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- meta tag로 검색엔진들이 content의 설정 단어들을 검색 할 수 있도록 함. -->
<meta name="keyword" content="연예인, 영화, 드라마, 소품, 옷, 가방, 착용">
<!-- meta tag로 페이지 설명을 입력하여 정보파악, 최대 155자 -->
<meta name="desciption" content="설명">
<!-- meta tag로 제작자 명시 -->
<meta name="author" content="James">
<!-- meta tag로 페이지 관한 문의처 메일 주소를 명시하는 옵션 -->
<meta name="reply-to" content="James@naver.com">
<!-- meta tag로 문자열 감지 금지 for iPhone -->
<meta name="format-detection" content="telephone=no">
<!-- meta tag로 웹 페이지에서 풀 스크린 동작 for iPhone -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- meta tag로 디바이스에 따라 풀 스크린 제공 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

//Script to open and close sidebar
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
}

function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
}

	$().ready(function(){
		
			<c:if test="${isAdminUser || isOperatorUser}">
		
				editMovieButton();
				
				addMoiveItemButton();
				
				deleteMovieButton();
			
			</c:if>
				
			likeButton();
		
			});
	
			
	
		
	// --------------기능 정의 시작---------------------
	
		function editMovieButton(){
			$("#editMovie").click(function(){
				$("#movieForm").attr({
					"method" : "get",
					"action" : "/ppl/movie/modify"
				});
				$("#movieForm").submit();
			});
		}
	
		function deleteMovieButton(){
			$("#deleteMovie").click(function(){
				$("#movieForm").attr({
					"method" : "post",
					"action" : "/ppl/movie/delete"
				});
				$("#movieForm").submit();
			});
		}
		
		function addMoiveItemButton(){
			var movieId = $("#movieId").val();
			$("#addItem").click(function(){
				window.open(
						"/ppl/item/write?movieId="+movieId,
						"등록",
						"resizable=no, scrollbars=yes, toolbar=no,width=500px, height=400px, menubar=no");
			});
		}
		
		function likeButton(){
			$("#likeBtn").click(function() {
				$.post("/ppl/like/doLike", {
					"targetId" : "${param.movieId}",
					"userId" : "${sessionScope._USER_.userId}"
				}, function(response){
					var jsonObj = JSON.parse(response);
					
					if(jsonObj.status == "success"){
						$("#likeSpan").text(" ♥ " + jsonObj.likeCount);
					}
				});
			});
		}
</script>
</head>
<body>

<!-- Top menu -->
<div class="w3-top">
  <div class="w3-white w3-xlarge" style="max-width:1200px;margin:auto">
    <div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>
    <a href="/ppl/user/signOut"><div class="w3-right w3-padding-16">LogOut</div></a>
    <div class="w3-center w3-padding-16">Movie Detail</div>
  </div>
</div>
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">


<c:choose>
	<c:when test="${isAdminUser || isOperatorUser }">
		<form id="movieForm">
			<!-- 무비 -->
			<input type="hidden" id="movieId" name="movieId" value="${movie.movieId}" />
			<input type="button" id="editMovie" name="editMovie" value="수정" class="w3-button w3-light-grey" />
			<input type="button" id="deleteMovie" name="deleteMovie" value="삭제" class="w3-button w3-light-grey" />
		</form>	
	</c:when>	
</c:choose>	
						
<hr/>


	<div align="center" >
			<div align="center" id="movieDetail" >
				<h3>${movie.movieTitle}</h3><br/>
				<div style="display:inline-block;">
					<img style="width:auto; height:250px; margin-right: 10px;" class="showMovieImage" src="/ppl/movie/post?movieId=${movie.movieId}" width="250px" height="250px"/>
				</div>
				
				<div align="left" style="display:inline-block; padding-left: 30px; padding-right: 30px; vertical-align: 20px;">
				  	<table>
					  	<tr><td style="padding-right:10px;">부제</td><td>${movie.movieSubtitle}</td></tr>
					  	<tr><td style="padding-right:10px;">감독</td><td>${movie.movieDirector}</td></tr>
					  	<tr><td style="padding-right:10px;">배우</td><td>${movie.movieActor}</td></tr>
					  	<tr><td style="padding-right:10px;">장르</td><td>${movie.movieGenre}</td></tr>
					  	<tr><td style="padding-right:10px;">Intro</td><td>${movie.movieIntro}</td></tr>
					  	<tr><td style="padding-right:10px;"><span id="likeSpan">♥ ${movie.movieLikeCount}</span></td>
							<td><input style="margin-bottom: 10px; margin-top: 10px;" type="button" id="likeBtn" value="Like" class="w3-button w3-light-grey"/></td></tr>
				  	</table>
				</div> 
			</div>
	</div>
	
<hr/>

<!-- 아이템 -->
<c:choose>
	<c:when test="${isAdminUser || isOperatorUser }">
		<input type="hidden" id="itemId" name="itemId" value="${items.itemId}" />
		<input id="addItem" type="button" value="등록" class="w3-button w3-light-grey" />
	</c:when>
</c:choose>

		
<div align="center" >
		<table>
		<tr>
			<c:forEach items="${itemsList}" var="items" varStatus="index" >
				<td>
					<div align="center" style="background-color: #f1f1f1; width:200px; height: 280px; margin: 10px 10px 10px 10px;">
						<a href="/ppl/item/detail?itemId=${items.itemId}">
							<div style="width: 200px; height: 250px; overflow: hidden; padding: 10px 10px 10px 10px; "  >
								<img src="/ppl/item/post?itemId=${items.itemId}" style="max-width: 100%; height: auto;" />
							</div>	
						</a>
						<div style="margin: 3px 3px 3px 3px; ">${items.itemName}</div><br/>
					 </div>
				</td>
				<c:if test="${(index.index+1) % 5 == 0 }">
					</tr>
					<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</div>
	
</body>
</html>