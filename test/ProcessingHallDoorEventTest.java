import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.Arrays;
import java.util.Collections;

public class ProcessingHallDoorEventTest {
    @Test
    public void ProcessingHallDoorEventRightRoom() {
        SmartHome smartHome = new SmartHome();

        String lightIdFirst = "1";
        Light lightFirst = new Light(lightIdFirst, true);

        String lightIdSecond = "2";
        Light lightSecond = new Light(lightIdSecond, false);

        String doorIdFirst = "1";
        Door doorFirst = new Door(true, doorIdFirst);

        smartHome.addRoom(new Room(Arrays.asList(lightFirst, lightSecond), Arrays.asList(doorFirst), "hall"));

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorIdFirst);

        ProcessingHallDoorEvent processingHallDoorEvent = new ProcessingHallDoorEvent(event, smartHome);
        processingHallDoorEvent.processEvent();

        Assert.assertFalse(lightFirst.getStatus());
        Assert.assertFalse(lightSecond.getStatus());
    }

    @Test
    public void ProcessingHallDoorEventWrongRoom(){
        SmartHome smartHome = new SmartHome();

        String lightIdFirst = "1";
        Light lightFirst = new Light(lightIdFirst, true);

        String lightIdSecond = "2";
        Light lightSecond = new Light(lightIdSecond, false);

        String doorIdFirst = "1";
        Door doorFirst = new Door(true, doorIdFirst);

        smartHome.addRoom(new Room(Arrays.asList(lightFirst, lightSecond), Arrays.asList(doorFirst), "roomSecond"));

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorIdFirst);

        ProcessingHallDoorEvent processingHallDoorEvent = new ProcessingHallDoorEvent(event, smartHome);
        processingHallDoorEvent.processEvent();

        Assert.assertTrue(lightFirst.getStatus());
        Assert.assertFalse(lightSecond.getStatus());
    }

}
