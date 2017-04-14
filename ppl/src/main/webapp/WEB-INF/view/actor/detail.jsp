<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<jsp:include page="/WEB-INF/common/common_header.jsp" /> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actor Detail & Item List</title>
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
		var actorId = $(".actorId").val();
		$("#registButton").click(function() {
			window.open("/ppl/item/write?actorId=${param.actorId}", "아이템 등록", "resizable=no, scrollbars=yes, toolbar=no, width=500px, height=500px, menubar=no");
		});
		$("#actorModifyButton").click(function() {
			//alert(actorId);
			window.open("/ppl/actor/modify?actorId=" + actorId, "배우 수정", "resizable=no, scrollbars=yes, toolbar=no, width=500px, height=500px, menubar=no");
		});
		
		$("#actorDeleteButton").click(function() {
			$("#actorForm").attr({
				"method" : "post",
				"action" : "/ppl/actor/doDelete"
			});
			$("#actorForm").submit();
		});
		
		/* $("#modifyButton").click(function() {
			var itemId = $(".select:checked").val();
			window.open("/ppl/item/modify?itemId=" + itemId, "아이템 수정", "resizable=no, scrollbars=yes, toolbar=no, width=300px, height=500px, menubar=no");
		}); */
		
		/* $("#deleteButton").click(function() {
			var itemId = $(".select:checked").val();
			// ajax로 삭제시 화면reload
			$.post( "/ppl/item/doDelete", {
				"itemId" : itemId,
			}, function(response) {
				location.reload();
			});
		}); */
		var isLogin = $(".isLogin").val();
		
		if ( isLogin == 'true' ) {
			
			var isLikeActor = $(".isLikeActor").val();
			
			$(".actorLikeEmpty").click(function() {
				$.post("/ppl/actor/doLikeActor",
						{
							"actorId" : $(".actorId").val(),
							"actorIsLike" : isLikeActor
						},
						function(response) {
							var obj = JSON.parse(response);
							if( obj.status == "success" ){
								location.reload(); // 화면 새로고침 
							}
							else {
							 	alert(" 실패");
							}
						});
			});
			
			$(".actorLikeFull").click(function() {
				$.post("/ppl/actor/doNotLikeActor",
						{
							"actorId" : $(".actorId").val(),
							"actorIsLike" : isLikeActor
						},
						function(response) {
							var obj = JSON.parse(response);
							if( obj.status == "success" ){
								location.reload(); // 화면 새로고침 
							}
							else {
							 	alert(" 실패");
							}
						});
			});
		}
		

		else {
			
			$(".actorLikeEmpty").click(function() {
				alert("로그인 후에 사용할 수 있는 기능입니다.");
				$("#actorForm").attr({
					"action" : "/ppl/user/signIn",
					"method" : "post"
				});
				$("#actorForm").submit();
			});
			
		}
		/* $("#itemLikeIcon").click(function() {
			
			var isLike = $("#isLike").val();
			// if( $(".itemLikeIcon").attr ) 
			$.post("/ppl/item/doLikeItem",
					{
						"itemId" : $("#itemId").val()
					},
					function(response) {
						var obj = JSON.parse(response);
						if( obj.status == "success" ){
							//location.reload(); // 화면 새로고침 
							$(".itemLikeIcon").attr("src", "/ppl/static/img/1490793551_like.png");
							//location.reload();
						}
						else {
						 	alert(" 실패");
						}
					});
		}); */
		
		$("#likeBtn").click(function() {
			$.post("/ppl/like/doLike", {
				"targetId" : "${param.actorId}",
				"userId" : "${sessionScope._USER_.userId}"
			}, function(response){
				var jsonObj = JSON.parse(response);
				
				if(jsonObj.status == "success"){
					$("#likeSpan").text(" ♥ " + jsonObj.likeCount);
				}
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
    <div class="w3-center w3-padding-16">Actor Detail</div>
  </div>
</div>
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">



<div>
	<form id="actorForm">
	<c:choose>
		<c:when test="${isAdminUser || isOperatorUser }">
		<input type="button" id="actorModifyButton" value="배우 수정" class="w3-button w3-light-grey"/>
		<input type="button" id="actorDeleteButton" value="배우 삭제" class="w3-button w3-light-grey"/>
		</c:when>
	</c:choose>
	
<hr/>	
		<div align="center">
			<div >
				<h3>${actorVO.actorName}</h3><br/>
				<div style="width: 250px; height: 250px; overflow: hidden; display:inline-block;" >
					<img src="/ppl/actor/post?actorId=${actorVO.actorId}" 
		 				 style="width: 250px; height: auto;" />
		 		</div>	
		 		<div align="left" style="display:inline-block; padding-left: 30px; padding-right: 30px; vertical-align: 50px;">	
						<input type="hidden" class="isLogin" value="${isLogin}" />
						<input type="hidden" class="isLikeActor value="${isLikeActor}" />
						<input type="hidden" class="actorId" value="${actorVO.actorId}" name="actorId" /> 
						
						<table>
							<tr>
							<td style="padding-right:10px;">Birth</td>
							<td>${actorVO.actorBirth}</td>
							</tr>
							<tr>
							<td style="padding-right:10px;">Height </td>
							<td>${actorVO.actorHeight}cm</td>
							</tr>
							<tr>
							<td style="padding-right:10px;">Weight</td>
							<td>${actorVO.actorWeight}kg</td>
							</tr>
							<tr>
							<td style="padding-right:10px;">Entertaiment</td>
							<td>${actorVO.actorEntertainment}</td>
							</tr>
							<tr>
							<td style="padding-right:10px;">Like
							<tr><td style="padding-right:10px;"><span id="likeSpan"> ♥  ${actorVO.actorLikeCount } </span></td>
								<td><input style="margin-bottom: 10px; margin-top: 10px;" type="button" id="likeBtn" value="좋아요" class="w3-button w3-light-grey"/></td></tr>
								<%-- <c:choose>
								<c:when test="${isLikeActor == false}">
										<img src="/ppl/static/img/1490793559_like_outline.png" class="actorLikeEmpty" />
								</c:when>
								<c:when test="${isLikeActor}">
										<img src="/ppl/static/img/1490793551_like.png" class="actorLikeFull" />
								</c:when>
								</c:choose> 
									
							</td>
							<td>${actorVO.actorLikeCount}</td>
							</tr> --%>
						</table>
						<%-- <p>Birth   ${actorVO.actorBirth}</p>
						<p>Height   ${actorVO.actorHeight}cm</p>
						<p>Weight   ${actorVO.actorWeight}kg</p>
						<p>Entertaiment   ${actorVO.actorEntertainment}</p>
						<p>Like   ${actorVO.actorLikeCount} --%>
						
						
				</div>
			</div>	
		</div>
	</form>
</div>

<hr/>

<form id=registForm >
	<c:choose>
	<c:when test="${isAdminUser || isOperatorUser }">
		<input type="button" value="아이템 등록" id="registButton" class="w3-button w3-light-grey" />	
	</c:when>
	</c:choose>
	<!-- 	<input type="button" value="아이템 수정" id="modifyButton" />
		<input type="button" value="아이템 삭제" id="deleteButton" /> -->
		
<div align="center">
			<table>
			<tr>
				<c:forEach items="${itemList}" var="item" varStatus="index">
					<td>
						<input type="hidden" id="isLikeItem" value="false" />
						<input type="hidden" id="itemId" value="${item.itemId }" name="itemId" />
						
						<div align="center" style="background-color: #f1f1f1; width:200px; height: 280px; margin: 10px 10px 10px 10px;" >	
							<a href="/ppl/item/detail?itemId=${item.itemId}&actorId=${actorVO.actorId}">
								<div style="width: 200px; height: 250px; overflow: hidden; padding: 10px 10px 10px 10px; "  >
									<img src="/ppl/item/post?itemId=${item.itemId}" style="max-width: 100%; height: auto;"/>
								</div>	
							</a>
							<div style="margin: 3px 3px 3px 3px; ">${item.itemName}</div><br/>
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
</div>
<%-- <div align="center">
<form method="post" id="searchForm">
			${pager}
	</form>
	</div> --%>
</body>
</html>