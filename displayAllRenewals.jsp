<%@ page import="java.util.List, com.wipro.renewal.bean.RenewalBean" %>
<%
    List<RenewalBean> renewalList = (List<RenewalBean>) request.getAttribute("renewalList");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head><title>All Renewal Records</title></head>
<body>
    <h2>All Renewal Records</h2>
    <%
        if (message != null) {
    %>
        <p><%= message %></p>
    <%
        } else if (renewalList != null && !renewalList.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Renewal ID</th>
                <th>Subscriber Name</th>
                <th>Subscription ID</th>
                <th>Renewal Date</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Remarks</th>
            </tr>
            <%
                for (RenewalBean r : renewalList) {
            %>
            <tr>
                <td><%= r.getRenewalId() %></td>
                <td><%= r.getSubscriberName() %></td>
                <td><%= r.getSubscriptionId() %></td>
                <td><%= r.getRenewalDate() %></td>
                <td><%= r.getAmount() %></td>
                <td><%= r.getStatus() %></td>
                <td><%= r.getRemarks() %></td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p>No records available!</p>
    <%
        }
    %>
</body>
</html>