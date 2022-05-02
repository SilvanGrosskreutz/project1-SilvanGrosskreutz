package com.revature.services;

import com.revature.exceptions.ReimbursementNotFoundException;
import com.revature.exceptions.ResolverIsNullException;
import com.revature.exceptions.UserIsNotFinanceManagerException;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li> 			FINISHED
 *     <li>Update Reimbursement</li>			FINISHED
 *     <li>Get Reimbursements by ID</li>		FINISHED
 *     <li>Get Reimbursements by Author</li>	FINISHED
 *     <li>Get Reimbursements by Resolver</li> 	FINISHED
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {
	
	public static List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
	
	public Reimbursement createImbursement(User author) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Whats the Reimbursements ID?");
		int id = Integer.valueOf(scan.nextLine());
		System.out.println("Whats the amount?");
		double amount = Double.valueOf(scan.nextLine());
		Reimbursement reimbursement = new Reimbursement(id, Status.PENDING, author, amount);
		return reimbursement;
	}

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>		FINISHED
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>	FINISHED
     *     <li>Should ensure that the reimbursement request exists</li>					FINISHED
     *     <li>Must throw exception if the reimbursement request is not found</li>		FINISHED
     *     <li>Should persist the updated reimbursement status with resolver information</li> FINISHED
     *     <li>Must throw exception if persistence is unsuccessful</li>						
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     * @throws Exception 
     */
    public Reimbursement process(Reimbursement unprocessedReimbursement,
    		Status finalStatus, User resolver) throws Exception {
    	boolean check = false;
    	
    	if(!resolver.getRole().equals(Role.FINANCE_MANAGER)){
    		throw new UserIsNotFinanceManagerException("User not logged in as Finance Manager!");
    	}
    	
    	for (Reimbursement reimbursement : reimbursementList) {
    		if(unprocessedReimbursement.equals(reimbursement)) {
    			check = true;
    		} 
		}
    	
    	if(check == false) {
    		throw new ReimbursementNotFoundException("Reimbursement request not in the list.");
    	}
    	
    	if(unprocessedReimbursement.getResolver().equals(null)) {
    		unprocessedReimbursement.setResolver(resolver);
    	} else throw new ResolverIsNullException("Resolver is not null. Persistence unsuccessful");
    	
    	Scanner scan = new Scanner(System.in);
    	System.out.println("Do you want to accept(1) or deny(2) the request?");
    	int answer = Integer.valueOf(scan.nextLine());
    	boolean request = true;
    	while(request) {
	    	switch (answer) {
			case 1:
				unprocessedReimbursement.setStatus(Status.APPROVED);
				request = false;
				break;
			case 2:
				unprocessedReimbursement.setStatus(Status.DENIED);
				request = false;
				break;
			default:
				System.out.println("Invalid input. Try again to accept(1) or deny(2) the request!");
				break;
			}
    	}
    	
        return unprocessedReimbursement;
    }
    
    public Reimbursement getReimbursementById(int id){
    	if(id <= 0) {
    		System.out.println("Input ID should be non-zero and positive.");
    		return null;
    	}
    	for (Reimbursement reimbursement : reimbursementList) {
			if(reimbursement.getId() == id) {
				return reimbursement;
			}
		}	
    	return null;
    }
    
    public Reimbursement getReimbursementByAuthor(User author){
    	if(author.equals(null)) {
    		System.out.println("The Author you are searching for should be not null.");
    		return null;
    	}
    	for (Reimbursement reimbursement : reimbursementList) {
			if(reimbursement.getAuthor().equals(author)) {
				return reimbursement;
			}
		}	
    	return null;
    }
    
    public Reimbursement getReimbursementByResolver(User resolver){
    	if(resolver.equals(null)) {
    		System.out.println("The Resolver you are searching for should be not null.");
    		return null;
    	}
    	for (Reimbursement reimbursement : reimbursementList) {
			if(reimbursement.getResolver().equals(resolver)) {
				return reimbursement;
			}
		}	
    	return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
        return Collections.emptyList();
    }
}
