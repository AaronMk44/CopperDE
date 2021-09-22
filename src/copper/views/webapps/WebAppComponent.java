package copper.views.webapps;

import java.io.IOException;

import copper.controllers.webapps.WebAppComponentController;
import copper.controllers.webapps.WebAppsController;
import copper.entities.WebApp;
import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class WebAppComponent
{

    public VBox getEntry(WebApp obj, WebAppsController wObj)
    {
        VBox root = null;
        try
        {
            FXMLLoader loader;            
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                		getResource("/copper/views/webapps/WebAppComponentDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                		getResource("/copper/views/webapps/WebAppComponent.fxml"));
            }
            root = loader.load();
            WebAppComponentController ctrl = loader.getController();
            ctrl.populate(obj, wObj);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return root;
    }
}
