<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gestione Titoli</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>Lista Titoli</h2>	
	<a href="<c:url value='/titolo/new' />">Aggiungi titolo</a>
	<table>
		<tr>
			<td>Titolo</td><td>Isin</td><td>Mercato</td><td>Valuta</td><td></td>
		</tr>
		<c:forEach items="${titoli}" var="titolo">
			<tr>
			<td>${titolo.denominazione}</td>
			<td>${titolo.isin}</td>
			<td>${titolo.mercato.nome}</td>
			<td>${titolo.valuta.nome}</td>
			<td><a href="<c:url value='/titolo/edit-${titolo.isin}-titolo' />">${titolo.isin}</a></td>
			<td><a href="<c:url value='/titolo/delete-${titolo.isin}-titolo' />">Cancella</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/titolo/new' />">Aggiungi titolo</a>
</body>
</html>