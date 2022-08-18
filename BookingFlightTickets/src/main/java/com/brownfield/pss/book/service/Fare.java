package com.brownfield.pss.book.service;

public class Fare 
{
	String flightNumber;
	String flightDate;
	String fare;
	
	@Override
	public String toString() {
		return "Fare [flightNumber=" + flightNumber + ", flightDate=" + flightDate + ", fare=" + fare + "]";
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	
	
}
