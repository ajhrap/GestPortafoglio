<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Censimento Portafoglio</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>Inserimento titolo</h2>
 
	<form:form method="POST" modelAttribute="titolo">
		<form:input type="hidden" path="titoloId" id="titoloId"/>
		<table>
			<tr>
				<td><label for="denominazione">Denominazione: </label> </td>
				<td><form:input path="denominazione" id="denominazione"/></td>
				<td><form:errors path="denominazione" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="isin">ISIN: </label> </td>
				<td><form:input path="isin" id="isin"/></td>
				<td><form:errors path="isin" cssClass="error"/></td>
		    </tr>

			<tr>
				<td><form:label path = "mercato">Mercato:</form:label></td>
<!-- 				<td> -->
<%-- 					<form:select path = "mercato" id="mercato"> --%>
<%--                      <form:option value = "NONE" label = "Select"/> --%>
<%--                      <form:options items = "${mercatiList}" /> --%>
<%--                   </form:select> 				 --%>
<!--                 </td>   -->
               <td>
                  <form:select path = "mercato.mercatoId">
                     <form:options items = "${mercatiList}" />
                  </form:select>     	
               </td>
            	<td><form:errors path="mercato" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><form:label path = "valuta">Valuta:</form:label></td>
				<td><form:select path = "valuta.valutaId" id="valuta">
                     <form:options items = "${valuteList}" />
                  </form:select> 				
                </td>  
				<td><form:errors path="valuta" cssClass="error"/></td>
		    </tr>
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Aggiorna"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Inserisci"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Torna a <a href="<c:url value='/titolo/list' />">Lista Titoli in portafoglio</a>
</body>
</html>