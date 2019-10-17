import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.Arrays;
import java.util.Collections;

public class ProcessingDoorEventTest {
    @Test
    public void ProcessingDoorEvent() {
        SmartHome smartHome = new SmartHome();

        String doorIdFirst = "1";
        Door doorFirst = new Door(true, doorIdFirst);

        String doorIdSecond = "2";
        Door doorSecond = new Door(false, doorIdSecond);

        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(doorFirst), "roomFirst"));
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(doorSecond), "roomSecond"));

        SensorEvent eventFirst = new SensorEvent(SensorEventType.DOOR_OPEN, doorIdFirst);
        SensorEvent eventSecond = new SensorEvent(SensorEventType.DOOR_OPEN, doorIdSecond);

        ProcessingDoorEvent processingDoorEventFirst = new ProcessingDoorEvent(eventFirst, smartHome);
        processingDoorEventFirst.processEvent();

        ProcessingDoorEvent processingDoorEventSecond = new ProcessingDoorEvent(eventSecond, smartHome);
        processingDoorEventSecond.processEvent();

        Assert.assertTrue(doorFirst.getStatus());
        Assert.assertTrue(doorSecond.getStatus());

        SensorEvent newEventFirst = new SensorEvent(SensorEventType.DOOR_CLOSED, doorIdFirst);
        SensorEvent newEventSecond = new SensorEvent(SensorEventType.DOOR_CLOSED, doorIdSecond);

        ProcessingDoorEvent processingNewDoorEventFirst = new ProcessingDoorEvent(newEventFirst, smartHome);
        processingNewDoorEventFirst.processEvent();

        ProcessingDoorEvent processingNewDoorEventSecond = new ProcessingDoorEvent(newEventSecond, smartHome);
        processingNewDoorEventSecond.processEvent();

        Assert.assertFalse(doorFirst.getStatus());
        Assert.assertFalse(doorSecond.getStatus());
    }
}
