<%@page import="register.AddressBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- 
	gets input for info to put into sql, checks for errors using jQuery
	then goes to exRegister.jsp
 -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<script>

<%
	AddressBean reg =(AddressBean) request.getSession().getAttribute("reg");
	if (reg !=null)
	{
		out.println ("const data = "+reg);
	}
	else{
		out.println ("const data = null;");
	}
%>

$().ready (()=>{
	if (data)
	{
		$("#fname").val(data.fname);
		$("#sname").val(data.sname);
		$("#telephone").val(data.telephone);
		$("#mobile").val(data.mobile);
		$("#email").val(data.email);
		$("#address").val(data.address);
		$("#postalnr").val(data.postalnr);
		$("#town").val(data.town);
		$("#country").val(data.country);
		
		if (data.errors)
		{
			const errors =data.errors.split(",");
			for (let i in errors)
			{
				$("#"+errors[i]+"Error").html ("<font color='red'>Invalid or missing value</font>");	
			}
		}
	}
});
</script>
</head>
<body>
<%
//register.AddressBean ab = new register.AddressBean();
%>
<h1>Registration</h1>




<form method='post' action='exRegister.jsp'>
<p><input type='text' name='fname' id='fname' placeholder='First Name'/>
<span id='fnameError'></span>
</p>
<p><input type='text' name='sname' id='sname' placeholder='Last Name'/></p>
<p><input type='text' name='telephone' id='telephone'  placeholder='telephone'/></p>
<p><input type='text' name='mobile' id='mobile' placeholder='mobile'/></p>
<p><input type='text' name='email' id='email' placeholder='email '/>
<span id='emailError'></span>
</p>
<p><input type='text' name='address' id='address' placeholder='address'/></p>
<p><input type='text' name='postalnr' id='postalnr' placeholder='Post Number'/></p>
<p><input type='text' name='town' id='town' placeholder='town'/></p>
<p><input type='text' name='country' id='country' placeholder='country'/></p>
<p><input type='submit' name='submit' value='Save'/></p>
</form>
</body>
</html>
