package ProgAssignment4;

import java.util.ArrayList;

public class catalog {
	private ArrayList<book> log=new ArrayList<book>();
	public void addBook(book adding){
		log.add(adding);
	}
	public void print(){
		for(int i=0;i<log.size();i++){
			System.out.println(getBook(i));
		}
	}
	public book getBook(int i){
		return log.get(i);
	}
	public int size(){
		return log.size();
	}
	public book[] search(String keyword){
		ArrayList<book> temp=new ArrayList<book>();
		for(int i=0;i<log.size();i++){
			book tp=this.getBook(i);
			if(tp.match(keyword))
				temp.add(tp);
		}
		book[] result=new book[temp.size()];
		for(int i=0;i<result.length;i++){
			result[i]=temp.get(i);
		}
		return result;
	}	
	public book[] searchMagicNo(String MagicNo){
		ArrayList<book> temp=new ArrayList<book>();
		for(int i=0;i<log.size();i++){
			book tp=this.getBook(i);
			if(tp.matchMagicNo(MagicNo))
				temp.add(tp);
		}
		book[] result=new book[temp.size()];
		if(temp.size()==0){
			result=null;
		}
		else{
			for(int i=0;i<result.length;i++){
				result[i]=temp.get(i);
			}
		}
		return result;
	}
}
