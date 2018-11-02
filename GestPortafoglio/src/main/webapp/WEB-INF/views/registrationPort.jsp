<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="css/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="js/datepicker-it.js"></script>
  <script>
  $(function() {
		$.datepicker.setDefaults($.datepicker.regional['it']);
		$(".data:input").datepicker({
			showAnim: "fade",
			dateFormat: "mm/dd/yy"
		});
	});
  </script>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Censimento titolo</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>Registrazione titolo</h2>
 
	<form:form method="POST" modelAttribute="portafoglio">
		<form:input type="hidden" path="portafoglioId" id="portafoglioId"/>
		<table>
			<tr>
				<td><form:label path = "titolo">Titolo:</form:label></td>
				<td><form:select path = "titolo.titoloId" id="titolo">
                     <form:options items = "${titoliList}" />
                  </form:select> 				
                </td>  
		    </tr>
			<tr>
				<td><label for="quantita">Quantita: </label> </td>
				<td><form:input path="quantita" id="quantita"/></td>
				<td><form:errors path="quantita" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="dataAcquisto">Data di acquisto (mm/dd/yyyy): </label> </td>
				<td><form:input path="dataAcquisto" id="dataAcquisto" class="data" /></td>
				<td><form:errors path="dataAcquisto" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="prezzoAcquisto">Prezzo di acquisto: </label> </td>
				<td><form:input path="prezzoAcquisto" id="prezzoAcquisto"/></td>
				<td><form:errors path="prezzoAcquisto" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="tassoValutaAcquisto">Tasso valuta di acquisto: </label> </td>
				<td><form:input path="tassoValutaAcquisto" id="tassoValutaAcquisto"/></td>
				<td><form:errors path="tassoValutaAcquisto" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="dataVendita">Data di vendita (mm/dd/yyyy): </label> </td>
				<td><form:input path="dataVendita" id="dataVendita" class="data" /></td>
				<td><form:errors path="dataVendita" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="prezzoVendita">Prezzo di vendita: </label> </td>
				<td><form:input path="prezzoVendita" id="prezzoVendita"/></td>
				<td><form:errors path="prezzoVendita" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="tassoValutaVendita">Tasso valuta di vendita: </label> </td>
				<td><form:input path="tassoValutaVendita" id="tassoValutaVendita"/></td>
				<td><form:errors path="tassoValutaVendita" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="controvaloreEuro">Controvalore Euro: </label> </td>
				<td><form:input path="controvaloreEuro" id="controvaloreEuro"/></td>
				<td><form:errors path="controvaloreEuro" cssClass="error"/></td>
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
	Torna a <a href="<c:url value='/list' />">Lista Titoli</a>
</body>
</html>