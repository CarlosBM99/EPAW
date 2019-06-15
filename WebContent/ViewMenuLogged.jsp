<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<script type="text/javascript">
$(document).ready(function() {
    $(".menu").click(function(event) {
        $('#content').load('ContentController',{content: $(this).attr('id')});
        });
});
</script>

<li class="nav-item active"><a class="nav-link menu" id="MyProfileController" href="#">My profile</a></li>
<li class="nav-item active"><a class="nav-link menu" id="PersonalizedTimelineController" href="#">Personalized timeline</a></li>
<li class="nav-item active"><a class="nav-link menu" id="WhoToFollowController" href="#">Who to follow</a></li>
<li class="nav-item active"><a class="nav-link menu" id="LogoutController" href="#">Logout</a></li>


