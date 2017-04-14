package ProgAssignment4;
public class Users {
	private String ID;
	private String PIN;
	private String name;
	private String email;
	private String phoneNo;
	private String checkedOut[];
	private book checkedBooks[];
	private boolean isBlocked;
	private int returnedDays;
	private int noBooks;
	public void update(){
		System.out.println(this.name+" has been notified");
	}
	
	public Users(String ID, String PIN, String name, String email, String phoneNo){
		setID(ID);
		setPIN(PIN);
		setName(name);
		setEmail(email);
		setPhoneNo(phoneNo);
		setBlocked(false);
		setCheckedOut(null);
		setCheckedBooks(null);
	}
	public boolean userMatch(String ID){
		if(this.ID.equals(ID)){
			return true;
		}
		return false;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public void setReturnedDays(int set){
		returnedDays=set;
	}
	
	public int getReturnedDays(){
		return returnedDays;
	}
	
	public void setNoBooks(int set){
		this.noBooks=set;
	}
	
	public int getNobooks(){
		return noBooks;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String pIN) {
		PIN = pIN;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String[] getCheckedOut() {
		return checkedOut;
	}
	public void setCheckedOut(String checkedOut[]) {
		this.checkedOut = checkedOut;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	@Override
	public String toString(){
		String temp=null;
		if(checkedOut!=null){
			temp=checkedOut[0];
			for(int i=1;i<checkedOut.length;i++){
				temp=temp+" | "+checkedOut[i];
			}
		}
		String tp=null;
		if(checkedBooks!=null){
			tp=checkedBooks[0].getTitle();
			for(int i=1;i<checkedBooks.length;i++){
				tp=tp+" | "+checkedBooks[i].getTitle();
			}
		}
		String op=null;
			if(isBlocked==true){
				op="\nUser is blocked";
			}
			else{
				op="";
			}
		return "ID: "+ID+"\nName: "+name+"\nEmail: "+email+"\nPhoneNo: "+phoneNo+"\nChecked out info: "+temp
				+" \nChecked out books: "+ tp+op;
		
	}

	public book[] getCheckedBooks() {
		return checkedBooks;
	}

	public void setCheckedBooks(book[] checkedBooks) {
		this.checkedBooks = checkedBooks;
	}
	
}
 