</div>
			</div>
			<div class="row" style="margin: 15px 15px">
				<br>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$(".iconBtn").each(function(){
				btns = $(this).attr("id");
				$("#"+ btns).click(function(){
// 					console.log($(this).attr("id"));
					$(location).attr("href", "/Tourcan/"+$(this).attr("id")+"/list.jsp");
				});
			});
			
		});
	</script>
</body>
</html>