<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".menu").click(function(event) {
			$('#content').load('ContentController', {
				content : $(this).attr('id')
			});
		});
	});
</script>

<li class="nav-item active"><a class="nav-link menu" id="LoginController" href="#">Login</a></li>
<li class="nav-item active"><a class="nav-link menu" id="RegisterController" href="#">Registration</a></li>
<li class="nav-item active"><a class="nav-link menu" id="HomeController" href="#">Enter without login</a></li>
