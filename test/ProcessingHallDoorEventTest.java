import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.Arrays;
import java.util.Collections;

public class ProcessingHallDoorEventTest {
    @Test
    public void ProcessingHallDoorEvent() {
        SmartHome smartHome = new SmartHome();

        String lightIdFirst = "1";
        Light lightFirst = new Light(lightIdFirst, true);

        String lightIdSecond = "2";
        Light lightSecond = new Light(lightIdSecond, false);

        String doorIdFirst = "1";
        Door doorFirst = new Door(true, doorIdFirst);

        smartHome.addRoom(new Room(Arrays.asList(lightFirst), Arrays.asList(doorFirst), "hall"));
        smartHome.addRoom(new Room(Arrays.asList(lightSecond), Collections.emptyList(), "roomSecond"));

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorIdFirst);

        ProcessingHallDoorEvent processingHallDoorEvent = new ProcessingHallDoorEvent(event, smartHome);
        processingHallDoorEvent.processEvent();

        ProcessingDoorEvent processingDoorEvent = new ProcessingDoorEvent(event, smartHome);
        processingDoorEvent.processEvent();

        Assert.assertTrue(lightFirst.getStatus());
        Assert.assertFalse(lightSecond.getStatus());
        Assert.assertFalse(doorFirst.getStatus());
    }
}
