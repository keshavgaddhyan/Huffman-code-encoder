// I have used java for my assignment (JavaSE-1.8)
// the text editor i have used is eclipse
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;




public class hmencoder {
	static ArrayList<String> fnl=new ArrayList<String>();
	 static int numletters=0;
	public static void main(String args[]) throws IOException
	{
		
		ArrayList<Integer> frequency= new ArrayList<Integer>();
		ArrayList<Character> letters= new ArrayList<Character>();
		
		int count;
		FileInputStream f = new FileInputStream(args[0]);
	      char current;
	      
	      while (f.available() > 0) {
	        current = (char) f.read();
	        count=0;
	        numletters++;
	       
	        FileInputStream fnew = new FileInputStream(args[0]);
	        while(fnew.available()>0)
	        {
	        	 
	        	char cur=(char)fnew.read();
	        	
	        	
	        	if(cur==current)
	        	{
	        		count++;
	  
	        	}
	        }
	        if(!letters.contains(current))
	        {
	        	letters.add(current);
	        	frequency.add(count);
	        }
	        
	      }
	int length=letters.size();
	      PriorityQueue<node> tree = new PriorityQueue<node>(length, new Mycomparator());
	      for(int i=0;i<length;i++)
	      {
	    	  node hn = new node(); 
	    	  
	            hn.letter = letters.get(i); 
	            hn.freq = frequency.get(i); 
	            hn.left = null; 
	            hn.right = null; 
	            tree.add(hn); 
	      }
	    
	      node root=null;
	      while(tree.size()>1)
	      {
	            node x = tree.peek(); 
	            tree.poll(); 
	            node y = tree.peek(); 
	            tree.poll(); 
	            
	            
	            
	  // write the code for ambiguous case scenario when 3 or more elements have the same frequency
	             
	            node temp = new node(); 
	            temp.freq = x.freq + y.freq; 
	            
	            
	            if((int)x.letter>(int)y.letter)
	            {
	            	temp.letter = y.letter; 
	            	temp.left=y;
	            	temp.right=x;
	            }
	            else
	            {
	            	temp.letter = x.letter; 
	            	temp.left=x;
	            	temp.right=y;
	            }
	            
	 
	  
	          
	            root = temp; 
	  
	
	            tree.add(temp); 
	      }
	  
	      printCode(root,"");
	      Collections.sort(fnl);

	      

	      double avg;
	      String codedtxt="";
	      double bitlength=0;
	      
	      

	      FileInputStream fnnew = new FileInputStream(args[0]);
	        while(fnnew.available()>0)
	        {
	        	 
	      char cur=(char)fnnew.read(); 
	    	  
	      for(int i=0;i<fnl.size();i++)
	      {
           if(cur==fnl.get(i).charAt(0))
           {
        	   codedtxt=codedtxt+fnl.get(i).substring(3);

           }
	      }
	      }
	        
		      for(int i=0;i<fnl.size();i++)
		      {
		    	  
		    	  bitlength=bitlength+(fnl.get(i).substring(3)).length();
		    	  if(fnl.get(i).charAt(0)==' ')
		    	  {
		    		  fnl.set(i, fnl.get(i).replaceFirst(" ", "Space"));
		    	  }
		      }
		     bitlength=codedtxt.length();
		     avg= bitlength/numletters;
		      avg = (double)Math.round(avg*100)/100;
		   
		     
		      File file=new File("code.txt");
		      FileWriter fr=new FileWriter(file);
		      for(int i=0;i<fnl.size();i++)
		      {
		    	  fr.write(fnl.get(i)+"\n");
		    	  
		      }
		      fr.append("Ave = "+avg+" bits per symbol");
		      fr.close();
		      
		      File fle=new File("encodemsg.txt");
		      FileWriter fre=new FileWriter(fle);
		      for(int i=0;i<bitlength;i++)
		      {
		    	  char ch=codedtxt.charAt(i);
		    	  if(i%80==0 && i!=0)
		    	  {
		    		  fre.append("\n");
		    	  }
		    	  fre.write(ch);
		      }
		      fre.close();
		      

		      
		      

	}
	
	
	
	 public static void printCode(node root, String s) 
	    { 
	  
	        
	        if (root.left == null && root.right== null ) { 
	  
                 
	            fnl.add(root.letter + ": " + s); 
	            return; 
	        } 
	  
	         
	        printCode(root.left, s + "0"); 
	        printCode(root.right, s + "1"); 
	    } 
}
 

	 


class node {

		char letter;
		int freq;
		node left;
		node right;
	} 



 class Mycomparator implements Comparator<node>
{ 
    public int compare(node x, node y) 
    { 
  
        if( x.freq!=y.freq)
        {
        	return x.freq-y.freq;
        }
        else
        {
        	return (int)x.letter-(int)y.letter;
        }
        
    } 
} 


