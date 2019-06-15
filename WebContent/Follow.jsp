<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<button type="button" id="follow_button" class="btn btn-info">Follow</button>

<script>
$("#follow_button").click(function(){
	$("#content").load("FollowUserController", {nickname: '<%=request.getAttribute("nickname")%>', user_nickname: '<%=request.getAttribute("user_nickname")%>'} )
})

</script>