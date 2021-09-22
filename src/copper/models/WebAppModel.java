package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copper.confidential.RemoteDatabase;
import copper.entities.WebApp;

public class WebAppModel
{

    private final String prefix = "zictc_intra_";
    private final String TABLE_NAME = this.prefix + "webapps";

    public WebAppModel()
    {
    }

    public boolean insert(WebApp obj)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "INSERT INTO " + this.TABLE_NAME + " (developer_id, app_name, "
            + "domain_name, document_root, logo_root, category_id, languages, "
            + "seo_keywords, brief_description, detailed_description, status)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDeveloperID());
            stmt.setString(2, obj.getAppName());
            stmt.setString(3, obj.getDomainName());
            stmt.setString(4, obj.getDocumentRoot());
            stmt.setString(5, obj.getLogoRoot());
            stmt.setString(6, obj.getCategoryID());
            stmt.setString(7, obj.getLanguages());
            stmt.setString(8, obj.getSeoKeywords());
            stmt.setString(9, obj.getBriefDescription());
            stmt.setString(10, obj.getDetailedDescription());
            stmt.setString(11, obj.getStatus());

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

    public WebApp getWebApp(String query)
    {
        WebApp obj = new WebApp();

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME + " "
            + "WHERE app_name = ? OR domain_name = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, query);
            stmt.setString(2, query);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                obj.setID(results.getInt("id") + "");
                obj.setDeveloperID(results.getString("developer_id"));
                obj.setAppName(results.getString("app_name"));
                obj.setDomainName(results.getString("domain_name"));
                obj.setDocumentRoot(results.getString("document_root"));
                obj.setLogoRoot(results.getString("logo_root"));
                obj.setCategoryID(results.getString("category_id"));
                obj.setLanguages(results.getString("languages"));
                obj.setSeoKeywords(results.getString("seo_keywords"));
                obj.setBriefDescription(results.getString("brief_description"));
                obj.setDetailedDescription(results.getString("detailed_description"));
                System.out.println(obj.getDetailedDescription());
                obj.setCreatedAt(results.getTimestamp("created_at").toString());
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return obj;
    }

    public boolean update(WebApp obj)
    { 
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "UPDATE " + this.TABLE_NAME + " "
            + "SET app_name = ?, domain_name = ?, document_root = ?, "
            + "logo_root = ?, languages = ?, seo_keywords = ?,"
            + "brief_description = ?, status = ?"
            + " WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getAppName());
            stmt.setString(2, obj.getDomainName());
            stmt.setString(3, obj.getDocumentRoot());
            stmt.setString(4, obj.getLogoRoot());
            stmt.setString(5, obj.getLanguages());
            stmt.setString(6, obj.getSeoKeywords());
            stmt.setString(7, obj.getBriefDescription());
            stmt.setString(8, obj.getStatus());
            stmt.setInt(9, Integer.parseInt(obj.getID()));

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

    public boolean delete(String ID)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "DELETE FROM " + this.TABLE_NAME + " WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(ID));

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

    public WebApp[] getWebApps(String ID)
    {
        WebApp[] data = new WebApp[this.getSize(ID)];

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME + " "
            + "WHERE developer_id = ? ORDER BY id DESC";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ID);

            ResultSet results = stmt.executeQuery();

            int counter = 0;
            while (results.next())
            {
                data[counter] = new WebApp();
                data[counter].setID(results.getString("id"));
                data[counter].setAppName(results.getString("app_name"));
                data[counter].setDomainName(results.getString("domain_name"));
                data[counter].setDocumentRoot(results.getString("document_root"));
                data[counter].setLogoRoot(results.getString("logo_root"));
                data[counter].setCategoryID(results.getString("category_id"));
                data[counter].setLanguages(results.getString("languages"));
                data[counter].setSeoKeywords(results.getString("seo_keywords"));
                data[counter].setBriefDescription(results.getString("brief_description"));
                data[counter].setStatus(results.getString("status"));
                data[counter].setCreatedAt(results.getTimestamp("created_at").toString());

                counter++;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return data;
    }

    public int getSize(String ID)
    {
        int size = 0;

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE developer_id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ID);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                size++;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);

        return size;
    }
    
    public int getNumberOfApps(String devID)
    {
        int size = 0;

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(devID));
            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                size++;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return size;
    }
    
    public boolean isDNSTaken(String dns)
     {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE domain_name = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dns);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                dbr.closeConnection(conn);
                return true;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return false;
     }
}
