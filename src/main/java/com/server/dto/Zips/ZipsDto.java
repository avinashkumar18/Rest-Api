package com.server.dto.Zips;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("zips")
public class ZipsDto {

    private String id;
	private String city;
	private String zip;
    private int pop;
	private String state;
    private ZipsLoc loc;
    public ZipsLoc getLoc() {
        return loc;
    }
    public void setLoc(ZipsLoc loc) {
        this.loc = loc;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public int getPop() {
        return pop;
    }
    public void setPop(int pop) {
        this.pop = pop;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
