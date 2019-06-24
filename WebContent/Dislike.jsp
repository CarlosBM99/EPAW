<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<%
HttpSession session = request.getSession();
Integer numLikes = (Integer)session.getAttribute("numLikes"); 
%>
<div><i class="fa fa-heart"></i><%=numLikes%></div>