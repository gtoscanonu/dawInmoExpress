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
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Inmueble</h1>
                    <p>Añadir Inmueble </p>
                <!--
                    <a href="<c:url value="/j_spring_security_logout" />" class="btn btndanger btn-mini pull-right">desconnectar</a>
                    <div class="pull-right" style="padding-right:50px">
                        <a href="?language=ca" >Català</a>|<a href="?language=en" >Anglès</a>
                    </div> -->
                </div>

            </div>
        </section>
        <section class="container">
            <form:form modelAttribute="newInmueble" class="form-horizontal" action="add" enctype="multipart/form-data"> <!--modelAttribute="newInmueble"-->
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
                        <label class="control-label col-lg-2 col-lg-2" for="numBanios">
                            Baños:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="numBanios" path="numBanios" type="text" class="form:input-large"/>
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
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="imagen">
                           Imagen2:
                        </label>
                        <div class="col-lg-10">
                            <form:input id="imagen" path="imagen" type="file" name="file" class="file"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" id="btnAdd" class="btn btn-primary"
                                   value ="Crear"/>
                        </div>
                    </div>   
                </fieldset>
            </form:form>
        </section>
    </body>
</html>
