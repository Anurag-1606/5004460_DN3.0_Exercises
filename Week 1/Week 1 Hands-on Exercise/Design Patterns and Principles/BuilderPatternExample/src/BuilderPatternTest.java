public class BuilderPatternTest {
    public static void main(String[] args) {
        // Creating a Computer instance with all options enabled
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM(32)
                .setStorage(1024)
                .enableGraphicsCard(true)
                .enableBluetooth(true)
                .build();

        System.out.println("Gaming PC: " + gamingPC);

        // Creating a Computer instance with minimal configuration
        Computer officePC = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM(8)
                .setStorage(256)
                .build();

        System.out.println("Office PC: " + officePC);

        // Creating a Computer instance with some options
        Computer developerPC = new Computer.Builder()
                .setCPU("AMD Ryzen 7")
                .setRAM(16)
                .setStorage(512)
                .enableGraphicsCard(true)
                .build();

        System.out.println("Developer PC: " + developerPC);
    }
}
