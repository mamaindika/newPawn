 <%@page import="model.CustomerDetails"%>
<%@page import="services.DateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.TicketDetails"%>
<div class="panel panel-primary" style="height: 100%">
        <div class="panel-heading " style="background-color: #527a7a">
          <h2 class="panel-title">Tickets </h2>
        </div>
        <%
            TicketDetails td = (TicketDetails)request.getAttribute("TicketDetails"); 
            String TicketNo  = request.getAttribute("TicketNo").toString();
            DecimalFormat priceFormatter = new DecimalFormat("#,#00.00");

        %>


        <div class="panel-body panel-scroll" style="height:92%;overflow-y: auto">
            <table class="table">

                <tbody>
                  <tr>
                      <td><b>Ticket number</b></td>
                    <td><b><%=TicketNo %></b></td>
                  </tr>
                  <tr>
                    <td>Type</td>
                    <td><%=td.getTickettype() %></td>
                  </tr>
                  <tr>
                    <td>Ticket status</td>
                    <td><%=td.getTicketstatus() %></td>
                  </tr>
                  <tr>
                    <td>Loan purpose </td>
                    <td><%=td.getLoanpurposecode() %></td>
                  </tr>
                  <tr>
                    <td>Period</td>
                    <td><%=td.getPeriod() %></td>
                  </tr>
                  <%
                    DateFormat df = new DateFormat();
                  %>
                  <tr>
                    <td>Date granted</td>
                    <td><%=df.getDateFormat(td.getDategranted()) %></td>
                  </tr>
                  <tr>
                    <td>Maturity date</td>
                    <td><%=df.getDateFormat(td.getMaturitydate()) %></td>
                  </tr>
                  <tr>
                    <td>Assessed value</td>
                    <td><%=priceFormatter.format(Double.parseDouble(td.getAssessedvalue())) %></td>
                  </tr>
                  <tr>
                    <td>Granted amount</td>
                    <td><%=priceFormatter.format(Double.parseDouble(td.getGrantedamount())) %></td>
                  </tr>
                  <tr>
                    <td>Interest rate</td>
                    <td><%=td.getInterestrate() %></td>
                  </tr>
                  <tr>
                    <td>Number of articles</td>
                    <td><%=td.getNumberofarticles() %></td>
                  </tr>
                  <tr>
                  <tr>
                   <td>Date of NPA transfer</td>
                   <td><%=df.getDateFormat(td.getDateofNPAtransfer()) %></td>
                  </tr>
                  <tr>
                    <td>Last transaction date</td>
                    <td><%=df.getDateFormat(td.getLasttransactiondate()) %></td>
                  </tr>
                              
                </tbody>
              </table>
        </div>
    </div>
</div>
<!-- -->
<div class="col-sm-9 well" style="height: 100%;" style="background-color:">
    <div class="panel panel-primary" style="height:19%">
        <div class="panel-heading" style="background-color: #527a7a">
          <h3 class="panel-title">Customer</h3>
        </div>

        <div class="panel-body panel-scroll" style="padding-right: 10px;height: 80%; padding-bottom: 3px;overflow-y: auto">

                <%
                    CustomerDetails cd = (CustomerDetails)request.getAttribute("CustomerDetails");
                    String customer = cd.getSalutation()+"."+cd.getFirstname()+" "+cd.getLastname();
                %>
                <div class="col-sm-6">
                    Customer ID:<%=td.getCustomerID() %><br>
                    Name: <%=customer %>
                </div>
                <div class="col-sm-6" style="text-align: right">
                    NIC No: <%=cd.getIdnumber() %>
                </div>

        </div>
    </div>                
    <div class="panel panel-primary " style="height: 46%;" >
        <div class="panel-heading" style="background-color: #527a7a">
          <h3 class="panel-title"> Ticket articles</h3>
        </div>

        <script>
            var json =<%=request.getAttribute("ArticleDetailsObj").toString() %>
        </script>

        <div class="panel-body panel-scroll" style="height: 92%;overflow-y: auto">

            <table class="table" id="ArticleTable">
                <thead>
                  <tr>

                    <th>No of sItems</th>
                    <th>Caretage</th>
                    <th>Gross weight</th>
                    <th>Net weight</th>
                    <th>Type of Description</th>
                    <th>Assessed value</th>
                  </tr>
                </thead>
                <tbody>
                <script>
                    $(document).ready(function () {
                       //var json = [{"User_Name":"John Doe","score":"10","team":"1"},{"User_Name":"Jane Smith","score":"15","team":"2"},{"User_Name":"Chuck Berry","score":"12","team":"2"}];

                       var tr;
                       for (var i = 0; i < json.length; i++) {
                           tr = $('<tr/>');
                           tr.append("<td>" + json[i].typedescription+ "</td>");
                           tr.append("<td>" + json[i].noofitems + "</td>");                                       tr.append("<td>" + json[i].caretage+ "</td>");
                           tr.append("<td>" + json[i].grossweight+ "</td>");
                           tr.append("<td>" + json[i].netweight+ "</td>");
                           tr.append("<td>" + NumberFormat(json[i].assessedvalue)+ "</td>");
                           $('#ArticleTable').append(tr);
                       }
                   });                                
                    function NumberFormat(yourNumber) {
                         //Seperates the components of the number
                         var components = yourNumber.toString().split(".");
                         //Comma-fies the first part
                         components [0] = components [0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                         //Combines the two sections
                         return components.join(".");
                    }

                </script>   
                </tbody>
              </table>
        </div>
    </div>
    <div class="panel panel-primary " style="height: 25%;" >
        <div class="panel-heading" style="background-color: #527a7a">
          <h3 class="panel-title"> Due Accounts</h3>
        </div>
        <div class="panel-body panel-scroll" style="padding-right: 10px;height: 70%; padding-bottom: 3px;overflow-y: auto">
            <div class="col-sm-6">
                <div class="col-sm-10">
                    <table class="table">
                        <tbody>
                          <tr>
                            <td>Paid Interest</td>
                            <td><%=td.getPaidInterest() %></td>
                          </tr>
                          <tr>
                            <td>Paid Capital</td>
                            <td><%=td.getPaidCapital() %></td>
                          </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="col-sm-12">
                <table class="table">
                    <tbody >
                        <tr>
                          <td>Interest Due</td>
                          <td id="IntDue"><%=priceFormatter.format(Double.parseDouble(td.getInterestDue())) %></td>
                        </tr>

                        <tr>
                        <td>Capital Due</td>
                          <td id="CapDue"><%=priceFormatter.format(Double.parseDouble(td.getBalanceamount())) %></td>
                        </tr>
                        <%
                         double total = Double.parseDouble(td.getInterestDue().trim())+Double.parseDouble(td.getBalanceamount().trim());
                        %>
                        <tr>
                          <td><b>Total Due</b></td>
                          <td id="totalDue"><b><%=priceFormatter.format(total) %></b></td>
                        </tr>
                    </tbody>
                </table>

                </div>
            </div>
        </div>
    </div>
                        




