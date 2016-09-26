<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.35.3/js/bootstrap-dialog.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.35.3/js/bootstrap-dialog.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
 <link href="css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body{
  padding:0;
  margin:0;
}
.vid-container{
  position:relative;
  height:100vh;
  overflow:hidden;
}
.bgvid.back {
  position: fixed; right: 0; bottom: 0;
  min-width: 100%; min-height: 100%;
  width: auto; height: auto; z-index: -100;
}
.inner {
  position: absolute;
}
.inner-container{
  width:400px;
  height:400px;
  position:absolute;
  top:calc(50vh - 200px);
  left:calc(50vw - 200px);
  overflow:hidden;
}
.bgvid.inner{
  top:calc(-50vh + 200px);
  left:calc(-50vw + 200px);
  filter: url("data:image/svg+xml;utf9,<svg%20version='1.1'%20xmlns='http://www.w3.org/2000/svg'><filter%20id='blur'><feGaussianBlur%20stdDeviation='10'%20/></filter></svg>#blur");
  -webkit-filter:blur(10px);
  -ms-filter: blur(10px);
  -o-filter: blur(10px);
  filter:blur(10px);
}
.box{
  position:absolute;
  height:100%;
  width:100%;
  font-family:Helvetica;
  color:#fff;
  background:rgba(0,0,0,0.13);
  padding:30px 0px;
}
.box h1{
  text-align:center;
  margin:30px 0;
  font-size:30px;
}
.box input{
  display:block;
  width:300px;
  margin:20px auto;
  padding:15px;
  background:rgba(0,0,0,0.2);
  color:#fff;
  border:0;
}
.box input:focus,.box input:active,.box button:focus,.box button:active{
  outline:none;
}
.box button{
  background:#742ECC;
  border:0;
  color:#fff;
  padding:10px;
  font-size:20px;
  width:330px;
  margin:20px auto;
  display:block;
  cursor:pointer;
}
.box button:active{
  background:#27ae60;
}
.box p{
  font-size:14px;
  text-align:center;
}
.box p span{
  cursor:pointer;
  color:#666;
}
</style>
</head>

<body>
  <div class="vid-container">
    <video id="Video1" class="bgvid back" autoplay="false" muted="muted" preload="auto" loop>
      <source src="http://ak8.picdn.net/shutterstock/videos/2543468/preview/stock-footage-classic-teton-peaks-panorama-with-fall-colors-and-dramatic-sky.mp4" type="video/mp4">
    </video>
  <div class="inner-container">
    <video id="Video2" class="bgvid inner" autoplay="false" muted="muted" preload="auto" loop>
      <source src="http://ak8.picdn.net/shutterstock/videos/2543468/preview/stock-footage-classic-teton-peaks-panorama-with-fall-colors-and-dramatic-sky.mp4" type="video/mp4">
    </video>
    <div class="box">
      <h1>管理員登入</h1>
      <form action="${contextPath}/adridores/login" method="post">
        <table>
          <tr>
            <td><input type="text" placeholder="帳號：" name="admin_account"></td>
          </tr>
          <tr>
            <td><input type="text" placeholder="密碼：" name="admin_pwd"/></td>
          </tr>
          <tr>
            <td colspan="2"><button>登入</button></td>
          </tr>
        </table>
        </form>
    </div>
  </div>
</div>
</body>
</html>

<script type="text/javascript">

    var promises = [];
    function makePromise(i, video) {
        promises[i] = new $.Deferred();
        video.oncanplaythrough = function() {
            promises[i].resolve();
        }
    }
    
    $('video').each(function(index){
        this.pause();
        makePromise(index, this);
    })

    $.when.apply(null, promises).done(function () {
        $('video').each(function(){
        this.play();
        });
    });
</script>