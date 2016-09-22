<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/frontPageHeader.jsp"/>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<div>
		<form name="addQuest">
			<div >
				<div>
					<h1 id=div_title>我要發問</h1>
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
			            <div class="form-group" id=div_quest_quiz>
				           <label for="questQuiz">問題內容:</label> <textarea
					        class="form-control"  id="quest_quiz" name="quest_quiz" placeholder="問題內容"></textarea>
			            </div>
					</div>
						<div style="display: none;">
							<label for="memUid">會員Id</label> <input type="text"
								 id="mem_uid" name="mem_uid" value="${vo.mem_uid}"
								placeholder="會員Id" min=-1>
						</div>
					
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
<jsp:include page="/frontPageFooter.jsp"/>
