import javax.swing.*;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import java.awt.*;
import java.io.*;

public class testPlayer1 {

	public static JFileChooser fileDialog = new JFileChooser();

	public static void main(String[] args) {

		String path = "";
		String mPath = "";

		File file;
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				"/Applications/VLC.app/Contents/MacOS/lib");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		/**
		 * Region 1
		 **/
		fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileDialog.showSaveDialog(null);
		fileDialog.setName("Neptune View Media Player Dialog");
		file = fileDialog.getSelectedFile();
		path = file.getAbsolutePath();
		/**
		 * Region 2
		 */
		fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileDialog.showSaveDialog(null);
		file = fileDialog.getSelectedFile();
		mPath = file.getAbsolutePath();

		new Player(path, mPath).run();
	}
}
