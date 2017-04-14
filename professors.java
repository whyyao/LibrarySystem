package ProgAssignment4;
public class professors extends Users{
	public professors(String ID, String PIN, String name, String email, String phoneNo){
		super(ID,PIN,name, email, phoneNo);
		this.setReturnedDays(40);
		this.setNoBooks(20);
	}
	public String toString(){
		return "PROF "+super.toString();
	}
}
