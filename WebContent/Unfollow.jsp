<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

<button type="button" id="unfollow_button" class="btn btn-info">Unfollow</button>

<script>
$("#unfollow_button").click(function(){
	$("#content").load("UnfollowUserController", {nickname: '<%=request.getAttribute("nickname")%>', user_nickname: '<%=request.getAttribute("user_nickname")%>'} )
})

</script>