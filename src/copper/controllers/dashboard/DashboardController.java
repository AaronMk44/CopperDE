package copper.controllers.dashboard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import copper.models.Configurations;
import copper.views.account.ManageAccountView;
import copper.views.dialogs.InfoView;
import copper.views.dialogs.LogInView;
import copper.views.dialogs.SettingsView;
import copper.views.mail.MailView;
import copper.views.webapps.WebAppsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardController
{
    private Stage window;

    public DashboardController(){}

    @FXML
    private ImageView inboxImg;

    @FXML
    private JFXButton logOutBtn;

    @FXML
    private VBox browserCont;

    @FXML
    private VBox webAppsCont;

    @FXML
    private Label usernameLabel;

    @FXML
    private VBox fileManagerCont;

    @FXML
    private VBox phpMyAdminCont;

    @FXML
    private VBox helpCont;

    @FXML
    private VBox settingsCont;

    @FXML
    void loadBrowser(MouseEvent event) 
    {
    	Configurations.authorise();
    	Runtime runTime = Runtime.getRuntime();
		try {
			Process process = runTime.exec("Adapters\\BrowserAdapter.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void loadFileManager(MouseEvent event) 
    {
    	Configurations.authorise();
    	Runtime runTime = Runtime.getRuntime();
		try {
			Process process = runTime.exec("Adapters\\FileManagerAdapter.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void loadHelp(MouseEvent event) 
    {
        InfoView box = new InfoView("Open the Copper Browser and go to:\n"
                + "zictc.help-desk.info");
        box.getWindow();
    }

    @FXML
    void loadPhpMyAdmin(MouseEvent event) 
    {
    	Configurations.authorise();
    	Runtime runTime = Runtime.getRuntime();
		try {
			Process process = runTime.exec("Adapters\\PhpMyAdminAdapter.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void loadSettings(MouseEvent event) 
    {
    	SettingsView view = new SettingsView();
        view.getWindow();
    }

    @FXML
    void loadWebApps(MouseEvent event) 
    {
    	WebAppsView view = new WebAppsView();
    	view.getWindow();
    }

    @FXML
    void logOut(ActionEvent event)
    {
    	this.window.close();
        LogInView initialView = new LogInView();
        initialView.getWindow();
        Configurations.setUser(null);
    }

    @FXML
    void openInbox(MouseEvent event)
    {
        MailView view = new MailView();
        view.getWindow();
    }

    public void setData(Stage window)
    {  
        this.usernameLabel.setText(Configurations.getUser().getFirstName() + " " + 
        		Configurations.getUser().getLastName());
        this.window = window;
    }
}
