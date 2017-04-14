<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

	$().ready(function(){
		
		
		$("#signUpForm").find("button[type=submit]").click(function(){
			// 필수 입력 값 체크
			if($("#userId").val() == "" ){
				alert("아이디를 입력하지 않았습니다.");
				$("#userId").focus();
				return; // 종료
			}
			
			if($("#userPassword").val() == "" ){
				alert("비밀번호를 입력하지 않았습니다.");
				$("#userPassword").focus();
				return; // 종료
			}
			
			if($("#userName").val() == "" ){
				alert("이름을 입력하지 않았습니다.");
				$("#userName").focus();
				return; // 종료
			}
			
			if($("#userPassword").val().length < 7){ // 자바스크립트에서 length는 속성에 해당.
				alert("비밀번호는 8자 이상입력해야 합니다.");
				$("#userPassword").focus();
				return;
			}
			$("#signUpForm").attr({
				"method" : "post",
				"action" : "/ppl/user/signUp"
			});
			$("#signUpForm").submit();
			
					
		});
		
		$("#userId").keyup(function(){
			$.post("/ppl/user/checkDuplicate",
					{
						"userId" : $("#userId").val()
					},
					function(response){
						var jsonObj = JSON.parse(response);
						console.log(jsonObj);
						
						if(jsonObj.duplicated) {
							$("#duplicated").text("중복되는 아이디입니다. 다른 아이디를 입력해주세요.");
						}else{
							$("#duplicated").text("사용하실 수 있는 아이디입니다.");
						}
					});
		});
	});
</script>
</head>

<style>
/* Full-width input fields */
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

/* Set a style for all buttons */
button {
    background-color: #303030;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

/* Float signup button and add an equal width */
.signupbtn {
    float: left;
    width: 100%;
}

/* Add padding to container elements */
.container {
    padding: 16px;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
       width: 100%;
    }
}
</style>
<body>
	<c:if test="${not empty param.errorCode }" > <!--  에러코드가 있는 경우 -->
		<div>
			<c:choose>
				<c:when test="${param.errorCode == 0 }">
					ID는 필수 입력사항입니다.
				</c:when>
				<c:when test="${param.errorCode == 1 }">
					PASSWORD는 필수 입력사항입니다.
				</c:when>
				<c:when test="${param.errorCode == 2 }">
					이름은 필수 입력사항입니다.
				</c:when>
				<c:when test="${param.errorCode == 3 }">
					이미 사용중인 ID 입니다.
				</c:when>
				<c:otherwise>
					????
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<form id="signUpForm">
		<div class="container" style ="margin:0 auto; width:400px;">
		<h1><a href ="/ppl/main" style ="text-decoration:none; color : #575757;"><div align = "center">JM</div></a></h1>
		<input type="text" name="userId" id="userId" placeholder="ID를 입력해주세요." />
		<span id="duplicated"></span> <!-- 키업이벤트 발생시마다 보여준다.--><br/>
		<input type="password" name="userPassword" id="userPassword" placeholder="비밀번호를 입력해주세요." /><br/>
		<input type="text" name= "userName" id="userName" placeholder="이름을 입력해주세요." /><br/>
		
		<div class="clearfix" style = "margin:0 auto; width:400px;">
		<button type="submit" class="signupbtn">SignUp</button><br/>
		</div>
		</div>
	
	</form>
</body>
</html>