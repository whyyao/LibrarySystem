package ProgAssignment4;

import java.util.ArrayList;

public class usersCat {
	private static ArrayList<Users> log=new ArrayList<Users>();
	public static void addUsers(Users adding){
		log.add(adding);
	}
	public static void print(){
		for(int i=0;i<log.size();i++){
			System.out.println(getUser(i));
		}
	}
	public static Users getUser(int i){
		return log.get(i);
	}
	public static int size(){
		return log.size();
	}
	
	public static Users searchUsers(String ID){
		for(int i=0;i<log.size();i++){
			if (log.get(i).userMatch(ID)){
				return log.get(i);
			}
		}
		return null;
	}
}
