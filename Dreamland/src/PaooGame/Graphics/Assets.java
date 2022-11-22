package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage pinkSoil1;
    public static BufferedImage pinkSoil2;
    public static BufferedImage whiteSoil;
    public static BufferedImage lollipop;
    public static BufferedImage gift;
    public static BufferedImage candy;
    public static BufferedImage toothbrush;
    public static BufferedImage chtree;
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage heroDown;
    public static BufferedImage heroUp;
    public static BufferedImage hud;
    public static BufferedImage menuPNG;
    public static BufferedImage santa;



    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/game_sprite.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        pinkSoil1=sheet.crop(0,0);
        pinkSoil2 = sheet.crop(1,0);
        whiteSoil = sheet. crop(2, 0);
        lollipop = sheet.crop(0,1);
        gift = sheet.crop(1,1);
        candy = sheet.crop(2,1);
        toothbrush = sheet.crop(3,1);
        chtree = sheet.crop(0,2);
        heroLeft = sheet.crop(0, 6);
        heroRight = sheet.crop(0, 3);
        heroDown =sheet.crop(0,4);
        heroUp = sheet.crop(0,5);
        santa = sheet.crop(1,2);
        hud = ImageLoader.LoadImage("/textures/hud.png");
        menuPNG = ImageLoader.LoadImage("/textures/menuPNg.png");


    }
}
