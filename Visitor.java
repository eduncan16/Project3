interface ItemElement
{
    public int accept(Visitor visitor);
}

class Book implements ItemElement
{
    private int price;
    private String isbn;

    public Book(int cost, String isbn)
    {
        this.price=cost;
        this.isbn =isbn;
    }

    public int getPrice()
    {
        return price;
    }

    public String getIsbn()
    {
        return isbn;
    }

    @Override
    public int accept(Visitor visitor)
    {
        return visitor.visit(this);
    }

}

class Pants implements ItemElement
{
    private int price;
    private String size;

    public Pants(int cost, String size)
    {
        this.price=cost;
        this.size=size;
    }

    public int getPrice()
    {
        return price;
    }

    public String getSize()
    {
        return size;
    }

    @Override
    public int accept(Visitor visitor)
    {
        return visitor.visit(this);
    }

}

class Fruit implements ItemElement
{
    private int pricePerPound;
    private int weight;
    private String name;

    public Fruit(int pricePerPound, int wt, String nm)
    {
        this.pricePerPound = pricePerPound;
        this.weight=wt;
        this.name = nm;
    }

    public int getPricePerPound()
    {
        return pricePerPound;
    }

    public int getWeight()
    {
        return weight;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public int accept(Visitor visitor)
    {
        return visitor.visit(this);
    }

}

interface Visitor
{

    int visit(Book book);
    int visit(Fruit fruit);
    int visit(Pants pants);
}

class VisitorImpl implements Visitor
{

    @Override
    public int visit(Book book)
    {
        int cost = book.getPrice();
        System.out.println("ISBN = "+book.getIsbn() + " cost = $"+cost);
        return cost;
    }

    @Override
    public int visit(Fruit fruit)
    {
        int cost = fruit.getPricePerPound()*fruit.getWeight();
        System.out.println(fruit.getName() + " cost = $" + cost);
        return cost;
    }

    @Override
    public int visit(Pants pants)
    {
        int cost = pants.getPrice();
        System.out.println("Size = " + pants.getSize() + " cost = $" + cost);
        return cost;
    }

}

class Client3
{

    public static void main(String[] args)
    {
        ItemElement[] items = new ItemElement[]{new Book(20, "1234"),
                new Book(100, "5678"), new Fruit(10, 2, "Banana"),
                new Fruit(5, 5, "Apple")};
                new Pants(100, "XXL");

        int total = calculatePrice(items);
        System.out.println("Total Cost = $"+total);
    }

    private static int calculatePrice(ItemElement[] items)
    {
        Visitor visitor = new VisitorImpl();
        int sum=0;
        for(ItemElement item : items)
        {
            sum = sum + item.accept(visitor);
        }
        return sum;
    }

}