<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.BeanPost"
	session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col">
		<h1>My profile</h1>
		<form id="profile_form">
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-4 col-form-label">First
					Name</label>
				<div class="col-sm-6">
					<input type="text" name="firstname" readonly
						class="form-control-plaintext" id="firstname"
						value="<%=request.getAttribute("first_name")%>">
				</div>
			</div>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-4 col-form-label">Last
					Name</label>
				<div class="col-sm-6">
					<input type="text" readonly name="lastname"
						class="form-control-plaintext" id="lastname"
						value="<%=request.getAttribute("last_name")%>">
				</div>
			</div>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-4 col-form-label">Nickname</label>
				<div class="col-sm-6">
					<input type="text" readonly
						class="form-control-plaintext"
						value="<%=request.getAttribute("nickname")%>">
				</div>
			</div>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-4 col-form-label">Email</label>
				<div class="col-sm-6">
					<input type="text" readonly name="mail"
						class="form-control-plaintext" id="mail"
						value="<%=request.getAttribute("email")%>">
				</div>
			</div>
			<div class="row" id="follow_container" style="padding-top: 30px">
			</div>
	</div>
	<div class="col">
		<h1>Posts</h1>
		<div class="text-center" id="my_posts" style="height:500px;
  overflow-y: scroll;">
			<%
				String posts_size = request.getParameter("posts_size");
				BeanPost[] posts = (BeanPost[]) request.getAttribute("posts");
				int size = (int) request.getAttribute("posts_size");
				for (int i = size - 1; i >= 0; i--) {
					out.println("<div class='card' style='margin-top: 10px' id='post_" + posts[i].getId() + "'>" + "<div class='card-header'>"
							+ posts[i].getNickname() + "</div>" + "<div class='card-body'>" + "<input class='form-control-plaintext' type='text' readonly id='text_post_" + 
					posts[i].getId() +"' value='"+ posts[i].getText() + "' style='text-align: center'/>"+
							"</div>" + "</div>");
				}
			%>
		</div>

	</div>
</div>

<script>
<%HttpSession session = request.getSession();%>
var isSession = "<%=((session.getAttribute("nickname") != "" && session.getAttribute("nickname") != null) || session.getAttribute("anonymous") == "yes")%>";
if(isSession === "true"){
	$('#follow_container').load('CheckFollowUserController', {nickname: '<%=session.getAttribute("nickname")%>', user_nickname: '<%=request.getAttribute("nickname")%>'});
}
//$('#follow_container').load('CheckFollowUserController', {nickname: session});
	
	
</script>

</form>