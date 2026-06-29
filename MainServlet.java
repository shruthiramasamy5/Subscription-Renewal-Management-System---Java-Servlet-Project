package com.wipro.renewal.servlets;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.wipro.renewal.bean.*;
import com.wipro.renewal.service.*;
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public String addRecord(HttpServletRequest request) {
        RenewalBean bean = new RenewalBean();
        try {
            String subscriberName = request.getParameter("subscriberName");
            String subscriptionId = request.getParameter("subscriptionId");
            String dateStr= request.getParameter("renewalDate");
            String amountStr= request.getParameter("amount");
            String status = request.getParameter("status");
            String remarks= request.getParameter("remarks");
            bean.setSubscriberName(subscriberName);
            bean.setSubscriptionId(subscriptionId);
            bean.setStatus(status);
            bean.setRemarks(remarks);
            if (dateStr != null && !dateStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(dateStr);
                bean.setRenewalDate(date);
            }
            if (amountStr != null && !amountStr.isEmpty()) {
                bean.setAmount(Double.parseDouble(amountStr));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
        Administrator admin = new Administrator();
        return admin.addRecord(bean);
    }
    public RenewalBean viewRecord(HttpServletRequest request) {
        try {
            String subscriptionId = request.getParameter("subscriptionId");
            String dateStr= request.getParameter("renewalDate");
            Date renewalDate = null;
            if (dateStr != null && !dateStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                renewalDate = sdf.parse(dateStr);
            }
            Administrator admin = new Administrator();
            return admin.viewRecord(subscriptionId, renewalDate);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<RenewalBean> viewAllRecords(HttpServletRequest request) {
        Administrator admin = new Administrator();
        return admin.viewAllRecords();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if ("newRecord".equals(operation)) {
            String result = addRecord(request);
            if (result == null || result.equals("FAIL") || result.equals("INVALID INPUT")
                    || result.equals("INVALID SUBSCRIBER NAME") || result.equals("INVALID SUBSCRIPTION ID")
                    || result.equals("INVALID AMOUNT") || result.equals("ALREADY EXISTS")) {
                response.sendRedirect("error.html");
            } else {
                response.sendRedirect("success.html");
            }
        } else if ("viewRecord".equals(operation)) {
            RenewalBean bean = viewRecord(request);
            if (bean == null) {
                request.setAttribute("message", "No matching records exists! Please try again!");
                request.getRequestDispatcher("displayRenewal.jsp").forward(request, response);
            } else {
                request.setAttribute("renewal", bean);
                request.getRequestDispatcher("displayRenewal.jsp").forward(request, response);
            }

        } else if ("viewAllRecords".equals(operation)) {
            List<RenewalBean> list = viewAllRecords(request);
            if (list == null || list.isEmpty()) {
                request.setAttribute("message", "No records available!");
                request.getRequestDispatcher("displayAllRenewals.jsp").forward(request, response);
            } else {
                request.setAttribute("renewalList", list);
                request.getRequestDispatcher("displayAllRenewals.jsp").forward(request, response);
            }
        }
    }
}