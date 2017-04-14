<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/ppl-admin/static/js/jquery-3.1.1.min.js"></script>
   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart1);
      google.charts.setOnLoadCallback(drawChart2);
      google.charts.setOnLoadCallback(drawChart3);
      google.charts.setOnLoadCallback(drawChart4);
      
      
      
      
      function drawChart1() {

    	  
    	  
      	var movie1 = $("#movie1").val();
    	var movie2 = $("#movie2").val();
    	var movie3 = $("#movie3").val();
    	var movie4 = $("#movie4").val();
    	var movie5 = $("#movie5").val();
    	
    	
    	var movieLK1 = Number($("#movieLK1").val());
    	var movieLK2 = Number($("#movieLK2").val());
    	var movieLK3 = Number($("#movieLK3").val());
    	var movieLK4 = Number($("#movieLK4").val());
    	var movieLK5 = Number($("#movieLK5").val());
    	
    	
        var data1 = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          [movie1, movieLK1],
          [movie2, movieLK2],
          [movie3, movieLK3],
          [movie4, movieLK4],
          [movie5, movieLK5],
          
        ]);

        var options1 = {
          title: '영화 좋아요 TOP 5'
        };

        var chart1 = new google.visualization.PieChart(document.getElementById('piechart'));

        chart1.draw(data1, options1);
      }
      function drawChart2() {

    	  
      	var drama1 = $("#drama1").val();
    	var drama2 = $("#drama2").val();
    	var drama3 = $("#drama3").val();
    	var drama4 = $("#drama4").val();
    	var drama5 = $("#drama5").val();
    	
    	
    	var dramaLK1 = Number($("#dramaLK1").val());
    	var dramaLK2 = Number($("#dramaLK2").val());
    	var dramaLK3 = Number($("#dramaLK3").val());
    	var dramaLK4 = Number($("#dramaLK4").val());
    	var dramaLK5 = Number($("#dramaLK5").val());
    	
          var data2 = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            [drama1, dramaLK1],
            [drama2, dramaLK2],
            [drama3, dramaLK3],
            [drama4, dramaLK4],
            [drama5, dramaLK5]
          ]);

          var options2 = {
            title: '드라마 좋아요 TOP 5'
          };

          var chart2 = new google.visualization.PieChart(document.getElementById('piechart1'));

          chart2.draw(data2, options2);
        }
      function drawChart3() {

    	  
    	var item1 = $("#item1").val();
      	var item2 = $("#item2").val();
      	var item3 = $("#item3").val();
      	var item4 = $("#item4").val();
      	var item5 = $("#item5").val();
      	
      	
      	var itemLK1 = Number($("#itemLK1").val());
      	var itemLK2 = Number($("#itemLK2").val());
      	var itemLK3 = Number($("#itemLK3").val());
      	var itemLK4 = Number($("#itemLK4").val());
      	var itemLK5 = Number($("#itemLK5").val());
    	  
    	  
    	  
          var data3 = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            [item1, itemLK1],
            [item2, itemLK2],
            [item3, itemLK3],
            [item4, itemLK4],
            [item5, itemLK5]
          ]);

          var options3 = {
            title: '아이템 좋아요 TOP 5'
          };

          var chart3 = new google.visualization.PieChart(document.getElementById('piechart2'));

          chart3.draw(data3, options3);
        }
      
      
      
      function drawChart4() {
    	  
    	var actor1 = $("#actor1").val();
    	var actor2 = $("#actor2").val();
    	var actor3 = $("#actor3").val();
    	var actor4 = $("#actor4").val();
    	var actor5 = $("#actor5").val();
    	
    	
    	var actorLK1 = Number($("#actorLK1").val());
    	var actorLK2 = Number($("#actorLK2").val());
    	var actorLK3 = Number($("#actorLK3").val());
    	var actorLK4 = Number($("#actorLK4").val());
    	var actorLK5 = Number($("#actorLK5").val());


 
          var data4 = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            [actor1, actorLK1],
            [actor2, actorLK2],
            [actor3, actorLK3],
            [actor4, actorLK4],
            [actor5, actorLK5]
          ]);

          var options4 = {
            title: '연예인 좋아요 TOP 5'
          };

          var chart4 = new google.visualization.PieChart(document.getElementById('piechart3'));

          chart4.draw(data4, options4);
        }
      
    </script>


<!-- css -->

<style>
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













</head>



<body>


<br/>


<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="/ppl-admin/authorization/manage">Authorization</a>
  <a href="/ppl-admin/user/list">Clients</a>
  <a href="/ppl-admin/ranking">Ranking</a>
  <a href="/ppl-admin/user/signdate">Signdate</a>
</div>

<div id="main">
 <span style="font-size:30px;cursor:pointer" float="left" onclick="openNav()">&#9776; Ranking</span>
 
<br/><br/>


	 <div id="piechart" style=  "width: 300px; height: 400px; float:left;"></div>
  	<div id="piechart1" style= "width: 300px; height: 400px; float: left;"></div>
  	<br/><br/><br/><br/><br/><br/> <br/><br/><br/> <br/> <br/> <br/><br/><br/><br/> <br/><br/><br/><br/><br/>
  	<div id="piechart2" style= "width: 300px; height: 400px; float: left"></div>
  	<div id="piechart3" style= "width: 300px; height: 400px; float: left"></div>



 
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



<br/><br/>



  	
  	
  	<c:forEach items ="${actorList}"  var="actors"  varStatus="index">
 	<input type= "hidden" value ="${actors.actorName}" id="actor${index.index+1}"/>
 	<input type= "hidden" value =${actors.actorLikeCount} id="actorLK${index.index+1}"/>
  	</c:forEach>
  
  	 <c:forEach items ="${dramaList}"  var="dramas"  varStatus="index">
 	<input type= "hidden" value ="${dramas.dramaTitle}" id="drama${index.index+1}"/>
 	<input type= "hidden" value =${dramas.dramaLikeCount} id="dramaLK${index.index+1}"/>
  	</c:forEach> 
  
  	<c:forEach items ="${movieList}"  var="movies"  varStatus="index">
 	<input type= "hidden" value ="${movies.movieTitle}" id="movie${index.index+1}"/>
 	<input type= "hidden" value =${movies.movieLikeCount} id="movieLK${index.index+1}"/>
  	</c:forEach>
  	
  	 	<c:forEach items ="${itemList}"  var="items"  varStatus="index">
 	<input type= "hidden" value ="${items.itemName}" id="item${index.index+1}"/>
 	<input type= "hidden" value =${items.itemLikeCount} id="itemLK${index.index+1}"/>
  	</c:forEach>
  	
  
  
 
  
  
  
  
  
  
</body>
</html>