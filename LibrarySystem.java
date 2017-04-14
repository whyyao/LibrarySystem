package ProgAssignment4;

//Yuan Yao, 8647851, yuanyao00@umail.ucsb.edu
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class LibrarySystem {
	private static final Users[][] Users = null;
	private catalog cat=new catalog();
	private usersCat userCat=new usersCat();
	private Users currentUser;
	private boolean running;
	public LibrarySystem(){
		//read files
		ArrayList<String> stored;
		stored=new ArrayList<String>();
		FileInputStream fstream;
		try{
			fstream= new FileInputStream("Library.data");
			BufferedReader br=  new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			
			while((strLine=br.readLine())!=null){
				String[] parts = strLine.split("\n");
				for(int i=0;i<parts.length;i++){
					stored.add(parts[i]);
				}
			}
			br.close();
		}catch (Exception e){
				System.out.println("Something went wrong!");
				e.printStackTrace();
			}
		//seperate to books
		int cursor=0;
		for(int i=cursor;i<stored.size();i++){
			if(stored.get(i).equals("::::::::::") && stored.get(i+1).equals("Books")){
				cursor=i;
				break;
			}
		}
		int to=stored.size()-1;
			for(int i=cursor+3;i<stored.size();i++){
				if(stored.get(i).equals("::::::::::")){
					to=i;
					break;
				}
			}
		ArrayList<String>books=new ArrayList<String>(stored.subList(cursor+3, to));
		//seperate to students
		cursor=0;
		for(int i=cursor;i<stored.size();i++){
			if(stored.get(i).equals("::::::::::") && stored.get(i+1).equals("Students")){
				cursor=i;
				break;
			}
		}
		to=stored.size();
			for(int i=cursor+3;i<stored.size();i++){
				if(stored.get(i).equals("::::::::::")){
					to=i;
					break;
				}
			}
		ArrayList<String>students=new ArrayList<String>(stored.subList(cursor+3, to));
		//seperate to graduates
		cursor=0;
		for(int i=cursor;i<stored.size();i++){
			if(stored.get(i).equals("::::::::::") && stored.get(i+1).equals("Graduate Researchers")){
				cursor=i;
				break;
			}
		}
		to=stored.size();
			for(int i=cursor+3;i<stored.size();i++){
				if(stored.get(i).equals("::::::::::")){
					to=i;
					break;
				}
			}
		ArrayList<String>graduates=new ArrayList<String>(stored.subList(cursor+3, to));
		
		//Seperate Prof
		cursor=0;
		for(int i=cursor;i<stored.size();i++){
			if(stored.get(i).equals("::::::::::") && stored.get(i+1).equals("Professors")){
				cursor=i;
				break;
			}
		}
		to=stored.size();
			for(int i=cursor+3;i<stored.size();i++){
				if(stored.get(i).equals("::::::::::")){
					to=i;
					break;
				}
			}
		ArrayList<String>prof=new ArrayList<String>(stored.subList(cursor+3, to));
		for(int i=0;i<books.size();i=i+7){
			book temp=new book(books.get(i),books.get(i+1),books.get(i+2),books.get(i+3),books.get(i+4),books.get(i+5),books.get(i+6));
			cat.addBook(temp);
		}
		
		//file in students
		for(int i=0;i<students.size();i=i+5){
			students temp=new students(students.get(i),students.get(i+1),students.get(i+2),students.get(i+3),students.get(i+4));
			usersCat.addUsers(temp);
			if(i+5>=students.size()){
				continue;
			}
			if(students.get(i+5).length()!=7){
				String[] checkedOut=students.get(i+5).split(",");
				temp.setCheckedOut(checkedOut);
				i++;
			}
			if(i+5>=students.size()){
				continue;
			}
			if(students.get(i+5).length()!=7){
				temp.setBlocked(true);
				i++;
			}
		}
		//file in graduates
		for(int i=0;i<graduates.size();i=i+5){
			graduates temp=new graduates(graduates.get(i),graduates.get(i+1),graduates.get(i+2),graduates.get(i+3),graduates.get(i+4));
			usersCat.addUsers(temp);
			if(i+5>=graduates.size()){
				continue;
			}
			if(graduates.get(i+5).length()!=7){
				String[] checkedOut=graduates.get(i+5).split(",");
				temp.setCheckedOut(checkedOut);
				i++;
			}
			if(i+5>=graduates.size()){
				continue;
			}
			if(graduates.get(i+5).length()!=7){
				temp.setBlocked(true);
				i++;
			}

		}
		//file in professors
		for(int i=0;i<prof.size();i=i+5){
			professors temp=new professors(prof.get(i),prof.get(i+1),prof.get(i+2),prof.get(i+3),prof.get(i+4));
			usersCat.addUsers(temp);
			if(i+5>=prof.size()){
				continue;
			}
			if(prof.get(i+5).length()!=7){
				String[] checkedOut=prof.get(i+5).split(",");
				temp.setCheckedOut(checkedOut);
				i++;
			}
			if(i+5>=prof.size()){
				continue;
			}
			if(prof.get(i+5).length()!=7){
				temp.setBlocked(true);
				i++;
			}

		}
		//usersCat.print();

		for(int i=0;i<usersCat.size();i++){
			Users temp=usersCat.getUser(i);
			if(temp.getCheckedOut()!=null){
				String[] checkedOutInfo=temp.getCheckedOut();
				ArrayList<book> booksChecked=new ArrayList<book>();
				for(int j=0;j<checkedOutInfo.length;j++){
					String[] info=checkedOutInfo[j].split(":");
					book[] qualifiedBooks= cat.search(info[1]);
					for(int k=0;k<qualifiedBooks.length;k++){
						if(qualifiedBooks[k].getCopyNo().equals(info[2])){
							booksChecked.add(qualifiedBooks[k]);
							qualifiedBooks[k].setOut(true);
							String[] date=info[0].split("/");
							int day=Integer.parseInt(date[2])+temp.getReturnedDays();
							int realDate=day;
						    int realMonth=Integer.parseInt(date[1]);
								while(realDate>31){
									realDate=realDate-31;
									realMonth=realMonth+1;
								}
								String result=date[0]+"/"+realMonth+"/"+realDate;
								qualifiedBooks[k].setReturnedDate(result);
								}
					}
					}
				book[] settingBook=new book[booksChecked.size()];
				for(int k=0;k<booksChecked.size();k++){
					settingBook[k]=booksChecked.get(k);
				}
				temp.setCheckedBooks(settingBook);
				}
		}
//		usersCat.print();
	}
	
	public void run(){
		running=true;
		while(running==true){
			login();
			logOut();
		}
	}
	public void login(){
		System.out.println("Welcome to the library!");
		boolean logedIn=false;
		Scanner reader=new Scanner(System.in);
		System.out.println("Please enter your ID, enter -1 to exit the entire program");
		String ID=reader.nextLine();
		if(ID.equals("-1")){
			this.stopRun();
			System.out.println("Program exit"); 
			return;
		}
		while(userCat.searchUsers(ID)==null){
			System.out.println("Wrong ID, please re-enter");
			ID=reader.nextLine();
		}
		Users findUsers=userCat.searchUsers(ID);
		currentUser=findUsers;
		System.out.println("Please enter PIN for "+ID);
		String PIN=reader.nextLine();
		String realPIN=findUsers.getPIN();
		if(PIN.equals(realPIN)){
			logedIn=true;
		}
		String date=null;
		if(logedIn==true){
			System.out.println("You are in! Input today's date in yyyy/mm/dd");
			date=reader.nextLine();
		}
		
		while(logedIn==true){
			System.out.println("Input an Operation Letter");
			System.out.println("Search(S)\nDisplay(D)\nCheckout(C)\nRecall(R)\nWait(W)\nReturn(T)\nShow(U)\nLogout(E)");
			String input=reader.nextLine();
			switch (input){
			case "S": 
				System.out.println("Input the keyword");
				String keyword=reader.nextLine();
				book[] t=this.searchBooks(keyword);
				for(int i=0;i<t.length;i++){
					System.out.println(t[i]);
				}
				break;
			case "D":
				System.out.println("Input magic number");
				String number=reader.nextLine();
				this.displayInfo(number);
				break;
			case "C":
				//cat.print();
				System.out.println("Input magic number");
				String magicNo=reader.nextLine();
				System.out.println("Input copy number");
				String copyNo=reader.nextLine();
				this.checkOut(magicNo,copyNo,date);
				//cat.print();
				//usersCat.print();
				break;
			case "T":
				//cat.print();
				System.out.println("Input magic number");
				String magicNo1=reader.nextLine();
				System.out.println("Input copy number");
				String copyNo1=reader.nextLine();
				this.returnBook(magicNo1,copyNo1);
		//		cat.print();
				//usersCat.print();
				break;
			case "U":
				this.UserInfo();
				break;
			case "R":
				System.out.println("Input magic number");
				String magicNo2=reader.nextLine();
				this.recall(magicNo2, date);
				break;
			case "W":
				System.out.println("Input magic number");
				String magicNo3=reader.nextLine();
				this.wait(magicNo3);
				break;
			case "E":
				logedIn=false;
				System.out.println("Log out successed");
				break;
			
			}
			System.out.println("================================================");
		}
	}
	
	public void logOut(){
		currentUser=null;
	}
	public void stopRun(){
		running=false;
	}
	public void displayInfo(String magicNo){
		book[] temp=cat.searchMagicNo(magicNo);
		for(int i=0;i<temp.length;i++){
			if(temp[i].getIsOut()==false){
				System.out.println(temp[i].getTitle()+", copy "+temp[i].getCopyNo()+", available");
			}
			else{
				System.out.println(temp[i].getTitle()+", copy "+temp[i].getCopyNo()+", is currently out, in loean till "+temp[i].getReturnedDate());
			}
		}
	}

	public book[] searchBooks(String keyword){
		return cat.search(keyword);
	}
	
	public void checkOut(String magicNo, String copyNo, String date){
		boolean check=currentUser.isBlocked();
		if (check==true){
			return;
		}
		book[] temp=cat.searchMagicNo(magicNo);
		for(int i=0;i<temp.length;i++){
			if(temp[i].getCopyNo().equals(copyNo)){
				if(temp[i].getIsOut()==true)
					return;
				temp[i].setOut(true);
				String[] dateDetail=date.split("/");
				int day=Integer.parseInt(dateDetail[2])+currentUser.getReturnedDays();
				int realDate=day;
			    int realMonth=Integer.parseInt(dateDetail[1]);
			    while(realDate>31){
			    	realDate=realDate-31;
			    	realMonth=realMonth+1;
			    }
			    String result=dateDetail[0]+"/"+realMonth+"/"+realDate;
			    temp[i].setReturnedDate(result);
			    System.out.println("Checked out succeed, you return date is "+result);
			    String checkedOutInfo=date+":"+magicNo+":"+copyNo;
			    if(currentUser.getCheckedOut()!=null){
			    String[] newCheckedOut= new String[currentUser.getCheckedOut().length+1];
			    for(int j=0;j<currentUser.getCheckedOut().length;j++){
			    	newCheckedOut[j]=currentUser.getCheckedOut()[j];
			    }
			    newCheckedOut[currentUser.getCheckedOut().length]=checkedOutInfo;
			    currentUser.setCheckedOut(newCheckedOut);
			    book newbook=temp[i];
			    book[] newCheckedBook=new book[currentUser.getCheckedBooks().length+1];
			    for(int j=0;j<currentUser.getCheckedBooks().length;j++){
			    	newCheckedBook[j]=currentUser.getCheckedBooks()[j];
			    }
			    newCheckedBook[currentUser.getCheckedBooks().length]=newbook;
			    currentUser.setCheckedBooks(newCheckedBook);
			    }
			  
			    else{
			    	String[] newCheckedOut= new String[1];
			    	newCheckedOut[0]=checkedOutInfo;
			    	currentUser.setCheckedOut(newCheckedOut);
			    	book[] newCheckedBook=new book[1];
			    	newCheckedBook[0]=temp[i];
			    	currentUser.setCheckedBooks(newCheckedBook);
			    }
			}
		}
	}
	
	public void returnBook(String magicNo, String copyNo){
		book[] temp=cat.searchMagicNo(magicNo);
		if(temp==null){
			System.out.println("Wrong magic number");
			return;
		}
		for(int i=0;i<temp.length;i++){
			if(temp[i].getCopyNo().equals(copyNo)){
				
				temp[i].setOut(false);
				temp[i].setReturnedDate(null);
				System.out.println("You book is returned");
				if(temp[i].getNotifyUsers()!=null){
					for(int j=0;j<temp[i].getNotifyUsers().length;j++){
						temp[i].getNotifyUsers()[j].update();
					}
				}
				book[] newbook=new book[currentUser.getCheckedBooks().length-1];
				int index=0;
				if(currentUser.getCheckedBooks().length-1==0){
					currentUser.setCheckedBooks(null);
					currentUser.setCheckedOut(null);
				}
				else{
				for(int j=0;j<currentUser.getCheckedBooks().length;j++){
					if(currentUser.getCheckedBooks()[j].getMagicNo().equals(temp[i].getMagicNo()) && currentUser.getCheckedBooks()[j].getCopyNo().equals(temp[i].getCopyNo() ))
							continue;
					newbook[index]=currentUser.getCheckedBooks()[j];
					index++;
				}
				currentUser.setCheckedBooks(newbook);
				index=0;
				String[] CheckedOutInfo=currentUser.getCheckedOut();
				String[] newCheckedOut=new String[CheckedOutInfo.length-1];
				for(int j=0;j<CheckedOutInfo.length;j++){
					String parts[]=CheckedOutInfo[j].split(":");
					if(parts[1].equals(magicNo) && parts[2].equals(copyNo)){
						continue;
					}
					newCheckedOut[index]=CheckedOutInfo[j];
					index++;
				}
				currentUser.setCheckedOut(newCheckedOut);
				}
			}
		}
	}
	public void UserInfo(){
		System.out.println(currentUser);
	}
	public void recall(String magicNo, String date){
		//usersCat.print();
		if(cat.searchMagicNo(magicNo)==null){
			System.out.println("Book not found");
			return;
		}
		book[] temp=cat.searchMagicNo(magicNo);
		for(int i=0;i<temp.length;i++){
			if(temp[i].getIsOut()==false){
				System.out.println("Book Available");
				return;
			}
		}
		String[] dateDetail=date.split("/");
		int day=Integer.parseInt(dateDetail[2])+3;
		int realDate=day;
	    int realMonth=Integer.parseInt(dateDetail[1]);
	    while(realDate>31){
	    	realDate=realDate-31;
	    	realMonth=realMonth+1;
	    }
	    String result=dateDetail[0]+"/"+realMonth+"/"+realDate;
		for(int i=0;i<usersCat.size();i++){
			Users user =usersCat.getUser(i);
			String[] info=user.getCheckedOut();
			String newInfo=null;
			if(info==null){
				continue;
			}
			for(int j=0;j<info.length;j++){
				String parts[]=info[j].split(":");
				if(parts[1].equals(magicNo)){
					System.out.println("Book not available, notified current loaner "+user.getName());
					newInfo=result+":"+parts[1]+":"+parts[2];
					info[j]=newInfo;
				}	
			}
			user.setCheckedOut(info);
		}
		//usersCat.print();
	}
	public void wait(String magicNo){
		if(cat.searchMagicNo(magicNo)==null){
			System.out.println("Book not found");
			return;
		}
		book[] temp=cat.searchMagicNo(magicNo);
		for(int i=0;i<temp.length;i++){
			if(temp[i].getIsOut()==false){
				System.out.println("Book Available");
				return;
			}
		}
		for(int i=0;i<temp.length;i++){
			if(temp[i].getNotifyUsers()!=null){
			int newlen=temp[i].getNotifyUsers().length+1;
			Users[] newNU= Users[newlen];
			for(int j=0;j<temp[i].getNotifyUsers().length;j++){
				newNU[j]=temp[i].getNotifyUsers()[j];
			}
			newNU[newlen-1]=currentUser;
			temp[i].setNotifyUsers(newNU);
			}
			else{
				Users[] newNU=new Users[1];
				newNU[0]=currentUser;
				temp[i].setNotifyUsers(newNU);
			}
		}
		System.out.println("You will be informed when the book is returned!");
	}
}
