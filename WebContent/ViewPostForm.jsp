<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.BeanPost" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$(document).ready(
			function() {
				$("#post_button").click(
						function(event) {
							$.ajaxSetup({
								cache : false
							}); //Avoids Internet Explorer caching!	
							$('#content').load('PostController',
									$(".form-control").serialize());
							event.preventDefault();
						});
			});
</script>
<%
	BeanPost post = new BeanPost();
	HttpSession session = request.getSession();
	post.setNickname(session.getAttribute("nickname").toString());
%>
<textarea class="form-control" name="text" id="text" rows="3"
	placeholder="Lets talk about this movie you saw last night..."
	value=<%=post.getText()%>></textarea>
<div class="row">
	<div class="col text-right" style="padding-top: 20px;">
		<button type="button" id="post_button" class="btn btn-info">Post</button>
	</div>
</div>
<div class="row">
	<div class="col">
		<hr>
	</div>
</div>