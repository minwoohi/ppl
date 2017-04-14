<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>signIn</title>
<script type="text/javascript" src="/ppl-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#signInForm").find("button[type=submit]").click(function(){
			$("#signInForm").attr({
				"method" : "post",
				"action" : "/ppl-admin/user/signIn"
			});
			$("#signInForm").submit();
		});
	});
</script>
</head>

<style>

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}


button {
    background-color: #303030;
    color: white;
    padding: 14px 20px;
    /* margin: 8px 0; */
    border: none;
    cursor: pointer;
    width: 86%;
}
.signup:hover{
	opacity : 0.8;
}

button:hover {
    opacity: 0.8;
}
.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}

img.avatar {
    width: 40%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
   
}
</style>

<body>
<form id="signInForm">
	<div class ="container" style ="margin:0 auto; width:400px;">	
		<h1><a href ="/ppl/main" style ="text-decoration:none; color : #575757 ;"><div align = "center">JM</div></a></h1>
		<input type="text" name="userId" id="userId" placeholder="ID를 입력하세요." /><br/>
		<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요." /><br/>
		<button type="submit">SignIn</button>
		<!-- <div  style = "margin:0 auto; width:10px; height:10px;"> --><a href ="/ppl/user/signUp"><img src ="/ppl-admin/static/img/user/ic_person_add_black_24dp_2x.png" class ="signup" style="padding-bottom: 0px; width:50px; height:50px;"></a>
	</div>
	</form>
	
</body>
</html>