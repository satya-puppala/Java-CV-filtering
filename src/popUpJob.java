import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class popUpJob 
{
    public static void display()
    {
        Stage popup = new Stage();
        
        popup.initModality(Modality.APPLICATION_MODAL);
        
         //add button to add job title and job description (opens a new window)
      Label company = new Label("Company Name");

      TextField companyname = new TextField();
      companyname.setPromptText("Company Name");
      companyname.setMaxWidth(140);    
      companyname.setFont(Font.font("Verdana", FontWeight.BOLD,12));
      
      Label jobtitle = new Label("Job Title");

      TextField job = new TextField();
      job.setPromptText("Job Title");
      job.setMaxWidth(140);
      
      Label salary = new Label("Salary per annum");

      TextField salarytxt = new TextField();
      salarytxt.setPromptText("Salary");
      salarytxt.setMaxWidth(140);
      
      Label jobdescrip = new Label("Job Description");

      TextArea description = new TextArea();
      description.setMaxSize(300, 400);
      description.setEditable(true);
      
      Button addjob = new Button("Add job");
      addjob.setTextFill(Color.WHITE);
      addjob.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
      Button back = new Button("Cancel");
      back.setTextFill(Color.WHITE);
      back.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
      
     

        //adding jobs in to list
      addjob.setOnAction(e ->
      {
 
      
          try{
              if(!companyname.getText().isEmpty() && !job.getText().isEmpty()
                      && !salarytxt.getText().isEmpty() && !description.getText().isEmpty() )
              {
                    JobDetails jd = new JobDetails(companyname.getText(),job.getText()
                            ,Double.parseDouble(salarytxt.getText()),description.getText());
                    JobAgency.alistjob.add(jd);
                    int i = JobAgency.alistjob.indexOf(jd);
                    JobAgency.listjob.getItems().add(JobAgency.alistjob.get(i).getJobtitle());
                    JobAgency.listjob.getSelectionModel().clearSelection();
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Saved Successfully");
                     alert.setHeaderText(null);
                     alert.setContentText("Job Saved!");

                    alert.showAndWait();
                    JobAgency.saveJob();
                    companyname.clear();
                    job.clear();
                    salarytxt.clear();
                    description.clear();
                    popup.close();

              }
              else
              {
                  
                  // dialog box if there are any errors
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText(null);
                   alert.setContentText("Please enter the details as follows");
                   alert.showAndWait();
              }
          }catch(Exception p)
          {
              
          }
          
      });
      
      salarytxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    salarytxt.setText(oldValue);
                }
            }
        });
      
      back.setOnAction(e ->
      {
          popup.close();
      });
      
      HBox buttonbox = new HBox(20);
      buttonbox.getChildren().addAll(addjob,back);

      VBox main = new VBox(20);

      main.getChildren().addAll(company,companyname,jobtitle,job,salary,salarytxt,jobdescrip,description,buttonbox);
      
      Scene scene = new Scene(main,600,600);
      scene.getStylesheets().add("Popup.css");
      
      popup.setTitle("Add Jobs");
      popup.setScene(scene);
      popup.show();
      
    }
}
