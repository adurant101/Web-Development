<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--
	calculator page that UseCalc servlet operates on
	that displays calcuations in a trail of calculations using the
	last solution for the next calcuation 
-->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calculator</title>
<script>
	//if no input displays alert
	function onSubmit(frm)
	{
		//alert(frm.nums.value);
		if (frm.nums.value=="" ||isNaN(frm.nums.value))
		{
			alert("Not a number");
			return false;
		}
	}
</script>
</head>
<body>
<h1>Calculator</h1>
<!-- displays calculator using UseCalc servlet with operands, clear button, submit button -->
<form action="UseCalc" method="POST" onSubmit="return onSubmit(this)">
<input type="text" name="nums"/>
<select name ="op">
	<option value="+">+</option>
	<option value="-">-</option>
	<option value="*">*</option>
	<option value="/">/</option>
</select><br/>
<input type='checkbox' name='clearSession'/>Clear
<input type="submit" name="Calculate" value="calc"/>

</form>


<% 
	//display trail from UseCalc
	Object trail=request.getAttribute("trail");
	if (trail != null )
	{
		out.print(trail);
	}
%>
</body>
</html>
