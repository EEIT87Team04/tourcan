<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <title>${pageName}${(pageName!=null) ? " - " : ""}${siteName}</title>

    <!-- Bootstrap Core CSS -->
  <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/tourcanFront.css" rel="stylesheet">
  <link href="//cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  
<!--   <script src="//s3-ap-northeast-1.amazonaws.com/justfont-user-script/jf-44849.js"></script> -->

<link href='//fonts.googleapis.com/css?family=Open+Sans:600' rel='stylesheet' type='text/css'><script type="text/javascript">var _jf = _jf || [];_jf.push(['p','44849']);_jf.push(['_setFont','dath7','css','.dath7']);_jf.push(['_setFont','dath7','alias','dath7']);_jf.push(['_setFont','dath7','english','Open Sans']);_jf.push(['_setFont','dath7','weight',700]);(function(f,q,c,h,e,i,r,d){var k=f._jf;if(k.constructor===Object){return}var l,t=q.getElementsByTagName("html")[0],a=function(u){for(var v in k){if(k[v][0]==u){if(false===k[v][1].call(k)){break}}}},j=/\S+/g,o=/[\t\r\n\f]/g,b=/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,g="".trim,s=g&&!g.call("\uFEFF\xA0")?function(u){return u==null?"":g.call(u)}:function(u){return u==null?"":(u+"").replace(b,"")},m=function(y){var w,z,v,u,x=typeof y==="string"&&y;if(x){w=(y||"").match(j)||[];z=t[c]?(" "+t[c]+" ").replace(o," "):" ";if(z){u=0;while((v=w[u++])){if(z.indexOf(" "+v+" ")<0){z+=v+" "}}t[c]=s(z)}}},p=function(y){var w,z,v,u,x=arguments.length===0||typeof y==="string"&&y;if(x){w=(y||"").match(j)||[];z=t[c]?(" "+t[c]+" ").replace(o," "):"";if(z){u=0;while((v=w[u++])){while(z.indexOf(" "+v+" ")>=0){z=z.replace(" "+v+" "," ")}}t[c]=y?s(z):""}}},n;k.push(["_eventActived",function(){p(h);m(e)}]);k.push(["_eventInactived",function(){p(h);m(i)}]);k.addScript=n=function(u,A,w,C,E,B){E=E||function(){};B=B||function(){};var x=q.createElement("script"),z=q.getElementsByTagName("script")[0],v,y=false,D=function(){x.src="";x.onerror=x.onload=x.onreadystatechange=null;x.parentNode.removeChild(x);x=null;a("_eventInactived");B()};if(C){v=setTimeout(function(){D()},C)}x.type=A||"text/javascript";x.async=w;x.onload=x.onreadystatechange=function(G,F){if(!y&&(!x.readyState||/loaded|complete/.test(x.readyState))){y=true;if(C){clearTimeout(v)}x.src="";x.onerror=x.onload=x.onreadystatechange=null;x.parentNode.removeChild(x);x=null;if(!F){setTimeout(function(){E()},200)}}};x.onerror=function(H,G,F){if(C){clearTimeout(v)}D();return true};x.src=u;z.parentNode.insertBefore(x,z)};a("_eventPreload");m(h);n(r,"text/javascript",false,3000)})(this,this.document,"className","jf-loading","jf-active","jf-inactive","//d3gc6cgx8oosp4.cloudfront.net/js/stable/v/5.0.1/id/171580764262");</script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    <!-- Custom CSS -->
    <style>
    body {
		width: 100%;
		height: 100%;
    }
    .front-bg-color{
		background: url(${contextPath}/images/front/icons/front_iconbg.png) no-repeat;
		background-color: rgb(153,69,36);	
	}
	.pageHeader{
		position:fixed;
	/* 	top:0px; */
	/* 	padding:45px; */
		text-align:center;
	    vertical-align: middle;
		margin:0px 0px;
		width:100%;
		height:45px;
		background: url(${contextPath}/images/front/fp-header.jpg) no-repeat center center scroll;
	}
	.pageFooter{
		position:fixed;
		bottom:0px;
		clear:both;
		margin:0px 0px;
		width:100%;
		height:45px;
		background: url(${contextPath}/images/front/fp-header.jpg) no-repeat bottom center scroll;
	/* 	background: rgba(216,174,71,0.1); */
		background-color: rgb(216,174,71);
	}
    </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.css"
	rel="stylesheet">
</head>

<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${contextPath}/index.jsp">Tourcan</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a class="dath7" href="${contextPath}/attractions">景點</a></li>
                    <li><a class="dath7" href="${contextPath}/restaurants">美食</a></li>
                    <li><a class="dath7" href="${contextPath}/hotels">住宿</a></li>
                    <li><a class="dath7" href="${contextPath}/trip/listOneFromMemTrip.jsp">規劃行程</a></li>
                    <li><a class="dath7" href="${contextPath}/articles/list.jsp">討論區</a></li>
                    <c:if test="${vo!=null}">
                    <li><a class="dath7" href="${contextPath}/quest/questGetById.jsp">客服</a></li>
                    </c:if>
                    <c:if test="${vo==null}">
                    <li><a class="dath7" onclick=alert('請先登入會員')>客服</a></li>
                    </c:if>
                    
                    
                    <c:if test="${vo!=null}">
                    <li style="margin-left: 450px"><a class="dath7" href="${contextPath}/mem/edituser" style="float: right;">
                    ${vo.mem_nick},<c:if test="${vo.mem_nick==null}">${vo.mem_fname}</c:if>您好！
                    </a>
                    </li>
                    <li>
                    <a class="dath7" href="${contextPath}/logout.jsp">登出</a>
                    </li>
	            	</c:if>
                    <c:if test="${vo==null}">
                    <li style="margin-left: 450px">
                    <a class="dath7" href="${contextPath}/login.jsp">登入</a>
                    </li>
					</c:if>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
<div class="pageHeader">
</div>
    </nav>

    <!-- Page Content -->

    <div class="container containPad">
        <div class="row">
            <div class="col-lg-12 dath7">

            
