package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copper.confidential.RemoteDatabase;
import copper.entities.Message;

public class UserInboxModel 
{
	private final String prefix = "zictc_intra_"; 
	private final String TABLE_NAME = this.prefix + "users_inbox";
	
	public UserInboxModel() {}

	/*
	 * This model does not obey the CRUD requirements because of user access levels.
	 */
	 
	public Message getMessage(String query)
	{
		Message obj = new Message();
		
		/*
		 * Get connection
		 */
		RemoteDatabase dbr = new RemoteDatabase();
		Connection conn = dbr.getConnection();
		
		/*
		 * Use connection
		 */
		
		String sql = "SELECT * FROM "+this.TABLE_NAME+" WHERE user_id = ? OR title = ?";
		try 
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, query);
			stmt.setString(2, query);
			
			ResultSet results = stmt.executeQuery();
			
			while(results.next())
			{
				obj.setID(results.getInt("ID")+"");
				obj.setUserID(results.getString("user_id"));
				obj.setTitle(results.getString("title"));
				obj.setMessage(results.getString("message "));
				obj.setStatus(results.getString("status"));
				obj.setCreatedAt(results.getTimestamp("createdAt").toString());
			}
			
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Close connection
		 */
		
		dbr.closeConnection(conn);
		
		return obj;
	}
	
	public Message[] getMessages(String userID)
	{
		Message[] data = new Message[this.getSize(userID)];
		
		/*
		 * Get connection
		 */
		RemoteDatabase dbr = new RemoteDatabase();
		Connection conn = dbr.getConnection();
		
		/*
		 * Use connection
		 */
		
		String sql = "SELECT * FROM "+this.TABLE_NAME+" WHERE user_id = ? ORDER BY ID DESC";
		try 
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userID);
			
			ResultSet results = stmt.executeQuery();
			
			int counter = 0;
			while(results.next())
			{
				data[counter] = new Message();
				data[counter].setID(results.getString("ID"));
				data[counter].setUserID(results.getString("user_id"));
				data[counter].setTitle(results.getString("title"));
				data[counter].setMessage(results.getString("message"));
				data[counter].setStatus(results.getString("status"));
				data[counter].setCreatedAt(results.getTimestamp("createdAt").toString());
								
				counter++;
			}
			
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Close connection
		 */
		
		dbr.closeConnection(conn);
		
		return data;
	}
	
	public int getSize(String userID)
	{
		int size = 0;
		
		/*
		 * Get connection
		 */
		RemoteDatabase dbr = new RemoteDatabase();
		Connection conn = dbr.getConnection();
		
		/*
		 * Use connection
		 */
		
		String sql = "SELECT * FROM "+this.TABLE_NAME+" WHERE user_id = ?";
		try 
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userID);
			
			ResultSet results = stmt.executeQuery();
			
			while(results.next())
			{
				size++;
			}
			
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Close connection
		 */
		
		dbr.closeConnection(conn);
		
		return size;
	}
}
