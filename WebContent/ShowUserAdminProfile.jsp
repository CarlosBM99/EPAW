<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.BeanUser" import="models.BeanPost"
	session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	BeanUser user = null;
	user = new BeanUser();
	user.setFirstname(request.getAttribute("first_name").toString());
	user.setLastname(request.getAttribute("last_name").toString());
	user.setNickname(request.getAttribute("nickname").toString());
	user.setMail(request.getAttribute("email").toString());
%>

<div class="row">
	<div class="col">
		<button type="button" id="delete_account" class="btn btn-danger">Delete Account</button>
		<h1>Profile</h1>
		<form id="profile_form">
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-4 col-form-label">First
					Name</label>
				<div class="col-sm-6">
					<input type="text" name="firstname" readonly
						class="form-control-plaintext" id="firstname"
						value="<%=user.getFirstname()%>">
				</div>
			</div>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-4 col-form-label">Last
					Name</label>
				<div class="col-sm-6">
					<input type="text" readonly name="lastname"
						class="form-control-plaintext" id="lastname"
						value="<%=user.getLastname()%>">
				</div>
			</div>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-4 col-form-label">Nickname</label>
				<div class="col-sm-6">
					<input type="text" readonly name="nickname"
						class="form-control-plaintext" id="nickname"
						value="<%=user.getNickname()%>">
				</div>
			</div>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-4 col-form-label">Email</label>
				<div class="col-sm-6">
					<input type="text" readonly name="mail"
						class="form-control-plaintext" id="mail"
						value="<%=user.getMail()%>">
				</div>
			</div>
			</form>
			<div class="row">
				<button type="button" class="btn btn-primary" id="edit_button"
					>Edit</button>
				<button type="button" class="btn btn-danger" id="cancel_button"
					style="margin-right: 20px">Cancel</button>
				<button type="button" class="btn btn-success" id="save_button"
					style="margin-right: 20px">Save</button>
			</div>
			
			<div id="followed_users">
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
							"<div class='row' style='justify-content: center'>"+
							"<button type='button' class='btn btn-primary' id='edit_button_post_" + posts[i].getId() + "'"+
							">Edit</button>"+
							"<button type='button' class='btn btn-warning' id='delete_button_post_"  + posts[i].getId() + "'" + "style='margin-left: 20px'>Delete Spoiler</button>"+
						"<button type='button' class='btn btn-danger' id='cancel_button_post_" + posts[i].getId() + "'"+
							"style='margin-right: 20px; display: none'>Cancel</button>"+
						"<button type='button' class='btn btn-success' id='save_button_post_" + posts[i].getId() + "'"+
							"style='margin-right: 20px; display: none'>Save</button>"+
					"</div>"+
							"</div>" + "</div>");
				}
			%>
		</div>

	</div>
</div>

<script>
	$("#delete_account").click(function() {
		$('#content').load('DeleteAccountController', {nickname: "<%=user.getNickname()%>"})
	})
	$("#followed_users").load('FollowedUsersController',{nickname: "<%=user.getNickname()%>"});
	$("#cancel_button").css("display", "none")
	$("#save_button").css("display", "none")
	$("#cancel_button").click(function(event) {
		$('#content').load('ContentController', {
			content : "ShowUserAdminProfileController"
		});
	});
	$("#save_button").click(
			function(event) {
				$.ajaxSetup({
					cache : false
				}); //Avoids Internet Explorer
				var a = document.querySelector("#firstname")
				var b = document.querySelector("#lastname")
				$('#content').load('SaveUserAdminInfoController', {nickname: "<%=user.getNickname()%>", 
					first_name: a.value, last_name: b.value});
				
				event.preventDefault();
			}
			);
	$("#edit_button").click(function(event) {
		$('input').each(function() {
			if (this.id == "firstname" || this.id == "lastname") {
				$("#edit_button").css("display", "none")
				$("#save_button").css("display", "block")
				$("#cancel_button").css("display", "block")
				$(this).prop('readonly', false);
				$(this).prop('class', "form-control");
			}
		})
	});
	var my_posts = document.querySelector("#my_posts");
	var size = my_posts.childNodes.length -1;
	function showButtons(id) {
		console.log("id" + id)
		document.querySelector("#save"+ id.replace("edit", "")).style.display = "block";
		document.querySelector("#cancel" + id.replace("edit", "")).style.display = "block";
		document.querySelector("#delete" + id.replace("edit", "")).style.display = "none";
		document.querySelector("#"+ id).style.display = "none";
		$("#text_" +id.replace("edit_button_", "")).prop('readonly', false);
		$("#text_" +id.replace("edit_button_", "")).prop('class', "form-control");
	}
	function closeButtons(id) {
		$('#content').load('ContentController', {
			content : "ShowUserAdminProfileController"
		});
	}
	function saveInfo(id){
		var _id = id.replace("save_button_post_", "")
		var text = document.querySelector("#text_"+ id.replace("save_button_", "")).value
			$.ajaxSetup({
				cache : false
			}); //Avoids Internet Explorer
			$('#content').load('SaveUserAdminPostController',
					{id: _id, text: text } );
	}
	function deletePost(id){
		var _id = id.replace("delete_button_post_", "")
		$('#content').load('DeleteUserAdminPostController', {post_id: _id});
	}
	for(var i=size-1; i>=1; i=i-2){
		var id = my_posts.childNodes[i].id
		document.querySelector("#edit_button_" + id).addEventListener('click', function(e) {
			showButtons(e.path[0].id)
		})
		document.querySelector("#cancel_button_"+ id).addEventListener('click', function(e) {
			closeButtons(e.path[0].id)
		})
		document.querySelector("#save_button_"+ id).addEventListener('click', function(e) {
			saveInfo(e.path[0].id)
		})
		document.querySelector("#delete_button_"+ id).addEventListener('click', function(e) {
			deletePost(e.path[0].id)
		})
	}
</script>

</form>