package PaooGame.Audio;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

// clasa pt sunetul jocului
public class SoundClipTest extends JFrame {

    // Constructor
    public SoundClipTest() {

        try {
            // Open an audio input stream.
            Connection connection = null; //folosim baza de date pt a selecta numele fisierului din care vom incarca audio-ul
            Statement stmt = null;
            String fileMusic = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:game.db");
                connection.setAutoCommit(false);
                stmt =  connection.createStatement();
                ResultSet rs = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM GAME;");
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String file = rs.getString("Path");
                    if (id == 1)
                        fileMusic = file;
                    URL url = this.getClass().getClassLoader().getResource(fileMusic);
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    // Get a sound clip resource.
                    Clip clip = AudioSystem.getClip();
                    // Open audio clip and load samples from the audio input stream.
                    clip.open(audioIn);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                connection.close();

            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

