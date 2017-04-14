<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/ppl-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<div id="chart_div"></div>
<script type="text/javascript">
	
	google.charts.load('current', {packages: ['corechart', 'line']});
	google.charts.setOnLoadCallback(drawChart1);
	
	
	 function drawChart1() {

		 
		 	var signdate = $("#signdate").val();
		 	var signdate1 = $("#signdate1").val();
		 	var signdate2 = $("#signdate2").val();
		 	var signdate3 = $("#signdate3").val();
		 	var signdate4 = $("#signdate4").val();
		 	var signdate5 = $("#signdate5").val();
		 	
		 	
		 	var signcount = Number($("#signcount").val());
		 	var signcount1 = Number($("#signcount1").val());
		 	var signcount2 = Number($("#signcount2").val());
		 	var signcount3 = Number($("#signcount3").val());
		 	var signcount4 = Number($("#signcount4").val());
		 	var signcount5 = Number($("#signcount5").val());
		 	
	        var data1 =  google.visualization.arrayToDataTable([
	            ['date', 'sign'],
	            [signdate5,  signcount5],
	            [signdate4, signcount4],
	            [signdate3, signcount3],
	            [signdate2, signcount2],
	            [signdate1, signcount1],
	            [signdate, signcount]
	          ]);

	        var options1 = {
	        	    hAxis: {
	        	          title: 'Date'
	        	        },
	        	        vAxis: {
	        	          title: 'Sign'
	        	        },
	        	       /*  backgroundColor: '#f1f8e9' */
	        };
	     
	        var chart1 =new google.visualization.LineChart(document.getElementById('chart_div1'));
	        chart1.draw(data1, options1);
	      }

</script>





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















<title>Insert title here</title>


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
 <span style="font-size:30px;cursor:pointer" float="left" onclick="openNav()">&#9776; Signdate</span>
 
<br/><br/>


	 <div id="chart_div1" style=  "width: 850px; height: 500px; float:left;"></div>
 
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






<input type = "hidden" id = "signdate" value = "${signdate}"/>
<input type = "hidden" id = "signcount" value = "${signcount}"/>

<input type = "hidden" id = "signdate1" value = "${signdate1}"/>
<input type = "hidden" id = "signcount1" value = "${signcount1}"/>

<input type = "hidden" id = "signdate2" value = "${signdate2}"/>
<input type = "hidden" id = "signcount2" value = "${signcount2}"/>

<input type = "hidden" id = "signdate3" value = "${signdate3}"/>
<input type = "hidden" id = "signcount3" value = "${signcount3}"/>

<input type = "hidden" id = "signdate4" value = "${signdate4}"/>
<input type = "hidden" id = "signcount4" value = "${signcount4}"/>

<input type = "hidden" id = "signdate5" value = "${signdate5}"/>
<input type = "hidden" id = "signcount5" value = "${signcount5}"/>



</body>
</html>