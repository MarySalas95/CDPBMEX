<%-- 
    Document   : Index
    Created on : 17 ene 2024, 13:37:47
    Author     : msalas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Bellota CDP 2.0</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible"  content="IE=EDGE, IE=11">

        <!-- ESTILOS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="css/libre.css">
        <link rel="stylesheet" href="css/jquery.dataTables.css">

        <!-- JAVASCRIPT -->
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/jquery-3.5.1.js"></script>
        <script src="js/jquery.dataTables.js"></script>
        <script src="js/jquery-3.2.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    </head>
    <body>
        
        <c:if test="${sessionScope.usuario==null}">
            <c:redirect url="../"/>
        </c:if>

        <div class="wrapper">
            <div class="content-wrapper">

                <!-- Barra de navegacion con bootstrap -->
                <nav class="navbar sticky-top navbar-dark bg-dark">
                    <div class="container">
                        <a class="navbar-brand" href="#">
                            <img src="img/logo.png" alt="Logotipo bellota" height="36">
                        </a>
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item dropdown">
                                <a class="nav-link" href="Exit" id="navbarDropdown" role="button">
                                    <i class="bi bi-box-arrow-right"></i> Cerrar Sesión
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <!-- Barra de navegacion con bootstrap -->

                <div class="content-header">
                    <!-- Filtros de Complementos -->
                    <div class="card">
                        <div class="card-header"> <b>Consulta de Complementos:</b> </div>
                        <div class="card-body">
                            <form action="Inicio" method="post" class="form-inline" name="formFiltro">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="input-group input-group-sm mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-sm">Fecha de Inicio</span>
                                            </div>
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${fechaI}" var="fechaInicial"/>
                                            <input type="date" id="fechaInicio" name="fechaInicio" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" style="text-align: center" value="${fechaInicial}" required>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group input-group-sm mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-sm">Fecha Final</span>
                                            </div>
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${fechaF}" var="fechaFinal"/>
                                            <input type="date" id="fechaFin" name="fechaFin" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" style="text-align: center" value="${fechaFinal}" required>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <button class="btn btn-danger" type="submit">
                                            <i class="bi bi-search"></i> Consultar
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- Filtros de Complementos -->
                </div>

                <br>

                <!-- Seccion de Respuesta -->
                <div class="card">
                    <div class="card-header"> <b>Respuesta de Consulta:</b> </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="table-dark">
                                    <tr>
                                        <th style="text-align: center">Fecha</th>
                                        <th style="text-align: center">No. Cliente</th>
                                        <th style="text-align: center">Cliente</th>
                                        <th style="text-align: center">Folio</th>
                                        <th style="text-align: center">Transaccion</th>
                                        <th style="text-align: center">Banco</th>
                                        <th style="text-align: center">Cuenta</th>
                                        <th style="text-align: center">RFC</th>
                                        <th style="text-align: center">Estatus</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Respuesta Cabecera -->
                                    <c:forEach items="${cabecera}" var="cabecera" varStatus="loop">
                                        <tr class="table-primary">
                                            <th style="text-align: center">${cabecera.fecha}</th>
                                            <th style="text-align: center">${cabecera.cliente}</th>
                                            <th style="text-align: center">${cabecera.lsCliente}</th>
                                            <th style="text-align: center">${cabecera.folio}</th>
                                            <th style="text-align: center">${cabecera.transaccion}</th>

                                            <c:if test="${cabecera.lsMensaje1=='01'}">
                                                <th colspan="3" style="text-align: center">
                                                    <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#cuentas${cabecera.transaccion}">
                                                        <i class="bi bi-credit-card"></i>
                                                        01: Multiples Cuentas Bancarias
                                                    </button>

                                                    <div class="modal fade" id="cuentas${cabecera.transaccion}">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">

                                                                <!-- cabecera del diálogo -->
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title">Seleccionar Cuenta Bancaria </h5>
                                                                </div>

                                                                <!-- cuerpo del diálogo -->
                                                                <div class="modal-body">
                                                                    <form action="ActualizarCtaBancaria"  method="post" class="form-inline">
                                                                        CDP ${cabecera.folio} 
                                                                        <div class="form-group">
                                                                            <div class="input-group input-group-sm mb-3">
                                                                                <div class="input-group-prepend">
                                                                                    <span class="input-group-text" id="inputGroup-sizing-sm">Inf. Bancaria</span>
                                                                                </div>
                                                                                <input type="hidden" id="transaccion" name="transaccion" value="${cabecera.transaccion}">
                                                                                <input type="hidden" id="fechaI" name="fechaI" value="${fechaInicial}">
                                                                                <input type="hidden" id="fechaF" name="fechaF" value="${fechaFinal}">
                                                                                <input type="hidden" id="msg" name="msg" value="${cabecera.lsMensaje}">
                                                                                <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="ctaBancaria${cabecera.transaccion}" required>
                                                                                    <option value="" selected>Seleccione informacion bancaria</option>
                                                                                    <c:forEach items="${bancos}" var="bancos" varStatus="loop">
                                                                                        <c:if test="${bancos.cliente==cabecera.cliente}">
                                                                                            <option value="${bancos.RFC}_${bancos.banco}_${bancos.ctaOrdenante}">${bancos.RFC}--${bancos.banco}--${bancos.ctaOrdenante}</option>
                                                                                        </c:if>
                                                                                    </c:forEach>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                        <!-- pie del diálogo -->
                                                                        <div class="modal-footer">
                                                                            <button type="submit" class="btn btn-primary">Actualizar</button>
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                                        </div>
                                                                    </form>
                                                                </div>



                                                            </div>
                                                        </div>
                                                    </div>

                                                </th>
                                            </c:if>

                                            <c:if test="${cabecera.lsMensaje3=='03'}">
                                                <th colspan="3" style="text-align: center">
                                                    <span style=" color: #FC3004;">
                                                        <i class="bi bi-x-circle"></i>
                                                        03: Sin Cuentas Bancarias Registradas
                                                    </span>
                                                </th>
                                            </c:if>

                                            <c:if test="${cabecera.lsMensaje1!='01' && cabecera.lsMensaje3!='03'}">
                                                <th style="text-align: center">${cabecera.lsBanco}</th>
                                                <th style="text-align: center">${cabecera.lsCuenta}</th>
                                                <th style="text-align: center">${cabecera.lsRFCBanco}</th>
                                                </c:if>

                                            <th style="text-align: center">
                                                <c:if test="${cabecera.lsMensaje=='' && cabecera.lsTXT=='SI'}">
                                                    <span style=" color: #52BE80;"><i class="bi bi-file-earmark-check-fill"></i></span> Timbrado
                                                    </c:if>
                                            </th>

                                        </tr>
                                        <!-- Respuesta Cabecera -->
                                        <!-- Respuesta Detalle -->
                                        <tr>
                                            <td colspan="9">
                                                <table class="table table-bordered">
                                                    <thead class="table-light">
                                                        <tr>
                                                            <th style="text-align: center">Factura</th>
                                                            <th style="text-align: center">Parcialidad</th>
                                                            <th style="text-align: center">Saldo Anterior</th>
                                                            <th style="text-align: center">Pago</th>
                                                            <th style="text-align: center">Nvo. Saldo</th>
                                                            <th style="text-align: center">Moneda</th>
                                                            <th style="text-align: center">Mensaje</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:set var="totalPago" value="${0}"/>
                                                        <c:forEach items="${detalle}" var="detalle" varStatus="loop">
                                                            <c:if test="${cabecera.transaccion==detalle.transaccion}">
                                                                <tr>
                                                                    <td style="text-align: center">${detalle.numFactura}</td>
                                                                    <td style="text-align: center">${detalle.parcialidad}</td>
                                                                    <td style="text-align: right"><fmt:formatNumber type="number" groupingUsed="true" value="${detalle.ldSaldoAnterior}"/></td>
                                                                    <td style="text-align: right">
                                                                        <fmt:formatNumber type="number" groupingUsed="true" value="${detalle.ldMontoPagado}"/>
                                                                        <c:set var="totalPago" value="${totalPago + detalle.ldMontoPagado}" />
                                                                    </td>
                                                                    <td style="text-align: right"><fmt:formatNumber type="number" groupingUsed="true" value="${detalle.ldNvoSaldo}"/></td>
                                                                    <td style="text-align: center">${detalle.lsMoneda}</td>

                                                                    <c:if test="${detalle.lsMensaje2=='02'}">
                                                                        <td style="text-align: center">
                                                                            <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#dialogo${detalle.numFactura}">
                                                                                <i class="bi bi-pencil-square"></i>
                                                                                02: Falta Capturar el UUID
                                                                            </button>

                                                                            <div class="modal fade" id="dialogo${detalle.numFactura}">
                                                                                <div class="modal-dialog">
                                                                                    <div class="modal-content">

                                                                                        <!-- cabecera del diálogo -->
                                                                                        <div class="modal-header">
                                                                                            <h5 class="modal-title">Registrar UUID </h5>
                                                                                        </div>

                                                                                        <!-- cuerpo del diálogo -->
                                                                                        <div class="modal-body">
                                                                                            <form action="ActualizarUUID"  method="post" class="form-inline" name="actualizarForm">
                                                                                                <div class="form-group">
                                                                                                    <label for="uuid" class="col-form-label">Factura ${detalle.numFactura} UUID: </label>
                                                                                                    <input type="text" class="form-control" id="uuid" name="uuid" maxlength="36">
                                                                                                    <input type="hidden" id="factura" name="factura" value="${detalle.numFactura}">
                                                                                                    <input type="hidden" id="prefijo" name="prefijo" value="${detalle.lsPrefijo}">
                                                                                                    <input type="hidden" id="mensaje" name="mensaje" value="${detalle.lsMensaje}">
                                                                                                    <input type="hidden" id="transaccion" name="transaccion" value="${detalle.transaccion}">
                                                                                                    <input type="hidden" id="fechaI" name="fechaI" value="${fechaInicial}">
                                                                                                    <input type="hidden" id="fechaF" name="fechaF" value="${fechaFinal}">
                                                                                                </div>
                                                                                                <!-- pie del diálogo -->
                                                                                                <div class="modal-footer">
                                                                                                    <button type="submit" class="btn btn-primary">Registrar</button>
                                                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                                                                </div>
                                                                                            </form>
                                                                                        </div>



                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                        </td>
                                                                    </c:if>
                                                                    <c:if test="${detalle.lsMensaje2!='02'}">
                                                                        <td style="text-align: center">${detalle.lsMensaje2}</td>
                                                                    </c:if>
                                                                </tr>
                                                                <!-- Respuesta Detalle -->
                                                            </c:if>
                                                        </c:forEach>
                                                        <tr class="table-light">
                                                            <td colspan="3" style="text-align: right"><b>Total Pago:</b></td>
                                                            <td style="text-align: right"><b><fmt:formatNumber type="number" groupingUsed="true" value="${totalPago}"/></b></td>
                                                            <td colspan="6"></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>

            window.onload = function () {
                if ('${respuesta}' !== "") {
                    const Toast = Swal.mixin({
                        toast: true,
                        position: "top-end",
                        showConfirmButton: false,
                        timer: 2000,
                        timerProgressBar: true,
                        didOpen: (toast) => {
                            toast.onmouseenter = Swal.stopTimer;
                            toast.onmouseleave = Swal.resumeTimer;
                        }
                    });
                    Toast.fire({
                        icon: "success",
                        title: "Información actualizada correctamente"
                    });
            <c:set var="respuesta" scope="session" value=""/>
                }
            };

        </script>

    </body>
</html>
