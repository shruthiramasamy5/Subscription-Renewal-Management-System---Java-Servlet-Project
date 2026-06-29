<!DOCTYPE html>
<html>
<head><title>View Renewal</title></head>
<body>
    <h2>View Renewal Record</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="viewRecord">
        Subscription ID: <input type="text" name="subscriptionId"/><br/>
        Renewal Date:    <input type="date" name="renewalDate"/><br/>
        <input type="submit" value="Search"/>
    </form>
</body>
</html>