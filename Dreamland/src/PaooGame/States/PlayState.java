package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Hero;
import PaooGame.Maps.Map;
import PaooGame.Observers.CandyObserver;
import PaooGame.Observers.ToothbrushObserver;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State {
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;
    private long initialtime;
    public CandyObserver candies;//observator pt bomboane
    public ToothbrushObserver toothbrushes;//observator pt periute

    /*!< Referinta catre harta curenta.*/
    //private ArrayList<Candy> candies;

    //private ArrayList<Candy> candies;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink,int level) {
        ///Apel al constructorului clasei de baza

        super(refLink);

        ///Construieste harta jocului
        map = new Map(refLink, level);

        hero = Hero.getInstance(refLink);///Construieste eroul
        hero.SetX(160); // ca sa am pozitia de la nivelul 2 a eroului, ca nu imi mai creeaza altul
        hero.SetY(32);

        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);



        refLink.setHero(hero);
        initialtime = System.currentTimeMillis();

        candies = new CandyObserver();
        toothbrushes = new ToothbrushObserver();

        hero.addObserver(candies);
        hero.addObserver(toothbrushes);

        //candies = new ArrayList<Candy>();
        //populateCandy();
    }

  /* private void populateCandy() {
        Candy c;
        c = new Candy(refLink, 4, 5);
        candies.add(c);
       c = new Candy(refLink, 6, 5);
        candies.add(c);

    }*/

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update() {

        map.Update();
        hero.Update();

        if(System.currentTimeMillis()-initialtime>120000 && initialtime!= 0 && refLink.getMap().getLevel() == 1)
            SetState(new LostState(refLink));
        if(System.currentTimeMillis()-initialtime>240000 && initialtime!= 0 && refLink.getMap().getLevel() == 2)
            SetState(new LostState(refLink));

/*        for (Candy c : candies) {
            c.Update();
        }*/
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        map.Draw(g, hero);
        /*for (int i = 0; i < candies.size(); i++)
            candies.get(i).Draw(g);*/

        hero.Draw(g);

        /*g.setColor(new Color(255,248,250));
        g.fillRect(0, 290, 288,30);
        g.setColor(Color.BLACK);*/
        //g.drawLine(0,290,290,290);
        //g.drawRect(0,290,288,32);*/
        g.drawImage(Assets.hud, 0, 290 ,288, 30, null);//desenez hud ul
        candies.Draw(g,refLink);
        toothbrushes.Draw(g,refLink);
        //g.drawString(this.hero.getNrCandies()+"", 32, 312);
        //g.drawString(this.hero.getNrToothbrushes()+"", 100, 312);
        String min = Long.toString(((System.currentTimeMillis()-initialtime)/1000)/60);

        String sec = Long.toString(((System.currentTimeMillis()-initialtime)/1000)%60);
        String aux = ":";
        g.drawString(min, 200, 312);
        g.drawString(aux, 212,312);
        g.drawString(sec, 220,312);

/*        for (Candy c : candies) {
            c.Draw(g);*/

        }
    }

