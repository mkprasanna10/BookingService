package com.brownfield.pss.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Inventory 
{
	@Id
	@SequenceGenerator(name="INVENTORY_ID_GENERATOR",sequenceName="INVENTORY_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="INVENTORY_ID_GENERATOR")
	long id;
    String flightNumber;
    String flightDate;
    int available;
    
    public Inventory()
    {	
    	
    }
    
	public Inventory(String flightNumber, String flightDate, int available) {
		super();
		this.flightNumber = flightNumber;
		this.flightDate = flightDate;
		this.available = available;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", flightNumber=" + flightNumber + ", flightDate=" + flightDate + ", available="
				+ available + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
    
    
    
}
