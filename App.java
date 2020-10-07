package com.tut.webscraper;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;






public class App
{
	
	public static String getData(String c) throws Exception{
       final StringBuffer br=new StringBuffer();
       
       br.append("<html>" + "<body style = 'text-align:center;color:red;'>");
       
       br.append(c.toUpperCase()+"<br>");
       
       String url="https://www.worldometers.info/coronavirus/country/" + c + "/";		
	   Document doc=Jsoup.connect(url).get();
	   //maincounter-wrap
	
       Elements elements = doc.select("#maincounter-wrap");
       elements.forEach(new Consumer<Element>() {
		@Override
		public void accept(Element e) {
			   String text=e.select("h1").text();
			   String count=e.select(".maincounter-number>span").text();
			br.append(text).append(count).append("<br>");
		   }
	});
       br.append("</body>" + "</html>");
	return br.toString();
	}
    public static void main( String[] args ) throws Exception
    {
       // System.out.println( "Hello World!" );
       // Scanner sc=new Scanner(System.in);
       // System.out.println("Enter country: ");
       // String co=sc.nextLine();
       // System.out.println(getData(co));
    	JFrame root=new JFrame("Country wise details of corona cases");
    	root.setSize(500,500);
    	
    	Font f=new Font("Poppins",Font.BOLD,30);
    	
    	//textfield
    	final JTextField field=new JTextField();
    	//label
    	final JLabel dataL=new JLabel();
    	field.setFont(f);
    	dataL.setFont(f); 
    	
    	field.setHorizontalAlignment(SwingConstants.CENTER);
    	dataL.setHorizontalAlignment(SwingConstants.CENTER);
    	JButton button=new JButton("Get");
    	
    	button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//click
				try {
				 String maindata=field.getText();
				 String result=getData(maindata);
				 dataL.setText(result);
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}});
    	button.setFont(f);
    	
    	root.setLayout(new BorderLayout());
    	
    	root.add(field,BorderLayout.NORTH);
    	root.add(dataL,BorderLayout.CENTER);
    	root.add(button,BorderLayout.SOUTH);
    	
    	root.setVisible(true);
    	
    	
    	
    	
    }
}
