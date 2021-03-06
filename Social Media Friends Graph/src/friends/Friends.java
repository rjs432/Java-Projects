package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null or empty array list if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		if (p1 == null || p2 == null) { //if either of them is null then there is no path
			return null;
		}
	

		ArrayList<String> sChain = new ArrayList<String>();
		int f = g.members.length; //the legth of members that will serve as he length for the BFS elements
		int ind = g.map.get(p1);//its index in the map
		if(sameorno(p1, p2) == true) { //if they are equal then add the string
			return null;
		}
		
		else {
			
			boolean[] ved = new boolean[f];  
				for (int t = 0; t < f; t++) {//boolean array- initalized all to false 
					ved[t] = false;
				}//ends for
			
			Person[] vA = new Person[f];
				for (int t = 0; t < f; t++) {//Person array- initalized all to null
					vA[t] = null;
				}//ends for
				
			Queue<Person> que = new Queue<Person>(); //Queue for BFS
			
			
			Person h = null;
			int s = g.members.length;
			
				for (int i = 0; i < s; i++) { //gets the member at the index in members
					if (i == ind) {
						h = g.members[i];
					}
				} //ends for
				
			ved [ind] = true;	//true bc we saw the first one 
			sChain.add(h.name); //adds the first one to the list 
			que.enqueue(h); //enqs the p1
			Person point = null;
	while (que.size() != 0) {//BFS//////////
			point =  que.dequeue();//dequeues first thing 
			int inde = g.map.get(point.name); //sets the int value location in the map - 
			ved[inde] = true; //says we saw that index
			Friend nextp = point.first;
			boolean hg = nChec(null, nextp, null, "f");
				//if (hg = false) {
				while (hg != true) {
					 hg = nChec(null, nextp, null, "f");
					if (hg == true) { //use helper
						break;
						}
					boolean tt = true;
					int yy = nextp.fnum;
					String jj = g.members[yy].name;
					for (int hh = 0; hh < ved.length; hh ++) {
						if (ved[yy] == true) {
							tt = false;
						}
						else if (ved[yy] == false) {
							tt = true;
						}
					}
					if (tt == true) { 
						ved[yy] = true;
						vA[yy] = point; 
						que.enqueue(g.members[yy]);	
						}
					
					if (jj.equals(p2)) {
						point = g.members[yy];
						while (point.name.equals(p1) == false) {
							boolean flag = true;
							for (int b = 0; b < sChain.size(); b++) {
								
								String t = sChain.get(b);
								if (t == point.name) {
									flag = true;
								}
								else {
									flag = false;
								}
							}
							if (flag == false) {
								sChain.add(point.name);
							}
						
							int o = g.members.length;
							for (int lk = 0; lk < o; lk++) {
								if (g.members[lk].name == point.name) {
									point = vA[lk];
									break;
								}
							}
						}
						ArrayList<String> gg = Rev(sChain);//call helper to rearrange output
						return gg;
					}
					
					
					 hg = nChec(null, nextp, null, "f");
						if (hg == true) { //use helper
							break;
							}
						nextp = nextp.next;
					}

					

				}//ends 1st while
		}
	return null;		
}//ends method
	
	private static ArrayList<String> Rev (ArrayList<String>sChain){ //reverses the output to correct order
		ArrayList<String> updated = new ArrayList<String>();
		
		if (sChain.size() > 0 ) {
		
			String d = sChain.remove(0);
		
		
		for (int y = sChain.size() ; y > 0; y-- ) {
			updated.add(sChain.get(y-1));
		}
		updated.add(0,d);
		}
		else {
			updated = null;
		}
		return updated;
		
	}
	
	private static boolean sameorno (String p1, String p2) { //sees if the p1 and p2 are the same
			boolean f = false;
			if (p1.length() < p2.length()){
				f = false;
			}
			else if (p1.length() > p2.length()) {
				f = false;
			}
			else {
				for (int i = 0; i < p1.length();i++ ) {
				char s = p1.charAt(i);
				char y = p2.charAt(i);
					if (s == y) {
						f = true;
					}
					else if (s != y) {
						f = false;
						break;
					}
				}
			}
			return f;
		}
	


	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null or empty array list if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		
			ArrayList<ArrayList<String>> mainList = new ArrayList<ArrayList<String>>();
			
			int pp = g.members.length;
			boolean[] ved = new boolean[pp];
			for (int t = 0; t < pp; t++) {//boolean array- initalized all to false 
				ved[t] = false;
			}
			for(int v= 0; v < pp;v++){
				ArrayList<String> addList = new ArrayList<String>();
				Person person = g.members[v];
				boolean flag = true;
				String j = g.members[v].school;
				for (int f = 0; f < pp; f ++) {
					String a = g.members[f].school;
					if (a!= null) {
						if (a.equals(j)){
							flag = true;
							break;
							}
						}
					else if (a == null) {
							flag = false;
						}
				}
				
				if(flag == true){
					boolean sameSchool = false;
					 if(person.school.equals(school)){
						 sameSchool = true;
					 }
					 
					if(sameSchool == true){
						if (ved[v]==false){
							addList = dfspt1(person, school, addList, ved,v, g);
						}
					}
				}
				int count = 0;
				for (int c = 0; c < addList.size(); c++) {
					count ++;
				}
			
				if (count > 0) {
					mainList.add(addList);
				}
			
			}
			if (mainList.size() == 0) {
				return null;
			}
			else {
			return mainList;
			}
		
	}
	
	
	private static boolean nChec (Person p, Friend f, String y, String h) {
		String tt = h;
		boolean flag = false;
		if (tt.equals("f")) {
			if (f == null) { //f
				flag = true;
			}
			else {
				flag = false;
			}
		}
		
		else if (tt.equals("p")) {
		if (p == null) { //p
			flag = true;
		}
		else {
			flag = false;
		
		}
		}
		else if ((!tt.equals("f")) && (!tt.equals("p"))){
			if (y == null) { //y
				flag = true;
			}
			else {
				flag = false;
			}
		}
		
		return flag;
			
	}
	
	
	
	private static ArrayList<String> dfspt1 ( Person person, String college, ArrayList<String> addList,boolean[] ved, int ind, Graph g){ //simple DFS 
		boolean yu = nChec(person, null, null, "p");
		if (yu == true) {
			return null;
		}
		else{
			
		Person f = g.members[ind];
		addList.add(f.name);
		ved[ind]=true;
		Friend curr = f.first;
		boolean d = nChec(null,curr,null,"f");
	

			while(d == false){
			d = nChec(null,curr,null,"f");
			if(d == true) {
				break;
			}
			Person currp = g.members[curr.fnum];
			
			String op = currp.school;
			boolean q = nChec(null,null,op,"op");
			
			if(q == true){
				curr = curr.next;
				continue;
			}
			else if (q == false) {
				
				if(ved[curr.fnum] == false ) {
						if (op.equals(college)){
					int yy= curr.fnum;
					addList = dfspt1(person,college, addList, ved, yy, g);
						}
				}
			
			curr = curr.next;
		

		}
			}
		return addList;
	}
	}
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null or empty array list if there are no connectors.
	 */
	
	

	public static ArrayList<String> connectors(Graph g) {
		int len = g.members.length;
		ArrayList<String> ro = new ArrayList<String>(len); //d
		ArrayList<String> peoplelist = new ArrayList<String>(len);//d
		int c = 0;
		boolean[] goneto = new boolean[len];
			for (int y = 0; y < len; y++) {
				goneto[y]= false;
			}
		int co = 0;
		int[] df = new int[len];
		ArrayList<Integer> holder = new ArrayList<Integer>(len);
		int[] done = new int[len];
		int l = 0;
			while (l < len){
				if (goneto[l] == false) {
					int p = done.length; //back length
					int qa = df.length; //dfs len
					int po = ro.size(); //backwards len
					Person f = g.members[l];
					String y = f.name;
					peoplelist = dfspttwo(peoplelist, true, g, f,y, holder, goneto, c, co, df, qa, done, p,  ro, po);
				}
					l++;
			}
		
		if (peoplelist.size()== 0) {
			return null;
		}
		return peoplelist;
	}
	
	private static ArrayList<String> dfspttwo(ArrayList<String> peoplelist, boolean started, Graph g, Person start, String name, ArrayList<Integer> holder, boolean[] goneto, int c, int co, int[] df, int qa, int[] done, int p, ArrayList<String> names, int po){
		int pers = g.map.get(name);
		goneto[pers] = true;
		df[pers] = c;
		Friend nextt = start.first;
		boolean d = nChec(null,nextt,null,"f");
		done[pers] = co;
		
	
		while(d == false){
			d = nChec(null,nextt,null,"f");
			if(d == true) {
				break;
			}
			int k = nextt.fnum;
		if (goneto[k] == true) {

				 qa = df.length;
				int we = 0;
				int yu = 0;
				int cu = 0;
				int wecount = 0;
				int ycount = 0;
				for (int y = 0; y < qa; y++) {
					if (y == pers) {
						 we = done[y];
						 wecount++;
					}
					else if (y == k) {
						 yu = df[y];
						 ycount++;
					}
					else {
						 cu = y;
						 holder.add(cu);
					}
					if (wecount > 0 && ycount > 0) {
						 if (we < yu) {
							done[pers] = we;
						}
							else  {
							done[pers] = yu;
						}
						
						break;
					}
				}
			}
			
			
			
			
			
			
			if (goneto[k] == false) {
				boolean joe = false;
				Person star = g.members[k];
				String nam = g.members[k].name;
				peoplelist = dfspttwo(peoplelist, joe, g, star ,nam, holder, goneto, c + 1, co + 1, df, qa, done, p, names, po);
				


				 if (done[k] <= df[pers]) {
					 p = done.length;
					int w = 0;
					int yo = 0;
					int cu = 0;
					int wcount = 0;
					int yocount = 0;
					
					for (int y = 0; y < p; y++) {
						if (y == pers) {
							 w = done[y];
							 wcount++;
							 
						}
						else if (y == k) {
							 yo = done[y];
							 yocount++;
						}
						else {
							 cu = y;
							 holder.add(cu);
						}
						if (wcount > 0 && yocount > 0) {
							 if (w < yo) {
								done[pers] = w;
							}
							 else {
									done[pers] = yo;
								}
								
							break;
						}
					}
					
				}
				
				
				 if (df[pers] <= done[k]) {
					  po = names.size();
					 int count1 = 0;
					 int count2 = 0;
					 int lo = peoplelist.size();
					 int e = 0;
					 int as = 0;
					 String rob = "";
					for (int i = 0; i < lo; i++ ) {
						rob = peoplelist.get(i);
						if(rob == name) {
							e = i;
							count1++;
							break;
						}
					}
					for (int t = 0; t < po; t++) {
						rob = names.get(t);
						if(rob == name) {
							as = t;
							count2++;
							break;
						}
					}
					if (count1 > 0 || count2 > 0) {
						if (count1 <= 0 && count2 > 0){
							peoplelist.add(lo, name);
						}
					}
					else if (count1 <= 0 && started == false) {
						peoplelist.add(lo, name);
					}
					else if (count1 > 0 && count2 > 0) {
						continue;
					}
				}
				
			int ptr = 0;
			if (po == 0) {
				names.add( name);
			}
			else {
				while(ptr != po) {
					ptr ++;
				}
				names.add(ptr, name);
				}
			}
			
			d = nChec(null,nextt,null,"f");
			if(d == true) {
				break;
			}
			nextt = nextt.next; //pushes to next
		}
		return peoplelist; //returns list
	}
}

