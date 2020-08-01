
import java.io.IOException;
import java.util.ArrayList;


public class MiniProject1VP{
	
    int catX;
    int catY;
    int counter=0;
    boolean first=false;
    boolean syntaxerror=false;
    boolean ifinwhile=false;

	static int Ifcounter=0;
	static int Ifnum=0;
	int count=0;
	static int whilecounter=0;
	String lastend="";

	static int elsecounter=0;
	static int ifs=0;
	int nbegins=0;
	int nends=0;
	int whiles=0;
	int Ifs=0;

	boolean ifbool=false;
	boolean ifendbool=false;

	int catRotation;
	static ArrayList<Integer>inds = new ArrayList<>();
	static ArrayList<Integer>ends = new ArrayList<>();
    static String[] cat_words;
    static ArrayList<String> order=new ArrayList();
    int label;
	static String keyPressed;
	
	
	public MiniProject1VP(int l) {
		this.label=l;		
		catX = 400;
		catY = 230;
		catRotation = 0;
		
		
	    String s=GUI.string;   
	    cat_words=s.split(" ");
	    for(int i=0; i<cat_words.length;i++)  {
         if(cat_words[i].equals("begin"))
           	nbegins+=1;
         if(cat_words[i].equals("end"))
	        nends+=1; 
         if(cat_words[i].equals("If"))
        	 Ifs+=Integer.parseInt(cat_words[i+3]);
         
         if(cat_words[i].equals("While"))

        	 whiles=Integer.parseInt(cat_words[i+1]);
                  }
	     if(nbegins!=nends)
	     	syntaxerror=true;
	    }

	public void run(String [] words) throws NumberFormatException, InterruptedException{
		
		int end=0;
		int startindex=0;
		int endindex;
		int num=0;
	    int whilesint=0;
		for(int i = 0; i < words.length ; i++) {
		if(Ifcounter==1) {
		if(words[i].equals("end")) {
			ifendbool=true;
			if(order.size()>0) {
				
				if(ifinwhile) {
			if(!first & count==whiles) {
					first=true;
					
					for(int k=0; k<whiles; k++) {
                     if(order.get(order.size()-1).equals("if"))
                     ifendbool=true;
					order.remove(order.size()-1);
			        lastend=order.get(order.size()-1);


}}}
				else {	
                    if(order.get(order.size()-1).equals("if"))
                    ifendbool=true;

					lastend=order.get(order.size()-1);
				}
					

			
			}
			Ifnum-=1;
			if(Ifnum==1)
				Ifnum=0;}}
		
		if(Ifcounter==1 && Ifnum==0)	{
			Ifcounter=0;
			}
		if((elsecounter==1 && words[i].equals("Else"))) {
		
		   if(0<i-1 && words[i-1].equals("end")) {
		   if((lastend.equals("if"))) {
		   Ifcounter=1;
		   }
		   else if(lastend.length()>0) {
			   
		   syntaxerror=true;
			System.out.println("syntax error2");}}

		   elsecounter=0;
		   
		}
		if(words[i].equals("Else")&&(!ifbool||!ifendbool)) {
			syntaxerror=true;
			System.out.println("syntax error1");
			}
		
		//if(Ifcounter==1 && words[i].equals("Else"))
		//	syntaxerror=true;
		
		if(Ifcounter==0 && !syntaxerror) {

           
		   if(words[i].equals("While")) {
			   order.add("while");
			  
			   
			 
			   whilecounter+=1;
			   num=Integer.parseInt(words[i+1]);
			   startindex=i+2;
			   
			   inds.add(startindex);
			   for(int j=i+2; j<words.length; j++) {
				   if(words[j].equals("If")) {
					   ifs+=1;
					   ifinwhile=true;}
				   if(words[j].equals("begin")) {
					   
					   whilesint+=1;
					   inds.add(j);
				}
				   if(words[j].equals("end")) {
					   ifendbool=true;
					   whilesint-=1;
					  
					   if(ifs>0)
						   ifs--;
					   else
						   ends.add(j);
				   
			   if(whilesint==0) {
				   end=j;
				
				   break;
				   
			   }}}
			   
			
				
			  int s=0;
			  int r=0;
			  String [] temp=new String[end-startindex + 1];
			  for(int k=startindex; k<=end; k++) {
				  
				 temp[r]=words[k];
				 r++;
			  }
			  while(s<num) {
				  
			      s++;
			      count++;
			      counter=s;
			      run(temp);
			      }
			      i=end;}
			   
				  
			   
		  
		   
		
	
			if(words[i].equals("If")) {
				order.add("if");
				ifbool=true;

				
				String condition1 = words[i+1];
				String condition2 = "";
				String condition3 = "";
				Ifcounter=1;
				Ifnum+=1;
				if(condition1.equals("i"))  {
					condition2 = words[i+2];
					condition3 = words[i+3];
					
					if(condition2.equals(">") && counter > Integer.parseInt(condition3))
						Ifcounter=0;
					else if (condition2.equals("==") && counter == Integer.parseInt(condition3))
						Ifcounter=0;
					else if(condition2.equals("<") && counter < Integer.parseInt(condition3))
						Ifcounter=0;			
				 
				}
				
				

				
				
				if(Ifcounter==0) {
				elsecounter=1;}
				
			}
			if(words[i].equals("Move")) {
				if(words[i+1].charAt(0)=='+') {
				moveCat(Integer.parseInt((words[i+1]).substring(1)));
				}
				else if(words[i+1].charAt(0)=='-') {
					moveCat(Integer.parseInt((words[i+1]).substring(1))*-1);
			
				}
			}	
			if(words[i].equals("Movey")) {
				if(words[i+1].charAt(0)=='+') {
				moveCaty(Integer.parseInt((words[i+1]).substring(1)));
				}
				else if(words[i+1].charAt(0)=='-') {
					moveCaty(Integer.parseInt((words[i+1]).substring(1))*-1);
				}
			}	
			
			
			
			
			
		}
		}
		
	
		return;
	}
	
	
	public void moveCat(int n) throws InterruptedException {
		catX += n*Math.cos(catRotation*(Math.PI/180));
		catY += n*Math.sin(catRotation*(Math.PI/180));
	    GUI.catlocation();
	}
	public void moveCaty(int n) throws InterruptedException {
		catY += n*Math.cos(catRotation*(Math.PI/180));
		catX += n*Math.sin(catRotation*(Math.PI/180));
	    GUI.catlocation();
	}
	
	
	public int getCatX() {
		return catX;
	}

	public void setCatX(int catX) throws InterruptedException {
		this.catX = catX;
		GUI.catlocation();
	}

	public int getCatY() {
		return catY;
	}

	public void setCatY(int catY) throws InterruptedException {
		this.catY = catY;
		GUI.catlocation();
	}

	public int getCatRotation() {
		return catRotation;
	}

	public void setCatRotation(int catRotation) {
		this.catRotation = catRotation;
	}
	
	
	
public static void main(String[] args) throws IOException {
	
		
}



	
}



