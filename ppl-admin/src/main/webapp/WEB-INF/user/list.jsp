<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


 <style>
table {
    border-collapse: collapse;
}

table, td, th {
    border: 1px solid black;
    font-family: "Lato", sans-serif;
}

th {
	height: 40px;
	background-color: #333333;
	color: #818181;
}


body {
    font-family: "Lato", sans-serif;
}

.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s
}

.sidenav a:hover, .offcanvas a:focus{
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}

#main {
    transition: margin-left .5s;
    padding: 16px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}



</style> 

<link rel="stylesheet" type="text/css" href="/ppl-admin/static/css/font.css"> 

</head>
<body>


<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="/ppl-admin/authorization/manage">Authorization</a>
  <a href="/ppl-admin/user/list">Clients</a>
  <a href="/ppl-admin/ranking">Ranking</a>
  <a href="/ppl-admin/user/signdate">Signdate</a>
</div>

<div id="main">
 <span style="font-size:30px;cursor:pointer" float="left" onclick="openNav()">&#9776; Clients</span>
 
<br/>


	 
<h5>총 ${userCount}명의 유저 이용중</h5>

<form method="post" action="/ppl-admin/user/doselect">
<table>
		<tr>
			<th>번호</th>
			<th>ID</th>
			<th>이름</th>
			<th>가입일</th>
			<th>권한</th>
		</tr>
		
		<c:forEach items="${userList}" var="user">
			<tr>
				
				<td>
					
						<input type = "checkbox" name ="chk-info" value="${user.userId}"/>
							${user.index}
				</td>
				<td>
					<a href = "/ppl-admin/user/detail?userId=${user.userId}" >${user.userId}</a>
				</td>
				<td>${user.userName}</td>
				<td>${user.registDate}</td>
				<td>${user.authorizationVO.authorizationName}</td>
			</tr>
		</c:forEach>
			
	</table>
	<br/>

	<select id = "auth3" name = "auth3">
			<c:forEach items = "${authList}" var = "auth">
				<option value="${auth.authorizationId}">"${auth.authorizationName}"</option>
			</c:forEach>
				<option value="${auth.authorizationId}">""</option>
		</select> 으로
		<input type = "submit" id ="authBtn2" value="선택변경하기" />
	</form>
	
	
	
	

	
	
	
 
	
	<form id="allModify" method="post" action="/ppl-admin/user/doModify" >
	<select id = "auth1" name = "auth1">
		<c:forEach items = "${authList}" var = "auth">
			<option value="${auth.authorizationId}">"${auth.authorizationName}"</option>
		</c:forEach>
			<option value="${auth.authorizationId}"></option>
	</select> 을
	<select id = "auth2" name = "auth2">
		<c:forEach items = "${authList}" var = "auth">
			<option value="${auth.authorizationId}">"${auth.authorizationName}"</option>
		</c:forEach>
			<option value="${auth.authorizationId}"></option>
	</select> 으로
	<input type = "submit" id ="authBtn" value="일괄변경하기" />
	</form>
	
	
	

	
	
	
	
	<div>
		<form id="searchForm">
			${pager}
		</form>
	</div>



 
</div>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
}
</script>




</body>
</html>