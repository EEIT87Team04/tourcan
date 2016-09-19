<%@ page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Sortable - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <style>
  #sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
  #sortable li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px; }
  #sortable li span { position: absolute; margin-left: -1.3em; }
  .float{
  float: right;
  }
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

<div id="start">
	開始時間：<input name="sTime" type="datetime-local" value="2016-09-19T07:59:00"/>
	交通時間：<input name="tTime" type="number" value="30"/>
	<input name="wTime" type="number" value="0" style="display: none"/>
</div>
 
<div id="sortable">
	  <div style="border:solid 1px; padding:5px; vertical-align: middle;text-align: center;">
	  	<h3><strong>Trip 1</strong></h3>
	  		交通時間：<input name="tTime" type="number"/><br/>
	 		<p name="sTime">起：</p>
	 		<p name="eTime">迄：</p>
	 		逗留時間：<input name="wTime" type="number"/><br/>
	  </div>
	  <div style="border:solid 1px; padding:5px; vertical-align: middle;text-align: center;">
	  	<h3><strong>Trip 2</strong></h3>
	  		交通時間：<input name="tTime" type="number"/><br/>
	 		<p name="sTime">起：</p>
	 		<p name="eTime">迄：</p>
	 		逗留時間：<input name="wTime" type="number"/><br/>
	  </div>
</div>
 
  <script>
  $( function() {
// 	  var sT = $("#start input[name='sTime']").val().replace('T'," ");
// 	  var tT = $("#start input[name='tTime']").val()*60*1000;
// 	  var startD = new Date(sT); 
// // 	  ---------------------------------------------
// 	  console.log("出發時間:");
// 	  console.log(startD);
// 	  console.log("毫秒:");
// 	  console.log(startD.getTime());
// 	  console.log("加上交通時間:");
// 	  console.log(startD.getTime()+tT);
// // 	  --------------------------------------------- 

// 	  var startT = new Date(startD.getTime()+tT);
	  
// // 	  ---------------------------------------------
// 	  console.log("開始時間:");
// 	  console.log(startT);
// 	  console.log("毫秒:");
// 	  console.log(startT.getTime());
// // 	  ---------------------------------------------
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
    $( "#sortable" ).sortable({
        start: function(event, ui) {
            ui.item.startPos = ui.item.index();
        },
        stop: function(event, ui) {
            console.log("Start position: " + ui.item.startPos);
            console.log("New position: " + ui.item.index());
			var tripList = $(this).children();
			console.log(tripList.length);
            for(var i=0;i<tripList.length;i++)
            {
				console.log(tripList[i].id);
            }
        }
    	
//     	update:function(e,ui){
//     		console.log(e);
//     		console.log(ui);
//     	}	
    });
    $( "#sortable" ).disableSelection();
  });
  </script>
  
  
<!--   Testing -->
   <script>
  $( function() {
// 	  		var code = 65;
// 			code++;
// 			for(var i=0;i<5;i++)
// 			{
// 				console.log(String.fromCharCode(code)+ i);
// 			}
  } );
  </script>
</body>
</html>