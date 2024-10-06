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

    private final TalonFX m_leaderShooter;
    private final TalonFX m_followerShooter;

//  private  final Follower follow = new Follower(Constants.HardwarePorts.shooterLeader, false );

    private double velocitySetpoint = 0;

    final VelocityVoltage topVelocityVoltage = new VelocityVoltage(0);

    /**
     * Constructor
     */
    public Shooter() {
        m_leaderShooter = new TalonFX(Constants.HardwarePorts.leftShooter);
        m_followerShooter = new TalonFX(Constants.HardwarePorts.rightShooter);

        m_followerShooter.setControl(new Follower(m_leaderShooter.getDeviceID(), true)); // TODO not sure which direction

        configMotor(m_leaderShooter, true, 0.005, 0.025);
    }

    private void configMotor(TalonFX motor, boolean inverted, double kS, double kV) {
        motor.setInverted(inverted);
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
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
     *
     * @param velocity velocity to set
     */
    public void setVelocity(double velocity) {
        m_leaderShooter.setControl(topVelocityVoltage.withVelocity(velocity));
        velocitySetpoint = velocity;
    }

    public void setSpeed(double speed) {
        m_leaderShooter.set(speed);
    }

    public void setVoltage(double voltage) {
        m_leaderShooter.setVoltage(voltage);
    }

    // Getters

    /**
     * Gets the velocity of the shooter motor
     *
     * @return velocity
     */
    public double getVelocity() {
        return m_leaderShooter.getVelocity().getValueAsDouble();
    }

    public double getSpeed() {
        return m_leaderShooter.get();
    }

    public double getVoltage() {
        return m_leaderShooter.getMotorVoltage().getValueAsDouble();
    }

// Setpoints

    /**
     * Setpoint of the shooter  motor
     *
     * @return velocitySetpoint
     */
    public double getVelocitySetpoint() {
        return velocitySetpoint;
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
