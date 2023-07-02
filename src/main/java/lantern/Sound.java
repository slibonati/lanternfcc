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

import java.applet.Applet;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JApplet;

import org.slf4j.Logger;

class Sound extends JApplet implements LineListener, Runnable {
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(Sound.class);
	
	private java.applet.AudioClip song; // Sound player
	private URL songPath; // Sound path
	String operatingSystem;
	/**
	 * The current thread playing the sound.
	 */

	private volatile Thread playerThread = null;

	/**
	 * The queue holding AudioClips for the player thread to play.
	 */

	ConcurrentLinkedQueue<URL> queue = new ConcurrentLinkedQueue<URL>();

	Sound(String filename) {
		try {
			songPath = new URL(getCodeBase(), filename); // Geturl of sound
			song = Applet.newAudioClip(songPath); // Load
			song.play();
		} catch (Exception e) {
			logger.error("exception encountered: ", e);
		} 
	}

	void runSound(URL songPath) {

	}

	Sound(URL songPath1) {

		String os = System.getProperty("os.name").toLowerCase();
		
		if (os.indexOf("win") >= 0)
			operatingSystem = "win";
		else if (os.indexOf("mac") >= 0)
			operatingSystem = "mac";
		else
			operatingSystem = "unix";

		if (operatingSystem.equals("mac") || (operatingSystem.equals("win") && Channels.firstSound == false)) {
			try {

				free.util.audio.AudioClip unixClip = new free.util.audio.AudioClip(songPath1);
				unixClip.play();
			} catch (Exception dui) {
				logger.error("exception encountered: ", dui);
			}
		} else if (operatingSystem.equals("unix")) {

			if (playerThread == null) { // Lazily start the thread.
				playerThread = new Thread(this, "SunAudioPlayer");
				playerThread.setDaemon(true);
				queue.add(songPath1);
				playerThread.start();
			} else {
				queue.add(songPath1);
			}

		} else {

			try {
				Channels.firstSound = false;
				songPath = songPath1;
				playNative player = new playNative();
				Thread t = new Thread(player);
				t.start();
				// song = Applet.newAudioClip(songPath); // Load
				// song.play();

			} catch (Exception e) {
				logger.error("exception encountered: ", e);
			} 

		} // end else
	} // end method

	boolean playCompleted;

	/**
	 * Play a given audio file.
	 * 
	 * @param audioFilePath Path of the audio file.
	 */

	public void run() {
		try {

			URL url = queue.poll();
			if (url == null) {
				try {
					Thread.sleep(50);
				} catch (Exception einterupt) {
				}
			} else {

				AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);

				// AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
				// AudioSystem.NOT_SPECIFIED,16, 2, 4, AudioSystem.NOT_SPECIFIED, true);
				// free.util.audio.AudioClip unixClip = new free.util.audio.AudioClip(url);
				// byte [] data = unixClip.getData();
				// AudioFormat format = getFormatForPlaying(data);

				AudioFormat format = audioStream.getFormat();

				DataLine.Info info = new DataLine.Info(Clip.class, format);

				// Clip audioClip = (Clip) AudioSystem.getLine(info);
				Clip audioClip = (Clip) AudioSystem.getClip(null);

				audioClip.addLineListener(this);

				audioClip.open(audioStream);

				audioClip.start();

				while (!playCompleted) {
					// wait for the playback completes
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						logger.error("exception encountered: ", ex);
					}
				}

				audioClip.close();
			} // end else
		} catch (UnsupportedAudioFileException ex) {
			logger.error("The specified audio file is not supported.", ex);
			
		} catch (LineUnavailableException ex) {
			logger.error("Audio line for playing back is unavailable.", ex);
			
		} catch (IOException ex) {
			logger.error("Error playing the audio file.",ex);
			
		}

	}

	protected static AudioFormat getFormatForPlaying(byte[] audioData)
			throws UnsupportedAudioFileException, IOException {
		AudioFormat format = AudioSystem.getAudioFileFormat(new ByteArrayInputStream(audioData)).getFormat();

		// At present, ALAW and ULAW encodings must be converted
		// to PCM_SIGNED before it can be played
		if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
			return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(),
					format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2,
					format.getFrameRate(), true);
		else
			return format;
	}

	/**
	 * Listens to the START and STOP events of the audio line.
	 */
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();

		if (type == LineEvent.Type.START) {
			System.out.println("Playback started.");

		} else if (type == LineEvent.Type.STOP) {
			playCompleted = true;
			System.out.println("Playback completed.");
		}

	}

	class playNative implements Runnable {

		public void run() {
			song = Applet.newAudioClip(songPath); // Load
			song.play();
		}
	}

}
