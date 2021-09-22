package copper.controllers.webapps;

import com.jfoenix.controls.JFXButton;

import copper.entities.Developer;
import copper.entities.WebApp;
import copper.models.Configurations;
import copper.models.WebAppModel;
import copper.views.dialogs.InfoView;
import copper.views.webapps.CreateWebAppView;
import copper.views.webapps.WebAppComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WebAppsController
{

    private Developer dObj = Configurations.getUser();
    private Stage window = null;
    private WebAppModel model = null;
    private WebApp[] apps = null;
    
    private HBox appContainer1;
    private HBox appContainer2;
    private VBox masterContainer;

    public WebAppsController()
    {
        this.model = new WebAppModel();
        
        this.appContainer1 = new HBox();
        this.appContainer1.setSpacing(30);        
        
        this.appContainer2 = new HBox();
        this.appContainer2.setSpacing(30);
        
        this.masterContainer = new VBox();
        this.masterContainer.setPadding(new Insets(20));
        this.masterContainer.setSpacing(30);
        
        if(Configurations.getConfig("theme").equals("dark"))
        {
        	this.appContainer1.setStyle("-fx-background-color:  #202020");
        	this.appContainer2.setStyle("-fx-background-color:  #202020");
        	this.masterContainer.setStyle("-fx-background-color:  #202020");
        }
    }
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private JFXButton createAppBtn;

    @FXML
    void createApp(ActionEvent event)
    {
        if (this.model.getSize(this.dObj.getDeveloperID()) == 10)
        {
            InfoView box = new InfoView("You have reached the Web App limit.");
            box.getWindow();
        } else
        {
            CreateWebAppView view = new CreateWebAppView();
            view.getWindow(this.dObj, this);
        }

    }

    public void populate()
    {
        int size = this.model.getSize(this.dObj.getDeveloperID());
        this.apps = this.model.getWebApps(this.dObj.getDeveloperID());
        WebAppComponent[] cards = new WebAppComponent[size];

        for (int i = 0; i < size; i++)
        {
            if (i < 5)
            {
                cards[i] = new WebAppComponent();
                this.appContainer1.getChildren().add(cards[i].
                    getEntry(this.apps[i], this));
            } else
            {
                cards[i] = new WebAppComponent();
                this.appContainer2.getChildren().add(cards[i].
                    getEntry(this.apps[i], this));
            }
        }
        
        this.masterContainer.getChildren().addAll(this.appContainer1, appContainer2);
        this.scrollPane.setContent(this.masterContainer);
    }

    public void reload()
    {
        this.appContainer1.getChildren().clear();
        this.appContainer2.getChildren().clear();
        int size = this.model.getSize(this.dObj.getDeveloperID());
        this.apps = this.model.getWebApps(this.dObj.getDeveloperID());
        WebAppComponent[] cards = new WebAppComponent[size];

        for (int i = 0; i < size; i++)
        {
            if (i < 5)
            {
                cards[i] = new WebAppComponent();
                this.appContainer1.getChildren().add(cards[i].
                    getEntry(this.apps[i], this));
            } else
            {
                cards[i] = new WebAppComponent();
                this.appContainer2.getChildren().add(cards[i].
                    getEntry(this.apps[i], this));
            }
        }
    }

    public void setData(Stage window)
    {
        this.window = window;
    }
}
