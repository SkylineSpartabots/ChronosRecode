// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  /**
   * Returns a singleton instance of the Shooter class
   */
  private static Shooter instance = null;
  public static Shooter getInstance() {
    if (instance == null) {
      instance = new Shooter();
    }
    return instance;
  }

  private final TalonFX m_leaderShooterTop;
  private final TalonFX m_followerShooterBottom;

//  private  final Follower follow = new Follower(Constants.HardwarePorts.shooterLeader, false );

  private double topVelocitySetpoint = 0;
  private  double bottomVelocitySetpoint = 0;

  final VelocityVoltage topVelocityVoltage = new VelocityVoltage(0);

  /** Constructor */
  public Shooter() {
    m_leaderShooterTop = new TalonFX(Constants.HardwarePorts.shooterTop);
    m_followerShooterBottom = new TalonFX(Constants.HardwarePorts.shooterBottom);

    m_followerShooterBottom.setControl(new Follower(m_leaderShooterTop.getDeviceID(), true)); // TODO not sure which direction
    m_leaderShooterTop.setInverted(true);
    configMotor(m_leaderShooterTop, 0.01, 0.04);
  }

  private void configMotor(TalonFX motor, double kS, double kV){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        // CurrentLimitsConfigs currentLimitsConfigs = new CurrentLimitsConfigs();
        //currentLimitsConfigs.SupplyCurrentLimit = Constants.shooterContinuousCurrentLimit;
        //currentLimitsConfigs.SupplyCurrentLimitEnable = true;
        //currentLimitsConfigs.SupplyCurrentThreshold = Constants.shooterPeakCurrentLimit;

        /*
          No current limits for shooter were used in orginal chornos
          so we arnt getting any unless we guess or find time to test

          Robot wont be under much stress so as long as
          we set shooting based on triggers and not joystick
        */

        // values from old code, kF was set to 0.05 so im making a educated guess of the static and velocity ones
        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.withKS(kS);
        slot0Configs.withKV(kV);
        slot0Configs.withKP(0.12); 

        // config.CurrentLimits = currentLimitsConfigs;
        motor.getConfigurator().apply(config);
        motor.getConfigurator().apply(slot0Configs);
  }

  // Setters

  // Velocity
  /**
   * Sets the velocity of both the shooter motors
   * @param velocity velocity to set
   */
  public void setVelocity(double velocity) {
      m_leaderShooterTop.setControl(topVelocityVoltage.withVelocity(velocity));

      topVelocitySetpoint = velocity;
      bottomVelocitySetpoint = velocity;
  }

  // Getters

  public double getVelocity() {
    return m_leaderShooterTop.getVelocity().getValueAsDouble();
  }

// Setpoints
  /**
   * Setpoint of the shooter top motor
   * @return topVelocitySetpoint
   */
  public double getTopSetpoint() {
    return  topVelocitySetpoint;
  }
  /**
   * Setpoint of the shooter bottom motor
   * @return bottomVelocitySetpoint
   */
  public double getBottomSetpoint() {
    return bottomVelocitySetpoint;
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
