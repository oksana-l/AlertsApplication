package com.SafetyNet.alerts.model;

public class FireStation {
	private String address;
	private String station;
	
	public FireStation() {
		super();
	}

	public FireStation(String address, String station) {
		super();
		this.address = address;
		this.station = station;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Override
	public String toString() {
		return "\n FireStation [address=" + address + ", station=" + station + "]";
	}
	
	
}
