<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.BeanPost" import="models.BeanUser" import="models.Comment" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<link href="css/structure.css" rel="stylesheet" />
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
	if(session != null){
		//System.out.println("session");
		if(session.getAttribute("nickname") != "" && session.getAttribute("nickname") != null){
			post.setNickname(session.getAttribute("nickname").toString());
		}
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
			for(int i=size-1; i>=0; i--){%>
						<div class='card' style='margin-top: 10px'>
				  <div class='card-header' id='nickname_<%=posts[i].getId()%>'><%=posts[i].getNickname()%></div>
				  <div class='card-body'>
				  <p class='card-text'><%=posts[i].getText()%></p>
				  <button type='button' class='btn btn-primary' id='profile_button_post_<%=posts[i].getId()%>'>See profile</button>
				  </div>
				  <div class='card-body' style='border-top: 1px solid lightgray; text-align: center'>
				  <div class="col text-center">
				  
				  <div id="like_button_post_<%=posts[i].getId()%>">
				  <%
				  int var = 0;
				  posts[i].setNickLikes();
				  for(int p =0; p < posts[i].getNickLikes().size(); p++ ){
					  if(posts[i].getNickLikes().get(p).equals(session.getAttribute("nickname"))){
						  var = 1;
						  break;
					  }
				  }
				  if(var == 0) {
					  %><div><i class="fa fa-heart"></i><%=posts[i].getNumLikes()%></div>
					  <%
				  } else {
					  %><div><i class="fa fa-heart" style='color: red'></i><%=posts[i].getNumLikes()%></div>
					  <%
				  }
				  %>
				  
				  </div>
				  <p>
  <button class="btn btn-info" type="button" data-toggle="collapse" data-target="#collapse_<%=posts[i].getId()%>" aria-expanded="false" aria-controls="collapseExample">
    Comments
  </button> <div> ( <%=posts[i].getNumComments()%> )</div>
</p>
<div class="collapse" id="collapse_<%=posts[i].getId()%>">
	<%
				  posts[i].setComments();
				  for(int p =0; p < posts[i].getComments().size(); p++ ){
					  Comment c = posts[i].getComments().get(p);
					  %>
					  <div class="card card-body">
    <%= c.getNickname()%>: <%= c.getText()%>
  </div>
				  <%}
				  %>
  
</div>
				  
	</div>	
	<div class="row">
	<input type="text" class="form-control col-lg" id="input_comment_<%=posts[i].getId()%>" placeholder="Type something">
	<button type="button" class="btn btn-dark" id="comment_button_post_<%=posts[i].getId()%>">Comment</button>
	</div>		  
				  </div>
				</div>
			<%}%>
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
	
	function addLike(id){
		$('#' + id).load('AddLikeController',{ post_id: id.replace("like_button_post_", "") });
	}
	function addComment(id){
		var text = document.querySelector('#'+ 'input_comment_' + id.replace("comment_button_post_", "")).value
		$('#content').load('AddCommentController',{ text: text, post_id: id.replace("comment_button_post_", "") });
	}
	var home_posts = document.querySelector("#home_posts");
	var size = home_posts.childNodes.length -1;
	for(var i=size-1; i>=1; i=i-2){
		var id = home_posts.childNodes[i].childNodes[1].id
		document.querySelector("#profile_button_post_" + id.replace("nickname_", "")).addEventListener('click', function(e) {
			showProfile(e.path[0].id)
		})
		document.querySelector("#like_button_post_" + id.replace("nickname_", "")).addEventListener('click', function(e) {
			addLike(e.path[2].id)
		})
		document.querySelector("#comment_button_post_" + id.replace("nickname_", "")).addEventListener('click', function(e) {
			//console.log(e)
			addComment(e.path[0].id)
		})
	}
	</script>
</body>