package warehouse_administration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.DoubleStream;

/**
 * Class model for warehouse_administration.Warehouse
 *
 * Allows adding shelves to a warehouse and lookup of products.
 * Contains getters to monitor the state of the warehouse.
 *
 * @version: 1.0
 * @author: Fabian Diemand
 */
public class Warehouse {
    private final ArrayList<Shelf> warehouse;

    /**
     * Create an empty warehouse.
     */
    public Warehouse(){
        this.warehouse = new ArrayList<>();
    }

    /**
     * <b>Constructor overload</b> <br>
     * Create a warehouse with a predefined quantity of shelves with the same capacity
     * @param quantity int: how many shelves to be put into the warehouse
     * @param shelfWeightLimit double: weight limit for all shelves
     * @throws ZeroShelvesException if quantity is negative or 0
     * @throws InvalidWeightException if weightLimit is less than or equal to 0
     */
    public Warehouse(int quantity, int shelfWeightLimit) throws ZeroShelvesException, InvalidWeightException{
        if(quantity <= 0) throw new ZeroShelvesException();
        if(shelfWeightLimit<=0) throw new InvalidWeightException("All shelves must have a weight limit bigger than 0.");

        this.warehouse = new ArrayList<>();
        this.addShelf(quantity, shelfWeightLimit);
    }

    /**
     * <b>Constructor overload</b> <br>
     * Create a warehouse with a predefined quantity of shelves with different capacities
     * @param quantity int: how many shelves to be put into the warehouse
     * @param shelfWeightLimits double[]: weight limit for each shelf
     */
    public Warehouse(int quantity, double[] shelfWeightLimits) throws ZeroShelvesException, InvalidWeightException{
            this.warehouse = new ArrayList<>();
            this.addShelves(quantity, shelfWeightLimits);
    }


    /**
     * Add a predefined quantity of empty shelves with different capacities
     * @param quantity int: how many shelves to be put into the warehouse
     * @param shelfWeightLimits double[]: weight limit for each shelf
     * @throws ZeroShelvesException if quantity is negative or 0
     * @throws InvalidWeightException if weightLimit is less than or equal to 0
     */
    public void addShelves(int quantity, double[] shelfWeightLimits) throws ZeroShelvesException, InvalidWeightException{
            if(quantity <= 0){throw new ZeroShelvesException();}

            if(shelfWeightLimits.length < quantity || DoubleStream.of(shelfWeightLimits).anyMatch(x -> x<=0)){
                throw new InvalidWeightException("All shelves must have a weight limit bigger than 0.");
            }

            for(int i = 0; i<quantity; i++) {
                Shelf shelf = new Shelf(shelfWeightLimits[i]);
                shelf.setShelfNumber(warehouse.size() + 1);
                warehouse.add(shelf);
            }
    }


    /**
     * Add a specific shelf instance to the warehouse.
     * @param shelf warehouse_administration.Shelf: to be added to the warehouse
     */
    public void addShelf(Shelf shelf){
        warehouse.add(shelf);
    }

    /**
     * <b>Overload of addShelf(warehouse_administration.Shelf shelf)</b> <br>
     * Add a predefined quantity of empty shelves with the same capacity
     * @param quantity int: how many shelves to be put into the warehouse
     * @param shelfWeightLimit double: weight limit for all shelves
     * @throws ZeroShelvesException if quantity is negative or 0
     * @throws InvalidWeightException if weightLimit is less than or equal to 0
     */
    public void addShelf(int quantity, int shelfWeightLimit) throws ZeroShelvesException, InvalidWeightException{
            if(quantity <= 0){throw new ZeroShelvesException();}
            if(shelfWeightLimit<=0){throw new InvalidWeightException("All shelves must have a weight limit bigger than 0.");}
            for(int i = 0; i<quantity; i++) {
                Shelf shelf = new Shelf(shelfWeightLimit);
                shelf.setShelfNumber(warehouse.size() + 1);
                warehouse.add(shelf);
            }
    }

    /**
     * Looks up a certain product by iterating over the product lists of all shelves in the warehouse.
     * @param searchTerm String: name of the product to be looked up (casing ignored)
     */
    public void searchProduct(String searchTerm) throws  ProductNotFoundException{
            String shelves = lookupProduct(searchTerm).toString().replace("[", "").replace("]", "");
            System.out.println("Found your product in shelf/ shelves: " + shelves);
    }

    /**
     * @return total storing capacity of the warehouse in kilograms
     */
    public double getTotalStorage(){
        return calculateTotalStorage();
    }

    /**
     * @return remaining storing capacity of the warehouse in kilograms
     */
    public double getRemainingStorage(){
        return calculateRemainingStorage();
    }

    /*
     Looks up a product in the warehouse, by iterating over the product lists of the shelves in the warehouse.
     @param searchTerm String: name of the product to be looked up (casing ignored)
     @throws warehouse_administration.ProductNotFoundException: if the product is found in any shelf
     @return ArrayList containing numbers of the shelves the product was found in
     */
    private ArrayList<Integer> lookupProduct(String searchTerm) throws ProductNotFoundException{
        ArrayList<Integer> shelfNumbers = new ArrayList<>();
        for(Shelf shelf: warehouse){
            if(shelf.containsProduct(searchTerm)){
                shelfNumbers.add(shelf.getShelfNumber());
            }
        }
        if(shelfNumbers.size() == 0){
            throw new ProductNotFoundException(searchTerm + "could not be found.");
        } else {
            Collections.sort(shelfNumbers);
            return shelfNumbers;
        }
    }

    /*
    Calculates the total storage capacity, by summing up the weight limits of all shelves within the warehouse.
    @return total storage capacity in kilograms
     */
    private double calculateTotalStorage(){
        double storage = 0;

        for(Shelf shelf: warehouse){
            storage += shelf.getWeightLimit();
        }

        return storage;
    }

    /*
    Calculates the remaining storage capacity, by summing up the remaining capacities of all shelves within the warehouse.
    @return remaining storage capacity in kilograms
     */
    private double calculateRemainingStorage(){
        double storage = 0;

        for(Shelf shelf: warehouse){
            storage += shelf.getWeightCapacity();
        }

        return storage;
    }
}
