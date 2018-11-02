<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gestione Portafoglio</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>Lista Titoli in Portafoglio</h2>	
	<a href="<c:url value='/new' />">Aggiungi titolo</a>
	<table>
		<tr>
			<td>Titolo</td><td>Quantità</td><td>Prezzo Acquisto</td><td>Valuta</td><td></td>
		</tr>
		<c:forEach items="${portafogli}" var="portafoglio">
			<tr>
			<td>${portafoglio.titolo.denominazione}</td>
			<td>${portafoglio.quantita}</td>
			<td>${portafoglio.prezzoAcquisto}</td>
			<td>${portafoglio.titolo.valuta.codice}</td>
			<td><a href="<c:url value='/edit-${portafoglio.portafoglioId}-portafoglio' />">${portafoglio.titolo.isin}</a></td>
			<td><a href="<c:url value='/delete-${portafoglio.portafoglioId}-portafoglio' />">Cancella</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/new' />">Aggiungi titolo</a>
</body>
</html>