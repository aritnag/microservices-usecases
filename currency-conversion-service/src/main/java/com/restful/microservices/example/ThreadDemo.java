package com.restful.microservices.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadDemo implements Comparable {

	int id;
	ThreadDemo(int id){
		this.id=id;
	}
	public static void main(String []args){
		List l =new ArrayList();
		ThreadDemo d= new ThreadDemo(4);
		ThreadDemo d1= new ThreadDemo(3);
		l.add(d);
		l.add(d1);
		Collections.sort(l);
		for(int i=0;i<l.size();i++){
			System.out.println(((ThreadDemo)l.get(i)).id);
		}
	}
	@Override
	public int compareTo(Object arg0) {
		int ret=0;
		if(this.id==((ThreadDemo)arg0).id)
			ret=0;
		if(this.id<=((ThreadDemo)arg0).id)
			ret=-1;
		if(this.id>=((ThreadDemo)arg0).id)
			ret=+1;
		
		return ret;
		
	}
	
}
