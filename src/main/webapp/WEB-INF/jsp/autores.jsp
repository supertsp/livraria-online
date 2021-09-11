<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8"/>
	
	<link 
		rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"  
		integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" 
		crossorigin="anonymous" />
		
	<title>Autores</title>
</head>

<body>
	<c:set var="actionPage" value="/autores"/>
	
	<div class="container">
		
		<h2 class="text-center">Cadastro de Autores</h2>
		
		<form method="post" action="<c:url value="/autores"/>">
			
			<div class="form-group">
				<label>Nome</label>
				<input type="text" name="nome" class="form-control">
			</div>
			
			<div class="form-group">
				<label>E-mail</label>
				<input type="email" name="email" class="form-control">
			</div>
			
			<div class="form-group">
				<label>Data Nascimento</label>
				<input type="date" name="dataNascimento" class="form-control">
			</div>			
			
			<input type="submit" value="Salvar" class="btn btn-primary mt-1">
			
		</form>
		
		<hr />
		
		<h2 class="text-center">Lista de Autores</h2>
		
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Nome</th>
					<th>E-mail</th>
					<th>Data Nascimento</th>
			</thead>
			<tbody>
				<c:forEach var="autor" items="${autores}">
					<tr>
						<td>${autor.nome}</td>
						<td>${autor.email}</td>
						<td>
							<f:parseDate var="dataFormatada" value="${autor.dataNascimento}" type="date" pattern="yyyy-MM-dd" />
							<f:formatDate pattern="dd/MM/yyyy" value="${dataFormatada}"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>
	
</body>

</html>
<!-- 
Formatando LocalDate com uma TAG personalizada: 
https://stackoverflow.com/questions/28516766/using-java-time-localdate-with-jstl-fmtformatdate-action

Formatação simples da classe Date
https://www.tutorialspoint.com/jsp/jstl_format_formatdate_tag.htm
 -->