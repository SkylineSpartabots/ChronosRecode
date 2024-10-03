// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private IntakeState currentState = IntakeState.OFF;
    private TalonFX m_Intake;

    /* Static Instance Handling */
    private static Intake instance;

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }

    public Intake() {
        m_Intake = new TalonFX(Constants.HardwarePorts.intakeM);

        configMotor(m_Intake, false);
    }

    /**
     * Configures the Intake motor with the appropriate settings:
     * - Inverted
     * - Current Limits
     * - Neutral Mode
     */
    private void configMotor(TalonFX motor, boolean inverted) {
        motor.setInverted(inverted);

      TalonFXConfiguration config = new TalonFXConfiguration();
      config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
      config.MotorOutput.NeutralMode = NeutralModeValue.Coast;

      Slot0Configs slot0Configs = new Slot0Configs();
      slot0Configs.withKP(0.4);

      motor.getConfigurator().apply(config);
      motor.getConfigurator().apply(slot0Configs);
    }

  /**
     * Determines the speed of the Intake motors
     */
    public enum IntakeState {
        ON(0.6),
        OFF(0),
        REV(-0.6);

        private double speed;

        IntakeState(double speed) {
            this.speed = speed;
        }
    }
    
    public IntakeState getCurrentState() {
        return currentState;
    }
    public double getSpeed() {
        return m_Intake.get();
    }
    public double getVoltage() {
        return m_Intake.getMotorVoltage().getValueAsDouble();
    }
    
    public void setState(IntakeState state) {
        currentState = state;
        m_Intake.set(state.speed);
    }
    public void setSpeed(double speed) {
        m_Intake.set(speed);
    }
    public void setVoltage(double voltage) {
        m_Intake.setVoltage(voltage);
    }
    

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }


}
