<%-- 
    Document   : dashboard
    Created on : Mar 2, 2017, 9:24:50 AM
    Author     : it177479
--%>

<%@page import="services.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
  <title></title>
  <meta  charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/styles.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/bootbox.min.js"></script>
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
      .well{
          padding: 5px;
      }
      .jumbotron{
          background-color: #d1e0e0;
      }
      .vertical-center {
        display: flex;
        align-items: center;
      }
      .modal-content{
          background-color: #f0f5f5;
      }
      .modal-content 
  </style>


</head>
<body onload="startTime();">
<script type="text/javascript">

</script>
<%@ include file="div/header.html" %>

<%@ include file="div/userBar.jsp" %>

<div class="rowCont " style="height: auto;">
    <%@ include file="div/sideNav.html" %>
   
    <%
     int state = UserDetails.runningStatus;
     if(state == 1){
    %>
    <script>
        
        document.getElementById("DayStart").disabled = true;
        document.getElementById("DayEnd").disabled = false;
        document.getElementById("DayReopen").disabled = true;
        document.getElementById("PartPayment").disabled = false;
        document.getElementById("TicketInquiry").disabled = false;
        document.getElementById("MarkLostTicket").disabled = false;
        document.getElementById("Reports").disabled = false;
        document.getElementById("Redemption").disabled = false;
        
    </script>
   
    <% 
     }
     else if(state == 9){
     %>
     <script>
         
        document.getElementById("DayStart").disabled = false;
        document.getElementById("DayEnd").disabled = true;
        document.getElementById("DayReopen").disabled = false; 
        document.getElementById("PartPayment").disabled = true;
        document.getElementById("TicketInquiry").disabled = true;
        document.getElementById("MarkLostTicket").disabled = true;
        document.getElementById("Reports").disabled = true;
        document.getElementById("Redemption").disabled = true;
    </script>
    <%
     }
    
    
    %>
    
    <%
        if(null!=request.getAttribute("validationMessage")){
            String errMgs = (String)request.getAttribute("validationMessage");
            if(errMgs.equals("PartPaymentErr")){%>
                <script>
                        bootbox.alert("Unknown Ticket Number!");
                </script>
            <%    
            }
            else if(errMgs.equals("RedemptionErr")){%>
                <script>
                    bootbox.alert("Unknown Ticket Number!");
                </script>
            <%
            }
            else if(errMgs.equals("DayEndFail")){%>
                <script>
                    bootbox.alert("This DayEndFail!");
                </script>
            <%
            }
            else if(errMgs.equals("DayStartSuccess")){%>
                <script>
                    bootbox.alert("DayStartSucess!");
                </script>
            <%
            }
            else if(errMgs.equals("DayStartFail")){%>
                <script>
                    bootbox.alert("This DayStartFail!");
                </script>
            <%
            }
            else if(errMgs.equals("PartPayConfirmSuccess")){%>
                <script>
                    bootbox.alert("This PartPayConfirmSuccess!");
                </script>
            <%
            }
            else if(errMgs.equals("RedemConfirmSuccess")){%>
                <script>
                    bootbox.alert("This RedemConfirmSuccess!");
                </script>
            <%
            }
                        else if(errMgs.equals("TicketInquiryErr")){%>
                <script>
                    bootbox.alert("Unknown Ticket Number!");
                </script>
            <%
            }

       
        }
    %>
    <!--Ticket Inquiry div Start-->
    <div class="col-sm-10 textContainer_about" id="pages_about"  style="padding-top: 10px;border:1px solid white;height: 100%;overflow-y:auto;" >
        <!--Ticket Inquiry div start-->
        <div class="textWord_about " data-link="TicketInquiry" style="background-color:graytext;margin-left: 10px;margin-right:10px;height: 98%">
                <div class="row well vertical-center" style="height:100%;background-color:#b3cbcb" >
                    <div class="col-sm-6 col-sm-offset-3  " style="">
                        <form  class=""action="TicketInquiryController" method="POST" style="padding:55px;background-color:  #95b7b7">
                           <fieldset>
                               <legend>Ticket Inquiry</legend>
                               <div class="form-group">
                                 <label for="" class="col-lg-3 control-label">Ticket Number</label>
                                 <div class="col-lg-9">
                                     <input type="text" class="form-control" id="ticketNo" name="ticketNo" placeholder="Enter Ticket Number">
                                 </div>
                               </div>
                               <div class="form-group" >
                                 
                                 <div class="col-lg-3 col-lg-offset-9" style="padding-top: 5px">
                                   <button type="submit" class="btn btn-primary">Submit</button>
                                 </div>
                               </div>
                           </fieldset>
                        </form>
                    </div>
                </div>
        </div>
        <!--Ticket Inquiry div end-->
        
        <!--Day End div start-->
        <div class="textWord_about" data-link="DayEnd" id="DayEnd" style="background-color:graytext;margin-left: 10px;margin-right:10px;height: 98%">
            <div class="row well vertical-center" style="height:100%;background-color:#b3cbcb" >
                    <div class="col-sm-6 col-sm-offset-3" style="">
                        <form action="DayEndController" method="POST" style="padding:55px;background-color:  #95b7b7">
                           <fieldset>
                               <legend>Day End Process...</legend>
                              
                               <div class="form-group">
                                   <input type="hidden" name="Sequence" id="Sequence" value="<%=UserDetails.sequence %>">
                                 <label for="" class="col-lg control-label" >Last Business Date</label>
                                 <div class="col-sm-12" style="padding:0px">
                                    <%
                                       int DEndDateLast = UserDetails.lastWorkDate;
                                       DateFormat df = new DateFormat();
                                       String DEndDateFormatLast = df.getDateFormat(DEndDateLast);
                                    %>
                                    <input class="form-control" disabled="" id="DayEndLast" name="DayEndLast" type="text" value="<%=DEndDateFormatLast %>" /> 
                                 </div>
                               </div>
                               <div class="form-group">
                                 <label for="" class="col-lg control-label">Next Business Date</label>
                                 <div class="col-sm-12" style="padding:0px">
                                    <%
                                       int DEndDateNext = UserDetails.nextWorkDate;
                                       String DEndDateFormatnext = df.getDateFormat(DEndDateNext);
                                                                           %>
                                    <input class="form-control"  disabled="" id="DayEndNext" name="DayEndNext" type="text" value="<%=DEndDateFormatnext %>" /> 
                                 </div>
                               </div>                               
                               <div class="form-group" >
                                 
                                 <div class=" pull-right" style="margin-top: 8px">
                                   <button type="submit" class="btn btn-primary">Start</button>
                                 </div>
                               </div>
                           </fieldset>
                        </form>
                    </div>
                </div>
        </div>

        <!--Day end div end-->
        
        <!--Day Start div start-->
        <div class="textWord_about" data-link="DayStart" id="DayStart" style="background-color:graytext;margin-left: 10px;margin-right:10px;height: 98%">
            <div class="row well vertical-center" style="height:100%;background-color:#b3cbcb" >
                    <div class="col-sm-6 col-sm-offset-3" style="height: auto;">
                        <form action="DayStartController" method="POST" style="padding:55px;background-color:  #95b7b7">
                           <fieldset>
                               <legend>Day Start...</legend>
                               <div class="form-group">
                                 <input type="hidden" name="Sequence" id="Sequence" value="<%=UserDetails.sequence %>">
                                 <input type="hidden" name="username" id="Sequence" value="<%=UserDetails.username %>">
                                 <input type="hidden" name="branchname" id="Sequence" value="<%=UserDetails.name %>">
                                 <label for="" class="col-lg control-label" >Last Business Date</label>
                                 <div class="col-sm-12" style="padding:0px">
                                    <%
                                       int DStartDateLast = UserDetails.lastWorkDate; 
                                       String DStartDateFormatLast = df.getDateFormat(DStartDateLast);
                                       
                                    %>
                                    <input class="form-control"  disabled="" id="DayStartNext" name="DayStartNext" type="text" value="<%=DStartDateFormatLast %>" />
                                 </div>
                               </div>
                               <div class="form-group">
                                 <label for="" class="col-lg control-label">Next Business Date</label>
                                 <div class="col-sm-12" style="padding:0px">
                                    <%
                                       int DStartDateNext = UserDetails.nextWorkDate; 
                                       String DStartDateFormatNext = df.getDateFormat(DStartDateNext);
                                       
                                    %>
                                    <input class="form-control"  disabled="" id="DayStartNext" name="DayEndNext" type="text" value="<%=DStartDateFormatNext %>" />
                                 </div>
                               </div>                               
                               <div class="form-group" >
                                 
                                 <div class=" pull-right" style="margin-top: 5px">
                                   <button type="submit" class="btn btn-primary">Start</button>
                                 </div>
                               </div>
                           </fieldset>
                        </form>
                    </div>
                </div>
        </div>
        <!--Day Start div end-->
        
        <!--Day Reopen div start-->
        <div class="textWord_about" data-link="DayReopen" style="background-color:graytext;margin-left: 10px;margin-right:10px;">
            <div class="row well" style="height:fit-content;background-color:#b3cbcb">
                <div class="jumbotron" style="padding: 0px">
                    <div class="col-sm-6 col-sm-offset-3">
                        <h1>Day Reopen </h1>
                    </div>
                </div>
            </div>
        </div>
        <!--Day Reopen div end-->
        
        <!--Part Payment div start-->
        <div class="textWord_about" data-link="PartPayment" style="background-color:graytext;margin-left: 10px;margin-right:10px;height: 98%">
                <div class="row well vertical-center" style="height:100%;background-color:#b3cbcb" >
                    <div class="col-sm-6 col-sm-offset-3  " style="">
                        <form  class="" action="PartPaymentController" method="POST" style="padding:55px;background-color:  #95b7b7">
                           <fieldset>
                               <legend>Part Payment</legend>
                               <div class="form-group">
                                 <label for="" class="col-lg-3 control-label">Ticket Number</label>
                                 <div class="col-lg-9">
                                     <input type="text" class="form-control" id="ticketNo" name="ticketNo" placeholder="Enter Ticket Number">
                                 </div>
                               </div>
                               <div class="form-group" >
                                 
                                 <div class="col-lg-3 col-lg-offset-9" style="padding-top: 5px">
                                   <button type="submit" class="btn btn-primary">Submit</button>
                                 </div>
                               </div>
                           </fieldset>
                        </form>
                    </div>
                </div>
        </div>
        <!--Part Payment div end-->
        
        <!--Redemption div start-->
        <div class="textWord_about" data-link="Redemption" style=";margin-left: 10px;margin-right:10px;height: 98%">
                <div class="row well vertical-center" style="height:100%;background-color:#b3cbcb" >
                    <div class="col-sm-6 col-sm-offset-3  " style="">
                        <form  class=""action="RedemptionController" method="POST" style="padding:55px;background-color:  #95b7b7">
                           <fieldset>
                               <legend>Redemption</legend>
                               <div class="form-group">
                                 <label for="" class="col-lg-3 control-label">Ticket Number</label>
                                 <div class="col-lg-9">
                                     <input type="text" class="form-control" id="ticketNo" name="ticketNo" placeholder="Enter Ticket Number">
                                 </div>
                               </div>
                               <div class="form-group" >
                                 
                                 <div class="col-lg-3 col-lg-offset-9" style="padding-top: 5px">
                                   <button type="submit" class="btn btn-primary">Submit</button>
                                 </div>
                               </div>
                           </fieldset>
                        </form>
                    </div>
                </div>
        </div>
        <!--Redemption div end-->
        
        <!--Mark Lost Ticket div start-->
        <div class="textWord_about" data-link="MarkLostTicket" style="background-color:graytext;margin-left: 10px;margin-right:10px;height: 98%">
                <div class="row well vertical-center" style="height:100%;background-color:#b3cbcb" >
                    <div class="col-sm-6 col-sm-offset-3  " style="">
                        <form  class=""action="MarkLostTicketController" method="POST" style="padding:55px;background-color:  #95b7b7">
                           <fieldset>
                               <legend>Mark Lost Ticket</legend>
                               <div class="form-group">
                                 <label for="" class="col-lg-3 control-label">Ticket Number</label>
                                 <div class="col-lg-9">
                                     <input type="text" class="form-control" id="ticketNo" name="ticketNo" placeholder="Enter Ticket Number">
                                 </div>
                               </div>
                               <div class="form-group" >
                                 
                                 <div class="col-lg-3 col-lg-offset-9" style="padding-top: 5px">
                                   <button type="submit" class="btn btn-primary">Submit</button>
                                 </div>
                               </div>
                           </fieldset>
                        </form>
                    </div>
                </div>
        </div>
        <!--Mark Lost Ticket div end-->
        
        <!--Reports div start-->
        <div class="textWord_about" data-link="Reports" style="background-color:graytext;margin-left: 10px;margin-right:10px;">
            <div class="row well" style="height:fit-content;background-color:#b3cbcb">
                <div class="jumbotron" style="padding: 0px">
                    <div class="col-sm-6 col-sm-offset-3">
                        <h1> Reports </h1>
                    </div>
                </div>
            </div>
        </div>
        <!--Reports div end-->

    </div>
</div>

    

<%@ include file="div/footer.html" %>

<script type="text/javascript">

    $('.textWord_about').hide();

    $('.link').click(function() {
        $('.textWord_about').hide();       
        $('.textWord_about[data-link=' + $(this).data('link') + ']').fadeIn({
            width: '200px'
        }, 300);
    });
</script> 

</body>

