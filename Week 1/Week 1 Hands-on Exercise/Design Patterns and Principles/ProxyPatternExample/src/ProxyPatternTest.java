
public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("test_image1.jpg");
        Image image2 = new ProxyImage("test_image2.jpg");

        // Image will be loaded from the server the first time
        System.out.println("First call to display() for image1:");
        image1.display(); 
        System.out.println("\nSecond call to display() for image1:");
        image1.display(); // No loading, image is cached

        // Image will be loaded from the server the first time
        System.out.println("\nFirst call to display() for image2:");
        image2.display(); 
    }
}
