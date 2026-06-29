package com.wipro.renewal.dao;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import com.wipro.renewal.bean.*;
import com.wipro.renewal.util.*;
public class RenewalDAO {
    public String createRecord(RenewalBean bean) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.getDBConnection();
            String sql = "INSERT INTO RENEWAL_TB (RENEWALID, SUBSCRIBERNAME, SUBSCRIPTIONID, RENEWALDATE, AMOUNT, STATUS, REMARKS) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getRenewalId());
            ps.setString(2, bean.getSubscriberName());
            ps.setString(3, bean.getSubscriptionId());
            ps.setDate(4, new java.sql.Date(bean.getRenewalDate().getTime()));
            ps.setDouble(5, bean.getAmount());
            ps.setString(6, bean.getStatus());
            ps.setString(7, bean.getRemarks());
            int rows = ps.executeUpdate();
            if (rows > 0) return bean.getRenewalId();
        } catch (SQLException e) {
            e.printStackTrace();
            return "FAIL";
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "FAIL";
    }
    public RenewalBean fetchRecord(String subscriptionId, Date renewalDate) {
        RenewalBean bean = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getDBConnection();
            String sql = "SELECT RENEWALID, SUBSCRIBERNAME, SUBSCRIPTIONID, RENEWALDATE, AMOUNT, STATUS, REMARKS "
                       + "FROM RENEWAL_TB WHERE SUBSCRIPTIONID = ? AND RENEWALDATE = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, subscriptionId);
            ps.setDate(2, new java.sql.Date(renewalDate.getTime()));
            rs = ps.executeQuery();
            if (rs.next()) {
                bean = new RenewalBean();
                bean.setRenewalId(rs.getString("RENEWALID"));
                bean.setSubscriberName(rs.getString("SUBSCRIBERNAME"));
                bean.setSubscriptionId(rs.getString("SUBSCRIPTIONID"));
                bean.setRenewalDate(rs.getDate("RENEWALDATE"));
                bean.setAmount(rs.getDouble("AMOUNT"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
    public String generateRenewalID(String subscriptionId, Date renewalDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            DateFormat f = new SimpleDateFormat("yyyyMMdd");
            String temp = f.format(renewalDate);
            String subCode = subscriptionId.substring(0, 2).toUpperCase();
            con = DBUtil.getDBConnection();
            String sql = "SELECT RENEWAL_SEQ.NEXTVAL FROM DUAL";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int seq = rs.getInt(1);
                String seqVal = String.format("%02d", seq);
                return temp + subCode + seqVal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public boolean recordExists(String subscriptionId, Date renewalDate) {
        RenewalBean bean = fetchRecord(subscriptionId, renewalDate);
        return bean != null;
    }
    public List<RenewalBean> fetchAllRecords() {
        List<RenewalBean> list = new ArrayList<RenewalBean>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getDBConnection();
            String sql = "SELECT RENEWALID, SUBSCRIBERNAME, SUBSCRIPTIONID, RENEWALDATE, AMOUNT, STATUS, REMARKS "
                       + "FROM RENEWAL_TB";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                RenewalBean bean = new RenewalBean();
                bean.setRenewalId(rs.getString("RENEWALID"));
                bean.setSubscriberName(rs.getString("SUBSCRIBERNAME"));
                bean.setSubscriptionId(rs.getString("SUBSCRIPTIONID"));
                bean.setRenewalDate(rs.getDate("RENEWALDATE"));
                bean.setAmount(rs.getDouble("AMOUNT"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}