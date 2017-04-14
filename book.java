package ProgAssignment4;
public class book {
	private String magicNo;
	private String title;
	private String author;
	private String publisher;
	private String year;
	private String subject;
	private String copyNo;
	private boolean isOut;
	private String returnedDate;
	private Users[] notifyUsers;
	
	public book(String magicNo, String title ,String author, String publisher, String year, String subject, String copyNo){
		this.setMagicNo(magicNo);
		this.setTitle(title);
		this.setAuthor(author);
		this.setPublisher(publisher);
		this.setYear(year);
		this.setSubject(subject);
		this.setCopyNo(copyNo);
		
	}
	
	public void setMagicNo(String magicNo){
		this.magicNo=magicNo;
	}
	public String getMagicNo(){
		return magicNo;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setAuthor(String author){
		this.author=author;
	}
	public String getAuthor(){
		return author;
	}
	public void setPublisher(String publisher){
		this.publisher=publisher;
	}
	public String getPublisher(){
		return publisher;
	}
	public void setYear(String year){
		this.year=year;
	}
	public String getYear(){
		return year;
	}
	public void setSubject(String subject){
		this.subject=subject;
	}
	public String getSubject(){
		return subject;
	}
	public void setCopyNo(String copyNo){
		this.copyNo=copyNo;
	}
	public String getCopyNo(){
		return copyNo;
	}
	public boolean equal(book compared){
		if(!this.getMagicNo().equals(compared.getMagicNo())){
			return false;
		}
		return true;
	}
	public boolean match(String keyword){
		String lowerKey=keyword.toLowerCase();
		if(!copyNo.equals("1"))
			return false;
		String lt=title.toLowerCase();
		String la=author.toLowerCase();
		String ls=subject.toLowerCase();
		String lp=publisher.toLowerCase();
		if(lt.contains(lowerKey)){
			return true;
		}
		else if(la.contains(lowerKey))
			return true;
		else if(ls.contains(lowerKey))
			return true;
		else if(lp.contains(lowerKey))
			return true;
		else if(keyword.equals(year))
			return true;
		else if (keyword.equals(magicNo))
			return true;
		return false;
	}
	public boolean matchMagicNo(String MagicNo){
		if(MagicNo.equals(magicNo)){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String temp=null;
		if(isOut==true){
			temp="Not available";
		}
		else{
			temp="Available!";
		}
		return magicNo+"\n"+title+"\nAuthor(s): "+author+"; Publisher: "+publisher+"; Published in: " +year+ "\nSubject: "+subject+"; Copy number: "+copyNo+"; "+temp+"\nWill be return on: "+returnedDate;
		
	}

	public boolean getIsOut() {
		return isOut;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Users[] getNotifyUsers() {
		return notifyUsers;
	}

	public void setNotifyUsers(Users[] notifyUsers) {
		this.notifyUsers = notifyUsers;
	}


}
