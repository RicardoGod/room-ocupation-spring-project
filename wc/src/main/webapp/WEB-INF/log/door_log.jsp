<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html >

<head>



<meta http-equiv="Refresh" Content="5">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Door State</title>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<link rel="icon" type="image/x-icon" href="${path}/static/images/${favicon}.ico" />




<style type="text/css">
			@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css");
</style>

<style>
.jumbotron { 
    background-color: #004594; /* Blue */
	   	
   	
    color: #ffffff;
}



</style>

</head>
<body>
	<div >
	
		<div class="container">
		
			<div >
			
			<section>
				<table class="table table-hover table-condensed table-striped table-bordered text-center">
				
					<thead>
						<tr>
							<td><h3>Tempo Entrada</h3></td>
							<td><h3>Tempo Saída</h3></td>
							<td><h3>Tempo Decorrido</h3></td>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${eventos}" var="evento">
							<tr>
								<td>${evento.entry_time}</td>
								<td>${evento.exit_time}</td>
								<td>${evento.elapsed_time}</td>
							</tr>
						</c:forEach>
					</tbody>
					
					<tfoot>
						<tr>
							<c:set var="size" value="${eventos.size()}"/>
							<td colspan="3" class="text-center"><h4>Média de tempo registado:${eventos[size-1].average_time}</h4></td>
						</tr>
						<tr>	
							<td colspan="3" class="text-center"><h4>Eventos registados: ${eventos.size()}</h4></td>
						</tr>
						
					</tfoot>
				</table>
				
			</section>
			
			</div> <!-- jumbotron -->
		
		</div> <!-- container -->
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.js"></script>
	
</body>
</html>