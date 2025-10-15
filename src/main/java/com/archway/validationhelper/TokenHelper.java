package com.archway.validationhelper;

import com.archway.model.DecodeTokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenHelper {
  private static final Logger log = LoggerFactory.getLogger(TokenHelper.class);
  
  private static final String TOKEN = "access_token";
  
  private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
  
  @Value("${spring.oauth2.url}")
  private String url;
  
  @Value("${spring.oauth2.resource}")
  private String resource;
  
  @Value("${spring.oauth2.client-id}")
  private String clientId;
  
  @Value("${spring.oauth2.client-secret}")
  private String clientSecret;
  
  private String body;
  
  private String getToken() throws UnirestException {
    String methodName = "TokenHelper.java getToken()";
    log.info("Entering ::: {}", methodName);
    return ((JsonNode)Unirest.post(this.url)
      .header("Content-Type", "application/x-www-form-urlencoded")
      .body(this.body).asJson().getBody())
      .getObject().getString("access_token");
  }
  
  public boolean verifyToken(String requestToken) throws UnirestException, JsonProcessingException {
    String methodName = "TokenHelper.java verifyToken(requestToken)";
    log.info("Entering ::: {}", methodName);
    String incomeToken = requestToken.replace("Bearer ", "");
    boolean checkComplete = completeTokenValuesCheck(incomeToken);
    log.info("Exiting ::: {}", methodName);
    return checkComplete;
  }
  
  private boolean completeTokenValuesCheck(String incomeToken) throws UnirestException {
    String methodName = "TokenHelper.java completeTokenValuesCheck()";
    log.info("Entering ::: {}", methodName);
    DecodeTokenResponse requestTokenDecoder = decodeJWT(incomeToken);
    DecodeTokenResponse apiTokenDecoder = decodeJWT(getToken());
    boolean tokenTypeCheck = requestTokenDecoder.getTyp().matches(apiTokenDecoder.getTyp());
    boolean algorithmCheck = requestTokenDecoder.getAlg().matches(apiTokenDecoder.getAlg());
    boolean x5tCheck = requestTokenDecoder.getX5t().matches(apiTokenDecoder.getX5t());
    boolean audienceCheck = requestTokenDecoder.getAud().matches(apiTokenDecoder.getAud());
    boolean appIdCheck = requestTokenDecoder.getAppId().matches(apiTokenDecoder.getAppId());
    boolean issuerCheck = requestTokenDecoder.getIss().matches(apiTokenDecoder.getIss());
    boolean timeChecker = timeChecker(requestTokenDecoder.getExp(), requestTokenDecoder.getIat());
    boolean appTypeCheck = requestTokenDecoder.getAppType().matches(apiTokenDecoder.getAppType());
    if (tokenTypeCheck && x5tCheck && algorithmCheck && audienceCheck && appIdCheck && timeChecker && issuerCheck & appTypeCheck) {
      log.info("completeTokenValuesCheck Check Passed");
    } else {
      log.info("completeTokenValuesCheck Check Failed");
    } 
    log.info("Exiting ::: {}", methodName);
    return (tokenTypeCheck && x5tCheck && algorithmCheck && audienceCheck && appIdCheck && timeChecker && issuerCheck & appTypeCheck);
  }
  
  private boolean timeChecker(Integer exp, Integer iat) {
    String methodName = "TokenHelper.java timeChecker()";
    log.info("Entering ::: {}", methodName);
    LocalDate issuedTime = Instant.ofEpochSecond(iat.intValue()).atZone(ZoneId.systemDefault()).toLocalDate();
    log.info("CHECKING  issued time " + issuedTime +" and local time "+LocalDate.now(), methodName);
    boolean issuedTimeCheck = LocalDate.now().isEqual(issuedTime) || LocalDate.now().minusDays(1).isEqual(issuedTime);
    log.info("CHECKING  issuedTimeCheck flag " + issuedTimeCheck , methodName);
		/*
		 * if(ChronoUnit.DAYS.between(LocalDate.now(), issuedTime) <=1 ) {
		 * issuedTimeCheck = true; }
		 */
    LocalDateTime expNow = LocalDateTime.now();
    LocalDateTime expirationTime = Instant.ofEpochSecond(exp.intValue()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    log.info("CHECKING  expirationTime " + expirationTime , methodName);
    if (expirationTime.isAfter(expNow) && issuedTimeCheck) {
      log.info("Time Check Passed");
    } else {
      log.info("Time Check Failed");
    } 
    log.info("Exiting ::: {}", methodName);
    return (expirationTime.isAfter(expNow) && issuedTimeCheck);
  }
  
  private DecodeTokenResponse decodeJWT(String token) {
    String methodName = "TokenHelper.java decodeJWT()";
    log.info("Entering ::: {}", methodName);
    DecodeTokenResponse tokenResponse = new DecodeTokenResponse();
    ObjectMapper mapper = new ObjectMapper();
    try {
      String[] split_string = token.split("\\.");
      String base64EncodedHeader = split_string[0];
      String base64EncodedBody = split_string[1];
      Base64 base64Url = new Base64(true);
      String header = new String(base64Url.decode(base64EncodedHeader));
      String body = new String(base64Url.decode(base64EncodedBody));
      Map<String, Object> bodyData = (Map<String, Object>)mapper.readValue(body, Map.class);
      Map<String, Object> headerData = (Map<String, Object>)mapper.readValue(header, Map.class);
      tokenResponse.setTyp((String)headerData.get("typ"));
      tokenResponse.setAlg((String)headerData.get("alg"));
      tokenResponse.setX5t((String)headerData.get("x5t"));
      tokenResponse.setAud((String)bodyData.get("aud"));
      tokenResponse.setExp((Integer)bodyData.get("exp"));
      tokenResponse.setAppId((String)bodyData.get("appid"));
      tokenResponse.setIat((Integer)bodyData.get("iat"));
      tokenResponse.setIss((String)bodyData.get("iss"));
      tokenResponse.setAppType((String)bodyData.get("apptype"));
    } catch (Exception tokenException) {
      log.error("Exception e : {}", tokenException.getMessage());
    } 
    log.info("Exiting ::: {}", methodName);
    return tokenResponse;
  }
  
  @PostConstruct
  public void init() {
    this.body = String.format("grant_type=client_credentials&resource=%s&client_id=%s&client_secret=%s", new Object[] { this.resource, this.clientId, this.clientSecret });
  }
}
