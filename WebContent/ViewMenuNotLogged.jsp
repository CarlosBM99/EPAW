<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".menu").click(function(event) {
			if($(this).attr('id') === "HomeController"){
				$('#content').load('HomeController', {anonymous: "no"})
			} else {
				$('#content').load('ContentController', {
					content : $(this).attr('id')
				});
			}
			
		});
	});
</script>

<li class="nav-item active"><a class="nav-link menu" id="LoginController" href="#">Login</a></li>
<li class="nav-item active"><a class="nav-link menu" id="RegisterController" href="#">Registration</a></li>
<li class="nav-item active"><a class="nav-link menu" id="HomeController" href="#">Menu</a></li>
