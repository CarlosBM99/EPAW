<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.BeanUser" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(
			function() {
				$("#registerForm").submit(
						function(event) {
							$.ajaxSetup({
								cache : false
							}); //Avoids Internet Explorer caching!	
							$('#content').load('RegisterController',
									$("#registerForm").serialize());
							event.preventDefault();
						});
			});
</script>
<%
	/* 
		This java code is only for debbuging purposes it avoids errors
		when calling the view before the controller. Using the MVC design
		pattern this will not occur and this java code can be safely removed.
	*/
	BeanUser user = null;
	if (request.getAttribute("user") != null)
		user = (BeanUser) request.getAttribute("user");
	else
		user = new BeanUser();
%>
<h1>Register Form</h1>
<form id="registerForm">
	<div class="form-group row">
		<div class="col">
			<label for="inputEmail4">First name</label> <input type="text"
				class="form-control <%if (user.getError()[0] == 3) {
				out.println("is-invalid");
			}%>"
				placeholder="First name" value="<%=user.getFirstname()%>"  name="firstname"
                        id="firstname" required>
			<%
				if (user.getError()[0] == 3) {
					out.println("<div class='invalid-feedback'>Illegal characters(space, tab, /, \\)</div>");
				}
			%>
		</div>
		<div class="col">
			<label for="inputEmail4">Last name</label> <input type="text"
				class="form-control <%if (user.getError()[1] == 3) {
				out.println("is-invalid");
			}%>"
				placeholder="Last name" value="<%=user.getLastname()%>" name="lastname"
                        id="lastname" required>
			<%
				if (user.getError()[1] == 3) {
					out.println("<div class='invalid-feedback'>Illegal characters(space, tab, /, \\)</div>");
				}
			%>
		</div>
		<div class="col">
			<label for="inputEmail4">Nickname</label> <input type="text"
				class="form-control <%if (user.getError()[2] == 2 || user.getError()[2] == 3) {
				out.println("is-invalid");
			}%>"
			name="nickname"
                        id="nickname"
				placeholder="Nickname" value="<%=user.getNickname()%>" required />
			<%
				if (user.getError()[2] == 2) {
					out.println("<div class='invalid-feedback'>This nickname already exists.</div>");
				}

				if (user.getError()[2] == 3) {
					out.println("<div class='invalid-feedback'>Illegal characters(space, tab, /, \\)</div>");
				}
			%>
		</div>
	</div>

	<div class="form-group row">
		<div class="form-group col-md-6">
			<label for="inputEmail4">Email</label> <input type="email"
			name="mail"
                        id="mail"
				class="form-control <%if (user.getError()[3] == 2 || user.getError()[3] == 3) {
				out.println("is-invalid");
			}%>"
				id="inputEmail4" placeholder="Email" required
				value="<%=user.getMail()%>">
			<%
				if (user.getError()[3] == 2) {
					out.println("<div class='invalid-feedback'>This email is already being used.</div>");
				}

				if (user.getError()[3] == 3) {
					out.println("<div class='invalid-feedback'>Illegal characters(space, tab, /, \\)</div>");
				}
			%>
		</div>
		<div class="form-group col-md-6 row">
			<div class="form-group col">
				<label for="inputPassword4">Password</label> <input type="password"
				name="password"
                        id="password"
					class="form-control <%if (user.getError()[4] == 3) {
				out.println("is-invalid");
			}%>"
					id="inputPassword4" placeholder="Password"
					value="<%=user.getPassword()%>" required />
				<%
					if (user.getError()[4] == 3) {
						out.println("<div class='invalid-feedback'>Illegal characters(space, tab, /, \\)</div>");
					}
				%>

			</div>
			<div class="form-group col">
				<label for="inputPassword4">Re-Password</label> <input
					type="password"
					name="repassword"
                        id="repassword"
					class="form-control <%if (user.getError()[5] == 3) {
				out.println("is-invalid");
			}%>"
					id="inputRePassword" placeholder="Password"
					value="<%=user.getRepassword()%>" required />
				<%
					if (user.getError()[5] == 3) {
						out.println("<div class='invalid-feedback'>Passwords do not match.</div>");
					}
				%>

			</div>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-10">
			<button type="submit" class="btn btn-primary">Sign in</button>
		</div>
	</div>
</form>