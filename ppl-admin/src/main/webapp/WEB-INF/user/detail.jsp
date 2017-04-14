<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<style>
	body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif; font-style: black;}
	.w3-bar-block .w3-bar-item {padding:20px}
</style>	
<title>Insert title here</title>

<script type="text/javascript" src="/ppl-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$().ready(function(){
	
	
	
	
	var userId = "${param.userId}";
	$("#auth").val("${user.authorizationId}"); 
	$("#registDateBtn").click(function(){
		var buttonText = $(this).val();
		
		if (buttonText == "변경하기"){
			$(this).val("변경완료");
			$("#registDate").removeAttr("disabled")
		}
		else if (buttonText == "변경완료"){
			$.post("/ppl-admin/user/modify",{
					
					"userId"      : userId,
					"registDate"   : $("#registDate").val()
					
				},function(response){
					 	
					 console.log(response);
					 	var jsonObj = JSON.parse(response);
					 	if(jsonObj.status == "success"){

							$(this).val("변경하기");
							$("#point").attr("disabled","disabled");
					
					 	}
					});
		
			
			
		}
		
		
	});
	
	$("#passwordBtn").click(function(){
		var buttonText = $(this).val();
		
		if (buttonText == "변경하기"){
			$(this).val("변경완료");
			$("#password").removeAttr("disabled")
		}
		else if (buttonText == "변경완료"){
			
			
			
			$.post("/ppl-admin/user/modify",{
				
				"userId"      : userId,
				"userPassword"   : $("#password").val()
				
				
			},function(response){
				 	
				 console.log(response);
				 	var jsonObj = JSON.parse(response);
				 	if(jsonObj.status == "success"){
						$(this).val("변경하기");
						$("#password").attr("disabled","disabled");
				
				 	}
				});

	
		}
		
		
	});
	
	$("#authBtn").click(function(){
		var buttonText = $(this).val();
		
		if (buttonText == "변경하기"){
			$(this).val("변경완료");
			$("#auth").removeAttr("disabled")
		}
		else if (buttonText == "변경완료"){
			

			$.post("/ppl-admin/user/modify",{
				
				"userId"      : userId,
				"authorizationId"   : $("#auth").val()
				
			},function(response){
				 	
				 console.log(response);
				 	var jsonObj = JSON.parse(response);
				 	if(jsonObj.status == "success"){
				 		$(this).val("변경하기");
						$("#auth").attr("disabled","disabled");
				
				 	}
				});
			
			
		}
		
		
	});
	
	
	$("#deleteForm").find("input[type=button]").click(function(){
		$("#deleteForm").attr({
			
			"method" : "post",
			"action" : "/ppl-admin/user/delete?userId=${param.userId}"
		
		});
		
		$("#deleteForm").submit();
	});
	

	
	
	
});

</script>

</head>
<body>
<div align="center" style="margin:0;">
<h1 style="background-color: #222; color:white; padding: 20px 20px 20px 20px;">Information</h1>
<br/>
<img src="/ppl-admin/static/img/person-icon.png" style="width:200px; height:200px;"><br/><br/>
<span>ID : ${user.userId}</span><br/> 
<span>Name : ${user.userName}</span><br/> 
<span>가입일 : ${user.registDate}</span><br/> 
<span>권한 : ${user.authorizationVO.authorizationName}</span><br/>

<form id = "modifyForm">
	
	<input type = "password" id="password" disabled="disabled"/>
	<input type = "button" id ="passwordBtn"  value="변경하기" class="w3-button w3-light-grey" />
	<br/>
	<select id = "auth" disabled="disabled">
		<c:forEach items = "${authList}" var = "auth">
			<option value="${auth.authorizationId}">"${auth.authorizationName}"</option>
		</c:forEach>
	</select>
	<input type = "button" id ="authBtn" value="변경하기" class="w3-button w3-light-grey" />
</form>


<form id = "deleteForm">
	<input type="button" id = "deleteBtn" value = "삭제하기" class="w3-button w3-light-grey" />



</form>




</div>
</body>
</html>