/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author SeifBS
 */
public class MyAddress {
    


private String country_code;
	private String utc_offset;
	private String country;
	private String state;
	private String city;
	private String zip;
	private String lat;
	private String lon;
	
	private String ip_address;
	
	
	
	
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getUtc_offset() {
		return utc_offset;
	}
	public void setUtc_offset(String utc_offset) {
		this.utc_offset = utc_offset;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}

    @Override
    public String toString() {
        return "MyAddress{" + "country_code=" + country_code + ", utc_offset=" + utc_offset + ", country=" + country + ", state=" + state + ", city=" + city + ", zip=" + zip + ", lat=" + lat + ", lon=" + lon + ", ip_address=" + ip_address + '}';
    }
	
	





}
