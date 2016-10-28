<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
${favicon}
<title>Estado da Porta</title>
</head>
<body>
	<section>
		<table border="1">
			<thead>
				<tr>
					<td>#</td>
					<td>Data</td>
					<td>Estado</td>				
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${doorEvents}" var="doorEvent">			
					<tr>
						<td>${doorEvent.id}</td>
						<td>${doorEvent.date}</td>
						<td>${doorEvent.state}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">Total de Eventos registados da porta: ${doorEvents.size()}</td>
				</tr>
			</tfoot>
		</table>
	
	</section>
	
	
</body>
</html>