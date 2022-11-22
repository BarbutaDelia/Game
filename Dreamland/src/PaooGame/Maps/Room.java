package PaooGame.Maps;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

public class Room {
    private RefLinks refLink;
    private int width=9;
    private int height=9;
    public int room[][];//camera sub forma de matrice

    public int RoomX = 0; // unde se situeaza camera pe axa X
    public int RoomY = 0; // unde se situeaza camera pe axa Y

    Room(){
        room = new int[width][height];
    } // constructor
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public int getRoom(int x, int y){return this.room[x][y];}
    public int getRoomX() {
        return RoomX;
    }
    public int getRoomY() {
        return RoomY;
    }

    public void Update(){ }

    //functie pt schimbarea camerei in care se situeaza eroul
    public void changeRoom(Hero hero){
        if (hero.GetX()<0) // daca coordonata pe axa X < 0, se intra in camera din stanga
        {
            this.RoomX--;
            hero.SetX(GameWindow.GetWndWidth()); // setam pozitia eroului
        }
        if(hero.GetX()>GameWindow.GetWndWidth()) {
            this.RoomX++;
            hero.SetX(0);
        }
        if(hero.GetY()<0) {
            this.RoomY--;
            hero.SetY(GameWindow.GetWndHeight()-32);
        }
        if(hero.GetY()>GameWindow.GetWndHeight()-32) {
            this.RoomY++;
            hero.SetY(0);
        }
    }
    // functie pt obtinerea tile-ului din camera
    public Tile getRoomTile(int Roomx, int Roomy, int x, int y, Room[][] camera) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.whiteSoilTile;

        }
        if ( Roomx < 0 || Roomy < 0 || Roomx >= refLink.getMap().getWidth()|| Roomy >= refLink.getMap().getHeight()) {
            System.out.println("ai iesit din labirint"+ RoomX+ RoomY);// daca am iesit din labirint
            return Tile.whiteSoilTile;
        }
        Tile t = Tile.tiles[camera[Roomx][Roomy].room[x][y]];
        if(t == null) // daca nu s-a initializat bine tile-ul
            return Tile.whiteSoilTile;

        return t;
    }










}
