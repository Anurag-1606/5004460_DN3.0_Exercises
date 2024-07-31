import java.util.HashMap;

public class InventoryManagement {
    private HashMap<String, Product> inventory;

    public InventoryManagement() {
        inventory = new HashMap<>();
    }

    // Add a product
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product already exists.");
        } else {
            inventory.put(product.getProductId(), product);
            System.out.println("Product added successfully.");
        }
    }

    // Update a product
    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete a product
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Display all products
    public void displayAllProducts() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }

    // Find a product by ID
    public Product findProductById(String productId) {
        return inventory.get(productId);
    }

    public static void main(String[] args) {
        InventoryManagement inventoryManagement = new InventoryManagement();

        // Create products
        Product product1 = new Product("001", "Laptop", 10, 999.99);
        Product product2 = new Product("002", "Smartphone", 20, 499.99);
        Product product3 = new Product("003", "Tablet", 15, 299.99);

        // Add products
        inventoryManagement.addProduct(product1);
        inventoryManagement.addProduct(product2);
        inventoryManagement.addProduct(product3);

        // Update a product
        Product updatedProduct2 = new Product("002", "Smartphone", 30, 449.99);
        inventoryManagement.updateProduct(updatedProduct2);

        // Delete a product
        inventoryManagement.deleteProduct("003");

        // Display all products
        inventoryManagement.displayAllProducts();
    }
}
