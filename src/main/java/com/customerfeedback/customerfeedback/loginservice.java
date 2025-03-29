package com.customerfeedback.customerfeedback;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;


@Service
public class loginservice {
	@Autowired
	loginrepo rep;
	@Autowired
	feedbackrepo frep;
	
	
	
	
	  public String LoginCheck(login log, HttpSession session) {
		  String username = "john@jenis.com";
		  String password = "John@123";
		login existuser = rep.findByUsername(log.getUsername());
		session.setAttribute("username", log.getUsername());
		System.out.println("Password is" + log.getPassword());
		System.out.println("Username is" + log.getUsername());
		if(existuser != null && existuser.getPassword().equals(log.getPassword())) {
			if(log.getUsername().equals(username) && log.getPassword().equals(password))
				{return "admin";} 
			else {
				return "success";
			}
		
	    }else {
	    	System.out.println("Invalid credentials");
	    	return "Invalid credentials";
	    	
	    }
	    
	    
}

	public feedback SaveFeedback(feedback feed, HttpSession session) {
		LocalDate date = LocalDate.now();
		feed.setUsername((String) session.getAttribute("username"));
		feed.setDate(date);
		System.out.println(feed.getFeedbacks());
		System.out.println(feed.getUsername());
		return frep.save(feed);
		
	}

	public List<feedback> showFeedback() {
		return frep.findAll();
		
	}

	public feedback editFeedback(int id, feedback fed) {
		System.out.println("Received Feedback ID: " + id);

		 Optional<feedback> existingFeedback = frep.findById(id);

	        if (existingFeedback.isPresent()) {
	            feedback feedbackToUpdate = existingFeedback.get();
	            
	            
	           //  feedbackToUpdate.setFeedbackId(id);
	          //   feedbackToUpdate.setUsername(fed.getUsername());
	               feedbackToUpdate.setFeedbacks(fed.getFeedbacks());
	          //   feedbackToUpdate.setDate(fed.getDate());
	            
	            // Save updated feedback
	            return frep.saveAndFlush(feedbackToUpdate);
	        } else {
	            throw new RuntimeException("Feedback not found with ID: " + fed.getFeedbackId());
	        }
		
	}

	public void deleteFeedback(int id) {
        
		
		 frep.deleteById(id);
			/*
			 * List<feedback> allFeedbacks = frep.findAll(Sort.by(Sort.Direction.ASC,
			 * "id"));
			 * 
			 * 
			 * for (int i = 0; i < allFeedbacks.size(); i++) {
			 * allFeedbacks.get(i).setFeedbackId(i + 1); // Assign sequential IDs }
			 * 
			 * // Step 4: Save the resequenced records frep.saveAll(allFeedbacks);
			 */
		 
	}

	public String CreateAccount(login log) {
		
		login existingcheck = rep.findByUsername(log.getUsername());
		if(log.getUsername().equals(existingcheck.getUsername()))
		{	
			return "failure";
		}else {
			rep.save(log);
			
			return "success";
		}
		
		
		
	}

	
	
	  public Optional<feedback> getFeedbackId(int id) {
		 System.out.println(id);
		return frep.findById(id);
	      
	  }
	

	
	  
	 
	
	
 
}
