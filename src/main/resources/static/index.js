
 
 async function submitForm(event) {
        event.preventDefault();
        
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        // Use correct property names expected by the backend
        const obj = { 
            username: username, 
            password: password 
        };

        const baseurl = "/logincheck";

        try {
            const response = await fetch(baseurl, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'  // Fixed content type casing
                },
                body: JSON.stringify(obj)
            });

            if (response.ok) {
                const result = await response.text();
                
                if (result === "success") {
                    window.location.href = "/feedback.html";   // Redirect on success
                }else if(result === "admin"){
					window.location.href = "/admin.html";
				} else {
                    alert("Invalid credentials. Please try again.");
                }
            } else {
                alert("Failed to login. Server error.");
            }

        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred. Please try again later.");
        }
    }
    function start(){
		document.getElementById("label").innerHTML = message;
		document.getElementById("label").style.color = "green";
	}
    
    async function createaccount(event) {
        event.preventDefault();
        const message = getElementById("message");
        const username = document.getElementsByName("username")[0].value;
        const password = document.getElementsByName("password")[0].value;
        const cpassword = document.getElementsByName("cpassword")[0].value;
		if(password!=cpassword){
			alert("Your passwords are not matching!")
		}else{
        // Use correct property names expected by the backend
        const obj = { 
            username: username, 
            password: password 
        };

        const baseurl = "/createaccount";

        try {
            const response = await fetch(baseurl, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'  // Fixed content type casing
                },
                body: JSON.stringify(obj)
            });

            if (response.ok) {
				const result = await response.text();
				if(result === "success"){
					window.location.href = "/index.html"; 
					
					message.innerHTML = "Account has been created successfully, Please try to login now";
                    
				}else if(result === "failure"){
				message = "username already exist's, please try other usernames";
                	
                   
                } else {
                alert("Failed to create account. Server error.");
            }

        } }catch (error) {
            console.error("Error:", error);
            alert("An error occurred. Please try again later.");
        }
        }
    }
    
    
    async function feedbacksubmit(event) {
        event.preventDefault();
        
        const feedbacks = document.getElementsByName("feedbacks")[0].value;
         if (feedbacks == null || feedbacks.trim() === '') {
        document.getElementById("message").innerHTML = "Please enter the feedback!";
                    document.getElementById("message").style.color = "red";
        return;  // Stop the function execution
    }
			
		
		
        // Use correct property names expected by the backend
        const obj = { 
            feedbacks: feedbacks 
        };

        const baseurl = "/feedback";

        try {
            const response = await fetch(baseurl, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'  // Fixed content type casing
                },
                body: JSON.stringify(obj)
            });
			
            if (response.ok) {
                	document.getElementById("message").innerHTML = "Thank you! Your feedback has been submitted";
                	feedbacks.value = "";
                    document.getElementById("message").style.color = "green";
                   	setTimeout(() => {
						document.getElementById("message").style.transition = "0.5s";
    document.getElementById("message").innerHTML = "";
}, 5000); 
                } else {
                alert("Failed to submit your feedback. Server error.");
            }

        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred. Please try again later.");
        }
        }
        
    async function getfeedback(event) {
        
        
       
        const datafield = document.getElementById("data");
		datafield.innerHTML = "";
		
        const baseurl = "/adminfeedback";

        try {
            const response = await fetch(baseurl, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json'
                },
                
            });
            if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
			
            if (response.ok) {
                	const data = await response.json();
                	data.forEach(user => {
            const row = document.createElement("tr");

            // Create table cells
            const idCell = document.createElement("td");
            idCell.textContent = user.feedbackId;

            const nameCell = document.createElement("td");
            nameCell.textContent = user.feedbacks;
            const dateCell = document.createElement("td");
            dateCell.textContent = user.date;
			const actioncell = document.createElement("td");
			const edit = document.createElement("button");
			edit.textContent = "edit";
			edit.onclick = () => editrequest(event, user.feedbackId, nameCell	);
			const del = document.createElement("button");
			del.textContent = "delete"
			del.onclick = () => delrequest(user.feedbackId);
			const view = document.createElement("button");
			view.textContent = "view";
			view.classList.add("edit-btn");
            view.onclick = (event) =>  viewFeedback(user.feedbackId);
        
			actioncell.appendChild(edit);
			actioncell.appendChild(del);
			actioncell.appendChild(view);	
            // Append cells to the row
            row.appendChild(idCell);
            row.appendChild(nameCell);
            row.appendChild(dateCell);
            row.appendChild(actioncell);

            // Append row to the table body
            datafield.appendChild(row);
        });
                   
                } else {
                alert("Failed to submit your feedback. Server error.");
            }

        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred. Please try again later.");
        }
        }
        
        
async function delrequest(idCell){
  const baseurl = `/admin/delete/${idCell}`;

        try {
            const response = await fetch(baseurl, {
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json'
                },
                
            });
            if (response.ok) {
            alert("Deleted successfully!");
            const row = document.getElementById(`row-${idCell}`); 
            
                row.remove();  // Remove row from DOM
                window.location.href = "/adminfeedback.html";
            }
        else{
			alert("Deletion was unsuccessfull!");
		}
			}catch(error){
				console.log("Something went wrong:"+ error);
			}
	
}

async function editrequest(event, id,feedback){ 
	feedback.contentEditable = "true";
	feedback.focus();	
    feedback.onblur = async () => {
        const updatedFeedback = feedback.textContent;

        const baseurl = `/admin/${id}`;
        const payload = { feedbacks: updatedFeedback };

        try {
            const response = await fetch(baseurl, {
                method: "PUT",
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                alert("Feedback updated successfully!");
                feedback.contentEditable = "false";
            } else {
                alert("Failed to update feedback.");
            }

        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while updating feedback.");
        }
    };
}

async function viewFeedback(id) {
	
    const baseurl = `/adminfeedback/${id}`;

    try {
        const response = await fetch(baseurl, {
            method: "GET",
            headers: { 'Content-Type': 'application/json' }
        });

        if (!response.ok) {
            throw new Error(`Failed to fetch details for ID: ${id}`);
        }

        const data = await response.json();

        // Populate popup with feedback details
        document.getElementById("popup-id").textContent = data.feedbackId;
        document.getElementById("popup-feedback").textContent = data.feedbacks;
        document.getElementById("popup-date").textContent = data.date;
        document.getElementById("popup-username").textContent = data.username;

        // Display the popup
        document.getElementById("popup-container").style.display = "block";

    } catch (error) {
        console.error("Error:", error);
        alert("Failed to fetch details.");
    }
}
function closePopup(){
	document.getElementById("popup-container").style.display = "none";
}
function logout() {
    // Clear stored authentication tokens or session data
    localStorage.removeItem("authToken");  // Example
    sessionStorage.clear();

    // Redirect to login page
    window.location.href = "/index.html";
}