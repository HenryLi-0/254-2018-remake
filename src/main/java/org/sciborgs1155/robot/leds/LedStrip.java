package org.sciborgs1155.robot.leds;

import static org.sciborgs1155.robot.leds.LedConstants.*;

import java.util.function.Function;

import static org.sciborgs1155.robot.Ports.Leds.*;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;

public class LedStrip extends SubsystemBase implements AutoCloseable, Logged{
    private final AddressableLED led = new AddressableLED(PORT);


    public LedStrip(){
        led.setLength(LENGTH);
        led.setData(new AddressableLEDBuffer(LENGTH));
        led.start();
    }

    public Command set(AddressableLEDBuffer buffer){
        return run(() -> led.setData(buffer));
    }

    public Command none(){
        return run(() -> {});
    }

    public Command idk(){
        return set(singleColor(Color.kYellow));
    }

    public Command idk2(){
        return set(singleColor(Color.kGreen));
    }

    public AddressableLEDBuffer singleColor(Color color){
        return genBuffer(i -> color);
    }

    public static AddressableLEDBuffer genBuffer(Function<Integer, Color> f){
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LENGTH);
        buffer.forEach((i, r, g, b) -> buffer.setLED(i, f.apply(i)));
        return buffer;
    }



    @Override
    public void close() throws Exception {
        led.close();
    }
}
