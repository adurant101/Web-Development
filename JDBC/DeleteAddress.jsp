<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- 
	display bean sql info in table
 -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border='1'><tr><th>Delete</th><th>Name</th><th>Email</th><th>Mobile</th></tr>
<%

java.util.ArrayList<register.AddressBean> list = register.AddressBean.getAll();
for (register.AddressBean ab :list)
{
	out.println (String.format (
			"<tr><td><a href='exDelete.jsp?id=%d'>Delete</td><td>%s, %s</td><td>%s</td><td>%s</td></tr>",
			ab.getId(), ab.getSname(), ab.getFname(), ab.getEmail(), ab.getMobile()));
}

%>
</table>
</body>
</html>
