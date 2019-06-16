<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#navigation').load('MenuController');
	    $(".active").click(function(event) {
	    	if($(this).attr('id') == "HomeController"){
	    		$('#content').load('HomeController',{anonymous: "yes"});
	    	} else {
	    		$('#content').load('ContentController',{content: $(this).attr('id')});
	    	}
	        
	        });
	});
	</script>
	
	<head>
		
	</head>
	<body>
		<div class="col text-center">
			<div class="btn-group-vertical" style="padding: 20px">
				<a href="#" class="btn btn-primary btn-lg active" id="RegisterController" role="button" aria-pressed="true" style="margin: 20px">Register</a>
				<a href="#" class="btn btn-primary btn-lg active" id="LoginController" role="button" aria-pressed="true" style="margin: 20px">Log In</a>
				<a href="#" class="btn btn-primary btn-lg active" id="HomeController" role="button" aria-pressed="true" style="margin: 20px">Enter without logging</a>
			</div>
		</div>
	</body>
</html>