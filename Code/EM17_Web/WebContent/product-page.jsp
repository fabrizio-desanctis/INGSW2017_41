<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.IndexController"
    import="models.Evento"
    import="java.util.List"
    import="java.util.LinkedList"
    import="java.text.SimpleDateFormat"
	import="java.util.Date"
	import="models.dao.concrete.MySQL.EventoMySQLDAO"
	import="models.dao.interfaces.EventoDAO"
	import="controller.LoginController"
	import="controller.CarrelloController"
	import="controller.ProductController" %>
<!DOCTYPE html>
<html>
<%
	CarrelloController cart = new CarrelloController();
	LoginController log = new LoginController();
	ProductController pro = new ProductController();
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product - EM '17</title>
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
                	<%
                		LoginController logController = new LoginController();
                	%>
					<%
						if(logController.getAutenticato()==false) {
					%>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.jsp"><em>ACCEDI/REGISTRATI &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <%
 	}
 %>
                    <%
                    	if(logController.getAutenticato()==true) {
                    %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyInfo?param=<%out.print(logController.getIdUtente());%>"><em>I miei dati/Logout &nbsp;</em><i class="fa fa-user-circle-o"></i></a></li> <%
 	}
 %>                    <% if(logController.getAutenticato()==false) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="login.jsp"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li> <% } %>
                    <% if(logController.getAutenticato()==true) { %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="Ordini?param=<% out.print(logController.getIdUtente()); %>"><em>I miei ordini &nbsp;</em><i class="fa fa-cloud"></i></a></li> <% } %>
					<%
						if(cart.statusCart(logController.getIdUtente())==false){
					%>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<%out.print(logController.getIdUtente());%>">CARRELLO(0) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> <%
 	}
 %>
                    <%
                    	if(cart.statusCart(logController.getIdUtente())==true){
                    %>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="MyCarrello?param=<%out.print(logController.getIdUtente());%>">CARRELLO(1) &nbsp;<i class="fa fa-shopping-cart"></i></a></li> <%
 	}
 %>                </ul>
        </div>
        </div>
    </nav>
    <main class="page product-page">
    	<%
    		List <Evento> list= new LinkedList <Evento>();
    	                                		list = (List<Evento>) request.getAttribute("eventi");
    	%>
                                		<%
                                			Evento e = list.get(0);   pro.setIdEvento(e.getId());
                                		%>
                                		<%
                                			SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                		                        				String dateFormat = dataFormat.format(e.getData());
                                		%>
        <section class="clean-block clean-product dark">
            <div class="container">
                <div class="block-content">
                    <div class="product-info">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="gallery" style="background-color:rgb(255,255,255);">
                                    <div class="sp-wrap"><img class="img-fluid d-block mx-auto" src="<%out.print(e.getLinkImmagine());%>"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="info">
                                
                                    <h3><%
                                                                    	out.print(e.getNome());
                                                                    %></h3>
                                    <div class="summary"><label><strong>DESCRIZIONE EVENTO</strong></label>
                                        <p><%
                                        	out.print(dateFormat);
                                        %></p>
                                        <p><%
                                        	out.print(e.getDescrizione());
                                        %></p>
                                    </div>
                                    
                                    <div class="form-group">
                                    <form method="POST" action="AddToCart" class="search-form">
                                        <div class="price"><label><strong>Prezzo biglietto:&nbsp;</strong></label>
                                            <h3 style="color:rgb(255,0,0);">EUR <%
                                            	out.print(e.getPrezzo());
                                            %></h3>
                                            
                                            <label><strong>Quantità</strong>:&nbsp;</label>
                                            <%
                                            	EventoDAO ev = new EventoMySQLDAO();
                                                                                        	Integer restanti = new Integer(ev.getRestanti(e.getId()));
                                                                                        if(restanti>5) {
                                            %>
                                            <input name="quantita" type="number" value="1" min="1" max="5" step="1" style="width:45px;"> <% } %>
                                            <% if(restanti<5 && restanti>0) {%>
                                            <input name="quantita" type="number" value="1" min="1" max="<%out.print(restanti); %>" step="1" style="width:45px;"> <% } %>
                                           	<% if(restanti==0) {%>
                                            <input disabled name="quantita" type="number" value="0" min="0" max="0" step="1" style="width:45px;"> <% } %>
                                            </div>
                                           	<%int utente = log.getIdUtente(); %>
                                           	<% if(cart.statusCart(utente)==false && logController.getAutenticato()==true && restanti>0 ){ %>
                                            <button class="btn btn-primary" type="submit"><i class="icon-basket"></i>Aggiungi al carrello</button><% } %>
                                            <% if(cart.statusCart(utente)==true || logController.getAutenticato()==false || restanti==0){ %>
                                            <button disabled class="btn btn-primary" type="submit"><i class="icon-basket"></i>Aggiungi al carrello</button>
                                            <% if(restanti==0){ %> <span style="color:red"><strong>SOLD OUT</strong></span> <% } %>
                                            <br>
                                            <% if(logController.getAutenticato()==false){ %> <span style="color:red"><strong>EFFETTUA IL LOGIN PER ACQUISTARE</strong></span> <% } %>
                                            
                                            	
                                            <% } %></div> 
                                            </form>
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
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>