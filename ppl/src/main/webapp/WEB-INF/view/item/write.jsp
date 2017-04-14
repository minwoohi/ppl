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
				"action":"/ppl/item/write"				
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
			<input type="hidden" name="actorId" value="${param.actorId }" />
			<input type="hidden" name="dramaId" value="${param.dramaId }" />
			<input type="hidden" name="movieId" value="${param.movieId }" />
			<div class="name">
				<input type="text" name="itemName" placeholder="아이템 이름" />	
			</div>
			<div class="name">	
				<input type="text" name="itemBrand" placeholder="아이템 브랜드" />
			</div>
			<div class="name">		
				<input type="text" name="itemProductCode" placeholder="품번" />
			</div>	
			<div class="name">
				<input type="text" name="itemPrice"	placeholder="가격" />
			</div>	
				<div class="postFile">
					<input id="upload-name" value="" disabled="disabled" />
					<label for="post">파일 선택</label>
					<input type="file" name="post" id="post" accept=".gif, .jpg, .png"/><br/>
				</div>
				<input type="button" value="완료" id="registButton"/>
		</div>	
	</form>
</div>	
</body>
</html>