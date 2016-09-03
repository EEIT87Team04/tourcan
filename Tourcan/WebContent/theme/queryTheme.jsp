<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<input type="button" value="New Theme" id="but1" /> 
		<input type="button" value=" New Response" id="but2" />
	</div>
<div>
		<label for="themeID">ThemeID</label> <input type="text"
			class="form-control" id="themeID" name="themeID"
			 placeholder="id">
		<button type="button" id="search1">search</button>
	</div>
	<div>
		<label for="themeTopice">ThemeTopice</label> <input type="text"
			class="form-control" id="themeTopice" name="themeTopice"
			placeholder="themeTopice">
		<button type="button" id="search2">search</button>
	</div>
	<div>
		<label for="themeAll">LIST ALL Theme</label>
		<button type="button" id="search3">search all</button>
	</div>

	<div id="d1"></div>
	<hr>
	<hr>
</body>
</html>