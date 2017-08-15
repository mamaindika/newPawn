<%-- 
    Document   : userBar
    Created on : Mar 2, 2017, 9:50:40 AM
    Author     : boc
--%>

<%@page import="services.UserDetails"%>
<%@page import="model.Branch"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .vertical-center {
        display: flex;
        align-items: center;
    }
    .row1 {
        height: 70px;
        background-color: #94b8b8;
    }
</style>

<script>
    function startTime() {
        var today = new Date();
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        
        m = checkTime(m);
        s = checkTime(s);
        document.getElementById('txt').innerHTML =
        h + ":" + m + ":" + s;
        var t = setTimeout(startTime, 500);
    }
    function checkTime(i) {
        if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
        return i;
    }
</script>


<div class="row1" style="padding-top: 5px;">
   
    
    <div class="col-sm-4">
            <%
                String date = Integer.toString(UserDetails.lastWorkDate); 
                String year  = date.substring(0,4);
                String month = date.substring(4,6);
                String day = date.substring(6,8);
                String dateFormat = year+"/"+month+"/"+day+"";
             %>
        <div class="col-sm-12">
            <p style="font-size: 20px;"><%=UserDetails.username%></p>
        </div>
        <div class="">
            <div class="col-sm-5" style="margin-bottom: 0px">
                 <p style="font-size: 20px;margin-bottom: 0px"><%=dateFormat %> </p>
             </div>
             <div class="col-sm-4">
                
             </div>
        </div>
    </div>
    <div class="col-sm-4">
        <p style="font-size: 20px;text-align:"><%=UserDetails.name%></p>
        
    </div>

    <div class="col-sm-4">
        <ul class="nav navbar-nav navbar-right vertical-center" style="padding-right:10px;">
            
            <li>
                <form>
                    <a class="btn btn-default btn-xs " href="dashboard.jsp" style="font-size: 15px;color: black;background-color: #c6d9ec;margin-right: 3px"> Home </a>
                </form>
            <li>
            <li>
                <form action="LogOutController" method="post">
                    <input class="btn btn-default btn-xs" type="submit" name="logout" style="font-size: 15px;color: black;background-color: #c6d9ec" value="Logout"> 
                </form>
            </li>
            
        </ul>
        <p style="font-size: 20px" id="txt"> </p>
    </div>
    
</div>
        
