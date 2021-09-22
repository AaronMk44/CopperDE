package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copper.confidential.RemoteDatabase;
import copper.entities.Message;

public class AdminMessagesModel 
{
	private final String prefix = "zictc_intra_"; 
	private final String TABLE_NAME = this.prefix + "admin_inbox";
	
	public AdminMessagesModel() {}
	
	/*
	 * This model does not obey the CRUD requirements because of user access levels.
	 */
	
	public boolean insert(Message obj)
	{
		/*
		 * Get connection
		 */
		RemoteDatabase dbr = new RemoteDatabase();
		Connection conn = dbr.getConnection();
		
		/*
		 * Use connection
		 */
		
		String sql = "INSERT INTO " + this.TABLE_NAME + " (user_id, title, message, status) "
				   + "VALUES(?,?)";
		try 
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getUserID());
			stmt.setString(2, obj.getTitle());
			stmt.setString(1, obj.getMessage());
			stmt.setString(2, obj.getStatus());
			
			stmt.execute();
			stmt.close();
			
			return true;
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Close connection
		 */
		
		dbr.closeConnection(conn);
		
		return false;
	}
		
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

}
