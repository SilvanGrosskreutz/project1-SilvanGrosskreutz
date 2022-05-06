package com.revature.services;

import com.revature.exceptions.ReimbursementNotFoundException;
import com.revature.exceptions.ResolverIsNullException;
import com.revature.exceptions.UserIsNotFinanceManagerException;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

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
	
	protected ReimbursementDAO reimDAO = new ReimbursementDAO();
	
	public Reimbursement createReimbursement(User author, double amount) {
		Reimbursement reim = new Reimbursement(0, Status.PENDING, author, null, amount);
		reimDAO.createReimbursement(reim);
		return reim;
	}
	
	public Reimbursement createReimbursement(Reimbursement reim) {
		reim.setStatus(Status.PENDING);		
		reimDAO.createReimbursement(reim);
		return reim;	
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
     * 
     */
    public Reimbursement process(Reimbursement unprocessedReimbursement,
    		Status finalStatus, User resolver){
    	
    	if(!resolver.getRole().equals(Role.FINANCE_MANAGER)){
    		throw new UserIsNotFinanceManagerException("User not logged in as Finance Manager!");
    	}
    	
    	if(reimDAO.getById(unprocessedReimbursement.getId()).equals(null)) {
    		throw new ReimbursementNotFoundException("Reimbursement request not in the list.");
    	}
    	
    	if(unprocessedReimbursement.getResolver().equals(null)) {
    		unprocessedReimbursement.setResolver(resolver);
    	} else throw new ResolverIsNullException("Resolver is not null. Persistence unsuccessful");
    	
        return unprocessedReimbursement;
    }
    
    public Reimbursement getReimbursementById(int id){
    	if(id <= 0) {
    		System.out.println("Input ID should be non-zero and positive.");
    		return null;
    	}
    	Reimbursement reim = reimDAO.getById(id).get();
    	return reim;
    }
    
    public List<Reimbursement> getReimbursementByAuthor(User author){
    	if(author.equals(null)) {
    		System.out.println("The Author you are searching for should be not null.");
    		return null;
    	}
    	List<Reimbursement> reim = reimDAO.getByAuthor(author);
    	return reim;
    }
    
    public List<Reimbursement> getReimbursementByResolver(User resolver){
    	if(resolver.equals(null)) {
    		System.out.println("The Resolver you are searching for should be not null.");
    		return null;
    	}
    	List<Reimbursement> reim = reimDAO.getByResolver(resolver);
    	return reim;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
    	List<Reimbursement> list = reimDAO.getByStatus(status);
        return list;
    }
    
    public List<Reimbursement> getAllReimbursements(){
    	List<Reimbursement> list = reimDAO.getAllReimbursements();
    	return list;
    }

	


}
