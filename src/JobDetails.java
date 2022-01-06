
import java.io.Serializable;


    public class JobDetails implements Serializable
    {
        private String jobtitle;
        private String jobdescription;
        private String jobcompany;
        private double salary;

    public JobDetails(String jobcompany,String jobtitle,double salary, String jobdescription) 
    {
        this.jobcompany = jobcompany;
        this.jobtitle = jobtitle;
        this.salary = salary;
        this.jobdescription = jobdescription;     
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public void setJobcompany(String jobcompany)
    {
        this.jobcompany = jobcompany;
    }

    public String getJobcompany()
    {
        return jobcompany;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public String getJobdescription() {
       return jobdescription; 
    }
    }
    