<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ca">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>Dispositius</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-8"></div>
                <div class="col-md-4">  
                    <nav>
                        <ul class="nav nav-pills">
                            <li role="presentation" class="active">
                                <a href="<spring:url value= '/'/>">
                                    Inici
                                </a>
                            </li>
                            <li role="presentation" class="active">
                                <a href="<spring:url value= '/vendedores/all'/>">
                                    Llista de dispositius
                                </a>
                            </li>
                            <li role="presentation" class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                    Nou dispositiu <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="<spring:url value= '/dispositius/dispositiu?codi=&tipus=Llum' />">
                                            Llum
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<spring:url value= '/dispositius/dispositiu?codi=&tipus=Dimmer' />">
                                            Dimmer
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<spring:url value= '/dispositius/dispositiu?codi=&tipus=Persiana' />">
                                            Persiana
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<spring:url value= '/dispositius/dispositiu?codi=&tipus=Tendal' />">
                                            Tendal
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>    
                    </nav>
                </div>
            </div>
        </div>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Vendedores</h1>
                    <p>Lista de Vendedores</p>
                </div>
            </div>
        </section>
        <section class="container">
            <div class="row">
                <c:forEach items="${vendedores}" var="vendedor">
                    <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>${vendedor.nombre}</h3>
                                <p>${vendedor.email}</p>
                                <p>${vendedor.telefono}</p>
                                <p>${vendedor.idVendedor}</p>
                                <p>
                                    <a href=" <spring:url value= '/vendedor/${vendedor.idVendedor}' /> " class="btn btn-primary">
                                        <span class="glyphicon-info-sign glyphicon"/></span> Inmuebles
                                    </a>
                                    <a href=" <spring:url value= '/vendedores/${vendedor.idVendedor}/delete' /> " class="btn btn-primary">
                                        <span class="glyphicon-info-sign glyphicon"/></span> Eliminar
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </body>
</html>
