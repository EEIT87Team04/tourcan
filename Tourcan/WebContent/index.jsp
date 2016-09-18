<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href='//fonts.googleapis.com/css?family=Open+Sans:600' rel='stylesheet' type='text/css'>
<script src="//s3-ap-northeast-1.amazonaws.com/justfont-user-script/jf-44849.js"></script>
<!-- class name: .dath7 -->
<!-- fontfamily: dath7 -->
<!-- both can change -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tourcan</title>

    <!-- Bootstrap Core CSS -->
<!--     <link href="/Tourcan/css/bootstrap.min.css" rel="stylesheet"> -->
    <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />

    <!-- Custom CSS -->
    <link href="/Tourcan/css/stylish-portfolio.css" rel="stylesheet">
    <link href="/Tourcan/css/tourcanFront.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/Tourcan/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Navigation -->
    <a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a>
    <nav id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
            <li class="sidebar-brand">
                <a href="#top" onclick=$("#menu-close").click();>Tourcan</a>
            </li>
            <li>
                <a href="#top" onclick=$("#menu-close").click();>Home</a>
            </li>
            <li>
                <a href="#about" onclick=$("#menu-close").click();>About</a>
            </li>
            <li>
                <a href="#services" onclick=$("#menu-close").click();>Services</a>
            </li>
            <li>
                <a href="#portfolio" onclick=$("#menu-close").click();>Recommend</a>
            </li>
            <li>
                <a href="#contact" onclick=$("#menu-close").click();>Contact</a>
            </li>
        </ul>
    </nav>

    <!-- Header -->
    <header id="top" class="header">
        <div class="text-vertical-center">
            <h1 class="frontText">Tourcan</h1>
            <h2 class="frontText dath7">我的旅遊易開罐，不管想去哪玩都很容易！</h2>
            <br>
            <a href="#about" class="btn btn-dark btn-lg dath7">馬上開始</a>
        </div>
    </header>

    <!-- About -->
    <section id="about" class="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center dath7">
                    <h2>簡介</h2>
                    <p class="lead dath7" style="text-align:justify;">　　短暫的假期不知該往何處去，想出門走走放鬆、減壓，反被行程規劃磨盡了耐心和精力。當有一個把美食、住宿與交通都能集合在內的網站，讓行程規劃不在困擾著你，既有的行程模式，景點、美食與住宿通通幫您想好。還想要來點驚喜的你，還有 TourCan 獨特的隨選行程，讓你隨時為自己準備一場精彩的旅程！
                    </p>
                    <a href="#" class="btn btn-dark">註冊/登入會員</a>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container -->
    </section>

    <!-- Services -->
    <!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->
    <section id="services" class="services front-bg-color">
        <div class="container">
            <div class="row text-center">
                <div class="col-lg-10 col-lg-offset-1">
                    <h2 class="dath7">服務項目</h2>
                    <hr class="small">
                    <div class="row">
                        <div class="col-md-2 col-sm-6">
                            <div class="service-item">
                                <span class="fa-stack fa-4x">
                                <a href="#"><img src="/Tourcan/images/front/icons/map-location.png"  class="front_icon"></a>
                            </span>
                                <h4>
                                    <strong class="dath7 frontIcon">景　　點</strong>
                                </h4>
                                <p class="dath7" style="text-align:justify;">好玩有趣的台灣景點一覽無遺，讓您輕鬆玩遍全台灣！</p>
<!--                                 <a href="#" class="btn btn-light">Learn More</a> -->
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-6">
                            <div class="service-item">
                                <span class="fa-stack fa-4x">
                                <a href="#"><img src="/Tourcan/images/front/icons/windows.png" class="front_icon"></a>
                            </span>
                                <h4>
                                    <strong class="dath7">住　　宿</strong>
                                </h4>
                                <p class="dath7" style="text-align:justify;">找一個小憩的美好空間，蓄滿精力再出發！</p>
<!--                                 <a href="#" class="btn btn-light">Learn More</a> -->
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-6">
                            <div class="service-item">
                                <span class="fa-stack fa-4x">
                                <a href="#"><img src="/Tourcan/images/front/icons/food.png" class="front_icon"></a>
                            </span>
                                <h4>
                                    <strong class="dath7" >美　　食</strong>
                                </h4>
                                <p class="dath7" style="text-align:justify;">台灣佳餚吃不完，藏身巷弄中的美食也不要錯過！</p>
<!--                                 <a href="#" class="btn btn-light">Learn More</a> -->
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-6">
                            <div class="service-item">
                                <span class="fa-stack fa-4x">
                               <a href="#"><img src="/Tourcan/images/front/icons/suitcase.png" class="front_icon"></a>
                            </span>
                                <h4>
                                     <strong class="dath7" >規劃行程</strong>
                                </h4>
                                <p class="dath7" style="text-align:justify;">美好的旅程自己描繪，現在就開始！</p>
<!--                                 <a href="#" class="btn btn-light">Learn More</a> -->
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-6">
                            <div class="service-item">
                                <span class="fa-stack fa-4x">
                               <a href="#"><img src="/Tourcan/images/front/icons/note.png" class="front_icon"></a>
                            </span>
                                <h4>
                                     <strong class="dath7" >討 論 區</strong>
                                </h4>
                                <p class="dath7" style="text-align:justify;">每個期待旅行的心情，分享給大家！</p>
<!--                                 <a href="#" class="btn btn-light">Learn More</a> -->
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-6">
                            <div class="service-item">
                                <span class="fa-stack fa-4x">
                               <a href="#"><img src="/Tourcan/images/front/icons/info.png" class="front_icon"></a>
                            </span>
                                <h4>
                                     <strong class="dath7" >客　　服</strong>
                                </h4>
                                <p class="dath7" style="text-align:justify;">您的每一個建議，都是我們進步的動力。</p>
<!--                                 <a href="#" class="btn btn-light">Learn More</a> -->
                            </div>
                        </div>
                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.col-lg-10 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container -->
    </section>


<!-- commercial slide -->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="/Tourcan/images/front/taiwan1.jpg">
      <div class="carousel-caption">
      </div>
    </div>
    <div class="item">
      <img src="/Tourcan/images/front/taiwan2.jpg">
      <div class="carousel-caption">
      </div>
    </div>
    <div class="item">
      <img src="/Tourcan/images/front/taiwan3.jpg">
      <div class="carousel-caption">
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>


    <!-- Callout -->
<!--     <aside class="callout"> -->
<!--         <div class="text-vertical-center frontText"> -->
<!--             <h1 class="dath7">Tourcan 熱門推薦</h1> -->
<!--         </div> -->
<!--     </aside> -->

    <!-- Portfolio -->
    <section id="portfolio" class="portfolio">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
                    <h2>Recommend</h2>
                    <hr class="small">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="portfolio-item">
                                <a href="#">
                                    <img class="img-portfolio img-responsive" src="/Tourcan/images/front/front-bamboo.jpg">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="portfolio-item">
                                <a href="#">
                                    <img class="img-portfolio img-responsive" src="/Tourcan/images/front/front-boats.jpg">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="portfolio-item">
                                <a href="#">
                                    <img class="img-portfolio img-responsive" src="/Tourcan/images/front/front-food.jpg">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="portfolio-item">
                                <a href="#">
                                    <img class="img-portfolio img-responsive" src="/Tourcan/images/front/front-kaohsiung.jpg">
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.col-lg-10 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container -->
    </section>

    <!-- Call to Action -->
    <aside class="call-to-action" style="background-color: rgb(173,93,93)">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3 style="color: white;">Tourcan makes you tour.</h3>
                </div>
            </div>
        </div>
    </aside>

    <!-- Map -->
    <section id="contact" class="map">
<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3615.0111684555704!2d121.54151376429307!3d25.03369504540593!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abd379a5ec97%3A0xec12a62666a81e3d!2zMTA25Y-w54Gj5Y-w5YyX5biC5aSn5a6J5Y2A5b6p6IiI5Y2X6Lev5LiA5q61Mzkw6Jmf!5e0!3m2!1szh-TW!2s!4v1474017157662" width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
    </section>

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
                    <h4><strong>Tourcan</strong>
                    </h4>
                    <p>台灣大安區
                        <br>復興南路一段390號</p>
                    <ul class="list-unstyled">
                        <li><i class="fa fa-phone fa-fw"></i> (02) 6631-6666</li>
                        <li><i class="fa fa-envelope-o fa-fw"></i> <a href="service@tourcan.com">service@tourcan.com</a>
                        </li>
                    </ul>
                    <br>
                    <ul class="list-inline">
                        <li>
                            <a href="#"><i class="fa fa-facebook fa-fw fa-3x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter fa-fw fa-3x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-dribbble fa-fw fa-3x"></i></a>
                        </li>
                    </ul>
                    <hr class="small">
                    <p class="text-muted">Copyright &copy; Tourcan 2016</p>
                </div>
            </div>
        </div>
        <a id="to-top" href="#top" class="btn btn-dark btn-lg"><i class="fa fa-chevron-up fa-fw fa-1x"></i></a>
    </footer>

    <!-- jQuery -->
<!--     <script src="/Tourcan/js/jquery.js"></script> -->

    <!-- Bootstrap Core JavaScript -->
<!--     <script src="/Tourcan/js/bootstrap.min.js"></script> -->

    <!-- Custom Theme JavaScript -->
    <script>
    // Closes the sidebar menu
    $("#menu-close").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });
    // Opens the sidebar menu
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });
    // Scrolls to the selected menu item on the page
    $(function() {
        $("a[href*='#']:not([href='#'],[data-toggle],[data-target],[data-slide])").click(function() {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
            }
        });
    });
    //#to-top button appears after scrolling
    var fixed = false;
    $(document).scroll(function() {
        if ($(this).scrollTop() > 250) {
            if (!fixed) {
                fixed = true;
                // $('#to-top').css({position:'fixed', display:'block'});
                $('#to-top').show("slow", function() {
                    $('#to-top').css({
                        position: 'fixed',
                        display: 'block'
                    });
                });
            }
        } else {
            if (fixed) {
                fixed = false;
                $('#to-top').hide("slow", function() {
                    $('#to-top').css({
                        display: 'none'
                    });
                });
            }
        }
    });
    // Disable Google Maps scrolling
    // See http://stackoverflow.com/a/25904582/1607849
    // Disable scroll zooming and bind back the click event
    var onMapMouseleaveHandler = function(event) {
        var that = $(this);
        that.on('click', onMapClickHandler);
        that.off('mouseleave', onMapMouseleaveHandler);
        that.find('iframe').css("pointer-events", "none");
    }
    
    var onMapClickHandler = function(event) {
            var that = $(this);
            // Disable the click handler until the user leaves the map area
            that.off('click', onMapClickHandler);
            // Enable scrolling zoom
            that.find('iframe').css("pointer-events", "auto");
            // Handle the mouse leave event
            that.on('mouseleave', onMapMouseleaveHandler);
        }
    
        // Enable map zooming with mouse scroll when the user clicks the map
    $('.map').on('click', onMapClickHandler);
    </script>

</body>

</html>
