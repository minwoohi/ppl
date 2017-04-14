<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/ppl/static/css/popup.css"/>     
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/ppl/static/css/common_layout.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actor Modify</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#registButton").click(function() {
						
			$("#modifyForm").attr({
				"action":"/ppl/actor/doModify?actorId=${param.actorId}",
				"method":"post"
			});
			$("#modifyForm").submit();
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
	<form id="modifyForm" enctype="multipart/form-data">
		<div class="contents">
			<input type="hidden" name="actorId" value="${actorVO.actorId }" />
			<div class="name">
				<input type="text" name="actorName" value="${actorVO.actorName}"  /><br/>
			</div>	
			<div class="checks">
				<input type="radio" id="male" name="actorSex" value="male" checked /><label for="male">남자</label> <br/>
				<input type="radio" name="actorSex" value="female" /><label for="female">여자</label><br/>
				<input type="radio" name="actorSex" value="other" /><label for="other">그 외</label><br/>
			</div>
			<div class="birth">
					<p>생년월일 :	
					<input type="date" name="actorBirth" /></p>
			</div>
			<div class="three">	
				<div class="actorHeight">
					<input type="text" name="actorHeight" value="${actorVO.actorHeight}" placeholder="배우 키" /><br/>
				</div>
				<div class="actorWeight">
					<input type="text" name="actorWeight" value="${actorVO.actorWeight}" placeholder="배우 몸무게" /><br/>
				</div>
				<div class="actorEntertainment">	
					<input type="text" name="actorEntertainment" value="${actorVO.actorEntertainment }" placeholder="배우 소속사" /><br/><br/>
				</div>
			</div>	
			<div class="postFile">
					<input id="upload-name" value="" disabled="disabled" />
					<label for="post">파일 선택</label>
					<input type="file" name="post" id="post" accept=".gif, .jpg, .png"/><br/>
			</div>
				<input type="button" value="수정완료" id="registButton"/>
			</div>	
	</form>	
</div>
</body>
</html>