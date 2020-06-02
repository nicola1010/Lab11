/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Simulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	Simulator sim= new Simulator();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

   

    @FXML
    void handleSimula(ActionEvent event) {

    	this.txtResult.clear();
        
        	sim.run(Integer.parseInt(this.txtK.getText()), Float.parseFloat(this.model.fmed(boxRiver.getValue())),
        			this.boxRiver.getValue());
        	
        	int giono= sim.getGiorniNO();
        	LinkedList<Float> ccc= new LinkedList <>(sim.getCcc());
        	float cmed=0;
        	
        	for(float cc: ccc) {
        		cmed=cmed+cc;
        	}
        	
        	cmed=cmed/ccc.size();
        	
        	this.txtResult.appendText("GIORNI NO: " +giono+ "\n Capacità media= "+cmed);
        
    	
    }

    @FXML
    void scegliFiume(ActionEvent event) {

    	this.txtNumMeasurements.clear();
    	this.txtStartDate.clear();
    	this.txtEndDate.clear();
    	this.txtFMed.clear();
    	
    	this.txtNumMeasurements.appendText(this.model.numMis(boxRiver.getValue()));
    	this.txtStartDate.appendText(this.model.primaMis(boxRiver.getValue()));
    	this.txtEndDate.appendText(this.model.ultimaMis(boxRiver.getValue()));
    	this.txtFMed.appendText(this.model.fmed(boxRiver.getValue()));
    	
    }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
        this.boxRiver.getItems().addAll(this.model.getAllRivers());   	
        }
    
    
}
