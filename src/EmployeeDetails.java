
import java.io.Serializable;


    public class EmployeeDetails implements Serializable
    {
        private String EmployeeName;
        private String EmployeeSkills;
        private String emailid;
        private String phnum;
    
    
            public EmployeeDetails(String EmployeeName, String EmployeeSkills,String emailid,String phnum) 
            {
                this.EmployeeName = EmployeeName;
                this.EmployeeSkills = EmployeeSkills;
                this.emailid = emailid;
                this.phnum = phnum;
            }

                public String getPhnum()
                {
                    return phnum;
                }

                public void setPhnum(String phnum)
                {
                    this.phnum = phnum;
                }

                public String getEmployeeName() 
                {
                    return EmployeeName;
                }

                public String getEmployeeSkills() 
                {
                   return EmployeeSkills;
                }
                public void setEmployeeName(String EmployeeName) 
                {
                    this.EmployeeName = EmployeeName;
                }

                public void setEmployeeSkills(String EmployeeSkills) 
                {
                    this.EmployeeSkills = EmployeeSkills;
                }

                public void setEmailid(String emailid)
                {
                    this.emailid = emailid;
                }

                public String getEmailid()
                {
                    return emailid;
                }
      }