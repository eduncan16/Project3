abstract class Toy {
    protected Workshop workShop1;
    protected Workshop workShop2;

    protected Toy(Workshop one, Workshop two)
    {
        this.workShop1 = one;
        this.workShop2 = two;
    }

    abstract public void manufacture();
}

class Bear extends Toy {
    public Bear(Workshop one, Workshop two)
    {
        super(one, two);
    }

    @Override
    public void manufacture()
    {
        System.out.print("Bear ");
        workShop1.work();
        workShop2.work();
    }
}

class Xbox extends Toy {
    public Xbox(Workshop workShop1, Workshop workShop2)
    {
        super(workShop1, workShop2);
    }

    @Override
    public void manufacture()
    {
        System.out.print("Xbox ");
        workShop1.work();
        workShop2.work();
    }
}

interface Workshop
{
    abstract public void work();
}

class Produce implements Workshop {
    public void work()
    {
        System.out.print("Produced");
    }
}

class Assemble implements Workshop {
    public void work()
    {
        System.out.print(" And");
        System.out.println(" Assembled.");
    }
}

class Client1 {
    public static void main(String[] args)
    {
        Toy toy1 = new Bear(new Produce(), new Assemble());
        toy1.manufacture();
        Toy toy2 = new Xbox(new Produce(), new Assemble());
        toy2.manufacture();
    }
}