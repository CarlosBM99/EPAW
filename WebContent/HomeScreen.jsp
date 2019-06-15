<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.BeanPost" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<link href="css/structure.css" rel="stylesheet" />
<%
	BeanPost post = new BeanPost();
	HttpSession session = request.getSession();
	if(session != null){
		//System.out.println("session");
		if(session.getAttribute("nickname") != "" && session.getAttribute("nickname") != null){
			post.setNickname(session.getAttribute("nickname").toString());
		}
	} else {
		//System.out.println("no-session");
	}
		
%>
</head>
<script type="text/javascript">
$(document).ready(function() {
	$('#post_from_container').load('PostViewController');
    $("#post_button").click(function(event) {
    	<%session.setAttribute("post", post);%>
        $('#content').load('ContentController',{content: "PostController"});
        });
});
</script>
<body>
	<div class="container">
		<div class="row">
			<div class="col text-center"></div>
			<div class="col-7 text-center">
				<h2>Spoilers</h2>
				<div id="post_from_container"></div>
				<h3>Global Timeline</h3>
				<div id="home_posts" style="margin-bottom: 30px">
				<%
			String posts_size = request.getParameter("posts_size");
			BeanPost[] posts = (BeanPost[])request.getAttribute("posts");
			int size = (int)request.getAttribute("posts_size");
			for(int i=size-1; i>=0; i--){
				out.println(
						"<div class='card' style='margin-top: 10px'>"+
				  "<div class='card-header' id='nickname_" + posts[i].getId() + "'>"+
				    posts[i].getNickname()+
				  "</div>"+
				  "<div class='card-body'>"+
				    "<p class'card-text'>"+
				  posts[i].getText()+
				  "</p>"+
				  "<button type='button' class='btn btn-primary' id='profile_button_post_" + posts[i].getId() + "'"+
					">See profile</button>"+
				  "</div>"+
				"</div>");
			}
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
		if(nickname === nick2){
			$('#content').load('MyProfileController');
		} else {
			$('#content').load('ShowProfileController',{nickname: nickname });
		}
	}
	var home_posts = document.querySelector("#home_posts");
	var size = home_posts.childNodes.length -1;
	for(var i=size-1; i>=1; i=i-2){
		var id = home_posts.childNodes[i].childNodes[0].id
		document.querySelector("#profile_button_post_" + id.replace("nickname_", "")).addEventListener('click', function(e) {
			showProfile(e.path[0].id)
		})
	}
	</script>
</body>