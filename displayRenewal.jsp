<%@ page import="com.wipro.renewal.bean.RenewalBean" %>
<%
    RenewalBean renewal = (RenewalBean) request.getAttribute("renewal");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head><title>Renewal Details</title></head>
<body>
    <h2>Renewal Record Details</h2>
    <%
        if (message != null) {
    %>
        <p><%= message %></p>
    <%
        } else if (renewal != null) {
    %>
        <table border="1">
            <tr><th>Renewal ID</th>      <td><%= renewal.getRenewalId() %></td></tr>
            <tr><th>Subscriber Name</th> <td><%= renewal.getSubscriberName() %></td></tr>
            <tr><th>Subscription ID</th> <td><%= renewal.getSubscriptionId() %></td></tr>
            <tr><th>Renewal Date</th>    <td><%= renewal.getRenewalDate() %></td></tr>
            <tr><th>Amount</th>          <td><%= renewal.getAmount() %></td></tr>
            <tr><th>Status</th>          <td><%= renewal.getStatus() %></td></tr>
            <tr><th>Remarks</th>         <td><%= renewal.getRemarks() %></td></tr>
        </table>
    <%
        } else {
    %>
        <p>No matching records exists! Please try again!</p>
    <%
        }
    %>
</body>
</html>