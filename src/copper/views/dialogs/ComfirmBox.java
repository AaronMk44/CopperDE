package copper.views.dialogs;

import java.io.IOException;

import copper.controllers.dialogs.ComfirmController;
import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ComfirmBox
{
    private String infoMsg;
    private ComfirmController ctrl;

    public ComfirmBox(String infoMsg)
    {
        this.infoMsg = infoMsg;
    }

    public void getWindow()
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/copper/views/dialogs/ComfirmBox.fxml"));
            root = loader.load();

            this.ctrl = loader.getController();
            ctrl.setWindow(window);
            ctrl.setMessage(this.infoMsg);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        
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

        window.setTitle("Comfirm");
        window.setScene(scene);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    public ComfirmController getCtrl()
    {
        return this.ctrl;
    }
}