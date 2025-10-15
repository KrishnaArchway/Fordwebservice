package com.archway.validationhelper;

import com.archway.util.StringUtility;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VinImplHelper {
  private static final Logger log = LoggerFactory.getLogger(VinImplHelper.class);
  
  private VinImplHelper instance = null;
  
  @Value("${spring.web_service_accesskeys}")
  private String storedAccessKey;
  
  public boolean dokeyValidation(String accessKey) {
    String methodName = "VinController.java dokeyValidation (String)";
    log.info("Entering ::: {}", methodName);
    boolean returnFlag = false;
    if (StringUtility.isStringBlank(accessKey)) {
      log.warn("Incoming Access KeyValue is null ::: {}", accessKey);
      return false;
    } 
    if (this.storedAccessKey.matches(accessKey) && this.storedAccessKey.length() == accessKey.length())
      returnFlag = true; 
    if (!this.storedAccessKey.matches(accessKey) || this.storedAccessKey.length() != accessKey.length())
      log.error("Access Key Mis-Matches"); 
    log.info("Exiting ::: {}", methodName);
    return returnFlag;
  }
  
  public boolean doADFSTokenValidation(String authorization) {
	  String str;
    if (authorization != null)
       str = authorization.split(" ")[1]; 
    return false;
  }
  
  public boolean doParametersValidation(String vin, String pacode) {
    String methodName = "VinController.java doParametersValidation (String , String)";
    log.info("Entering ::: {}", methodName);
    boolean returnFlag = true;
    if (StringUtility.isStringBlank(vin))
      return false; 
    if (StringUtility.isStringBlank(pacode))
      return false; 
    if (vin.length() != 17)
      return false; 
    log.info("Exiting ::: {}", methodName);
    return returnFlag;
  }
  
  public String getPayloadID() {
    initialize();
    return System.currentTimeMillis() + "." + this.instance.getProcessID() + "." + this.instance.getRandomNumber() + "@Archway-Key-Code-Application.home";
  }
  
  private void initialize() {
    if (null == this.instance)
      this.instance = new VinImplHelper(); 
  }
  
  private Random random = null;
  
  public VinImplHelper() {
    this.random = new Random();
  }
  
  private int getProcessID() {
    return Thread.currentThread().hashCode();
  }
  
  private long getRandomNumber() {
    long randomNumber = this.random.nextLong();
    return Math.max(randomNumber, randomNumber * -1L);
  }
}
