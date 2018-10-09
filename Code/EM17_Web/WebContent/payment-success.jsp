<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="java.text.SimpleDateFormat"
	import="java.util.Date"
	import="java.util.Calendar"
	import="controller.LoginController"
	import="controller.CarrelloController"
	import="models.Ordine"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagamento completato - EM '17</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="assets/css/Pretty-Search-Form.css">
</head>
<%LoginController logController = new LoginController(); %>
	<% Ordine o = (Ordine) request.getAttribute("ordine");%>
	<% if (logController.getAutenticato()  == true && o != null) { %>
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
                	<li class="nav-item" role="presentation"><a class="nav-link" href="MyInfo?param=<% out.print(logController.getIdUtente()); %>"><em>I miei dati/Logout &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li>           
					<li class="nav-item" role="presentation"><a class="nav-link" href="login.html"><em>i miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li>
					
                    
					<% CarrelloController cart=new CarrelloController(); if(cart.statusCart(logController.getIdUtente())==false){ %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<% out.print(logController.getIdUtente()); %>">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> <% } %>
                    <% if(cart.statusCart(logController.getIdUtente())==true){ %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<% out.print(logController.getIdUtente()); %>">CARRELLO(1) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> <% } %> 
                </ul>
        </div>
        </div>
    </nav>
    <main class="page shopping-cart-page">
        <section class="clean-block clean-cart dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Pagamento effettuato con successo!</h2>
                </div>
                <div class="content">
                    <div class="row no-gutters">
                        <div class="col-md-12 col-lg-8">
                            <div class="items">
                                <div><label><strong>Codice ordine:&nbsp;</strong></label><label><%out.print(o.getId_ordine()); %></label></div>
                                <div><label><strong>Totale:&nbsp;</strong></label><label>&nbsp;<%out.print(o.getTotale()); %> EUR</label></div>
                                <hr>
                                <div class="product">
                                    <div class="row justify-content-center align-items-center">
                                        <div class="col-md-3">
                                            <div class="product-image"><img class="img-fluid d-block mx-auto image" src="assets/img/evento4.jpg"></div>
                                        </div>
                                        <div class="col-md-5 product-info">
                                            <h3><strong><%out.print(o.getNome_evento()); %></strong></h3><label><strong>Prezzo</strong>:&nbsp;</label><span><% out.print(o.getPrezzo()); %></span><label><strong>&nbsp; &nbsp;Quantità:&nbsp;</strong></label><label><%out.print(o.getQuantita()); %></label></div>
                                        <div class="col-6 col-md-2 quantity"></div>
                                    </div>
                                </div>
                                <hr>
                               
                                <div style="font-size:21px;"><a href="<%out.print(o.getPdf());%>"><strong>Per visualizzare il QR-Code ed i dettagli, scarica il PDF &nbsp;</strong><i class="fa fa-file-pdf-o"></i></a></div>
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
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>
 <% } %>
 
 <% if (logController.getAutenticato()==false){ %> 
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accesso negato  - EM '17</title>
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
                   <% if(logController.getAutenticato()==false) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.jsp"><em>ACCEDI/REGISTRATI &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <% } %>
                    <% if(logController.getAutenticato()==true) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyInfo?param=<% out.print(logController.getIdUtente()); %>"><em>I miei dati/Logout &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <% } %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.html"><em>i miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="shopping-cart.jsp">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li>
                </ul>
        </div>
        </div>
    </nav>
    <main class="page registration-page">
        <section class="clean-block clean-form dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">ACCESSO NEGATO</h2>
                    <p>Spiacente, non sei autorizzato a visualizzare il contenuto di questa pagina. <a href="login.jsp">Clicca qui</a> per effettuare il login.</p>
                  	<p></p>
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
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>
<% } %>


<% if (o==null){ %> 
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Errore  - EM '17</title>
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
                   <% if(logController.getAutenticato()==false) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.jsp"><em>ACCEDI/REGISTRATI &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <% } %>
                    <% if(logController.getAutenticato()==true) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyInfo?param=<% out.print(logController.getIdUtente()); %>"><em>I miei dati/Logout &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <% } %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.html"><em>i miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<% out.print(logController.getIdUtente()); %>">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> 
                </ul>
        </div>
        </div>
    </nav>
    <main class="page registration-page">
        <section class="clean-block clean-form dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Carrello</h2>
                    <p>NESSUN ELEMENTO PRESENTE NEL CARRELLO.</p>
                  	<p></p>
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
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>
<% } %>

</html>