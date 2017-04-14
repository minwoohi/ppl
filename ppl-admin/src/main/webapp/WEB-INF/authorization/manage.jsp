<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/ppl-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$().ready(function(){
	
	//1. 권한 목록 가져오기
	$.post("/ppl-admin/authorization/all",{},function(response){
		console.log(response);
		//json 포멧 스트링을 자바스크립트 오브젝트로 변환함
		var auth = JSON.parse(response);
		var authList = auth.authorizations;
		
		
		var authorizationDiv=$("#authorization");
		var parentAuthorizationId=$("#parentAuthorizationId");
		
		console.log(authorization);
		
		for(var i in authList){
			console.log(authList[i].authorizationName);
			
			
			var eachAuth = $("<div id = '"+ authList[i].authorizationId +"'></div>")
			eachAuth.text(authList[i].authorizationName);
			eachAuth.data("parent",authList[i].parentAuthorizationId);
			
			authorizationDiv.append(eachAuth);
			
			
			
			var itemAuth = $("<option value='"+ authList[i].authorizationId +"'>"
					+ authList[i].authorizationName +"</option>");
			parentAuthorizationId.append(itemAuth);
			
			
		}
		
	});
	
	
	
	//리스폰스에 제이슨형식 들어감 
	// 데이터 가져옴
	//쉐도우 돔은 이렇게안됨$("#authorization").find("div").click(function(){})
	$("#authorization").on("click" , "div", function(){
		alert($(this).text());
		$("#authorizationId").val($(this).attr("id"));
		$("#authorizationName").val($(this).text());
		$("#parentAuthorizationId").val($(this).data("parent"));
		
		$("#modifyBtn").remove();
		$("#deleteBtn").remove();
		
		var modifyBtn=$("<input type = 'button' id='modifyBtn' value='수정'/>");
		var deleteBtn=$("<input type = 'button' id='deleteBtn' value='삭제'/>");
		
		$("#registBtn").after(deleteBtn);
		$("#registBtn").after(modifyBtn);
		
	});
	
	
	
	
	
	
	//수정
	$("#registForm").on("click","#modifyBtn",function(){
	
		$.post("/ppl-admin/authorization/modify",{
			
			
			"authorizationId"      : $("#authorizationId").val(),
			"authorizationName"      : $("#authorizationName").val(),
			"parentAuthorizationId"  : $("#parentAuthorizationId").val()
		
		
		},
		function(response){
			var jsonObj = JSON.parse(response);
			if(jsonObj.status == "success"){
			var modifiedAuth = $("#authorizationId").val();
			$("#" + modifiedAuth).text($("#authorizationName").val() );
			$("#" + modifiedAuth).data("parent", $("#parentAuthorizationId").val());
			$("#parentAuthorizationId").find("option[value="+modifiedAuth+"]").text($("#authorizationName").val());
			
			
			$("#modifyBtn").remove();
			$("#deleteBtn").remove();
			$("#authorizationId").val("");
			$("#authorizationName").val("");
			$("#parentAuthorizationId").val("");
			}
		});
		
	
	
	});
	
	
	
	
	
	
	//삭제
	$("#registForm").on("click","#deleteBtn",function(){
		
		$.post("/ppl-admin/authorization/delete",{
			
			"authorizationId"      : $("#authorizationId").val()
			
		},function(response){
			 	
			 console.log(response);
			 	var jsonObj = JSON.parse(response);
			 	if(jsonObj.status == "success"){
			 		var deletedAuth = $("#authorizationId").val();
					$("#"+deletedAuth).remove(); 
					$("#parentAuthorizationId").find("option[value="+deletedAuth+"]").remove();
					
					$("#modifyBtn").remove();
					$("#deleteBtn").remove();
					$("#authorizationId").val("");
					$("#authorizationName").val("");
					$("#parentAuthorizationId").val("");
			 	}
			});

	});
	
	
	//$("#registForm").find("input[type=button]").click(function(){ 쉐도우 돔이기때문에
	//등록
	$("#registBtn").click(function(){
		$.post("/ppl-admin/authorization/regist",{
			"authorizationName"      : $("#authorizationName").val(),
			"parentAuthorizationId"  : $("#parentAuthorizationId").val()
		},function(response){
			var auth = JSON.parse(response);
			var authInfo = auth.authorization;
					
			
			var authorizationDiv=$("#authorization");
			var parentAuthorizationId=$("#parentAuthorizationId");
			
			var eachAuth = $("<div id = '"+ authInfo.authorizationId+"'></div>");
			eachAuth.text(authInfo.authorizationName);
			eachAuth.data("parent",authInfo.parentAuthorizationId);
			
			authorizationDiv.append(eachAuth);
			
			var itemAuth = $("<option value='"+ authInfo.authorizationId +"'>"
					+ authInfo.authorizationName +"</option>");
			parentAuthorizationId.append(itemAuth);
		});
	});
	
	
});
	

</script>
<title>Insert title here</title>









<style>
body {
    /* font-family: "Lato", sans-serif; */
    font-family: Nanum Gothic" !important;
 
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
<!-- 추가 -->

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
body {font-size: 16px;}
img {margin-bottom: -8px;}
.mySlides {display: none;}
</style>
<body class="w3-content w3-black" style="max-width:1500px;">

<!-- Header with Slideshow -->
<header class="w3-display-container w3-center">
  <button class="w3-button w3-block w3-green w3-hide-large w3-hide-medium" onclick="document.getElementById('download').style.display='block'">Download <i class="fa fa-android"></i> <i class="fa fa-apple"></i> <i class="fa fa-windows"></i></button>
  <div class="mySlides w3-animate-opacity">
    <img class="w3-image" src="/ppl-admin/static/img/main.PNG" alt="Image 1" style="min-width:500px" width="1500" height="1000">
    <div class="w3-display-left w3-padding w3-hide-small" style="width:35%">
      <div class="w3-black w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
        <a href="/ppl/main" style="text-decoration:none"><h1 class="w3-xlarge">Photos our web</h1>
        <hr class="w3-opacity">
        <p>team JM - Mainpage</p></a>
       <!--  <p><button class="w3-button w3-block w3-green w3-round" onclick="document.getElementById('download').style.display='block'">Download <i class="fa fa-android"></i> <i class="fa fa-apple"></i> <i class="fa fa-windows"></i></button></p> -->
      </div>
    </div>
  </div>
  <div class="mySlides w3-animate-opacity">
    <img class="w3-image" src="/ppl-admin/static/img/act.PNG" alt="Image 2" style="min-width:500px" width="1500" height="1000">
    <div class="w3-display-left w3-padding w3-hide-small" style="width:35%">
      <div class="w3-black w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
        <a href="/ppl/main" style="text-decoration:none"><h1 class="w3-xlarge">Photos our web</h1>
        <hr class="w3-opacity">
        <p>team JM - ActorPage</p></a>
       <!--  <p><button class="w3-button w3-block w3-red w3-round" onclick="document.getElementById('download').style.display='block'">Download <i class="fa fa-android"></i> <i class="fa fa-apple"></i> <i class="fa fa-windows"></i></button></p> -->
      </div>
    </div>
  </div>
  <div class="mySlides w3-animate-opacity">
    <img class="w3-image" src="/ppl-admin/static/img/drm.PNG" alt="Image 3" style="min-width:500px" width="1500" height="1000">
    <div class="w3-display-left w3-padding w3-hide-small" style="width:35%">
      <div class="w3-black w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
       <a href="/ppl/main" style="text-decoration:none"> <h1 class="w3-xlarge">Photos our web</h1>
        <hr class="w3-opacity">
        <p>team JM - DramaPage</p></a>
       <!--  <p><button class="w3-button w3-block w3-indigo w3-round" onclick="document.getElementById('download').style.display='block'">Download <i class="fa fa-android"></i> <i class="fa fa-apple"></i> <i class="fa fa-windows"></i></button></p> -->
      </div>
    </div>
  </div>
  
   <div class="mySlides w3-animate-opacity">
    <img class="w3-image" src="/ppl-admin/static/img/mov.PNG" alt="Image 4" style="min-width:500px" width="1500" height="1000">
    <div class="w3-display-left w3-padding w3-hide-small" style="width:35%">
      <div class="w3-black w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
        <a href="/ppl/main" style="text-decoration:none"><h1 class="w3-xlarge">Photos our web</h1>
        <hr class="w3-opacity">
        <p>team JM - MoviePage</p></a>
       <!--  <p><button class="w3-button w3-block w3-indigo w3-round" onclick="document.getElementById('download').style.display='block'">Download <i class="fa fa-android"></i> <i class="fa fa-apple"></i> <i class="fa fa-windows"></i></button></p> -->
      </div>
    </div>
  </div>
  
     <div class="mySlides w3-animate-opacity">
    <img class="w3-image" src="/ppl-admin/static/img/itm.PNG" alt="Image 5" style="min-width:500px" width="1500" height="1000">
    <div class="w3-display-left w3-padding w3-hide-small" style="width:35%">
      <div class="w3-black w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
        <a href="/ppl/main" style="text-decoration:none"><h1 class="w3-xlarge">Photos our web</h1>
        <hr class="w3-opacity">
        <p>team JM - ItemPage</p></a>
       <!--  <p><button class="w3-button w3-block w3-indigo w3-round" onclick="document.getElementById('download').style.display='block'">Download <i class="fa fa-android"></i> <i class="fa fa-apple"></i> <i class="fa fa-windows"></i></button></p> -->
      </div>
    </div>
  </div>
  
  
     <div class="mySlides w3-animate-opacity">
    <img class="w3-image" src="/ppl-admin/static/img/det.PNG" alt="Image 5" style="min-width:500px" width="1500" height="1000">
    <div class="w3-display-left w3-padding w3-hide-small" style="width:35%">
      <div class="w3-black w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
       <a href="/ppl/main" style="text-decoration:none"> <h1 class="w3-xlarge">Photos our web</h1>
        <hr class="w3-opacity">
        <p>team JM - DetailPage</p></a>
       <!--  <p><button class="w3-button w3-block w3-indigo w3-round" onclick="document.getElementById('download').style.display='block'">Download <i class="fa fa-android"></i> <i class="fa fa-apple"></i> <i class="fa fa-windows"></i></button></p> -->
      </div>
    </div>
  </div>
  
  
  
  <a class="w3-button w3-black w3-display-right w3-margin-right w3-round w3-hide-small w3-hover-light-grey" onclick="plusDivs(1)">Take Tour <i class="fa fa-angle-right"></i></a>
  <a class="w3-button w3-block w3-black w3-hide-large w3-hide-medium" onclick="plusDivs(1)">NEXT Page<i class="fa fa-angle-right"></i></a>


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
 <span style="font-size:30px;cursor:pointer" float="left" onclick="openNav()">&#9776; Authorization</span>
 
<br/>
<br/>
<br/>






	<div id = "regist">
		<form id = "registForm">
		 <input type="hidden" id = "authorizationId" />
			<span>권한 명</span>
			<input type ="text" id = "authorizationName"/> <br/>
			<br/>
			<span>상위 권한</span>
			<select id = "parentAuthorizationId"></select><br/>
			<br/>
			<input type = "button"  id="registBtn" value="저장" />
			</form>
	</div>
	<div id = "authorization"></div>
	
	
	
	
	
	
	
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
	
	
<script>
// Slideshow
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  if (n > x.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";  
  }
  x[slideIndex-1].style.display = "block";  
}
</script>	
	
	
	
</body>
</html>