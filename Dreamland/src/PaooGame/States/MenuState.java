package PaooGame.States;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.candy;
import static PaooGame.Graphics.Assets.menuPNG;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
    private BufferedImage menuImage;
    private BufferedImage candyImage;

    private int currentOption = 0;
    private String[] options = {
            "START",
            "QUIT"
    };

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
        menuImage = menuPNG;
        candyImage = candy;

    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        handleInput(refLink);
    }
    public void handleInput(RefLinks refLink) {
        if( refLink.GetKeyManager().down && currentOption < options.length - 1)
            currentOption++;

        if(refLink.GetKeyManager().up && currentOption > 0)
            currentOption--;

        if(refLink.GetKeyManager().enter) {
            selectOption();
        }
    }
    private void selectOption() {
        if(currentOption == 0) {
            super.SetState(new PlayState(refLink,1));
        }
        if(currentOption == 1) {
            System.exit(0);
        }
    }


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(menuImage, 0, 0, null);
        g.setFont(new Font("Times New Roman",Font.PLAIN,22));
        g.setColor(new Color(226, 112, 146));
        g.drawString( options[0], 130, 210);
        g.drawString( options[1], 130, 245);

        if(currentOption == 0) g.drawImage(candyImage, 95, 187, null);
        else if(currentOption == 1) g.drawImage(candyImage, 95, 220, null);


    }

}
