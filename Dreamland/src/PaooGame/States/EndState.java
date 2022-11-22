package PaooGame.States;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.*;

import static PaooGame.Graphics.Assets.menuPNG;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class EndState extends State {
    private BufferedImage menuImage;
    private String won = "YOU'VE WON! :D";


    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public EndState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        menuImage = menuPNG;

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() {

    }


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(menuImage, 0, 0, null);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.setColor(new Color(226, 112, 146));
        g.drawString(won, 30, 240);

        String totalCandies="";
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:score.db");
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rs = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String finalScore = rs.getString("Nr");
                if (id == 1)
                    totalCandies = finalScore;
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String aux = "Your score is: ";

        g.drawString(aux, 40,280);
        g.drawString(totalCandies, 215, 280);

    }

}
