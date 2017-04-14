package ProgAssignment4;
public class students extends Users{
	public students(String ID, String PIN, String name, String email, String phoneNo){
		super(ID,PIN,name, email, phoneNo);
		this.setReturnedDays(20);
		this.setNoBooks(10);
	}
	public String toString(){
		return "STUD "+super.toString();
	} 
}
