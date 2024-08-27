package org.sciborgs1155.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sciborgs1155.robot.leds.LedConstants;
import org.sciborgs1155.robot.leds.LedStrip;

public class LedTest {
  LedStrip led;

  @BeforeEach
  public void setup() {
    led = new LedStrip();
  }

  @Test
  public void testSingleColor() {
    led.green();
    String correct = "[";
    for (int i = 0; i < LedConstants.LENGTH; i++) {
      correct += "#008000" + (i == LedConstants.LENGTH - 1 ? "]" : ",");
    }

    assertEquals(correct, LedStrip.getLedListString(LedStrip.singleColor(Color.kGreen)));
  }

  @AfterEach
  public void afterEach() throws Exception {
    CommandScheduler.getInstance().unregisterAllSubsystems();
    CommandScheduler.getInstance().cancelAll();
    led.close();
  }
}
