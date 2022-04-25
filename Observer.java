import java.util.ArrayList;
import java.util.Iterator;

interface Subject
{
    public void registerObserver(Observer o);
    public void unregisterObserver(Observer o);
    public void notifyObservers();
}

class Character implements Subject
{
    int health;
    int mana;
    ArrayList<Observer> observerList;

    public Character() {
        observerList = new ArrayList<Observer>();
    }

    public void setHealth(int health){
        this.health=health;
    }

    public void setMana(int mana){
        this.mana=mana;
    }

    public int getHealth(){
        return health;
    }

    public int getMana(){
        return mana;
    }


    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(observerList.indexOf(o));
    }

    @Override
    public void notifyObservers()
    {
        for (Iterator<Observer> it =
             observerList.iterator(); it.hasNext();)
        {
            Observer o = it.next();
            o.update(health, mana);
        }
    }


    public void dataChanged()
    {

        health = getHealth();
        mana = getMana();

        notifyObservers();
    }
}


interface Observer
{
    public void update(int health, int mana);
}


class Display implements Observer
{
    private int health;
    private int mana;

    public void update(int health, int mana)
    {
        this.health = health;
        this.mana = mana;
        display();
    }

    public void display()
    {
        System.out.println("\nCharacter Display:\n"
                + "Health: " + health +
                "\nMana:" + mana);
    }
}

class Client2
{
    public static void main(String args[])
    {
        Display display = new Display();
        Character character = new Character();

        character.registerObserver(display);
        character.dataChanged();

        character.setHealth(100);
        character.setMana(100);
        character.dataChanged();

        character.unregisterObserver(display);
        character.setHealth(0);
        character.setMana(0);
        character.dataChanged();
    }
}