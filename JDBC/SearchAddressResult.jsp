<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- 
	display items from sql database after search
 -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border='1'><tr><th>Name</th><th>Email</th><th>Mobile</th></tr>
<%

java.util.ArrayList<register.AddressBean> list = 
	(java.util.ArrayList<register.AddressBean>)request.getSession().getAttribute("listValues");
for (register.AddressBean ab :list)
{
	out.println (String.format (
			"<tr><td>%s, %s</td><td>%s</td><td>%s</td></tr>",
			 ab.getSname(), ab.getFname(), ab.getEmail(), ab.getMobile()));
}

%>
</table>
</body>
</html>
