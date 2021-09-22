package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copper.confidential.RemoteDatabase;
import copper.entities.WebAppCategory;

public class WebAppCategoriesModel
{

    private final String prefix = "zictc_intra_";
    private final String TABLE_NAME = this.prefix + "webapp_categories";

    public WebAppCategoriesModel()
    {
    }

    public WebAppCategory getWebAppCategory(String query)
    {
        WebAppCategory obj = new WebAppCategory();

        /*
		 * Get connection
         */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
		 * Use connection
         */
        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE category_name = ? OR web_app_ext = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, query);
            stmt.setString(2, query);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                obj.setID(results.getInt("id") + "");
                obj.setName(results.getString("category_name"));
                obj.setExtension(results.getString("web_app_ext"));
            }

            stmt.close();

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

    public WebAppCategory[] getWebApps()
    {
        WebAppCategory[] data = new WebAppCategory[this.getSize()];

        /*
		 * Get connection
         */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
		 * Use connection
         */
        String sql = "SELECT * FROM " + this.TABLE_NAME;
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet results = stmt.executeQuery();

            int counter = 0;
            while (results.next())
            {
                data[counter] = new WebAppCategory();
                data[counter].setID(results.getString("id"));
                data[counter].setName(results.getString("category_name"));
                data[counter].setExtension(results.getString("web_app_ext"));

                counter++;
            }

            stmt.close();

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

    public int getSize()
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
        String sql = "SELECT * FROM " + this.TABLE_NAME;
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                size++;
            }

            stmt.close();

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

    public String getCategoryName(String ID)
    {
        //Get connection		 
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        //Use connection		
        String sql = "SELECT category_name FROM " + this.TABLE_NAME + " WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ID);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                return results.getString("category_name");
            }
            stmt.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        //Close connection
        dbr.closeConnection(conn);

        return "";
    }
}
