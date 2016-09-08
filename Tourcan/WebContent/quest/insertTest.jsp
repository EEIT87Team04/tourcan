<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增問題單資料</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link  rel="stylesheet">
<style type="text/css">

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
	<div>
		<form name="addQuest">
			<div >
				<div>
					<h1>新增問題</h1>
				</div>
			</div>
			<div>
				<div >
				    <div >
						<div >
							<label for="questCatalog">問題分類</label> <input type="number"
								id="quest_catalog" name="quest_catalog"
								placeholder="問題分類" min=0>
						</div>
						<div >
							<label for="questTopic">問題名稱</label> <input type="text"
								id="quest_topic" name="quest_topic"
								placeholder="問題名稱">
						</div>
					</div>


					<div >
						<div >
							<label for="questQuiz">問題內容</label> <input type="text"
								 id="quest_quiz" name="quest_quiz"
								placeholder="行程">
						</div>
						<div >
							<label for="memUid">會員Id</label> <input type="text"
								 id="mem_uid" name="mem_uid"
								placeholder="會員Id" min=-1>
						</div>
					</div>
					
					<div >
						<div >
							<label for="questReply">回覆內容</label> <input type="text"
								 id="quest_reply" name="quest_reply"
								placeholder="回覆內容">
						</div>
						<div >
							<label for="adminId">管理員Id</label> <input type="text"
								 id="admin_id" name="admin_id"
								placeholder="管理員Id" min=-1>
						</div>
					</div>

					<div >
						<div >
							<button type="button" class="btn btn-success form-control"
								id="btnInsert">Save</button>
						</div>
						<div >
							<button type="button" class="btn btn-danger form-control"
								id="btnReset">Reset</button>
						</div>
					</div>
				</div>
			</div>
			<div >
				<div  id="result"></div>
			</div>
		</form>
	</div>

	<script src="../js/bootstrap.min.js"></script>

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
			$.post("QuestServlet", JSON.stringify(json)).done(function(data) {
				console.log(data);
				$.each(data, function(errTrip, errMsg) {
					if (errMsg == "新增成功") {
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