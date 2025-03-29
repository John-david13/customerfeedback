package com.customerfeedback.customerfeedback;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface feedbackrepo extends JpaRepository<feedback, Integer> {
	
	List<feedback>  findAll();

	
}
