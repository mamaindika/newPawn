<%-- 
    Document   : ticketInquiry
    Created on : Mar 2, 2017, 12:01:56 PM
    Author     : boc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"     src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <style>
        .rowCont{

          position: absolute;
          left:0px;
          right: 0px;
          bottom: 50px;
          top: 270px;
          display: table-row;
        }

       
    </style>


</head>
    
    <body onload="startTime()">
        <%@ include file="div/header.html" %>

        <%@ include file="div/userBar.jsp" %>
        <div class="rowCont " style="height: auto;">
            <%@ include file="div/divTicketInquiry.jsp" %>
        </div>
        <%@ include file="div/footer.html" %>
    </body>
</html>
