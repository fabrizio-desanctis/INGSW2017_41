<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.IndexController"
    import="models.Evento"
    import="java.util.TreeSet"
    import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<% IndexController controller = new IndexController(); %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EM'17 - Home Page</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/user.css">
</head>

<body style="">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header"><a class="navbar-brand navbar-link" href="#">EM'17 </a>
                <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1"></button>
            </div>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav">
                    <li role="presentation"><a href="#">Concerti </a></li>
                    <li role="presentation"><a href="#">Spettacolo </a></li>
                    <li role="presentation"><a href="#">Sport </a></li>
                    <li role="presentation"><a href="#">Cultura </a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li role="presentation"><a href="#">Accedi </a></li>
                    <li role="presentation"><a href="#">Registrati </a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid" style="">
    	
        <div class="row">
        <form method="POST" action="SearchEvent.do">
            <div class="col-sm-2" style="width: 150px">
            
                <select class="input-sm" name="tipologia" style="height: 40px">
                    <optgroup label="Tipologia">
                        <option value="Concerti">Concerti</option>
                        <option value="Sport">Sport</option>
                        <option value="Musei">Musei</option>
                        <option value="Teatro">Teatro</option>
                        <option value="Eventi">Eventi</option>
                        <option value="Cinema">Cinema</option>
                    </optgroup>
                </select>
            </div>
            <div class="col-md-2 col-sm-2" style="width: 200px">
                <select class="input-sm" name="località" style="height: 40px">
                    <optgroup label="Località">
                    <% TreeSet<String> list = controller.ListaLocalita();
                    	for (String x : list) { %>
                       	 <option value="<% out.print(x); %>" ><% out.print(x); %></option>
                       	 <% } %>
                    </optgroup>
                </select>
            </div>
            <div class="col-md-2" style="width: 530px">
                <input class="input-sm" name="cerca" type="search" placeholder="Cerca" style="height: 40px; width: 540px">
            </div>
            <div class="col-md-offset-0 col-sm-2" style="width: 290px">
                <button class="btn btn-default" type="submit" style="width: 100px">Cerca </button>
            </div>
            </form>
            <form method="POST" action="getCarrello.do">
            <div class="col-md-offset-0 col-sm-2" style="width: 160px">
                <button class="btn btn-default" type="submit" style="width: 150px">Carrello </button>
             </div>
            </form>
        </div>
        
        <hr size="1">
        <div class="row product" style="background: #222F41;">
            <div class="col-lg-1" style="width:320px">
            <form method="POST" action="CreateUser.do">
            <input type="image" src="assets/img/Immagine.png" value="evento1" class="img-rounded" src="assets/img/Immagine.png" alt="" style="width: 320px; height: 277px;" > 
            </form>
            </div>
            <div class="col-lg-6 col-lg-offset-0 col-md-12" style="width:675px; height: 277px;">
                <form method="POST" action="CreateUser.do">
                <div class="carousel slide" data-ride="carousel" id="carousel-1" style="width:675px; height: 277px;">
                    <div class="carousel-inner" role="listbox">
                    	<%ArrayList<Evento> img = controller.getEventiSport(); %>
                    	<% Evento x = img.remove(0);%>
                        <div class="item active"><input type="image" class="img-rounded" src="<% out.print(x.getLinkImmagine()); %>"  value="<% out.print(x.getId()); %>" alt="Slide Image" style="width:675px; height: 277px;"></div>
                       	<% x = img.remove(0);%>
                        <div class="item"><input type="image" src="<% out.print(x.getLinkImmagine()); %>"  value="<% out.print(x.getId()); %>" alt="Slide Image" style="width:675px; height: 277px;"></div>
                   	</div>
                    <div><a class="left carousel-control" href="#carousel-1" role="button" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i><span class="sr-only">Previous</span></a><a class="right carousel-control" href="#carousel-1" role="button"
                        data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i><span class="sr-only">Next</span></a></div>
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-1" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-1" data-slide-to="1"></li>
                    </ol>
                </div></form>
            </div>
            
            <div class="col-lg-1" style="width:320px">
            <form method="POST" action="CreateUser.do">
            <input type="image" src="assets/img/Immagine.png" value="evento1" class="img-rounded" src="assets/img/Immagine.png" alt="" style="width: 320px; height: 277px;" > 
            </form>
            </div>
        </div>
        <hr size="1">
        <div class="col-md-4" style="width: 320px;">
            <div class="row">
                <div class="col-md-12 col-md-offset-0 col-md-pull-0" style="width: 251px; height: 257px;"><img class="img-rounded" src="assets/img/banner_ assistenza ita nuovo(8).png" style="width: 251px; height: 257px;"></div>
            </div>
        </div>
        <div class="col-md-2 col-md-offset-0" style="height: 307px;">
            <div class="row" style="width: 675px">
                <div class="col-md-12" style="width: 900px;">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr></tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="width: 150px; height: 200px;"> <img class="img-rounded" src="assets/img/1.JPG" style="width: 150px; height: 200px;"></td>
                                    <td style="width: 150px; height: 200px;"> <img class="img-rounded" src="assets/img/1.JPG" style="width: 150px; height: 200px;"></td>
                                    <td style="width: 150px; height: 200px;"> <img class="img-rounded" src="assets/img/1.JPG" style="width: 150px; height: 200px;"></td>
                                    <td style="width: 150px; height: 200px;"> <img class="img-rounded" src="assets/img/1.JPG" style="width: 150px; height: 200px;"></td>
                                    <td style="width: 150px; height: 200px;"> <img class="img-rounded" src="assets/img/1.JPG" style="width: 150px; height: 200px;"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row" style="width: 900px;">
                <div class="col-md-12" style="width: 900px;"><img class="img-rounded" src="assets/img/metodi-di-pagamento.png" style="width:860px; height: 100px;"></div>
            </div>
        </div>
    </div>
    <footer class="site-footer">
        <hr border-color="black">
        <div class="container" style="">
            <div class="row">
                <div class="col-lg-2 col-md-12" style="width:145px;">
                    <p class="text-left"><strong>Seguici sui nostri social...</strong></p>
                </div>
                <div class="col-lg-11 col-xs-12 social-icons" style="width: 150px;"><a href="#"><i class="fa fa-facebook"></i></a><a href="#"><i class="fa fa-twitter"></i></a><a href="#"><i class="fa fa-instagram"></i></a></div>
            </div>
        </div>
    </footer>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>