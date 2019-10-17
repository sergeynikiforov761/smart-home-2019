import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.Arrays;
import java.util.Collections;

public class ProcessingLightEventTest {
    @Test
    public void ProcessingLightEvent() {
        SmartHome smartHome = new SmartHome();

        String lightIdFirst = "1";
        Light lightFirst = new Light(lightIdFirst, true);

        String lightIdSecond = "2";
        Light lightSecond = new Light(lightIdSecond, false);

        smartHome.addRoom(new Room(Arrays.asList(lightFirst), Collections.emptyList(), "lightFirst"));
        smartHome.addRoom(new Room(Arrays.asList(lightSecond), Collections.emptyList(), "lightSecond"));

        SensorEvent eventFirst = new SensorEvent(SensorEventType.LIGHT_ON, lightIdFirst);
        SensorEvent eventSecond = new SensorEvent(SensorEventType.LIGHT_ON, lightIdSecond);

        ProcessingLightEvent processingLightEventFirst = new ProcessingLightEvent(eventFirst, smartHome);
        processingLightEventFirst.processEvent();

        ProcessingLightEvent processingLightEventSecond = new ProcessingLightEvent(eventSecond, smartHome);
        processingLightEventSecond.processEvent();

        Assert.assertTrue(lightFirst.getStatus());
        Assert.assertTrue(lightSecond.getStatus());

        SensorEvent newEventFirst = new SensorEvent(SensorEventType.LIGHT_OFF, lightIdFirst);
        SensorEvent newEventSecond = new SensorEvent(SensorEventType.LIGHT_OFF, lightIdSecond);

        ProcessingLightEvent processingNewLightEventFirst = new ProcessingLightEvent(newEventFirst, smartHome);
        processingNewLightEventFirst.processEvent();

        ProcessingLightEvent processingNewLightEventSecond = new ProcessingLightEvent(newEventSecond, smartHome);
        processingNewLightEventSecond.processEvent();

        Assert.assertFalse(lightFirst.getStatus());
        Assert.assertFalse(lightSecond.getStatus());
    }
}
