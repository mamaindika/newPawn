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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .rowCont{

          position: absolute;
          left:0px;
          right: 0px;
          bottom: 50px;
          top: 270px;
          display: table-row;
        }
        
        .bs-example{
            margin: 20px;
        }
    </style>
       
   
    

    <script type="text/javascript">
    $(document).ready(function(){
        function alignModal(){
            var modalDialog = $(this).find(".modal-dialog");

            // Applying the top margin on modal dialog to align it vertically center
            modalDialog.css("margin-top", Math.max(0, ($(window).height() - modalDialog.height()) / 2));
        }
        // Align modal when it is displayed
        $(".modal").on("shown.bs.modal", alignModal);

        // Align modal when user resize the window
        $(window).on("resize", function(){
            $(".modal:visible").each(alignModal);
        });   
    });
    </script>
    

</head>
    <%
        if(null!=request.getAttribute("validationMessage")){
            String errMgs = (String)request.getAttribute("validationMessage");
            if(errMgs.equals("RedemConfirmFail")){%>
                <script>
                        bootbox.alert("RedemConfirmFail");
                </script>
            <%    
            }
        }
    %>
    <body onload="startTime()">
        <%@ include file="div/header.html" %>

        <%@ include file="div/userBar.jsp" %>
        <div class="rowCont " style="height: auto;">
            <%@ include file="div/divRedemption.jsp" %>
        </div>
        <%@ include file="div/footer.html" %>
    </body>
</html>
                            		