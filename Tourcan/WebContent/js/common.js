/**
 * 
 */
$.ajax({
	url : baseurl + "/att/RegionServlet",
	method : "GET",
	contentType : "application/json; charset=UTF-8",
	dataType : "json"
}).done(function(l) {
	var f = document.createDocumentFragment();
	for (var i = 1; i < l.length; i++)
		f.appendChild(new Option(l[i].region_name, l[i].region_id));
	$("#region_id").append(f);
}).fail(function(xhr) {
	console.log("Get region list unsuccessful.");
});
$(".datepicker").datepicker({
	dateFormat : "yy-mm-dd",
	//dayNamesMin : [ "日", "月", "火", "水", "木", "金", "土" ],
	dayNamesMin : [ "日", "一", "二", "三", "四", "五", "六" ],
	//monthNames: ["睦月", "如月", "彌生", "卯月", "皐月", "水無月", "文月", "葉月", "長月", "神無月", "霜月", "師走" ],
	//monthNames: ["端月", "花月", "梅月", "桐月", "蒲月", "伏月", "荔月", "桂月", "菊月", "陽月", "葭月", "臘月" ],
	monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	monthNamesShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
	showMonthAfterYear: true,
	changeYear: true,
	changeMonth: true,
	firstDay : 1,
	onClose : function(){$(this).blur()}
});