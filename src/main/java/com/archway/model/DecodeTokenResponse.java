package com.archway.model;

public class DecodeTokenResponse {
  private String aud;
  
  private Integer exp;
  
  private Integer iat;
  
  private String iss;
  
  private String appType;
  
  private String appId;
  
  private String typ;
  
  private String alg;
  
  private String x5t;
  
  public void setAud(String aud) {
    this.aud = aud;
  }
  
  public void setExp(Integer exp) {
    this.exp = exp;
  }
  
  public void setIat(Integer iat) {
    this.iat = iat;
  }
  
  public void setIss(String iss) {
    this.iss = iss;
  }
  
  public void setAppType(String appType) {
    this.appType = appType;
  }
  
  public void setAppId(String appId) {
    this.appId = appId;
  }
  
  public void setTyp(String typ) {
    this.typ = typ;
  }
  
  public void setAlg(String alg) {
    this.alg = alg;
  }
  
  public void setX5t(String x5t) {
    this.x5t = x5t;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof DecodeTokenResponse))
      return false; 
    DecodeTokenResponse other = (DecodeTokenResponse)o;
    if (!other.canEqual(this))
      return false; 
    Object this$aud = getAud(), other$aud = other.getAud();
    if ((this$aud == null) ? (other$aud != null) : !this$aud.equals(other$aud))
      return false; 
    Object this$exp = getExp(), other$exp = other.getExp();
    if ((this$exp == null) ? (other$exp != null) : !this$exp.equals(other$exp))
      return false; 
    Object this$iat = getIat(), other$iat = other.getIat();
    if ((this$iat == null) ? (other$iat != null) : !this$iat.equals(other$iat))
      return false; 
    Object this$iss = getIss(), other$iss = other.getIss();
    if ((this$iss == null) ? (other$iss != null) : !this$iss.equals(other$iss))
      return false; 
    Object this$appType = getAppType(), other$appType = other.getAppType();
    if ((this$appType == null) ? (other$appType != null) : !this$appType.equals(other$appType))
      return false; 
    Object this$appId = getAppId(), other$appId = other.getAppId();
    if ((this$appId == null) ? (other$appId != null) : !this$appId.equals(other$appId))
      return false; 
    Object this$typ = getTyp(), other$typ = other.getTyp();
    if ((this$typ == null) ? (other$typ != null) : !this$typ.equals(other$typ))
      return false; 
    Object this$alg = getAlg(), other$alg = other.getAlg();
    if ((this$alg == null) ? (other$alg != null) : !this$alg.equals(other$alg))
      return false; 
    Object this$x5t = getX5t(), other$x5t = other.getX5t();
    return !((this$x5t == null) ? (other$x5t != null) : !this$x5t.equals(other$x5t));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof DecodeTokenResponse;
  }
  
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $aud = getAud();
    result = result * 59 + (($aud == null) ? 43 : $aud.hashCode());
    Object $exp = getExp();
    result = result * 59 + (($exp == null) ? 43 : $exp.hashCode());
    Object $iat = getIat();
    result = result * 59 + (($iat == null) ? 43 : $iat.hashCode());
    Object $iss = getIss();
    result = result * 59 + (($iss == null) ? 43 : $iss.hashCode());
    Object $appType = getAppType();
    result = result * 59 + (($appType == null) ? 43 : $appType.hashCode());
    Object $appId = getAppId();
    result = result * 59 + (($appId == null) ? 43 : $appId.hashCode());
    Object $typ = getTyp();
    result = result * 59 + (($typ == null) ? 43 : $typ.hashCode());
    Object $alg = getAlg();
    result = result * 59 + (($alg == null) ? 43 : $alg.hashCode());
    Object $x5t = getX5t();
    return result * 59 + (($x5t == null) ? 43 : $x5t.hashCode());
  }
  
  public String toString() {
    return "DecodeTokenResponse(aud=" + getAud() + ", exp=" + getExp() + ", iat=" + getIat() + ", iss=" + getIss() + ", appType=" + getAppType() + ", appId=" + getAppId() + ", typ=" + getTyp() + ", alg=" + getAlg() + ", x5t=" + getX5t() + ")";
  }
  
  public String getAud() {
    return this.aud;
  }
  
  public Integer getExp() {
    return this.exp;
  }
  
  public Integer getIat() {
    return this.iat;
  }
  
  public String getIss() {
    return this.iss;
  }
  
  public String getAppType() {
    return this.appType;
  }
  
  public String getAppId() {
    return this.appId;
  }
  
  public String getTyp() {
    return this.typ;
  }
  
  public String getAlg() {
    return this.alg;
  }
  
  public String getX5t() {
    return this.x5t;
  }
}
