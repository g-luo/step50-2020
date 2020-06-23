<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.google.sps.models.*" %>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Log In Test</title>
   <% User user = null;
   if (session.getAttribute("userID") != null) {
      user = Database.getUserByID((long) session.getAttribute("userID")); 
   } %>
</head>
   
<body onload="load()">
  <p id="log"></p> 
  
  <% if (session.getAttribute("userID") != null) { %>
  <p> <%= session.getAttribute("userID") %>, <%= user.getNickname() %> </p>
  <% } %>

  <script>
    function load() {
      var xhttp = new XMLHttpRequest();
      xhttp.open("GET", "/Auth", true);
      xhttp.onreadystatechange = function() {
        document.getElementById("log").innerHTML = this.responseText;
      }
      xhttp.send();
    }
  </script>

</body>
</html>