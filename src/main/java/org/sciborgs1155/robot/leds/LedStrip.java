package org.sciborgs1155.robot.leds;

import static org.sciborgs1155.robot.Ports.Leds.*;
import static org.sciborgs1155.robot.leds.LedConstants.*;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.Function;
import monologue.Logged;

public class LedStrip extends SubsystemBase implements AutoCloseable, Logged {
  private final AddressableLED led = new AddressableLED(PORT);

  public LedStrip() {
    led.setLength(LENGTH);
    led.setData(new AddressableLEDBuffer(LENGTH));
    led.start();
  }

  public Command set(AddressableLEDBuffer buffer) {
    return run(() -> led.setData(buffer));
  }

  public Command none() {
    return run(() -> {});
  }

  public Command yellow() {
    return set(singleColor(Color.kYellow));
  }

  public Command green() {
    return set(singleColor(Color.kGreen));
  }

  /**
   * Returns an AddressableLEDBuffer with leds following instructions of a given function
   * @param f
   * @return
   */
  public static AddressableLEDBuffer genBuffer(Function<Integer, Color> f) {
    AddressableLEDBuffer buffer = new AddressableLEDBuffer(LENGTH);
    buffer.forEach((i, r, g, b) -> buffer.setLED(i, f.apply(i)));
    return buffer;
  }

  /**
   * Returns an AddressableLEDBuffer of a single given color
   * @param color
   * @return
   */
  public AddressableLEDBuffer singleColor(Color color) {
    return genBuffer(i -> color);
  }


  @Override
  public void close() throws Exception {
    led.close();
  }
}
