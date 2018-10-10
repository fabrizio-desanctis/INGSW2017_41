<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.LoginController"
    import="controller.PersonalController"
    import="models.Evento"
    import="models.User"
    import="java.text.SimpleDateFormat"
    import="java.util.Calendar"
    import="java.util.Date"
    import="models.dao.interfaces.EventoDAO"
    import="models.dao.concrete.oracle.EventoOracleDAO"%>
<!DOCTYPE html>
<html>
	<%LoginController logController = new LoginController(); %>
	<% Evento e = (Evento) request.getAttribute("carrello");%>
	<% if (logController.getAutenticato()  == true && e != null) { %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart - EM '17</title>
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
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyInfo?param=<% out.print(logController.getIdUtente()); %>"><em>I miei dati/Logout &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <% } %>                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.html"><em>i miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<% out.print(logController.getIdUtente()); %>">CARRELLO(1) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> 
                </ul>
        </div>
        </div>
    </nav>
    <main class="page shopping-cart-page">
        <section class="clean-block clean-cart dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Carrello</h2>
                </div>
                <div class="content">
                    <div class="row no-gutters">
                        <div class="col-md-12 col-lg-8">
                            <div class="items">
                               <hr>
                                <hr>
                                <hr>
                               
                                <hr>
                                <div class="product">
                                    <div class="row justify-content-center align-items-center">
                                        <div class="col-md-3">
                                            <div class="product-image"><img class="img-fluid d-block mx-auto image" src="<%out.print(e.getLinkImmagine());%>"></div>
                                        </div>
                                        <div class="col-md-5 product-info">
                                            <h3><strong><% out.print(e.getNome()); %></strong></h3><label><strong>Prezzo</strong>:&nbsp;</label><span><% out.print(e.getPrezzo()); %></span><span> EUR</span>
                                            <div class="row">
                                                <div class="col"><label><strong>Quantità</strong>:&nbsp;</label><span><% out.print(e.getNumeroBiglietti()); %></span></div>
                                            </div>
                                            <div class="row">
                                              <div class="col"><a href="Delete?param=<% out.print(logController.getIdUtente()); %>">Rimuovi</a></div>
                                            </div>
                                        </div>
                                        <div class="col-6 col-md-2 quantity"></div>
                                    </div>
                                </div>
                                <hr>
                                <hr>
                                <hr>
                                <hr>
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-4">
                            <div class="summary">
                            <% float totale = e.getPrezzo()*e.getNumeroBiglietti();%>
                                <h3>RIEPILOGO</h3>
                                <h4><span class="text">Subtotal (EUR)</span> 
                                <div class="form-group"><input  style="text-transform:uppercase" value="<%out.print(totale); %>" class="form-control item" type="text" readonly inputmode="latin-name" name="nome"></div>
                                </h4>
                                <h4><span class="text">Altri costi (EUR)</span> 
                                <div class="form-group"><input  style="text-transform:uppercase" value="0" class="form-control item" type="text" readonly inputmode="latin-name" name="nome"></div>
                                </h4>
								<h4><span class="text">TOTALE (EUR)</span> 
                                <div class="form-group"><input  style="text-transform:uppercase" value="<%out.print(totale); %>" class="form-control item" type="text" readonly inputmode="latin-name" id="paypal"></div>
                                </h4>                                <br>
                                
<div align="center" id="paypal-button-container"></div>
<script src="https://www.paypalobjects.com/api/checkout.js"></script>
<script>

// Render the PayPal button
paypal.Button.render({
// Set your environment
env: 'sandbox', // sandbox | production

// Specify the style of the button
style: {
  layout: 'vertical',  // horizontal | vertical
  size:   'medium',    // medium | large | responsive
  shape:  'rect',      // pill | rect
  color:  'gold'       // gold | blue | silver | white | black
},

// Specify allowed and disallowed funding sources
//
// Options:
// - paypal.FUNDING.CARD
// - paypal.FUNDING.CREDIT
// - paypal.FUNDING.ELV
funding: {
  allowed: [
    paypal.FUNDING.CARD,
    paypal.FUNDING.CREDIT
  ],
  disallowed: []
},

// PayPal Client IDs - replace with your own
// Create a PayPal app: https://developer.paypal.com/developer/applications/create
client: {
  sandbox: 'AXHY_mumI2bkf5VqWpEOqYTeFNUrYMjlLuGW7Isbt7CCtlLLhVEryaf2x7ph1yVUm5x-83jFaI1Q457s',
  production: 'EH2dxTVJbF89-qs_cuMW2A58xGfR9xMpq4bzaWDLU6nx8riq3NmqU6GOePsgTPZUbeIXN47uH-vg0fvB'
},

payment: function (data, actions) {
	var txt = document.getElementById("paypal").value;
  return actions.payment.create({
    payment: {
      transactions: [
        {
          amount: {
            total: txt,
            currency: 'EUR'
          }
        }
      ]
    }
  });
},

onAuthorize: function (data, actions) {
     return actions.payment.execute()
    .then(function (res) {
    	if (res.error === 'INSTRUMENT_DECLINED') {
    		window.location.href = 'payment-failed.html';
          }
    	else {
    		var min=4; 
    	    var max=Number.MAX_SAFE_INTEGER;  
    	    var random = Math.random() * (+max - +min) + +min; 
    	    var str1 = "Checking?param=";
    	    var res = str1.concat(random);
    		window.location.href = res;
    	}
    	
    });
}
}, '#paypal-button-container');
</script>                      
                                
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


<% if (e==null){ %> 
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello vuoto  - EM '17</title>
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