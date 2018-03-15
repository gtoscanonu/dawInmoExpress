<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>        
        <title>Dispositiu</title>
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
                                <a href="<spring:url value= '/dispositius/all'/>">
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
        <section class="container">
            <h1>Añadir/Modificar Inmueble</h1>
            <section class="container">
            <form:form modelAttribute="formInmueble" class="form-horizontal" action="inmueble/update">
                <fieldset>
                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="tipo">
                            Tipo Inmueble:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="tipo" path="tipo" type="text" class="form:input-large"/>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="precio">
                            Precio:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="precio" path="precio" type="text" class="form:input-large"/>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="superficie">
                            Superficie:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="superficie" path="superficie" type="text" class="form:input-large"/>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="ubicacion">
                            Ubicación:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="ubicacion" path="ubicacion" type="text" class="form:input-large"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="numHabitaciones">
                            Habitaciones:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="numHabitaciones" path="numHabitaciones" type="text" class="form:input-large"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="numBaños">
                            Baños:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="numBaños" path="numBaños" type="text" class="form:input-large"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="extras">
                            Extras:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="extras" path="extras" type="text" class="form:input-large"/>
                        </div>
                    </div>
                      <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="descripcion">
                            Descripción:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="descripcion" path="descripcion" type="text" class="form:input-large"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="idVivienda">
                            IdVivienda:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="idVivienda" path="idVivienda" type="text" class="form:input-large"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" id="btnAdd" class="btn btn-primary"
                                   value ="Desar"/>
                        </div>
                    </div>
                    
                </fieldset>
            </form:form>
        </section>
        </section>
    </body>
</html>
