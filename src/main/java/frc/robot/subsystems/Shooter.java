// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
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

  private final TalonFX m_shooterLeader;
  private final TalonFX m_shooterFollower;

//  private  final Follower follow = new Follower(Constants.HardwarePorts.shooterLeader, false );

  private double leaderVelocitySetpoint = 0;
  private  double followerVelocitySetpoint = 0;


  final VelocityVoltage leaderVelocityVoltage = new VelocityVoltage(0);
  final VelocityVoltage followerVelocityVoltage = new VelocityVoltage(0);

  /** Constructor */
  public Shooter() {
    m_shooterLeader = new TalonFX(Constants.HardwarePorts.shooterLeader);
    m_shooterFollower = new TalonFX(Constants.HardwarePorts.shooterFollower);

    configMotor(m_shooterLeader, true);
    configMotor(m_shooterFollower, false);

//    m_shooterFollower.setControl(follow);
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
      m_shooterLeader.setControl(leaderVelocityVoltage.withVelocity(velocity));
      m_shooterFollower.setControl(followerVelocityVoltage.withVelocity(velocity));

      leaderVelocitySetpoint = velocity;
      followerVelocitySetpoint = velocity;
  }
  /**
   * Sets the velocity of the leader shooter motor
   * @param velocity velocity to set
   */
  public void setLeaderVelocity(double velocity) {
    m_shooterLeader.setControl(leaderVelocityVoltage.withVelocity(velocity));
    leaderVelocitySetpoint = velocity;

  }
  /**
   * Sets the velocity of the follower shooter motor
   * @param velocity velocity to set
   */
  public void setFollowerVelocity(double velocity) {
    m_shooterFollower.setControl(followerVelocityVoltage.withVelocity(velocity));
    followerVelocitySetpoint = velocity;

  }

  // Getters

  // Velocities
    /**
   * Gets the m_shooterLeader velocity as a double
   * @return shooterLeader velocity
   */
  public double getLeaderVelocity(){
    return m_shooterLeader.getVelocity().getValueAsDouble();
  }
  /**
   * Gets the m_shooterFollower velocity as a double
   * @return shooterFollower velocity
   */
  public double getFollowerVelocity(){
    return m_shooterFollower.getVelocity().getValueAsDouble();
  }
  /**
   * Get both leader and follower motor velocities as an array
   * @return Array {shooterLeaderVelocity, shooterFollowerVelocity}
   */
  public double[] getVelocities() {
    return  new double[] {m_shooterLeader.getVelocity().getValueAsDouble(), m_shooterFollower.getVelocity().getValueAsDouble()};
  }

// Setpoints
  /**
   * Setpoint of the shooter leader motor
   * @return leaderVelocitySetpoint
   */
  public double getLeaderSetpoint() {
    return  leaderVelocitySetpoint;
  }
  /**
   * Setpoint of the shooter follower motor
   * @return followerVelocitySetpoint
   */
  public double getFollowerSetpoint() {
    return followerVelocitySetpoint;
  }
  /**
   * Get both leader and follower motor setpoints as an array
   * @return Array {leaderVelocitySetpoint, followerVelocitySetpoint}
   */
  public double[] getBothSetpoints() {
    return new double[] { leaderVelocitySetpoint, followerVelocitySetpoint };
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
