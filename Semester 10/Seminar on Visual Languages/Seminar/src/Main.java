import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.Color;

class MyDraggableComponent extends JPanel {

  private volatile int screenX = 0;
  private volatile int screenY = 0;
  private volatile int myX = 0;
  private volatile int myY = 0;
//  private JLabel label;

  public MyDraggableComponent() {
//    setBorder(new LineBorder(Color.BLUE, 3));
//    setBackground(Color.WHITE);
//    setBounds(0, 0, 400, 100);
//    setOpaque(false);
//	  
//
//      JLabel label = new JLabel("Go to school by");
//      label.setHorizontalAlignment(JLabel.LEFT);  
//      
//      this.add(label);
//
//      
//      String transportation[]={"car","bus","walking","bicycle"};        
//      JComboBox cb=new JComboBox(transportation);
//      this.add(cb);
//
//      
//      JLabel label2 = new JLabel("" + cb.getItemAt(cb.getSelectedIndex()));
//      this.add(label2);
      
    addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) { }

      @Override
      public void mousePressed(MouseEvent e) {
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        myX = getX();
        myY = getY();
      }

      @Override
      public void mouseReleased(MouseEvent e) { }

      @Override
      public void mouseEntered(MouseEvent e) { }

      @Override
      public void mouseExited(MouseEvent e) { }

    });
    addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(MouseEvent e) {
        int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;

        setLocation(myX + deltaX, myY + deltaY);
      }

      @Override
      public void mouseMoved(MouseEvent e) { }

    });
  }

}

public class Main {
	
	public static String lunchFood;
	public static String transportationMean;
	public static String trashMethod;
	public static String strawType;
	
	public static int wildLifeCount = 100;
	public static int airPollutionCount = 0;
	public static int resourcesCount = 100;
	public static int sustainabilityCount = 100;
	
	

  public static void main(String[] args) {
    JFrame f = new JFrame("Earthling");
    
    ImageIcon imageIcon = new ImageIcon("/Users/heba/Downloads/earth3.png");
    JLabel image = new JLabel(imageIcon);
    image.setBounds(950, 20, 200, 200);
    f.add(image);

    JLabel title = new JLabel("HELLO EARTHLING!");
    title.setHorizontalAlignment(JLabel.CENTER);  
    title.setSize(1200,60);  
    title.setFont (title.getFont().deriveFont(20.0f));
    title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | Font.BOLD));
    f.add(title);
    
    JLabel blocksTitle = new JLabel("   DRAG A BLOCK TO THE CENTER:");
    blocksTitle.setHorizontalAlignment(JLabel.LEFT);  
    blocksTitle.setSize(1200,300); 
    blocksTitle.setFont (blocksTitle.getFont().deriveFont(16.0f));
    blocksTitle.setFont(blocksTitle.getFont().deriveFont(blocksTitle.getFont().getStyle() | Font.BOLD));
    f.add(blocksTitle);
    
    JPanel centerArea = new JPanel();
    centerArea.setBorder(new LineBorder(Color.BLACK, 4));
    centerArea.setBackground(Color.WHITE);
    centerArea.setBounds(300, 100, 600, 500);
    centerArea.setOpaque(false);
    f.add(centerArea);
    
    JPanel statusArea = new JPanel();
    statusArea.setBorder(new LineBorder(Color.GREEN, 4));
    statusArea.setBackground(Color.WHITE);
    statusArea.setBounds(925, 300, 250, 150);
    statusArea.setOpaque(false);
    
    JLabel earthStatus = new JLabel("Earth Status");
    earthStatus.setFont(earthStatus.getFont().deriveFont(earthStatus.getFont().getStyle() | Font.BOLD));
    earthStatus.setFont (earthStatus.getFont().deriveFont(20.0f));
    statusArea.add(earthStatus);
    
    
    JLabel wildLife = new JLabel("WildLife is at " + wildLifeCount + " %");
    wildLife.setFont (wildLife.getFont().deriveFont(16.0f));
    statusArea.add(wildLife);
    
    JLabel airPollution = new JLabel("Air Pollution is at " + airPollutionCount + " %");
    airPollution.setFont (airPollution.getFont().deriveFont(16.0f));
    statusArea.add(airPollution);
    
    JLabel resources = new JLabel("Resources is at " + resourcesCount + " %"); //straws
    resources.setFont (resources.getFont().deriveFont(16.0f));
    statusArea.add(resources);
    
    JLabel sustainability = new JLabel("Sustainability is at " + sustainabilityCount + " %"); //straws
    sustainability.setFont (sustainability.getFont().deriveFont(16.0f));
    statusArea.add(sustainability);
    
   
    
    f.add(statusArea);
    
    
    f.setLayout(null); // by doing this, we prevent Swing from resizing our nice component

    MyDraggableComponent mc1 = new MyDraggableComponent();
    mc1.setBorder(new LineBorder(Color.BLUE, 3));
    mc1.setBackground(Color.WHITE);
    mc1.setBounds(10, 200, 280, 50);
    mc1.setOpaque(false);
	
    JLabel label = new JLabel("Go to school by");
    label.setHorizontalAlignment(JLabel.LEFT);  
    mc1.add(label);
    String transportation[]={"car","bus","bicycle"};        
    JComboBox cb=new JComboBox(transportation);
    mc1.add(cb);

    f.add(mc1);
    
    MyDraggableComponent mc2 = new MyDraggableComponent();
    mc2.setBorder(new LineBorder(Color.RED, 3));
    mc2.setBackground(Color.WHITE);
    mc2.setBounds(10, 300, 280, 50);
    mc2.setOpaque(false);
	
    JLabel label2 = new JLabel("For lunch, I will have");
    label2.setHorizontalAlignment(JLabel.LEFT);  
    mc2.add(label2);
    String lunch[]={"a burger","chicken","vegtables"};        
    JComboBox cb2=new JComboBox(lunch);
    mc2.add(cb2);
    
    f.add(mc2);
    
    MyDraggableComponent mc3 = new MyDraggableComponent();
    mc3.setBorder(new LineBorder(Color.MAGENTA, 3));
    mc3.setBackground(Color.WHITE);
    mc3.setBounds(10, 400, 280, 50);
    mc3.setOpaque(false);
	
    JLabel label3 = new JLabel("I throw my trash"); //resources
    label3.setHorizontalAlignment(JLabel.LEFT);  
    mc3.add(label3);
    String trash[]={"in garbage cans","in recycle bins"};        
    JComboBox cb3=new JComboBox(trash);
    mc3.add(cb3);
    
    f.add(mc3);
 
    MyDraggableComponent mc4 = new MyDraggableComponent();
    mc4.setBorder(new LineBorder(Color.ORANGE, 3));
    mc4.setBackground(Color.WHITE);
    mc4.setBounds(10, 500, 280, 50);
    mc4.setOpaque(false);
	
    JLabel label4 = new JLabel("I use straws made of"); //sustainability
    label4.setHorizontalAlignment(JLabel.LEFT);  
    mc4.add(label4);
    String straws[]={"plastic","paper","steel"};        
    JComboBox cb4=new JComboBox(straws);
    mc4.add(cb4);
    
    f.add(mc4);
    
    
    JButton runButton=new JButton("Click to see the effects of your choices");  
    runButton.setBounds(350,650,500,50);  
    runButton.setFont(new Font("Arial",Font.BOLD,20));
    runButton.setBackground(Color.CYAN);
    runButton.setForeground(Color.BLACK);
    runButton.setFocusPainted(false);
    runButton.setOpaque(true);
    runButton.setBorderPainted(false);    
    f.add(runButton);
  
    runButton.addActionListener(new ActionListener() {  
        public void actionPerformed(ActionEvent e) {  
        	
        	if(mc1.getBounds().x > 300 && mc1.getBounds().x < 800 
        			&& mc1.getBounds().y > 100 && mc1.getBounds().y < 700) {
        		transportationMean = "" + cb.getItemAt(cb.getSelectedIndex());  
        		System.out.println(transportationMean);
        		
        		switch(transportationMean) {
	        		case "car": airPollution.setText("Air Pollution is at " + 50 +  " %");
	        					airPollutionCount = 50 ;break;  
	        		case "bus": airPollution.setText("Air Pollution is at " + 40 +  " %");
								airPollutionCount = 40 ;break;
	        		case "bicycle": airPollution.setText("Air Pollution is at " + 0 +  " %");
        						airPollutionCount = 0 ;break;
        		}
        	}
        	
        	if(mc2.getBounds().x > 300 && mc2.getBounds().x < 800
        			&& mc2.getBounds().y > 100 && mc2.getBounds().y < 700) {
        	lunchFood = "" + cb2.getItemAt(cb2.getSelectedIndex());  
            System.out.println(lunchFood);
            
            switch(lunchFood) {
	    		case "a burger": wildLife.setText("Wildlife is at " + 30 +  " %");
	    						 wildLifeCount = 30 ;break;
	    		case "chicken": wildLife.setText("Wildlife is at " + 50 +  " %");
	    						wildLifeCount = 50 ;break;
	    		case "vegtables": wildLife.setText("Wildlife is at " + 100 +  " %");
	    						  wildLifeCount = 100 ;break;
    			} 
        	}
        	
        	if(mc3.getBounds().x > 300 && mc3.getBounds().x < 800
        			&& mc3.getBounds().y > 100 && mc3.getBounds().y < 700) {
        	trashMethod = "" + cb3.getItemAt(cb3.getSelectedIndex());  
            System.out.println(trashMethod);
            
            switch(trashMethod) {
	    		case "in recycle bins": resources.setText("Resources is at " + 100 +  " %");
	    						 resourcesCount = 100 ;break;
	    		case "in garbage cans": resources.setText("Resources is at " + 50 +  " %");
	    						 resourcesCount = 50 ;break;
    			} 
        	}
        	
        	if(mc4.getBounds().x > 300 && mc4.getBounds().x < 800
        			&& mc4.getBounds().y > 100 && mc4.getBounds().y < 700) {
        	strawType = "" + cb4.getItemAt(cb4.getSelectedIndex());  
            System.out.println(strawType);
            
            switch(strawType) {
	    		case "plastic": sustainability.setText("Sustainability is at " + 0 +  " %");
	    								sustainabilityCount = 0 ;break;
	    		case "paper": sustainability.setText("Sustainability is at " + 50 +  " %");
	    								sustainabilityCount = 50 ;break;
	    		case "steel": sustainability.setText("Sustainability is at " + 100 +  " %");
	    								sustainabilityCount = 100 ;break;
    			} 
        	}
        	
        	
        	
        }   
    });
    
    
    JButton resetStatusButton=new JButton("Reset Status");  
    resetStatusButton.setBounds(950,550,200,50);  
    resetStatusButton.setFont(new Font("Arial",Font.BOLD,20));
    resetStatusButton.setBackground(Color.CYAN);
    resetStatusButton.setForeground(Color.BLACK);
    resetStatusButton.setFocusPainted(false);
    resetStatusButton.setOpaque(true);
    resetStatusButton.setBorderPainted(false);    
    f.add(resetStatusButton);
    
    resetStatusButton.addActionListener(new ActionListener() {  
        public void actionPerformed(ActionEvent e) {  
        	
        	wildLife.setText("WildLife is at 100 %");
        	airPollution.setText("Air Pollution is at 0 %");
        	resources.setText("Resources is at 100 %");
        	sustainability.setText("Sustainability is at 100 %");
        	wildLifeCount = 100;
        	airPollutionCount = 0;
        	resourcesCount = 100;
        	sustainabilityCount = 100;
     
        }   
    });
    
    
    
    f.setSize(1200,800);
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    f.setVisible(true);
     
  }

}