package copper.controllers.webapps;

import copper.entities.WebApp;
import copper.views.webapps.WebAppOptionsView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class WebAppComponentController 
{
    private WebApp app;
    private WebAppsController observer;

    @FXML
    private Label webAppName;

    @FXML
    private Label domainName;

    @FXML
    private Label status;

    @FXML
    private Button options;

    @FXML
    private ImageView optionsImg;

    @FXML
    void loadOptions(MouseEvent event) 
    {
        WebAppOptionsView view = new WebAppOptionsView();
        view.getWindow(this.app, this.observer);
    }
    
    public void populate(WebApp obj, WebAppsController wObj)
    {
        this.app = obj;
        this.observer = wObj;
        this.webAppName.setText(obj.getAppName());
        this.domainName.setText(obj.getDomainName());
               
    }

}