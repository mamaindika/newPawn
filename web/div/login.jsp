<style>
      .well{
          padding: 5px;
      }
  </style>
<!--Page Content Start-->
<div class="container" >    
  <div class="row" >
    <div class="col-md-4 col-md-offset-4 well" style="margin-top:150PX;margin-bottom:100PX" >
      <form action="LoginController" method="POST" style="padding:55px;background-color:  #95b7b7">
       
            <%
                if(null!=request.getAttribute("validationMessage")){
                    String errMgs = (String)request.getAttribute("validationMessage");
                    if(errMgs.equals("DayEndSuccess")){%>
                        <script>
                                bootbox.alert("This Day End Success!");
                        </script>
                    <%    
                    }
                    else if(errMgs.equals("Invalidusernamepassword")){%>

                        <div class="alert alert-dismissible alert-danger" style="margin-top:20px;padding-top:7px;padding-bottom: 3px;padding-right:25px">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong><% out.println(request.getAttribute("validationMessage")); %></strong>
                        </div>
                    <%
                    }
                    else if(errMgs.equals("Unregistered user")){%>

                        <div class="alert alert-dismissible alert-danger" style="margin-top:20px;padding-top:7px;padding-bottom: 3px;padding-right:25px">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong><% out.println(request.getAttribute("validationMessage")); %></strong>
                        </div>
                    <%
                    }
                }
            %>    
          <label><b>Username</b></label>
          <input type="text" placeholder="Enter Username" name="username" class="form-control" style="background-color:#e6ffe6;text-transform:uppercase" name="uname" required>

          <label><b>Password</b></label>
          <input type="password" placeholder="ENTER PASSWORD" name="password"  class="form-control" style="background-color: 	 #e6ffe6" name="psw" required>
            <br>
          <button type="submit" class="btn btn-md btn-primary btn-block">Login</button>
         
      </form>
    </div>
   
  </div>
</div>
<!--Page Content end-->
