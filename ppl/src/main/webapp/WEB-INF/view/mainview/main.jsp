<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>


<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type= "text/css" href="/ppl/static/css/login.css"> 

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body,h1 {font-family: "Raleway", Arial, sans-serif}
h1 {letter-spacing: 6px}
.w3-row-padding img {margin-bottom: 12px}

.modal-header, h4, .close {
      background-color: #5cb85c;
      color:white !important;
      text-align: center;
      font-size: 30px;
  }
  .modal-footer {
      background-color: #f9f9f9;
  }
</style>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#signInForm").find("input[type=submit]").click(function(){
			$("#signInForm").attr({
				"method" : "post",
				"action" : "/ppl/user/signIn"
			});
			$("#signInForm").submit();
		});
	});
</script>
</head>
<body>



<!-- !PAGE CONTENT! -->
<div class="w3-content" style="max-width:1500px">

<!-- Header -->
<header class="w3-panel w3-center w3-opacity" style="padding:128px 16px">
  <h1 class="w3-xlarge">DIRECTOR</h1>
  <h1>TEAM-JM</h1>
  
  <div class="w3-padding-32">
    <div class="w3-bar w3-border">
      <button type="button" class="w3-bar-item w3-button" data-toggle="modal" data-target="#loginmodal">SignIn</button>
      <a href="/ppl/movie/list" class="w3-bar-item w3-button w3-light-grey">Movie</a>
      <a href="/ppl/drama/list" class="w3-bar-item w3-button">Drama</a>
      <a href="/ppl/actor/list" class="w3-bar-item w3-button w3-hide-small">Actor</a>
    </div>
  </div>
  
</header>
    <!--modal-->
<div class="modal fade" id="loginmodal" role="dialog" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
    	  <div class="modal-dialog">
				<div class="loginmodal-container">
				  <form id="signInForm">
					<input type="text" name="userId" placeholder="ID를 입력하세요.">
					<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요"/>
					<input type="submit" name="login" class="loginmodal-submit" value="SignIn">
				  </form>
				  <div class="login-help">
					<a href="/ppl/user/signUp">SignUp</a> 
				  </div>
				</div>
			</div>
		  </div>
<!-- Photo Grid -->
<div class="w3-row-padding w3-grayscale" style="margin-bottom:128px">
  <div class="w3-half">
    <img src="/ppl/static/img/sjm/you.PNG" style="width:100%">
    <img src="/ppl/static/img/sjm/jung.PNG" style="width:100%">
      <img src="/ppl/static/img/sjm/misang.PNG" style="width:100%">
      <img src="/ppl/static/img/sjm/hobbit.PNG" style="width:100%">
  </div>

  <div class="w3-half">
  
  <img src="/ppl/static/img/sjm/lim.PNG" style="width:100%">
    <img src="/ppl/static/img/sjm/leegin.PNG" style="width:100%">
     <img src="/ppl/static/img/sjm/kumsa.PNG" style="width:100%">
      <img src="/ppl/static/img/sjm/harry.PNG" style="width:100%">

  </div>
</div>
  

<!-- End Page Content -->
</div>

<!-- Footer -->
<footer class="w3-container w3-padding-64 w3-light-grey w3-center w3-large"> 
  <i class="fa fa-facebook-official w3-hover-opacity"></i>
  <i class="fa fa-instagram w3-hover-opacity"></i>
  <i class="fa fa-snapchat w3-hover-opacity"></i>
  <i class="fa fa-pinterest-p w3-hover-opacity"></i>
  <i class="fa fa-twitter w3-hover-opacity"></i>
  <i class="fa fa-linkedin w3-hover-opacity"></i>
  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank" class="w3-hover-text-green">JM</a></p>
</footer>

</body>
</html>