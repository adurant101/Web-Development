<%@page import="register.AddressBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--  
	get all info from register.jsp and check for error to put in sql then
	go to DeleteAddress.jsp
 -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	window.location.replace("DeleteAddress.jsp");
</script>
</head>
<body>
<%
//register.AddressBean ab = new register.AddressBean();
%>
<h1>Registration</h1>
<jsp:useBean id="reg" class ="register.AddressBean" scope = "page">
<jsp:setProperty name="reg" property = "fname" param="fname"/>
<jsp:setProperty name="reg" property = "sname" param="sname"/>
<jsp:setProperty name="reg" property = "telephone" param="telephone"/>
<jsp:setProperty name="reg" property = "mobile" param="mobile"/>
<jsp:setProperty name="reg" property = "email" param="email"/>
<jsp:setProperty name="reg" property = "address" param="address"/>
<jsp:setProperty name="reg" property = "postalnr" param="postalnr"/>
<jsp:setProperty name="reg" property = "town" param="town"/>
<jsp:setProperty name="reg" property = "country" param="country"/>
</jsp:useBean>
<%
	String errors = reg.validate();

	if (errors==null)
	{
		//request.getRequestDispatcher("DeleteAddress.jsp").forward(request, response);
		reg.setSave("save");
	}else{

		request.getSession().setAttribute("reg", reg);
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}
%>
</body>
</html>
