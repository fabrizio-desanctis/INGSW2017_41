<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.IndexController"
    import="models.Evento"
    import="java.util.List"
    import="java.util.LinkedList"
     %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - EM '17</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="assets/css/Pretty-Search-Form.css">
</head>

<body>
	<% IndexController controller = new IndexController(); %>
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
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.html"><em>ACCEDI/REGISTRATI &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.html"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="shopping-cart.html">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li>
                </ul>
        </div>
        </div>
    </nav>
    <main class="page landing-page">
        <section class="clean-block features" style="margin:0px;padding:0px;">
            <form method="POST" action="SearchEvent2" class="search-form" style="margin:14px;">
                <div class="input-group">
                    <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-search"></i></span></div><input name=cerca class="form-control" type="text" placeholder="Cosa stai cercando?">
                    <div class="input-group-append"><button class="btn btn-primary" type="submit" style="background-color:rgb(59,153,224);">CERCA</button></div>
                </div>
            </form>
        </section>
        <section class="clean-block clean-hero" style="background-image:url(&quot;assets/img/crowd-at-concert.jpg&quot;);color:rgba(9, 162, 255, 0.85);">
            <div class="text">
                <h2><strong>EVENT MANAGER '17</strong></h2>
                <p>La miglior piattaforma per restare sempre aggiornato ed acquistare i biglietti di concerti,mostre,teatri,sport e tanto altro.</p><a class="btn btn-outline-light btn-lg" role="button" href="#primo">Perchè sceglierci</a></div>
        </section>
        
        
        <% List<Evento> eventi = new LinkedList<Evento>(); %>
        <% eventi = controller.getEventi();%>
        <% Evento e1,e2,e3,e4,e5; %>
        <% e1= eventi.get(0); %>
        <% e2= eventi.get(1); %>
        <% e3 = eventi.get(2); %>
        <% e4 = eventi.get(3); %>
        <% e5 = eventi.get(4); %>
      
        
        <section class="clean-block about-us">
            <div></div>
            <div class="container">
                <h2 class="text-center text-info" style="margin:22px;">Eventi più popolari</h2>
                <div class="row justify-content-center">
                    <div class="col-sm-6 col-lg-4">
                        <div class="card clean-card text-center"><img class="img-fluid card-img-top w-100 d-block" src="<% out.print(e1.getLinkImmagine()); %>">
                            <div class="card-body info"><a class="card-link" href="SearchEvent2?param=<%out.print(e1.getId());%>"><strong><% out.print(e1.getNome()); %></strong><br></a></div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-4">
                        <div class="card clean-card text-center"><img class="img-fluid card-img-top w-100 d-block" src="<% out.print(e2.getLinkImmagine()); %>">
                            <div class="card-body info"><a class="card-link" href="SearchEvent2?param=<%out.print(e2.getId());%>"><strong><% out.print(e2.getNome()); %></strong><br></a></div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-4">
                        <div class="card clean-card text-center"><img class="img-fluid card-img-top w-100 d-block" src="<% out.print(e3.getLinkImmagine()); %>">
                            <div class="card-body info"><a class="card-link" href="SearchEvent2?param=<%out.print(e3.getId());%>"><strong><% out.print(e3.getNome()); %></strong><br></a></div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-sm-6 col-lg-4">
                        <div class="card clean-card text-center"><img class="img-fluid card-img-top w-100 d-block" src="<% out.print(e4.getLinkImmagine()); %>">
                            <div class="card-body info"><a class="card-link" href="SearchEvent2?param=<%out.print(e4.getId());%>"><strong><% out.print(e4.getNome()); %></strong><br></a></div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-4">
                        <div class="card clean-card text-center"><img class="img-fluid card-img-top w-100 d-block" src="<% out.print(e5.getLinkImmagine()); %>">
                            <div class="card-body info"><a class="card-link" href="SearchEvent2?param=<%out.print(e5.getId());%>"><strong><% out.print(e5.getNome()); %></strong><br></a></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="clean-block features">
            <div class="container">
                <div class="block-heading"><a name="primo"></a>


                    <h2 class="text-info" name="primo">Features</h2>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-5 feature-box"><i class="icon-credit-card icon"></i>
                        <h4>Acquisti rapidi e sicuri con PayPal</h4>
                        <p>Compra in tutta tranquillità: possiamo proteggere i tuoi aquisti. &nbsp;Paga con carta o saldo PayPal. Al momento dell'acquisto basta inserire email e password. Al resto pensiamo noi. Il modo più sicuro di fare acquisti on-line.<br><br><br></p>
                    </div>
                    <div class="col-md-5 feature-box"><i class="icon-user icon"></i>
                        <h4>Area personale</h4>
                        <p>Accedi alla tua area personale per modificare i tuoi dati e tenere traccia di tutti i tuoi ordini in qualsiasi momento.</p>
                    </div>
                    <div class="col-md-5 feature-box"><i class="icon-screen-smartphone icon"></i>
                        <h4>Responsive</h4>
                        <p>Grazie al design responsive, il nostro sito web si adatta alle dimensioni di qualsiasi dispositivo o browser. Effettua acquisti in tutta tranquillità anche da mobile.</p>
                    </div>
                    <div class="col-md-5 feature-box"><i class="icon-frame icon"></i>
                        <h4>QR-Code</h4>
                        <p>Biglietto in formato digitale. Basta sprechi di carta e spese di spedzione. Potrai semplicemente fornire &nbsp;il QR-Code direttamente dal tuo smartphone &nbsp;al personale per poter entrare in qualsiasi evento.&nbsp;</p>
                    </div>
                    <div class="col-md-5 feature-box"><i class="icon-earphones-alt icon"></i>
                        <h4>Servizio Clienti</h4>
                        <p style="font-size:21px;padding:0px;margin:-2px;"><strong>06-******08</strong></p>
                        <p style="padding:0px;margin:0px;">Bisogno di aiuto? Il nostro servizio clienti è disponibile al numero verde 24 ore su 24.<br></p>
                    </div>
                </div>
            </div>
        </section>
        <section class="clean-block about-us">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col">
                        <h2 class="text-center text-info">I nostri social</h2>
                        <p class="text-center">Seguici sui nostri social network per non perderti nessuna novità o iniziativa.</p>
                        <div class="clean-block add-on social-icons" style="height:98px;">
                            <div class="icons"><a href="#"><i class="fa fa-facebook"></i></a><a href="#"><i class="fa fa-instagram"></i></a><a href="#"><i class="fa fa-twitter"></i></a></div>
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

</html>