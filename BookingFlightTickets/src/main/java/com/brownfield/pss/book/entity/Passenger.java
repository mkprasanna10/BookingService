package com.brownfield.pss.book.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Passenger 
{
	@Id
	@SequenceGenerator(name="PASSENGER_ID_GENERATOR",sequenceName="PASSENGER_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PASSENGER_ID_GENERATOR")
	long id;
    String firstName;
    String lastName;
    String gender;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOOKING_ID")
    @JsonIgnore
    private BookingRecord bookingRecord;
    
    public Passenger()
    {
    }
    
	public Passenger(String firstName, String lastName, String gender, BookingRecord bookingRecord) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.bookingRecord = bookingRecord;
	}

	public BookingRecord getBookingRecord() {
		return bookingRecord;
	}

	public void setBookingRecord(BookingRecord bookingRecord) {
		this.bookingRecord = bookingRecord;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
    
}
