package lantern;
/*
*  Copyright (C) 2010 Michael Ronald Adams.
*  All rights reserved.
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
*
*  This code is distributed in the hope that it will
*  be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
*  General Public License for more details.
*/

import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.GroupLayout.*;
import java.awt.geom.*;


class CustomizeExcludedBoardsDialog extends JDialog
{
resourceClass graphics;
boolean [] excludedBoards;
JTextField field;
JImagePanel myImagePanel=new JImagePanel();
JButton okButton;
JButton cancelButton;
resourceClass dummyUse = new resourceClass();
JCheckBox [] myBoards;
int Boards = 1;
JPanel checkPanel = new JPanel();
channels sharedVariables;
int notIndex;

CustomizeExcludedBoardsDialog(JFrame frame, boolean mybool, channels sharedVariables1, 	resourceClass graphics1, boolean [] excludedBoards1)
{
super(frame, mybool);
sharedVariables=sharedVariables1;
graphics=graphics1;
excludedBoards =excludedBoards1;
String excluded=getExcluded();
if(excluded== null)
excluded = "";
setTitle(" Boards checked show in random square tiles on observe.");
setSize(550,500);
setDefaultCloseOperation(DISPOSE_ON_CLOSE);

checkPanel.setLayout(new GridLayout(0, 3));
myBoards = new JCheckBox[dummyUse.maxBoards];



for(int z=0; z< dummyUse.maxBoards; z++)
{ if(z == 0 ) {
    myBoards[z] = new JCheckBox("Solid Colored Board");
   }
 else if(dummyUse.boardPaths[z].equals("wood2")) {
   myBoards[z] = new JCheckBox("wood6");
} else if(dummyUse.boardPaths[z].equals("wood3")) {
  myBoards[z] = new JCheckBox("wood7");
} else {
   myBoards[z] = new JCheckBox(dummyUse.boardPaths[z]);
}
  if(excludedBoards[z]==false)
  myBoards[z].setSelected(true);
  checkPanel.add(myBoards[z]);
  final int num=z;
  myBoards[z].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event)
				{
                                  //String mytext= field.getText();
				 try
			 	{        
                                         Boards=num;
                                         myImagePanel.repaint();
                                  }
                                  catch(Exception dummy){}

    }// end method
    }// end class
    );

} // end for

okButton = new JButton("Ok");
cancelButton = new JButton("Cancel");
field= new JTextField(3);
JPanel panel = new JPanel();



okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event)
				{
                                  //String mytext= field.getText();
				 try
			 	{
			         for(int z=0; z<dummyUse.maxBoards; z++)
			         if(myBoards[z].isSelected()==true)
			         excludedBoards[z]=false;
			         else
			         excludedBoards[z]=true;

				dispose();
				}
				catch(Exception dummy){dispose();}

			}// end event

		});

cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event)
				{
                                  //String mytext= field.getText();
				 try
			 	{
					dispose();
				}
				catch(Exception dummy){}

			}// end event

		});


JTextArea field2 = new JTextArea();
field2.setEditable(false);
Color backcol= new Color(0,0,0);
Color forcol = new Color(255,255,255);
field2.setBackground(backcol);
field2.setForeground(forcol);

field2.setLineWrap(true);
field2.setWrapStyleWord(true);
JScrollPane myscroller = new JScrollPane(field2);
field2.setFont(sharedVariables.myFont);
field2.setText(getText());



 	  GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

	ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
         SequentialGroup h4 = layout.createSequentialGroup();



	hGroup.addComponent(myImagePanel);
        hGroup.addComponent(myscroller);
        hGroup.addComponent(checkPanel);
	h4.addComponent(okButton);
	h4.addComponent(cancelButton);
        hGroup.addGroup(h4);


	//Create the horizontal group
	layout.setHorizontalGroup(hGroup);


	//Create a parallel group for the vertical axis
	ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);// was leading
	//Create a sequential group v1

SequentialGroup v4 = layout.createSequentialGroup();

	//Add the group v2 tp the group v1

	v4.addComponent(checkPanel);
        	v4.addComponent(myscroller);

   ParallelGroup v3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);

	v3.addComponent(okButton);
	v3.addComponent(cancelButton);


        v4.addComponent(myImagePanel, 75, 75, 75);
          v4.addGroup(v3);


	vGroup.addGroup(v4);
	//Create the vertical group
	layout.setVerticalGroup(vGroup);





}// end constructor

String getExcluded()
{

String mess=" ";

for(int z=0; z < dummyUse.maxBoards; z++)
if(excludedBoards[z] == true)
{

mess+= " " + z;
}
return mess;
}

String getText()
{
String mess="";

mess = "Check the Boards you wish for random square tiles on observe to choose from and uncheck those you dont. Hit ok to save your changes.";


 return mess;
}

class JImagePanel extends JPanel
{
	public void paintComponent(Graphics g)
		{

		try {

		super.paintComponent(g);

		setBackground(sharedVariables.boardBackgroundColor);

		Graphics2D g2 = (Graphics2D) g;

                int boardx=5;
		int boardy=5; // upper left
		int square=75;
		int dif=2;
                for(int a=0; a<6; a++)   {
                 if(Boards == 0)
                 {
                     if(a%2 == 1)
				g2.setColor(sharedVariables.lightcolor);
				else
				g2.setColor(sharedVariables.darkcolor);



					g2.fill(new Rectangle2D.Double((double) boardx +  a * square + dif, (double) boardy + dif, (double) square - dif, (double)square-4));

                 }//end solid colored board
                 else {
                     g.drawImage(graphics.boards[Boards][(a+1)%2], boardx +  a * square + dif , boardy + dif, square - dif, square-4, this);
                 }
                }


  }
  catch(Exception dui){}

}
}// end image class
}// end class
