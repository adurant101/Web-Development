<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- 
	get variables for list and go to SearchAddressResult.jsp
 -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String column = request.getParameter ("column");
	String value = request.getParameter("searchForValue");
	ArrayList list = register.AddressBean.search(column, value);
	
	if (list !=null)
	{
		request.getSession().setAttribute("listValues", list);
		request.getRequestDispatcher("SearchAddressResult.jsp").forward(request, response);
		
	}
%>
</body>
</html>
