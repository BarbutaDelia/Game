package PaooGame.States;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.menuPNG;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class LostState extends State
{
    private BufferedImage menuImage;
    private String lost = "YOU'VE LOST! :(";


    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public LostState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        menuImage = menuPNG;

    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {

    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(menuImage, 0, 0, null);
        g.setFont(new Font("Times New Roman",Font.PLAIN,30));
        g.setColor(new Color(226, 112, 146));
        g.drawString( lost, 30, 240);



    }

}
