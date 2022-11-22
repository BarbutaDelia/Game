package PaooGame.Maps;

import PaooGame.Exception.UnknownTileException;
import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map extends Room {
    private Inventory[][] inventory; // matrice de inventories
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private Room[][] background;//matrice de rooms pt background
    private Room[][] foreground;//matrice de rooms pt foreground
    private Room[][] background2;//matrice de rooms pt background lvl 2
    private Room[][] foreground2;//matrice de rooms pt foreground lvl 2
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink, int level) {
        /// Retine referinta "shortcut"
        this.refLink = refLink;
        this.level = level;
        System.out.println(level);
        if (level == 1) {
            inventory = new Inventory[3][2];
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 2; j++)
                    inventory[i][j] = new Inventory(refLink);
        } else {
            RoomX = 0;
            RoomY = 0;
            inventory = new Inventory[4][4];
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    inventory[i][j] = new Inventory(refLink);

        }
        //inventory ul e pe camere


        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld();

    }

    // functie pentru a obtine tile- ul curent pe care se afla eroul
    public Tile getTile(int x, int y) {
        if (level == 1) {
            Tile t = null;
            if (x == 9) //cazurile in care eroul incearca sa iasa din camera
                t = Tile.tiles[foreground[RoomX + 1][RoomY].room[x - 9][y]];
            else if (x == 10)
                t = Tile.tiles[foreground[RoomX + 1][RoomY].room[x - 10][y]];
            else if (y == 9)
                t = Tile.tiles[foreground[RoomX][RoomY + 1].room[x][y - 9]];
            else if (y == 10)
                t = Tile.tiles[foreground[RoomX][RoomY + 1].room[x][y - 10]];
            else
                t = Tile.tiles[foreground[RoomX][RoomY].room[x][y]];
            return t;
        } else {
            Tile t = Tile.whiteSoilTile;
            if (x == 9) //cazurile in care eroul incearca sa iasa din camera
                t = Tile.tiles[foreground2[RoomX + 1][RoomY].room[x - 9][y]];
            else if (x == 10)
                t = Tile.tiles[foreground2[RoomX + 1][RoomY].room[x - 10][y]];
            else if (y == 9)
                t = Tile.tiles[foreground2[RoomX][RoomY + 1].room[x][y - 9]];
            else if (y == 10)
                t = Tile.tiles[foreground2[RoomX][RoomY + 1].room[x][y - 10]];
            else
                t = Tile.tiles[foreground2[RoomX][RoomY].room[x][y]];
            return t;

        }


    }


    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update() {
        //if(level == 1)
        inventory[RoomX][RoomY].Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g, Hero hero) {
        changeRoom(hero);
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        if (level == 1) {
            for (int y = 0; y < refLink.GetGame().GetHeight() / Tile.TILE_HEIGHT; y++) {
                for (int x = 0; x < refLink.GetGame().GetWidth() / Tile.TILE_WIDTH; x++) {
                    getRoomTile(RoomX, RoomY, x, y, background).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
                    getRoomTile(RoomX, RoomY, x, y, foreground).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);

                    ;// randam ambele layere ale hartii
                }
            }
            inventory[RoomX][RoomY].Draw(g);
        } else {
            //RoomX = 0;
            //RoomY = 0;
            for (int y = 0; y < refLink.GetGame().GetHeight() / Tile.TILE_HEIGHT; y++) {
                for (int x = 0; x < refLink.GetGame().GetWidth() / Tile.TILE_WIDTH; x++) {
                    getRoomTile(RoomX, RoomY, x, y, background2).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
                    getRoomTile(RoomX, RoomY, x, y, foreground2).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);

                    ;// randam ambele layere ale hartii
                }
            }
            inventory[RoomX][RoomY].Draw(g);
        }
        //g.fillRect((int)(hero.GetX() + hero.getBounds().x),(int)(hero.GetY() + hero.getBounds().x),hero.getBounds().width, hero.getBounds().height);
        //g.fillRect(inventory[0][0].getNormalBounds().x, inventory[0][0].getNormalBounds().y, inventory[0][0].getNormalBounds().width,inventory[0][0].getNormalBounds().height);
    }

    private void populateItems() {
        Candy c;
        c = new Candy(refLink, 7, 3);
        inventory[0][0].add(c);
        c = new Candy(refLink, 5, 6);
        inventory[1][0].add(c);
        c = new Candy(refLink, 3, 6);
        inventory[2][0].add(c);

        c = new Candy(refLink, 5, 2);
        inventory[0][1].add(c);
        c = new Candy(refLink, 5, 7);
        inventory[1][1].add(c);
        c = new Candy(refLink, 6, 5);
        inventory[2][1].add(c);

        Toothbrush t;
        t = new Toothbrush(refLink, 2, 4);
        inventory[2][0].add(t);

        Gift g;
        g = new Gift(refLink, 5, 4);
        inventory[2][0].add(g);


    }

    private void populateItemslvl2() {

        Candy c;
        c = new Candy(refLink, 2, 2);
        inventory[0][0].add(c);
        c = new Candy(refLink, 1, 2);
        inventory[1][0].add(c);
        c = new Candy(refLink, 1, 7);
        inventory[3][0].add(c);

        c = new Candy(refLink, 1, 5);
        inventory[0][1].add(c);
        c = new Candy(refLink, 1, 4);
        inventory[1][1].add(c);
        c = new Candy(refLink, 3, 1);
        inventory[1][1].add(c);
        c = new Candy(refLink, 1, 3);
        inventory[3][1].add(c);

        c = new Candy(refLink, 7, 6);
        inventory[0][2].add(c);
        c = new Candy(refLink, 4, 1);
        inventory[1][2].add(c);
        c = new Candy(refLink, 6, 3);
        inventory[3][2].add(c);

        c = new Candy(refLink, 7, 4);
        inventory[1][3].add(c);
        c = new Candy(refLink, 4, 7);
        inventory[2][3].add(c);

        Toothbrush t;

        t = new Toothbrush(refLink, 6, 3);
        inventory[1][1].add(t);

        t = new Toothbrush(refLink, 5, 3);
        inventory[2][2].add(t);

        t = new Toothbrush(refLink, 4, 6);
        inventory[0][3].add(t);

        t = new Toothbrush(refLink, 6, 4);
        inventory[3][3].add(t);

        Santa s;
        s = new Santa(refLink, 7, 7);
        inventory[3][3].add(s);


    }


    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld() {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de camere.
        if (level == 1) {
            width = 3;
            ///Se stabileste inaltimea hartii in numar de camere
            height = 2;
            ///Se construieste matricea de camere
            background = new Room[width][height];
            foreground = new Room[width][height];

            populateItems();
            int[][] map1Background = BackgroundLvl1();
            int[][] map1Foreground = Foreground();
            for (int x = 0; x < width; x++)
                for (int y = 0; y < height; y++) { // se construiesc camerele
                    background[x][y] = new Room();
                    foreground[x][y] = new Room();
                    for (int i = 0; i < 9; i++)
                        for (int j = 0; j < 9; j++) {
                            background[x][y].room[i][j] = map1Background[j + y * 8][i + x * 8]; // 8 pt ca am nevoie sa randez peretii in ambele camere
                            foreground[x][y].room[i][j] = map1Foreground[j + y * 8][i + x * 8];
                        }
                }
            //}
        } else {
            width = 4;
            ///Se stabileste inaltimea hartii in numar de camere
            height = 4;
            ///Se construieste matricea de camere
            background2 = new Room[width][height];
            foreground2 = new Room[width][height];


            int[][] map2Background = BackgroundLvl2();
            int[][] map2Foreground = Foregroundlvl2();

            populateItemslvl2();


            for (int x = 0; x < width; x++)
                for (int y = 0; y < height; y++) { // se construiesc camerele
                    background2[x][y] = new Room();
                    foreground2[x][y] = new Room();
                    for (int i = 0; i < 9; i++)
                        for (int j = 0; j < 9; j++) {
                            background2[x][y].room[i][j] = map2Background[j + y * 8][i + x * 8]; // 8 pt ca am nevoie sa randez peretii in ambele camere
                            foreground2[x][y].room[i][j] = map2Foreground[j + y * 8][i + x * 8];
                        }
                }

        }
    }


    /*! \fn private int MiddleEastMap(int x ,int y)
        \brief O harta incarcata static.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */
    private int[][] BackgroundLvl1() {
        ///Definire statica a matricei de coduri de dale.
        int[][] map = new int[17][25];
        try {
            Scanner sc = new Scanner(new File("res/maps/BackgroundLvl1.txt"));
            for (int i = 0; i < 17; i++)
                for (int j = 0; j < 25; j++) {
                    int currentID = sc.nextInt();
                    if (currentID < 0 || currentID > 7)
                        throw new UnknownTileException("Unknown tile id" + currentID);

                    map[i][j] = currentID;
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnknownTileException e) {
            e.printStackTrace();
        }

        return map;
    }

    private int[][] BackgroundLvl2() {
        ///Definire statica a matricei de coduri de dale.
        int[][] map = new int[33][33];
        try {
            Scanner sc = new Scanner(new File("res/maps/BackgroundLvl2.txt"));
            for (int i = 0; i < 33; i++)
                for (int j = 0; j < 33; j++) {
                    int currentID = sc.nextInt();
                    if (currentID < 0 || currentID > 7)
                        throw new UnknownTileException("Unknown tile id " + currentID);

                    map[i][j] = currentID;
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnknownTileException e) {
            e.printStackTrace();
        }

        return map;
    }


    private int[][] Foreground() {
        ///Definire statica a matricei de coduri de dale.
        int[][] map = new int[17][25];
        try {
            Scanner sc = new Scanner(new File("res/maps/ForegroundLvl1.txt"));
            for (int i = 0; i < 17; i++)
                for (int j = 0; j < 25; j++) {
                    int currentID = sc.nextInt();
                    if (currentID < 0 || currentID > 7)
                        throw new UnknownTileException("Unknown tile id " + currentID);

                    map[i][j] = currentID;
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnknownTileException e) {
            e.printStackTrace();
        }

        return map;
    }

    private int[][] Foregroundlvl2() {
        ///Definire statica a matricei de coduri de dale.
        int[][] map = new int[33][33];
        try {
            Scanner sc = new Scanner(new File("res/maps/ForegroundLvl2.txt"));
            for (int i = 0; i < 33; i++)
                for (int j = 0; j < 33; j++) {
                    int currentID = sc.nextInt();
                    if (currentID < 0 || currentID > 7)
                        throw new UnknownTileException("Unknown tile id " + currentID);

                    map[i][j] = currentID;
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnknownTileException e) {
            e.printStackTrace();
        }

        return map;
    }
}