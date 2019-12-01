package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.HomeRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;
import ru.sbt.mipt.oop.remotecontrol.remotecontrolevents.*;

@Configuration
public class RemoteControlConfiguration {

    @Bean
    RemoteControl remoteControlRealisation(LightOnWholeHouse lightOnWholeHouse,
                                           LightOffWholeHouse lightOffWholeHouse,
                                           AlarmActivate alarmActivate,
                                           HallLightOn hallLightOn,
                                           RemoteControlRegistry remoteControlRegistry,
                                           DangerAlarmStateActivate dangerAlarmStateActivate,
                                           HallDoorClosed hallDoorClosed) {
        HomeRemoteControl remoteControl = new HomeRemoteControl();
        remoteControl.addRemoteControl("A", lightOffWholeHouse);
        remoteControl.addRemoteControl("B", lightOnWholeHouse);
        remoteControl.addRemoteControl("C", alarmActivate);
        remoteControl.addRemoteControl("D", hallLightOn);
        remoteControl.addRemoteControl("1", dangerAlarmStateActivate);
        remoteControl.addRemoteControl("2", hallDoorClosed);
        remoteControlRegistry.registerRemoteControl(remoteControl, "1");
        return remoteControl;
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Bean
    LightOffWholeHouse lightOffWholeHouse(SmartHome smartHome) {
        return new LightOffWholeHouse(smartHome);
    }

    @Bean
    LightOnWholeHouse lightOnWholeHouse(SmartHome smartHome) {
        return new LightOnWholeHouse(smartHome);
    }

    @Bean
    HallDoorClosed hallDoorClosed(SmartHome smartHome) {
        return new HallDoorClosed(smartHome);
    }

    @Bean
    HallLightOn hallLightOn(SmartHome smartHome) {
        return new HallLightOn(smartHome);
    }

    @Bean
    AlarmActivate alarmActivate(Alarm alarm) {
        return new AlarmActivate(alarm);
    }

    @Bean
    DangerAlarmStateActivate dangerAlarmStateActivate(Alarm alarm) {
        return new DangerAlarmStateActivate(alarm);
    }
}
