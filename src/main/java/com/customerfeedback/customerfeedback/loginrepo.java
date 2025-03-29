package com.customerfeedback.customerfeedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loginrepo extends JpaRepository<login, Integer> {

	//login findByUserName(String username);

	login findByUsername(String username);

	feedback save(feedback log);

	

}
