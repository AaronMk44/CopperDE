package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copper.confidential.RemoteDatabase;
import copper.entities.Developer;

public class DeveloperModel
{

    private final String prefix = "zictc_intra_";
    private final String TABLE_NAME = this.prefix + "developers";

    public DeveloperModel()
    {
    }

    public boolean insert(Developer obj)
    {

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "INSERT INTO " + this.TABLE_NAME + " (user_id, username, password, status) "
            + "VALUES(?,?,?,?)";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getUserID());
            stmt.setString(2, obj.getUsername());
            stmt.setString(3, obj.getDevPassword());
            stmt.setString(4, obj.getStatus());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);

        return false;
    }

    public Developer getDeveloper(String username)
    {
        Developer obj = null;

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM (" + this.TABLE_NAME + " "                   
            + "INNER JOIN zictc_intra_users ON " + this.TABLE_NAME + 
            ".user_id = zictc_intra_users.id)"
            + "WHERE " + this.TABLE_NAME + ".username = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                obj = new Developer();
                obj.setUserID(results.getInt("zictc_intra_users.id") + "");
                obj.setFirstName(results.getString("first_name"));
                obj.setLastName(results.getString("last_name"));
                obj.setGender(results.getString("gender"));
                obj.setEmail(results.getString("email"));
                obj.setUsrPassword(results.getString("password"));
                obj.setUserGroup(results.getString("user_group"));
                obj.setUsrCreatedAt(results.getTimestamp("created_at").toString());
                obj.setDeveloperID(results.getInt("zictc_intra_developers.id") + "");
                obj.setUsername(results.getString("username"));
                obj.setDevPassword(results.getString("zictc_intra_developers.password"));
                obj.setStatus(results.getString("developer_status"));
                obj.setNumberOfApps(this.getNumberOfApps(obj.getDeveloperID()));
                obj.setHasReadLicence(results.getString("has_read_licence"));
                obj.setDevCreatedAt(results.getTimestamp("created_at").toString());
            }
            stmt.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return obj;
    }

    public boolean update(Developer obj)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "UPDATE " + this.TABLE_NAME + " SET username = ?, password = ?"
            + "WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getUsername());
            stmt.setString(2, obj.getDevPassword());
            stmt.setInt(3, Integer.parseInt(obj.getDeveloperID()));

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);

        return false;
    }

    public boolean delete(String username)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "DELETE FROM " + this.TABLE_NAME + " WHERE username = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);

        return false;
    }
    
    private int getNumberOfApps(String id)
    {
        WebAppModel model = new WebAppModel();
        return model.getNumberOfApps(id);
    }
}
