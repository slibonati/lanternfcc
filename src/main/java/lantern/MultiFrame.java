package lantern;
/*
*  Copyright (C) 2010-2022 Michael Ronald Adams, Andrey Gorlin.
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

//http://java.sun.com/products/jfc/tsc/articles/tablelayout/
import layout.*;
import java.util.*;
import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JDialog;
import java.io.*;
import java.net.*;
import java.lang.Thread.*;
import java.applet.*;
import javax.swing.GroupLayout.*;
import javax.swing.colorchooser.*;
import javax.swing.event.*;
import java.lang.Integer;
import javax.swing.text.*;

import org.slf4j.Logger;

import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.applet.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue; // added by Andrey
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.filechooser.FileFilter;
import java.util.Timer;
import java.util.TimerTask;

class Multiframe extends JFrame implements ActionListener, ChangeListener, WindowListener {

	Logger logger = org.slf4j.LoggerFactory.getLogger(Multiframe.class);
	userButtonsDialog mydialog = null;
	connectionDialog myConnection;
	seekGraphFrame seekGraph;
	JToolBar toolBar;
	docWriter myDocWriter;
	listFrame myfirstlist;
	listInternalFrame mysecondlist;
	seekGameDialog myseeker;
	notifyFrame myNotifyFrame;
	topGamesFrame myTopGamesFrame;
	gameFrame myGameList;
	listClass eventsList;
	listClass tournamentList;
	listClass seeksList;
	listClass computerSeeksList;
	toolboxDialog mybox;
	saveScores mineScores;

	listClass notifyList;
	tableClass gameList;
	webframe mywebframe;
	channels sharedVariables;
	private JTextPane[] consoles;
	private JTextPane[] gameconsoles;
	protected JColorChooser tcc;
	int colortype;

	// Andrey says:
	// organize these in an intuitive manner
	JCheckBoxMenuItem autonoidle;
	JCheckBoxMenuItem autobufferchat;
	JCheckBoxMenuItem chatbufferlarge;
	JCheckBoxMenuItem autoHistoryPopup;
	JCheckBoxMenuItem showMugshots;
	JCheckBoxMenuItem makeObserveSounds;
	JCheckBoxMenuItem hearsound;
	JCheckBoxMenuItem gameend;
	JCheckBoxMenuItem channelNumberLeft;
	JCheckBoxMenuItem tabbing;
	/*
	 * Andrey edits: make an array for the board designs JCheckBoxMenuItem
	 * BoardDesign1; JCheckBoxMenuItem BoardDesign2; JCheckBoxMenuItem BoardDesign3;
	 */
	JCheckBoxMenuItem[] boarddesignarray = new JCheckBoxMenuItem[3];

	JCheckBoxMenuItem ucimultipleone;
	JCheckBoxMenuItem ucimultipletwo;
	JCheckBoxMenuItem ucimultiplethree;
	JCheckBoxMenuItem maketellsounds;
	JCheckBoxMenuItem makeatnamesounds;
	JCheckBoxMenuItem makedrawsounds;
	JCheckBoxMenuItem makemovesounds;

	JCheckBoxMenuItem tellswitch;
	JCheckBoxMenuItem addnameontellswitch;
	JCheckBoxMenuItem highlight;
	JCheckBoxMenuItem materialCount;
	JCheckBoxMenuItem drawCoordinates;
	JCheckBoxMenuItem showRatings;
	JCheckBoxMenuItem showFlags;
	// JCheckBoxMenuItem chessFontForMoveList;
	JCheckBoxMenuItem showPallette;
	JCheckBoxMenuItem autoChat;
	JCheckBoxMenuItem lowTimeColors;
	JCheckBoxMenuItem newObserveGameSwitch;
	JCheckBoxMenuItem blockSays;
	JCheckBoxMenuItem useLightBackground;
	/*
	 * Andrey edits: make an array for the console selection JCheckBoxMenuItem
	 * boardconsole0; JCheckBoxMenuItem boardconsole1; JCheckBoxMenuItem
	 * boardconsole2; JCheckBoxMenuItem boardconsole3;
	 */
	JCheckBoxMenuItem[] boardconsolearray = new JCheckBoxMenuItem[4];
	JCheckBoxMenuItem sidewaysconsole;
	JCheckBoxMenuItem sidewaysconsolemax;
	JCheckBoxMenuItem bottomconsole;
	JCheckBoxMenuItem playersInMyGame;
	JCheckBoxMenuItem unobserveGoExamine;

	JCheckBoxMenuItem alwaysShowEdit;
	JCheckBoxMenuItem consolemenu;

	JCheckBoxMenuItem toolbarvisible;
	JCheckBoxMenuItem lineindent;

	JCheckBoxMenuItem tabLayout1;
	JCheckBoxMenuItem tabLayout2;
	JCheckBoxMenuItem tabLayout3;

	JCheckBoxMenuItem shoutTimestamp;
	JCheckBoxMenuItem tellTimestamp;
	JCheckBoxMenuItem channelTimestamp;
	JCheckBoxMenuItem leftNameTimestamp;
	JCheckBoxMenuItem reconnectTimestamp;
	JCheckBoxMenuItem qtellTimestamp;
	JCheckBoxMenuItem timeStamp24hr;
	JCheckBoxMenuItem checkLegality;
	JCheckBoxMenuItem noFocusOnObserve;
	JCheckBoxMenuItem useTopGame;
	JCheckBoxMenuItem dontReuseGameTabs;

	/*
	 * Andrey edits: make an array for the aspects JCheckBoxMenuItem aspect0;
	 * JCheckBoxMenuItem aspect1; JCheckBoxMenuItem aspect2; JCheckBoxMenuItem
	 * aspect3;
	 */
	JCheckBoxMenuItem[] aspectarray = new JCheckBoxMenuItem[4];

	/*
	 * Andrey edits: make an array for the boards JCheckBoxMenuItem woodenboard1;
	 * JCheckBoxMenuItem woodenboard2; JCheckBoxMenuItem woodenboard3;
	 * JCheckBoxMenuItem grayishboard; JCheckBoxMenuItem solidboard;
	 * JCheckBoxMenuItem oliveboard; JCheckBoxMenuItem cherryboard;
	 * JCheckBoxMenuItem purpleboard;
	 * 
	 * JCheckBoxMenuItem board5; JCheckBoxMenuItem board6; JCheckBoxMenuItem board7;
	 */
	JCheckBoxMenuItem[] boardarray;

	/*
	 * Andrey edits: make an array for the presets JMenuItem preset0; JMenuItem
	 * preset1; JMenuItem preset2; JMenuItem preset3;
	 */
	JMenuItem[] presetarray = new JMenuItem[4];

	/*
	 * Andrey edits: make an array for the pieces JCheckBoxMenuItem pieces1;
	 * JCheckBoxMenuItem pieces2; JCheckBoxMenuItem pieces3; JCheckBoxMenuItem
	 * pieces4; JCheckBoxMenuItem pieces5; JCheckBoxMenuItem pieces6;
	 * JCheckBoxMenuItem pieces7; JCheckBoxMenuItem pieces8; JCheckBoxMenuItem
	 * pieces9; JCheckBoxMenuItem pieces10; JCheckBoxMenuItem pieces11;
	 * JCheckBoxMenuItem pieces12; JCheckBoxMenuItem pieces13; JCheckBoxMenuItem
	 * pieces14; JCheckBoxMenuItem pieces15; JCheckBoxMenuItem pieces16;
	 * JCheckBoxMenuItem pieces17; JCheckBoxMenuItem pieces18; JCheckBoxMenuItem
	 * pieces19; JCheckBoxMenuItem pieces20; JCheckBoxMenuItem pieces21;
	 * JCheckBoxMenuItem pieces22; JCheckBoxMenuItem pieces23; JCheckBoxMenuItem
	 * pieces24;
	 */
	JCheckBoxMenuItem[] piecesarray = new JCheckBoxMenuItem[27];
	JCheckBoxMenuItem[] checkerspiecesarray = new JCheckBoxMenuItem[2];
	JCheckBoxMenuItem[] italicsBehavior = new JCheckBoxMenuItem[3];

	JCheckBoxMenuItem randomArmy;
	JCheckBoxMenuItem randomTiles;

	JCheckBoxMenuItem iloggedon;
	JCheckBoxMenuItem rotateaways;
	JCheckBoxMenuItem notifysound;
	JCheckBoxMenuItem correspondenceNotificationSounds;
	JCheckBoxMenuItem qsuggestPopup;
	JCheckBoxMenuItem disableHyperlinks;
	JCheckBoxMenuItem autopopup;
	JCheckBoxMenuItem basketballFlag;
	JCheckBoxMenuItem autoPromote;
	JMenu moveInputMenu;
	JCheckBoxMenuItem dragMoveInput;
	JCheckBoxMenuItem clickMoveInput;

	JCheckBoxMenuItem pgnlogging;
	JCheckBoxMenuItem pgnObservedLogging;
	JCheckBoxMenuItem compactNameList;

	JMenuItem reconnect2;

	createWindows mycreator;
	resourceClass graphics;
	Runtime rt;

	ConcurrentLinkedQueue<myoutput> queue;
	// Andrey says:
	// want to be able to change this to:
	// Queue<myoutput> queue;

	chessbot4 client;
	Gameboard[] myboards;
	Image[] img;
	ConcurrentLinkedQueue<newBoardData> gamequeue;
	// Andrey says:
	// want to be able to change this to:
	// Queue<newBoardData> gamequeue;
	subframe[] consoleSubframes;
	chatframe[] consoleChatframes;

	settings mysettings;

	class MyFocusTraversalPolicy extends ContainerOrderFocusTraversalPolicy {

		protected boolean accept(Component aComp) {
			if (aComp instanceof subframe || aComp instanceof Gameboard)
				return super.accept(aComp);

			return false; // JLabel and JPanel.
		}
	}

	Multiframe() {

		graphics = new resourceClass();
		gamequeue = new ConcurrentLinkedQueue<newBoardData>();

		sharedVariables = new channels();
		// chess font setting
		sharedVariables.chessFontForMoveList = false;
		loadGraphicsStandAlone();
		sharedVariables.useTopGames = getOnTopSetting();
		queue = new ConcurrentLinkedQueue<myoutput>();

		seekGraph = new seekGraphFrame(sharedVariables, queue, (JFrame) this);

		try {
			seekGraph.setSize(sharedVariables.mySeekSizes.con0x, sharedVariables.mySeekSizes.con0y);
			seekGraph.setLocation(sharedVariables.mySeekSizes.point0.x, sharedVariables.mySeekSizes.point0.y);
		} catch (Exception duiseeek) {
		}

		myboards = new Gameboard[sharedVariables.maxGameTabs];
		for (int bbo = 0; bbo < sharedVariables.maxGameTabs; bbo++)
			myboards[bbo] = null;

		img = new Image[14];
		mineScores = new saveScores();

		mysettings = new settings(sharedVariables);

		sharedVariables.img = img;

		consoles = new JTextPane[sharedVariables.maxConsoleTabs];
		gameconsoles = new JTextPane[sharedVariables.maxGameConsoles];
		consoleSubframes = new subframe[sharedVariables.maxConsoles];
		consoleChatframes = new chatframe[sharedVariables.maxConsoles];

		colortype = 1;
		sharedVariables.desktop = new JDesktopPaneCustom(sharedVariables, myboards, consoleSubframes, this);

		sharedVariables.desktop.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */) {
					JPopupMenu menu2 = new JPopupMenu("Popup2");
					JMenuItem item1 = new JMenuItem("Set Background");
					item1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setWallPaper();
						}

					});

					menu2.add(item1);

					JMenuItem item2 = new JMenuItem("Set Application Background Color");
					item2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setApplicationColor();

						}

					});

					menu2.add(item2);
					add(menu2);
					menu2.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		sharedVariables.desktop.add(seekGraph);

		setFocusCycleRoot(true);
		setFocusTraversalPolicy(new MyFocusTraversalPolicy());

		String myweburl = "www.google.com";
		mywebframe = new webframe(sharedVariables, queue, myweburl);
		sharedVariables.desktop.add(mywebframe);
		try {
			mywebframe.setSize(sharedVariables.webframeWidth, sharedVariables.webframeHeight);
		} catch (Exception d) {
			logger.error("exception encountered: ", d);
		}
		;

		/*************** trying list code *****************/
		notifyList = new listClass("Notify Online");
		notifyList.notifyStateChanged("Double Click to Watch", "1");
		eventsList = new listClass("Events");
		tournamentList = new listClass("Tournaments");
		seeksList = new listClass("Human Seeks");
		computerSeeksList = new listClass("Computer Seeks");
		sharedVariables.activitiesPanel = new ActivitiesWindowPanel(this, sharedVariables, queue, eventsList, seeksList,
				computerSeeksList, notifyList, tournamentList, this);
		myfirstlist = new listFrame(this, sharedVariables, queue);
		mysecondlist = new listInternalFrame(this, sharedVariables, queue);
		sharedVariables.desktop.add(mysecondlist);
		myNotifyFrame = new notifyFrame(this, sharedVariables, queue, notifyList);
		myNotifyFrame.setLocation(1, 1); // if 1 1 we'll position it later
		myTopGamesFrame = new topGamesFrame(this, sharedVariables, queue, eventsList);
		myTopGamesFrame.setLocation(1, 1); // if 1 1 we'll position it later
		gameList = new tableClass();
		myGameList = new gameFrame(sharedVariables, queue, gameList);
		sharedVariables.myGameList = myGameList;
		myGameList.setSize(600, 425);
		// sharedVariables.desktop.add(myGameList);

		/*************** end list code ******************/
		docWriter myDocWriter = new docWriter(sharedVariables, consoleSubframes, consoles, gameconsoles, myboards,
				consoleChatframes);
		mycreator = new createWindows(sharedVariables, consoleSubframes, myboards, consoles, gameconsoles, queue, img,
				gamequeue, mywebframe, graphics, myfirstlist, mysecondlist, myDocWriter, consoleChatframes, this);
		mycreator.createConsoleFrame(); // Create first window
		mycreator.createGameFrame();
		// mycreator.createListFrame(eventsList, seeksList, computerSeeksList,
		// notifyList, this);
		// setContentPane(sharedVariables.desktop);
		// add window listener so we can close an engine if open
		addWindowListener(this);
		getContentPane().add(sharedVariables.desktop, "Center");

		getSettings();

		String[][] settingsComboMemory = new String[sharedVariables.maxConsoleTabs][sharedVariables.maxConsoleTabs];
		for (int seta = 0; seta < sharedVariables.maxConsoleTabs; seta++)
			for (int setb = 0; setb < sharedVariables.maxConsoleTabs; setb++)
				settingsComboMemory[seta][setb] = ">";

		sharedVariables.hasSettings = mysettings.readNow(myboards, consoleSubframes, sharedVariables, consoles,
				gameconsoles, settingsComboMemory);
		// read for any saved settings don't know what get settings is doing MA 5-30-10
		mineScores.readNow(sharedVariables);

		client = new chessbot4(gameconsoles, gamequeue, queue, consoles, sharedVariables, myboards, consoleSubframes,
				mycreator, graphics, eventsList, tournamentList, seeksList, computerSeeksList, notifyList, gameList,
				myGameList, this, consoleChatframes, seekGraph, this, myConnection, myfirstlist, mysecondlist);
		repaint();
		client.enabletimestamp();

		loadSoundsStandAlone();
		// loadGraphicsApplet();
		// loadSoundsApplet();

		// we look if these files exist and if we do load name pass then
		// any commands into script array list for connect to run MA
		// 1-1-11
		try {

			scriptLoader loadScripts = new scriptLoader();
			loadScripts.loadScript(sharedVariables.iccLoginScript, "lantern_icc.ini");
			loadScripts.loadScript(sharedVariables.ficsLoginScript, "lantern_fics.ini");
			loadScripts.loadScript(sharedVariables.notifyControllerScript, sharedVariables.notifyControllerFile);

		} catch (Exception scriptErrror) {
			logger.error("exception encountered: ", scriptErrror);
		}

		setUpChannelNotify();
		setUpLanternNotify();
		parseCountries();
		Thread t = new Thread(client);
		t.start();

		createMenu();
		consoleSubframes[0].makeHappen(0);

		highlight.setSelected(sharedVariables.highlightMoves);
		gameend.setSelected(sharedVariables.gameend);
		dontReuseGameTabs.setSelected(sharedVariables.dontReuseGameTabs);
		useTopGame.setSelected(sharedVariables.useTopGames);
		materialCount.setSelected(sharedVariables.showMaterialCount);
		drawCoordinates.setSelected(sharedVariables.drawCoordinates);
		showRatings.setSelected(sharedVariables.showRatings);
		showFlags.setSelected(sharedVariables.showFlags);
		// chessFontForMoveList.setSelected(sharedVariables.chessFontForMoveList);
		showPallette.setSelected(sharedVariables.showPallette);
		autoChat.setSelected(sharedVariables.autoChat);
		lowTimeColors.setSelected(sharedVariables.lowTimeColors);
		newObserveGameSwitch.setSelected(sharedVariables.newObserveGameSwitch);
		blockSays.setSelected(sharedVariables.blockSays);
		useLightBackground.setSelected(sharedVariables.useLightBackground);

		setPieces(sharedVariables.pieceType);
		setCheckersPieces(sharedVariables.checkersPieceType);
		if (sharedVariables.boardType > graphics.maxBoards - 1 || sharedVariables.boardType < 0)
			sharedVariables.boardType = 2;
		setBoard(sharedVariables.boardType);
		if (sharedVariables.moveInputType == 0) {
			dragMoveInput.setSelected(true);
			clickMoveInput.setSelected(false);
		} else {
			dragMoveInput.setSelected(false);
			clickMoveInput.setSelected(true);
		}
		autoPromote.setSelected(sharedVariables.autoPromote);
		pgnObservedLogging.setSelected(sharedVariables.pgnObservedLogging);
		pgnlogging.setSelected(sharedVariables.pgnLogging);
		tellswitch.setSelected(sharedVariables.switchOnTell);
		addnameontellswitch.setSelected(sharedVariables.addNameOnSwitch);
		toolbarvisible.setSelected(sharedVariables.toolbarVisible);
		autobufferchat.setSelected(sharedVariables.autoBufferChat);
		chatbufferlarge.setSelected(sharedVariables.chatBufferLarge);
		chatbufferlarge.setSelected(sharedVariables.chatBufferLarge);
		channelNumberLeft.setSelected(sharedVariables.channelNumberLeft);
		channelTimestamp.setSelected(sharedVariables.channelTimestamp);
		shoutTimestamp.setSelected(sharedVariables.shoutTimestamp);
		qtellTimestamp.setSelected(sharedVariables.qtellTimestamp);
		timeStamp24hr.setSelected(channels.timeStamp24hr);
		reconnectTimestamp.setSelected(sharedVariables.reconnectTimestamp);

		if (sharedVariables.andreysLayout >= 0 && sharedVariables.andreysLayout < boarddesignarray.length)
			boarddesignarray[sharedVariables.andreysLayout].setSelected(true);

		playersInMyGame.setSelected((sharedVariables.playersInMyGame == 2));
		unobserveGoExamine.setSelected(sharedVariables.unobserveGoExamine);
		tellTimestamp.setSelected(sharedVariables.tellTimestamp);
		leftNameTimestamp.setSelected(channels.leftTimestamp);
		checkLegality.setSelected(sharedVariables.checkLegality);
		noFocusOnObserve.setSelected(sharedVariables.noFocusOnObserve);
		lineindent.setSelected(sharedVariables.indent);

		checkItalicsBehavior(sharedVariables.italicsBehavior);

		randomArmy.setSelected(sharedVariables.randomArmy);
		randomTiles.setSelected(sharedVariables.randomBoardTiles);
		notifysound.setSelected(sharedVariables.specificSounds[4]);
		correspondenceNotificationSounds.setSelected(sharedVariables.correspondenceNotificationSounds);
		maketellsounds.setSelected(sharedVariables.makeTellSounds);
		makeatnamesounds.setSelected(sharedVariables.makeAtNameSounds);
		makemovesounds.setSelected(sharedVariables.makeMoveSounds);
		makedrawsounds.setSelected(sharedVariables.makeDrawSounds);
		tabbing.setSelected(sharedVariables.tabsOnly);
		qsuggestPopup.setSelected(sharedVariables.showQsuggest);
		disableHyperlinks.setSelected(sharedVariables.disableHyperlinks);
		rotateaways.setSelected(sharedVariables.rotateAways);
		if (sharedVariables.uciMultipleLines == 1) {
			ucimultipleone.setSelected(true);
		} else if (sharedVariables.uciMultipleLines == 2) {
			ucimultipletwo.setSelected(true);
		} else if (sharedVariables.uciMultipleLines == 3) {
			ucimultiplethree.setSelected(true);
		}
		if (sharedVariables.rotateAways) {
			try {
				scriptLoader loadScripts = new scriptLoader();
				if (channels.macClient) {
					loadScripts.loadScript(sharedVariables.lanternAways, channels.publicDirectory + "lantern_away.txt");
				} else {
					loadScripts.loadScript(sharedVariables.lanternAways, "lantern_away.txt");
				}

			} catch (Exception du) {
			}
		}
		boolean sideValue = false;
		if (sharedVariables.sideways == 1) {
			sidewaysconsole.setSelected(true);
		} else if (sharedVariables.sideways == 0) {
			sidewaysconsolemax.setSelected(true);
		} else if (sharedVariables.sideways == 2) {
			bottomconsole.setSelected(true);
		}

		iloggedon.setSelected(sharedVariables.iloggedon);

		alwaysShowEdit.setSelected(sharedVariables.alwaysShowEdit);
		autopopup.setSelected(sharedVariables.autopopup);
		basketballFlag.setSelected(sharedVariables.basketballFlag);
		autoHistoryPopup.setSelected(sharedVariables.autoHistoryPopup);
		makeObserveSounds.setSelected(sharedVariables.makeObserveSounds);
		showMugshots.setSelected(sharedVariables.showMugshots);
		autonoidle.setSelected(sharedVariables.noidle);
		hearsound.setSelected(sharedVariables.makeSounds);
		consolemenu.setSelected(sharedVariables.showConsoleMenu);

		/*
		 * if(sharedVariables.consoleLayout == 1) { tabLayout1.setSelected(true);
		 * tabLayout2.setSelected(false); tabLayout3.setSelected(false); } else
		 * if(sharedVariables.consoleLayout == 3) { tabLayout1.setSelected(false);
		 * tabLayout2.setSelected(false); tabLayout3.setSelected(true);
		 * consoleSubframes[0].overall.recreate(); } else {
		 * tabLayout1.setSelected(false); tabLayout2.setSelected(true);
		 * tabLayout3.setSelected(false);
		 * 
		 * consoleSubframes[0].overall.recreate(); }
		 */

		/* name list stuff */
		sharedVariables.activitiesPanel.theChannelList.setForeground(sharedVariables.nameForegroundColor);
		sharedVariables.activitiesPanel.theChannelList.setBackground(sharedVariables.nameBackgroundColor);
		sharedVariables.activitiesPanel.theChannelList.setFont(sharedVariables.nameListFont);
		sharedVariables.activitiesPanel.theChannelList2.setForeground(sharedVariables.nameForegroundColor);
		sharedVariables.activitiesPanel.theChannelList2.setBackground(sharedVariables.nameBackgroundColor);
		sharedVariables.activitiesPanel.theChannelList2.setFont(sharedVariables.nameListFont);
		sharedVariables.activitiesPanel.theChannelList3.setForeground(sharedVariables.nameForegroundColor);
		sharedVariables.activitiesPanel.theChannelList3.setBackground(sharedVariables.nameBackgroundColor);
		sharedVariables.activitiesPanel.theChannelList3.setFont(sharedVariables.nameListFont);

		for (int iii = 0; iii < sharedVariables.maxConsoleTabs; iii++) {
			if (consoleSubframes[iii] != null) {

				if (sharedVariables.nameListFont == null)
					sharedVariables.nameListFont = consoleSubframes[iii].myNameList.getFont();
				if (sharedVariables.consolesNamesLayout[iii] == 0) {

					consoleSubframes[iii].listChoice.setSelected(false);
				}
				consoleSubframes[iii].myNameList.setForeground(sharedVariables.nameForegroundColor);
				consoleSubframes[iii].myNameList.setBackground(sharedVariables.nameBackgroundColor);

				consoleSubframes[iii].overall.recreate(sharedVariables.consolesTabLayout[iii]);
				final int iiii = iii;
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							if (sharedVariables.tellconsole != iiii)
								consoleSubframes[iiii].tellCheckbox.setSelected(false);
							// we set it to true in create console to match tell
							// variable. undo the first one here if needed
						} catch (Exception e1) {

						}
					}
				});
			}
		}

		try {
			for (int bam = 0; bam < sharedVariables.openConsoleCount; bam++)
				if (sharedVariables.showConsoleMenu) {
					consoleSubframes[bam].setJMenuBar(consoleSubframes[bam].consoleMenu);
					consoleSubframes[bam].consoleMenu.revalidate();
				} else {
					consoleSubframes[bam].setJMenuBar(consoleSubframes[bam].consoleEditMenu);
					consoleSubframes[bam].consoleEditMenu.revalidate();
					consoleSubframes[bam].consoleEditMenu.setVisible(sharedVariables.alwaysShowEdit);
				}
			// consoleSubframes[bam].consoleMenu.setVisible(sharedVariables.showConsoleMenu);
		} catch (Exception bal) {
		}

		/******************
		 * we do these next few in gui thread
		 *****************************/
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					if (sharedVariables.boardConsoleType != 2)
						redrawBoard(sharedVariables.boardConsoleType);
					// now apply foreground color to tabs
					for (int cona = 0; cona < sharedVariables.maxConsoleTabs; cona++)
						consoles[cona].setForeground(sharedVariables.ForColor);
					// now add in channel tab default names
					try {
						for (int mycons = 1; mycons < sharedVariables.maxConsoleTabs; mycons++) {
							setConsoleTabTitles asetter = new setConsoleTabTitles();
							asetter.createConsoleTabTitle(sharedVariables, mycons, consoleSubframes,
									sharedVariables.consoleTabCustomTitles[mycons]);
						}
					} catch (Exception badsetting) {
					}

					setInputFont();
					try {
						// now game boards
						for (int i = 0; i < sharedVariables.maxGameTabs; i++) {
							if (myboards[i] != null) {
								myboards[i].mycontrolspanel.setFont();
							}
						}

						// JFrame framer = new JFrame("open board count is (later event hopefully)" +
						// sharedVariables.openBoardCount);
						// framer.setSize(200,100);
						// framer.setVisible(true);
					} catch (Exception bdfont) {
					}

				} catch (Exception e1) {

				}
			}
		});

		// applet
		// Image myIconImage = getImage(getDocumentBase(), "lantern.png");
		// setIconImage(myIconImage);
		// end applet
		// stand alone
		try {
			Image myIconImage = null;
			URL myurl = this.getClass().getResource("lantern.png");
			myIconImage = Toolkit.getDefaultToolkit().getImage(myurl);

			Image myIconImage16 = null;
			URL myurl16 = this.getClass().getResource("lantern-16.png");
			myIconImage16 = Toolkit.getDefaultToolkit().getImage(myurl16);

			Image myIconImage24 = null;
			URL myurl24 = this.getClass().getResource("lantern-24.png");
			myIconImage24 = Toolkit.getDefaultToolkit().getImage(myurl24);

			Image myIconImage32 = null;
			URL myurl32 = this.getClass().getResource("lantern-32.png");
			myIconImage32 = Toolkit.getDefaultToolkit().getImage(myurl32);

			Image myIconImage48 = null;
			URL myurl48 = this.getClass().getResource("lantern-48.png");
			myIconImage48 = Toolkit.getDefaultToolkit().getImage(myurl48);

			Image myIconImage64 = null;
			URL myurl64 = this.getClass().getResource("lantern-64.png");
			myIconImage64 = Toolkit.getDefaultToolkit().getImage(myurl64);

			Image myIconImage128 = null;
			URL myurl128 = this.getClass().getResource("lantern-128.png");
			myIconImage128 = Toolkit.getDefaultToolkit().getImage(myurl128);

			Image myIconImage256 = null;
			URL myurl256 = this.getClass().getResource("lantern-256.png");
			myIconImage256 = Toolkit.getDefaultToolkit().getImage(myurl256);

			Image myIconImage512 = null;
			URL myurl512 = this.getClass().getResource("lantern-512.png");
			myIconImage512 = Toolkit.getDefaultToolkit().getImage(myurl512);

			Image myPearlImage512 = null;
			URL myurlPearl512 = this.getClass().getResource("pearlchess.png");
			myPearlImage512 = Toolkit.getDefaultToolkit().getImage(myurlPearl512);

			final java.util.List<Image> icons = new ArrayList<Image>();
			if (channels.fics) {
				icons.add(myPearlImage512);
			} else {
				icons.add(myIconImage16);
				icons.add(myIconImage24);
				icons.add(myIconImage32);
				icons.add(myIconImage48);
				icons.add(myIconImage64);
				icons.add(myIconImage128);
				icons.add(myIconImage256);
				icons.add(myIconImage512);
			}

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {

					setIconImages(icons);

				}
			});
			// setIconImage(myIconImage);
			// need a package for this
			/*
			 * if (sharedVariables.operatingSystem.equals("mac")) { Application app = new
			 * Application(); app.setDockIconImage(myIconImage); }
			 */
		} catch (Exception e) {
			logger.error("exception encountered: ", e);
		}
		try {
			setMySize();
		} catch (Exception donthaveit) {
			logger.error("exception encountered: ", donthaveit);
		}

		try {
			sharedVariables.activitiesPanel.theEventsList.setFont(sharedVariables.eventsFont);
		} catch (Exception badfontsetting) {
		}

		if (!sharedVariables.hasSettings) {
			// this hasSettings is returned by readNow which reads
			// settings. if false. they have no settings file and i try to
			// position windows. MA 9-19-10
			try {
				if (sharedVariables.screenW > 100 && sharedVariables.screenH > 100) {
					mycreator.restoreConsoleFrame();
					int px = 10;
					int py = 10;
					int cw = (int) (sharedVariables.screenW * 2 / 5 - px);
					int ch = (int) ((sharedVariables.screenH / 2) - py - (sharedVariables.screenH / 2) / 9);

					consoleSubframes[0].setLocation(px, py);
					consoleSubframes[0].setSize(cw, ch);
					consoleSubframes[1].setLocation(px, py + py / 2 + ch);
					consoleSubframes[1].setSize(cw, ch);

					px = px + px + cw;
					py = 30;
					cw = (int) (sharedVariables.screenW * 3 / 5 - 30);
					ch = (int) (sharedVariables.screenH - py - sharedVariables.screenH / 9);
					if (ch > cw + 100)
						ch = cw + 100;

					if (!sharedVariables.useTopGames) {
						myboards[0].setLocation(px, py);
						myboards[0].setSize(cw, ch);
					} else {
						final int px1 = px;
						final int py1 = py;
						final int cw1 = cw;
						final int ch1 = ch;
						try {
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									try {
										if (myboards[0].topGame != null) {
											myboards[0].topGame.setLocation(px1, py1);
											myboards[0].topGame.setSize(cw1, ch1);
										}
									} catch (Exception e1) {
										logger.error("exception encountered: ", e1);
									}
								}
							});

						} catch (Exception badf) {
							logger.error("exception encountered: ", badf);
						}
					}
				}
			} catch (Exception dontknow) {
				logger.error("exception encountered: ", dontknow);
			}
			// could not complete getting screen size and postioning windows
		} // end if not have any settings read

		// make as many consoles as we had last time
		try {
			if (sharedVariables.visibleConsoles > 1)
				for (int nummake = 1; nummake < sharedVariables.visibleConsoles; nummake++)
					mycreator.restoreConsoleFrame();

		} catch (Exception makingConsoles) {
			logger.error("exception encountered: ", makingConsoles);
		}

		makeToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		toolBar.setVisible(sharedVariables.toolbarVisible);

		try {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						// myboards[0].recreate();
					} catch (Exception e1) {
						logger.error("exception encountered: ", e1);
					}
				}
			});

		} catch (Exception badf) {
			logger.error("exception encountered: ", badf);
		}

		mycreator.updateBoardsMenu(0); // first board

		updateTabPrefixesToSettings(settingsComboMemory);
	}

	void updateTabPrefixesToSettings(String settingsComboMemory[][]) {

		for (int cona = 0; cona < sharedVariables.maxConsoleTabs; cona++)
			for (int conb = 1; conb < sharedVariables.maxConsoleTabs; conb++)
				if (!settingsComboMemory[cona][conb].equals(">")) {

					if (sharedVariables.looking[cona] == conb) {

						// they are on that tab. update the prefix
						boolean go = false;
						for (int zb = 0; zb < consoleSubframes[cona].prefixHandler.getItemCount(); zb++) {
							String tempo = (String) consoleSubframes[cona].prefixHandler.getItemAt(zb);

							if (tempo.trim().equals(settingsComboMemory[cona][conb].trim())) {
								consoleSubframes[cona].prefixHandler.setSelectedIndex(zb);
								break;
							}

						} // end for

					} else {

						consoleSubframes[cona].comboMemory[conb] = settingsComboMemory[cona][conb];
						/*
						 * JFrame framer = new JFrame(" cona is " + cona + " and conb is " + conb +
						 * " and pre is " + pre + " and aitem is " + aItem); framer.setSize(500,100);
						 * framer.setVisible(true);
						 */

					}

				}

	}

	public void parseCountries() {
		/*
		 * try { scriptLoader loadScripts = new scriptLoader(); ArrayList<String>
		 * country = new ArrayList(); loadScripts.loadScript(country, "flags3.txt");
		 * String output = "";
		 * 
		 * 
		 * for (int z=0; z<country.size(); z++) { String line= country.get(z); String
		 * first=""; String last=""; String text=""; String oldtext="";
		 * 
		 * try {
		 * 
		 * line=line.replace("\t", " "); if (line.contains("N/A")) continue;
		 * StringTokenizer tokens = new StringTokenizer(line, " ");
		 * 
		 * boolean lastToken=false;
		 * 
		 * while (!lastToken) { oldtext=text; try {
		 * 
		 * text=tokens.nextToken(); if (text==null) { last=oldtext; lastToken=true; //
		 * end if null } else { if (first.equals("")) first=oldtext; else first=first +
		 * "_" + oldtext; }
		 * 
		 * }// end try catch (Exception dui) { last=oldtext; lastToken=true; } } // end
		 * while output+=last.toUpperCase() + ";" + first + ";"; //int
		 * i=line.indexOf(";"); //String line1=line.substring(0, i); //String
		 * line2=line.substring(i+1, line.length()); //output+=line2 + ";" + line1 +
		 * ";";
		 * 
		 * } catch (Exception dogeatdog) {} }// end for FileWrite writer = new
		 * FileWrite(); writer.write(output, "new-countries.txt"); } catch(Exception
		 * dumb) {}
		 */
	}

	boolean getOnTopSetting() {

		scriptLoader loadScripts = new scriptLoader();
		ArrayList<String> ontop = new ArrayList();
		// Andrey says:
		// want to be able to change this to:
		// List<String> ontop = new ArrayList<String>();
		loadScripts.loadScript(ontop, channels.privateDirectory + "lantern_board_on_top.txt");
		if (ontop.size() > 0) {
			String top = ontop.get(0);
			if (top.equals("true"))
				return true;
		}
		return false;
	}

	boolean getChessFontSetting() {

		return false;
		/*
		 * scriptLoader loadScripts = new scriptLoader(); ArrayList<String> ontop = new
		 * ArrayList(); // Andrey says: // want to be able to change this to:
		 * //List<String> ontop = new ArrayList<String>(); loadScripts.loadScript(ontop,
		 * "lantern_move_list_font_choice.txt"); if (ontop.size() > 0) { String top =
		 * ontop.get(0); if (top.equals("true")) return true; } else { return true; //
		 * no file its true } return false;
		 */
	}

	public void setUpLanternNotify() {
		try {
			scriptLoader loadScripts = new scriptLoader();
			ArrayList<String> notifyScript = new ArrayList();
			// Andrey says:
			// want to be able to change this to:
			// List<String> notifyScript = new ArrayList<String>();
			loadScripts.loadScript(notifyScript, "lantern_global_notify.txt");
			for (int z = 0; z < notifyScript.size(); z++) {
				String notString = notifyScript.get(z);
				try {
					int i = notString.indexOf(" ");
					if (i > 1) {// first two spaces minimum needed for name

						lanternNotifyClass temp = new lanternNotifyClass();
						temp.name = notString.substring(0, i);
						try {
							int on = Integer.parseInt(notString.substring(i + 1, notString.length() - 2));
							// -2 for the \r\n
							if (on == 1)
								temp.sound = true;
						} catch (Exception nosound) {
						}
						sharedVariables.lanternNotifyList.add(temp);
					}
					// end inner try
				} catch (Exception duii) {
				}
			} // end for
				// end try
		} catch (Exception dui) {
		}
	}// end method

	public void setUpChannelNotify() {
		try {
			scriptLoader loadScripts = new scriptLoader();
			ArrayList<String> notifyScript = new ArrayList();
			// Andrey says:
			// want to be able to change this to:
			// List<String> notifyScript = new ArrayList<String>();
			loadScripts.loadScript(notifyScript, "lantern_channel_notify.txt");
			String channel = "";
			channelNotifyClass temp = null;
			for (int z = 0; z < notifyScript.size(); z++) {
				if (notifyScript.get(z).startsWith("#")) {
					channel = notifyScript.get(z).substring(1, notifyScript.get(z).length());
					// add node
					if (temp != null) // add last channel on new channel
						sharedVariables.channelNotifyList.add(temp);

					temp = new channelNotifyClass();
					temp.channel = channel;
				} else if (!channel.equals("")) {
					temp.nameList.add(notifyScript.get(z));
				} // end else if
			} // end for
			if (temp != null && !channel.equals(""))
				if (temp.nameList.size() > 0)
					// to get last one or even first one since prior loads happen on next item
					sharedVariables.channelNotifyList.add(temp);
			// end try
		} catch (Exception dui) {
		}
	}// end method setupchannelnotify

	JCheckBoxMenuItem autoExamine; // accessed outside this class to update from telnet

	public void createMenu() {

		boardarray = new JCheckBoxMenuItem[graphics.maxBoards];
		// the whole menu bar
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);

		/****************************** File ******************************/
		JMenu myfiles = new JMenu("Connection");
		// File /
		JMenuItem reconnectFics = new JMenuItem("Reconnect to FICS");
		JMenuItem reconnect1 = new JMenuItem("Reconnect to ICC");
		JMenuItem reconnect3 = new JMenuItem("Reconnect to Queen");
		JMenuItem reconnect4 = new JMenuItem("Reconnect to ICC (alternate)");
		JMenuItem help_connecting = new JMenuItem("Anon and Guest Login Help");
		reconnect2 = new JMenuItem("Reconnect to FICS");// off now
		JMenuItem wallpaper1 = new JMenuItem("Set Background");
		JMenuItem settings2 = new JMenuItem("Save Settings");
		JMenuItem quitItem = new JMenuItem("Disconnect");

		// add shortcuts
		myfiles.setMnemonic(KeyEvent.VK_F);
		reconnect1.setMnemonic(KeyEvent.VK_R);
		reconnect4.setMnemonic(KeyEvent.VK_A);
		reconnect3.setMnemonic(KeyEvent.VK_Q);
		wallpaper1.setMnemonic(KeyEvent.VK_W);
		settings2.setMnemonic(KeyEvent.VK_S);

		// add to menu bar
		menu.add(myfiles);
		// File /
		if (!sharedVariables.fics) {
			myfiles.add(reconnect1);
			myfiles.add(reconnect4);
			myfiles.add(reconnect3);
		} else {
			myfiles.add(reconnectFics);
		}
		if (!channels.fics) {
			myfiles.addSeparator();
			myfiles.add(help_connecting);
			// myfiles.add(reconnect2);
			myfiles.addSeparator();
		}

		// myfiles.add(wallpaper1);
		myfiles.add(settings2);
		myfiles.add(quitItem);

		// add listeners
		settings2.addActionListener(this);
		reconnect1.addActionListener(this);
		reconnect4.addActionListener(this);
		reconnectFics.addActionListener(this);
		help_connecting.addActionListener(this);
		// reconnect2.addActionListener(this);
		reconnect3.addActionListener(this);
		wallpaper1.addActionListener(this);
		quitItem.addActionListener(this);

		/****************************** Colors ******************************/
		JMenu mywindowscolors = new JMenu("Appearance");
		// Colors /
		consolemenu = new JCheckBoxMenuItem("Show Console Menu Bar");
		JMenuItem fontchange = new JMenuItem("Change Font");
		JMenuItem channelcol = new JMenuItem("Channel Colors");
		JMenuItem consoleColors = new JMenuItem("Console Colors");
		JMenuItem listColor = new JMenuItem("Notify and Events Background Color");
		JMenuItem tellNameColor = new JMenuItem("PTell Name Color");
		JMenu typingarea = new JMenu("Typing Field");
		// .. / Typing Field /
		JMenuItem inputfontchange = new JMenuItem("Change Input Font");
		JMenuItem inputcommand = new JMenuItem("Input Command Color");
		JMenuItem inputchat = new JMenuItem("Input Chat Color");
		// .. /
		JMenu inChannelNamesMenu = new JMenu("In Channel Names Menu");
		// .. / In Channel Names Menu /
		JMenuItem nameForegroundColor = new JMenuItem("Names List Foreground Color");
		JMenuItem nameBackgroundColor = new JMenuItem("Names List Background Color");
		JMenuItem namelistFont = new JMenuItem("Names List Font");
		// .. /
		JMenuItem eventsFont = new JMenuItem("Event List/Tournaments Font");
		if (channels.fics) {
			eventsFont = new JMenuItem("Activities Font");
		}
		JMenuItem colortimestamp = new JMenuItem("Chat Timestamp Color");
		JMenu tabsColorsMenu = new JMenu("Tabs Colors Menu");
		// .. / Tabs Colors Menu /
		JMenu tabback = new JMenu("Tab Background");
		// .. / .. / Tab Background /
		JMenuItem tabback1 = new JMenuItem("Visited");
		JMenuItem tabback2 = new JMenuItem("Unvisited");
		JMenuItem tabback5 = new JMenuItem("Unvisited/Visited");
		JMenuItem tabimon = new JMenuItem("Tab I'm On Background");
		// .. / .. /
		JMenu tabfore = new JMenu("Tab Foreground");
		// .. / .. / Tab Foreground /
		JMenuItem tabback3 = new JMenuItem("Active");
		JMenuItem tabback4 = new JMenuItem("Non Active");
		// .. / .. /
		JMenuItem tabborder1 = new JMenuItem("Tab Border");
		JMenuItem tabborder2 = new JMenuItem("Tell Tab Border");
		JMenuItem tabfontchange = new JMenuItem("Change Tab Font");
		// .. /
		JMenuItem wallpaper2 = new JMenuItem("Set Application Background Color");

		// add shortcuts
		mywindowscolors.setMnemonic(KeyEvent.VK_C);

		// add to menu bar
		menu.add(mywindowscolors);
		// Colors /
		mywindowscolors.add(consolemenu);
		mywindowscolors.add(fontchange);
		mywindowscolors.add(channelcol);
		mywindowscolors.add(consoleColors);
		mywindowscolors.addSeparator();
		mywindowscolors.add(listColor);
		mywindowscolors.add(tellNameColor);
		mywindowscolors.add(typingarea);
		// .. / Typing Area /
		typingarea.add(inputfontchange);
		typingarea.add(inputcommand);
		typingarea.add(inputchat);
		// .. /
		// mywindowscolors.add(inChannelNamesMenu);
		// .. / In Channel Names Menu /
		inChannelNamesMenu.add(nameForegroundColor);
		inChannelNamesMenu.add(nameBackgroundColor);
		inChannelNamesMenu.add(namelistFont);
		// .. /
		mywindowscolors.add(eventsFont);
		mywindowscolors.add(colortimestamp);
		mywindowscolors.add(tabsColorsMenu);
		// .. / Tabs Colors Menu /
		tabsColorsMenu.add(tabback);
		// .. / .. / Tab Background /
		tabback.add(tabback1);
		tabback.add(tabback2);
		tabback.add(tabback5);
		tabback.add(tabimon);
		// .. / .. /
		tabsColorsMenu.add(tabfore);
		// .. / .. / Tab Foreground /
		tabfore.add(tabback3);
		tabfore.add(tabback4);
		// .. / .. /
		tabsColorsMenu.add(tabborder1);
		tabsColorsMenu.add(tabborder2);
		tabsColorsMenu.add(tabfontchange);
		// .. /
		mywindowscolors.addSeparator();
		mywindowscolors.add(wallpaper2);
		mywindowscolors.add(wallpaper1);

		// add listeners
		fontchange.addActionListener(this);
		channelcol.addActionListener(this);
		consoleColors.addActionListener(this);
		listColor.addActionListener(this);
		tellNameColor.addActionListener(this);
		inputfontchange.addActionListener(this);
		inputcommand.addActionListener(this);
		inputchat.addActionListener(this);
		nameForegroundColor.addActionListener(this);
		nameBackgroundColor.addActionListener(this);
		namelistFont.addActionListener(this);
		eventsFont.addActionListener(this);
		colortimestamp.addActionListener(this);
		tabback1.addActionListener(this);
		tabback2.addActionListener(this);
		tabback3.addActionListener(this);
		tabback4.addActionListener(this);
		tabback5.addActionListener(this);
		tabimon.addActionListener(this);
		tabborder1.addActionListener(this);
		tabborder2.addActionListener(this);
		tabfontchange.addActionListener(this);
		wallpaper2.addActionListener(this);

		// JMenuItem channelTitles = new JMenuItem("Titles In Channel Color");
		// mywindowscolors.add(channelTitles);
		// channelTitles.addActionListener(this);

		// duplicate
		// JMenuItem mainback = new JMenuItem("Main Background");
		// myfiles.add(mainback);

		// mainback.addActionListener(this);

		/****************************** Options ******************************/
		JMenu optionsmenu = new JMenu("Options");
		// Options /
		showMugshots = new JCheckBoxMenuItem("Show Profile Mugshots");

		JMenu soundmenu = new JMenu("Sound");
		// .. / Sounds /
		hearsound = new JCheckBoxMenuItem("Sounds");
		makeObserveSounds = new JCheckBoxMenuItem("Sounds for Observed Games");
		makemovesounds = new JCheckBoxMenuItem("Sounds for Moves");
		makedrawsounds = new JCheckBoxMenuItem("Sounds for Draw Offers");
		notifysound = new JCheckBoxMenuItem("Sounds for Notifications");
		correspondenceNotificationSounds = new JCheckBoxMenuItem("Sounds for Correspondence Notifications");
		maketellsounds = new JCheckBoxMenuItem("Sounds for Tells");
		makeatnamesounds = new JCheckBoxMenuItem("Sounds for @yourname");
		// .. / (separator)
		JMenuItem stockfishanalysis = new JMenuItem("Analyze with Stockfish 15");
		if (sharedVariables.operatingSystem.equals("mac")) {
			stockfishanalysis = new JMenuItem("Analyze with Stockfish 15");
		}
		JMenuItem cuckooanalysis = new JMenuItem("Analyze with CuckooChess 1.12");
		JMenuItem mediocreanalysis = new JMenuItem("Analyze with Mediocre Chess v0.5");
		JMenuItem helpanalysis = new JMenuItem("Engine Analysis Help");
		JMenuItem ucianalysis = new JMenuItem("Load UCI Engine");
		JMenuItem winanalysis = new JMenuItem("Load Winboard Engine");
		JMenuItem enginerestart = new JMenuItem("Restart Engine");
		JMenuItem enginestop = new JMenuItem("Stop Engine");
		JMenu uciMultipleMenu = new JMenu("Multiple Lines(UCI)");
		// .. / Multiple LinesDisplay /
		ucimultipleone = new JCheckBoxMenuItem("One Line(Default)");
		ucimultipletwo = new JCheckBoxMenuItem("Two Lines");
		ucimultiplethree = new JCheckBoxMenuItem("Three Lines");
		JMenu engineMenu = new JMenu("Analysis Display");
		// .. / Analysis Display /
		JMenuItem ananfont = new JMenuItem("Analysis Font");
		JMenuItem ananfore = new JMenuItem("Analysis Foreground Color");
		JMenuItem ananback = new JMenuItem("Analysis Background Color");
		JMenuItem openingbookitem = new JMenuItem("Opening Explorer");
		// .. / (separator)
		JMenuItem customizetools = new JMenuItem("Customize User Buttons");
		JMenuItem toolbox = new JMenuItem("Run a Script");
		JMenuItem toolboxhelp = new JMenuItem("Script Help");
		// .. / (separator)
		JMenu advancedOptions = new JMenu("Advanced");
		// .. / Advanced /
		JMenuItem advancedmenuhelp = new JMenuItem("Advanced Menu Help");
		qsuggestPopup = new JCheckBoxMenuItem("Qsuggest Popups");
		disableHyperlinks = new JCheckBoxMenuItem("Disable Web Hyperlinks");
		alwaysShowEdit = new JCheckBoxMenuItem("Always Show Console Edit Menu");

		channelNumberLeft = new JCheckBoxMenuItem("Channel Number On Left");
		compactNameList = new JCheckBoxMenuItem("Compact Channel Name List");
		autobufferchat = new JCheckBoxMenuItem("Auto Buffer Chat Length");
		chatbufferlarge = new JCheckBoxMenuItem("Large Chat Buffer");
		useTopGame = new JCheckBoxMenuItem("Make Boards Always On Top");
		dontReuseGameTabs = new JCheckBoxMenuItem("Don't Reuse Game Tabs");
		autopopup = new JCheckBoxMenuItem("Auto Name Popup");
		autoHistoryPopup = new JCheckBoxMenuItem("Auto History Popup");
		basketballFlag = new JCheckBoxMenuItem("Use Basketball Logo ICC Flag");
		lineindent = new JCheckBoxMenuItem("Indent Multi Line Tells");
		JMenu italicsBehaviorMenu = new JMenu("` ` Behavior");
		// .. / .. / ` ` Behavior /
		italicsBehavior[0] = new JCheckBoxMenuItem("` ` Do Nothing");
		italicsBehavior[1] = new JCheckBoxMenuItem("` ` Italics");
		italicsBehavior[2] = new JCheckBoxMenuItem("` ` Brighter Color");
		// .. /
		JMenu featuresMenu = new JMenu("Features");
		// .. / Features /
		JMenuItem featuresmenuhelp = new JMenuItem("Features Menu Help");
		tellswitch = new JCheckBoxMenuItem("Switch Tab On Tell");
		addnameontellswitch = new JCheckBoxMenuItem("Add Name On Tell Switch");
		autonoidle = new JCheckBoxMenuItem("No Idle");
		rotateaways = new JCheckBoxMenuItem("Rotate Away Message");
		iloggedon = new JCheckBoxMenuItem("Send iloggedon");
		// .. /
		JMenu observeOptions = new JMenu("Observing Options");
		// .. / Observing Options /
		JMenuItem observingmenuhelp = new JMenuItem("Observing Menu Help");
		JMenu tournieFollow = new JMenu("Follow Tomato Tournament Games");
		// .. / .. / Follow Tomato Tournament Games /
		JCheckBoxMenuItem autoflash = new JCheckBoxMenuItem("Flash");
		JCheckBoxMenuItem autocooly = new JCheckBoxMenuItem("Cooly");
		JCheckBoxMenuItem autotomato = new JCheckBoxMenuItem("Tomato");
		JCheckBoxMenuItem autowildone = new JCheckBoxMenuItem("WildOne");
		JCheckBoxMenuItem autoslomato = new JCheckBoxMenuItem("Slomato");
		JCheckBoxMenuItem autoketchup = new JCheckBoxMenuItem("Ketchup");
		JCheckBoxMenuItem autoolive = new JCheckBoxMenuItem("Olive");
		JCheckBoxMenuItem autolittleper = new JCheckBoxMenuItem("LittlePer");
		JCheckBoxMenuItem autopear = new JCheckBoxMenuItem("Pear");
		JCheckBoxMenuItem autoAutomato = new JCheckBoxMenuItem("Automato");
		JCheckBoxMenuItem autoYenta = new JCheckBoxMenuItem("Yenta");
		JCheckBoxMenuItem autouscf = new JCheckBoxMenuItem("uscf");
		// .. / .. /
		JMenu randomGraphics = new JMenu("Random Pieces Board when Observing");
		// .. / .. / Random Pieces Board when Observing /
		randomArmy = new JCheckBoxMenuItem("Random Piece Set Observe Only");
		JMenuItem configureRand = new JMenuItem("Configure Random Pieces For White");
		JMenuItem configureRandBlack = new JMenuItem("Configure Random Pieces For Black");
		randomTiles = new JCheckBoxMenuItem("Random Square Tiles Observe Only");
		JMenuItem configureRandBoards = new JMenuItem("Configure Random Square Tiles");
		// .. /
		JMenu chattimestamp = new JMenu("Chat Timestamp");
		// .. / Chat Timestamp /
		channelTimestamp = new JCheckBoxMenuItem("Timestamp Channels and Kibs");
		shoutTimestamp = new JCheckBoxMenuItem("Timestamp Shouts");
		tellTimestamp = new JCheckBoxMenuItem("Timestamp Tells and Notifications");
		leftNameTimestamp = new JCheckBoxMenuItem("Timestamp To Left Of Name");
		qtellTimestamp = new JCheckBoxMenuItem("Timestamp Channel Qtells");
		reconnectTimestamp = new JCheckBoxMenuItem("Timestamp Connecting");
		timeStamp24hr = new JCheckBoxMenuItem("Timestamp in 24hr Format");

		// add shortcuts
		optionsmenu.setMnemonic(KeyEvent.VK_O);
		soundmenu.setMnemonic(KeyEvent.VK_S);
		customizetools.setMnemonic(KeyEvent.VK_U);
		toolbox.setMnemonic(KeyEvent.VK_P);
		advancedOptions.setMnemonic(KeyEvent.VK_A);
		featuresMenu.setMnemonic(KeyEvent.VK_F);
		observeOptions.setMnemonic(KeyEvent.VK_O);
		tournieFollow.setMnemonic(KeyEvent.VK_F);
		autoflash.setMnemonic(KeyEvent.VK_F);
		autocooly.setMnemonic(KeyEvent.VK_C);
		autotomato.setMnemonic(KeyEvent.VK_T);
		autowildone.setMnemonic(KeyEvent.VK_W);
		autoslomato.setMnemonic(KeyEvent.VK_S);
		autopear.setMnemonic(KeyEvent.VK_P);
		autoketchup.setMnemonic(KeyEvent.VK_K);
		autoolive.setMnemonic(KeyEvent.VK_O);
		autolittleper.setMnemonic(KeyEvent.VK_L);
		randomGraphics.setMnemonic(KeyEvent.VK_R);
		chattimestamp.setMnemonic(KeyEvent.VK_C);

		// add keystrokes
		consolemenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));

		// add button groups
		ButtonGroup italicsBehaviorGroup = new ButtonGroup();
		italicsBehaviorGroup.add(italicsBehavior[0]);
		italicsBehaviorGroup.add(italicsBehavior[1]);
		italicsBehaviorGroup.add(italicsBehavior[2]);

		// options is added to menu bar now by game and board
		optionsmenu.add(soundmenu);
		// .. / Sound /
		soundmenu.add(hearsound);

		soundmenu.add(makeObserveSounds);
		soundmenu.add(makemovesounds);
		soundmenu.add(makeatnamesounds);
		soundmenu.add(makedrawsounds);
		soundmenu.add(notifysound);
		if (!channels.fics) {
			soundmenu.add(correspondenceNotificationSounds);
		}

		soundmenu.add(maketellsounds);
		if (!channels.fics) {
			optionsmenu.add(showMugshots);
		}

		optionsmenu.addSeparator();
		optionsmenu.add(openingbookitem);
		// .. /
		optionsmenu.addSeparator();

		JMenu javaEngines = new JMenu("Other Engines");
		boolean unixStockfishInstalled = false;
		try {
			File f = new File(sharedVariables.stockfishName);
			if (f.exists() && !f.isDirectory())
				unixStockfishInstalled = true;
		} catch (Exception duistocky) {
		}
		if (sharedVariables.operatingSystem.equals("win") || sharedVariables.operatingSystem.equals("mac")
				|| unixStockfishInstalled) {
			optionsmenu.add(stockfishanalysis);
			javaEngines.add(cuckooanalysis);
		} else {
			optionsmenu.add(cuckooanalysis);
		}
		javaEngines.add(mediocreanalysis);
		if (!channels.fics) {
			optionsmenu.add(javaEngines);
		}

		optionsmenu.addSeparator();
		if (!channels.fics) {
			optionsmenu.add(helpanalysis);

			optionsmenu.addSeparator();
		}

		optionsmenu.add(ucianalysis);
		optionsmenu.add(enginerestart);
		optionsmenu.add(enginestop);
		if (!channels.fics) {
			optionsmenu.add(winanalysis);
		}

		optionsmenu.addSeparator();
		optionsmenu.add(uciMultipleMenu);
		optionsmenu.add(engineMenu);
		// .. / Multile Linmes Display /
		uciMultipleMenu.add(ucimultipleone);
		uciMultipleMenu.add(ucimultipletwo);
		uciMultipleMenu.add(ucimultiplethree);

		// .. / Analysis Display /
		engineMenu.add(ananfont);
		engineMenu.add(ananfore);
		engineMenu.add(ananback);
		// .. /

		optionsmenu.addSeparator();
		optionsmenu.add(customizetools);
		optionsmenu.add(toolbox);
		optionsmenu.addSeparator();
		if (!channels.fics) {
			optionsmenu.add(toolboxhelp);
			optionsmenu.addSeparator();
		}

		// .. / Advanced /
		optionsmenu.add(advancedOptions);
		if (!channels.fics) {
			advancedOptions.add(advancedmenuhelp);
			advancedOptions.addSeparator();
		}

		if (!channels.fics) {
			advancedOptions.add(qsuggestPopup);
		}

		advancedOptions.add(disableHyperlinks);
		advancedOptions.add(alwaysShowEdit);

		advancedOptions.add(channelNumberLeft);
		advancedOptions.add(autobufferchat);
		advancedOptions.add(chatbufferlarge);
		advancedOptions.add(useTopGame);
		advancedOptions.add(dontReuseGameTabs);
		advancedOptions.add(autopopup);
		advancedOptions.add(autoHistoryPopup);
		if (!channels.fics) {
			advancedOptions.add(basketballFlag);
		}

		advancedOptions.add(lineindent);
		advancedOptions.add(italicsBehaviorMenu);
		// .. / .. / ` ` Behavior /
		italicsBehaviorMenu.add(italicsBehavior[0]);
		italicsBehaviorMenu.add(italicsBehavior[1]);
		italicsBehaviorMenu.add(italicsBehavior[2]);
		// .. /
		optionsmenu.add(featuresMenu);
		// .. / Features /
		if (!channels.fics) {
			featuresMenu.add(featuresmenuhelp);
			featuresMenu.addSeparator();
		}

		featuresMenu.add(tellswitch);
		featuresMenu.add(addnameontellswitch);
		featuresMenu.add(autonoidle);
		featuresMenu.add(rotateaways);
		featuresMenu.add(iloggedon);
		// .. /
		optionsmenu.add(observeOptions);
		// .. / Observing Options /
		if (!channels.fics) {
			observeOptions.add(observingmenuhelp);
			observeOptions.addSeparator();
			observeOptions.add(tournieFollow);
			// .. / .. / Follow Tomato Tournament Games /
			tournieFollow.add(autoflash);
			tournieFollow.add(autocooly);
			tournieFollow.add(autotomato);
			tournieFollow.add(autowildone);
			tournieFollow.add(autoslomato);
			tournieFollow.add(autoketchup);
			tournieFollow.add(autoolive);
			tournieFollow.add(autolittleper);
			tournieFollow.add(autopear);
			tournieFollow.add(autoAutomato);
			tournieFollow.add(autoYenta);
			tournieFollow.add(autouscf);
		}
		// .. / .. /

		// .. / .. / Random Pieces Board when Observing /
		observeOptions.add(randomArmy);
		observeOptions.addSeparator();
		observeOptions.add(configureRand);
		observeOptions.add(configureRandBlack);
		observeOptions.addSeparator();
		observeOptions.add(randomTiles);
		observeOptions.add(configureRandBoards);

		// .. /
		optionsmenu.add(chattimestamp);
		// .. / Chat Timestamp /
		chattimestamp.add(channelTimestamp);
		chattimestamp.add(shoutTimestamp);
		chattimestamp.add(tellTimestamp);
		// chattimestamp.add(leftNameTimestamp); //disabling the option
		chattimestamp.add(qtellTimestamp);
		chattimestamp.add(reconnectTimestamp);
		chattimestamp.add(timeStamp24hr);

		// special settings
		consolemenu.setSelected(true);
		channelNumberLeft.setSelected(true);
		compactNameList.setSelected(false);

		// add listeners
		showMugshots.addActionListener(this);
		makeObserveSounds.addActionListener(this);
		maketellsounds.addActionListener(this);
		makeatnamesounds.addActionListener(this);
		makemovesounds.addActionListener(this);
		makedrawsounds.addActionListener(this);
		hearsound.addActionListener(this);

		notifysound.addActionListener(this);
		correspondenceNotificationSounds.addActionListener(this);
		ucimultipleone.addActionListener(this);
		ucimultipletwo.addActionListener(this);
		ucimultiplethree.addActionListener(this);
		stockfishanalysis.addActionListener(this);
		cuckooanalysis.addActionListener(this);
		mediocreanalysis.addActionListener(this);
		helpanalysis.addActionListener(this);
		ucianalysis.addActionListener(this);
		winanalysis.addActionListener(this);
		enginerestart.addActionListener(this);
		enginestop.addActionListener(this);
		openingbookitem.addActionListener(this);
		ananfont.addActionListener(this);
		ananfore.addActionListener(this);
		ananback.addActionListener(this);
		customizetools.addActionListener(this);
		toolbox.addActionListener(this);
		toolboxhelp.addActionListener(this);
		advancedmenuhelp.addActionListener(this);
		qsuggestPopup.addActionListener(this);
		disableHyperlinks.addActionListener(this);
		alwaysShowEdit.addActionListener(this);
		consolemenu.addActionListener(this);
		channelNumberLeft.addActionListener(this);
		compactNameList.addActionListener(this);
		useTopGame.addActionListener(this);
		dontReuseGameTabs.addActionListener(this);
		autobufferchat.addActionListener(this);
		chatbufferlarge.addActionListener(this);
		autopopup.addActionListener(this);
		autoHistoryPopup.addActionListener(this);
		basketballFlag.addActionListener(this);
		lineindent.addActionListener(this);
		italicsBehavior[0].addActionListener(this);
		italicsBehavior[1].addActionListener(this);
		italicsBehavior[2].addActionListener(this);
		tellswitch.addActionListener(this);
		featuresmenuhelp.addActionListener(this);
		addnameontellswitch.addActionListener(this);
		autonoidle.addActionListener(this);
		iloggedon.addActionListener(this);
		rotateaways.addActionListener(this);
		observingmenuhelp.addActionListener(this);
		autoflash.addActionListener(this);
		autocooly.addActionListener(this);
		autotomato.addActionListener(this);
		autowildone.addActionListener(this);
		autoslomato.addActionListener(this);
		autoketchup.addActionListener(this);
		autoolive.addActionListener(this);
		autolittleper.addActionListener(this);
		autopear.addActionListener(this);
		autoAutomato.addActionListener(this);
		autoYenta.addActionListener(this);
		autouscf.addActionListener(this);
		randomArmy.addActionListener(this);
		configureRand.addActionListener(this);
		configureRandBlack.addActionListener(this);
		randomTiles.addActionListener(this);
		configureRandBoards.addActionListener(this);
		channelTimestamp.addActionListener(this);
		shoutTimestamp.addActionListener(this);
		tellTimestamp.addActionListener(this);
		// leftNameTimestamp.addActionListener(this);
		qtellTimestamp.addActionListener(this);
		reconnectTimestamp.addActionListener(this);
		timeStamp24hr.addActionListener(this);

		/****************************** Windows ******************************/
		sharedVariables.myWindows = new JMenu("Windows");
		// Windows /
		// JMenuItem nconsole = new JMenuItem("New Console");
		JMenuItem eventlist = new JMenuItem("Activities Window/Events");
		if (channels.fics) {
			eventlist = new JMenuItem("Activities Window");
		}
		JMenuItem seekingGraph = new JMenuItem("Seek Graph");
		JMenuItem mynotify = new JMenuItem("Notify Window");
		JMenuItem mytopgames = new JMenuItem("Top Games Window");
		JMenuItem windowhelp = new JMenuItem("Windows Menu Help");
		JMenuItem notifyhelp = new JMenuItem("Notify Help");

		// .. / (separator)
		JMenuItem nboard = new JMenuItem("New Board"); // we are not going to add this one anymore due to bugs
		JMenuItem rconsole = new JMenuItem("New Chat Console");
		JMenuItem detachedconsole = new JMenuItem("New Detached Chat Console");
		// .. / (separator)
		JMenuItem rconsole2 = new JMenuItem("Customize Tab");
		// JMenuItem webopener = new JMenuItem("Open Web");
		toolbarvisible = new JCheckBoxMenuItem("Show Toolbar");
		JMenuItem channelmap = new JMenuItem("Channel Map");
		JMenuItem channelnotifymap = new JMenuItem("Channel Notify Map");
		JMenuItem channelnotifyonline = new JMenuItem("Channel Notify Online");
		// JMenuItem toolbox = new JMenuItem("ToolBox");
		JMenuItem cascading = new JMenuItem("Cascade");
		// .. / (separator)
		// dynamic list of windows

		// add shortcuts
		sharedVariables.myWindows.setMnemonic(KeyEvent.VK_W);
		// eventlist.setMnemonic(KeyEvent.VK_A);
		seekingGraph.setMnemonic(KeyEvent.VK_S);
		mynotify.setMnemonic(KeyEvent.VK_N);
		nboard.setMnemonic(KeyEvent.VK_B);
		rconsole2.setMnemonic(KeyEvent.VK_U);
		channelmap.setMnemonic(KeyEvent.VK_C);
		toolbarvisible.setMnemonic(KeyEvent.VK_T);
		channelnotifymap.setMnemonic(KeyEvent.VK_M);
		channelnotifyonline.setMnemonic(KeyEvent.VK_O);

		// add keystrokes
		if (sharedVariables.operatingSystem.equals("mac")) {
			eventlist.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		} else {
			eventlist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		}

		// enginerestart.setMnemonic(KeyEvent.VK_R);

		if (sharedVariables.operatingSystem.equals("mac")) {
			enginerestart.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		} else {
			enginerestart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		}

		// enginestop.setMnemonic(KeyEvent.VK_S);

		if (sharedVariables.operatingSystem.equals("mac")) {
			enginestop.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		} else {
			enginestop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		}
		// openingbookitem.setMnemonic(KeyEvent.VK_S);

		if (sharedVariables.operatingSystem.equals("mac")) {
			openingbookitem.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		} else {
			openingbookitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		}

		// add to menu bar
		menu.add(sharedVariables.myWindows);
		// Windows /
		// sharedVariables.myWindows.add(nconsole);
		sharedVariables.myWindows.add(eventlist);

		sharedVariables.myWindows.add(seekingGraph);
		sharedVariables.myWindows.add(mynotify);
		if (!channels.fics) {
			sharedVariables.myWindows.add(mytopgames);
			sharedVariables.myWindows.addSeparator();
			sharedVariables.myWindows.add(windowhelp);
			sharedVariables.myWindows.add(notifyhelp);
		}

		sharedVariables.myWindows.addSeparator();
		// sharedVariables.myWindows.add(nboard);
		sharedVariables.myWindows.add(rconsole);
		sharedVariables.myWindows.add(detachedconsole);
		sharedVariables.myWindows.addSeparator();
		sharedVariables.myWindows.add(rconsole2);
		// sharedVariables.myWindows.add(webopener);
		sharedVariables.myWindows.add(toolbarvisible);
		sharedVariables.myWindows.add(channelmap);
		// sharedVariables.myWindows.add(channelnotifymap);
		// sharedVariables.myWindows.add(channelnotifyonline);
		// sharedVariables.myWindows.add(toolbox);
		sharedVariables.myWindows.add(cascading);
		sharedVariables.myWindows.addSeparator();

		// add listeners
		// nconsole.addActionListener(this);
		eventlist.addActionListener(this);
		seekingGraph.addActionListener(this);
		mynotify.addActionListener(this);
		mytopgames.addActionListener(this);
		windowhelp.addActionListener(this);
		notifyhelp.addActionListener(this);
		nboard.addActionListener(this);
		rconsole.addActionListener(this);
		detachedconsole.addActionListener(this);
		rconsole2.addActionListener(this);
		channelmap.addActionListener(this);
		channelnotifymap.addActionListener(this);
		channelnotifyonline.addActionListener(this);
		cascading.addActionListener(this);
		// webopener.addActionListener(this);
		toolbarvisible.addActionListener(this);
		// toolbox.addActionListener(this);

		/****************************** Board and Game ******************************/
		JMenu myboardappearancemenu = new JMenu("Board");
		JMenu myboardmenu = new JMenu("Game");
		// Game /
		JMenuItem jtournament = new JMenuItem("Join Tournaments");
		JMenuItem nseek = new JMenuItem("Seek a Game");
		JMenuItem jcorrespondence = new JMenuItem("Correspondence");
		JMenuItem nchallenge = new JMenuItem("Challenge");
		JMenuItem dorematch = new JMenuItem("Rematch");
		JMenuItem flipSent = new JMenuItem("Flip");
		JMenuItem withdrawSent = new JMenuItem("Withdraw Challenges");
		JMenuItem showexam = new JMenuItem("Enter Examination Mode");
		JMenuItem showexamlast = new JMenuItem("Examine My Last Game");
		JMenuItem unexamine = new JMenuItem("Unexamine");
		JMenuItem unfollowBroadcast = new JMenuItem("Stop Following");
		autoExamine = new JCheckBoxMenuItem("Auto Examine After Playing");
		JMenu boardDesign = new JMenu("Board Design");
		// .. / Board Design /
		boarddesignarray[0] = new JCheckBoxMenuItem("Original");
		boarddesignarray[1] = new JCheckBoxMenuItem("Modern");
		boarddesignarray[2] = new JCheckBoxMenuItem("Mixed");
		// .. /

		JMenu selectboards = new JMenu("Boards");
		// .. / Boards /
		boardarray[0] = new JCheckBoxMenuItem("Solid Color Board");
		boardarray[1] = new JCheckBoxMenuItem("Pale Wood");
		boardarray[2] = new JCheckBoxMenuItem("Light Wood");
		boardarray[3] = new JCheckBoxMenuItem("Dark Wood");
		boardarray[4] = new JCheckBoxMenuItem("Gray Marble");
		boardarray[5] = new JCheckBoxMenuItem("Red Marble");
		boardarray[6] = new JCheckBoxMenuItem("Crampled Paper");
		boardarray[7] = new JCheckBoxMenuItem("Winter");
		boardarray[8] = new JCheckBoxMenuItem("Olive Board");
		boardarray[9] = new JCheckBoxMenuItem("Cherry Board");
		boardarray[10] = new JCheckBoxMenuItem("Purple Board");
		boardarray[11] = new JCheckBoxMenuItem("Wood-4");
		boardarray[12] = new JCheckBoxMenuItem("Wood-5");
		boardarray[13] = new JCheckBoxMenuItem("Cold Marble");
		boardarray[14] = new JCheckBoxMenuItem("Green Marble");
		boardarray[15] = new JCheckBoxMenuItem("Slate");
		boardarray[16] = new JCheckBoxMenuItem("Thief");
		boardarray[17] = new JCheckBoxMenuItem("Tournament Mat");
		boardarray[18] = new JCheckBoxMenuItem("Tournament Mat2");
		boardarray[19] = new JCheckBoxMenuItem("Wood-6");
		boardarray[20] = new JCheckBoxMenuItem("Wood-7");
		boardarray[21] = new JCheckBoxMenuItem("Wood-8");
		// .. / .. / (separator)
		JMenu preset = new JMenu("Preset Color Boards");
		// .. / .. / Preset Color Boards /
		presetarray[0] = new JMenuItem("Default Board");
		presetarray[1] = new JMenuItem("Tan Board");
		presetarray[2] = new JMenuItem("Gray Color Board");
		presetarray[3] = new JMenuItem("Blitzin Green Board");
		// .. /
		JMenu selectpieces = new JMenu("Pieces");
		// .. / Pieces /
		piecesarray[0] = new JCheckBoxMenuItem("Dyche1");
		piecesarray[1] = new JCheckBoxMenuItem("Dyche2");
		piecesarray[2] = new JCheckBoxMenuItem("Dyche3");
		piecesarray[3] = new JCheckBoxMenuItem("Bookup");
		piecesarray[4] = new JCheckBoxMenuItem("Xboard");
		piecesarray[5] = new JCheckBoxMenuItem("Alpha");
		piecesarray[18] = new JCheckBoxMenuItem("Adventure");
		piecesarray[19] = new JCheckBoxMenuItem("Maya");
		piecesarray[20] = new JCheckBoxMenuItem("Medieval");
		JMenu mongeMenu = new JMenu("Monge");
		// .. / .. / Monge /
		piecesarray[6] = new JCheckBoxMenuItem("Spatial");
		piecesarray[13] = new JCheckBoxMenuItem("Eyes");
		piecesarray[14] = new JCheckBoxMenuItem("Fantasy");
		piecesarray[25] = new JCheckBoxMenuItem("Monge Mix");
		JMenuItem aboutmonge = new JMenuItem("About Monge Pieces");
		// .. / .. /
		piecesarray[7] = new JCheckBoxMenuItem("Harlequin");
		piecesarray[8] = new JCheckBoxMenuItem("Berlin");
		piecesarray[9] = new JCheckBoxMenuItem("Eboard Classic");
		piecesarray[15] = new JCheckBoxMenuItem("Line");
		piecesarray[16] = new JCheckBoxMenuItem("Motif");
		piecesarray[17] = new JCheckBoxMenuItem("Utrecht");
		JMenu moltenmenu = new JMenu("Molten");
		// .. / .. / Molten /
		piecesarray[10] = new JCheckBoxMenuItem("Molten Good");
		piecesarray[11] = new JCheckBoxMenuItem("Molten Evil");
		piecesarray[12] = new JCheckBoxMenuItem("Liebeskind");
		// .. / .. /
		piecesarray[21] = new JCheckBoxMenuItem("CCube");
		piecesarray[22] = new JCheckBoxMenuItem("Merida");
		piecesarray[23] = new JCheckBoxMenuItem("Kingdom");
		piecesarray[24] = new JCheckBoxMenuItem("Alpha-2");
		piecesarray[26] = new JCheckBoxMenuItem("Random Pieces");
		// .. /

		JMenu checkersselectpieces = new JMenu("Checkers Pieces");
		// .. / Pieces /
		checkerspiecesarray[0] = new JCheckBoxMenuItem("Black-Red");
		checkerspiecesarray[1] = new JCheckBoxMenuItem("Black-White");

		JMenu consoleaspect = new JMenu("Board Console");
		// .. / Board Console /
		boardconsolearray[0] = new JCheckBoxMenuItem("Hide Board Console");
		boardconsolearray[1] = new JCheckBoxMenuItem("Compact Board Console");
		boardconsolearray[2] = new JCheckBoxMenuItem("Normal Board Console");
		boardconsolearray[3] = new JCheckBoxMenuItem("Larger Board Console");
		// .. / .. / (separator)
		sidewaysconsole = new JCheckBoxMenuItem("Console On Side");
		sidewaysconsolemax = new JCheckBoxMenuItem("Console On Side when Maximized");
		bottomconsole = new JCheckBoxMenuItem("Console On Bottom");

		JMenu boardSquareColors = new JMenu("Board Squares Colors");
		// .. / Board Squares Colors /
		JMenuItem lcolor = new JMenuItem("Light Square Color");
		JMenuItem dcolor = new JMenuItem("Dark Square Color");
		// .. /
		JMenu boardColors = new JMenu("Board Colors");
		// .. / Board Colors /
		JMenuItem bbackcolor = new JMenuItem("Board Background Color");
		JMenuItem bforcolor = new JMenuItem("Board Foreground Color");
		JMenuItem cforcolor = new JMenuItem("Clock Foreground Color");
		JMenuItem bcbackcolor = new JMenuItem("Board Clock Background Color");
		JMenuItem highlightcolor = new JMenuItem("Highlight Moves Color");
		JMenuItem scrollhighlightcolor = new JMenuItem("Scroll Back Highlight Color");
		// .. /
		JMenu boardFonts9 = new JMenu("Board Fonts");
		// .. / Board Fonts /
		JMenuItem gamefont = new JMenuItem("Game Board Font");
		JMenuItem gameclockfont = new JMenuItem("Game Clock Font");
		// .. /
		JMenu theHideMenu = new JMenu("Things to Hide or Show");
		// .. / Things to Hide or Show /
		highlight = new JCheckBoxMenuItem("Highlight Moves");
		materialCount = new JCheckBoxMenuItem("Material Count");
		drawCoordinates = new JCheckBoxMenuItem("Draw Coordinates");
		showPallette = new JCheckBoxMenuItem("Show Examine Mode Palette");
		showFlags = new JCheckBoxMenuItem("Show Flags");
		showRatings = new JCheckBoxMenuItem("Show Ratings on Board When Playing");
		playersInMyGame = new JCheckBoxMenuItem("Show Observers In Games");
		useLightBackground = new JCheckBoxMenuItem("Use Light Square as Board Background");
		// chessFontForMoveList = new JCheckBoxMenuItem("Chess Font For Move List");
		// .. /
		JMenu examReplay = new JMenu("Examine Game Replay");
		// .. / Examine Game Replay /
		JMenuItem autoset = new JMenuItem("AutoExam Dialog");
		JMenuItem whatexaminereplay = new JMenuItem("What's Examine Game Replay Quick Help");
		// .. /
		autoPromote = new JCheckBoxMenuItem("Auto Promote");
		JMenu moveInputMenu = new JMenu("Move Input");
		// ../ Move Input /
		dragMoveInput = new JCheckBoxMenuItem("Drag Move");
		clickMoveInput = new JCheckBoxMenuItem("Click Click");
		moveInputMenu.add(dragMoveInput);
		moveInputMenu.add(clickMoveInput);
		// .. /

		JMenuItem opengamefiles = new JMenuItem("Open Game Files on Computer");
		JMenu PgnMenu = new JMenu("PGN");
		// .. / PGN /
		pgnlogging = new JCheckBoxMenuItem("Log Pgn");
		pgnObservedLogging = new JCheckBoxMenuItem("Log Observed Games To Pgn");
		JMenuItem openpgn = new JMenuItem("Open Pgn");
		// .. /
		JMenu Communications = new JMenu("Communications");
		// .. / Communications /
		blockSays = new JCheckBoxMenuItem("Block Opponents Says When Not Playing");
		gameend = new JCheckBoxMenuItem("Send Game End Messages");
		autoChat = new JCheckBoxMenuItem("AutoChat");
		// .. /
		JMenu AdvancedGameMenu = new JMenu("Advanced");
		// .. / Advanced /
		lowTimeColors = new JCheckBoxMenuItem("Low Time Clock Colors (Bullet Only)");
		checkLegality = new JCheckBoxMenuItem("Check Move Legality");
		noFocusOnObserve = new JCheckBoxMenuItem("No Focus on Observing");
		unobserveGoExamine = new JCheckBoxMenuItem("Unobserve Games Gone Examine");
		newObserveGameSwitch = new JCheckBoxMenuItem("Switch To New Game Tab On Observe");
		tabbing = new JCheckBoxMenuItem("Tabs Only");
		// .. / .. / Board Aspect Ratio /
		JMenu aspect = new JMenu("Board Aspect Ratio");
		aspectarray[0] = new JCheckBoxMenuItem("1:1");
		aspectarray[1] = new JCheckBoxMenuItem("5:4");
		aspectarray[2] = new JCheckBoxMenuItem("4:3");
		aspectarray[3] = new JCheckBoxMenuItem("3:2");
		// .. /
		JMenu bottom_console_menu = new JMenu("Bottom Console Size");
		JMenuItem help_board_advanced = new JMenuItem("Board Advanced Menu Help");
		JMenuItem help_pgn = new JMenuItem("PGN Menu Help");
		JMenuItem help_game_communication = new JMenuItem("Game Communication Menu Help");
		JMenuItem help_getting_game = new JMenuItem("Getting a Game Help");
		JMenuItem help_correspondence = new JMenuItem("Correspondence Help");
		JMenuItem help_tournaments = new JMenuItem("Tournament Help");
		JMenuItem help_tournament_schedule = new JMenuItem("Tournament Schedule");
		JMenuItem help_customizing_board = new JMenuItem("Customizing Board Help");
		// add shortcuts
		myboardmenu.setMnemonic(KeyEvent.VK_G);
		flipSent.setMnemonic(KeyEvent.VK_F);
		selectboards.setMnemonic(KeyEvent.VK_B);
		selectpieces.setMnemonic(KeyEvent.VK_P);
		AdvancedGameMenu.setMnemonic(KeyEvent.VK_A);

		// add button groups
		ButtonGroup boarddesigngroup = new ButtonGroup();
		for (int i = 0; i < boarddesignarray.length; i++)
			boarddesigngroup.add(boarddesignarray[i]);

		ButtonGroup boardgroup = new ButtonGroup();

		for (int i = 0; i < boardarray.length; i++) {
			boardgroup.add(boardarray[i]);
		}
		ButtonGroup piecesgroup = new ButtonGroup();
		for (int i = 0; i < piecesarray.length; i++)
			piecesgroup.add(piecesarray[i]);

		ButtonGroup aspectgroup = new ButtonGroup();
		for (int i = 0; i < aspectarray.length; i++)
			aspectgroup.add(aspectarray[i]);

		ButtonGroup boardconsolegroup = new ButtonGroup();
		for (int i = 0; i < boardconsolearray.length; i++)
			boardconsolegroup.add(boardconsolearray[i]);

		// add to menu bar
		menu.add(myboardappearancemenu);
		menu.add(myboardmenu);
		// Game /
		// add to menu bar
		menu.add(optionsmenu);
		// Options /
		if (!channels.fics) {
			myboardmenu.add(jtournament);
		}

		myboardmenu.add(nseek);
		if (!channels.fics) {
			myboardmenu.add(jcorrespondence);
		}

		myboardmenu.add(nchallenge);
		myboardmenu.add(dorematch);
		myboardmenu.add(withdrawSent);
		myboardmenu.add(unfollowBroadcast);
		if (!channels.fics) {
			myboardmenu.add(autoExamine);
		}

		if (!channels.fics) {
			myboardmenu.addSeparator();
			myboardmenu.add(help_getting_game);
			// myboardmenu.add(help_correspondence);
			myboardmenu.add(help_tournaments);
			myboardmenu.add(help_tournament_schedule);
		}

		myboardmenu.addSeparator();
		myboardmenu.add(flipSent);
		myboardmenu.add(showexam);
		myboardmenu.add(showexamlast);
		myboardmenu.add(unexamine);
		myboardmenu.addSeparator();
		myboardmenu.add(autoPromote);
		myboardmenu.add(moveInputMenu);

		myboardappearancemenu.add(boardDesign);
		// .. / Board Design /
		for (int i = 0; i < boarddesignarray.length; i++)
			boardDesign.add(boarddesignarray[i]);
		// .. /

		myboardappearancemenu.add(selectboards);
		// .. / Boards /
		for (int i = 0; i < boardarray.length - 11; i++) {
			selectboards.add(boardarray[i]);
			if (i == 3) // dark wood
			{
				selectboards.add(boardarray[11]);
				selectboards.add(boardarray[12]);
				selectboards.add(boardarray[19]);
				selectboards.add(boardarray[20]);
				selectboards.add(boardarray[21]);
			}

		}
		for (int i = boardarray.length - 9; i < boardarray.length - 3; i++) {
			selectboards.add(boardarray[i]);
		}
		selectboards.addSeparator();
		selectboards.add(preset);
		// .. / .. / Preset Color Boards /
		for (int i = 0; i < presetarray.length; i++)
			preset.add(presetarray[i]);
		// .. /
		myboardappearancemenu.add(selectpieces);
		if (!channels.fics) {
			myboardappearancemenu.add(checkersselectpieces);
		}

		myboardappearancemenu.addSeparator();
		// .. / Pieces /
		selectpieces.add(piecesarray[4]);
		selectpieces.add(piecesarray[5]);
		selectpieces.add(piecesarray[24]);
		JMenu dycheMenu = new JMenu("Dyche");
		for (int i = 0; i < 3; i++)
			dycheMenu.add(piecesarray[i]);
		selectpieces.add(dycheMenu);
		selectpieces.add(piecesarray[21]);
		selectpieces.add(piecesarray[22]);

		selectpieces.add(mongeMenu);
		// .. / .. / Monge /
		mongeMenu.add(piecesarray[14]);
		mongeMenu.add(piecesarray[6]);
		mongeMenu.add(piecesarray[13]);
		mongeMenu.add(piecesarray[25]);
		mongeMenu.add(aboutmonge);
		for (int i = 18; i < 21; i++)
			selectpieces.add(piecesarray[i]);
		// .. / .. /
		for (int i = 7; i < 10; i++)
			selectpieces.add(piecesarray[i]);
		for (int i = 15; i < 18; i++)
			selectpieces.add(piecesarray[i]);

		// .. / .. /

		selectpieces.add(piecesarray[23]);
		selectpieces.add(piecesarray[3]);
		selectpieces.add(moltenmenu);
		// .. / .. / Molten /
		for (int i = 10; i < 13; i++)
			moltenmenu.add(piecesarray[i]);
		selectpieces.add(piecesarray[26]);

		checkersselectpieces.add(checkerspiecesarray[0]);
		checkersselectpieces.add(checkerspiecesarray[1]);
		// .. /
		myboardappearancemenu.add(theHideMenu);
		// .. / Things to Hide or Show /
		theHideMenu.add(highlight);
		theHideMenu.add(materialCount);
		theHideMenu.add(drawCoordinates);
		theHideMenu.add(showPallette);
		if (!channels.fics) {
			theHideMenu.add(showFlags);
		}

		theHideMenu.add(showRatings);
		if (!channels.fics) {
			theHideMenu.add(playersInMyGame);
		}

		// theHideMenu.add(chessFontForMoveList);

		myboardappearancemenu.add(consoleaspect);
		// .. / Board Console /
		consoleaspect.add(bottom_console_menu);
		for (int i = 0; i < boardconsolearray.length; i++)
			bottom_console_menu.add(boardconsolearray[i]);
		consoleaspect.addSeparator();
		consoleaspect.add(sidewaysconsole);
		consoleaspect.add(sidewaysconsolemax);
		consoleaspect.add(bottomconsole);

		myboardappearancemenu.add(boardSquareColors);
		// .. / Board Squares Colors /
		boardSquareColors.add(lcolor);
		boardSquareColors.add(dcolor);
		// .. /
		myboardappearancemenu.add(boardColors);
		// .. / Board Colors /
		boardColors.add(bbackcolor);
		boardColors.add(bforcolor);
		boardColors.add(cforcolor);
		boardColors.add(bcbackcolor);
		boardColors.add(highlightcolor);
		boardColors.add(scrollhighlightcolor);
		// .. /
		myboardappearancemenu.add(boardFonts9);
		// .. / Board Fonts /
		boardFonts9.add(gamefont);
		boardFonts9.add(gameclockfont);
		if (!channels.fics) {
			myboardappearancemenu.addSeparator();
			myboardappearancemenu.add(help_customizing_board);
		}

		myboardappearancemenu.addSeparator();
		myboardappearancemenu.add(AdvancedGameMenu);

		// .. /
		myboardmenu.addSeparator();
		// myboardmenu.add(useLightBackground); // disabled
		// .. /
		myboardmenu.add(opengamefiles);
		myboardmenu.add(PgnMenu);
		// .. / PGN /
		if (!channels.fics) {
			PgnMenu.add(help_pgn);
			PgnMenu.addSeparator();
		}

		PgnMenu.add(pgnlogging);
		if (!channels.fics) {
			PgnMenu.add(pgnObservedLogging);
		}
		PgnMenu.add(openpgn);
		// .. /
		myboardmenu.add(examReplay);
		// .. / Examine Game Replay /
		examReplay.add(autoset);
		examReplay.add(whatexaminereplay);
		// .. /

		myboardmenu.add(Communications);
		// .. / Communications /
		if (!channels.fics) {
			Communications.add(help_game_communication);
			Communications.addSeparator();
		}
		if (!channels.fics) {
			Communications.add(blockSays);
		}

		Communications.add(gameend);
		Communications.add(autoChat);
		// .. /
		if (!channels.fics) {
			AdvancedGameMenu.add(help_board_advanced);
			AdvancedGameMenu.addSeparator();
		}

		// .. / Advanced /
		if (!channels.fics) {
			AdvancedGameMenu.add(lowTimeColors);
		}

		AdvancedGameMenu.add(checkLegality);
		if (!channels.fics) {
			AdvancedGameMenu.add(unobserveGoExamine);
		}

		AdvancedGameMenu.add(newObserveGameSwitch);
		AdvancedGameMenu.add(noFocusOnObserve);
		AdvancedGameMenu.add(tabbing);
		AdvancedGameMenu.add(aspect);
		// .. / .. / Board Aspect Ratio /
		for (int i = 0; i < aspectarray.length; i++)
			aspect.add(aspectarray[i]);
		// .. /

		// special settings
		// highlight.setSelected(true);
		// woodenboard2.setSelected(true);
		// pieces1.setSelected(true);
		// pgnlogging.setSelected(true);
		// pgnObservedLogging.setSelected(true);
		aspectarray[0].setSelected(true);
		boardconsolearray[2].setSelected(true);
		// sidewaysconsole.setSelected(false);

		// add listeners
		jtournament.addActionListener(this);
		jcorrespondence.addActionListener(this);
		nseek.addActionListener(this);
		nchallenge.addActionListener(this);
		dorematch.addActionListener(this);
		flipSent.addActionListener(this);
		withdrawSent.addActionListener(this);
		for (int i = 0; i < boarddesignarray.length; i++)
			boarddesignarray[i].addActionListener(this);
		tabbing.addActionListener(this);
		for (int i = 0; i < boardarray.length; i++)
			boardarray[i].addActionListener(this);
		/*
		 * solidboard.addActionListener(this); woodenboard1.addActionListener(this);
		 * woodenboard2.addActionListener(this); woodenboard3.addActionListener(this);
		 * grayishboard.addActionListener(this); board5.addActionListener(this);
		 * board6.addActionListener(this); board7.addActionListener(this);
		 * oliveboard.addActionListener(this); cherryboard.addActionListener(this);
		 * purpleboard.addActionListener(this);
		 */

		for (int i = 0; i < piecesarray.length; i++)
			piecesarray[i].addActionListener(this);
		for (int i = 0; i < checkerspiecesarray.length; i++)
			checkerspiecesarray[i].addActionListener(this);
		/*
		 * pieces1.addActionListener(this); pieces2.addActionListener(this);
		 * pieces3.addActionListener(this); pieces4.addActionListener(this);
		 * pieces5.addActionListener(this); pieces6.addActionListener(this);
		 * pieces7.addActionListener(this); pieces8.addActionListener(this);
		 * pieces9.addActionListener(this); pieces10.addActionListener(this);
		 * pieces11.addActionListener(this); pieces12.addActionListener(this);
		 * pieces13.addActionListener(this); pieces14.addActionListener(this);
		 * pieces15.addActionListener(this); pieces16.addActionListener(this);
		 * pieces17.addActionListener(this); pieces18.addActionListener(this);
		 * pieces19.addActionListener(this); pieces20.addActionListener(this);
		 * pieces21.addActionListener(this); pieces22.addActionListener(this);
		 * pieces23.addActionListener(this); pieces24.addActionListener(this);
		 */
		aboutmonge.addActionListener(this);
		for (int i = 0; i < presetarray.length; i++)
			presetarray[i].addActionListener(this);
		/*
		 * preset0.addActionListener(this); preset1.addActionListener(this);
		 * preset2.addActionListener(this); preset3.addActionListener(this);
		 */
		help_customizing_board.addActionListener(this);
		help_pgn.addActionListener(this);
		help_game_communication.addActionListener(this);
		help_getting_game.addActionListener(this);
		help_correspondence.addActionListener(this);
		help_tournaments.addActionListener(this);
		help_tournament_schedule.addActionListener(this);
		help_board_advanced.addActionListener(this);
		showexam.addActionListener(this);
		showexamlast.addActionListener(this);
		unexamine.addActionListener(this);
		unfollowBroadcast.addActionListener(this);
		autoExamine.addActionListener(this);
		lcolor.addActionListener(this);
		dcolor.addActionListener(this);
		bbackcolor.addActionListener(this);
		bcbackcolor.addActionListener(this);
		bforcolor.addActionListener(this);
		cforcolor.addActionListener(this);
		highlightcolor.addActionListener(this);
		scrollhighlightcolor.addActionListener(this);
		gamefont.addActionListener(this);
		gameclockfont.addActionListener(this);
		materialCount.addActionListener(this);
		drawCoordinates.addActionListener(this);
		showPallette.addActionListener(this);
		showFlags.addActionListener(this);
		// chessFontForMoveList.addActionListener(this);
		showRatings.addActionListener(this);
		playersInMyGame.addActionListener(this);
		// useLightBackground.addActionListener(this);
		whatexaminereplay.addActionListener(this);
		autoset.addActionListener(this);
		autoPromote.addActionListener(this);
		dragMoveInput.addActionListener(this);
		clickMoveInput.addActionListener(this);
		pgnlogging.addActionListener(this);
		pgnObservedLogging.addActionListener(this);
		openpgn.addActionListener(this);
		opengamefiles.addActionListener(this);
		blockSays.addActionListener(this);
		gameend.addActionListener(this);
		autoChat.addActionListener(this);
		lowTimeColors.addActionListener(this);
		checkLegality.addActionListener(this);
		noFocusOnObserve.addActionListener(this);
		unobserveGoExamine.addActionListener(this);
		newObserveGameSwitch.addActionListener(this);
		for (int i = 0; i < aspectarray.length; i++)
			aspectarray[i].addActionListener(this);
		for (int i = 0; i < boardconsolearray.length; i++)
			boardconsolearray[i].addActionListener(this);
		sidewaysconsole.addActionListener(this);
		sidewaysconsolemax.addActionListener(this);
		bottomconsole.addActionListener(this);
		highlight.addActionListener(this);

		/****************************** Actions ******************************/
		JMenu actionsmenu = new JMenu("Actions");
		// Actions /
		JMenuItem showhistory = new JMenuItem("Show My Recent Games");
		JMenuItem showlib = new JMenuItem("Show My Game Library");
		if (channels.fics) {
			showlib = new JMenuItem("Show My Journal");
		}
		JMenuItem showstored = new JMenuItem("Show My Adjourned Games");
		JMenuItem showcorrespondence = new JMenuItem("Show My Correspondence Games");
		JMenuItem showlogfile = new JMenuItem("Show My Game Log File");
		// .. / (separator)
		JMenuItem showStore = new JMenuItem("ICC Store");
		JMenuItem showMyICC = new JMenuItem("My ICC");

		JMenuItem lookupuser = new JMenuItem("Lookup User");
		JMenuItem showfinger = new JMenuItem("My Profile and Ratings");
		JMenuItem addfriend = new JMenuItem("Add a Friend");

		// .. / (separator)
		JMenuItem showobs = new JMenuItem("Observe High Rated Game");
		JMenuItem showobs5 = new JMenuItem("Observe High Rated 5-Minute Game");
		if (channels.fics) {
			showobs5 = new JMenuItem("Observe High Rated Blitz Game");
		}
		JMenuItem showobs15 = new JMenuItem("Observe High Rated 15-Minute Game");
		if (channels.fics) {
			showobs15 = new JMenuItem("Observe High Rated Standard Game");
		}
		// .. / (separator)
		JMenuItem showtitled = new JMenuItem("Show Titled Players Online in M0 Tab");
		JMenuItem showrelay = new JMenuItem("Show Relay Schedule");
		JMenuItem ratinggraph = new JMenuItem("Show Rating Graphs");
		// .. / (separator)
		JMenuItem broadcasthelp = new JMenuItem("ICC Calendar");
		JMenuItem servertime = new JMenuItem("Server Time");
		JMenuItem followBroadcast = new JMenuItem("Follow Broadcast- When On");
		JMenuItem showfm = new JMenuItem("Open Videos Page");

		// add shortcuts
		actionsmenu.setMnemonic(KeyEvent.VK_A);

		menu.add(actionsmenu);
		// Actions /
		actionsmenu.add(showhistory);
		actionsmenu.add(showlib);
		actionsmenu.add(showstored);
		if (!channels.fics) {
			actionsmenu.add(showcorrespondence);
		}
		actionsmenu.add(showlogfile);
		if (!channels.fics) {
			actionsmenu.addSeparator();
			actionsmenu.add(showStore);
			actionsmenu.add(showMyICC);

		}
		actionsmenu.addSeparator();
		actionsmenu.add(showfinger);
		actionsmenu.add(lookupuser);
		actionsmenu.add(addfriend);
		actionsmenu.addSeparator();
		actionsmenu.add(showobs);
		actionsmenu.add(showobs5);
		actionsmenu.add(showobs15);
		if (!channels.fics) {
			actionsmenu.addSeparator();
			actionsmenu.add(showtitled);
			actionsmenu.add(showrelay);
			actionsmenu.add(ratinggraph);
			actionsmenu.addSeparator();
			actionsmenu.add(servertime);
			actionsmenu.add(showfm);
		}

		lookupuser.addActionListener(this);
		showhistory.addActionListener(this);
		showlib.addActionListener(this);
		showstored.addActionListener(this);
		showcorrespondence.addActionListener(this);
		showlogfile.addActionListener(this);
		showStore.addActionListener(this);
		showMyICC.addActionListener(this);

		showfinger.addActionListener(this);
		showobs.addActionListener(this);
		showobs5.addActionListener(this);
		showobs15.addActionListener(this);
		showtitled.addActionListener(this);
		showrelay.addActionListener(this);
		ratinggraph.addActionListener(this);
		addfriend.addActionListener(this);
		followBroadcast.addActionListener(this);
		broadcasthelp.addActionListener(this);
		servertime.addActionListener(this);
		showfm.addActionListener(this);

		/****************************** Help ******************************/
		JMenu helpmenu = new JMenu("Help");
		// Help /
		JMenuItem joinrenewhelp = new JMenuItem("Join");
		JMenuItem helpdiscount = new JMenuItem("Help Discount");
		JMenuItem iccstore2 = new JMenuItem("ICC Store");
		JMenuItem passwordhelp = new JMenuItem("Lost Password");
		JMenuItem chesscoaches = new JMenuItem("Chess Coaches");
		JMenuItem askaquestion = new JMenuItem("Ask A Question");
		JMenuItem lanternmanual = new JMenuItem("Lantern Help Index");
		JMenuItem changelog = new JMenuItem("Change Log");
		JMenuItem privacypolicy = new JMenuItem("Privacy Policy");
		JMenuItem calendaritem = new JMenuItem("ICC Calendar");
		JMenuItem infohelp = new JMenuItem("ICC Information Help Files");
		JMenuItem commandhelp = new JMenuItem("ICC Command Help");

		JMenu poweroutmenu = new JMenu("Extra-games");
		// .. / Extra-games
		JMenuItem power = new JMenuItem("Start Powerout");
		JMenuItem mines = new JMenuItem("Start MineSweeper");
		JMenuItem mastermind = new JMenuItem("Start Mastermind");
		JMenuItem startfour = new JMenuItem("Start Connect Four");

		// add shortcut
		helpmenu.setMnemonic(KeyEvent.VK_H);
		lanternmanual.setMnemonic(KeyEvent.VK_M);
		changelog.setMnemonic(KeyEvent.VK_C);

		// add to menu bar
		menu.add(helpmenu);
		// Help /
		helpmenu.add(joinrenewhelp);
		if (!channels.fics) {
			helpmenu.add(helpdiscount);
		}
		helpmenu.add(passwordhelp);
		if (!channels.fics) {
			helpmenu.add(iccstore2);
			helpmenu.add(chesscoaches);
			helpmenu.addSeparator();
		}
		if (!channels.fics) {
			helpmenu.add(lanternmanual);
			helpmenu.add(changelog);
		}

		helpmenu.add(privacypolicy);
		if (!channels.fics) {

			helpmenu.add(calendaritem);
			helpmenu.add(askaquestion);
			helpmenu.addSeparator();
			// helpmenu.add(infohelp);
			helpmenu.add(commandhelp);
			helpmenu.addSeparator();
		}

		helpmenu.add(poweroutmenu);
		// .. / Extra-games
		poweroutmenu.add(power);
		poweroutmenu.add(mines);
		poweroutmenu.add(mastermind);
		poweroutmenu.add(startfour);

		// add listener
		askaquestion.addActionListener(this);
		lanternmanual.addActionListener(this);
		changelog.addActionListener(this);
		privacypolicy.addActionListener(this);
		calendaritem.addActionListener(this);
		infohelp.addActionListener(this);
		commandhelp.addActionListener(this);
		joinrenewhelp.addActionListener(this);
		helpdiscount.addActionListener(this);
		passwordhelp.addActionListener(this);
		chesscoaches.addActionListener(this);
		iccstore2.addActionListener(this);
		power.addActionListener(this);
		mines.addActionListener(this);
		mastermind.addActionListener(this);
		startfour.addActionListener(this);
	}

	public void stateChanged(ChangeEvent e) {

		for (int i = 0; i < sharedVariables.openConsoleCount; i++) {
			if (consoles[i] != null) {
				if (colortype == 1) {
					Color newColor = tcc.getColor();
					consoles[i].setForeground(newColor);
				} else if (colortype == 2) {
					Color newColor = tcc.getColor();
					consoles[i].setBackground(newColor);
				}
			} // end if not null
		} // end for
	}// end method

	public void actionPerformed(ActionEvent event) {
		// Object source = event.getSource();
		// handle action event here
		// Andrey edits:
		String action = event.getActionCommand();
		// and replaces "event.getActionCommand()" with "action" below

		if (action.equals("Single Rows of Tabs")) {
			sharedVariables.consoleLayout = 1;
			resetConsoleLayout();

		} else if (action.equals("No Visible Tabs")) {
			sharedVariables.consoleLayout = 3;
			resetConsoleLayout();

		} else if (action.equals("Two Rows of Tabs")) {
			sharedVariables.consoleLayout = 2;
			resetConsoleLayout();

		} else if (action.equals("Indent Multi Line Tells")) {
			sharedVariables.indent = !sharedVariables.indent;
			lineindent.setSelected(sharedVariables.indent);

		} else if (action.equals("Check Move Legality")) {
			sharedVariables.checkLegality = !sharedVariables.checkLegality;
			checkLegality.setSelected(sharedVariables.checkLegality);

		} else if (action.equals("No Focus on Observing")) {
			sharedVariables.noFocusOnObserve = !sharedVariables.noFocusOnObserve;
			checkLegality.setSelected(sharedVariables.noFocusOnObserve);

		}

		else if (action.equals("Unobserve Games Gone Examine")) {
			sharedVariables.unobserveGoExamine = !sharedVariables.unobserveGoExamine;
			unobserveGoExamine.setSelected(sharedVariables.unobserveGoExamine);

		} else if (action.equals("Compact Channel Name List")) {
			sharedVariables.compactNameList = !sharedVariables.compactNameList;
			sharedVariables.nameListSize = (sharedVariables.compactNameList ? 65 : 90);
			compactNameList.setSelected(sharedVariables.compactNameList);

			try {
				for (int iii = 0; iii < sharedVariables.maxConsoleTabs; iii++) {
					if (consoleSubframes[iii] != null) {
						consoleSubframes[iii].overall.recreate(sharedVariables.consolesTabLayout[iii]);
					}
				} // end for
					// end try
			} catch (Exception namebad) {
			}

		} else if (action.equals("Channel Number On Left")) {
			sharedVariables.channelNumberLeft = !sharedVariables.channelNumberLeft;
			channelNumberLeft.setSelected(sharedVariables.channelNumberLeft);

		} else if (action.equals("Show Console Menu Bar")) {
			sharedVariables.showConsoleMenu = !sharedVariables.showConsoleMenu;
			consolemenu.setSelected(sharedVariables.showConsoleMenu);

			try {
				for (int bam = 0; bam < sharedVariables.openConsoleCount; bam++)
					if (sharedVariables.showConsoleMenu) {
						consoleSubframes[bam].setJMenuBar(consoleSubframes[bam].consoleMenu);
						consoleSubframes[bam].consoleMenu.revalidate();
					} else {
						consoleSubframes[bam].setJMenuBar(consoleSubframes[bam].consoleEditMenu);
						consoleSubframes[bam].consoleEditMenu.revalidate();
						consoleSubframes[bam].consoleEditMenu.setVisible(sharedVariables.alwaysShowEdit);
					}
				// consoleSubframes[bam].consoleMenu.setVisible(sharedVariables.showConsoleMenu);
			} catch (Exception bal) {
			}

		} else if (action.equals("Always Show Console Edit Menu")) {
			sharedVariables.alwaysShowEdit = !sharedVariables.alwaysShowEdit;
			alwaysShowEdit.setSelected(sharedVariables.alwaysShowEdit);

			try {
				for (int bam = 0; bam < sharedVariables.openConsoleCount; bam++)
					if (!sharedVariables.showConsoleMenu) {
						consoleSubframes[bam].consoleEditMenu.setVisible(sharedVariables.alwaysShowEdit);
					}
				// consoleSubframes[bam].consoleMenu.setVisible(sharedVariables.showConsoleMenu);
			} catch (Exception bal) {
			}

		} else if (action.equals("Advanced Menu Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/optionsadvanced.html");

		} else if (action.equals("Features Menu Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/options.html");

		} else if (action.equals("Observing Menu Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/observingoptions.html");

		} else if (action.equals("Engine Analysis Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/engine-analysis.html");

		} else if (action.equals("Customizing Board Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/customizing-board.html");

		} else if (action.equals("Board Advanced Menu Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/board-advanced.html");

		} else if (action.equals("PGN Menu Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/mygames.html");

		} else if (action.equals("Game Communication Menu Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/game-communications.html");

		} else if (action.equals("Anon and Guest Login Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/guestanonloginhelp.html");

		} else if (action.equals("Getting a Game Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/getting-games.html");

		} else if (action.equals("Tournament Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/tournaments.html");

		} else if (action.equals("Correspondence Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/correspondence.html");

		} else if (action.equals("Script Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/toolbox.html");

		} else if (action.equals("Windows Menu Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/windowsmenu.html");

		} else if (action.equals("Notify Help")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/notifywindow.html");

		} else if (action.equals("Tournament Schedule")) {
			sharedVariables.openUrl("http://www.chessclub.com/help/tournaments");

		} else if (action.equals("ICC Calendar")) {
			sharedVariables.openUrl("https://www20.chessclub.com/calendar");

		} else if (action.equals("Server Time")) {
			myoutput output = new myoutput();
			output.data = "`t0`" + "multi date" + "\n";
			output.consoleNumber = 0;
			queue.add(output);
		} else if (action.equals("Disable Web Hyperlinks")) {
			sharedVariables.disableHyperlinks = !sharedVariables.disableHyperlinks;
			disableHyperlinks.setSelected(sharedVariables.disableHyperlinks);

		}

		else if (action.equals("Qsuggest Popups")) {
			sharedVariables.showQsuggest = !sharedVariables.showQsuggest;
			qsuggestPopup.setSelected(sharedVariables.showQsuggest);

		} else if (action.equals("Ask A Question")) {
			AskAQuestionDialog mypopper = new AskAQuestionDialog(this, false, queue);
			mypopper.setSize(500, 140);
			mypopper.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
			mypopper.setVisible(true);

		} else if (action.equals("Lantern Help Index")) {
			mycreator.createWebFrame("http://www.lanternchess.com/lanternhelp/lantern-help.php");

		} else if (action.equals("Change Log")) {
			mycreator.createWebFrame("http://www.lanternchess.com/changelog.htm");

		} else if (action.equals("Privacy Policy")) {
			if (channels.fics) {
				sharedVariables.openUrl("http://www.pearlchess.com/pearlchess-privacypolicy.html");
			} else {
				sharedVariables.openUrl("http://www.lanternchess.com/lanternchessios-privacypolicy.html");
			}

		} else if (action.equals("ICC Information Help Files")) {
			sharedVariables.openUrl("http://www.chessclub.com/help/info-list");

		} else if (action.equals("ICC Command Help")) {
			sharedVariables.openUrl("http://www.chessclub.com/help/commands");

		} else if (action.equals("Join")) {
			if (channels.fics) {
				sharedVariables.openUrl("https://www.freechess.org/Register/index.html");
			} else {
				sharedVariables.openUrl("https://store.chessclub.com/rewardsref/index/refer/id/LanternApp/");
			}

		} else if (action.equals("Chess Coaches")) {
			sharedVariables.openUrl("https://store.chessclub.com/teachers");

		} else if (action.equals("Lost Password")) {
			if (channels.fics) {
				sharedVariables.openUrl("https://www.freechess.org/cgi-bin/Utilities/requestPassword.cgi");
			} else {
				sharedVariables.openUrl("https://login.chessclub.com/Account/ForgotPassword");
			}

			/*
			 * } else if (action.equals("Start AutoExam")) { for (int a=0;
			 * a<sharedVariables.maxGameTabs; a++) { if (myboards[a]!=null) { if
			 * (sharedVariables.mygame[a].state == 2) myboards[a].setautoexamon(); } }
			 */

			// Andrey edits:
			// merge the responses for loading engines
		} else if (action.equals("Analyze with CuckooChess 1.12")) {
			if (sharedVariables.engineOn) {
				return;
			}
			boolean installed = false;
			File f = new File(channels.privateDirectory + sharedVariables.cuckooEngineName);
			if (f.exists() && !f.isDirectory()) {
				installed = true;
			}
			if (!installed) {
				InstallBookDialog myDialog = new InstallBookDialog(this, InstallBookDialog.cuckooChess112,
						sharedVariables.myFont);
				myDialog.setLocation(getLocation().x + sharedVariables.cornerDistance,
						getLocation().y + sharedVariables.cornerDistance);
				myDialog.setVisible(true);
			} else {
				sharedVariables.uci = true;
				sharedVariables.engineFile = f;
				startTheEngine(true);

			}
		} else if (action.equals("Analyze with Mediocre Chess v0.5")) {
			if (sharedVariables.engineOn) {
				return;
			}
			boolean installed = false;
			File f = new File(channels.privateDirectory + sharedVariables.mediocreEngineName);
			if (f.exists() && !f.isDirectory()) {
				installed = true;
			}
			if (!installed) {
				InstallBookDialog myDialog = new InstallBookDialog(this, InstallBookDialog.mediocreChess5,
						sharedVariables.myFont);
				myDialog.setLocation(getLocation().x + sharedVariables.cornerDistance,
						getLocation().y + sharedVariables.cornerDistance);
				myDialog.setVisible(true);
			} else {
				sharedVariables.uci = true;
				sharedVariables.engineFile = f;
				startTheEngine(true);

			}
		}

		else if (action.equals("Analyze with Stockfish 15")) {
			startStockfish();
		} else if (action.equals("Load Winboard Engine") || action.equals("Load UCI Engine")) {
			boolean go = false;
			if (!sharedVariables.engineOn) {
				go = true;
				try {
					JFileChooser fc = new JFileChooser();
					if (sharedVariables.engineDirectory != null)
						fc.setCurrentDirectory(sharedVariables.engineDirectory);
					else {
						if (channels.macClient) {
							fc.setCurrentDirectory(new File(channels.publicDirectory));
						} else {
							if (channels.macClient) {
								fc.setCurrentDirectory(new File(channels.publicDirectory));
							} else {
								fc.setCurrentDirectory(new File("."));
							}
						}
					}

					int returnVal = fc.showOpenDialog(this);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						sharedVariables.engineFile = fc.getSelectedFile();
						sharedVariables.engineDirectory = fc.getCurrentDirectory();
						sharedVariables.uci = (action.equals("Load UCI Engine"));

						startTheEngine(false);
					}
				} catch (Exception e) {
				}
			}

			if (!go && !sharedVariables.engineOn)
				makeEngineWarning(false);

		} else if (action.equals("One Line(Default)")) {
			if (sharedVariables.engineOn == true && sharedVariables.uciMultipleLines != 1
					&& !sharedVariables.showedUciMultiLineWarning) {
				client.writeToSubConsole("Stop and start the engine for lines change to take effect\n", 0);
				sharedVariables.showedUciMultiLineWarning = true;
			}
			sharedVariables.uciMultipleLines = 1;
			ucimultipleone.setSelected(true);
			ucimultipletwo.setSelected(false);
			ucimultiplethree.setSelected(false);

		} else if (action.equals("Two Lines")) {
			if (sharedVariables.engineOn == true && sharedVariables.uciMultipleLines != 2
					&& !sharedVariables.showedUciMultiLineWarning) {
				client.writeToSubConsole("Stop and start the engine for lines change to take effect\n", 0);
				sharedVariables.showedUciMultiLineWarning = true;
			}
			sharedVariables.uciMultipleLines = 2;
			ucimultipleone.setSelected(false);
			ucimultipletwo.setSelected(true);
			ucimultiplethree.setSelected(false);
		} else if (action.equals("Three Lines")) {
			if (sharedVariables.engineOn == true && sharedVariables.uciMultipleLines != 3
					&& !sharedVariables.showedUciMultiLineWarning) {
				client.writeToSubConsole("Stop and start the engine for lines change to take effect\n", 0);
				sharedVariables.showedUciMultiLineWarning = true;
			}
			sharedVariables.uciMultipleLines = 3;
			ucimultipleone.setSelected(false);
			ucimultipletwo.setSelected(false);
			ucimultiplethree.setSelected(true);
		}

		else if (action.equals("Set Application Background Color")) {
			setApplicationColor();

		} else if (action.equals("Open Web")) {
			mycreator.createWebFrame("http://www.google.com");

		} else if (action.equals("Open Pgn") || action.equals("Open Game Files on Computer")) {
			try {
				JFileChooser fc = new JFileChooser();
				if (channels.macClient) {
					fc.setCurrentDirectory(new File(channels.publicDirectory));
					;
				} else {
					fc.setCurrentDirectory(new File("."));
					;
				}

				fc.setFileFilter(new FileFilter() {
					public boolean accept(File f) {
						return (f.getName().toLowerCase().endsWith(".pgn") || f.isDirectory());
						/*
						 * if (f.getName().toLowerCase().endsWith(".pgn")) return true;
						 * 
						 * if(f.isDirectory()) return true; return false;
						 */
					}

					public String getDescription() {
						/*
						 * if(f.getName().toLowerCase().endsWith(".b2s")) return "*.b2s";
						 * if(f.getName().toLowerCase().endsWith(".b2a")) return "*.b2a";
						 */

						return "*.pgn";
					}
				});

				int returnVal = fc.showOpenDialog(this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String myfile = fc.getSelectedFile().toString();
					pgnLoader myLoader = new pgnLoader(myfile);
					tableClass myTableClass = new tableClass();
					myTableClass.createPgnListColumns();
					myLoader.loadTable(myTableClass);
					pgnFrame myPgnFrame = new pgnFrame(sharedVariables, queue, myTableClass, myLoader);
					sharedVariables.desktop.add(myPgnFrame);
					myPgnFrame.setSize(750, 400);
					myPgnFrame.setVisible(true);
				}
			} catch (Exception nine) {
			}

		} else if (action.equals("Set Background")) {
			setWallPaper();

			// Andrey edits:
			// merging the various analysis settings into one response
		} else if (action.equals("Analysis Font") || action.equals("Analysis Foreground Color")
				|| action.equals("Analysis Background Color")) {
			// Andrey edits:
			// checking if the new setting was made
			boolean newSetting = false;

			if (action.equals("Analysis Font")) {
				JFrame f = new JFrame("FontChooser Startup");
				FontChooser2 fc = new FontChooser2(f, sharedVariables.analysisFont);
				fc.setVisible(true);
				Font fnt = fc.getSelectedFont();

				if (fnt != null) {
					sharedVariables.analysisFont = fnt;
					newSetting = true;
				}

			} else {
				boolean foregroundColor = action.equals("Analysis Foreground Color");
				JDialog frame = new JDialog();
				Color analysisColor = (foregroundColor ? sharedVariables.analysisForegroundColor
						: sharedVariables.analysisBackgroundColor);

				Color newColor = JColorChooser.showDialog(frame, action, analysisColor);

				if (newColor != null) {
					if (foregroundColor)
						sharedVariables.analysisForegroundColor = newColor;
					else
						sharedVariables.analysisBackgroundColor = newColor;
					newSetting = true;
				}
			}

			if (newSetting) {
				for (int a = 0; a < sharedVariables.maxGameTabs; a++)
					if (myboards[a] != null)
						if (sharedVariables.gamelooking[a] == sharedVariables.engineBoard) {
							if ((sharedVariables.mygame[sharedVariables.gamelooking[a]].state == sharedVariables.STATE_EXAMINING
									|| sharedVariables.mygame[sharedVariables.gamelooking[a]].state == sharedVariables.STATE_OBSERVING)
									&& sharedVariables.engineOn)
								if (sharedVariables.mygame[sharedVariables.gamelooking[a]].clickCount % 2 == 1)
									myboards[a].myconsolepanel.setEngineDoc();
						}
			}

		} else if (action.equals("Stop Engine")) {

			stopTheEngine();

		} else if (action.equals("Restart Engine")) {
			restartEngine();

		} else if (action.equals("Activities Window/Events") || action.equals("Activities Window")) {
			openActivities();

		} else if (action.equals("Show My Game Log File")) {
			try {
				String myfile = channels.publicDirectory + "lantern_" + sharedVariables.whoAmI + ".pgn";
				if (channels.fics) {
					myfile = channels.publicDirectory + "pearl-" + sharedVariables.whoAmI + ".pgn";
				}
				File f = new File(myfile);
				if (f.exists() && !f.isDirectory()) {
					pgnLoader myLoader = new pgnLoader(myfile);
					tableClass myTableClass = new tableClass();
					myTableClass.createPgnListColumns();
					myLoader.loadTable(myTableClass);
					pgnFrame myPgnFrame = new pgnFrame(sharedVariables, queue, myTableClass, myLoader);
					sharedVariables.desktop.add(myPgnFrame);
					myPgnFrame.setSize(750, 400);
					myPgnFrame.setVisible(true);
				} else {
					String mess = channels.fics ? "pearl-" : "lantern_";
					if (channels.fics) {
						mess += sharedVariables.whoAmI
								+ ".pgn not found. Is Game menu / PGN / Log My Games selected?. Have you played a game with Pearl on this username? On Mac give Pearl permission to access the Documents Directory so save games there in a PearlChess subfolder.";
					} else {
						mess += sharedVariables.whoAmI
								+ ".pgn not found. Is Game menu / PGN / Log My Games selected?. Have you played a game with Lantern on this username? On Mac give Lantern permission to access the Documents Directory so save games there in a LanternChess subfolder.";
					}

					Popup mypopper = new Popup(this, true, mess, sharedVariables);
					mypopper.setVisible(true);
				}

			} catch (Exception logproblem) {

			}
		} else if (action.equals("Join Tournaments") || action.equals("Correspondence")
				|| action.equals("Show My Correspondence Games")) {

			try {
				if (!mysecondlist.isVisible() && !myfirstlist.isVisible())
					openActivities();
				else if (mysecondlist.isVisible()) {
					mysecondlist.setSelected(true);
				}
				if (action.equals("Join Tournaments")) {
					sharedVariables.activitiesPanel.switchToTournaments();
				} else // two correspondence options
				{
					sharedVariables.activitiesPanel.switchToCorrespondence();
				}

			} catch (Exception badtournswitch) {
			}

		}

		else if (action.equals("Notify Window")) {

			launchNotifyWindow();

		} else if (action.equals("Top Games Window")) {

			launchTopGames();

		} else if (action.equals("Seek Graph")) {
			openSeekGraph();

		} else if (action.equals("Send iloggedon")) {
			sharedVariables.iloggedon = !sharedVariables.iloggedon;
			iloggedon.setSelected(sharedVariables.iloggedon);

		} else if (action.equals("Channel Notify Map")) {
			String mess = "Map of people on channel notify.\n\n";
			String mess2 = "";
			ArrayList<String> verticalList = new ArrayList();
			for (int z = 0; z < sharedVariables.channelNotifyList.size(); z++)
				if (sharedVariables.channelNotifyList.get(z).nameList.size() > 0) {
					mess2 = "\n#" + sharedVariables.channelNotifyList.get(z).channel + " ";

					ArrayList<String> tempo = new ArrayList();

					for (int x = 0; x < sharedVariables.channelNotifyList.get(z).nameList.size(); x++) {
						tempo.add(sharedVariables.channelNotifyList.get(z).nameList.get(x));
					}
					if (tempo.size() > 0) {
						Collections.sort(tempo, new Comparator<String>() {
							public int compare(String name1, String name2) {
								// ascending order
								return name1.toLowerCase().compareTo(name2.toLowerCase());
							}
						});

						for (int m = 0; m < tempo.size(); m++)
							mess2 += tempo.get(m) + " ";

					} // end if size
					verticalList.add(mess2);
				}
			if (verticalList.size() > 0) {
				Collections.sort(verticalList, new Comparator<String>() {
					public int compare(String name1, String name2) {
						// ascending order

						try {
							int s = name1.indexOf(" ");
							int number1 = Integer.parseInt(name1.substring(2, s));
							s = name2.indexOf(" ");
							int number2 = Integer.parseInt(name2.substring(2, s));
							if (number1 < number2)
								return 0;

							return 1;
						} catch (Exception dui) {
						}

						return name1.toLowerCase().compareTo(name2.toLowerCase());
					}
				});

				for (int v = 0; v < verticalList.size(); v++)
					mess += verticalList.get(v);

			} // if vertical list size
			Popup mypopper = new Popup(this, false, mess, sharedVariables);
			mypopper.setSize(600, 500);
			mypopper.setVisible(true);

		} else if (action.equals("Channel Notify Online")) {

			String mess = sharedVariables.getChannelNotifyOnline();
			mess += sharedVariables.getConnectNotifyOnline();
			Popup mypopper = new Popup(this, false, mess, sharedVariables);
			mypopper.setSize(600, 500);
			mypopper.setVisible(true);

		} else if (action.equals("Channel Map")) {

			String mymap = "Map of channels, shouts and sshouts moved to tabs.\n\n";

			for (int a = 1; a < sharedVariables.maxConsoleTabs; a++) {
				mymap += "C" + a + ": ";
				for (int aa = 0; aa < 500; aa++)
					if (sharedVariables.console[a][aa] == 1) {
						mymap += aa;

						if (sharedVariables.mainAlso[aa])
							mymap += "m";

						mymap += " ";
					}

				if (sharedVariables.shoutRouter.shoutsConsole == a)
					mymap += "Shouts ";
				if (sharedVariables.shoutRouter.sshoutsConsole == a)
					mymap += "S-Shouts ";
				mymap += "\n";
			}

			Popup mypopper = new Popup(this, false, mymap, sharedVariables);
			mypopper.setSize(600, 500);
			mypopper.setVisible(true);

		} else if (action.equals("Send Game End Messages")) {
			sharedVariables.gameend = !sharedVariables.gameend;
			gameend.setSelected(sharedVariables.gameend);

			if (sharedVariables.gameend) {
				String mes1 = "Lantern will send to the server the commands: "
						+ "gameendwin, gameendloss, gameenddraw.  These will produce "
						+ "'command not found: gameendwin' for example untill you alias"
						+ " them to do 'says' which speak to last opponent.\n\n";
				String mes2 = "type for example: +alias gameendloss say darnit lost again\n\n";
				String mes3 = "Create 3 gameend alias's, one for each type, "
						+ "gameendwin, gameenddraw, gameendloss.  format is type in main"
						+ " console +alias   then the alias name, then the command which"
						+ " should start with 'say' to speak to your opponent\n";
				Popup mypopper = new Popup(this, false, mes1 + mes2 + mes3, sharedVariables);
				mypopper.setSize(600, 500);
				mypopper.setVisible(true);
			}
			// end gameend menu item

		} else if (action.equals("Rotate Away Message")) {
			if (!sharedVariables.rotateAways) {
				scriptLoader loadScripts = new scriptLoader();
				sharedVariables.lanternAways.clear();
				if (channels.macClient) {
					loadScripts.loadScript(sharedVariables.lanternAways, channels.publicDirectory + "lantern_away.txt");
				} else {
					loadScripts.loadScript(sharedVariables.lanternAways, "lantern_away.txt");
				}

				if (sharedVariables.lanternAways.size() > 0) {
					rotateaways.setSelected(true);
					sharedVariables.rotateAways = true;
					// size > 0
				} else {
					String mes = "lantern_away.txt not found or has nothing in it.  "
							+ "Create a file called lantern_away.txt";
					if (channels.macClient) {
						mes += " in Documents/LanternChess";
					} else {
						mes += " in the lantern folder";
					}
					mes += " and put away messages " + "in it till you run out of ideas, then reselect this option";
					Popup mypopper = new Popup(this, false, mes, sharedVariables);
					mypopper.setVisible(true);
					rotateaways.setSelected(false);
				} // else size not > 0

				// if rotateAways == false
			} else {
				rotateaways.setSelected(false);
				sharedVariables.rotateAways = false;
			} // if rotate aways true

		} else if (action.equals("What's Examine Game Replay Quick Help")) {
			String mes = "If Examining a game from a history (including your own),"
					+ " library or search list, you can have Lantern issue the command "
					+ "forward 1, at a set interval with delay set by the user between "
					+ "moves.\n\nFor example go to the Game menu and choose Examine My "
					+ "Last game, then to to Start Examine Game Replay.";
			if (channels.fics) {
				mes = "If Examining a game from a history (including your own)"
						+ " or journal, you can have Pearl issue the command "
						+ "forward 1, at a set interval with delay set by the user between "
						+ "moves.\n\nFor example go to the Game menu and choose Examine My "
						+ "Last game, then to to Start Examine Game Replay.";
			}
			Popup mypopper = new Popup(this, false, mes, sharedVariables);
			mypopper.setSize(300, 350);
			mypopper.setVisible(true);

			/*
			 * } else if (action.equals("Stop AutoExam")) { sharedVariables.autoexam=0;
			 */

		} else if (action.equals("AutoExam Dialog")) {
			// } else if (action.equals("Set AutoExam Speed")) {
			autoExamDialog frame = new autoExamDialog((JFrame) this, false, sharedVariables, myboards);
			frame.pack();
			frame.setVisible(true);

			// Andrey edits:
			// merging queen and main reconnect
		} else if (action.equals("Reconnect to Queen") || action.equals("Reconnect to ICC")
				|| action.equals("Reconnect to ICC (alternate)")) {
			try {
				sharedVariables.myServer = "ICC";

				if (action.equals("Reconnect to Queen")) {
					try {
						sharedVariables.chessclubIP = java.net.InetAddress.getByName("queen.chessclub.com")
								.getHostAddress();
						sharedVariables.chessclubPort = "5000";
						/*
						 * JFrame dot = new JFrame("pass, queen.chessclub. ip is " +
						 * sharedVariables.chessclubIP); dot.setSize(700,100); dot.setVisible(true);
						 */
					} catch (Exception efy) {
						sharedVariables.chessclubIP = "207.99.83.231";
						sharedVariables.chessclubPort = "5000";
					} // end catch
				} // end if
				else if (action.equals("Reconnect to ICC")) {
					try {
						sharedVariables.chessclubIP = java.net.InetAddress.getByName("main.chessclub.com")
								.getHostAddress();
						sharedVariables.chessclubPort = "443";
						/*
						 * JFrame dot = new JFrame("pass, chessclub.com ip is " +
						 * sharedVariables.chessclubIP); dot.setSize(700,100); dot.setVisible(true);
						 */
					} catch (Exception efy) {
						sharedVariables.chessclubIP = "207.99.83.228";
						sharedVariables.chessclubPort = "5000";
					} // end catch
				} // end if

				else if (action.equals("Reconnect to ICC (alternate)")) {
					try {
						sharedVariables.chessclubIP = java.net.InetAddress.getByName("alt1.chessclub.com")
								.getHostAddress();
						sharedVariables.chessclubPort = "443";
						/*
						 * JFrame dot = new JFrame("pass, alt1.chessclub. ip is " +
						 * sharedVariables.chessclubIP); dot.setSize(700,100); dot.setVisible(true);
						 */
					} catch (Exception efy) {
						sharedVariables.chessclubIP = "207.99.83.239";
						sharedVariables.chessclubPort = "443";
					} // end catch
				} // end if

				/*
				 * sharedVariables.chessclubIP = (action.equals("Reconnect to Queen") ?
				 * "207.99.83.231" : (action.equals("Reconnect to ICC") ?
				 * java.net.InetAddress.getByName("www.chessclub.com").getHostAddress() :
				 * "207.99.83.239"));
				 */

				sharedVariables.doreconnect = true;
				// if (myConnection == null)
				if (myConnection == null || !myConnection.isVisible())
					myConnection = new connectionDialog(this, sharedVariables, queue, false);
				// else if (!myConnection.isVisible())
				// myConnection = new connectionDialog(this, sharedVariables, queue, false);
				myConnection.setLocation(getLocation().x, getLocation().y);
				myConnection.setVisible(true);
			} catch (Exception conn) {
			}

		} else if (action.equals("Reconnect to FICS")) {

			sharedVariables.myServer = "FICS";
			sharedVariables.doreconnect = true;
			try {
				if (myConnection == null || !myConnection.isVisible())
					myConnection = new connectionDialog(this, sharedVariables, queue, false);
				myConnection.setLocation(getLocation().x, getLocation().y);

				myConnection.setVisible(true);
			} catch (Exception conn) {
			}

		} else if (action.equals("Save Settings")) {
			storeCurrentSizes();
			saveMainApplicationWindowSize();
			sharedVariables.activitiesOpen = (myfirstlist != null && myfirstlist.isVisible()
					|| mysecondlist != null && mysecondlist.isVisible());

			sharedVariables.seeksOpen = (seekGraph != null && seekGraph.isVisible());

			mysettings.saveNow(myboards, consoleSubframes, sharedVariables);
			mineScores.saveNow(sharedVariables);
			sharedVariables.hasSettings = true;
			sharedVariables.activitiesOpen = false;
			// it gets set to true on close and here, needs to be false so
			// it's checked on close
			sharedVariables.seeksOpen = false;

		} else if (action.equals("Run a Script")) {

			launchScripterDialog();
		} else if (action.equals("Customize User Buttons")) {
			launchUserButtonDialog();

		} else if (action.equals("Show Toolbar")) {
			sharedVariables.toolbarVisible = !sharedVariables.toolbarVisible;
			toolbarvisible.setSelected(sharedVariables.toolbarVisible);
			toolBar.setVisible(sharedVariables.toolbarVisible);

		} else if (action.equals("New Console")) {
			createChannelConsoleDialog frame = new createChannelConsoleDialog((JFrame) this, true, sharedVariables,
					mycreator, consoleSubframes);
			frame.setSize(550, 120);
			frame.setVisible(true);

		} else if (action.equals("Customize Tab")) {
			int hasfocus = -1;
			for (int nn = 0; nn < sharedVariables.openConsoleCount; nn++)
				if (consoleSubframes[nn] != null && consoleSubframes[nn].isSelected())
					hasfocus = nn;

			if (hasfocus == -1) {
				String swarning = "First click or select a console window, " + "and change tab to one to customize.";
				Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);

				pframe.setVisible(true);
				return;
			}

			// new idea we know the console lets find the tab
			hasfocus = sharedVariables.looking[hasfocus];

			String consoleWithFocus = "No console has focus";
			if (hasfocus > 0) {
				customizeChannelsDialog frame =

						new customizeChannelsDialog((JFrame) this, false, hasfocus, sharedVariables, consoleSubframes);
				frame.setLocation(getLocation().x + sharedVariables.cornerDistance,
						getLocation().y + sharedVariables.cornerDistance);

				String actionmess = "`m1`" + "multi =chan\n";
				if (sharedVariables.fics) {
					actionmess = "=channels\n";
				}

				myoutput data = new myoutput();
				data.data = actionmess;
				queue.add(data);
			} else {
				String swarning = "The currently selected window is looking at the "
						+ "main console tab, this can't be customized, click C1, C2, etc., first.";
				Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);
				pframe.setVisible(true);
				return;
			}

		} else if (action.equals("New Detached Chat Console")) {
			// Andrey says:
			// worth looking at further
			int detachedIndex = 11;
			while (detachedIndex > 9 && consoleChatframes[detachedIndex] != null
					&& consoleChatframes[detachedIndex].isVisible())
				detachedIndex--;

			if (detachedIndex > 9) {
				sharedVariables.chatFrame = detachedIndex;
				consoleChatframes[detachedIndex] = new chatframe(sharedVariables, consoles, queue,
						mycreator.myDocWriter);
				consoleChatframes[detachedIndex].setVisible(true);
			} else {
				Popup mypopper = new Popup(this, false, "Can only have two detached chat frames open now.",
						sharedVariables);
				mypopper.setVisible(true);
			}

		} else if (action.equals("New Chat Console")) {
			mycreator.restoreConsoleFrame();

		} else if (action.equals("New Board")) {
			mycreator.createGameFrame();

		} else if (action.equals("Cascade")) {
			int x = 160;
			int y = 120;
			int width = 400;
			int height = 300;
			int dif = 30;
			int count = 0;
			try {
				for (int a = 0; a < sharedVariables.openConsoleCount; a++)
					if (consoleSubframes[a] != null && consoleSubframes[a].isVisible()) {
						consoleSubframes[a].setSize(width, height);
						consoleSubframes[a].setLocation(x + count * dif, y + count * dif);
						consoleSubframes[a].setSelected(true);
						count++;
					}
				for (int a = 0; a < myboards.length; a++)
					if (myboards[a] != null && myboards[a].isVisible()) {
						if (!sharedVariables.useTopGames) {
							myboards[a].setSize(width, height);
							myboards[a].setLocation(x + count * dif, y + count * dif);
							myboards[a].setSelected(true);
						} else {
							if (myboards[a].topGame != null) {
								myboards[a].topGame.setSize(width, height);
								myboards[a].topGame.setLocation(x + count * dif, y + count * dif);
							}
						}
						count++;
					}

				if (myfirstlist != null && myfirstlist.isVisible()) {
					myfirstlist.setSize(width, height);
					myfirstlist.setLocation(x + count * dif, y + count * dif);
					// myfirstlist.setSelected(true);
				}

				if (mysecondlist != null && mysecondlist.isVisible()) {
					mysecondlist.setSize(width, height);
					mysecondlist.setLocation(x + count * dif, y + count * dif);
					mysecondlist.setSelected(true);
				}
			} catch (Exception d) {
			}

		} else if (action.equals("Make Boards Always On Top")) {
			/*
			 * SwingUtilities.invokeLater(new Runnable() {
			 * 
			 * @Override public void run() { try { int x=160; int y=120; int width=400; int
			 * height=300; int dif=30; int count=0;
			 * 
			 * try {
			 * 
			 * for (int a=0; a<myboards.length; a++) if (myboards[a]!=null) { if
			 * (sharedVariables.useTopGames == true) { myboards[a].switchFrame(false);
			 * 
			 * myboards[a].setSize(width,height); myboards[a].setLocation(x + count * dif, y
			 * + count * dif); myboards[a].setSelected(true);
			 * 
			 * myboards[a].topGame.setAlwaysOnTop(false); } else { if (myboards[a].topGame
			 * != null) { myboards[a].switchFrame(true);
			 * 
			 * myboards[a].topGame.setSize(width,height); myboards[a].topGame.setLocation(x
			 * + count * dif, y + count * dif);
			 * 
			 * myboards[a].topGame.setAlwaysOnTop(true); myboards[a].setVisible(false); }
			 * }// end else count++; // if not null } else break; } catch(Exception d) {} if
			 * (sharedVariables.useTopGames == true) sharedVariables.useTopGames = false;
			 * else sharedVariables.useTopGames = true; } catch (Exception e1) { //ignore }
			 * } });
			 * 
			 * SwingUtilities.invokeLater(new Runnable() {
			 * 
			 * @Override public void run() { try { for (int b=0; b< myboards.length; b++) if
			 * (myboards[b]!=null && sharedVariables.useTopGames == true)
			 * myboards[b].setVisible(false); } catch (Exception e1) { //ignore } } });
			 */

			// lantern_board_on_top.txt

			boolean ontop = getOnTopSetting();

			FileWrite mywriter = new FileWrite();
			String mess = "Next time you start the program, boards " + (ontop ? "will NOT" : "will")
					+ " be on top windows.";
			Popup mypopper = new Popup(this, true, mess, sharedVariables);
			mypopper.setVisible(true);
			mywriter.write((ontop ? "false" : "true") + "\r\n", channels.privateDirectory + "lantern_board_on_top.txt");

		} else if (action.equals("Seek a Game")) {

			openSeekAGame();
		} else if (action.equals("Challenge")) {
			sharedVariables.challengeCreator("", this, queue);

		} else if (action.equals("Log Pgn")) {
			sharedVariables.pgnLogging = !sharedVariables.pgnLogging;
			pgnlogging.setSelected(sharedVariables.pgnLogging);

		} else if (action.equals("Auto Promote")) {
			sharedVariables.autoPromote = !sharedVariables.autoPromote;
			pgnlogging.setSelected(sharedVariables.autoPromote);
			if (!sharedVariables.autoPromote) {
				String s = "A promotion dialog will appear on promotion.  For premoves no dialog will appear and it will be auto promote.";
				Popup temp = new Popup(this, false, s, sharedVariables);
				temp.setVisible(true);
			}
		}

		else if (action.equals("Drag Move")) {
			sharedVariables.moveInputType = channels.DRAG_DROP;
			dragMoveInput.setSelected(true);
			clickMoveInput.setSelected(false);
		} else if (action.equals("Click Click")) {
			sharedVariables.moveInputType = channels.CLICK_CLICK;
			dragMoveInput.setSelected(false);
			clickMoveInput.setSelected(true);

		} else if (action.equals("Log Observed Games To Pgn")) {
			sharedVariables.pgnObservedLogging = !sharedVariables.pgnObservedLogging;
			pgnObservedLogging.setSelected(sharedVariables.pgnObservedLogging);
			if (sharedVariables.pgnObservedLogging) {
				String s = "Lantern will log bullet, blitz and standard games as well as wild games "
						+ "you observe to lantern_obullet.pgn, lantern_oblitz.pgn, "
						+ "lantern_ostandard.pgn and lantern_owild.pgn.\n\n";
				Popup temp = new Popup(this, false, s, sharedVariables);
				temp.setVisible(true);
			}

			// Andrey edits:
			// merging the aspect actions
		} else if (action.equals("1:1") || action.equals("5:4") || action.equals("4:3") || action.equals("3:2")) {
			sharedVariables.aspect = (action.equals("1:1") ? 0
					: (action.equals("5:4") ? 1 : (action.equals("4:3") ? 2 : 3)));
			// Andrey says:
			// I believe this can be done with a button group
			aspectarray[sharedVariables.aspect].setSelected(true);

			for (int a = 0; a < sharedVariables.maxGameTabs; a++)
				if (myboards[a] != null && myboards[a].isVisible())
					myboards[a].mypanel.repaint();

		} else if (action.equals("Hide Board Console")) {
			sharedVariables.boardConsoleType = 0;

			redrawBoard(sharedVariables.boardConsoleType);

		} else if (action.equals("Compact Board Console")) {
			compactConsole();

		} else if (action.equals("Normal Board Console")) {
			normalConsole();

		} else if (action.equals("Larger Board Console")) {
			largerConsole();

		} else if (action.equals("Console On Side")) {
			sideConsole(1);
			sidewaysconsole.setSelected(true);
			sidewaysconsolemax.setSelected(false);
			bottomconsole.setSelected(false);

			// Andrey edits:
			// merge the board actions
		} else if (action.equals("Console On Side when Maximized")) {
			sideConsole(0);
			sidewaysconsole.setSelected(false);
			sidewaysconsolemax.setSelected(true);
			bottomconsole.setSelected(false);

			// Andrey edits:
			// merge the board actions
		} else if (action.equals("Console On Bottom")) {
			sideConsole(2);
			sidewaysconsole.setSelected(false);
			sidewaysconsolemax.setSelected(false);
			bottomconsole.setSelected(true);

			// Andrey edits:
			// merge the board actions
		} else if (action.equals("Default Board") || action.equals("Tan Board") || action.equals("Gray Color Board")
				|| action.equals("Blitzin Green Board")) {
			int boardTypeIndex = (action.equals("Default Board") ? 0
					: (action.equals("Tan Board") ? 1 : (action.equals("Gray Color Board") ? 2 : 3)));
			sharedVariables.boardType = 0;
			sharedVariables.lightcolor = sharedVariables.preselectBoards.light[boardTypeIndex];
			sharedVariables.darkcolor = sharedVariables.preselectBoards.dark[boardTypeIndex];

			setBoard(0);

			// Andrey edits:
			// merge the board color settings
		} else if (action.equals("Board Clock Background Color") || action.equals("Board Background Color")
				|| action.equals("Highlight Moves Color") || action.equals("Scroll Back Highlight Color")
				|| action.equals("Board Foreground Color") || action.equals("Clock Foreground Color")
				|| action.equals("Light Square Color") || action.equals("Dark Square Color")) {

			JDialog frame = new JDialog();

			int boardSetting = (action.equals("Board Clock Background Color") ? 0
					: (action.equals("Board Background Color") ? 1
							: (action.equals("Highlight Moves Color") ? 2
									: (action.equals("Scroll Back Highlight Color") ? 3
											: (action.equals("Board Foreground Color") ? 4
													: (action.equals("Clock Foreground Color") ? 5
															: (action.equals("Light Square Color") ? 6 : 7)))))));

			Color boardSettingColor = (boardSetting == 0 ? sharedVariables.onMoveBoardBackgroundColor
					: (boardSetting == 1 ? sharedVariables.boardBackgroundColor
							: (boardSetting == 2 ? sharedVariables.highlightcolor
									: (boardSetting == 3 ? sharedVariables.scrollhighlightcolor
											: (boardSetting == 4 ? sharedVariables.boardForegroundColor
													: (boardSetting == 5 ? sharedVariables.clockForegroundColor
															: (boardSetting == 6 ? sharedVariables.lightcolor
																	: sharedVariables.darkcolor)))))));

			Color newColor = JColorChooser.showDialog(frame, action, boardSettingColor);

			if (newColor != null) {
				if (boardSetting == 0)
					sharedVariables.onMoveBoardBackgroundColor = newColor;
				else if (boardSetting == 1)
					sharedVariables.boardBackgroundColor = newColor;
				else if (boardSetting == 2)
					sharedVariables.highlightcolor = newColor;
				else if (boardSetting == 3)
					sharedVariables.scrollhighlightcolor = newColor;
				else if (boardSetting == 4)
					sharedVariables.boardForegroundColor = newColor;
				else if (boardSetting == 5)
					sharedVariables.clockForegroundColor = newColor;
				else if (boardSetting == 6)
					sharedVariables.lightcolor = newColor;
				else // if (boardSetting == 7)
					sharedVariables.darkcolor = newColor;
			}

			for (int a = 0; a < sharedVariables.maxGameTabs; a++)
				if (myboards[a] != null)
					// if(myboards[a].isVisible() == true)
					myboards[a].repaint();

			// Andrey says:
			// the next few actions appear to be no longer called
			/*
			 * } else if (action.equals("Titles In Channel Color")) { JDialog frame = new
			 * JDialog(); Color newColor = JColorChooser.showDialog(frame,
			 * "Set Titles In Channel Color", sharedVariables.channelTitlesColor); if
			 * (newColor != null) sharedVariables.channelTitlesColor=newColor;
			 * 
			 * } else if (action.equals("Channel Name Color")) { JDialog frame = new
			 * JDialog(); Color newColor = JColorChooser.showDialog(frame,
			 * "Set Channel Name Color", sharedVariables.qtellChannelNumberColor); if
			 * (newColor != null) sharedVariables.qtellChannelNumberColor=newColor;
			 * 
			 * // Andrey edits: // merge brighter channel name color with darker } else if
			 * (action.equals("Brighter Channel Name Color") ||
			 * action.equals("Darker Channel Name Color")) { String mycolstring; float[]
			 * hsbValues = new float[3]; Color col2 =
			 * sharedVariables.qtellChannelNumberColor; hsbValues =
			 * Color.RGBtoHSB(col2.getRed(), col2.getGreen(), col2.getBlue(), hsbValues);
			 * float hue, saturation, brightness; hue = hsbValues[0]; saturation =
			 * hsbValues[1]; brightness = hsbValues[2]; mycolstring =
			 * "color values were hue= " + hue + " and saturation= " + saturation +
			 * " and brightness=" + brightness + " and red=" + col2.getRed() + " and blue ="
			 * + col2.getGreen() + " and green=" + col2.getBlue() + " ";
			 * 
			 * sharedVariables.qtellChannelNumberColor =
			 * (action.equals("Brighter Channel Name Color") ? col2.brighter() :
			 * col2.darker());
			 * 
			 * col2 = sharedVariables.qtellChannelNumberColor; hsbValues =
			 * Color.RGBtoHSB(col2.getRed(), col2.getGreen(), col2.getBlue(), hsbValues);
			 * 
			 * hue = hsbValues[0]; saturation = hsbValues[1]; brightness = hsbValues[2];
			 * mycolstring = mycolstring + " color values now hue= " + hue +
			 * " and saturation= " + saturation + " and brightness=" + brightness +
			 * " and red=" + col2.getRed() + " and blue =" + col2.getGreen() + " and green="
			 * + col2.getBlue() + " "; Popup mypopper= new Popup(this, false, mycolstring);
			 * mypopper.setVisible(true);
			 * 
			 * } else if (action.equals("Darker Channel Name Color")) {
			 * 
			 * String mycolstring = ""; float[] hsbValues = new float[3]; Color col2 =
			 * sharedVariables.qtellChannelNumberColor; hsbValues =
			 * Color.RGBtoHSB(col2.getRed(), col2.getGreen(),col2.getBlue(), hsbValues);
			 * float hue, saturation, brightness; hue = hsbValues[0]; saturation =
			 * hsbValues[1]; brightness = hsbValues[2]; mycolstring =
			 * "color values were hue= " + hue + " and saturation= " + saturation +
			 * " and brightness=" + brightness + " and red=" + col2.getRed() + " and blue ="
			 * + col2.getGreen() + " and green=" + col2.getBlue() + " ";
			 * 
			 * sharedVariables.qtellChannelNumberColor =
			 * sharedVariables.qtellChannelNumberColor.darker();
			 * 
			 * col2 = sharedVariables.qtellChannelNumberColor; hsbValues =
			 * Color.RGBtoHSB(col2.getRed(), col2.getGreen(),col2.getBlue(), hsbValues);
			 * 
			 * hue = hsbValues[0]; saturation = hsbValues[1]; brightness = hsbValues[2];
			 * mycolstring = mycolstring + " color values now hue= " + hue +
			 * " and saturation= " + saturation + " and brightness=" + brightness +
			 * " and red=" + col2.getRed() + " and blue =" + col2.getGreen() + " and green="
			 * + col2.getBlue() + " ";
			 * 
			 * Popup mypopper= new Popup(this, false, mycolstring);
			 * mypopper.setVisible(true);
			 */

		} else if (action.equals("PTell Name Color")) {
			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Tell Name Color", sharedVariables.tellNameColor);
			if (newColor != null)
				sharedVariables.tellNameColor = newColor;

		} else if (action.equals("Names List Foreground Color")) {
			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Names List Foreground Color",
					sharedVariables.nameForegroundColor);
			if (newColor != null) {
				sharedVariables.nameForegroundColor = newColor;
				sharedVariables.activitiesPanel.theChannelList.setForeground(sharedVariables.nameForegroundColor);
				sharedVariables.activitiesPanel.theChannelList2.setForeground(sharedVariables.nameForegroundColor);
				sharedVariables.activitiesPanel.theChannelList3.setForeground(sharedVariables.nameForegroundColor);
				for (int c = 0; c < sharedVariables.maxConsoleTabs; c++) {
					if (consoleSubframes[c] != null) {
						consoleSubframes[c].myNameList.setForeground(newColor);
					}
				}
			}

		} else if (action.equals("Names List Background Color")) {
			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Names List Background Color",
					sharedVariables.nameBackgroundColor);
			if (newColor != null) {
				sharedVariables.nameBackgroundColor = newColor;

				sharedVariables.activitiesPanel.theChannelList.setBackground(sharedVariables.nameBackgroundColor);
				sharedVariables.activitiesPanel.theChannelList2.setBackground(sharedVariables.nameBackgroundColor);
				sharedVariables.activitiesPanel.theChannelList3.setBackground(sharedVariables.nameBackgroundColor);

				for (int c = 0; c < sharedVariables.maxConsoleTabs; c++) {
					if (consoleSubframes[c] != null) {
						consoleSubframes[c].myNameList.setBackground(newColor);
					}
				} // end for
			} // end if not null
				// end if name list background

			// Andrey edits:
			// merge all the commands sent from the action menu
		} else if (action.equals("Show My Recent Games") || action.equals("Show My Game Library")
				|| action.equals("Show My Journal") || action.equals("Show My Adjourned Games")
				|| action.equals("My Profile and Ratings") || action.equals("Enter Examination Mode")
				|| action.equals("Examine My Last Game") || action.equals("Unexamine") || action.equals("Help Discount")
				|| action.equals("Observe High Rated Game") || action.equals("Observe High Rated 5-Minute Game")
				|| action.equals("Observe High Rated 15-Minute Game") || action.equals("Observe High Rated Blitz Game")
				|| action.equals("Observe High Rated Standard Game") || action.equals("Stop Following")
				|| action.equals("Auto Examine After Playing") || action.equals("Follow Broadcast- When On")
				|| action.equals("Disconnect") || action.equals("Rematch")
				|| action.equals("Show Titled Players Online in M0 Tab") || action.equals("Withdraw Challenges")) {
			if (action.equals("Follow Broadcast- When On"))
				client.writeToSubConsole("Be sure to turn on the radio by opening ChessFM, "
						+ "Actions - Open ChessFM in the menu. See \"Broadcast Help\" in Lantern Manul for any latest info.\n",
						0);
			if (action.equals("Show Titled Players Online in M0 Tab"))
				client.writeToSubConsole(
						"For a context menu with observe, finger etc - double click on a name or highlight a name and right click. ^ next to a name means they are playing.\n",
						0);
			// String actionmess = "History\n";
			String actionmess = (action.equals("Show My Recent Games") ? "History"
					: (action.equals("Show My Game Library") ? "Liblist"
							: (action.equals("Show My Journal") ? "Journal"
									: (action.equals("Show My Adjourned Games") ? "Stored"
											: (action.equals("My Profile and Ratings")
													? channels.fics ? "Finger" : "`f1`Finger"
													: (action.equals("Enter Examination Mode") ? "Examine"
															: (action.equals("Show Titled Players Online in M0 Tab")
																	? "Who T"
																	: (action.equals("Disconnect") ? "Quit"
																			: (action.equals("Help Discount")
																					? "Help discount"
																					: (action.equals("Rematch")
																							? "Rematch"
																							: (action.equals(
																									"Examine My Last Game")
																									&& !channels.fics
																											? "Examine -1"
																											: (action
																													.equals("Examine My Last Game")
																													&& channels.fics
																															? "exl"
																															: (action
																																	.equals("Unexamine")
																																			? "Unexamine"
																																			: (action
																																					.equals("Observe High Rated Game")
																																							? "Observe *"
																																							: (action
																																									.equals("Observe High Rated 5-Minute Game")
																																											? "Observe *f"
																																											: (action
																																													.equals("Observe High Rated 15-Minute Game")
																																															? "Observe *P"
																																															: (action
																																																	.equals("Observe High Rated Blitz Game")
																																																			? "Observe /b"
																																																			: (action
																																																					.equals("Observe High Rated Standard Game")
																																																							? "Observe /s"
																																																							: (action
																																																									.equals("Stop Following")
																																																											? "Unfollow"
																																																											: (action
																																																													.equals("Auto Examine After Playing")
																																																															? "set examine "
																																																																	+ !sharedVariables.myseek.examine
																																																															: (action
																																																																	.equals("Follow Broadcast- When On")
																																																																			? "Follow Broadcast"
																																																																			: "Match")))))))))))))))))))))
					+ "\n";

			if (action.equals("Withdraw Challenges") && channels.fics) {
				actionmess = "$unseek\n";
				myoutput data = new myoutput();
				data.data = actionmess;
				queue.add(data);
				actionmess = "$withdraw t match\n";

			}
			if (!channels.fics && !actionmess.startsWith("`"))
				actionmess = "`c0`" + actionmess;

			myoutput data = new myoutput();
			data.data = actionmess;
			queue.add(data);

		} else if (action.equals("Show Relay Schedule")) {
			sharedVariables.openUrl("https://www.chessclub.com/relayed-events");

		}

		else if (action.equals("Add a Friend")) {
			openAddAFriend();

		} else if (action.equals("Lookup User")) {
			LookupUserDialog frame = new LookupUserDialog(this, false, queue, sharedVariables);
			frame.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);

		} else if (action.equals("Open Videos Page")) {
			sharedVariables.openUrl("https://www.chessclub.com/videos");

		} else if (action.equals("ICC Store")) {
			sharedVariables.openUrl("https://store.chessclub.com/");

		} else if (action.equals("My ICC")) {
			sharedVariables.openUrl("https://login.chessclub.com");

		}

		else if (action.equals("Show Rating Graphs")) {

			String thename = "WimpB";
			if (sharedVariables.myname.length() > 0) {
				thename = sharedVariables.myname;
			}
			sharedVariables.openUrl("http://statistics.chessclub.com/Embed/Ratings?user=" + thename + "#");

		} else if (action.equals("Event List/Tournaments Font") || action.equals("Activities Font")) {
			setEventListFont();

		} else if (action.equals("Names List Font")) {
			JFrame f = new JFrame("FontChooser Startup");
			FontChooser2 fc = new FontChooser2(f, sharedVariables.nameListFont);
			fc.setVisible(true);
			Font fnt = fc.getSelectedFont();
			if (fnt != null) {
				sharedVariables.nameListFont = fnt;
				sharedVariables.activitiesPanel.theChannelList.setFont(sharedVariables.nameListFont);
				sharedVariables.activitiesPanel.theChannelList2.setFont(sharedVariables.nameListFont);
				sharedVariables.activitiesPanel.theChannelList3.setFont(sharedVariables.nameListFont);

				for (int c = 0; c < sharedVariables.maxConsoleTabs; c++) {
					if (consoleSubframes[c] != null) {
						consoleSubframes[c].myNameList.setFont(fnt);
					}
				} // end for
			}

		} else if (action.equals("Chat Timestamp Color")) {
			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Set Timestamp Color", sharedVariables.chatTimestampColor);
			if (newColor != null)
				sharedVariables.chatTimestampColor = newColor;

		} else if (action.equals("Solid Color Board")) {
			sharedVariables.boardType = 0;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Pale Wood")) {
			sharedVariables.boardType = 1;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Light Wood")) {
			sharedVariables.boardType = 2;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Dark Wood")) {
			sharedVariables.boardType = 3;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Gray Marble")) {
			sharedVariables.boardType = 4;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Red Marble")) {
			sharedVariables.boardType = 5;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Crampled Paper")) {
			sharedVariables.boardType = 6;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Winter")) {
			sharedVariables.boardType = 7;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Olive Board")) {
			sharedVariables.boardType = 8;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Cherry Board")) {
			sharedVariables.boardType = 9;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Purple Board")) {
			sharedVariables.boardType = 10;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Wood-4")) {
			sharedVariables.boardType = 11;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Wood-5")) {
			sharedVariables.boardType = 12;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Wood-6")) {
			sharedVariables.boardType = 19;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Wood-7")) {
			sharedVariables.boardType = 20;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Wood-8")) {
			sharedVariables.boardType = 21;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Cold Marble")) {
			sharedVariables.boardType = 13;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Green Marble")) {
			sharedVariables.boardType = 14;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Slate")) {
			sharedVariables.boardType = 15;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Thief")) {
			sharedVariables.boardType = 16;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Tournament Mat")) {
			sharedVariables.boardType = 17;
			setBoard(sharedVariables.boardType);

		} else if (action.equals("Tournament Mat2")) {
			sharedVariables.boardType = 18;
			setBoard(sharedVariables.boardType);

		}

		else if (action.equals("Dyche1")) {
			sharedVariables.pieceType = 0;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Dyche2")) {
			sharedVariables.pieceType = 1;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Dyche3")) {
			sharedVariables.pieceType = 2;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Bookup")) {
			sharedVariables.pieceType = 3;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Xboard")) {
			sharedVariables.pieceType = 4;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Alpha")) {
			sharedVariables.pieceType = 5;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Spatial")) {
			sharedVariables.pieceType = 6;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Harlequin")) {
			sharedVariables.pieceType = 7;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Berlin")) {
			sharedVariables.pieceType = 8;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Eboard Classic")) {
			sharedVariables.pieceType = 9;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Molten Good")) {
			sharedVariables.pieceType = 10;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Molten Evil")) {
			sharedVariables.pieceType = 11;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Liebeskind")) {
			sharedVariables.pieceType = 12;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Eyes")) {
			sharedVariables.pieceType = 13;
			setPieces(sharedVariables.pieceType);

		}
		if (action.equals("Fantasy")) {
			sharedVariables.pieceType = 14;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Adventure")) {
			sharedVariables.pieceType = 18;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Maya")) {
			sharedVariables.pieceType = 19;
			setPieces(sharedVariables.pieceType);

		}
		if (action.equals("Medieval")) {
			sharedVariables.pieceType = 20;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("CCube")) {
			sharedVariables.pieceType = 21;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Monge Mix")) {
			sharedVariables.pieceType = 25;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("About Monge Pieces")) {

			String warning = "The Monge chess pieces are authored by Maurizio Monge, "
					+ "at this time, three of the six sets are currently in Lantern and they "
					+ "are under the LGPL (library GPL) license at the time of this writing. "
					+ "\n\n Virtually all the piece sets in Lantern come from the Jin Chess, "
					+ "and except in cases like the Monge pieces, where I know the license, "
					+ "I offer no more rights than Jin does.  LGPL allows you to reuse the "
					+ "pieces in your own application if you're a developer. So unzip the "
					+ "lantern.jar to get at the pieces.  A warning though, the \\setName\\64\\"
					+ "folder is a general folder for when I want pieces I can resize, and the "
					+ "monge pieces don't actually come in the 64 size.\n\n  The monge mix is a "
					+ "mix of pieces from the Fantasy and Spatial set I've put together.";
			Popup mypopup = new Popup(this, false, warning, sharedVariables);
			mypopup.setSize(600, 500);
			mypopup.setVisible(true);

		} else if (action.equals("Merida")) {
			sharedVariables.pieceType = 22;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Kingdom")) {
			sharedVariables.pieceType = 23;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Alpha-2")) {
			sharedVariables.pieceType = 24;
			setPieces(sharedVariables.pieceType);

		}

		else if (action.equals("Random Pieces")) {
			sharedVariables.pieceType = 26;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Black-Red")) {
			sharedVariables.checkersPieceType = 1;
			setCheckersPieces(sharedVariables.checkersPieceType);

		} else if (action.equals("Black-White")) {
			sharedVariables.checkersPieceType = 2;
			setCheckersPieces(sharedVariables.checkersPieceType);

		} else if (action.equals("Line")) {
			sharedVariables.pieceType = 15;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Motif")) {
			sharedVariables.pieceType = 16;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("Utrecht")) {
			sharedVariables.pieceType = 17;
			setPieces(sharedVariables.pieceType);

		} else if (action.equals("` ` Do Nothing")) {
			sharedVariables.italicsBehavior = 0;
			checkItalicsBehavior(0);

		} else if (action.equals("` ` Italics")) {
			sharedVariables.italicsBehavior = 1;
			checkItalicsBehavior(1);

		} else if (action.equals("` ` Brighter Color")) {
			sharedVariables.italicsBehavior = 2;
			checkItalicsBehavior(2);

		} else if (action.equals("Unvisited/Visited")) {// active tab

			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Unvisited/Visited Color", sharedVariables.tabBackground2);
			if (newColor != null)
				sharedVariables.tabBackground2 = newColor;

		} else if (action.equals("Unvisited")) {// active tab

			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Unvisited Color", sharedVariables.newInfoTabBackground);
			if (newColor != null)
				sharedVariables.newInfoTabBackground = newColor;

			for (int a = 0; a < sharedVariables.openBoardCount; a++)
				if (myboards[a] != null && myboards[a].isVisible())
					for (int aa = 0; aa < sharedVariables.openBoardCount; aa++) {
						newColor = myboards[a].myconsolepanel.channelTabs[aa].getBackground();

						if (newColor.getRGB() != sharedVariables.tabBackground.getRGB())
							myboards[a].myconsolepanel.channelTabs[aa]
									.setBackground(sharedVariables.newInfoTabBackground);
					}
			// now update consoles
			for (int a = 0; a < sharedVariables.openConsoleCount; a++)
				if (consoleSubframes[a] != null && consoleSubframes[a].isVisible())
					for (int aa = 0; aa < sharedVariables.openConsoleCount; aa++) {
						newColor = consoleSubframes[a].channelTabs[aa].getBackground();
						if (newColor.getRGB() != sharedVariables.tabBackground.getRGB())
							consoleSubframes[a].channelTabs[aa].setBackground(sharedVariables.newInfoTabBackground);
					}

		} else if (action.equals("Tab I'm On Background")) {// active tab

			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Tab I'm On Color", sharedVariables.tabImOnBackground);
			if (newColor != null)
				sharedVariables.tabImOnBackground = newColor;

			for (int a = 0; a < sharedVariables.openConsoleCount; a++)
				if (consoleSubframes[a] != null && consoleSubframes[a].isVisible())
					consoleSubframes[a].channelTabs[sharedVariables.looking[a]]
							.setBackground(sharedVariables.tabImOnBackground);

		} else if (action.equals("Visited")) {// active tab

			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Visited Color", sharedVariables.tabBackground);
			if (newColor != null)
				sharedVariables.tabBackground = newColor;

			for (int a = 0; a < sharedVariables.openBoardCount; a++)
				if (myboards[a] != null && myboards[a].isVisible())
					for (int aa = 0; aa < sharedVariables.openBoardCount; aa++) {
						newColor = myboards[a].myconsolepanel.channelTabs[aa].getBackground();
						if (!newColor.equals(sharedVariables.newInfoTabBackground))
							myboards[a].myconsolepanel.channelTabs[aa].setBackground(sharedVariables.tabBackground);
					}
			// now update consoles
			for (int a = 0; a < sharedVariables.openConsoleCount; a++)
				if (consoleSubframes[a] != null && consoleSubframes[a].isVisible())
					for (int aa = 0; aa < sharedVariables.maxConsoleTabs; aa++) {
						newColor = consoleSubframes[a].channelTabs[aa].getBackground();
						if (!newColor.equals(sharedVariables.newInfoTabBackground))
							consoleSubframes[a].channelTabs[aa].setBackground(sharedVariables.tabBackground);
					}

			// Andrey edits:
			// merge the tab border actions
		} else if (action.equals("Tab Border") || action.equals("Tell Tab Border")) {// active tab

			JDialog frame = new JDialog();

			Color tabSetting = (action.equals("Tab Border") ? sharedVariables.tabBorderColor
					: sharedVariables.tellTabBorderColor);

			Color newColor = JColorChooser.showDialog(frame, action + " Color", tabSetting);

			if (newColor != null) {
				if (action.equals("Tab Border"))
					sharedVariables.tabBorderColor = newColor;
				else
					sharedVariables.tellTabBorderColor = newColor;
			}
			repaintTabBorders();

			// Andrey edits:
			// merge the input settings
		} else if (action.equals("Input Command Color") || action.equals("Input Chat Color")) {// Input Colors

			JDialog frame = new JDialog();

			Color inputSetting = (action.equals("Input Command Color") ? sharedVariables.inputCommandColor
					: sharedVariables.inputChatColor);

			Color newColor = JColorChooser.showDialog(frame, action, inputSetting);

			if (newColor != null) {
				if (action.equals("Input Command Color"))
					sharedVariables.inputCommandColor = newColor;
				else
					sharedVariables.inputChatColor = newColor;
			}

			// Andrey edits:
			// merge active with non-active
		} else if (action.equals("Active") || // active tab
				action.equals("Non Active")) {

			JDialog frame = new JDialog();

			Color activeSetting = (action.equals("Active") ? sharedVariables.activeTabForeground
					: sharedVariables.passiveTabForeground);

			Color newColor = JColorChooser.showDialog(frame, action + " Foreground Color", activeSetting);
			if (newColor != null) {
				if (action.equals("Active"))
					sharedVariables.activeTabForeground = newColor;
				else // if (action.equals("Non Active")
					sharedVariables.passiveTabForeground = newColor;
			}

			for (int a = 0; a < sharedVariables.openBoardCount; a++)
				if (myboards[a] != null && myboards[a].isVisible())
					myboards[a].myconsolepanel.setActiveTabForeground(sharedVariables.gamelooking[a]);

			// now update consoles
			for (int a = 0; a < sharedVariables.openConsoleCount; a++)
				if (consoleSubframes[a] != null && consoleSubframes[a].isVisible())
					consoleSubframes[a].setActiveTabForeground(sharedVariables.looking[a]);

		} else if (action.equals("Highlight Moves")) {
			sharedVariables.highlightMoves = !sharedVariables.highlightMoves;
			highlight.setSelected(sharedVariables.highlightMoves);

		} else if (action.equals("Material Count")) {
			sharedVariables.showMaterialCount = !sharedVariables.showMaterialCount;
			materialCount.setSelected(sharedVariables.showMaterialCount);

		} else if (action.equals("Draw Coordinates")) {
			sharedVariables.drawCoordinates = !sharedVariables.drawCoordinates;
			drawCoordinates.setSelected(sharedVariables.drawCoordinates);

			myoutput output = new myoutput();
			output.repaint64 = 1;
			queue.add(output);

		} else if (action.equals("Show Observers In Games")) {
			sharedVariables.playersInMyGame = (sharedVariables.playersInMyGame == 0 ? 2 : 0);
			playersInMyGame.setSelected(sharedVariables.playersInMyGame != 0);

		} else if (action.equals("Show Ratings on Board When Playing")) {
			sharedVariables.showRatings = !sharedVariables.showRatings;
			showRatings.setSelected(sharedVariables.showRatings);

		} else if (action.equals("Switch To New Game Tab On Observe")) {
			sharedVariables.newObserveGameSwitch = !sharedVariables.newObserveGameSwitch;
			newObserveGameSwitch.setSelected(sharedVariables.newObserveGameSwitch);

		} else if (action.equals("Low Time Clock Colors (Bullet Only)")) {
			sharedVariables.lowTimeColors = !sharedVariables.lowTimeColors;
			lowTimeColors.setSelected(sharedVariables.lowTimeColors);

		} else if (action.equals("AutoChat")) {
			sharedVariables.autoChat = !sharedVariables.autoChat;
			autoChat.setSelected(sharedVariables.autoChat);

		} else if (action.equals("Block Opponents Says When Not Playing")) {
			sharedVariables.blockSays = !sharedVariables.blockSays;
			blockSays.setSelected(sharedVariables.blockSays);

		} else if (action.equals("Show Examine Mode Palette")) {
			sharedVariables.showPallette = !sharedVariables.showPallette;
			showPallette.setSelected(sharedVariables.showPallette);

			for (int bn = 0; bn < sharedVariables.maxGameTabs; bn++) {
				if (myboards[bn] != null)
					myboards[bn].mypanel.repaint();
				else
					break;
			}

		} else if (action.equals("Show Flags")) {
			sharedVariables.showFlags = !sharedVariables.showFlags;
			showFlags.setSelected(sharedVariables.showFlags);

			String swarning = "This setting will update on board as soon as the next game starts.";
			Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);
			pframe.setVisible(true);

		} else if (action.equals("Chess Font For Move List")) {
			boolean chessfont = getChessFontSetting();

			FileWrite mywriter = new FileWrite();
			String mess = "Next time you start the program,  a chess font for move list "
					+ (chessfont ? "will NOT" : "will") + " be used.";
			Popup mypopper = new Popup(this, true, mess, sharedVariables);
			mypopper.setVisible(true);
			mywriter.write((chessfont ? "false" : "true") + "\r\n", "lantern_move_list_font_choice.txt");

		} else if (action.equals("Use Light Square as Board Background")) {
			sharedVariables.useLightBackground = !sharedVariables.useLightBackground;
			useLightBackground.setSelected(sharedVariables.useLightBackground);

			// Andrey edits:
			// merge the three actions
		} else if (action.equals("Original") || action.equals("Modern") || action.equals("Mixed")) {
			sharedVariables.andreysLayout = (action.equals("Original") ? 0 : (action.equals("Modern") ? 1 : 2));
			boarddesignarray[sharedVariables.andreysLayout].setSelected(true);
			redrawBoard(sharedVariables.boardConsoleType);

		} else if (action.equals("Flip")) {
			runFlipCommand();

		} else if (action.equals("Tabs Only")) {
			sharedVariables.tabsOnly = !sharedVariables.tabsOnly;
			tabbing.setSelected(sharedVariables.tabsOnly);

		} else if (action.equals("Auto Buffer Chat Length")) {
			sharedVariables.autoBufferChat = !sharedVariables.autoBufferChat;
			autobufferchat.setSelected(sharedVariables.autoBufferChat);

		} else if (action.equals("Large Chat Buffer")) {
			sharedVariables.chatBufferLarge = !sharedVariables.chatBufferLarge;
			chatbufferlarge.setSelected(sharedVariables.chatBufferLarge);
			sharedVariables.setChatBufferSize();

		} else if (action.equals("No Idle")) {
			sharedVariables.noidle = !sharedVariables.noidle;
			autonoidle.setSelected(sharedVariables.noidle);

		} else if (action.equals("Switch Tab On Tell")) {
			sharedVariables.switchOnTell = !sharedVariables.switchOnTell;
			tellswitch.setSelected(sharedVariables.switchOnTell);

		} else if (action.equals("Add Name On Tell Switch")) {
			sharedVariables.addNameOnSwitch = !sharedVariables.addNameOnSwitch;
			addnameontellswitch.setSelected(sharedVariables.addNameOnSwitch);

		} else if (action.equals("Timestamp To Left Of Name")) {
			channels.leftTimestamp = !channels.leftTimestamp;
			leftNameTimestamp.setSelected(channels.leftTimestamp);

		} else if (action.equals("Timestamp Connecting")) {
			sharedVariables.reconnectTimestamp = !sharedVariables.reconnectTimestamp;
			reconnectTimestamp.setSelected(sharedVariables.reconnectTimestamp);

		} else if (action.equals("Timestamp Shouts")) {
			sharedVariables.shoutTimestamp = !sharedVariables.shoutTimestamp;
			shoutTimestamp.setSelected(sharedVariables.shoutTimestamp);

		} else if (action.equals("Timestamp Channel Qtells")) {
			sharedVariables.qtellTimestamp = !sharedVariables.qtellTimestamp;
			qtellTimestamp.setSelected(sharedVariables.qtellTimestamp);

		} else if (action.equals("Timestamp in 24hr Format")) {
			channels.timeStamp24hr = !channels.timeStamp24hr;
			timeStamp24hr.setSelected(channels.timeStamp24hr);

		} else if (action.equals("Timestamp Tells and Notifications")) {
			sharedVariables.tellTimestamp = !sharedVariables.tellTimestamp;
			tellTimestamp.setSelected(sharedVariables.tellTimestamp);

		} else if (action.equals("Timestamp Channels and Kibs")) {
			sharedVariables.channelTimestamp = !sharedVariables.channelTimestamp;
			channelTimestamp.setSelected(sharedVariables.channelTimestamp);

		} else if (action.equals("Use Basketball Logo ICC Flag")) {
			sharedVariables.basketballFlag = !sharedVariables.basketballFlag;
			basketballFlag.setSelected(sharedVariables.basketballFlag);

		} else if (action.equals("Don't Reuse Game Tabs")) {
			sharedVariables.dontReuseGameTabs = !sharedVariables.dontReuseGameTabs;
			dontReuseGameTabs.setSelected(sharedVariables.dontReuseGameTabs);

		} else if (action.equals("Auto Name Popup")) {
			sharedVariables.autopopup = !sharedVariables.autopopup;
			autopopup.setSelected(sharedVariables.autopopup);

		} else if (action.equals("Auto History Popup")) {
			sharedVariables.autoHistoryPopup = !sharedVariables.autoHistoryPopup;
			autoHistoryPopup.setSelected(sharedVariables.autoHistoryPopup);

			// Andrey edits:
			// remove "Auto Observe"
		} else if (action.equals("Tomato")) {
			sharedVariables.autoTomato = !sharedVariables.autoTomato;

		} else if (action.equals("Cooly")) {
			sharedVariables.autoCooly = !sharedVariables.autoCooly;

		} else if (action.equals("WildOne")) {
			sharedVariables.autoWildOne = !sharedVariables.autoWildOne;

		} else if (action.equals("Flash")) {
			sharedVariables.autoFlash = !sharedVariables.autoFlash;

		}
		if (action.equals("Olive")) {
			sharedVariables.autoOlive = !sharedVariables.autoOlive;

		} else if (action.equals("Ketchup")) {
			sharedVariables.autoKetchup = !sharedVariables.autoKetchup;

		} else if (action.equals("LittlePer")) {
			sharedVariables.autoLittlePer = !sharedVariables.autoLittlePer;

		} else if (action.equals("Slomato")) {
			sharedVariables.autoSlomato = !sharedVariables.autoSlomato;

		} else if (action.equals("Pear")) {
			sharedVariables.autoPear = !sharedVariables.autoPear;

		} else if (action.equals("Automato")) {
			sharedVariables.autoAutomato = !sharedVariables.autoAutomato;

		}

		else if (action.equals("Yenta")) {
			sharedVariables.autoYenta = !sharedVariables.autoYenta;

		}

		else if (action.equals("uscf")) {
			sharedVariables.autouscf = !sharedVariables.autouscf;

		} else if (action.equals("Random Piece Set Observe Only")) {
			sharedVariables.randomArmy = !sharedVariables.randomArmy;
			randomArmy.setSelected(sharedVariables.randomArmy);

			// Andrey edits:
			// merge the next 2 actions
		} else if (action.equals("Configure Random Pieces For White")
				|| action.equals("Configure Random Pieces For Black")) {
			boolean whiteSetting = action.equals("Configure Random Pieces For White");
			boolean[] excludedSetting = (whiteSetting ? sharedVariables.excludedPiecesWhite
					: sharedVariables.excludedPiecesBlack);

			customizeExcludedPiecesDialog goConfigure = new customizeExcludedPiecesDialog(this, false, sharedVariables,
					graphics, excludedSetting, whiteSetting);
			goConfigure.setVisible(true);

		} else if (action.equals("Configure Random Square Tiles")) {
			CustomizeExcludedBoardsDialog goConfigure = new CustomizeExcludedBoardsDialog(this, false, sharedVariables,
					graphics, sharedVariables.excludedBoards);
			goConfigure.setVisible(true);

		} else if (action.equals("Random Square Tiles Observe Only")) {
			sharedVariables.randomBoardTiles = !sharedVariables.randomBoardTiles;
			randomTiles.setSelected(sharedVariables.randomBoardTiles);

		} else if (action.equals("Show Profile Mugshots")) {
			sharedVariables.showMugshots = !sharedVariables.showMugshots;
			showMugshots.setSelected(sharedVariables.showMugshots);
		} else if (action.equals("Sounds for Notifications")) {
			sharedVariables.specificSounds[4] = !sharedVariables.specificSounds[4];
			notifysound.setSelected(sharedVariables.specificSounds[4]);

		} else if (action.equals("Sounds for Correspondence Notifications")) {
			sharedVariables.correspondenceNotificationSounds = !sharedVariables.correspondenceNotificationSounds;
			correspondenceNotificationSounds.setSelected(sharedVariables.correspondenceNotificationSounds);

		} else if (action.equals("Sounds for Tells")) {
			sharedVariables.makeTellSounds = !sharedVariables.makeTellSounds;
			maketellsounds.setSelected(sharedVariables.makeTellSounds);

		} else if (action.equals("Sounds for @yourname")) {
			sharedVariables.makeAtNameSounds = !sharedVariables.makeAtNameSounds;
			maketellsounds.setSelected(sharedVariables.makeAtNameSounds);

		} else if (action.equals("Sounds for Moves")) {
			sharedVariables.makeMoveSounds = !sharedVariables.makeMoveSounds;
			makemovesounds.setSelected(sharedVariables.makeMoveSounds);

		} else if (action.equals("Sounds for Draw Offers")) {
			sharedVariables.makeDrawSounds = !sharedVariables.makeDrawSounds;
			makedrawsounds.setSelected(sharedVariables.makeDrawSounds);

		} else if (action.equals("Sounds for Observed Games")) {
			sharedVariables.makeObserveSounds = !sharedVariables.makeObserveSounds;
			makeObserveSounds.setSelected(sharedVariables.makeObserveSounds);

		} else if (action.equals("Sounds")) {
			sharedVariables.makeSounds = !sharedVariables.makeSounds;

		} else if (action.equals("Start Powerout")) {

			// JFrame aframe = new JFrame();
			// aframe.setVisible(true);
			poweroutframe frame = new poweroutframe(sharedVariables.poweroutSounds);
			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// frame.pack();
			sharedVariables.desktop.add(frame);
			frame.setVisible(true);
			/*
			 * JComponent newContentPane = new poweroutpanel();
			 * newContentPane.setOpaque(true); //content panes must be opaque
			 * frame.setContentPane(newContentPane);
			 */
			try {
				frame.setSelected(true);
				// aa.Input.setFocusable(true);
			} catch (Exception e) {
			}
			frame.setSize(700, 550);
			// frame.setExtendedState(Frame.MAXIMIZED_BOTH);

		} else if (action.equals("Start MineSweeper")) {

			minesweeper10 frame = new minesweeper10(sharedVariables, this);

			sharedVariables.desktop.add(frame);

			try {
				frame.setSelected(true);
				// aa.Input.setFocusable(true);
			} catch (Exception e) {
			}

		} else if (action.equals("Opening Explorer")) {

			openBook();
		}

		else if (action.equals("Start Connect Four")) {

			connectFour frame = new connectFour();

			sharedVariables.desktop.add(frame);

			try {
				frame.setSelected(true);
				// aa.Input.setFocusable(true);
			} catch (Exception e) {
			}

		} else if (action.equals("Start Mastermind")) {

			mastermind11 frame = new mastermind11();

			sharedVariables.desktop.add(frame);

			try {
				frame.setSelected(true);
				// aa.Input.setFocusable(true);
			} catch (Exception e) {
			}

		} else if (action.equals("Console Colors")) {

			// JDialog frame = new JDialog();
			// sharedVariables.shoutcolor =
			// JColorChooser.showDialog(frame, "Choose Shout Color",
			// sharedVariables.shoutcolor);
			customizeConsolelColorsDialog frame = new customizeConsolelColorsDialog((JFrame) this, false,
					sharedVariables, consoles, gameconsoles);
			frame.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);

		} else if (action.equals("Notify and Events Background Color")) {
			// BackColor
			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Choose " + action, sharedVariables.listColor);
			if (newColor != null)
				sharedVariables.listColor = newColor;
			if (sharedVariables.activitiesPanel != null)
				sharedVariables.activitiesPanel.setColors();
			if (myTopGamesFrame != null)
				myTopGamesFrame.theEventsList.setBackground(sharedVariables.listColor);
			if (myNotifyFrame != null)
				myNotifyFrame.notifylistScrollerPanel.theNotifyList.setBackground(sharedVariables.listColor);

		} else if (action.equals("Main Background")) {
			// BackColor
			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Choose Main Background Color",
					sharedVariables.MainBackColor);
			if (newColor != null)
				sharedVariables.MainBackColor = newColor;

			sharedVariables.desktop.setBackground(sharedVariables.MainBackColor);

			// Andrey says:
			// merge the font settings
		} else if (action.equals("Change Font") || action.equals("Change Tab Font")
				|| action.equals("Change Input Font") || action.equals("Game Board Font")
				|| action.equals("Game Clock Font")) {
			int fontsetting = (action.equals("Change Font") ? 0
					: (action.equals("Change Tab Font") ? 1
							: (action.equals("Change Input Font") ? 2 : (action.equals("Game Board Font") ? 3 : 4))));
			Font fontchanged = (fontsetting == 0 ? sharedVariables.myFont
					: (fontsetting == 1 ? sharedVariables.myTabFont
							: (fontsetting == 2 ? sharedVariables.inputFont
									: (fontsetting == 3 ? sharedVariables.myGameFont
											: sharedVariables.myGameClockFont))));
			JFrame f = new JFrame(action);
			FontChooser2 fc = new FontChooser2(f, fontchanged);
			fc.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
			fc.setVisible(true);
			Font fnt = fc.getSelectedFont();
			if (fnt != null) {
				if (fontsetting == 0) {
					sharedVariables.myFont = fnt;
					for (int i = 0; i < sharedVariables.openConsoleCount; i++) {
						if (consoles[i] != null && sharedVariables.tabStuff[i].tabFont == null) {
							consoles[i].setFont(sharedVariables.myFont);
						}
					}

					// now game boards
					for (int i = 0; i < sharedVariables.openBoardCount; i++) {
						if (gameconsoles[i] != null) {
							gameconsoles[i].setFont(sharedVariables.myFont);
						}
					}

				} else if (fontsetting == 1) {
					sharedVariables.myTabFont = fnt;
					repaintTabs();

				} else if (fontsetting == 2) {
					sharedVariables.inputFont = fnt;
					setInputFont();

				} else {
					if (fontsetting == 3)
						sharedVariables.myGameFont = fnt;
					else
						sharedVariables.myGameClockFont = fnt;

					// now game boards
					for (int i = 0; i < sharedVariables.openBoardCount; i++) {
						if (myboards[i] != null) {
							myboards[i].mycontrolspanel.setFont();
						}
					}
				}
			} // end if font not null

			/*
			 * } else if (action.equals("Change Tab Font")) { JFrame f = new
			 * JFrame("FontChooser Startup"); FontChooser2 fc = new FontChooser2(f,
			 * sharedVariables.myTabFont); fc.setVisible(true); Font fnt =
			 * fc.getSelectedFont(); if (fnt != null) { sharedVariables.myTabFont=fnt;
			 * repaintTabs(); }// end if font not null
			 * 
			 * } else if (action.equals("Change Input Font")) { JFrame f = new
			 * JFrame("FontChooser Startup"); FontChooser2 fc = new FontChooser2(f,
			 * sharedVariables.inputFont); fc.setVisible(true); Font fnt =
			 * fc.getSelectedFont(); if (fnt != null) { sharedVariables.inputFont=fnt;
			 * setInputFont(); }// end if font not null
			 * 
			 * } else if (action.equals("Game Board Font")) { JFrame f = new
			 * JFrame("FontChooser Startup"); FontChooser2 fc = new FontChooser2(f,
			 * sharedVariables.myGameFont); fc.setVisible(true); Font fnt =
			 * fc.getSelectedFont(); if (fnt != null) { sharedVariables.myGameFont=fnt;
			 * 
			 * // now game boards for (int i=0; i < sharedVariables.openBoardCount; i++) {
			 * if (myboards[i] != null) { myboards[i].mycontrolspanel.setFont(); } } }// not
			 * null font
			 * 
			 * } else if (action.equals("Game Clock Font")) { JFrame f = new
			 * JFrame("FontChooser Startup"); FontChooser2 fc = new FontChooser2(f,
			 * sharedVariables.myGameClockFont); fc.setVisible(true); Font fnt =
			 * fc.getSelectedFont(); if (fnt != null) { sharedVariables.myGameClockFont=fnt;
			 * 
			 * // now game boards for (int i=0; i < sharedVariables.openBoardCount; i++) {
			 * if (myboards[i] != null) { myboards[i].mycontrolspanel.setFont(); } } }// not
			 * null font
			 */

		} else if (action.equals("Channel Colors")) {
			customizeChannelColorDialog frame = new customizeChannelColorDialog((JFrame) this, false, sharedVariables,
					consoles);
			frame.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
			// frame.setSize(300,250);
			frame.setVisible(true);

			// Andrey says:
			// moved the for loop to the end of the action performed method
		} else {
			for (int openBoardMenu = 0; openBoardMenu < sharedVariables.maxGameTabs; openBoardMenu++) {
				if (myboards[openBoardMenu] == null)
					break;
				if (sharedVariables.openBoards[openBoardMenu] != null
						&& action.equals(sharedVariables.openBoards[openBoardMenu].getText())) {
					try {
						myboards[openBoardMenu].setSelected(true);
						break;
					} catch (Exception duiii) {
					}
				} // end if
					// end for
			}
		}
	}// end action performed method

	void setEventListFont() {
		JFrame f = new JFrame("Event List/Tournaments Font");
		if (channels.fics) {
			f = new JFrame("Activities Font");
		}
		FontChooser2 fc = new FontChooser2(f, sharedVariables.eventsFont);
		fc.setVisible(true);
		Font fnt = fc.getSelectedFont();
		if (fnt != null) {
			sharedVariables.eventsFont = fnt;
			sharedVariables.activitiesPanel.theEventsList.setFont(sharedVariables.eventsFont);
		}
		// end events font
	}

	void setApplicationColor() {
		try {
			JDialog frame = new JDialog();
			Color newColor = JColorChooser.showDialog(frame, "Application Color", sharedVariables.MainBackColor);
			if (newColor != null) {
				sharedVariables.MainBackColor = newColor;
				sharedVariables.wallpaperImage = null;
				sharedVariables.wallpaperFileName = "";
				repaint();
			}
		} catch (Exception e) {
		}
	}

	void runFlipCommand() {
		boolean sentFicsFlip = false;
		for (int a = 0; a < sharedVariables.maxGameTabs && a < myboards.length; a++) {
			if (myboards[a] != null && ((myboards[a].isVisible() && myboards[a].isSelected()) || channels.fics)) {
				int targetBoard = myboards[a].gameData.LookingAt;
				if (channels.fics) {
					targetBoard = myboards[a].gameData.BoardIndex;
				}
				int flipPlus = (sharedVariables.mygame[targetBoard].iflipped + 1) % 2;
				String flip = "" + flipPlus;
				String icsGameNumber = "" + sharedVariables.mygame[targetBoard].myGameNumber;
				myboards[targetBoard].flipSent(icsGameNumber, flip);
				myboards[a].mypanel.repaint();
				myboards[a].mycontrolspanel.repaint();
				if (channels.fics && !sentFicsFlip) {
					myoutput output = new myoutput();
					output.data = "$Flip\n";
					queue.add(output);
					sentFicsFlip = true;
				}
				if (!channels.fics) {
					break;
				}

			} // end selected
		}
	}

	void setWallPaper() {
		try {
			File file = new File("/System/Library/Desktop Pictures/Solid Colors");
			String filePath = "";
			if (file.exists()) {
				filePath = "/System/Library/Desktop Pictures//Solid Colors";
			}

			if (filePath.equals("")) {
				file = new File("/Library/Desktop Pictures/");
				if (file.exists()) {
					filePath = "/Library/Desktop Pictures/";
				}
			}
			JFileChooser fc;
			if (filePath.equals("")) {
				fc = new JFileChooser();
			} else {
				fc = new JFileChooser(filePath);
			}
			fc.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
			fc.setFileFilter(new FileFilter() {
				public boolean accept(File f) {
					String fname = f.getName().toLowerCase();
					return (fname.endsWith(".jpg") || fname.endsWith(".jpeg") || fname.endsWith(".img")
							|| fname.endsWith(".png") || f.isDirectory());
				}

				public String getDescription() {
					return "Image Files (*.img, *.jpg, *.img, *.png)";
				}
			});
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				sharedVariables.wallpaperFile = fc.getSelectedFile();
				sharedVariables.addWallPaper();
				// end stand alone
			}

			repaint();
			// end try
		} catch (Exception e) {
		}
	}

	void openSeekAGame() {
		if (channels.fics && DataParsing.inFicsExamineMode) {
			String swarning = "To seek games exit examine mode first. Go to Game Menu / Unexamine at top.";
			Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);
			pframe.setVisible(true);
			return;
		}
		if (myseeker != null && myseeker.isVisible()) {
			myseeker.dispose();
			myseeker = null;
			return;
		}
		myseeker = new seekGameDialog(this, false, sharedVariables, queue);
		int defaultWidth = 425;
		int defaultHeight = 220;
		myseeker.setSize(defaultWidth, defaultHeight);
		myseeker.setLocation(getLocation().x + getSize().width / 2 - defaultWidth / 2,
				getLocation().y + getSize().height / 2 - defaultHeight / 2);

		/*
		 * try { Toolkit toolkit = Toolkit.getDefaultToolkit(); Dimension dim =
		 * toolkit.getScreenSize(); int screenW = dim.width; int screenH = dim.height;
		 * int px = (int) ((screenW - defaultWidth) / 2); if (px < 50) px = 50; int py =
		 * (int) ((screenH - defaultHeight) / 2); if (py < 50) py=50;
		 * 
		 * myseeker.setLocation(px, py); } catch (Exception centerError) {}
		 */

		myseeker.setTitle("Seek a Game");

		myseeker.setVisible(true);

	}

	void openActivities() {
		try {
			// if(myfirstlist == null)
			// mycreator.createListFrame(eventsList, seeksList, computerSeeksList,
			// notifyList, this);
			if (!myfirstlist.isVisible() && !mysecondlist.isVisible()) {
				mycreator.createListFrame(eventsList, seeksList, computerSeeksList, notifyList, tournamentList, this);
			} else if (mysecondlist.isVisible() && !mysecondlist.isSelected())
				mysecondlist.setSelected(true);
			else if (mysecondlist.isVisible()) {
				mysecondlist.setBoardSize();
				mysecondlist.setVisible(false);

			} else if (myfirstlist.isVisible()) {
				myfirstlist.setBoardSize();
				myfirstlist.setVisible(false);
			}
			sharedVariables.activitiesPanel.setColors();
			if (sharedVariables.activitiesTabNumber == 4) {
				sharedVariables.activitiesPanel.theEventsList.setModel(tournamentList.eventsTable);
				sharedVariables.activitiesPanel.setEventTournamentTableProperties();
				sharedVariables.activitiesPanel.videoButton.setText("Tournament Schedule");
				sharedVariables.activitiesPanel.removeActionListeners(sharedVariables.activitiesPanel.videoButton);
				sharedVariables.activitiesPanel.videoButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sharedVariables.openUrl("https://www.chessclub.com/help/tournaments");
					}
				});
			}
			// myfirstlist.setSelected(true);
		} catch (Exception dui) {
		}
	}

	void makeEngineWarning(boolean usingJavaEngine) {
		String swarning = "You must be in examine or observe mode to load an engine. Go to Game menu Examine or Examine Last Game. "
				+ "You may need to click on the board and game tab that is in this mode as well first.";
		if (usingJavaEngine) {
			swarning = "You must be in examine mode to load a preinstalled engine. "
					+ "If you load a UCI engine you can use it in observe mode too.\n"
					+ "You also need to click on the board and game tab that is in this mode as well first.";
		}
		Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);
		pframe.setVisible(true);
	}

	void makeEngineWarning2() {
		String swarning = "You must not be playing to load an engine.";
		Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);
		pframe.setVisible(true);
	}

	void makeEngineWarningCorrespondence() {
		String swarning = "Played correspondence games can't be analyzed. Refresh the cc-list on the Correspondence tab of Activities if the state has changed.";
		Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);
		pframe.setVisible(true);
	}

	boolean isCorrespondencePlayedGame(String wName, String bName) {
		wName = wName.toLowerCase();
		bName = bName.toLowerCase();
		if (!wName.equals(sharedVariables.whoAmI.toLowerCase())
				&& !bName.equals(sharedVariables.whoAmI.toLowerCase())) {
			return false;
		}
		try {
			for (int a = 0; a < sharedVariables.ccListData.size(); a++) {
				Vector<String> game = sharedVariables.ccListData.get(a);
				if (game.get(2).toLowerCase().equals(wName) && game.get(4).toLowerCase().equals(bName)) {
					if (game.get(game.size() - 1).trim().equals("") || game.get(game.size() - 1).trim().contains("?"))
						return true;
				}
			}
		} catch (Exception dui) {
		}
		return false;
	}

	void setBoard(int type) {
		/*
		 * if (type == 0) solidboard.setSelected(true); else
		 * solidboard.setSelected(false);
		 * 
		 * if (type == 1) woodenboard1.setSelected(true); else
		 * woodenboard1.setSelected(false);
		 * 
		 * if (type == 2) woodenboard2.setSelected(true); else
		 * woodenboard2.setSelected(false);
		 * 
		 * if (type == 3) woodenboard3.setSelected(true); else
		 * woodenboard3.setSelected(false);
		 * 
		 * if (type == 4) grayishboard.setSelected(true); else
		 * grayishboard.setSelected(false);
		 * 
		 * if (type == 5) board5.setSelected(true); else board5.setSelected(false);
		 * 
		 * if (type == 6) board6.setSelected(true); else board6.setSelected(false);
		 * 
		 * if (type == 7) board7.setSelected(true); else board7.setSelected(false);
		 * 
		 * if (type == 8) oliveboard.setSelected(true); else
		 * oliveboard.setSelected(false);
		 * 
		 * if (type == 9) cherryboard.setSelected(true); else
		 * cherryboard.setSelected(false);
		 * 
		 * if (type == 10) purpleboard.setSelected(true); else
		 * purpleboard.setSelected(false);
		 */

		boardarray[type].setSelected(true);

		for (int a = 0; a < sharedVariables.maxGameTabs; a++)
			if (myboards[a] != null)
				if (myboards[a].isVisible())
					if (myboards[a].mypanel != null)
						myboards[a].mypanel.repaint();

	}

	void openSeekGraph() {
		try {
			if (!seekGraph.isVisible()) {
				seekGraph.setSize(sharedVariables.mySeekSizes.con0x, sharedVariables.mySeekSizes.con0y);
				seekGraph.setLocation(sharedVariables.mySeekSizes.point0.x, sharedVariables.mySeekSizes.point0.y);
				seekGraph.setVisible(true);
				seekGraph.setSelected(true);
				// seekGraph.setSize(600,600);
			} else if (!seekGraph.isSelected()) {
				seekGraph.setSelected(true);
			} else {
				seekGraph.setBoardSize();
				seekGraph.setVisible(false);
			}
		} catch (Exception dummyseek) {
		}
	}

	void resetConsoleLayout() {
		/*
		 * if (sharedVariables.consoleLayout == 1) { tabLayout1.setSelected(true);
		 * tabLayout2.setSelected(false); tabLayout3.setSelected(false); } else if
		 * (sharedVariables.consoleLayout == 2) { tabLayout1.setSelected(false);
		 * tabLayout2.setSelected(true); tabLayout3.setSelected(false); } else {
		 * tabLayout1.setSelected(false); tabLayout2.setSelected(false);
		 * tabLayout3.setSelected(true); }
		 */

		tabLayout1.setSelected((sharedVariables.consoleLayout == 1));
		tabLayout2.setSelected((sharedVariables.consoleLayout == 2));
		tabLayout3.setSelected((sharedVariables.consoleLayout == 3));

		for (int a = 0; a < sharedVariables.maxConsoleTabs; a++)
			if (consoleSubframes[a] != null)
				consoleSubframes[a].overall.recreate(sharedVariables.consolesTabLayout[a]);
	}

	void openBook() {
		boolean installed = false;
		boolean old = false;
		File f = new File(channels.privateDirectory + channels.openingBookName);
		if (f.exists() && !f.isDirectory()) {
			installed = true;
			old = false;
		}
		if (installed == false) {

			f = new File(channels.privateDirectory + channels.oldOpeningBookName);
			if (f.exists() && !f.isDirectory()) {
				installed = true;
				old = true;
			}
		} // installed false

		if (!installed) {
			InstallBookDialog myDialog = new InstallBookDialog(this, InstallBookDialog.openingBook18,
					sharedVariables.myFont);
			myDialog.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
			myDialog.setVisible(true);
		} else if (sharedVariables.myOpeningBookView == null) {
			sharedVariables.myOpeningBookView = new OpeningBookView(this, queue, old);
			sharedVariables.myOpeningBookView.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
			sharedVariables.myOpeningBookView.setVisible(true);

		} else if (sharedVariables.myOpeningBookView.isVisible()) {
			sharedVariables.myOpeningBookView.setVisible(false);
		} else {
			sharedVariables.myOpeningBookView.setVisible(true);
		}
		if (sharedVariables.myOpeningBookView != null && sharedVariables.myOpeningBookView.isVisible()) {
			sharedVariables.myOpeningBookView.update();
		}
	}

	void stopTheEngine() {
		if (sharedVariables.engineOn) {

			myoutput outgoing = new myoutput();
			outgoing.data = "exit\n";

			sharedVariables.engineQueue.add(outgoing);

			myoutput outgoing2 = new myoutput();
			outgoing2.data = "quit\n";

			sharedVariables.engineQueue.add(outgoing2);
			sharedVariables.engineOn = false;
		}

	}

	void startStockfish() {
		if (sharedVariables.engineOn) {
			return;
		}
		boolean installed = false;
		File f = new File(channels.privateDirectory + sharedVariables.stockfishName);
		if (f.exists() && !f.isDirectory()) {
			installed = true;
		}
		if (!installed) {
			if (sharedVariables.operatingSystem.equals("unix")) {
				return;
			}
			InstallBookDialog myDialog = new InstallBookDialog(this, InstallBookDialog.stockfish8,
					sharedVariables.myFont);
			myDialog.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
			myDialog.setVisible(true);
		} else {
			sharedVariables.uci = true;
			if (sharedVariables.operatingSystem.equals("mac") || sharedVariables.operatingSystem.equals("unix")) {
				try {
					String lookup = getClass().getProtectionDomain().getCodeSource().getLocation() + "";
					System.out.println("mike says lookup is " + lookup);
					if (lookup.length() > 7) {
						lookup = lookup.substring(5, lookup.length());
					}
					int b = 0;
					int c = 0;
					while (b != -1) {
						if (b + 1 >= lookup.length()) {
							break;
						}
						b = lookup.indexOf("/", b + 1);
						if (b > -1) {
							c = b;
						}
					}
					if (c > 0) {
						if (channels.macClient) {
							lookup = channels.privateDirectory + sharedVariables.stockfishName;
							System.out.println("mike says lookup is now " + lookup);
						} else {
							lookup = lookup.substring(0, c) + "/" + sharedVariables.stockfishName;
						}

					}

					// System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation()
					// + "/" + sharedVariables.stockfishName);
					System.out.println(lookup);
					File f2 = new File(lookup);
					sharedVariables.engineFile = f2;
				} catch (Exception duii) {
				}
			} else {
				sharedVariables.engineFile = f;
			}
			startTheEngine(false);

		}
	}

	void restartEngine() {
		boolean usingJavaEngine = false;
		if (sharedVariables.engineFile != null)
			if (sharedVariables.engineFile.toString().endsWith(sharedVariables.mediocreEngineName)
					|| sharedVariables.engineFile.toString().endsWith(sharedVariables.cuckooEngineName))
				usingJavaEngine = true;

		if (sharedVariables.engineFile == null) {
			if (sharedVariables.operatingSystem.equals("mac") || sharedVariables.operatingSystem.equals("win")) {
				startStockfish();
				return;
			}
			String swarning = "Load an engine before trying to restart it.";
			Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);
			pframe.setVisible(true);
			return;
		}
		if (!sharedVariables.engineOn)
			startTheEngine(usingJavaEngine);
		else {
			String swarning = "Stop the engine before trying to restart it.";
			Popup pframe = new Popup((JFrame) this, true, swarning, sharedVariables);
			pframe.setVisible(true);
		}
	}

	void startTheEngine(boolean usingJavaEngine) {
		boolean go = false;
		int aa;
		for (aa = 0; aa < sharedVariables.openBoardCount; aa++) {
			if (sharedVariables.mygame[aa].state == sharedVariables.STATE_PLAYING) {
				sharedVariables.engineOn = false;
				makeEngineWarning2();
				return;
			}
		}
		for (aa = 0; aa < sharedVariables.openBoardCount; aa++) {
			if (sharedVariables.mygame[aa].state == sharedVariables.STATE_EXAMINING) {
				if (sharedVariables.mygame[aa].time == 0 && sharedVariables.mygame[aa].inc == 0) {
					if (isCorrespondencePlayedGame(sharedVariables.mygame[aa].realname1,
							sharedVariables.mygame[aa].realname2)) {
						sharedVariables.engineOn = false;
						makeEngineWarningCorrespondence();
						return;
					}
				}
			}
		}
		int visibleBoardCount = 0;
		for (aa = 0; aa < sharedVariables.openBoardCount; aa++) {
			if (myboards[aa].isVisible()) {
				visibleBoardCount++;
			}
		}
		for (int a = 0; a < sharedVariables.openBoardCount; a++) {
			if (myboards[a].isSelected() || (myboards[a].isVisible() && visibleBoardCount == 1)) {
				if (sharedVariables.mygame[myboards[a].gameData.LookingAt] != null
						&& (sharedVariables.mygame[myboards[a].gameData.LookingAt].state == sharedVariables.STATE_EXAMINING
								|| (sharedVariables.mygame[myboards[a].gameData.LookingAt].state == sharedVariables.STATE_OBSERVING
										&& !usingJavaEngine))) {
					try {
						go = true;
						myoutput tosend = new myoutput();

						try {
							tosend = sharedVariables.engineQueue.poll();
							// we look for data from other areas of the program
							while (tosend != null)
								tosend = sharedVariables.engineQueue.poll();

						} catch (Exception duiii) {
						}

						sharedVariables.engineBoard = myboards[a].gameData.LookingAt;
						myboards[myboards[a].gameData.LookingAt].startEngine();

					} catch (Exception e) {
					}
					break;
				}
			}
		}

		if (!go) {
			sharedVariables.engineOn = false;
			makeEngineWarning(usingJavaEngine);
		} // if go false
	}

	void repaintTabBorders() {
		for (int a = 0; a < sharedVariables.openBoardCount; a++)
			if (myboards[a] != null && myboards[a].isVisible())
				myboards[a].myconsolepanel.channelTabs[a].repaint();

		// now update consoles
		for (int a = 0; a < sharedVariables.openConsoleCount; a++)
			if (consoleSubframes[a] != null && consoleSubframes[a].isVisible())
				for (int aa = 0; aa < sharedVariables.maxConsoleTabs; aa++)
					consoleSubframes[a].channelTabs[aa].repaint();
	}

	void openAddAFriend() {
		addFriendDialog frame = new addFriendDialog(this, false, sharedVariables, queue);
		frame.setLocation(getLocation().x + sharedVariables.cornerDistance,
				getLocation().y + sharedVariables.cornerDistance);

	}

	void checkItalicsBehavior(int n) {
		/*
		 * if (n == 0) italicsBehavior[0].setSelected(true); else
		 * italicsBehavior[0].setSelected(false);
		 * 
		 * if (n == 1) italicsBehavior[1].setSelected(true); else
		 * italicsBehavior[1].setSelected(false);
		 * 
		 * if (n == 2) italicsBehavior[2].setSelected(true); else
		 * italicsBehavior[2].setSelected(false);
		 */
		if (n >= 0 && n < italicsBehavior.length)
			italicsBehavior[n].setSelected(true);
	}

	void redrawBoard(int type) {
		/*
		 * if (type == 0) boardconsole0.setSelected(true); else
		 * boardconsole0.setSelected(false);
		 * 
		 * if (type == 1) boardconsole1.setSelected(true); else
		 * boardconsole1.setSelected(false);
		 * 
		 * if (type == 2) boardconsole2.setSelected(true); else
		 * boardconsole2.setSelected(false);
		 * 
		 * if (type == 3) boardconsole3.setSelected(true); else
		 * boardconsole3.setSelected(false);
		 */
		if (type >= 0 && type < boardconsolearray.length)
			boardconsolearray[type].setSelected(true);

		for (int a = 0; a < sharedVariables.maxGameTabs; a++)
			if (myboards[a] != null && myboards[a].isVisible())
				myboards[a].recreate();
	}

	void setPieces(int type) {
		/*
		 * if (type == 0) pieces1.setSelected(true); else pieces1.setSelected(false);
		 * 
		 * if (type == 1) pieces2.setSelected(true); else pieces2.setSelected(false);
		 * 
		 * if (type == 2) pieces3.setSelected(true); else pieces3.setSelected(false);
		 * 
		 * if (type == 3) pieces4.setSelected(true); else pieces4.setSelected(false);
		 * 
		 * if (type == 4) pieces5.setSelected(true); else pieces5.setSelected(false);
		 * 
		 * if (type == 5) pieces6.setSelected(true); else pieces6.setSelected(false);
		 * 
		 * if (type == 6) pieces7.setSelected(true); else pieces7.setSelected(false);
		 * 
		 * if (type == 7) pieces8.setSelected(true); else pieces8.setSelected(false);
		 * 
		 * if (type == 8) pieces9.setSelected(true); else pieces9.setSelected(false);
		 * 
		 * if (type == 9) pieces10.setSelected(true); else pieces10.setSelected(false);
		 * 
		 * if (type == 10) pieces11.setSelected(true); else pieces11.setSelected(false);
		 * 
		 * if (type == 11) pieces12.setSelected(true); else pieces12.setSelected(false);
		 * 
		 * if (type == 12) pieces13.setSelected(true); else pieces13.setSelected(false);
		 * 
		 * if (type == 13) pieces14.setSelected(true); else pieces14.setSelected(false);
		 * 
		 * if (type == 14) pieces15.setSelected(true); else pieces15.setSelected(false);
		 * 
		 * if (type == 15) pieces16.setSelected(true); else pieces16.setSelected(false);
		 * 
		 * if (type == 16) pieces17.setSelected(true); else pieces17.setSelected(false);
		 * 
		 * if (type == 17) pieces18.setSelected(true); else pieces18.setSelected(false);
		 * 
		 * if (type == 18) pieces19.setSelected(true); else pieces19.setSelected(false);
		 * 
		 * if (type == 19) pieces20.setSelected(true); else pieces20.setSelected(false);
		 * 
		 * if (type == 20) pieces21.setSelected(true); else pieces21.setSelected(false);
		 * 
		 * if (type == 21) pieces22.setSelected(true); else pieces22.setSelected(false);
		 * 
		 * if (type == 22) pieces23.setSelected(true); else pieces23.setSelected(false);
		 * 
		 * if (type == 23) { pieces24.setSelected(true); generateRandomPieces(type); }
		 * else { pieces24.setSelected(false); }
		 */

		if (type >= 0 && type < piecesarray.length)
			piecesarray[type].setSelected(true);

		if (type == 26)
			generateRandomPieces(type);

		for (int a = 0; a < sharedVariables.maxGameTabs; a++)
			if (myboards[a] != null)
				if (myboards[a].mypanel != null)
					if (myboards[a].isVisible())
						myboards[a].mypanel.repaint();
	}

	void setCheckersPieces(int type) {
		if (type == 1)
			checkerspiecesarray[0].setSelected(true);
		else
			checkerspiecesarray[0].setSelected(false);
		if (type == 2)
			checkerspiecesarray[1].setSelected(true);
		else
			checkerspiecesarray[1].setSelected(false);

		for (int a = 0; a < sharedVariables.maxGameTabs; a++)
			if (myboards[a] != null)
				if (myboards[a].mypanel != null)
					if (myboards[a].isVisible())
						myboards[a].mypanel.repaint();
	}

	void generateRandomPieces(int type) {
		Random randomGenerator = new Random();

		for (int a = 0; a < 12; a++) {
			int randomInt = randomGenerator.nextInt(type - 1);
			graphics.pieces[type][a] = graphics.pieces[randomInt][a];
		}
	}

	void setMySize() {
		/* check if we need to resize differently and not maximize */
		scriptLoader myloader = new scriptLoader();
		boolean valid = false;
		int width = 800;
		int height = 800;

		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension dim = toolkit.getScreenSize();
			sharedVariables.screenW = dim.width;
			sharedVariables.screenH = dim.height;

		} catch (Exception badtool) {
			return;
		}
		try {
			// Andrey says:
			// want to be able to change this to
			// List<String> myArray = new ArrayList<String>();
			ArrayList<String> myArray = new ArrayList();

			myloader.loadScript(myArray, "lantern_sizing.ini");
			try {
				if (myArray.size() > 1) {
					width = Integer.parseInt(myArray.get(0));
					height = Integer.parseInt(myArray.get(1));
					if (width > 200 && height > 200 && width < sharedVariables.screenW - 100
							&& height < sharedVariables.screenH - 50) {
						valid = true;
						sharedVariables.screenW = width;
						sharedVariables.screenH = height;
					} else {
						width = 800;
						height = 800;
					}
				} // end size of array
					// end try
			} catch (Exception wrongsize) {
				width = 800;
				height = 800;
			}

			ArrayList<String> myArray2 = new ArrayList();

			myloader.loadScript(myArray2, channels.privateDirectory + "lantern_default_size.ini");

			if (!valid) {
				try {
					if (myArray2.size() > 1) {
						width = Integer.parseInt(myArray2.get(0));
						height = Integer.parseInt(myArray2.get(1));
						if (width > 200 && height > 200 && width < sharedVariables.screenW - 100
								&& height < sharedVariables.screenH - 50) {
							valid = true;
							sharedVariables.screenW = width;
							sharedVariables.screenH = height;
						} else {
							width = (int) sharedVariables.screenW * 3 / 4;
							height = (int) sharedVariables.screenH * 3 / 4;
						}
					} // end size of array
						// end try
				} catch (Exception wrongsize) {
					width = (int) sharedVariables.screenW * 3 / 4;
					height = (int) sharedVariables.screenH * 3 / 4;
				}

			}

			if (!valid) {
				if (sharedVariables.screenW > 1920 && sharedVariables.screenH > 1080) {
					setSize(1920, 1080);
					sharedVariables.screenW = 1920;
					sharedVariables.screenH = 1080;
				} else if (sharedVariables.screenW > 1400 && sharedVariables.screenH > 900) {
					setSize(1400, 900);
					sharedVariables.screenW = 1400;
					sharedVariables.screenH = 900;
				} else {
					setSize(width, height);
				}

			} else {
				setSize(width, height);
			}

			int locX = 0;
			int locY = 0;
			if (valid) {
				try {
					if (myArray2.size() > 3) {
						locX = Integer.parseInt(myArray2.get(2));
						locY = Integer.parseInt(myArray2.get(3));
						Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
						if (locX + width <= size.width && locY + height <= size.height) {
							setLocation(locX, locY);
						}
					} // end size of array
						// end try
				} catch (Exception wrongsize) {

				}

			}
			if (!valid) {
				if (sharedVariables.operatingSystem.equals("unix")) {
					setVisible(true);
					// unix needs the window to be visible before maximized
					setLocation(0, 0);
					// put it in top corner to hopefully fix a bug on some linux
					// that mouse and menu got out of snych
				}
			}
			// end outer try
		} catch (Exception d) {
			setSize(width, height);
			if (!valid) {
				setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

		} // end catch
	}// end method set size

	public void windowClosed(WindowEvent e) {

		// if(sharedVariables.engineOn == true)
		sendToEngine("stop\n");
		sendToEngine("exit\n");
		sendToEngine("quit\n");
	}

	public void windowDeactivated(WindowEvent e) {

	}

	public void windowDeiconified(WindowEvent e) {

	}

	public void windowClosing(WindowEvent e) {
		windowClosingHandler();
	}

	public void windowClosingHandler() {
		// if(sharedVariables.engineOn == true)
		sendToEngine("stop\n");
		sendToEngine("exit\n");
		sendToEngine("quit\n");
		if (!sharedVariables.standAlone) {
			System.exit(0);
		} else {
			JSettingsDialog frame = new JSettingsDialog((JFrame) this, false, sharedVariables);
			frame.setLocation(getLocation().x + getSize().width / 2 - 100,
					getLocation().y + getSize().height / 2 - 100);
			frame.setVisible(true);
		}

	}

	/********************* Console Events **********************************/

	void compactConsole() {
		sharedVariables.boardConsoleType = 1;
		redrawBoard(sharedVariables.boardConsoleType);
	}

	void normalConsole() {
		sharedVariables.boardConsoleType = 2;
		redrawBoard(sharedVariables.boardConsoleType);
	}

	void largerConsole() {
		sharedVariables.boardConsoleType = 3;
		redrawBoard(sharedVariables.boardConsoleType);
	}

	void sideConsole(int val) {
		/*
		 * if (sharedVariables.sideways == true) { sharedVariables.sideways=false;
		 * sidewaysconsole.setSelected(false); } else { sharedVariables.sideways=true;
		 * sidewaysconsole.setSelected(true); }
		 */
		sharedVariables.sideways = val;
		sidewaysconsole.setSelected(true);

		redrawBoard(sharedVariables.boardConsoleType);
	}

	/****************************
	 * end console events
	 ********************************/

	class JSettingsDialog extends JFrame {

		channels sharedVariables;

		JSettingsDialog(JFrame frame, boolean mybool, channels sharedVariables1) {
			// super(frame, false);

			sharedVariables = sharedVariables1;
			JPanel pane = new JPanel();
			JLabel tosave = new JLabel("Save Settings?");
			JButton yes = new JButton("Yes");
			JButton no = new JButton("No");
			yes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						try {
							mysettings.saveNow(myboards, consoleSubframes, sharedVariables);
							mineScores.saveNow(sharedVariables);
							saveMainApplicationWindowSize();
							if (sharedVariables.myOpeningBookView != null) {
								sharedVariables.myOpeningBookView.closeDatabase();
							}
						} catch (Exception d) {
						}
						System.exit(0);
						dispose();
						// end try
					} catch (Exception e) {
					}
				}
			});
			no.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						if (sharedVariables.myOpeningBookView != null) {
							sharedVariables.myOpeningBookView.closeDatabase();
						}
						System.exit(0);
						dispose();
						// end try
					} catch (Exception e) {
					}
				}
			});
			pane.setLayout(new GridLayout(3, 1)); // rows collums
			pane.add(tosave);
			pane.add(yes);
			pane.add(no);
			add(pane);
			setSize(200, 200);

			storeCurrentSizes();

			setAlwaysOnTop(true);
		}// end constructor
	}// end class

	void storeCurrentSizes() {
		try {
			for (int b = 0; b < myboards.length; b++) {
				if (myboards[b] != null) {
					if (myboards[b].isVisible() && !myboards[b].isMaximum())
						myboards[b].setBoardSize();
				} else {
					// sharedVariables.openBoardCount=b;
					break;
				}
			}

			for (int b = 0; b < sharedVariables.maxConsoleTabs; b++)
				if (consoleSubframes[b] != null && consoleSubframes[b].isVisible() && !consoleSubframes[b].isMaximum())
					consoleSubframes[b].setBoardSize();

			sharedVariables.activitiesOpen = false;
			if (myfirstlist != null && myfirstlist.isVisible()) {
				sharedVariables.activitiesOpen = true;
				myfirstlist.setBoardSize();
			}

			if (mysecondlist != null && mysecondlist.isVisible()) {
				sharedVariables.activitiesOpen = true;
				mysecondlist.setBoardSize();
			}

			sharedVariables.seeksOpen = false;
			if (seekGraph != null && seekGraph.isVisible()) {
				sharedVariables.seeksOpen = true;
				seekGraph.setBoardSize();
			}
			if (myNotifyFrame != null && myNotifyFrame.isVisible()) {
				myNotifyFrame.saveSize();
			}
		} catch (Exception dui) {
			System.out.println(dui.getMessage());
		}

	}

	void saveMainApplicationWindowSize() {
		try {
			int screenW = 0;
			int screenH = 0;
			int locX = 0;
			int locY = 0;
			try {

				screenW = getWidth();
				screenH = getHeight();
				locX = getLocation().x;
				locY = getLocation().y;

			} catch (Exception badtool) {

			}
			FileWrite writer = new FileWrite();
			String outputSizes = "" + screenW + "\n" + screenH + "\n" + locX + "\n" + locY + "\n";
			writer.write(outputSizes, channels.privateDirectory + "lantern_default_size.ini");
		} catch (Exception dumb) {
		}

	}

	public void windowOpened(WindowEvent e) {

	}

	public void windowIconified(WindowEvent e) {

	}

	public void windowActivated(WindowEvent e) {
		myoutput data = new myoutput();
		data.data = "\n";
		data.reconnectTry = 1;
		queue.add(data);
	}

	void setButtonTitle(int a) {
		String buttonTitle = " " + a + " ";
		/*
		 * if (!sharedVariables.userButtonCommands[a].equals("") &&
		 * sharedVariables.showButtonTitle) { buttonTitle = "" + a + " - "; if
		 * (sharedVariables.userButtonCommands[a].length() > 11) buttonTitle +=
		 * sharedVariables.userButtonCommands[a].substring(0,11); else buttonTitle +=
		 * sharedVariables.userButtonCommands[a]; }
		 */
		sharedVariables.mybuttons[a].setText(buttonTitle);
	}

	void launchScripterDialog() {

		if (mybox == null) {
			mybox = new toolboxDialog(this, false, queue, sharedVariables);
			mybox.setVisible(true);
		} else if (mybox.isVisible())
			mybox.setVisible(false);
		else
			mybox.setVisible(true);

	}

	void launchUserButtonDialog() {
		if (mydialog == null || !mydialog.isVisible()) {
			mydialog = new userButtonsDialog((JFrame) this, sharedVariables);
			mydialog.setSize(400, 400);
			mydialog.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
			mydialog.setVisible(true);
		} else {
			mydialog.dispose();
			mydialog = null;
		}

	}

	void launchTopGames() {
		myTopGamesFrame.setBackground(sharedVariables.listColor);
		if (myTopGamesFrame.isVisible())
			myTopGamesFrame.setVisible(false);
		else {
			if (myTopGamesFrame.getLocation().x == 1 && myTopGamesFrame.getLocation().y == 1) {
				myTopGamesFrame.setLocation(getLocation().x + sharedVariables.cornerDistance,
						getLocation().y + sharedVariables.cornerDistance);
			}
			myTopGamesFrame.setVisible(true);

		}

	}

	void launchNotifyWindow() {
		myNotifyFrame.notifylistScrollerPanel.theNotifyList.setBackground(sharedVariables.listColor);
		int notifyWidth = 130;
		int notifyHeight = 240;
		if (sharedVariables.notifyWindowWidth > 20) {
			notifyWidth = sharedVariables.notifyWindowWidth;
		}

		if (sharedVariables.notifyWindowHeight > 20) {
			notifyHeight = sharedVariables.notifyWindowHeight;
		}
		myNotifyFrame.setSize(notifyWidth, notifyHeight);
		if (myNotifyFrame.getLocation().x == 1 && myNotifyFrame.getLocation().y == 1) {
			myNotifyFrame.setLocation(getLocation().x + sharedVariables.cornerDistance,
					getLocation().y + sharedVariables.cornerDistance);
		}
		myNotifyFrame.setVisible(!myNotifyFrame.isVisible());

	}

	class toolBarPanelClass extends JPanel {

		toolBarPanelClass(JLabel toggleEngineLabel, JButton pure1, JButton pure3, JButton pure5, JButton pure15,
				JButton pure25, JButton pure960, JLabel seeksLabel, JLabel activitesLabel, JLabel userbuttonLabel,
				JLabel scripterLabel, JLabel topGamesFlipLabel, JLabel notifyBookLabel, JToolBar toolBar) {

			JLabel rematchLabel = new JLabel("  Rematch  ");
			rematchLabel.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
						;
					else {
						myoutput output = new myoutput();
						output.data = "$Rematch\n";
						queue.add(output);
					} // end else
				}

				public void mouseReleased(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent me) {
				}

				public void mouseExited(MouseEvent me) {
				}

				public void mouseClicked(MouseEvent me) {
				}
			});
			JLabel ficsActivites = new JLabel("  Activities  ");
			ficsActivites.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
						;
					else {
						openActivities();
					} // end else
				}

				public void mouseReleased(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent me) {
				}

				public void mouseExited(MouseEvent me) {
				}

				public void mouseClicked(MouseEvent me) {
				}
			});
			GroupLayout layout = new GroupLayout(toolBar);
			SequentialGroup hgroup = layout.createSequentialGroup();
			for (int a = 1; a < 10; a++)
				hgroup.addComponent(sharedVariables.mybuttons[a]);
			hgroup.addComponent(sharedVariables.mybuttons[0]);
			hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
			hgroup.addComponent(userbuttonLabel);
			hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
			hgroup.addComponent(toggleEngineLabel);
			// hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
			// hgroup.addComponent(scripterLabel);

			hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
			hgroup.addComponent(topGamesFlipLabel);
			if (channels.fics) {
				topGamesFlipLabel.setVisible(false);
			}
			hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
			hgroup.addComponent(notifyBookLabel);

			hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
			hgroup.addComponent(seeksLabel, 100, 100, 100);
			hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
			hgroup.addComponent(activitesLabel);
			if (!channels.fics) {
				hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
				hgroup.addComponent(pure1);
				hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
				hgroup.addComponent(pure3);
				hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
				hgroup.addComponent(pure5);
				hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
				hgroup.addComponent(pure15);
				hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
				hgroup.addComponent(pure25);
				hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
				hgroup.addComponent(pure960);
			} else {
				hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
				hgroup.addComponent(rematchLabel);
				hgroup.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
				hgroup.addComponent(ficsActivites);
			}

			layout.setHorizontalGroup(hgroup);
			ParallelGroup vgroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
			vgroup.addComponent(sharedVariables.mybuttons[0]);
			for (int a = 1; a < 10; a++)
				vgroup.addComponent(sharedVariables.mybuttons[a]);
			vgroup.addComponent(userbuttonLabel);
			vgroup.addComponent(toggleEngineLabel);
			// vgroup.addComponent(scripterLabel);
			vgroup.addComponent(notifyBookLabel);
			if (!channels.fics) {
				vgroup.addComponent(topGamesFlipLabel);

			}

			vgroup.addComponent(seeksLabel);
			if (!channels.fics) {
				vgroup.addComponent(activitesLabel);
				vgroup.addComponent(pure1);
				vgroup.addComponent(pure3);
				vgroup.addComponent(pure5);
				vgroup.addComponent(pure15);
				vgroup.addComponent(pure25);
				vgroup.addComponent(pure960);
			} else {
				vgroup.addComponent(ficsActivites);
				vgroup.addComponent(rematchLabel);
			}

			layout.setVerticalGroup(vgroup);

		}
	}

	JLabel toggleEngineLabel;
	JLabel topGamesFlipLabel;
	boolean toggleEngineEnabled = true;
	JLabel notifyBookLabel;

	void makeToolBar() {
		toolBar = new JToolBar("Still draggable");
		sharedVariables.mybuttons = new JButton[10];
		// toolBar.setLayout(new BorderLayout());
		JButton pure1 = new JButton(" 1-min ");
		JButton pure3 = new JButton(" 3-min ");
		JButton pure5 = new JButton(" 5-min ");
		JButton pure15 = new JButton(" 15-min ");
		JButton pure25 = new JButton(" 25-min ");
		JButton pure960 = new JButton(" Chess960 ");
		toggleEngineLabel = new JLabel(" Toggle Engine ");
		JLabel seeksLabel = new JLabel();
		JLabel activitesLabel = new JLabel();
		JLabel userbuttonLabel = new JLabel();
		JLabel scripterLabel = new JLabel();
		topGamesFlipLabel = new JLabel();
		notifyBookLabel = new JLabel();
		for (int a = 0; a < 10; a++) {
			sharedVariables.mybuttons[a] = new JButton("" + a);
			setButtonTitle(a);
			sharedVariables.mybuttons[a].setFont(sharedVariables.myFont);
			final int con = a;

			sharedVariables.mybuttons[a].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					toolbarCommands commander = new toolbarCommands(myboards);
					commander.dispatchCommand(con, 0, false, sharedVariables, queue);
				}
			});

		}

		toolBarPanelClass toolBarPanel = new toolBarPanelClass(toggleEngineLabel, pure1, pure3, pure5, pure15, pure25,
				pure960, seeksLabel, activitesLabel, userbuttonLabel, scripterLabel, topGamesFlipLabel, notifyBookLabel,
				toolBar);

		/*
		 * pure1.setIcon(sharedVariables.pure1); pure3.setIcon(sharedVariables.pure3);
		 * pure5.setIcon(sharedVariables.pure5); pure15.setIcon(sharedVariables.pure15);
		 * pure45.setIcon(sharedVariables.pure45);
		 * pure960.setIcon(sharedVariables.pure960);
		 */
		pure1.setBackground(new Color(255, 255, 255));
		pure3.setBackground(new Color(255, 255, 255));
		pure5.setBackground(new Color(255, 255, 255));
		pure15.setBackground(new Color(255, 255, 255));
		pure25.setBackground(new Color(255, 255, 255));
		pure960.setBackground(new Color(255, 255, 255));
		seeksLabel.setIcon(sharedVariables.seekIcon);
		// seeksLabel.setHorizontalAlignment( SwingConstants.CENTER );
		seeksLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					openSeekGraph();
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		// activitesLabel.setIcon(sharedVariables.activitiesIcon);
		activitesLabel.setText("   Activities   ");
		if (channels.fics) {
			activitesLabel.setText("   Seek   ");
		}
		// activitesLabel.setHorizontalAlignment( SwingConstants.CENTER );
		activitesLabel.setOpaque(true);
		activitesLabel.setBackground(new Color(245, 245, 250));
		activitesLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					if (channels.fics) {
						openSeekAGame();
					} else {
						openActivities();
					}

				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});
		toggleEngineLabel.setOpaque(true);
		toggleEngineLabel.setBackground(new Color(245, 245, 250));
		toggleEngineLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!toggleEngineEnabled) {
					return;
				}
				toggleEngineEnabled = false;
				setToggleEngineToBeEnabled();
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					if (!sharedVariables.engineOn) {
						restartEngine();
					} else {
						stopTheEngine();
					}
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});
		userbuttonLabel.setText("   Set User Buttons   ");
		// userbuttonLabel.setHorizontalAlignment( SwingConstants.CENTER );
		userbuttonLabel.setOpaque(true);
		userbuttonLabel.setBackground(new Color(245, 245, 250));
		userbuttonLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					launchUserButtonDialog();
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		scripterLabel.setText("   Run a Script   ");
		// scripterLabel.setHorizontalAlignment( SwingConstants.CENTER );
		scripterLabel.setOpaque(true);
		scripterLabel.setBackground(new Color(245, 245, 250));
		scripterLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					launchScripterDialog();
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		topGamesFlipLabel.setText("   Top Games   ");
		topGamesFlipLabel.setOpaque(true);
		topGamesFlipLabel.setBackground(new Color(245, 245, 250));
		topGamesFlipLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					if (topGamesFlipLabel.getText().toLowerCase().contains("top games")) {
						launchTopGames();
					} else {
						runFlipCommand();
					}
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		notifyBookLabel.setText("   Notify   ");
		notifyBookLabel.setOpaque(true);
		notifyBookLabel.setBackground(new Color(245, 245, 250));
		notifyBookLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					if (notifyBookLabel.getText().toLowerCase().contains("notify")) {
						launchNotifyWindow();
					} else {
						openBook();
					}

				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		pure1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					myoutput data = new myoutput();
					data.data = "`c0`" + "1-Minute\n";
					data.consoleNumber = 0;
					queue.add(data);
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		pure3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					myoutput data = new myoutput();
					data.data = "`c0`" + "3-Minute\n";
					data.consoleNumber = 0;
					queue.add(data);
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		pure5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					myoutput data = new myoutput();
					data.data = "`c0`" + "5-Minute\n";
					data.consoleNumber = 0;
					queue.add(data);
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		pure15.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					myoutput data = new myoutput();
					data.data = "`c0`" + "15-Minute\n";
					data.consoleNumber = 0;
					queue.add(data);
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		pure25.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					myoutput data = new myoutput();
					data.data = "`c0`" + "25\n";
					data.consoleNumber = 0;
					queue.add(data);
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		pure960.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3/* || e.getClickCount() == 2 */)
					;
				else {
					myoutput data = new myoutput();
					data.data = "`c0`" + "960\n";
					data.consoleNumber = 0;
					queue.add(data);
				} // end else
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}
		});

		toolBar.add(toolBarPanel);

	}

	void setToggleEngineToBeEnabled() {
		Timer timer = new Timer();
		timer.schedule(new ToggleEngineTimer(), 500);
	}

	public class ToggleEngineTimer extends TimerTask {
		@Override
		public void run() {
			toggleEngineEnabled = true;
		}
	}

	void sendToEngine(String output) {
		byte[] b2 = new byte[2500];
		try {
			for (int a = 0; a < output.length(); a++)
				b2[a] = (byte) output.charAt(a);

			// sharedVariables.engineQueue.add(output);

			// Thread.sleep(1000);
			// for(int a=0; a<10; a++)
			// ;
			sharedVariables.engineOut.write(b2, 0, output.length());
			sharedVariables.engineOut.flush();
		} catch (Exception e) {
		}
	}

	private static final String CHARSET = "UTF-8";

	void repaintTabs() {

		for (int i = 0; i < sharedVariables.openConsoleCount; i++) {
			if (consoleSubframes[i] != null) {
				for (int a = 0; a < sharedVariables.maxConsoleTabs; a++) {
					try {
						if (a == sharedVariables.looking[consoleSubframes[i].consoleNumber])
							// tab = console number
							consoleSubframes[i].channelTabs[a].setBackground(sharedVariables.tabImOnBackground);
						else
							consoleSubframes[i].channelTabs[a].setBackground(sharedVariables.tabBackground);

						consoleSubframes[i].channelTabs[a].setFont(sharedVariables.myTabFont);
						consoleSubframes[i].channelTabs[a].repaint();
					} catch (Exception e) {
					}
				}
			}
		}

		// now game boards
		for (int i = 0; i < sharedVariables.maxGameTabs; i++) {
			if (myboards[i] != null) {
				myboards[i].myconsolepanel.repaint();
			}
		}
	}

	void setInputFont() {
		try {
			for (int i = 0; i < sharedVariables.openConsoleCount; i++) {
				if (consoleSubframes[i] != null) {
					consoleSubframes[i].overall.Input.setFont(sharedVariables.inputFont);
				}
			}

			// now game boards
			for (int i = 0; i < sharedVariables.maxGameTabs; i++) {
				if (myboards[i] != null) {
					myboards[i].myconsolepanel.Input.setFont(sharedVariables.inputFont);
				}
			}

		} catch (Exception badfont) {
		}
	}

	void loadSoundsStandAlone() {
		// load sounds
		try {
			URL songPath;

			if (sharedVariables.operatingSystem.equals("unix")) {
				songPath = this.getClass().getResource("whistle.au"); // Geturl of sound
				sharedVariables.songs[0] = songPath;
				songPath = this.getClass().getResource("move-icc.au"); // Geturl of sound
				sharedVariables.songs[1] = songPath;
				songPath = this.getClass().getResource("capture-icc.au"); // Geturl of sound
				sharedVariables.songs[2] = songPath;
				songPath = this.getClass().getResource("ding.au"); // Geturl of sound
				sharedVariables.songs[4] = songPath;
			} else { // these below 4 were waves but should have converted to au now
				songPath = this.getClass().getResource("tell.au"); // Geturl of sound
				sharedVariables.songs[0] = songPath;
				songPath = this.getClass().getResource("click18a.au"); // Geturl of sound
				sharedVariables.songs[1] = songPath;
				songPath = this.getClass().getResource("click10b.au"); // Geturl of sound
				sharedVariables.songs[2] = songPath;
				songPath = this.getClass().getResource("beeppure.au"); // Geturl of sound
				sharedVariables.songs[4] = songPath;
			}

			songPath = this.getClass().getResource("serv1a.au"); // Geturl of sound was wav
			sharedVariables.songs[3] = songPath;
			songPath = this.getClass().getResource("fitebell.au"); // Geturl of sound
			sharedVariables.songs[5] = songPath;
			songPath = this.getClass().getResource("buzzer.au"); // Geturl of sound was wav
			sharedVariables.songs[6] = songPath;
			songPath = this.getClass().getResource("fitbell.au"); // Geturl of sound was wav
			sharedVariables.songs[7] = songPath;
			songPath = this.getClass().getResource("alert.au"); // Geturl of sound was wav
			sharedVariables.songs[8] = songPath;
			songPath = this.getClass().getResource("draw.au"); // Geturl of sound was wav
			sharedVariables.songs[9] = songPath;
			songPath = this.getClass().getResource("AnyConv.com__MOVE2.au"); // Geturl of sound was wav
			sharedVariables.songs[10] = songPath;
			songPath = this.getClass().getResource("AnyConv.com__CAPTURE2.au"); // Geturl of sound was wav
			sharedVariables.songs[11] = songPath;

			songPath = this.getClass().getResource("BEEP_FM.au"); // Geturl of sound was wav
			sharedVariables.poweroutSounds[0] = songPath;
			songPath = this.getClass().getResource("BEEPPURE.au"); // Geturl of sound was wav
			sharedVariables.poweroutSounds[1] = songPath;
			songPath = this.getClass().getResource("BEEPSPAC.au"); // Geturl of sound was wav
			sharedVariables.poweroutSounds[2] = songPath;

			// song1 = new Sound("DING.WAV");
			// song2 = new Sound("BEEPPURE.wav");
			// song1 = new Sound("BEEP_FM.wav");
			// song3 = new Sound("BEEPSPAC.wav");

		} catch (Exception e) {
		}
	}

	void loadGraphicsStandAlone() {
		try {
			URL myiconurl = this.getClass().getResource("images/game.gif");
			sharedVariables.gameIcon = new ImageIcon(myiconurl, "Game");
			myiconurl = this.getClass().getResource("images/oval1.png");
			sharedVariables.pure1 = new ImageIcon(myiconurl, "1");
			myiconurl = this.getClass().getResource("images/oval3.png");
			sharedVariables.pure3 = new ImageIcon(myiconurl, "3");
			myiconurl = this.getClass().getResource("images/oval5.png");
			sharedVariables.pure5 = new ImageIcon(myiconurl, "5");
			myiconurl = this.getClass().getResource("images/oval15.png");
			sharedVariables.pure15 = new ImageIcon(myiconurl, "15");
			myiconurl = this.getClass().getResource("images/oval45.png");
			sharedVariables.pure45 = new ImageIcon(myiconurl, "45");
			myiconurl = this.getClass().getResource("images/oval960.png");
			sharedVariables.pure960 = new ImageIcon(myiconurl, "960");
			myiconurl = this.getClass().getResource("images/seekIcon.png");
			sharedVariables.seekIcon = new ImageIcon(myiconurl, "seekIcon");

			myiconurl = this.getClass().getResource("images/activitiesIcon.png");
			sharedVariables.activitiesIcon = new ImageIcon(myiconurl, "activitiesIcon");

			myiconurl = this.getClass().getResource("images/observing.gif");
			sharedVariables.observeIcon = new ImageIcon(myiconurl, "observing");

			myiconurl = this.getClass().getResource("images/playing.gif");
			sharedVariables.playingIcon = new ImageIcon(myiconurl, "Playing");

			myiconurl = this.getClass().getResource("images/examining.gif");
			sharedVariables.examiningIcon = new ImageIcon(myiconurl, "Examining");

			myiconurl = this.getClass().getResource("images/sposition.gif");
			sharedVariables.sposIcon = new ImageIcon(myiconurl, "Sposition");

			myiconurl = this.getClass().getResource("images/was.gif");
			sharedVariables.wasIcon = new ImageIcon(myiconurl, "was");
		} catch (Exception dd) {
			logger.error("exception encountered: ", dd);
		}

		URL myurl = this.getClass().getResource("images/x.gif");
		graphics.xpiece = Toolkit.getDefaultToolkit().getImage(myurl);

		for (int a = 1; a < graphics.maxBoards; a++) {
			if (a != 6) {
				myurl = this.getClass().getResource(graphics.boardPaths[a] + "/light.gif");
				graphics.boards[a][0] = Toolkit.getDefaultToolkit().getImage(myurl);
				myurl = this.getClass().getResource(graphics.boardPaths[a] + "/dark.gif");
				graphics.boards[a][1] = Toolkit.getDefaultToolkit().getImage(myurl);
			} else {
				myurl = this.getClass().getResource(graphics.boardPaths[a] + "/light.png");
				graphics.boards[a][0] = Toolkit.getDefaultToolkit().getImage(myurl);
				myurl = this.getClass().getResource(graphics.boardPaths[a] + "/dark.png");
				graphics.boards[a][1] = Toolkit.getDefaultToolkit().getImage(myurl);
			}
		}
		try {
			sharedVariables.chessfont1 = Font
					.createFont(Font.TRUETYPE_FONT,
							this.getClass().getResource("chessalpha2/ChessAlpha2.ttf").openStream())
					.deriveFont(Font.PLAIN, 14);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
					this.getClass().getResource("chessalpha2/ChessAlpha2.ttf").openStream()));
		} catch (Exception fonte) {
			System.out.println("font load exception");
		}
		for (int a = 0; a < graphics.maxPieces; a++) {
			String ext = "gif";
			ext = graphics.pieceExt[a];
			if (ext.equals("mix"))// was rand
				break; // allways last. not real pieces , randomly generated
			String resizableNumber = "64";
			if (a < 4) {
				resizableNumber = "76";
			}
			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/wp." + ext);
			graphics.pieces[a][0] = Toolkit.getDefaultToolkit().getImage(myurl);
			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/wn." + ext);
			graphics.pieces[a][1] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/wb." + ext);
			graphics.pieces[a][2] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/wr." + ext);
			graphics.pieces[a][3] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/wq." + ext);
			graphics.pieces[a][4] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/wk." + ext);
			graphics.pieces[a][5] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/bp." + ext);
			graphics.pieces[a][6] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/bn." + ext);
			graphics.pieces[a][7] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/bb." + ext);
			graphics.pieces[a][8] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/br." + ext);
			graphics.pieces[a][9] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/bq." + ext);
			graphics.pieces[a][10] = Toolkit.getDefaultToolkit().getImage(myurl);

			myurl = this.getClass().getResource(graphics.piecePaths[a] + "/" + resizableNumber + "/bk." + ext);
			graphics.pieces[a][11] = Toolkit.getDefaultToolkit().getImage(myurl);

			// now load multi pieces
			if (!graphics.resizable[a]) {
				for (int aa = 0; aa < graphics.numberPiecePaths[a]; aa++) {
					try {
						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/wp." + ext);
						graphics.multiPieces[a][aa][0] = Toolkit.getDefaultToolkit().getImage(myurl);
						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/wn." + ext);
						graphics.multiPieces[a][aa][1] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/wb." + ext);
						graphics.multiPieces[a][aa][2] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/wr." + ext);
						graphics.multiPieces[a][aa][3] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/wq." + ext);
						graphics.multiPieces[a][aa][4] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/wk." + ext);
						graphics.multiPieces[a][aa][5] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/bp." + ext);
						graphics.multiPieces[a][aa][6] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/bn." + ext);
						graphics.multiPieces[a][aa][7] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/bb." + ext);
						graphics.multiPieces[a][aa][8] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/br." + ext);
						graphics.multiPieces[a][aa][9] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/bq." + ext);
						graphics.multiPieces[a][aa][10] = Toolkit.getDefaultToolkit().getImage(myurl);

						myurl = this.getClass().getResource(
								graphics.piecePaths[a] + "/" + graphics.multiPiecePaths[a][aa] + "/bk." + ext);
						graphics.multiPieces[a][aa][11] = Toolkit.getDefaultToolkit().getImage(myurl);
					} // end try
					catch (Exception ee) {
						// System.out.println("error my url is " + myurl + " a is " + a + " and aa is "
						// + aa );

					} // end catch

				}

			} // end multi piece if
		} // end for
			// make monge mix pieces

		int fantasy = 0;
		int spatial = 0;

		for (int e = 0; e < graphics.maxPieces; e++) {
			if (graphics.piecePaths[e].equals("fantasy"))
				fantasy = e;
			if (graphics.piecePaths[e].equals("spatial"))
				spatial = e;
		}
		for (int aa = 0; aa < graphics.numberPiecePaths[graphics.maxPieces - 2]; aa++) {
			graphics.multiPieces[graphics.maxPieces - 2][aa][0] = graphics.multiPieces[fantasy][aa][0];
			graphics.multiPieces[graphics.maxPieces - 2][aa][1] = graphics.multiPieces[spatial][aa][1];
			graphics.multiPieces[graphics.maxPieces - 2][aa][2] = graphics.multiPieces[fantasy][aa][2];
			graphics.multiPieces[graphics.maxPieces - 2][aa][3] = graphics.multiPieces[fantasy][aa][3];
			graphics.multiPieces[graphics.maxPieces - 2][aa][4] = graphics.multiPieces[spatial][aa][4];
			graphics.multiPieces[graphics.maxPieces - 2][aa][5] = graphics.multiPieces[spatial][aa][5];
			graphics.multiPieces[graphics.maxPieces - 2][aa][6] = graphics.multiPieces[fantasy][aa][6];
			graphics.multiPieces[graphics.maxPieces - 2][aa][7] = graphics.multiPieces[spatial][aa][7];
			graphics.multiPieces[graphics.maxPieces - 2][aa][8] = graphics.multiPieces[fantasy][aa][8];
			graphics.multiPieces[graphics.maxPieces - 2][aa][9] = graphics.multiPieces[fantasy][aa][9];
			graphics.multiPieces[graphics.maxPieces - 2][aa][10] = graphics.multiPieces[spatial][aa][10];
			graphics.multiPieces[graphics.maxPieces - 2][aa][11] = graphics.multiPieces[spatial][aa][11];
		}

		myurl = this.getClass().getResource("join_event.gif");
		sharedVariables.eventsImages.add(Toolkit.getDefaultToolkit().getImage(myurl));
		myurl = this.getClass().getResource("watch_event.gif");
		sharedVariables.eventsImages.add(Toolkit.getDefaultToolkit().getImage(myurl));
		myurl = this.getClass().getResource("info_event.gif");
		sharedVariables.eventsImages.add(Toolkit.getDefaultToolkit().getImage(myurl));

		boolean flagger = true;
		int index11 = -1;
		int index22 = -1;
		int place = 0;
		while (flagger) {
			index11 = sharedVariables.countryNames.indexOf(";", index11 + 1);
			index22 = sharedVariables.countryNames.indexOf(";", index11 + 1);
			if (index11 > -1 && index22 > -1 && index22 > index11) {
				String lookup = sharedVariables.countryNames.substring(index11 + 1, index22);
				sharedVariables.flagImageNames.add(lookup);
				lookup = "flags-small/" + lookup + ".png";

				myurl = this.getClass().getResource(lookup);
				sharedVariables.flagImages.add(Toolkit.getDefaultToolkit().getImage(myurl));
			} else
				break;

			index11 = index22;
		}
	}// end method

	/*
	 * void loadGraphicsApplet() { for (int a=1; a<graphics.maxBoards; a++) { if (a
	 * != 6) { graphics.boards[a][0]=getImage(getDocumentBase(),
	 * graphics.boardPaths[a] + "/light.gif");
	 * graphics.boards[a][1]=getImage(getDocumentBase(), graphics.boardPaths[a] +
	 * "/dark.gif"); } else { graphics.boards[a][0]=getImage(getDocumentBase(),
	 * graphics.boardPaths[a] + "/light.png");
	 * graphics.boards[a][1]=getImage(getDocumentBase(), graphics.boardPaths[a] +
	 * "/dark.png"); } } for (int a=0; a<graphics.maxPieces; a++) {
	 * graphics.pieces[a][0] = getImage(getDocumentBase(), graphics.piecePaths[a] +
	 * "/64/wp.gif"); graphics.pieces[a][1] = getImage(getDocumentBase(),
	 * graphics.piecePaths[a] + "/64/wn.gif"); graphics.pieces[a][2] =
	 * getImage(getDocumentBase(), graphics.piecePaths[a] + "/64/wb.gif");
	 * graphics.pieces[a][3] = getImage(getDocumentBase(), graphics.piecePaths[a] +
	 * "/64/wr.gif"); graphics.pieces[a][4] = getImage(getDocumentBase(),
	 * graphics.piecePaths[a] + "/64/wq.gif"); graphics.pieces[a][5] =
	 * getImage(getDocumentBase(), graphics.piecePaths[a] + "/64/wk.gif");
	 * graphics.pieces[a][6] = getImage(getDocumentBase(), graphics.piecePaths[a] +
	 * "/64/bp.gif"); graphics.pieces[a][7] = getImage(getDocumentBase(),
	 * graphics.piecePaths[a] + "/64/bn.gif"); graphics.pieces[a][8] =
	 * getImage(getDocumentBase(), graphics.piecePaths[a] + "/64/bb.gif");
	 * graphics.pieces[a][9] = getImage(getDocumentBase(), graphics.piecePaths[a] +
	 * "/64/br.gif"); graphics.pieces[a][10] = getImage(getDocumentBase(),
	 * graphics.piecePaths[a] + "/64/bq.gif"); graphics.pieces[a][11] =
	 * getImage(getDocumentBase(), graphics.piecePaths[a] + "/64/bk.gif"); } }//end
	 * method
	 * 
	 * void loadSoundsApplet() {// load sounds try { URL songPath = new
	 * URL(getCodeBase(), "tell.wav"); // Geturl of sound
	 * sharedVariables.songs[0]=songPath; songPath = new URL(getCodeBase(),
	 * "click18a.wav"); // Geturl of sound sharedVariables.songs[1]=songPath;
	 * songPath = new URL(getCodeBase(), "click10b.wav"); // Geturl of sound
	 * sharedVariables.songs[2]=songPath; songPath = new URL(getCodeBase(),
	 * "serv1a.wav"); // Geturl of sound sharedVariables.songs[3]=songPath;
	 * 
	 * } catch (Exception e) {} }//end method
	 */

	public void saveSettings() {

	}
	// not really used. i am saving to a file on a hard drive now. this
	// was an attempt to write to the web but any settings are deleted
	// if they clear temp internet files

	public void getSettings() {

	}

	void repaintboards() {
		try {
			for (int coo = 0; coo < sharedVariables.openBoardCount; coo++) {
				if (myboards[coo] != null && myboards[coo].isVisible()) {
					myboards[coo].mypanel.repaint(0, 0, 5000, 5000);
				}
			}
			if (myboards[0] != null && myboards[0].isVisible())
				myboards[0].repaint();

		} catch (Exception dui) {
		}
	}// end method repaint boards
} // end multi frame class
