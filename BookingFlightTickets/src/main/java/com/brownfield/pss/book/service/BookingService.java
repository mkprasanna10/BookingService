package com.brownfield.pss.book.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.brownfield.pss.book.entity.BookingRecord;
import com.brownfield.pss.book.entity.Inventory;
import com.brownfield.pss.book.entity.Passenger;
import com.brownfield.pss.book.repository.BookingRepository;
import com.brownfield.pss.book.repository.InventoryRepository;

@Service
public class BookingService 
{
	private static final String FareURL = "http://localhost:8081/fares";  //To retrieve Fare Details
	private static final String SearchURL = "http://localhost:8090/search"; // To update Ticket Count Details
	
	BookingRepository bookingRepository;
	InventoryRepository inventoryRepository;
	
	private RestTemplate restTemplate;
	
	public BookingService()
	{
	}

	@Autowired
	public BookingService(BookingRepository bookingRepository, InventoryRepository inventoryRepository) {
		this.bookingRepository = bookingRepository;
		this.inventoryRepository = inventoryRepository;
		this.restTemplate = new RestTemplate();
	}
	
	public long book(BookingRecord record) 
	{
		Fare fare = null;
		long id = 0;
		try
		{
			System.out.println("1st Service");
			
			fare = restTemplate.getForObject(FareURL + "/get?flightNumber=" + record.getFlightNumber() + "&flightDate=" + record.getFlightDate(),Fare.class);
			System.out.println("Fare Details ---->"+fare);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			if (!record.getFare().equals(fare.getFare()))
				throw new Exception("fare is tampered");
			Inventory inv = inventoryRepository.findByFlightNumberAndFlightDate(record.getFlightNumber(), record.getFlightDate());
			
			inv.setAvailable(inv.getAvailable() - record.getPassengers().size());
			inventoryRepository.saveAndFlush(inv);
			
			record.setStatus(BookingStatus.BOOKING_CONFIRMED);
			Set<Passenger> passengers = record.getPassengers();
			passengers.stream().forEach(passenger -> passenger.setBookingRecord(record));
			record.setBookingDate(new Date());
			id = bookingRepository.save(record).getId();
			
			//Service call for Search Inventory update
			System.out.println("2nd Service");

			//restTemplate.postForEntity(uri, request, String.class);
			HttpHeaders headers = new HttpHeaders();   
		    headers.set("flightNumber", record.getFlightNumber());   
		    headers.set("flightDate", record.getFlightDate());  
		    headers.set("new_inventory", String.valueOf(inv.getAvailable()));  
		    
			HttpEntity<Inventory> request = new HttpEntity<>(inv, headers);
			 
			ResponseEntity<Inventory> result = restTemplate.postForEntity(SearchURL + "/TicketUpdate", request, Inventory.class);
			
			/*restTemplate.getForObject(SearchURL + "/TicketUpdate?flightNumber=" +record.getFlightNumber() +"&flightDate=" + 
			record.getFlightDate()+"&new_inventory=" +inv.getAvailable() ,Inventory.class);*/
			
			System.out.println("Processed successfully ---> "+result);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}
	
}
