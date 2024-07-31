
public class CommandPatternTest {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);

        Command kitchenLightOn = new LightOnCommand(kitchenLight);
        Command kitchenLightOff = new LightOffCommand(kitchenLight);

        RemoteControl remoteControl = new RemoteControl();

        // Turn on the living room light
        remoteControl.setCommand(livingRoomLightOn);
        remoteControl.pressButton();

        // Turn off the living room light
        remoteControl.setCommand(livingRoomLightOff);
        remoteControl.pressButton();

        // Turn on the kitchen light
        remoteControl.setCommand(kitchenLightOn);
        remoteControl.pressButton();

        // Turn off the kitchen light
        remoteControl.setCommand(kitchenLightOff);
        remoteControl.pressButton();
    }
}
