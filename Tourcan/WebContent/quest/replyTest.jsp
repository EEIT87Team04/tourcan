<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/bootstrap.min.js"></script>
<title>新增問題單資料</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link  rel="stylesheet">
<style type="text/css">
#div_title{
    text-align:center;
}

#div_questCatalog{
    margin: auto;
    width: 46%;
	border: 1px solid #d4d4d4;
	border-radius: 2px;
	padding: 20px;
	position: relative;
	-webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
	box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
	line-height: 18px;
	font-size: 18px;
}

#div_quest_topic{
    margin: auto;
    width: 46%;
	border: 1px solid #d4d4d4;
	border-radius: 2px;
	padding: 20px;
	position: relative;
	-webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
	box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
	display: block;
	line-height: 18px;
	font-size: 18px;
}

#div_quest_reply{
    margin: auto;
    width: 46%;
	border: 1px solid #d4d4d4;
	border-radius: 2px;
	padding: 20px;
	position: relative;
	-webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
	box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
	display: block;
	line-height: 18px;
	font-size: 18px;
}

#quest_reply{
    height: 130px;
    line-height: 18px;
    display: block;
    font-size: 18px;
}

#div_btnInsert{
    width: 46%;
    margin: auto;
}

#div_btnReset{
    width: 46%;
    margin: auto;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
	<div>
		<form name="addQuest">
			<div >
				<div>
					<h1 id=div_title>Please Reply To Users</h1>
				</div>
			</div>
			<div>
				<div >
				    <div >
						<div class="form-group" id=div_questCatalog>
				           <label for="questCatalog">問題分類:</label> <input type="number"
					        class="form-control" id="quest_catalog" name="quest_catalog" placeholder="問題分類" min=1>
			            </div>
			            <div class="form-group" id=div_quest_topic>
				           <label for="questTopic">主題:</label> <input type="text"
					       class="form-control" id="quest_topic" name="quest_topic" placeholder="問題名稱">
			            </div>
			            <div class="form-group" id=div_quest_reply>
				           <label for="questReply">回覆內容:</label> <textarea
					        class="form-control"  id="quest_reply" name="quest_reply" placeholder="回覆內容"></textarea>
			            </div>
					</div>

<!--                                                                                    須拿  uid 和  topic ( uid 和  topic 需與提問相同)  -->
<!-- 						<div > -->
<!-- 							<label for="memUid">會員Id</label> <input type="text" -->
<!-- 								 id="mem_uid" name="mem_uid" -->
<!-- 								placeholder="會員Id" min=-1> -->
<!-- 						</div> -->
					
<!-- 					<div > -->
<!-- 						<div > -->
<!-- 							<label for="questReply">回覆內容</label> <input type="text" -->
<!-- 								 id="quest_reply" name="quest_reply" -->
<!-- 								placeholder="回覆內容"> -->
<!-- 						</div> -->
<!-- 						<div > -->
<!-- 							<label for="adminId">管理員Id</label> <input type="text" -->
<!-- 								 id="admin_id" name="admin_id" -->
<!-- 								placeholder="管理員Id" min=-1> -->
<!-- 						</div> -->
<!-- 					</div> -->

					<div >
						<div id=div_btnInsert>
							<button type="button" class="btn btn-success form-control"
								id="btnInsert">Save</button>
						</div>
						<div id=div_btnReset>
							<button type="button" class="btn btn-danger form-control"
								id="btnReset">Reset</button>
						</div>
					</div>
				</div>
			</div>
			<div >
				<div  id="result"></div>
			</div>
			<input type="hidden" method="insertQuest" />
		</form>
	</div>

	

	<script type="text/javascript">
	
	$(function() {
		$("#btnInsert").click(function() {
			var errMsgSpan = $('form[name="addQuest"] span');
			errMsgSpan.remove();
			var form = $(document.addQuest).serializeArray(), json = {};
			for (var i = 0; i < form.length; i++) {
				
				if (form[i].value.length > 0) {
					json[form[i].name] = form[i].value;
				}
				console.log(json);
			}
			$.post("ReplyServlet", JSON.stringify(json)).done(function(data) {
				console.log(data);
				$.each(data, function(errTrip, errMsg) {
					if (errMsg == "回覆成功") {
						document.addQuest.reset();
						errMsgSpan.remove();
					}
					var errSpan = document.createElement("span");
					var errText = document.createTextNode(errMsg);
					var errId = 'err' + errTrip;
					errSpan.appendChild(errText);
					errSpan.setAttribute("style", "color:red");
					errSpan.setAttribute("id", errId);
					$('#' + errId).remove();
					$('#' + errTrip).after(errSpan);
				});
				console.log("200.");
			}).fail(function(xhr) {
				console.log("ERR.");
			});
		});
		$("#btnReset").click(function() {
			document.addQuest.reset();
			errMsgSpan.remove();
		});
	});
	
		</script>
</body>
</html>