package ProgAssignment4;
public class graduates extends Users{
	public graduates(String ID, String PIN, String name, String email, String phoneNo){
		super(ID,PIN,name, email, phoneNo);
		this.setReturnedDays(30);
		this.setNoBooks(15);
	}
	public String toString(){
		return "GRAD "+super.toString();
	}
}
