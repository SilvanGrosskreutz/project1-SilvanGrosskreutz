package com.revature.repositories;

import com.revature.exceptions.ReimbursementUpdateFailed;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {
	
	private UserDAO userDAO = new UserDAO();
    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE reim_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Reimbursement reim = new Reimbursement();
				reim.setId(result.getInt("reim_id"));
				if(result.getString("reim_status").equals("PENDING")) {
					reim.setStatus(Status.PENDING);
				} else if(result.getString("reim_status").equals("APPROVED")) {
					reim.setStatus(Status.APPROVED);
				} else if(result.getString("reim_status").equals("DENIED")) {
					reim.setStatus(Status.DENIED);
				}
				Optional<User> author = userDAO.getByUsername(result.getString("reim_author"));
				reim.setAuthor(author.get());
				
				Optional<User> resolver = userDAO.getByUsername(result.getString("reim_resolver"));
				reim.setResolver(resolver.get());
				
				reim.setAmount(result.getDouble("reim_amount"));
				return Optional.of(reim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE reim_status = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			if(status.equals(Status.PENDING)) {
				statement.setString(1, "PENDING");
			} else if(status.equals(Status.APPROVED)) {
				statement.setString(1, "APPROVED");
			} else if(status.equals(Status.DENIED)) {
				statement.setString(1, "DENIED");
			}
			
			ResultSet result = statement.executeQuery();
			
			List<Reimbursement> reimList = new ArrayList<Reimbursement>();
			
			while(result.next()) {
				Reimbursement reim = new Reimbursement();
				reim.setId(result.getInt("reim_id"));
				if(result.getString("reim_status").equals("PENDING")) {
					reim.setStatus(Status.PENDING);
				} else if(result.getString("reim_status").equals("APPROVED")) {
					reim.setStatus(Status.APPROVED);
				} else if(result.getString("reim_status").equals("DENIED")) {
					reim.setStatus(Status.DENIED);
				}
				Optional<User> author = userDAO.getByUsername(result.getString("reim_author"));
				reim.setAuthor(author.get());
				
				Optional<User> resolver = userDAO.getByUsername(result.getString("reim_resolver"));
				reim.setResolver(resolver.get());
				
				reim.setAmount(result.getDouble("reim_amount"));
				reimList.add(reim);
			}
			return reimList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return new ArrayList<Reimbursement>();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) throws ReimbursementUpdateFailed {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE reimbursement SET reim_status = ?,"
					+ " reim_author = ?, reim_resolver = ?,"
					+ " reim_amount = ?"
					+ " WHERE reim_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			
			statement.setString(++count, String.valueOf(unprocessedReimbursement.getStatus()));
			statement.setString(++count, String.valueOf(unprocessedReimbursement.getAuthor()));
			statement.setString(++count, String.valueOf(unprocessedReimbursement.getResolver()));
			statement.setDouble(++count, unprocessedReimbursement.getAmount());
			statement.setInt(++count, unprocessedReimbursement.getId());
			
			statement.execute();
			Optional<Reimbursement> reim = getById(unprocessedReimbursement.getId());
			return reim.get();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
