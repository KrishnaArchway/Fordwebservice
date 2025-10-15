package com.archway.model;

public class VinRequestEntity {
  private String key;
  
  private String paCode;
  
  private String vin;
  
  public void setKey(String key) {
    this.key = key;
  }
  
  public void setPaCode(String paCode) {
    this.paCode = paCode;
  }
  
  public void setVin(String vin) {
    this.vin = vin;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof VinRequestEntity))
      return false; 
    VinRequestEntity other = (VinRequestEntity)o;
    if (!other.canEqual(this))
      return false; 
    Object this$key = getKey(), other$key = other.getKey();
    if ((this$key == null) ? (other$key != null) : !this$key.equals(other$key))
      return false; 
    Object this$paCode = getPaCode(), other$paCode = other.getPaCode();
    if ((this$paCode == null) ? (other$paCode != null) : !this$paCode.equals(other$paCode))
      return false; 
    Object this$vin = getVin(), other$vin = other.getVin();
    return !((this$vin == null) ? (other$vin != null) : !this$vin.equals(other$vin));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof VinRequestEntity;
  }
  
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $key = getKey();
    result = result * 59 + (($key == null) ? 43 : $key.hashCode());
    Object $paCode = getPaCode();
    result = result * 59 + (($paCode == null) ? 43 : $paCode.hashCode());
    Object $vin = getVin();
    return result * 59 + (($vin == null) ? 43 : $vin.hashCode());
  }
  
  public String toString() {
    return "VinRequestEntity(key=" + getKey() + ", paCode=" + getPaCode() + ", vin=" + getVin() + ")";
  }
  
  public String getKey() {
    return this.key;
  }
  
  public String getPaCode() {
    return this.paCode;
  }
  
  public String getVin() {
    return this.vin;
  }
}
