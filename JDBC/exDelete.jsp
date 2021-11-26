<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- 
	page to display after record deleted
 -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	if (id !=null)
	{
		if (register.AddressBean.delete(Integer.parseInt(id))>0)
		{
			out.println ("<h3><font color='green'>Record deleted</font></h3>");
			
		}else{
			out.println ("<h3><font color='red'>Record NOT deleted</font></h3>");
		}
		request.getRequestDispatcher("SearchAddress.jsp").forward(request, response);
	}
%>
</body>
</html>
