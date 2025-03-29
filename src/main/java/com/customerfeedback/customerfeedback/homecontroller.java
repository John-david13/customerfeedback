package com.customerfeedback.customerfeedback;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:5500") 
public class homecontroller {
	@Autowired
	loginservice service;
	
	
	
	  @PostMapping("/logincheck") 
	  public String LoginCheck(@RequestBody login log, HttpSession session) {
	  
	  String result = service.LoginCheck(log, session);
	  
	  if (result.equals("success")) {
		  return "success";
	  }
	  else if(result.equals("admin"))
		  {
			  return "admin";
		  }
		   
		   else 
		  { 
			  return "Invalid credentials"; }
	  }
	 
	  
	  @PostMapping("/feedback") 
	  public feedback SaveFeedback(@RequestBody feedback fed, HttpSession session) {
		  		
	           return service.SaveFeedback(fed, session);
	                 
		  }
	  @PostMapping("/createaccount") 
	  public String CreateAccount(@RequestBody login log) {
	          return service.CreateAccount(log);
		  }
	  @GetMapping("/adminfeedback")
	  public List<feedback> showFeedback(){
		  return service.showFeedback();
	  }
	  
	  
	  @PutMapping("/admin/{id}")
	  public feedback editFeedback(@PathVariable int id, @RequestBody feedback fed){
		  return service.editFeedback(id,fed);
	  }
	  
	  @DeleteMapping("/admin/delete/{id}")
	  public void deleteFeedback(@PathVariable int id){
		  service.deleteFeedback(id);
	  }
	  
	  @GetMapping("/adminfeedback/{id}")
	  public Optional<feedback> getFeedbackId(@PathVariable int id) {
	     return service.getFeedbackId(id);
	  }
	 
}
