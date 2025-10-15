package com.archway.model;

public class VinResponseEntity {
  private String errorCode;
  
  private String errorMessage;
  
  private String paCode;
  
  private String payloadId;
  
  private String vin;
  
  private String vinResult;
  
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
  
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  
  public void setPaCode(String paCode) {
    this.paCode = paCode;
  }
  
  public void setPayloadId(String payloadId) {
    this.payloadId = payloadId;
  }
  
  public void setVin(String vin) {
    this.vin = vin;
  }
  
  public void setVinResult(String vinResult) {
    this.vinResult = vinResult;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof VinResponseEntity))
      return false; 
    VinResponseEntity other = (VinResponseEntity)o;
    if (!other.canEqual(this))
      return false; 
    Object this$errorCode = getErrorCode(), other$errorCode = other.getErrorCode();
    if ((this$errorCode == null) ? (other$errorCode != null) : !this$errorCode.equals(other$errorCode))
      return false; 
    Object this$errorMessage = getErrorMessage(), other$errorMessage = other.getErrorMessage();
    if ((this$errorMessage == null) ? (other$errorMessage != null) : !this$errorMessage.equals(other$errorMessage))
      return false; 
    Object this$paCode = getPaCode(), other$paCode = other.getPaCode();
    if ((this$paCode == null) ? (other$paCode != null) : !this$paCode.equals(other$paCode))
      return false; 
    Object this$payloadId = getPayloadId(), other$payloadId = other.getPayloadId();
    if ((this$payloadId == null) ? (other$payloadId != null) : !this$payloadId.equals(other$payloadId))
      return false; 
    Object this$vin = getVin(), other$vin = other.getVin();
    if ((this$vin == null) ? (other$vin != null) : !this$vin.equals(other$vin))
      return false; 
    Object this$vinResult = getVinResult(), other$vinResult = other.getVinResult();
    return !((this$vinResult == null) ? (other$vinResult != null) : !this$vinResult.equals(other$vinResult));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof VinResponseEntity;
  }
  
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $errorCode = getErrorCode();
    result = result * 59 + (($errorCode == null) ? 43 : $errorCode.hashCode());
    Object $errorMessage = getErrorMessage();
    result = result * 59 + (($errorMessage == null) ? 43 : $errorMessage.hashCode());
    Object $paCode = getPaCode();
    result = result * 59 + (($paCode == null) ? 43 : $paCode.hashCode());
    Object $payloadId = getPayloadId();
    result = result * 59 + (($payloadId == null) ? 43 : $payloadId.hashCode());
    Object $vin = getVin();
    result = result * 59 + (($vin == null) ? 43 : $vin.hashCode());
    Object $vinResult = getVinResult();
    return result * 59 + (($vinResult == null) ? 43 : $vinResult.hashCode());
  }
  
  public String toString() {
    return "VinResponseEntity(errorCode=" + getErrorCode() + ", errorMessage=" + getErrorMessage() + ", paCode=" + getPaCode() + ", payloadId=" + getPayloadId() + ", vin=" + getVin() + ", vinResult=" + getVinResult() + ")";
  }
  
  public String getErrorCode() {
    return this.errorCode;
  }
  
  public String getErrorMessage() {
    return this.errorMessage;
  }
  
  public String getPaCode() {
    return this.paCode;
  }
  
  public String getPayloadId() {
    return this.payloadId;
  }
  
  public String getVin() {
    return this.vin;
  }
  
  public String getVinResult() {
    return this.vinResult;
  }
}
