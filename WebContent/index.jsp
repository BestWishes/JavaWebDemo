<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./user/login">
userName:		<input type="text" name="userName"><br>
password:		<input type="text" name="password"><br>
记住账号和密码<input type="checkbox" name="remenberme">
				<input type="hidden" name="islog" value="true">
 	<input type="submit" value="submit">
	</form>
</body>
</html>