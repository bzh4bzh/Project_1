package com.revature.beans;
import java.util.ArrayList;

public class Attachment {
	private int requestID;
	private int userID;
	private ArrayList<String> links;
	public Attachment() {
		// TODO Auto-generated constructor stub
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public ArrayList<String> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<String> links) {
		this.links = links;
	}
	public Attachment(int requestID, int userID, ArrayList<String> links) {
		super();
		this.requestID = requestID;
		this.userID = userID;
		this.links = links;
	}
	@Override
	public String toString() {
		return "Attachment [requestID=" + requestID + ", userID=" + userID + ", links=" + links.toString() + "]";
	}
	
	

}
