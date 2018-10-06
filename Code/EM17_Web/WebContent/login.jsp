<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.IndexController"
    import="models.Evento"
    import="java.util.List"
    import="java.util.LinkedList"
    import="controller.LoginController"
    import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Brand</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="assets/css/Pretty-Search-Form.css">
</head>

<body>
	<% LoginController logController = new LoginController();
	    ArrayList<Integer> error = new ArrayList<Integer>(2);%>
	<% error = (ArrayList<Integer>) request.getAttribute("errore"); %>
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
                    <li class="nav-item" role="presentation"><a class="nav-link" href="dati.jsp"><em>I miei dati &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <% } %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href=""><em>i miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="shopping-cart.html">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li>
                </ul>
        </div>
        </div>
    </nav>
    <main class="page login-page">
        <section class="clean-block clean-form dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Accedi alla tua area personale</h2>
                    <p>Effettua il log in per accedere alla tua area personale. Potrai aggiungere nuovi evento al carrello, scaricare i tuoi biglietti e molto altro ancora.</p>
                </div>
                <form method="POST" action="Login">
                    <div class="form-group"><label for="email">Indirizzo e-mail</label><input name="user" class="form-control item" type="text" id="email"></div>
                 	
                    <div class="form-group"><label for="password">Password</label><input name="password" class="form-control" type="password" id="password"></div>
                     <% if(error != null && error.get(0)==1) {  %>   
                    <i class="fa fa-exclamation-triangle" style="color:rgb(255,0,0);"></i>
                    <label style="color:rgb(255,0,0);"><strong>Password errata.</strong></label> <% } %>
                    <% if(error != null && error.get(1)==1) {  %>   
                    <i class="fa fa-exclamation-triangle" style="color:rgb(255,0,0);"></i>
                    <label style="color:rgb(255,0,0);"><strong>Utente non trovato.</strong></label> <% } %>
                    <button class="btn btn-primary btn-block" type="submit">Log In</button><label style="margin:12px;">Non sei registrato?</label>
                    
                    <a href="registration.html">Clicca qui</a>
                    
                </form>
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

</html>