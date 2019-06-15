<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<script type="text/javascript">
$(document).ready(function() {
	    $.ajaxSetup({ cache: false }); // Avoids Internet Explorer caching!
	    $('#navigation').load('MenuController');
	    setTimeout(function(){
		    $('#content').load('HomeController');
	    },2000);
});


</script>

<div class="alert alert-primary" role="alert">
  Register for user ${sessionScope.nickname} done! Logining...
</div>