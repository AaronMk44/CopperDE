package copper.controllers.webapps;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import copper.entities.WebApp;
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

public class EditWebAppController
{

    private WebApp app = null;
    private Stage window = null;
    private WebAppsController wObj = null;
    private WebAppModel modelW = null;
    private WebAppCategoriesModel modelC = null;
    private Label categoryLabel = null;

    public EditWebAppController()
    {
        this.modelW = new WebAppModel();
        this.modelC = new WebAppCategoriesModel();
        this.categoryLabel = new Label();
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
    void updateApp(ActionEvent event)
    {
        if (this.webAppName.getText().length() == 0
            || this.documentRoot.getText().length() == 0)
        {
            ErrorView box = new ErrorView("Please fill in the App Name and Document root.");
            box.getWindow();
        } else
        {
            this.app.setAppName(this.webAppName.getText());
            this.app.setDocumentRoot(this.documentRoot.getText());
            this.app.setLogoRoot(this.logoRoot.getText());
            this.app.setLanguages(this.languages.getText());
            this.app.setSeoKeywords(this.seoKeywords.getText());
            this.app.setBriefDescription(this.briefDescription.getText());

            if (this.modelW.update(this.app))
            {
                this.window.close();
                this.wObj.reload();

                InfoView box = new InfoView("WebApp successfully updated.");
                box.getWindow();
            } else
            {
                ErrorView box = new ErrorView("An error occured while updating.\n"
                    + "Contact the administrators.");
                box.getWindow();
            }
        }
    }

    public void setData(WebApp obj, WebAppsController wObj, Stage window)
    {
        this.wObj = wObj;
        this.app = obj;
        this.window = window;
    }

    public void populate()
    {
        this.webAppName.setText(this.app.getAppName());
        this.documentRoot.setText(this.app.getDocumentRoot());
        this.logoRoot.setText(this.app.getLogoRoot());
        this.categoryLabel.setText(this.modelC.
            getCategoryName(this.app.getCategoryID()));
        this.categoryBox.getChildren().add(this.categoryLabel);
        this.languages.setText(this.app.getLanguages());
        this.seoKeywords.setText(this.app.getSeoKeywords());
        this.briefDescription.setText(this.app.getBriefDescription());
        this.detailedDescription.setHtmlText(this.app.getDetailedDescription());
    }

}
