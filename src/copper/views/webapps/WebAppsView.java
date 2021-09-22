package copper.views.webapps;

import java.io.IOException;

import copper.controllers.webapps.WebAppsController;
import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WebAppsView
{

    public void getWindow()
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
        	FXMLLoader loader = null;
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                		getResource("/copper/views/webapps/WebAppsViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                		getResource("/copper/views/webapps/WebAppsView.fxml"));
            }
            root = loader.load();

            WebAppsController ctrl = loader.getController();
            ctrl.setData(window);
            ctrl.populate();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1267, 600);
        if(Configurations.getConfig("theme").equals("dark"))
        {
            scene.getStylesheets().add(getClass().getResource("/copper/assets/dark.css")
            .toExternalForm());
        }
        window.setTitle("Web Apps");
        window.setScene(scene);
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
