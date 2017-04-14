<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/common/common_header.jsp" />
<!DOCTYPE html >
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

<title>List of Movies</title>
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
			// 등록 버튼
			addMoiveButton();
		</c:if>
		
	});
	
	function addMoiveButton(){
		$("#add").click(function(){
			window.open(
					"/ppl/movie/write?movieId=${parma.movieId}",
					"영화등록",
					"resizable=no, scrollbars=yes, toolbar=no,width=500px, height=500px, menubar=no");
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
    <div class="w3-center w3-padding-16">Movie</div>
  </div>
</div>
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">

<c:choose>
	<c:when test="${isAdminUser || isOperatorUser}">
		<input id="add" type="button" value="영화등록" class="w3-button w3-light-grey" />
	</c:when>
</c:choose>
<hr/>

<div align="center">
	<table>
		<tr>
			<c:forEach items="${movieList}" var="movie" varStatus="index">
				<td>
					<div style="margin: 15px 15px 15px 15px; " align="center">
						<a href="/ppl/movie/detail?movieId=${movie.movieId}">
							<div>
								<img src="/ppl/movie/post?movieId=${movie.movieId}" width="200px" height="250px" />
							</div>
						</a>
					 </div>
				</td>
					<c:if test="${(index.index+1) % 4 == 0 }">
				</tr>
				<tr>
					</c:if>
					</c:forEach>
				</tr>
		</table>
	</div>
	<div>
		<form metod="post" id="searchForm">
			<div align="center">
				${pager}
			</div>	
		</form>
	</div>
</div>
</body>
</html>