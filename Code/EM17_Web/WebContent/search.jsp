<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.IndexController"
    import="models.Evento"
    import="java.util.List"
    import="java.util.LinkedList"
    import="java.text.SimpleDateFormat"
	import="java.util.Date"
	import="controller.LoginController"
	import="controller.CarrelloController" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eventi - EM '17</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="assets/css/Pretty-Search-Form.css">
</head>

<body>
    <nav class="navbar navbar-light navbar-expand-lg bg-white clean-navbar">
        <div class="container"><a class="navbar-brand logo" href="index.jsp" style="font-size:54px;padding:0px;margin:-16px;"><strong>EM'17</strong></a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div
                class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link" href="SearchEvent2?param=Concerti">CONCERTI</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="SearchEvent2?param=Spettacolo">SPETTACOLO</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="SearchEvent2?param=Sport">SPORT</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="SearchEvent2?param=Cultura">CULTURA</a></li>
                </ul>
                <ul class="nav navbar-nav ml-auto">
                	<%LoginController logController = new LoginController(); %>
                    <% if(logController.getAutenticato()==false) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.jsp"><em>ACCEDI/REGISTRATI &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <% } %>
                    <% if(logController.getAutenticato()==true) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyInfo?param=<% out.print(logController.getIdUtente()); %>"><em>I miei dati/Logout &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <% } %>
<% if(logController.getAutenticato()==false) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.jsp"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li> <% } %>
                    <% if(logController.getAutenticato()==true) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="Ordini?param=<% out.print(logController.getIdUtente()); %>"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li> <% } %>                    <% CarrelloController cart= new CarrelloController();
                    if(cart.statusCart(logController.getIdUtente())==false){ %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<% out.print(logController.getIdUtente()); %>">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> <% } %>
                    <% if(cart.statusCart(logController.getIdUtente())==true){ %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<% out.print(logController.getIdUtente()); %>">CARRELLO(1) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> <% } %>
                </ul>
        </div>
        </div>
    </nav>
    <main class="page search page">
        <section>
            <form method="POST" action="SearchEvent2" class="search-form" style="margin:14px;">
                <div class="input-group">
                    <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-search"></i></span></div><input name=cerca autofocus class="form-control" type="text" placeholder="Cosa stai cercando?">
                    <div class="input-group-append"><button class="btn btn-primary" type="submit" style="background-color:rgb(59,153,224);">CERCA</button></div>
                </div>
            </form>
        </section>
        <section class="clean-block clean-cart dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Ricerca</h2>
                </div>
                <div class="content">
                    <div class="row no-gutters">
                        <div class="col-md-12 col-lg-8">
                            <div class="items">
                                <div class="product">
                                	<% List <Evento> list= new LinkedList <Evento>();
                                		list = (List<Evento>) request.getAttribute("eventi"); %>
                                		<% for( Evento e : list) { %>
                                		<% SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        				String dateFormat = dataFormat.format(e.getData()); %>
                                    <div class="row justify-content-center align-items-center">
                                        <div class="col-md-3">
                                            <div class="product-image"><img class="img-fluid d-block mx-auto image" src="<% out.print(e.getLinkImmagine());%>"></div>
                                        </div>
                                        <div class="col-md-5 product-info">
                                            <div class="row">
                                                <div class="col"><a href="SearchEvent2?param=<%out.print(e.getId());%>" style="font-size:20px;"><strong><% out.print(e.getNome());%></strong></a></div>
                                            </div>
                                            <div class="row">
                                                <div class="col"><label class="col-form-label"><strong>Data:&nbsp;</strong><span><% out.print(dateFormat);%></span></label></div>
                                            </div>
                                        </div>
                                       
                                    </div>
                                    <hr>
                                    <hr>
                                    <%  } %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <footer class="page-footer dark">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <h5>Get started</h5>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Sign up</a></li>
                        <li><a href="#">Downloads</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>About us</h5>
                    <ul>
                        <li><a href="#">Company Information</a></li>
                        <li><a href="#">Contact us</a></li>
                        <li><a href="#">Reviews</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>Support</h5>
                    <ul>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">Help desk</a></li>
                        <li><a href="#">Forums</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>Legal</h5>
                    <ul>
                        <li><a href="#">Terms of Service</a></li>
                        <li><a href="#">Terms of Use</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <p>© 2018 Copyright Text</p>
        </div>
    </footer>
    <section class="clean-block features" style="margin:0px;padding:0px;">
        <form class="search-form" style="margin:14px;">
            <div class="input-group">
                <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-search"></i></span></div><input class="form-control" type="text" placeholder="Cosa stai cercando?">
                <div class="input-group-append"><button class="btn btn-primary" type="button" style="background-color:rgb(59,153,224);">CERCA</button></div>
            </div>
        </form>
    </section>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>