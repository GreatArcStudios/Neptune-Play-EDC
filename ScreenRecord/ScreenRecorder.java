import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.test.VlcjTest;

public class ScreenRecorder {

	private static final String[] OPTIONS = { "--quiet", "--quiet-synchro",
			"--intf", "dummy" };

	private static final String MRL = "screen://";
	private static final String SOUT = ":sout=#transcode{vcodec=FLV1,vb=%d,scale=%f}:duplicate{dst=file{dst=%s}}";
	private static final String FPS = ":screen-fps=%d";
	private static final String CACHING = ":screen-caching=%d";

	private static final int fps = 20;
	private static final int caching = 500;
	private static final int bits = 4096;
	private static final float scale = 0.5f;

	private final MediaPlayerFactory mediaPlayerFactory;
	private final MediaPlayer mediaPlayer;

	private final JFrame frame;

	public static void main(String[] args) {
		NativeLibrary.addSearchPath("vlc",
				"/Applications/VLC.app/Contents/MacOS/lib");
		System.setProperty("jna.library.path",
				"/Applications/VLC.app/Contents/MacOS/lib");

		final String destination = "record.flv";

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ScreenRecorder(destination);
			}
		});
	}

	public ScreenRecorder(final String destination) {
		mediaPlayerFactory = new MediaPlayerFactory(OPTIONS);
		mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
		JPanel cp = new JPanel();
		JButton recordButton = new JButton("Record");
		cp.add(recordButton);
		frame = new JFrame("vlcj");
		frame.setContentPane(cp);
		frame.setLocation(10, 10);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stop();
			}
		});
		recordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				go(destination);
			}
		});
		frame.setVisible(true);
	}

	private void go(String destination) {
		frame.setState(JFrame.ICONIFIED);
		mediaPlayer.playMedia(MRL, getMediaOptions(destination));
	}

	private void stop() {
		mediaPlayer.stop();
		mediaPlayer.release();
		mediaPlayerFactory.release();
	}

	private String[] getMediaOptions(String destination) {
		return new String[] { String.format(SOUT, bits, scale, destination),
				String.format(FPS, fps), String.format(CACHING, caching) };
	}
}