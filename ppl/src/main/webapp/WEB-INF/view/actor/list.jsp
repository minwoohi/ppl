<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<jsp:include page="/WEB-INF/common/common_header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actor List</title>
<script type="text/javascript" src="/ppl/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

	//Script to open and close sidebar
	function w3_open() {
	    document.getElementById("mySidebar").style.display = "block";
	}
	 
	function w3_close() {
	    document.getElementById("mySidebar").style.display = "none";
	}

	$().ready(function() {
		$("#registButton").click(function() {
			window.open("/ppl/actor/write", "배우 등록", "resizable=no, scrollbars=yes, toolbar=no, width=500px, height=500px, menubar=no");
		});
		
		$("#modifyButton").click(function() {
			
			var actorId = $(".select:checked").val();
			//alert(actorId);
			window.open("/ppl/actor/modify?actorId=" + actorId, "배우 수정", "resizable=no, scrollbars=yes, toolbar=no, width=300px, height=500px, menubar=no");
		});
		
		$("#deleteButton").click(function() {
			var actorId = $(".select:checked").val();
			//alert(actorId);
			// ajax로 삭제시 화면reload
			$.post( "/ppl/actor/doDelete", {
				"actorId" : actorId,
			}, function(response) {
				location.reload();
			});
			
		});
	});
</script>
</head>
<body>


<!-- Top menu -->
<div class="w3-top">
  <div class="w3-white w3-xlarge" style="max-width:1200px;margin:auto">
    <div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>
    <a href="/ppl/user/signOut"><div class="w3-right w3-padding-16">LogOut</div></a>
    <div class="w3-center w3-padding-16">Actor</div>
  </div>
</div>
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">

<form>
<c:choose>
	<c:when test="${isAdminUser || isOperatorUser }">
		<input type="button" value="배우 등록" id="registButton" class="w3-button w3-light-grey" />
	<!-- <input type="button" value="배우 수정" id="modifyButton" />
	<input type="button" value="배우 삭제" id="deleteButton" /> -->
	</c:when>
</c:choose>
<hr/>

	<div align="center">
	<table>
		<tr>
			<c:forEach items="${actorList}" var="actor" varStatus="index">
				<td>
					<div style="margin: 15px 15px 15px 15px; " align="center" >
						<a href="/ppl/actor/detail?actorId=${actor.actorId}">
						<div style="width: 200px; height: 200px; overflow: hidden; " >
							<img src="/ppl/actor/post?actorId=${actor.actorId}" style="max-width: 100%; height: auto;" />
						</div>
						</a>
							<p>${actor.actorName}</p>
					<%-- 
					<input type="radio" class="select" name="select" value="${actor.actorId}"><br/> --%>
					<!-- <p class="w3-opacity">Actor</p> -->
					</div>	
				</td>
				<c:if test="${(index.index + 1) % 4 == 0}">
						</tr>
						<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	</div>
</form>
<form method="post" id="searchForm">
	<div align="center">
		 ${pager} 
	</div>
</form>
</div>
<jsp:include page="/WEB-INF/common/common_footer.jsp" />