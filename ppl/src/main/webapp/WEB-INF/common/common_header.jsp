<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<style>
	body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif; font-style: black;}
	.w3-bar-block .w3-bar-item {padding:20px}
	
	.greyA {
		background-color: #f1f1f1;
		padding: 10px 10px 10px 10px;
		text-decoration:none;
	}
	
</style>
<title>Team JM</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
</head>
<body>

<!-- Sidebar (hidden by default) -->
	<nav class="w3-sidebar w3-bar-block w3-card-2 w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:40%;min-width:300px" id="mySidebar">
	  <a href="javascript:void(0)" onclick="w3_close()"
	  class="w3-bar-item w3-button">Close Menu</a>
	  <a href="/ppl/main" onclick="w3_close()" class="w3-bar-item w3-button">Main</a>
	  <a href="/ppl/movie/list" onclick="w3_close()" class="w3-bar-item w3-button">Movie</a>
	  <a href="/ppl/drama/list" onclick="w3_close()" class="w3-bar-item w3-button">Drama</a>
	  <a href="/ppl/actor/list" onclick="w3_close()" class="w3-bar-item w3-button">Actor</a>
	</nav>

	
