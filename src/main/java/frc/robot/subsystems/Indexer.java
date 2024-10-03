// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;


public class Indexer extends SubsystemBase {
  private final TalonFX m_Indexer;

  /** Creates a new Indexer. */
  public Indexer() {
    m_Indexer = new TalonFX(Constants.HardwarePorts.indexer);

    m_Indexer.setInverted(false); // no idea
    configMotor(m_Indexer);
  }
  
  private void configMotor(TalonFX motor){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.withKP(0.4); 

        motor.getConfigurator().apply(config);
        motor.getConfigurator().apply(slot0Configs);
  }
  
  public enum IndexerStates {
    INTAKE(0.6),
    INDEX(0.7),
    OFF(0),
    REV(-0.5);

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
