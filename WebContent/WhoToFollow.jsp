<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.BeanUser" import="models.BeanPost" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
BeanUser user = new BeanUser();
BeanPost post = new BeanPost();
HttpSession session = request.getSession();
int isUser = 0;
if(session.getAttribute("anonymous").equals("yes")){
	isUser = 0;
} else {
	user = (BeanUser)session.getAttribute("user");
	isUser = 1;
}	
%>
	<div class="container">
		<div class="row">
			<div class="col text-center"></div>
			<div class="col-7 text-center">
				<h3>Who To Follow</h3>
				<h4>Users</h4>
				<div id="home_posts" style="margin-bottom: 30px">
				<%
			String posts_size = request.getParameter("users_size");
			BeanUser[] posts = (BeanUser[])request.getAttribute("users");
			int size = (int)request.getAttribute("users_size");
			for(int i=size-1; i>=0; i--){%>
						<div class='card' style='margin-top: 10px'>
				  <div class='card-header' id='nickname_<%=posts[i].getNickname()%>'><%=posts[i].getNickname()%></div>
				  <div class='card-body'>
				    <p class='card-text'><%=posts[i].getFirstname()%> <%=posts[i].getLastname()%></p>
				  <button type='button' class='btn btn-primary' id='profile_button_post_<%=posts[i].getNickname()%>'
					>See profile</button>
				  </div>
				</div>
			<%}
			%>
				</div>
			</div>
			<div class="col text-center"></div>
		</div>
	</div>
	<script>
	function showProfile(id){
		var nickname = document.querySelector("#nickname_" + id.replace("profile_button_post_", "")).textContent
		var nick2 = "<%=session.getAttribute("nickname")%>"
		var isUser = "<%=isUser%>"
		var isAdmin = "0";
		if(isUser === "0"){
			isAdmin === "0"
		} else {
			isAdmin = "<%=user.getIsAdmin()%>"
		}
		 if (nickname === nick2) {
				$('#content').load('MyProfileController');
			}
		 else if(isAdmin === "1"){
			$('#content').load('ShowUserAdminProfileController',{nickname: nickname });
		}else {
			$('#content').load('ShowProfileController',{nickname: nickname });
		}
	}
	var home_posts = document.querySelector("#home_posts");
	var size = home_posts.childNodes.length -1;
	for(var i=size-1; i>=1; i=i-2){
		var id = home_posts.childNodes[i].childNodes[1].id
		document.querySelector("#profile_button_post_" + id.replace("nickname_", "")).addEventListener('click', function(e) {
			showProfile(e.path[0].id)
		})
	}
	</script>
</body>