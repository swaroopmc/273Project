package com.Project273.Client;

public class Track {
	
	String id;
	String clientEndPointName;
	int lifetime;	
	double lwm2mVersion;
	String bindingMode;
	int smsNumber;
	String objInst;	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientEndPointName() {
		return clientEndPointName;
	}

	public void setClientEndPointName(String clientEndPointName) {
		this.clientEndPointName = clientEndPointName;
	}

	public int getLifetime(){
		return lifetime;
	}
	
	public void setLifetime(int lifetime){
		this.lifetime = lifetime;
	}
	
	public double getLwm2mVersion(){
		return lwm2mVersion;
	}
	
	public void setLwm2mVersion(double lwm2mVersion){
		this.lwm2mVersion = lwm2mVersion;
	}
	
	public String getBindingMode() {
		return bindingMode;
	}

	public void setBindingMode(String bindingMode) {
		this.bindingMode = bindingMode;
	}	
	
	public int getSmsNumber(){
		return smsNumber;
	}
	
	public void setSmsNumber(int smsNumber){
		this.smsNumber = smsNumber;
	}
	
	public String getObjInst() {
		return objInst;
	}

	public void setObjInst(String objInst) {
		this.objInst = objInst;
	}	
	
}
