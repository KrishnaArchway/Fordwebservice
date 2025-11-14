package com.archway.controller;

import com.archway.model.VinRequestEntity;
import com.archway.model.VinResponseEntity;
import com.archway.repository.DMLManager;
import com.archway.validationhelper.TokenHelper;
import com.archway.validationhelper.VinImplHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/newservices"})
public class VinNewController {
  private static final Logger log = LoggerFactory.getLogger(VinNewController.class);
  
  @Autowired
  private VinImplHelper vinImplHelper;
  
  @Autowired
  private DMLManager dmlManager;
  
  @Autowired
  private TokenHelper tokenHelper;
  
  @GetMapping
  public String health() {
    return "I'm Healthy!";
  }
  
  @PostMapping({"/vin/validatenewvin"})
  public ResponseEntity<VinResponseEntity> validateVinNewVehicle(@RequestBody VinRequestEntity vinRequestEntity, @RequestHeader("Authorization") String requestTokenHeader) throws JsonProcessingException, UnirestException {
    String methodName = "VinNewController.java validateVinNewVehicle(VinRequestEntity)";
    log.info("Entering ::: {}", methodName);
    VinResponseEntity vre = new VinResponseEntity();
    String key = vinRequestEntity.getKey().trim();
    String pacode = vinRequestEntity.getPaCode().trim();
    String vin = vinRequestEntity.getVin().trim();
    if (!this.tokenHelper.verifyToken(requestTokenHeader)) {
      vre.setErrorCode("4001");
      vre.setErrorMessage("Invalid/Expired Token");
      return ResponseEntity.ok(vre);
    } 
    try {
      if (this.vinImplHelper.dokeyValidation(key)) {
        if (this.vinImplHelper.doParametersValidation(vin, pacode)) {
          vre.setErrorCode("2000");
          vre.setErrorMessage("SUCCESS");
          vre.setPaCode(pacode);
          vre.setPayloadId(this.vinImplHelper.getPayloadID());
          vre.setVin(vin);
          String value = this.dmlManager.getAuthNewVinRegistration4WebService(vin, pacode);
          vre.setVinResult(value);
        } else {
          vre.setErrorCode("1010");
          vre.setErrorMessage("VIN OR PACODE NOT VALID");
          vre.setPaCode(pacode);
          vre.setPayloadId(this.vinImplHelper.getPayloadID());
          vre.setVin(vin);
          vre.setVinResult("N");
        } 
      } else {
        vre.setErrorCode("1000");
        vre.setErrorMessage("KEY NOT VALID");
        vre.setPaCode(pacode);
        vre.setPayloadId(this.vinImplHelper.getPayloadID());
        vre.setVin(vin);
        vre.setVinResult("N");
      } 
    } catch (Exception e) {
      log.warn("Exception e :" + e.getMessage());
      vre.setErrorCode("9999");
      vre.setErrorMessage("FATAL ERROR CONTACT ARCHWAY");
      vre.setPaCode(pacode);
      vre.setPayloadId(this.vinImplHelper.getPayloadID());
      vre.setVin(vin);
      vre.setVinResult("N");
    } finally {
      log.info("Exiting ::: {}", methodName);
    } 
    return ResponseEntity.ok(vre);
  }
}
