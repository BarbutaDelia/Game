package PaooGame.Items;

import PaooGame.RefLinks;
import PaooGame.States.EndState;
import PaooGame.States.PlayState;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import static PaooGame.States.State.SetState;

public class Inventory {
    private RefLinks refLink;

    private ArrayList<Candy> candyItems;//array cu bomboane

    private ArrayList<Toothbrush> toothbrushItems;//array cu periute de dinti

    private ArrayList<Gift> giftItems;//Cadoul de la sfarsitul nivelului
    private ArrayList<Santa> santa;
    public Inventory(RefLinks refLink)
    {
        this.refLink = refLink;
        candyItems = new ArrayList<Candy>();
        toothbrushItems = new ArrayList<Toothbrush>();
        giftItems = new ArrayList<Gift>();
        santa = new ArrayList<Santa>();
    }

    public void Update() {
        Iterator<Toothbrush> indexToothbrush = toothbrushItems.iterator(); //trebuie iterator pt remove for some reason nu merge cu null

        while (indexToothbrush.hasNext()) {
            collisionWithHero(refLink.getHero()); //seteaza PickedUp
            Toothbrush i = indexToothbrush.next();
            i.Update();
            if (i.isPickedUp()) {// daca e picked up da remove
                indexToothbrush.remove();
                refLink.getHero().setNrToothbrushes(refLink.getHero().getNrToothbrushes() + 1);// candies ++; -_-
                refLink.getHero().setMax(refLink.getHero().getMax() + 3);
            }
        }

        Iterator<Candy> indexCandy = candyItems.iterator();

        while (indexCandy.hasNext()) {
            collisionWithHero(refLink.getHero());
            Candy i = indexCandy.next();
            i.Update();
            if (i.isPickedUp() && refLink.getHero().getNrCandies() < refLink.getHero().getMax()) {// daca e picked up si nu am atins nr max da remove

                indexCandy.remove();
                refLink.getHero().setNrCandies(refLink.getHero().getNrCandies() + 1); // toothbrush++
            } else
                i.setPickedUp(false);

        }
        Iterator<Gift> indexGift = giftItems.iterator();

        while (indexGift.hasNext()) {
            collisionWithHero(refLink.getHero()); //seteaza PickedUp
            Gift i = indexGift.next();
            i.Update();
            if (i.isPickedUp()) {// daca e picked up da remove
                indexGift.remove();
                int l = refLink.getMap().getLevel() + 1;
                SetState(new PlayState(refLink, l));


                //System.out.println(refLink.getMap().getLevel());
            }

        }

        Iterator<Santa> indexSanta = santa.iterator();

        while (indexSanta.hasNext()) {
            collisionWithHero(refLink.getHero()); //seteaza PickedUp
            Santa i = indexSanta.next();
            i.Update();
            if (i.isPickedUp()) {// daca e picked up da remove
                int l = refLink.getMap().getLevel() + 1;

                SetState(new EndState(refLink));
                Connection connection = null; // daca adaugam o bomboana in inventar, facem un update al nr de bomboane in baza de date
                Statement stmt = null;
                try {
                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:score.db");
                    connection.setAutoCommit(false);
                    stmt = connection.createStatement();
                    ResultSet rs = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM SCORE;");
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        if (id == 1) {
                            String sql = "UPDATE SCORE set Nr = " + refLink.getHero().getNrCandies() + ";";
                            //System.out.println(refLink.getHero().getNrCandies());
                            stmt.executeUpdate(sql);
                            connection.commit();
                        }


                        //System.out.println(refLink.getMap().getLevel());
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        }
    }

    public void collisionWithHero(Hero h) //functia care detecteaza coliziunea cu eroul si seteaza atributul PickedUp
    {
        Rectangle r = new Rectangle((int)(h.GetX() + h.bounds.x),(int)(h.GetY() + h.bounds.y), h.bounds.width, h.bounds.height); // dreptunghi care iti da coliziunea eroului

        for( int i = 0; i < candyItems.size(); i++)
                if (r.intersects(candyItems.get(i).normalBounds)) // daca dreptunghiul de coliziune a eroului se intersecteaza cu bomboana, o ridicam
                    candyItems.get(i).setPickedUp(true);
        for( int i = 0; i < toothbrushItems.size(); i++)
            if (r.intersects(toothbrushItems.get(i).normalBounds)) // daca dreptunghiul de coliziune a eroului se intersecteaza cu periuta, o ridicam
                toothbrushItems.get(i).setPickedUp(true);
        for( int i = 0; i < giftItems.size(); i++)
            if (r.intersects(giftItems.get(i).normalBounds)) // daca dreptunghiul de coliziune a eroului intersecteaza cadoul, il ridicam
                giftItems.get(i).setPickedUp(true);
        for( int i = 0; i < santa.size(); i++)
            if (r.intersects(santa.get(i).normalBounds)) // daca dreptunghiul de coliziune a eroului intersecteaza cadoul, il ridicam
                santa.get(i).setPickedUp(true);
    }


    public void Draw(Graphics g){//randam bomboanele & periutele
        for (Candy item : candyItems) {
            item.Draw(g);
        }
        for (Toothbrush item : toothbrushItems) {
            item.Draw(g);
        }
        for (Gift item : giftItems) {
            item.Draw(g);
        }
        for(Santa s: santa){
            s.Draw(g);
        }
    }
    public void add(Candy i){
        candyItems.add(i);
    } //adaugam o bomboana in array
    public void add(Toothbrush i){
        toothbrushItems.add(i);
    }//adaugam o periuta in array
    public void add(Gift i){
        giftItems.add(i);
    }//adaugam un cadou in array
    public void add(Santa i){
        santa.add(i);
    }


}
