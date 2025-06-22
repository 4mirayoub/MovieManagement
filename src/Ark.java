public class Ark {
    private SpeciesQueue<Animal> queue = new SpeciesQueue<>();
    private SpeciesQueue<Animal> in = new SpeciesQueue<>();
    public Ark(){}

    public void Add(Animal animal){
        queue.add(animal);
    }

    public void enterToArk(){
        Animal animal = queue.remove();
        System.out.println("A " + animal.getSpecies() +" enter the Ark");
    }

    public void enterAllToArk(){
        int size = queue.size();
        while (size > 0){
            enterToArk();
            size -= 1;
        }
    }
    @Override
    public String toString(){
        return queue.toString();
    }
    public void showQueue(){
        System.out.println(toString());
    }
}
