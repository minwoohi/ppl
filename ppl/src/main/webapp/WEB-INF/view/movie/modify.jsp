<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/ppl/static/css/popup.css"/> 
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

	$().ready(function(){
		
		modifyButton();
		
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
	
	function modifyButton() {
		
		$("#writeForm").find("input[type=button]").click(function(){
			
			$("#writeForm").attr({
				
				"action" : "/ppl/movie/modify?movieId=${movieVO.movieId}",
				"method" : "post"
				
			});
			
			$("#writeForm").submit();
			
		});
	}
	
</script>
</head>
<body>
<div class="cover">
	<form id="writeForm" enctype="multipart/form-data">
		<div class="contents">
			<input type="hidden" name="movieId" value="${param.movieId}" />
			<div class="name">
				<input type="text" name="movieTitle" placeholder="제목" value="${movieVO.movieTitle}" /> <br/>
			</div>
			<div class="name">	
				<input type="text" name="movieSubtitle" placeholder="부제" value="${movieVO.movieSubtitle}" /> <br/>
			</div>
			<div class="name">	
				<input type="text" name="movieDirector" placeholder="감독" value="${movieVO.movieDirector}" /> <br/>
			</div>
			<div class="name">	
				<input type="text" name="movieActor" placeholder="배우" value="${movieVO.movieActor}" /> <br/>
			</div>
			<div class="name">	
				<input type="text" name="movieGenre" placeholder="장르" value="${movieVO.movieGenre}" /> <br/>
			</div>
			<div class="postFile">
				<input id="upload-name" value="" disabled="disabled" />
				<label for="post">파일 선택</label>
				<input type="file" name="moviePost" id="post" accept=".gif, .jpg, .png" /> <br/>
			</div>	
			<p>
				<textarea name="movieIntro" id="movieIntro" placeholder="설명" >${movieVO.movieIntro}</textarea>
				<br />
			</p>
			<input type="button" value="수정 완료" id="registButton" />
		<div class="contents">
	</form>
</div>
</body>
</html>