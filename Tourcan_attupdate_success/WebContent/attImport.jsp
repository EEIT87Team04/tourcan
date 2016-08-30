<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>景點資料新增</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<h1>Title text</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-7">
				<div class="row">
					<div class="col-sm-12 form-group">
						<label for="attName">景點名稱</label> <input type="text"
							class="form-control" id="attName" placeholder="景點名稱">
					</div>
				</div>

				<div class="row">
					<div class="col-sm-3 form-group">
						<label>縣市</label> <select class="form-control" id="regionId">
							<option value="0">請選擇</option>
						</select>
					</div>
					<div class="col-sm-9 form-group">
						<label for="attAddr">街道地址</label> <input type="text"
							class="form-control" id="attAddr" placeholder="地址">
					</div>
				</div>

				<div class="row">
					<div class="col-sm-4 form-group">
						<label for="attStaytime">逗留時長</label> <input type="number"
							class="form-control" id="attStaytime" placeholder="分鐘">
					</div>
					<div class="col-sm-5 form-group">
						<label for="attPrice">景點開銷</label> <input type="number"
							class="form-control" id="attPrice" placeholder="新台幣元">
					</div>
					<div class="col-sm-3 form-group">
						<label for="attEat">吃貨</label> <input type="checkbox"
							class="checkbox" id="attEat">
					</div>
				</div>

				<div class="row">
					<div class="col-sm-7 form-group">
						<label for="電話">網址</label> <input type="text" class="form-control"
							id="attUrl" placeholder="網址">
					</div>
					<div class="col-sm-5 form-group">
						<label for="attPhone">電話</label> <input type="text"
							class="form-control" id="attPhone" placeholder="電話">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-7 form-group">
						<label>景點介紹</label>
						<textarea class="form-control resizable" rows="8" id="attIntro"
							placeholder="景點介紹"></textarea>
					</div>
					<div class="col-sm-5 form-group">
						<label>開放時間</label>
						<textarea class="form-control resizable" rows="8" id="attOpen"
							placeholder="開放時間"></textarea>
					</div>
				</div>
			</div>
			<div class="col-sm-5">
				<div class="row">
					<div class="col-sm-12">
						<label>即時預覽</label>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<h1>foot text</h1>
			</div>
		</div>
	</div>


	<script src="js/jquery-3.1.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>