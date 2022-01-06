import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class popUpEmp 
{
     static void display()
    {
        Stage popup = new Stage();
        
        
      Label empname = new Label("Employee name");
      
      TextField emptext = new TextField();
      emptext.setPromptText("Employee Name");
      emptext.setMaxWidth(140);
      

      Label emailname = new Label("Employee Email");
      
      TextField emailtext = new TextField();
      emailtext.setPromptText("Email id");
      emailtext.setMaxWidth(140);

      Label phnum = new Label("Phone Number");
      
      TextField phnumtxt = new TextField();
      phnumtxt.setPromptText("Phone Num");
      phnumtxt.setMaxWidth(140);

      Label empdes = new Label("Employee Skills");
      TextArea destext = new TextArea();
      destext.setMaxSize(300, 400);

      Button addempmain = new Button("Add Employee");
      addempmain.setTextFill(Color.WHITE);
      addempmain.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
      
      Button backemp = new Button("Cancel");
      backemp.setTextFill(Color.WHITE);
      backemp.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
      
      addempmain.setOnAction(e ->
      {
      
      
         try{
          if(!emptext.getText().isEmpty()&& !destext.getText().isEmpty() && 
                  !emailtext.getText().isEmpty() &&
                  !phnumtxt.getText().isEmpty() )
          {
                  if(JobAgency.validateEmailadress(emailtext.getText()))
                  {
                  if(JobAgency.validatePhoneNumber(phnumtxt.getText()))
                  
          {
            EmployeeDetails ed = new EmployeeDetails(emptext.getText(),destext.getText(),emailtext.getText(),phnumtxt.getText());
            JobAgency.alistemp.add(ed);
            JobAgency.saveEmployee();
            int i = JobAgency.alistemp.indexOf(ed);
            JobAgency.listemp.getItems().add(JobAgency.alistemp.get(i).getEmployeeName());
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved Successfully");
            alert.setHeaderText(null);
            alert.setContentText("Employee Saved!");
            alert.showAndWait();
           
            emptext.clear();
            destext.clear();
            emailtext.clear();
            phnumtxt.clear();
            
            popup.close();
          }
                   else
                {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Phone Number format is incorrect ");
                 alert.setHeaderText(null);
                 alert.setContentText("Number must start with '0' and should be 11 digits");
                 alert.showAndWait();
                 phnumtxt.clear();
                }
                  }
                  else
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Email Fromat is Incorrect");
             alert.setHeaderText(null);
             alert.setContentText("Correct Format: xyz@gmail.com or xyz@yahoo.in etc");
             alert.showAndWait();
             emailtext.clear();
        }
          }
          else
              {
                  
            // dialog box if there are any errors
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText(null);
             alert.setContentText("Please fill all the details");
             alert.showAndWait();
             
              }
         }catch(NumberFormatException n)
         {
             
         }
      });
      
            backemp.setOnAction(e ->
            {
               popup.close();
            });


            VBox main = new VBox(20);

            HBox empbuttonbox = new HBox(20);
            empbuttonbox.getChildren().addAll(addempmain,backemp);
            main.getChildren().addAll(empname,emptext,emailname,emailtext,phnum,phnumtxt,empdes,destext,empbuttonbox);
            
            Scene scene = new Scene(main,500,500);
            scene.getStylesheets().add("Popup.css");
            
            popup.setTitle("Add Employee");
            popup.setScene(scene);
            popup.show();
          }

}
