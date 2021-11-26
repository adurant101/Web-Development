<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- 
	search for info from sql database then goes to exSearchAddress.jsp
 -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method='post' action='exSearchAddress.jsp'>
<select name="column">
<option value='sname'>Sur Name</option>
<option value='fname'>First Name</option>
<option value='email'>Email</option>
<option value='mobile'>Mobile</option>
<option value='town'>Town</option>
</select>
<input type ='text' name='searchForValue' placeholder = "Enter value to search"/>
<input type='submit'/>
</form>
</body>
</html>
