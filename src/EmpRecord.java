public class EmpRecord {

    private String lastName;
    private String firstName;
    private String position;
    private String site;
    private String empID;
    private boolean delete;

    public EmpRecord(String last, String first, String pos, String loc){
        lastName = last;
        firstName = first;
        position = pos;
        site = loc;
        empID = "";
        delete = false;
    }

    public EmpRecord() {}

    public String getEmpID(){
        return empID;
    }
    public void setEmpID(String ID){
        empID = ID;
    }

    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getPosition(){
        return position;
    }
    public String getSite(){
        return site;
    }

    public boolean getDelete(){ return delete; }
    public void setDelete(){ delete = true; }

    public String getCompiled(){ return lastName + firstName + position + site + empID; }
    public String toString(){
        return getEmpID() + "\t" + getLastName() + "\t" + getFirstName() + "\t" + getPosition() + "\t" + getSite();
    }

}
