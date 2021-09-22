package copper.controllers.webapps;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import copper.entities.Developer;
import copper.entities.WebApp;
import copper.entities.WebAppCategory;
import copper.models.WebAppCategoriesModel;
import copper.models.WebAppModel;
import copper.views.dialogs.ErrorView;
import copper.views.dialogs.InfoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class CreateWebAppController
{

    private Developer dObj = null;
    private WebAppsController wObj = null;
    private Stage window = null;
    private WebAppCategoriesModel modelC = null;
    private WebAppModel modelW = null;
    private WebAppCategory[] categories = null;
    private JFXComboBox<String> category = null;

    public CreateWebAppController()
    {
        this.modelC = new WebAppCategoriesModel();
        this.modelW = new WebAppModel();
    }

    @FXML
    private JFXButton createAppBtn;

    @FXML
    private JFXTextField webAppName;

    @FXML
    private JFXTextField documentRoot;

    @FXML
    private JFXTextField logoRoot;

    @FXML
    private Label categoryLabel;

    @FXML
    private JFXTextArea languages;

    @FXML
    private JFXTextArea seoKeywords;

    @FXML
    private JFXTextArea briefDescription;

    @FXML
    private HTMLEditor detailedDescription;
    
    @FXML
    private HBox categoryBox;

    @FXML
    void createApp(ActionEvent event)
    {
        WebApp obj = new WebApp();

        if (this.webAppName.getText().length() == 0
            || this.documentRoot.getText().length() == 0)
        {
            ErrorView box = new ErrorView("Please fill in the App Name and Document root.");
            box.getWindow();
        }else if(this.isDNSTaken( 
                this.webAppName.getText(), 
                this.category.getValue()))
        {
            ErrorView box = new ErrorView("Domain name is taken. Select another.");
            box.getWindow();
        }else
        {
            obj.setDeveloperID(this.dObj.getDeveloperID());
            obj.setAppName(this.webAppName.getText());
            obj.setDocumentRoot(this.documentRoot.getText());
            obj.setLogoRoot(this.logoRoot.getText());
            obj.setCategoryID(this.extractCateID((String) this.category.getValue()));
            obj.setLanguages(this.languages.getText());
            obj.setSeoKeywords(this.seoKeywords.getText());
            obj.setBriefDescription(this.briefDescription.getText());
            obj.setDetailedDescription(this.detailedDescription.getHtmlText());
            obj.setDomainName(this.generateDomain(this.webAppName.getText(), 
                this.category.getValue()));
            obj.setStatus("running");

            if (this.modelW.insert(obj))
            {
                this.window.close();
                this.wObj.reload();
                InfoView box = new InfoView("WebApp successfully created.\n"
                    + "The domain name for the App is:\n" + obj.getDomainName());
                box.getWindow();
            } else
            {
                ErrorView box = new ErrorView("An error occured while creating the App.\n"
                    + "Contact the administrators.");
                box.getWindow();
            }
        }
    }

    public void populate()
    {
        int size = this.modelC.getSize();
        this.categories = this.modelC.getWebApps();

        this.category = new JFXComboBox<String>();
        this.categoryBox.getChildren().add(this.category);

        for (int i = 0; i < size; i++)
        {
            category.getItems().add(this.categories[i].getName());
        }
    }

    public void setData(Developer obj, WebAppsController wObj, Stage window)
    {
        this.dObj = obj;
        this.wObj = wObj;
        this.window = window;
    }

    private String extractCateID(String query)
    {
        for (WebAppCategory temp : this.categories)
        {
            if (temp.getName().equals(query))
            {
                return temp.getID();
            }
        }
        return "";
    }

    private String generateDomain(String name, String category)
    {
        String dns = "zictc." + name.toLowerCase();

        for (WebAppCategory temp : this.categories)
        {
            if (temp.getName().equals(category))
            {
                dns += temp.getExtension();
                break;
            }
        }
        return dns;
    }
    
    private boolean isDNSTaken(String name, String category)
    {
        String dns = "zictc." + name.toLowerCase();

        for (WebAppCategory temp : this.categories)
        {
            if (temp.getName().equals(category))
            {
                dns += temp.getExtension();
                break;
            }
        }
        return this.modelW.isDNSTaken(dns);
    }
}
