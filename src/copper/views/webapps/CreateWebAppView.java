package copper.views.webapps;

import java.io.IOException;

import copper.controllers.webapps.CreateWebAppController;
import copper.controllers.webapps.WebAppsController;
import copper.entities.Developer;
import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateWebAppView
{

    public void getWindow(Developer dObj, WebAppsController wObj)
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
            FXMLLoader loader;            
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                		getResource("/copper/views/webapps/CreateWebAppViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                		getResource("/copper/views/webapps/CreateWebAppView.fxml"));
            }
            root = loader.load();
            
            CreateWebAppController ctrl = loader.getController();
            ctrl.setData(dObj, wObj, window);
            ctrl.populate();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        if(Configurations.getConfig("theme").equals("dark"))
        {
            scene.getStylesheets().add(getClass().getResource("/copper/assets/dark.css")
            .toExternalForm());
        }
        window.setTitle("Create Web App");
        window.setScene(scene);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        try
        {
            if(Configurations.getConfig("theme").equals("dark"))
            {
                Image icon = new Image(getClass().
                getResourceAsStream("/copper/assets/images/logoDark.png"));
                window.getIcons().add(icon);
            }else
            {
                Image icon = new Image(getClass().
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
