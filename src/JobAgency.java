import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JobAgency extends Application implements Serializable 
{ 
     //observable list and arraylist for adding jobs
     static ObservableList olistjob = FXCollections.observableArrayList();
     static ListView listjob = new ListView(olistjob);
    
     static ArrayList <JobDetails> alistjob = new ArrayList();
     static ArrayList <EmployeeDetails> alistRemp = new ArrayList();
     static ArrayList <JobDetails> alistRjob = new ArrayList();
    
     //observable list and arraylist for adding employees
     static ArrayList <EmployeeDetails> alistemp = new ArrayList();
     static ObservableList olistemp = FXCollections.observableArrayList();
     static ListView listemp  = new ListView(olistemp);
    
     //listView for editing jobs
     ListView listeditjobs = new ListView(olistjob);
     ListView listempjobs = new ListView(olistjob);
     //listView for editing employees
     ListView listeditemps = new ListView(olistemp);
     
     //combobox and listview for filtering
     ComboBox compare = new ComboBox(olistjob);
     
     ObservableList flist = FXCollections.observableArrayList();
     ListView filterList = new ListView(flist);
     
     //table for adding recruited employee details
     TableView table = new TableView();
     ObservableList<EmployeeDetails> data =
            FXCollections.observableArrayList();
     
     //table for adding recruited job position
     TableView table1 = new TableView();
     ObservableList<JobDetails> datajob =
            FXCollections.observableArrayList();
     
     //textfields
     TextField editemptitle,editemailtitle, editphnumtxt,editcompanytitle,editsalarytxt,editjobtitle;
     TextArea editempdescription,editjobdescription;
     
     //scenes
     Scene scene,scene1,scene2,scene3,scene4,scene5,scene6;
     
     //stage
     Stage window;
     
    @Override
    public void start(Stage stage) 
    {
        window = stage;
        
        //populating all the jobs from textfile to arraylist
        populateJob();
        for(int i =0; i<alistjob.size();i++)
        {
            listjob.getItems().addAll(alistjob.get(i).getJobtitle());
        }
        
        //populating all the employees from textfile to arraylist
        populateEmp();
        for(int i =0; i<alistemp.size();i++)
        {
            listemp.getItems().addAll(alistemp.get(i).getEmployeeName());
        }
        
        populateRemp();
        for(int i =0; i< alistRemp.size();i++)
            {
                data.addAll(alistRemp);
            }
        for(int i =0; i< alistRjob.size();i++)
            {
                datajob.addAll(alistRjob);
            }
       
        //Login page
       
        //background
        Image bg = new Image("images/410119501Website-Design-Background.jpg");
        ImageView mv = new ImageView(bg);
        
        //labels for user id and password.
        Label username = new Label("User ID");
        Label password = new Label("Password");
 
        //textfield and password field for user to input login credentials.       
        TextField usertxt = new TextField();
        usertxt.setPromptText("User id");
        PasswordField pswrdtxt = new PasswordField();
        
        //size txtfield and password
        usertxt.setMaxWidth(130);
        pswrdtxt.setMaxWidth(130);
        
        //Button to login
        Button login = new Button("Log-in");
        login.setMaxWidth(120);
        login.setTextFill(Color.WHITE);
        login.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));

        //fonts
        username.setFont(Font.font("Calibri",14));
        username.setTextFill(Color.rgb(255, 255, 255));
        username.setGraphic(new ImageView("images/icons8-user-30.png"));
        
        password.setFont(Font.font("Calibri",14));
        password.setTextFill(Color.rgb(255, 255, 255));
        password.setGraphic(new ImageView("images/icons8-lock-30.png"));
        
        //layout
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(username,usertxt,password,pswrdtxt,login);
        box.setMaxSize(250, 300);
        box.setBackground(new Background(new BackgroundFill(Color.rgb(201, 199, 186,0.7), CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane sp = new StackPane();
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(mv,box);
 
        //scene and stage
        scene = new Scene(sp,700,450);
        stage.setTitle("JOB AGENCY");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.show();
        
        login.setDefaultButton(true);
        login.setOnAction(  e ->
        {
            if(usertxt.getText().equals("admin") && pswrdtxt.getText().equals("aceadmin"))
            {   
                    stage.setTitle("Jobs");
                    stage.setScene(scene1);
                    stage.setMaximized(true);
            }
            else
            {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter correct credentials");
                    alert.showAndWait();
                           
                    usertxt.clear();
                    pswrdtxt.clear();
            }
        }
        );
    
        ///jobs page
        Image bg1 = new Image("images/background_1.jpg");
        ImageView mv1 = new ImageView(bg1);
        
        //label
        Label listlabel = new Label("Jobs");
        listlabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        Label txtlabel = new Label("Job Description");
        txtlabel.setFont(Font.font(null, FontWeight.BOLD, 25));
       
        //list 
        listjob.setMaxSize(300,300);
        listjob.setStyle("-fx-background-color:  ;");
       
        //button
        Button add = new Button("Add Jobs");
        add.setTextFill(Color.WHITE);
   
        //textarea
        TextArea descrip = new TextArea();
        descrip.setMaxSize(400,300);
        descrip.setEditable(false);
      
        //layout
        HBox t = new HBox(20);
        VBox left = new VBox(20);
        VBox right = new VBox(20);
      
        left.getChildren().addAll(listlabel,listjob,add);
        right.getChildren().addAll(txtlabel,descrip);
        t.getChildren().addAll(left,right);     
      
        StackPane sp1 = new StackPane();
        sp1.setAlignment(Pos.CENTER);
        sp1.getChildren().addAll(mv1,menuBar(t));
      
        scene1 = new Scene(sp1,800,500);
        scene1.getStylesheets().add("Add.css");
     
        // taking it to new scene where user could add jobs and job descriptions
        add.setOnAction( e ->
        {
            try
            {
                popUpJob.display();
            }
            catch(Exception d)
            {
            }
        });
      
        //list mouse click event to display description 
        listjob.setOnMouseClicked(e ->
        {
            try
            {
                int i = listjob.getSelectionModel().getSelectedIndex();
                descrip.setText("COMPANY: " +"\n"+alistjob.get(i).getJobcompany()
                 .toUpperCase()+"\n"+"REQUIREMENTS: " +"\n"+alistjob.get(i).getJobdescription());
            }
            catch(Exception u)
            {
                   
            }
        });
        
        // employee page
            //label
        Label listlabelemp = new Label("Employees");
        Label txtlabelemp = new Label("Employee Description");
       listlabelemp.setStyle("-fx-font-family: Courier; -fx-font-size: 15pt; "
                + "-fx-font-weight: bold;");
       txtlabelemp.setStyle("-fx-font-family: Courier; -fx-font-size: 15pt; "
                + "-fx-font-weight: bold;");
        //list      
        listemp.setMaxSize(300,300);

        //image
        Image bg2 = new Image("images/scene2.png");
        ImageView mv2 = new ImageView(bg2);
        
        //button
        Button addemp = new Button("Add Employees");
        addemp.setTextFill(Color.WHITE);
      
        //textarea
        TextArea descripemp = new TextArea();
        descripemp.setMaxSize(400,300);
        descripemp.setEditable(false);
      
        //layout
        HBox temp = new HBox(20);
        VBox leftemp = new VBox(20);
        VBox rightemp = new VBox(20);
      
        leftemp.getChildren().addAll(listlabelemp,listemp,addemp);
        rightemp.getChildren().addAll(txtlabelemp,descripemp);
        temp.getChildren().addAll(leftemp,rightemp);
      
        StackPane sp2 = new StackPane();
        sp2.setAlignment(Pos.CENTER);
        sp2.getChildren().addAll(mv2,menuBar(temp));
      
        //BorderPane
        scene2 = new Scene(sp2,800,500);
        scene2.getStylesheets().add("Add.css");

        addemp.setOnAction(e ->
        {
            popUpEmp.display();
        });
      
        listemp.setOnMouseClicked(e ->
        {
            try
            {
                int i = listemp.getSelectionModel().getSelectedIndex();
                descripemp.setText(alistemp.get(i).getEmployeeSkills());
            }
            catch(Exception u)
            {  
            }
        });
      
        //edit jobs page
         //list
        Label listeditLabel= new Label("Jobs");
        listeditLabel.setStyle("-fx-font-family: Courier; -fx-font-size: 10pt; "
                + "-fx-font-weight: bold;");
        listeditjobs.setMaxSize(300, 300);
      
        Label editcompanyname = new Label("Edit Company Name");
        editcompanyname.setStyle("-fx-font-weight:bold;-fx-font-size:10pt;");
        editcompanytitle = new TextField();
        editcompanytitle.setMaxWidth(140);
      
        Label editjobname = new Label("Edit Title");
        editjobname.setStyle("-fx-font-weight:bold;-fx-font-size:10pt;");
        editjobname.setAlignment(Pos.CENTER);
        editjobtitle = new TextField();
        editjobtitle.setMaxWidth(140);
      
        Label editsalary = new Label("Edit Salary");
        editsalary.setStyle("-fx-font-weight:bold;-fx-font-size:10pt;");
        editsalarytxt = new TextField();
        editsalarytxt.setMaxWidth(140);
      
        Label editjobdes = new Label("Edit Job Description");
        editjobdes.setStyle("-fx-font-weight:bold;-fx-font-size:10pt;");
        editjobdescription = new TextArea();
        editjobdescription.setMaxSize(400, 300);
      
        Button edit = new Button("Edit job");
        edit.setTextFill(Color.WHITE);
        edit.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
        Button deletejob = new Button("Delete Job");
        deletejob.setTextFill(Color.WHITE);
        deletejob.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
      
        //Hbox
        HBox editHBox = new HBox(20);
        VBox leftVBox = new VBox(20);
        VBox rightVBox = new VBox(20);
      
        leftVBox.getChildren().addAll(listeditLabel,listeditjobs);
        rightVBox.getChildren().addAll(editcompanyname,editcompanytitle
              ,editjobname,editjobtitle,editsalary,editsalarytxt,editjobdes,editjobdescription,edit,deletejob);
        editHBox.getChildren().addAll(leftVBox,rightVBox);
      
        scene3 = new Scene(menuBar(editHBox));
        scene3.getStylesheets().add("Edit.css");
       
        listeditjobs.setOnMouseClicked(e ->
        {
            try
            {
                int i = listeditjobs.getSelectionModel().getSelectedIndex();
                editcompanytitle.setText(alistjob.get(i).getJobcompany());
                editjobtitle.setText(alistjob.get(i).getJobtitle());
                editsalarytxt.setText(String.valueOf(alistjob.get(i).getSalary()));
                editjobdescription.setText(alistjob.get(i).getJobdescription());
           }
           catch(Exception u)
           {
           }
        });
     
      edit.setOnAction(e ->
      {
          if(!editcompanytitle.getText().isEmpty() && !editjobtitle.getText().isEmpty()
                  && !editsalarytxt.getText().isEmpty() && !editjobdescription.getText().isEmpty())
          {
                int i = listeditjobs.getSelectionModel().getSelectedIndex();
                listeditjobs.getItems().set(i, editjobtitle.getText());
                listeditjobs.getSelectionModel().clearSelection();
                alistjob.set(i, new JobDetails(editcompanytitle.getText(),editjobtitle.getText()
                ,Double.parseDouble(editsalarytxt.getText()),editjobdescription.getText()));
                editcompanytitle.clear();
                editjobtitle.clear();
                editsalarytxt.clear();
                editjobdescription.clear();
                saveJob();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setContentText("Job edited successfully");
                alert.showAndWait();
          }
          else
          {
                // dialog box if there are any errors
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please choose from list to edit");
                alert.showAndWait();
                   
                editcompanytitle.clear();
                editjobtitle.clear();
                editjobdescription.clear();
          }
       });
      
       editsalarytxt.textProperty().addListener(new ChangeListener<String>() 
       {
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
           {
               if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) 
               {
                   editsalarytxt.setText(oldValue);
               }
           }
        });
       
       deletejob.setOnAction(e ->
       {
           if(!editcompanytitle.getText().isEmpty() && !editjobtitle.getText().isEmpty()
                  && !editsalarytxt.getText().isEmpty() && !editjobdescription.getText().isEmpty())
           {
               Alert alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Confirmation Dialog");
               alert.setHeaderText(null);
               alert.setContentText("Delete File?");

               Optional<ButtonType> result = alert.showAndWait();
               if (result.get() == ButtonType.OK)
               {
                   int i = listeditjobs.getSelectionModel().getSelectedIndex();
                   alistjob.remove(i);
                   listeditjobs.getItems().remove(i);
                   listeditjobs.getSelectionModel().clearSelection();
                   saveJob();
               }
            }
            else
           {
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("Invalid");
               alert.setHeaderText(null);
               alert.setContentText("Please choose from list to delete");
               alert.showAndWait();
           }   
        });
       
      
        //edit employees
            //list
        Label listeditempsLabel= new Label("Employees");
        listeditempsLabel.setStyle("-fx-font-family: Courier; -fx-font-size: 10pt; "
                + "-fx-font-weight: bold;");
      listeditemps.setMaxSize(300, 300);
      
      Label editempname = new Label("Edit Name");
      editempname.setStyle("-fx-font-weight:bold;-fx-font-size:10pt;");
      editemptitle = new TextField();
      editemptitle.setMaxWidth(140);
      
      Label editemailname = new Label("Edit Email");
      editemailname.setStyle("-fx-font-weight:bold;-fx-font-size:10pt;");
      editemailtitle = new TextField();
      editemailtitle.setMaxWidth(140);
      
      Label editphnum = new Label("Phone Number");
      editphnum.setStyle("-fx-font-weight:bold;-fx-font-size:10pt;");
      editphnumtxt = new TextField();
      editphnumtxt.setMaxWidth(140);
      
      Label editempdes = new Label("Edit Employee Skills");
      editempdes.setStyle("-fx-font-weight:bold;-fx-font-size:10pt;");
      editempdescription = new TextArea();
      editempdescription.setMaxSize(400, 300);
      
      Button empedit = new Button("Edit Employee");
      empedit.setTextFill(Color.WHITE);
      empedit.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
      Button deleteemp = new Button("Delete Employee");
      deleteemp.setTextFill(Color.WHITE);
      deleteemp.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
      
      //Hbox
      HBox editempHBox = new HBox(20);
      VBox leftempVBox = new VBox(20);
      VBox rightempVBox = new VBox(20);
      
      leftempVBox.getChildren().addAll(listeditempsLabel,listeditemps);
      rightempVBox.getChildren().addAll
        (
                editempname,editemptitle,editemailname,
                editemailtitle,editphnum,editphnumtxt,editempdes,editempdescription,empedit,deleteemp
        );
      
      editempHBox.getChildren().addAll(leftempVBox,rightempVBox);
      scene4 = new Scene(menuBar(editempHBox));
      scene4.getStylesheets().add("Edit.css");
     
      listeditemps.setOnMouseClicked(e ->
      {
          try
          {
              int i = listeditemps.getSelectionModel().getSelectedIndex();
              editemptitle.setText(alistemp.get(i).getEmployeeName());
              editempdescription.setText(alistemp.get(i).getEmployeeSkills());
              editemailtitle.setText(alistemp.get(i).getEmailid());
              editphnumtxt.setText(alistemp.get(i).getPhnum());
          }
          catch(Exception u)
          {
          }
      });
      
      empedit.setOnAction(e ->
      {
          try
          {
              if(!editemptitle.getText().isEmpty()&& !editempdescription.getText().isEmpty() && !editemailtitle.getText().isEmpty()
                  && !editphnumtxt.getText().isEmpty() )
              {
                  if( validateEmailadress(editemailtitle.getText()) )
                  {
                      if(validatePhoneNumber(editphnumtxt.getText()))
                      {
                          int i = listeditemps.getSelectionModel().getSelectedIndex();
                          listeditemps.getItems().set(i,editemptitle.getText());
                          listeditemps.getSelectionModel().clearSelection();
                          listemp.getSelectionModel().clearSelection();
                          alistemp.set(i, new EmployeeDetails(editemptitle.getText(),
                          editempdescription.getText(),editemailtitle.getText(),editphnumtxt.getText()));
        
                          editemptitle.clear();
                          editempdescription.clear();
                          editemailtitle.clear();
                          editphnumtxt.clear();
        
                          saveEmployee();
        
                          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("Successful");
                          alert.setHeaderText(null);
                          alert.setContentText("Employee Edited successfully");
                          alert.showAndWait();
                      }
                      else
                      {
                          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setHeaderText(null);
                          alert.setContentText("Please Enter Correct format of Phone Number");
                          alert.showAndWait();
                      }
                 }
                 else
                  {
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                      alert.setHeaderText(null);
                      alert.setContentText("Please Enter Correct format of email id");
                      alert.showAndWait();
                  }
              }
              else
              {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setHeaderText(null);
                  alert.setContentText("Please choose from list to edit");
                  alert.showAndWait();
              }
          }
          catch(Exception q)
          {
          }
      });
      
      deleteemp.setOnAction(e ->
      {
          try
          {
              if(!editemptitle.getText().isEmpty()&& !editempdescription.getText().isEmpty() && !editemailtitle.getText().isEmpty()
                  && validatePhoneNumber(editphnumtxt.getText()) )
              {
                  Alert alert2 = new Alert(AlertType.CONFIRMATION);
                  alert2.setTitle("Confirmation Dialog");
                  alert2.setHeaderText("Look, a Confirmation Dialog");
                  alert2.setContentText("Are you ok with this?");
                  Optional<ButtonType> result = alert2.showAndWait();
                  if (result.get() == ButtonType.OK)
                  {
                      int i = listeditemps.getSelectionModel().getSelectedIndex();
                      listeditemps.getItems().remove(i);
                      alistemp.remove(i);
                      listeditemps.getSelectionModel().clearSelection();
            
                      saveEmployee();
                
                      editemptitle.clear();
                      editempdescription.clear();
                      editemailtitle.clear();
                      editphnumtxt.clear();
                  } 
              }
              else
              {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setHeaderText(null);
                  alert.setContentText("Please choose from list to edit");
                  alert.showAndWait();
              }
          }
          catch(Exception u)
          {
          }
      });
      
       //view page
        compare.setMaxWidth(160);
        filterList.setMaxSize(300, 300);
        
        Label compname = new Label("Jobs");
        compname.setStyle("-fx-font-weight:bold;-fx-font-size:15pt;");
        Label listname = new Label("Employees suitable");
        listname.setStyle("-fx-font-weight:bold;-fx-font-size:15pt;");
        Label recemp = new Label("Employees Recruited");
        recemp.setStyle("-fx-font-weight:bold;-fx-font-size:15pt;");
        
        TableColumn NameCol = new TableColumn("Name");
        NameCol.setMinWidth(100);
        NameCol.setCellValueFactory(new PropertyValueFactory<EmployeeDetails,String>("EmployeeName"));
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(150);
        emailCol.setCellValueFactory(new PropertyValueFactory<EmployeeDetails,String>("emailid"));
        TableColumn phnumCol = new TableColumn("Phone Number");
        phnumCol.setMinWidth(100);
        phnumCol.setCellValueFactory(new PropertyValueFactory<EmployeeDetails,String>("phnum"));
        TableColumn positionCol = new TableColumn("Position");
        positionCol.setMaxWidth(100);
        positionCol.setCellValueFactory(new PropertyValueFactory<JobDetails,String>("jobtitle"));
        
        table1.getColumns().add(positionCol);
        table1.setItems(datajob);
        table1.setEditable(false);
        table.getColumns().addAll(NameCol, emailCol,phnumCol);
        table.setItems(data);
        table.setEditable(false);
        
        HBox mainBox = new HBox(30);
        VBox jobBox = new VBox(30);
        jobBox.getChildren().addAll(compname,compare,listname,filterList);
        
        HBox tableBox = new HBox();
        tableBox.getChildren().addAll(table,table1);
        tableBox.setMaxHeight(400);
        
        VBox jBox = new VBox(30);
        jBox.getChildren().addAll(recemp,tableBox);
        
        mainBox.getChildren().addAll(jobBox,jBox);
        
        scene5 = new Scene(menuBar(mainBox),900,600);
        scene5.getStylesheets().add("Filter.css");
        
        Label EmployeeName = new Label("Name:");
        TextField Name = new TextField("");
        Name.setMaxWidth(150);
        Name.setEditable(false);
        
        Label Descrip = new Label("Skills");
        TextArea descRiption = new TextArea();
        descRiption.setMaxSize(300, 400);
        descRiption.setEditable(false);
        
        Button recruit = new Button("Recruit");
        recruit.setTextFill(Color.WHITE);
        recruit.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
       
        Button cancil = new Button("Back");
        cancil.setTextFill(Color.WHITE);
        cancil.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
        
        VBox full = new VBox(20);
        full.getChildren().addAll(EmployeeName,Name,Descrip,descRiption,recruit,cancil);
        
        scene6 = new Scene(full,500,500);
        scene6.getStylesheets().add("Popup.css");
        
        compare.setOnAction(e ->
        {
            if(!filterList.getItems().isEmpty())
            {
                filterList.getItems().clear();
            }
            try
            {
                int i = compare.getSelectionModel().getSelectedIndex();
                for(int j =0; j< alistemp.size(); j++)
                {
                    if(containsAllChars(alistemp.get(j).getEmployeeSkills().toLowerCase(),
                        alistjob.get(i).getJobdescription().toLowerCase()))
                    {
                        filterList.getItems().add(alistemp.get(j).getEmployeeName());
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException q)
            {  
            }
        });
        
        filterList.setOnMouseClicked(e ->
        {
            int i = filterList.getSelectionModel().getSelectedIndex();
            try
            {
                if(compare.getValue() != null)
                {
                    if( filterList.getSelectionModel().isSelected(i))
                    {
                        window.setScene(scene6);
                        
                        for(int j =0; j< alistemp.size(); j++)
                        {
                            if(containsAllChars(alistemp.get(j).getEmployeeName().toLowerCase(),
                                    flist.get(i).toString().toLowerCase()
                                            .trim().replace("[", "").replace("]", "")))
                            {
                                Name.setText(alistemp.get(j).getEmployeeName());
                                descRiption.setText(alistemp.get(j).getEmployeeSkills());
                            }               
                        }
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Click on item ");
                        alert.showAndWait();
                    }
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please choose from dropdown to compare");
                    alert.showAndWait();
                }
            }
            catch(Exception q)
            {
            }
        });
        
        recruit.setOnAction(e ->
        {
            window.setScene(scene5);
            int i = filterList.getSelectionModel().getSelectedIndex();
            int k = compare.getSelectionModel().getSelectedIndex();
            
            for(int j =0; j< alistemp.size(); j++)
            {
                if(alistemp.get(j).getEmployeeName().toLowerCase().trim()
                        .contains(filterList.getItems().get(i).toString().toLowerCase().trim()))
                {
                    data.add(new EmployeeDetails(alistemp.get(j).getEmployeeName(),
                            alistemp.get(j).getEmployeeSkills(),alistemp.get(j).getEmailid(),
                            alistemp.get(j).getPhnum()));
                    
                    datajob.add(new JobDetails(alistjob.get(k).getJobcompany(),
                            alistjob.get(k).getJobtitle(),alistjob.get(k).getSalary(),
                            alistjob.get(k).getJobdescription()));

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Recruited Successfully");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee Recruited!");
                    alert.showAndWait();
                    
                    alistRemp.add(new EmployeeDetails(alistemp.get(j).getEmployeeName(),alistemp.get(j).getEmployeeSkills()
                    ,alistemp.get(j).getEmailid(),alistemp.get(j).getPhnum()));
                    alistRjob.add(new JobDetails(alistjob.get(k).getJobcompany(),
                            alistjob.get(k).getJobtitle(),alistjob.get(k).getSalary(),
                            alistjob.get(k).getJobdescription()));
                    saveRemp();
                }               
            }
            compare.getSelectionModel().clearSelection();
            filterList.getItems().clear();
        });
        
        cancil.setOnAction(e -> 
        {
            stage.setScene(scene5);
            compare.getSelectionModel().clearSelection();
            filterList.getItems().clear();
        });
    }
public Set<Character> stringToCharacterSet(String s)
{
    Set<Character> set = new HashSet<>();
    for (char c : s.toCharArray()) 
    {
        set.add(c);
    }
    return set;
}
public boolean containsAllChars(String container, String containee) 
{
    return  stringToCharacterSet(container).containsAll(stringToCharacterSet(containee));
}
public  BorderPane menuBar(HBox v)
{
    //menu bar
    MenuBar menuBar = new MenuBar();
    
    // --- Menu File
    Menu menuFile = new Menu("Add");
    MenuItem jobs = new MenuItem("Jobs");
        
    jobs.setOnAction(e ->
    {
        listjob.getSelectionModel().clearSelection();
        window.setTitle("Jobs");
        window.setScene(scene1);
    });
    
    MenuItem employees = new MenuItem("Employees");
    
    employees.setOnAction(e ->
    {
        listemp.getSelectionModel().clearSelection();
        window.setTitle("Employee");
        window.setScene(scene2);
    });
    
    menuFile.getItems().addAll(jobs,employees);
 
    // --- Menu Edit
    Menu menuEdit = new Menu("Edit");
    MenuItem ejobs = new MenuItem("Jobs");
        
    ejobs.setOnAction(e->
    {
        listeditjobs.getSelectionModel().clearSelection();
        editcompanytitle.clear();
        editjobtitle.clear();
        editjobdescription.clear();
        editsalarytxt.clear();
            
        window.setTitle("Edit Jobs");
        window.setScene(scene3);
    });
    
    MenuItem eemployees = new MenuItem("Employees");
        
    eemployees.setOnAction(e ->
    {
        listeditemps.getSelectionModel().clearSelection();
        editemptitle.clear();
        editempdescription.clear();
        editemailtitle.clear();
        editphnumtxt.clear();
            
        window.setTitle("Edit Employees");
        window.setScene(scene4);
    });
        
    menuEdit.getItems().addAll(ejobs,eemployees);
 
    // --- Menu View
    Menu menuView = new Menu("View");
    MenuItem viewclick = new MenuItem("Filter");
    
    viewclick.setOnAction(e ->
    {
        window.setScene(scene5);

        compare.getSelectionModel().clearSelection();
        filterList.getItems().clear();
    });
    
    menuView.getItems().add(viewclick);
    menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        
    BorderPane bp = new BorderPane();
    bp.setTop(menuBar);
    bp.setCenter(v);
    return bp;
} 
static boolean validatePhoneNumber(String phoneNo) 
{
    //validate phone numbers of format "1234567890"
    if (phoneNo.matches("\\d{11}") && phoneNo.startsWith("0"))
    {
        return true;
    }
    return false;
} 
static boolean validateEmailadress(String email)
{
    if(email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~"
                + "^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
    {
        return true;
    }
    return false;
}  
public static void  saveJob()
{
    try
    {
        FileOutputStream file = new FileOutputStream("Job.txt");
        ObjectOutputStream outputFile = new ObjectOutputStream(file);
        
        //for loop to increase ArrayList with each Object the user saves
        for (int i = 0; i<alistjob.size(); i++)
        {
            outputFile.writeObject(alistjob.get(i)); 
        }
        outputFile.close();  
    }
    catch (IOException e)
    {
    }
} 
public void populateJob()
{
    try
    {
        //opening file to be used
        FileInputStream file = new FileInputStream("Job.txt");
        //reading from the input stream 
        ObjectInputStream inputFile = new ObjectInputStream(file);

        /*checking for the end of the file
         (start on false as when you start reading it wont be the end)*/
        boolean endOfFile = false;
        /*while loop to make sure while its not the end of the file data
         continues to be read/*
        */
        while (!endOfFile)
        {
            try
            {
                alistjob.add((JobDetails)inputFile.readObject());
            }
             // when end of file happens change endOFfile to true
             catch (EOFException e)
             {
               endOfFile = true; 
             }
             catch (IOException | ClassNotFoundException f)
             { 
             }
        }
        inputFile.close();
    }
    catch (IOException e)
    {
    } 
}
public static void  saveRemp()
{
    try
    {
        FileOutputStream file = new FileOutputStream("Remp.txt");
        ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
        //for loop to increase ArrayList with each Object the user saves
        for (int i = 0; i<alistRemp.size(); i++)
        {
            outputFile.writeObject(alistRemp.get(i)); 
            outputFile.writeObject(alistRjob.get(i)); 
        }
        outputFile.close();
    }
    catch (IOException e)
    { 
    }
}
public void populateRemp()
{
    try
    {
        //opening file to be used
        FileInputStream file = new FileInputStream("Remp.txt");
        //reading from the input stream 
        ObjectInputStream inputFile = new ObjectInputStream(file);

        /*checking for the end of the file
        (start on false as when you start reading it wont be the end)*/
        boolean endOfFile = false;
        /*while loop to make sure while its not the end of the file data
         continues to be read/*
        */
        while (!endOfFile)
        {
            try
            {
                alistRemp.add((EmployeeDetails)inputFile.readObject());
                alistRjob.add((JobDetails)inputFile.readObject());
            }
             // when end of file happens change endOFfile to true
             catch (EOFException e)
             {
                 endOfFile = true;
             }
             catch (IOException | ClassNotFoundException f)
             {
             }
        }
        inputFile.close();
    }
    catch (IOException e)
    {
    }
}
public static void saveEmployee()
{
    try
    {
        FileOutputStream file = new FileOutputStream("Employee.txt");
        ObjectOutputStream outputFile = new ObjectOutputStream(file);
            
        //for loop to increase ArrayList with each Object the user saves
        for (int i = 0; i<alistemp.size(); i++)
        {
            outputFile.writeObject(alistemp.get(i)); 
        }
        outputFile.close();          
    }
    catch (IOException e)
    {
    }
}
public void populateEmp()
{
    try
    {
        //opening file to be used
        FileInputStream file = new FileInputStream("Employee.txt");
        //reading from the input stream 
        ObjectInputStream inputFile = new ObjectInputStream(file);
          
        /*checking for the end of the file
         (start on false as when you start reading it wont be the end)*/
        boolean endOfFile = false;
        /*while loop to make sure while its not the end of the file data
        continues to be read/*
        */
        while (!endOfFile)
        {
            try
            {
                alistemp.add((EmployeeDetails)inputFile.readObject());
            }
            // when end of file happens change endOFfile to true
            catch (EOFException e)
            {
                endOfFile = true; 
            }
            catch (IOException | ClassNotFoundException f)
            {
            }
        }
        inputFile.close();
    }
    catch (IOException e)
    {
    }
}
public static void main(String[] args)
{      
    launch(args);
}
}

