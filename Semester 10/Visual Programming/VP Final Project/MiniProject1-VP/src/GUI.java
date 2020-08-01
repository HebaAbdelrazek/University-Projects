import java.awt.GraphicsConfiguration;
import java.awt.TextArea;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

public class GUI {
	static String key="";
	static String string="";

	static String keyPressed;
	String Text="";
	String cond="";

	TextArea area;
	static JPanel panel;
	static int locationx;
	static int locationy;
	static rotateLabel sprite1;
	static JLabel cat;
	static JLabel If;
    static JTextArea jt;
    boolean ifexists=false;
    boolean whileexists=false;

    static float myAngle = 0;
	static Boolean Pressed=false;
	static MiniProject1VP project;
	static GraphicsConfiguration gc;
	int newx;
	int newy;
	int newx2;
	int newy2;
	int newrot;
	int dx;
	int dy;

	int newrot2=0;


	public GUI() {
		
		
		JFrame frame= new JFrame(gc);	
		frame.setTitle("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);		
		panel = new JPanel();
		JButton start = new JButton("t");
        start.setBounds(860, 69, 50, 50);
        start.setVisible(true);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
              
               
           
				try {
					
						  
						
					if(!Pressed) {
						boolean run=true;
						if(ifexists&&!whileexists) {
							run=false;
						    JOptionPane.showMessageDialog(null, "i not initialized. Please check your syntax", "" + "Syntax Error", JOptionPane.INFORMATION_MESSAGE);

						}
						
					if(ifexists) {
						String [] temp=area.getText().split("if");
						String []temp2= string.split("If i ... ");

						string="IFGREENFLAG "+temp2[0];
						for(int i=1; i<temp.length;i++) {
						if(temp[i].charAt(3)=='.' || temp[i].charAt(4)=='.'||temp[i].charAt(5)=='.') {
							run=false;
						    JOptionPane.showMessageDialog(null, "Please add the if condition", "" + "Error", JOptionPane.INFORMATION_MESSAGE);

							}
						if(!(temp[i].charAt(2)=='>' ||temp[i].charAt(2)=='<' || (temp[i].charAt(2)=='='&&temp[i].charAt(3)=='=' )) || !((Character.isDigit(temp[i].charAt(3)) ||(temp[i].charAt(2)=='='&&temp[i].charAt(3)=='=' )&&Character.isDigit(temp[i].charAt(4)) ))) {
						    JOptionPane.showMessageDialog(null, "Please enter correct format for the if condition", "" + "Error", JOptionPane.INFORMATION_MESSAGE);

						}
						else {
							if(Character.isDigit(temp[i].charAt(4))&&Character.isDigit(temp[i].charAt(5))){
								if(temp[i].charAt(2)=='='&&temp[i].charAt(3)=='=')
									cond="If i "+ temp[i].charAt(2)+temp[i].charAt(3)+" "+temp[i].charAt(4)+temp[i].charAt(5)+temp[i].charAt(6)+" ";
									else
									cond= "If i "+temp[i].charAt(2)+" "+temp[i].charAt(3)+temp[i].charAt(4)+temp[i].charAt(5)+" ";
							
							}
							else if(Character.isDigit(temp[i].charAt(4))){
								if(temp[i].charAt(2)=='='&&temp[i].charAt(3)=='=')
									cond="If i "+ temp[i].charAt(2)+temp[i].charAt(3)+" "+temp[i].charAt(4)+temp[i].charAt(5)+" ";
									else
									cond= "If i "+temp[i].charAt(2)+" "+temp[i].charAt(3)+temp[i].charAt(4)+" ";
							}
							else {
							if(temp[i].charAt(2)=='='&&temp[i].charAt(3)=='=')
							cond="If i "+ temp[i].charAt(2)+temp[i].charAt(3)+" "+temp[i].charAt(4)+" ";
							else
							cond= "If i "+temp[i].charAt(2)+" "+temp[i].charAt(3)+" ";
							}	
					}
						string+=cond+temp2[i];
						
						
					}}
					
					
					
					if(whileexists) {
						String [] temp=area.getText().split("while");
						String []temp2= string.split("While ... ");
                        if(!ifexists)
						string="IFGREENFLAG "+temp2[0];
                        else
                        	string=temp2[0];
						for(int i=1; i<temp.length;i++) {
						if(temp[i].charAt(6)=='.' || temp[i].charAt(4)=='.'||temp[i].charAt(5)=='.') {
							run=false;
						    JOptionPane.showMessageDialog(null, "Please add the while condition", "" + "Error", JOptionPane.INFORMATION_MESSAGE);

							}
						if(!(Character.isDigit(temp[i].charAt(4)))) {
						    JOptionPane.showMessageDialog(null, "Please enter correct format for the while condition", "" + "Error", JOptionPane.INFORMATION_MESSAGE);

						}
						else {
							int x=0;
							if(Character.isDigit(temp[i].charAt(6))&&Character.isDigit(temp[i].charAt(5))) {
							cond=temp[i].charAt(4) + temp[i].charAt(5) + temp[i].charAt(6) +"";
							}
							else if(Character.isDigit(temp[i].charAt(5)))
								cond="While "+temp[i].charAt(4) + temp[i].charAt(5) + "";
								
							else
								cond="While "+temp[i].charAt(4) +"";

								
					}
						string+=cond+" "+temp2[i];
						
						
					}}
						if(run) {
					System.out.println(string);
					project= new MiniProject1VP(0);
					if(!project.syntaxerror)
					project.run(project.cat_words);
					if(project.syntaxerror || (ifexists&&!whileexists)) {
				    JOptionPane.showMessageDialog(null, "Please check your syntax", "" + "Syntax Error", JOptionPane.INFORMATION_MESSAGE);
					}
					if(!project.syntaxerror) {
						System.out.println(dx+" "+locationx);
						System.out.println(dy+" "+locationy);

						if ((locationx>dx-500&&locationx<dx-350) && (locationy>dy-450&&locationy<dy-300))
					    JOptionPane.showMessageDialog(null, "YOU CAN NOW HAVE YOUR DONUT", "" + "CONGRATULATIONS!", JOptionPane.INFORMATION_MESSAGE);
						else
						    JOptionPane.showMessageDialog(null, "Try Again!", "" + "Hard Luck", JOptionPane.INFORMATION_MESSAGE);

						}
					    project.syntaxerror=false;

		            
		            }}
					
		          
		            Pressed=true;

				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				
			
            }
           });
        JLabel donut=new JLabel();
        JButton reset=new JButton("Reset");
        reset.setBounds(780, 80, 40, 40);
		ImageIcon r = new ImageIcon("reset.png");
		JLabel resetbutton=new JLabel();
		resetbutton.setBounds(760, 55, 100, 100);
		resetbutton.setIcon(r);
        reset.setVisible(true);
        resetbutton.setVisible(true);

	
    reset.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae){
             	Text="";
          	    area.setText(Text);
                area.repaint();
                Pressed=false;
		        area.setBounds(10,350, 300,400);
			    sprite1.setBounds(400, 230, 1000, 800);
			    string="";
		        Random rand = new Random(); 
                dx= rand.nextInt(400);
		        dx=800-dx;
		        dy=rand.nextInt(500)+200;
		        donut.setBounds(dx,dy,100,80);
		        ifexists=false;
		        whileexists=false;
		        if(project!=null)
                project.syntaxerror=false;

        	
        }});
       
       
      
        Random rand = new Random(); 


       dx= rand.nextInt(400);
        dx=800-dx;
        dy=rand.nextInt(500)+200;
        donut.setBounds(dx,dy,100,80);
		ImageIcon dt = new ImageIcon("donut.png");
		 donut.setIcon(dt);
       
        JLabel greenbutton=new JLabel();
		greenbutton.setBounds(850, 50, 100, 100);
		ImageIcon button = new ImageIcon("button.png");
        greenbutton.setIcon(button);
        greenbutton.setVisible(false);
        
	
		ImageIcon caticon = new ImageIcon("catt.png");
		
		sprite1=new rotateLabel(caticon, myAngle);
		sprite1.setVisible(true);

		
        
    	    start.setVisible(true);
      	greenbutton.setVisible(true);
    
      	If=new JLabel();
	
		
		JButton ifB = new JButton("if(i...) {");
		ifB.setBounds(50, 20, 100, 50);
        ifB.setVisible(true);
		ifB.setToolTipText("Add i conditions (Operator and number) in the text area after pressing");

        ifB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	area.setText(area.getText()+"if(i...){"+ "\r\n");
            	area.repaint();
            	ifexists=true;
                

            string+="If i ... begin ";
            	
            }});
              
        
        JButton elseB = new JButton("else {");
		elseB.setBounds(50, 70, 100, 50);
		elseB.setVisible(true);
		elseB.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae){
	            	area.setText(area.getText()+"else {"+ "\r\n");
	            	area.repaint();
	                string+="Else begin ";

	            }});
		
	    JButton whileB = new JButton("int i=0; \n while(i<...) { \n i++;");
		whileB.setToolTipText("Add number in the text area after pressing (maximum 3 digis)");
		whileB.setBounds(50, 230, 220, 100);
		whileB.setVisible(true);
		whileB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	area.setText(area.getText()+"int i=0;"+ "\r\n" + "while (i<...){" + "\r\n" + "i++;" + "\r\n");
            	area.repaint();
            	whileexists=true;
            	string+="While ... begin ";

            }});
		
		JButton xpB = new JButton("x+1;");
		xpB.setBounds(50, 120, 100, 50);
		xpB.setVisible(true);
		
		xpB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	area.setText(area.getText()+"x+1;"+ "\r\n");
            	area.repaint();
                string+="Move +50 ";

            }});
		
		JButton xmB = new JButton("x-1;");
		xmB.setBounds(50, 170, 100, 50);
		xmB.setVisible(true);
		
		xmB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	area.setText(area.getText()+"x-1;"+ "\r\n");
            	area.repaint();
                string+="Move -50 ";

            }});
		
		
		JButton ypB = new JButton("y+1;");
		ypB.setBounds(170, 120, 100, 50);
		ypB.setVisible(true);
		
		ypB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	area.setText(area.getText()+"y+1;"+ "\r\n");
            	area.repaint();
                string+="Movey +50 ";

            }});
		
		
		JButton ymB = new JButton("y-1;");
		ymB.setBounds(170, 170, 100, 50);
		ymB.setVisible(true);
		ymB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	area.setText(area.getText()+"y-1;"+ "\r\n");
            	area.repaint();
                string+="Movey -50 ";

            }});
		
		JButton op = new JButton("{") ;
		op.setBounds(170, 20, 100, 50);
		op.setVisible(true);
		
		op.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	area.setText(area.getText()+"{"+ "\r\n");
            	area.repaint();
                string+="begin";

            }});
		
		JButton cl = new JButton("}");
		cl.setBounds(170, 70, 100, 50);
		cl.setVisible(true);
		
		cl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	area.setText(area.getText()+"}"+ "\r\n");
            	area.repaint();
                string+="end ";

            }});
		


        
        
		
		
		sprite1.setBounds(400, 230, 1000, 800);


		





        

        panel.setLayout(null);
        panel.setSize(1000, 800);
        panel.add(ifB);
        panel.add(elseB);
        panel.add(xpB);
        panel.add(xmB);
        panel.add(ypB);
        panel.add(ymB);
        //panel.add(op);
        panel.add(cl);
        panel.add(whileB);
        panel.add(resetbutton);

        panel.add(reset);




        panel.add(donut);
        panel.add(greenbutton);
        panel.add(start);

        panel.add(sprite1);

        area=new TextArea(Text);  
        area.setBounds(10,350, 300,400); 
        
        frame.setFocusable(true);
        frame.add(area);
       

        frame.add(panel);

        frame.setSize(1000, 800);
		frame.setLocation(200, 200);
        frame.setVisible(true);
        
        
        

		
	}
	
	
	
	private class rotateLabel extends JLabel {
		public ImageIcon ikoon;
		public float angle;
		public rotateLabel() {
			super();
		}
		public rotateLabel(ImageIcon icon,float angle) {
			super(icon);
			ikoon = icon;
			this.angle = angle;
			
		}
		public void paintComponent(Graphics g) {
			Graphics2D gx = (Graphics2D) g;
			gx.rotate(angle*(Math.PI/180), getX() + getWidth()/2,getY() + getHeight()/2);
			super.paintComponent(g);
		}
		
		public void setAngle(float newAngle) {
			this.angle = newAngle;
		}
		
	}
	 
	 
	public static void main(String[] args){
		GUI vp=new GUI();
		
		
		

	}
 
 
	public static void catlocation() throws InterruptedException {
	
		locationx=project.getCatX();
	    locationy=project.getCatY();
	    if(!project.syntaxerror)
		sprite1.setBounds(locationx, locationy, 1000, 800);}
	 
	
	
	public static void rotateCat() {
		
		
		
		myAngle = project.getCatRotation();
		sprite1.setAngle(myAngle);
		sprite1.repaint();}
		
			
	}
	
	

