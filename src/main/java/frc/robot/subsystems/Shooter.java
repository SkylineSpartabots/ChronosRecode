// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
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

  private final TalonFX m_shooterTop;
  private final TalonFX m_shooterBottom;

//  private  final Follower follow = new Follower(Constants.HardwarePorts.shooterLeader, false );

  private double topVelocitySetpoint = 0;
  private  double bottomVelocitySetpoint = 0;


  final VelocityVoltage topVelocityVoltage = new VelocityVoltage(0);
  final VelocityVoltage bottomVelocityVoltage = new VelocityVoltage(0);

  /** Constructor */
  public Shooter() {
    m_shooterTop = new TalonFX(Constants.HardwarePorts.shooterTop);
    m_shooterBottom = new TalonFX(Constants.HardwarePorts.shooterBottom);

    configMotor(m_shooterTop, true);
    configMotor(m_shooterBottom, false);

//    m_shooterBottom.setControl(follow);
  }

  private void configMotor (TalonFX motor, boolean inverted, double kS, double kV){
    motor.setInverted(inverted);
    // TODO Config currents - See constants.java
            TalonFXConfiguration config = new TalonFXConfiguration();


        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        CurrentLimitsConfigs currentLimitsConfigs = new CurrentLimitsConfigs();
        // TODO Get these values
//        currentLimitsConfigs.SupplyCurrentLimit = Constants.shooterContinuousCurrentLimit;
//        currentLimitsConfigs.SupplyCurrentLimitEnable = true;
//        currentLimitsConfigs.SupplyCurrentThreshold = Constants.shooterPeakCurrentLimit;

            Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.kS = kS;
        slot0Configs.kV = kV;


        config.CurrentLimits = currentLimitsConfigs;
        motor.getConfigurator().apply(config);
        motor.getConfigurator().apply(slot0Configs);
  }

    private void configMotor (TalonFX motor, boolean inverted){
    motor.setInverted(inverted);
    // TODO Config currents - See constants.java
    // TODO Add feedforward control  kS and kV
  }

  // Setters

  // Velocity
  /**
   * Sets the velocity of both the shooter motors
   * @param velocity velocity to set
   */
  public void setVelocity(double velocity) {
      m_shooterTop.setControl(topVelocityVoltage.withVelocity(velocity));
      m_shooterBottom.setControl(bottomVelocityVoltage.withVelocity(velocity));

      topVelocitySetpoint = velocity;
      bottomVelocitySetpoint = velocity;
  }
  /**
   * Sets the velocity of the top shooter motor
   * @param velocity velocity to set
   */
  public void setTopVelocity(double velocity) {
    m_shooterTop.setControl(topVelocityVoltage.withVelocity(velocity));
    topVelocitySetpoint = velocity;

  }
  /**
   * Sets the velocity of the bottom shooter motor
   * @param velocity velocity to set
   */
  public void setBottomVelocity(double velocity) {
    m_shooterBottom.setControl(bottomVelocityVoltage.withVelocity(velocity));
    bottomVelocitySetpoint = velocity;

  }

  // Getters

  // Velocities
    /**
   * Gets the m_shooterTop velocity as a double
   * @return shooterTop velocity
   */
  public double getTopVelocity(){
    return m_shooterTop.getVelocity().getValueAsDouble();
  }
  /**
   * Gets the m_shooterBottom velocity as a double
   * @return shooterBottom velocity
   */
  public double getBottomVelocity(){
    return m_shooterBottom.getVelocity().getValueAsDouble();
  }
  /**
   * Get both top and bottom motor velocities as an array
   * @return Array {shooterTopVelocity, shooterBottomVelocity}
   */
  public double[] getVelocities() {
    return  new double[] {m_shooterTop.getVelocity().getValueAsDouble(), m_shooterBottom.getVelocity().getValueAsDouble()};
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
  /**
   * Get both top and bottom motor setpoints as an array
   * @return Array {topVelocitySetpoint, bottomVelocitySetpoint}
   */
  public double[] getBothSetpoints() {
    return new double[] { topVelocitySetpoint, bottomVelocitySetpoint };
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
