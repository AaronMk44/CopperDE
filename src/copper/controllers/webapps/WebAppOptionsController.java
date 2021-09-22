package copper.controllers.webapps;

import com.jfoenix.controls.JFXButton;

import copper.entities.WebApp;
import copper.models.WebAppModel;
import copper.views.dialogs.ComfirmBox;
import copper.views.webapps.EditWebAppView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class WebAppOptionsController
{

    private WebApp app = null;
    private WebAppModel model = null;
    private WebAppsController wObj = null;
    private Stage window = null;

    public WebAppOptionsController()
    {
        this.model = new WebAppModel();
    }

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton editBtn;

    @FXML
    void deleteWebApp(ActionEvent event)
    {
        this.window.close();
        ComfirmBox view = new ComfirmBox("Are you sure you want to DELETE " + 
            this.app.getAppName() + "?");
        view.getWindow();
        view.getCtrl().setData(app, wObj);
    }

    @FXML
    void editWebApp(ActionEvent event)
    {
        this.window.close();
        EditWebAppView view = new EditWebAppView();
        view.getWindow(this.app, this.wObj);
    }

    public void setData(WebApp obj, WebAppsController wObj, Stage window)
    {
        this.wObj = wObj;
        this.app = obj;
        this.window = window;
    }
}
