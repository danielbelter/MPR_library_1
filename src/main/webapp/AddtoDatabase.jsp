<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="library.dao.repos.*" %>
<%@ page import="library.dao.repos.impl.*" %>
<%@ page import="library.domain.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	<%
	String pageNumber = request.getParameter("page");
	String userId = request.getParameter("userId");
	IDatabaseCatalog library = new HsqlDbCatalogFactory().library();
	for(ReservationItem b: library.reservationItems().getPage(Integer.parseInt(pageNumber),5)){
	if(library.reservationItems().get(b.getReservationId()).getReservationId()==Integer.parseInt(userId)) {
		%>
			<tr>
			<td>title:<%=library.books().get(b.getBookId()).getTitle()%>
			<td>UserId:<%=library.reservationItems().get(b.getReservationId()).getReservationId() %>
			
			</td>
			</tr>		
		<%
		}
	}
	%>
	
	
	</table>
</body>
</html>