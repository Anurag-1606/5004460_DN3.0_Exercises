public class LinearSearch {
    public static Product linearSearchByName(Product[] products, String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Smartphone", "Electronics"),
            new Product(3, "Shirt", "Apparel"),
            new Product(4, "Shoes", "Footwear"),
            new Product(5, "Watch", "Accessories")
        };

        Product result = linearSearchByName(products, "Smartphone");
        if (result != null) {
            System.out.println("Product found: " + result);
        } else {
            System.out.println("Product not found.");
        }
    }
}
