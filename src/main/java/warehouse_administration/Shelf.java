package warehouse_administration;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashMap;

/**
 * Class model for Shelves <br>
 *
 * Allows storing and taking products, limited by the total weight, into and from the shelves. <br>
 * Possible to lookup products by their exact name (casing ignored). <br>
 * Contains setter to set a shelf's number within a warehouse. <br>
 * Contains various getters to monitor the state of shelves. <br>
 *
 * @version: 1.0
 * @author: Fabian Diemand
 */
public class Shelf {
    @Getter @NonNull
    private final double weightLimit;
    @Getter @NonNull
    private double weightCapacity;
    @Getter @Setter @NonNull
    private int shelfNumber;
    private final HashMap<Product, Integer> productList;

    /**
     * Create a shelf
     * @param weightLimit double: Limit of weight to be stored in this shelf
     */
    public Shelf(double weightLimit){

        this.weightLimit = weightLimit;
        this.weightCapacity = weightLimit;
        productList = new HashMap<>();
    }

    /**
     * @return number of different products (not total quantity of products!)
     */
    public int getNumberOfDifferentProducts(){
        return productList.size();
    }

    /**
     * @return total quantity of all products
     */
    public int getTotalNumberOfProducts(){ return countProducts();}

    /**
     * Clears the shelf by removing all products from the list
     */
    public void clearShelf(){productList.clear();}

    /**
     * Lookup a certain product within the shelf
     * @param searchTerm String product name to look for (casing ignored)
     * @return boolean TRUE if shelf contains product, else FALSE
     */
    public boolean containsProduct(String searchTerm){
        return lookForProduct(searchTerm);
    }

    /**
     * Store a quantity of a certain product to the shelf
     * @param product warehouse_administration.Product instance to be stored
     * @param quantity int Number of instances to store (limited by weight!)
     */
    public void storeProducts(Product product, int quantity) throws InvalidWeightException{
        double totalWeight = product.getWeight() * quantity;

        decreaseWeightCapacity(totalWeight);
        addProducts(product, quantity);
    }

    /**
     * Take products from the shelf.
     * @param product warehouse_administration.Product to be removed from shelf
     * @param quantity How many products to be removed
     */
    public void takeProducts(Product product, int quantity) throws ProductNotFoundException{
            double totalWeight = product.getWeight() * quantity;
            increaseWeightCapacity(totalWeight);

            removeProducts(product, quantity);
            System.out.println("You got " + quantity + " " + product.getName() + "s. \n"
                                + productList.get(product) + " " + product.getName() + "s left.");
    }

    /*
    warehouse_administration.Product is looked up inside the productList; case gets ignored
    @params searchTerm: the name of the product to be looked for
     */
    private boolean lookForProduct(String searchTerm){
        boolean found =  false;
        for(Product key: productList.keySet()){
            if(key.getName().equalsIgnoreCase(searchTerm)){
                found = true;
                break;
            }
        }
        return found;
    }

    /*
    The total number of products is evaluated by summing up the values from the HashMap productList
     */
    private int countProducts(){
        int sum = 0;

        for(int value: productList.values()){
            sum += value;
        }
        return sum;
    }

    /*
     warehouse_administration.Product is put into HashMap
     @param product warehouse_administration.Product key to store into the HashMap
     @param quantity int value for each product
     */
    private void addProducts(Product product, int quantity){
        this.productList.put(product, quantity);
    }

    /*
    A certain quantity of products gets removed from the shelf.
    @param product warehouse_administration.Product warehouse_administration.Product key to be removed or updated in the HashMap
    @param quantity int quantity of products from a certain instance to be removed
    @throws NotEnoughProductsException if there are not enough products on the shelf
     */
    private void removeProducts(Product product, int quantity) throws ProductNotFoundException{
        int quantityLeft = this.productList.get(product);
        if(quantityLeft - quantity < 0)
            throw new ProductNotFoundException("Can't remove that many products. Not enough products left.");
        else if(quantityLeft - quantity == 0) this.productList.remove(product);
        else productList.put(product, quantityLeft - quantity);
    }

    /*
    After removing a number of products from the shelf, the capacity has to be increased.
    @param totalWeight double value for how much the capacity can be raised
    @throws NotEnoughProductsException if capacity exceeds the weight limit (too many products were removed)
     */
    private void increaseWeightCapacity(double totalWeight) throws ProductNotFoundException{
        if((this.weightCapacity + totalWeight) > this.weightLimit){
            throw new ProductNotFoundException("Can't remove that many products. Not enough products left.");
        } else {
            this.weightCapacity += totalWeight;
        }
    }

    /*
    After storing a number of products to the shelf, the capacity has to be decreased.
    @param totalWeight double value for how much the capacity must be reduced
    @throws OverweightException if capacity goes below 0 (products are to heavy; limit is exceeded)
     */
    private void decreaseWeightCapacity(double totalWeight) throws InvalidWeightException{
        if((this.weightCapacity - totalWeight) < 0){
            throw new InvalidWeightException("Adding these products will exceed the shelf's weight limit.");
        } else {
            this.weightCapacity -= totalWeight;
        }
    }
}
