<!DOCTYPE html>
<html>
<head><title>Add Renewal</title></head>
<body>
    <h2>Add Renewal Record</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="newRecord">
        Subscriber Name: <input type="text" name="subscriberName"/><br/>
        Subscription ID: <input type="text" name="subscriptionId"/><br/>
        Renewal Date:    <input type="date" name="renewalDate"/><br/>
        Amount:          <input type="text" name="amount"/><br/>
        Status:
        <select name="status">
            <option value="Pending">Pending</option>
            <option value="Completed">Completed</option>
        </select><br/>
        Remarks: <input type="text" name="remarks"/><br/>
        <input type="submit" value="Add Record"/>
    </form>
</body>
</html>