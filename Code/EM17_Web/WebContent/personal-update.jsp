<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.LoginController"
    import="controller.PersonalController"
    import="models.User"
    import="java.text.SimpleDateFormat"
    import="java.util.Calendar"
    import="java.util.Date"
    import="controller.CarrelloController" %>
<!DOCTYPE html>
<html>
		<%LoginController logController = new LoginController(); %>
		<% PersonalController personController = new PersonalController();
		User myUser = null;
		myUser = (User) request.getAttribute("utente");%>
		
	<% if (logController.getAutenticato()  == true && myUser != null) { %>
	<% SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");%>
                <% String ago = dataFormat.format(myUser.getDataNascita()); %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiorna dati - EM '17</title>
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
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyInfo?param=<% out.print(logController.getIdUtente()); %>"><em>I miei dati/Logout &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li>
<% if(logController.getAutenticato()==false) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.jsp"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li> <% } %>
                    <% if(logController.getAutenticato()==true) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="Ordini?param=<% out.print(logController.getIdUtente()); %>"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li> <% } %>					<% CarrelloController cart=new CarrelloController(); if(cart.statusCart(logController.getIdUtente())==false){ %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<% out.print(logController.getIdUtente()); %>">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> <% } %>
                    <% if(cart.statusCart(logController.getIdUtente())==true){ %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<% out.print(logController.getIdUtente()); %>">CARRELLO(1) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> <% } %>             
                       </ul>
        </div>
        </div>
    </nav>
    <main class="page registration-page">
        <section class="clean-block clean-form dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">I miei dati</h2>
                    <p>In questa sezione puoi visualizzare e/o modificare le tue informazioni personali.<br></p>
                </div>
              
                <% Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -18);
                Date olderDate = cal.getTime();
                String older = dataFormat.format(olderDate);%>
                <form method="POST" action="ModifyUser"><label><strong>DATI ANAGRAFICI</strong></label>
                    <div class="form-group"><label for="name">Nome</label><input value="<%out.print(myUser.getNome());%>" style="text-transform:uppercase" pattern="[a-zA-Z'\s]{2,30}" autofocus required title="Sono ammessi solo caratteri alfabetici." placeholder="Inserire il nome." class="form-control item" type="text" inputmode="latin-name" name="nome"></div>
                    <div class="form-group"><label for="name">Cognome</label><input value="<%out.print(myUser.getCognome());%>" style="text-transform:uppercase" pattern="[a-zA-Z'\s]{2,30}" required title="Sono ammessi solo caratteri alfabetici." placeholder="Inserire il cognome." class="form-control item" type="text" inputmode="latin-name" name="cognome"></div>
                    <div class="form-group"><label for="name">Telefono/Cellulare</label><input value="<%out.print(myUser.getTelefono());%>" style="text-transform:uppercase"  minlength="10" maxlength="10" pattern="\d*" required title="Sono ammessi solo numeri (0-9). Il numero deve essere composto da 10 cifre." placeholder="Inserire recapito telefonico." class="form-control item" type="text" inputmode="tel" name="telefono"></div>
                    <% if(myUser.getSesso().equals("M")){ %>
                    <div class="form-group"><label for="name">Sesso</label><select  name="sessoM" class="form-control" style="width:82px;"><option value="M" selected="">M</option><option value="F">F</option></select></div> <% } %>
                    <% if(myUser.getSesso().equals("F")) { %>
                    <div class="form-group"><label for="name">Sesso</label><select  name="sessoF" class="form-control" style="width:82px;"><option value="M">M</option><option value="F" selected="">F</option></select></div> <% } %>
                    <div class="form-group"><label for="name">Data nascita</label><input name="data" required title="L'utente deve avere almeno 18 anni." class="form-control" type="date" value="<%out.print(ago);%>"max="<%out.print(older);%>"></div>
                    <div class="form-group"><label for="name">Indirizzo</label><input value="<%out.print(myUser.getIndirizzo());%>" style="text-transform:uppercase"  required title="Inserire l'indirizzo." placeholder="Inserire l'indirizzo." class="form-control item" type="text" inputmode="latin-name" name="cognome"></div>
                    <div class="form-group"><label for="name">Citt�</label><input value="<%out.print(myUser.getCitt�());%>" style="text-transform:uppercase" pattern="[a-zA-Z'\s]{2,30}" required title="Sono ammessi solo caratteri alfabetici." placeholder="Inserire una citt�." class="form-control item" type="text" inputmode="latin-name" id="citta" name="citta"></div>
                    <div class="form-group"><label for="name">Provincia</label><input value="<%out.print(myUser.getProvincia());%>" style="text-transform:uppercase"minlength="2" maxlength="2" pattern="[a-zA-Z'\s]{2,2}" required title="Sono ammessi solo caratteri alfabetici." placeholder="Inserire la provincia." class="form-control item" type="text" inputmode="latin-name" id="provincia" name="provincia"></div>
                    <div class="form-group"><label for="name">CAP</label><input value="<%out.print(myUser.getCap());%>" style="text-transform:uppercase" name="cap" minlength="5" maxlength="5" pattern="\d*" required title="Sono ammessi solo caratteri numerici." placeholder="Inserire il CAP." class="form-control item" type="text" inputmode="numeric" id="cap" name="cap"></div>
                    <button class="btn btn-primary btn-block" type="submit">Conferma modifiche</button>
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
            <p>� 2018 Copyright Text</p>
        </div>
    </footer>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>
<% } %>

<% if (logController.getAutenticato()==false || myUser==null){ %> 


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
<% if(logController.getAutenticato()==false) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.jsp"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li> <% } %>
                    <% if(logController.getAutenticato()==true) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="Ordini?param=<% out.print(logController.getIdUtente()); %>"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li> <% } %>                    <li class="nav-item" role="presentation"><a class="nav-link" href="shopping-cart.jsp">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li>
                </ul>
        </div>
        </div>
    </nav>
    <main class="page registration-page">
        <section class="clean-block clean-form dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">ACCESSO NEGATO</h2>
                    <p>Spiacente, non sei autorizzato a visualizzare il contenuto di questa pagina.</p>
                  
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
            <p>� 2018 Copyright Text</p>
        </div>
    </footer>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

<% } %>
</html>