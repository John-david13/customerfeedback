package com.customerfeedback.customerfeedback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="feedback")
public class feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "feedback_id")
	private int feedbackId;
	@JsonProperty("username")
	private String username;
	@JsonProperty("feedbacks")
	private String feedbacks;
	private LocalDate date;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(String feedbacks) {
		this.feedbacks = feedbacks;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public feedback(String username, int feedbackId, String feedbacks, LocalDate date) {
		this.username = username;
		this.feedbackId = feedbackId;
		this.feedbacks = feedbacks;
		this.date = date;
	}
	public feedback() {
		
	}
	@Override
	public String toString() {
		return "feedback [username=" + username + ", feedback_id=" + feedbackId + ", feedbacks=" + feedbacks + ", date="
				+ date + "]";
	}
	
	

	
	

}
