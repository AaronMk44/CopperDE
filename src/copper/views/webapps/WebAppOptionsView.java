package copper.views.webapps;

import java.io.IOException;

import copper.controllers.webapps.WebAppOptionsController;
import copper.controllers.webapps.WebAppsController;
import copper.entities.WebApp;
import copper.models.Configurations;
import copper.views.dialogs.ProgressView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WebAppOptionsView
{

    public void getWindow(WebApp obj, WebAppsController wObj)
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
        	FXMLLoader loader;            
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                		getResource("/copper/views/webapps/WebAppOptionsViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                		getResource("/copper/views/webapps/WebAppOptionsView.fxml"));
            }
            root = loader.load();    	

            WebAppOptionsController ctrl = loader.getController();
            ctrl.setData(obj, wObj, window);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 205, 137);

        window.setTitle("Options - " + obj.getAppName());
        window.setScene(scene);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        try
        {
            if(Configurations.getConfig("theme").equals("dark"))
            {
                Image icon = new Image(ProgressView.class.
                getResourceAsStream("/copper/assets/images/logoDark.png"));
                window.getIcons().add(icon);
            }else
            {
                Image icon = new Image(ProgressView.class.
                getResourceAsStream("/copper/assets/images/logoLight.png"));
                window.getIcons().add(icon);
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        window.show();

    }
}
