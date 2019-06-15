<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.BeanLogin" session="false"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$(document).ready(
			function() {
				$("#loginForm").submit(
						function(event) {
							$.ajaxSetup({
								cache : false
							}); //Avoids Internet Explorer caching!	
							$('#content').load('LoginController',
									$("#loginForm").serialize());
							event.preventDefault();
						});
			});
</script>
<%
	BeanLogin login = null;
	if (request.getAttribute("login") != null)
		login = (BeanLogin) request.getAttribute("login");
	else
		login = new BeanLogin();
%>
<h1>Login From</h1>
<form id="loginForm">
  <div class="form-group">
			<label for="inputEmail4">Nickname</label> <input type="text"
				class="form-control"
			name="nickname"
                        id="nickname"
				placeholder="Nickname" value="<%=login.getNickname()%>" required />
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control"
     name="password"
                        id="password"
    placeholder="Password" value="<%=login.getPassword()%>" required/>
  </div>
  <%
								if (login.getError() == 1) {
									out.println("<div class='alert alert-danger' role='alert'>Invalid username or/and password.</div>");
								}
							%>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
</html>