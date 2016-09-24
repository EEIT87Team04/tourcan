<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<title>Insert title here</title>
</head>
<body>
<form name="">
<input id="fileupload" type="file" name="imgs">
<input id="upload" type="button" name="ulbtn" value="send">
</form>



<script src="${contextPath}/js/vendor/jquery.ui.widget.js"></script>
<script src="${contextPath}/js/jquery.iframe-transport.js"></script>
<script src="${contextPath}/js/jquery.fileupload.js"></script>
<script>
var dat;
$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
        url:"PhotoTest",
        replaceFileInput:false,
        singleFileUploads:true,
        add:function(e, data){
			$("#upload").off('click').one('click',function(){
				data.submit();
			});
        },
        done: function (e, data) {
            $.each(data.result.files, function (index, file) {
                $('<p/>').text(file.name).appendTo(document.body);
            });
        }
    });
});
</script>
</body>
</html>