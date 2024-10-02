// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.ColorSensorV3;


public class Indexer extends SubsystemBase {
  private final TalonFX m_Indexer;
  private int ballCount = 0;

  private static final I2C.Port onboardI2C = I2C.Port.kOnboard;
   private static ColorSensorV3 intakeSensor;

  private static final ColorMatch colorMatcherIndexer = new ColorMatch();


  /** Creates a new Indexer. */
  public Indexer() {
    m_Indexer = new TalonFX(Constants.HardwarePorts.indexer);
    intakeSensor = new ColorSensorV3(onboardI2C);

    colorMatcherIndexer.addColorMatch(Constants.colorSensor.ColorSensorBlueIntake);
    colorMatcherIndexer.addColorMatch(Constants.colorSensor.ColorSensorRedIntake);

  }
  private void configMotor (TalonFX motor, boolean inverted){
    motor.setInverted(inverted);
    // DO I NEED TO USE THE CONFIG TO SET INVERTED??? COUNTERCLOCKWISE CLOCKWISE THING?
    // TODO Config currents - See constants.java
    // TODO Add feedforward control  kS and kV
  }

  public enum IndexerStates {
    INTAKE(0.6),
      INDEX(0.4),
    OFF(0),
    REV(-0.8);

    private double speed;

        public double getValue() {
            return speed;
        }

        IndexerStates(double speed) {
            this.speed = speed;
        }
  }


  /**
   * Set the speed of the motor.
   * @param percentageOutput Range from -1 to 1
   */
  public void setSpeed(double percentageOutput){
    m_Indexer.set(percentageOutput);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
