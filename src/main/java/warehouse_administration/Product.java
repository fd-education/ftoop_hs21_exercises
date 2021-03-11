package warehouse_administration;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Class model for for Products
 *
 * @version : 1.0
 * @author : Fabian Diemand
 */
public class Product {

    @Getter @Setter @NonNull
    private String name;

    @Getter @Setter @NonNull
    private double weight;

    /**
     * Instantiate a warehouse_administration.Product.
     *
     * @param name String; used to lookup product after storing
     * @param weight double; must be positive, limits storing quantity
     * @exception InvalidWeightException thrown if weight is 0 or negative
     */
    public Product(String name, double weight) throws InvalidWeightException{

           if (weight < 0) {
               throw new InvalidWeightException("Product must have a weight bigger than 0");
           }

           this.name = name;
           this.weight = weight;
    }

    @Override
    public String toString() {
        return (this.name + ": " + this.weight + "kg");
    }
}
