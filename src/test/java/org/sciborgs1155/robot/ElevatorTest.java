package org.sciborgs1155.robot;

import static edu.wpi.first.units.Units.Meters;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sciborgs1155.lib.TestingUtil.*;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.MAX_HEIGHT;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.MIN_HEIGHT;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sciborgs1155.robot.elevator.Elevator;

public class ElevatorTest {
  Elevator elevator;

  @BeforeEach
  public void setup() {
    elevator = Elevator.create();
  }

  @Test
  public void testMinMax() {
    run(elevator.goToMax());
    fastForward();
    assertEquals(MAX_HEIGHT.in(Meters), elevator.getPosition(), 0.01);
    run(elevator.goToMin());
    fastForward();
    assertEquals(MIN_HEIGHT.in(Meters), elevator.getPosition(), 0.01);
  }

  @ParameterizedTest
  @ValueSource(doubles = {0, 0.25, 0.5, 0.75, 1})
  public void testPercent(double p) {
    run(elevator.goToPercent(() -> p));
    fastForward();
    assertEquals(
        MIN_HEIGHT.in(Meters) + p * (MAX_HEIGHT.in(Meters) - MIN_HEIGHT.in(Meters)),
        elevator.getPosition(),
        0.01);
  }

  @AfterEach
  public void afterEach() throws Exception {
    closeSubsystem(elevator);
  }
}
