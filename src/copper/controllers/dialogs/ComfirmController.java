package copper.controllers.dialogs;

import com.jfoenix.controls.JFXButton;

import copper.controllers.webapps.WebAppsController;
import copper.entities.WebApp;
import copper.models.WebAppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ComfirmController 
{
	private Stage window = null;
	
	// The section below does not abide to the coding convention and good design practice
	private WebApp app = null;
	private WebAppModel model = null;
	private WebAppsController wObj = null;
	
	
	public ComfirmController()
	{
		this.model = new WebAppModel();
	}

    @FXML
    private JFXButton yesBtn;

    @FXML
    private JFXButton noBtn;
    
    @FXML
    private Label message;

    @FXML
    void setFalse(ActionEvent event) 
    {
    	this.window.close();
    }

    @FXML
    void setTrue(ActionEvent event) 
    {
    	//delete app and reload web apps window
    	this.model.delete(this.app.getID());
    	this.wObj.reload();
    	this.window.close();
    }
    
    public void setMessage(String msg)
    {
    	this.message.setText(msg);
    }
    
    public void setWindow(Stage window)
    {
    	this.window = window;
    }
    
    public void setData(WebApp obj, WebAppsController wObj)
    {
    	this.app = obj;
    	this.wObj = wObj;
    }
}