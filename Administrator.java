package com.wipro.renewal.service;
import java.util.Date;
import java.util.*;
import com.wipro.renewal.bean.*;
import com.wipro.renewal.dao.*;
import com.wipro.renewal.util.*;
public class Administrator {
    public String addRecord(RenewalBean bean) {
        try {
            if (bean == null|| bean.getSubscriberName() == null|| bean.getSubscriptionId() == null|| bean.getRenewalDate() == null) {
                throw new InvalidInputException();
            }
        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
        if (bean.getSubscriberName().length() < 2) {
            return "INVALID SUBSCRIBER NAME";
        }
        if (bean.getSubscriptionId().length() < 2) {
            return "INVALID SUBSCRIPTION ID";
        }
        if (bean.getAmount() <= 0) {
            return "INVALID AMOUNT";
        }
        RenewalDAO dao = new RenewalDAO();
        if (dao.recordExists(bean.getSubscriptionId(), bean.getRenewalDate())) {
            return "ALREADY EXISTS";
        }
        String renewalId = dao.generateRenewalID(bean.getSubscriptionId(), bean.getRenewalDate());
        if (renewalId == null) {
            return "FAIL";
        }
        bean.setRenewalId(renewalId);
        String result = dao.createRecord(bean);
        if (result.equals("FAIL")) {
            return "FAIL";
        }
        return "SUCCESS";
    }
    public RenewalBean viewRecord(String subscriptionId, Date renewalDate) {
        RenewalDAO dao = new RenewalDAO();
        return dao.fetchRecord(subscriptionId, renewalDate);
    }
    public List<RenewalBean> viewAllRecords() {
        RenewalDAO dao = new RenewalDAO();
        return dao.fetchAllRecords();
    }
}