package com.archway.repository;

import com.archway.util.GageException;
import java.sql.CallableStatement;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DMLManager {
  private static final Logger log = LoggerFactory.getLogger(DMLManager.class);
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  public String getAuthNewVinRegistration4WebService(String vin, String pacode) throws GageException {
    String methodName = "DMLManager.java getAuthNewVinRegistration4WebService ( String , String )";
    log.info("Entering :: {}", methodName);
    String returnFlag = "";
    String sql = "{call keycode_pkg.New_Authentification_request(?,?,?,?,?)}";
    try {
      log.warn("vin \t\t:  {} ,  pacode : {}", vin, pacode);
      log.warn("Length of vin "  + vin.length());
      Connection conn = this.jdbcTemplate.getDataSource().getConnection();
      try {
        CallableStatement cstmt = conn.prepareCall(sql);
        cstmt.setString(1, vin);
        cstmt.setString(2, pacode);
        cstmt.registerOutParameter(3, 12);
        cstmt.registerOutParameter(4, 12);
        cstmt.registerOutParameter(5, 12);
        cstmt.execute();
        String yesNo = cstmt.getString(3);
        returnFlag = yesNo;
        log.debug("yesNo : " + yesNo);
        log.warn("sqlCode \t\t: {}, sqlMessage \t: {}, returnFlag : {}", new Object[] { cstmt.getString(4), cstmt.getString(5), yesNo });
      } catch (Exception e) {
        log.warn("Exception " + e.getMessage());
      } finally {
        log.info("Exiting Connection");
        conn.close();
      } 
    } catch (Exception e) {
      log.warn("Exception " + e.getMessage());
      throw new GageException(e.getMessage(), 0, e);
    } finally {
      log.info("Exiting :: {}", methodName);
    } 
    return returnFlag;
  }
  
  public String getAuthVinRegistration4WebService(String vin, String pacode) throws Exception {
    String methodName = "DMLManager.java getVinRegistration4WebService ( String , String )";
    log.info("Entering :: {}", methodName);
    String returnFlag = "";
    String sql = "{call keycode_pkg.Authentification_request(?,?,?,?,?)}";
    try {
      log.warn("vin \t\t:  {} ,  pacode : {}", vin, pacode);
        log.warn("Length of vin "  + vin.length());
      Connection conn = this.jdbcTemplate.getDataSource().getConnection();
      try {
        CallableStatement cstmt = conn.prepareCall(sql);
        cstmt.setString(1, vin);
        cstmt.setString(2, pacode);
        cstmt.registerOutParameter(3, 12);
        cstmt.registerOutParameter(4, 12);
        cstmt.registerOutParameter(5, 12);
        cstmt.execute();
        String yesNo = cstmt.getString(3);
        returnFlag = yesNo;
        log.debug("yesNo : " + yesNo);
        log.warn("sqlCode \t\t: {}, sqlMessage \t: {}, returnFlag : {}", new Object[] { cstmt.getString(4), cstmt.getString(5), yesNo });
      } catch (Exception e) {
        log.warn("Exception " + e.getMessage());
      } finally {
        log.info("Exiting Connection");
        conn.close();
      } 
    } catch (Exception e) {
      log.warn("Exception " + e.getMessage());
      throw new GageException(e.getMessage(), 0, e);
    } finally {
      log.info("Exiting :: {}", methodName);
    } 
    return returnFlag;
  }
}
