// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private IntakeState _currentState = IntakeState.OFF;
    private TalonFX _intakeM;

    private double getVoltage() {
        return _intakeM.getMotorVoltage().getValueAsDouble();
    }

    private void setSpeed(double speed) {
        _intakeM.set(speed);
    }

    public Intake() {
        _intakeM = new TalonFX(Constants.HardwarePorts.intakeM);
    }

    /** 
     * Configures the Intake motor with the appropriate settings:
     * - Inverted
     * - Current Limits
     * - Neutral Mode
     */
    private void configMotor(TalonFX motor, boolean inverted) {
        motor.setInverted(inverted);

        TalonFXConfigurator configurator = motor.getConfigurator();
        CurrentLimitsConfigs currentConfigs = new CurrentLimitsConfigs();
        Slot0Configs slot0Configs = new Slot0Configs();

        currentConfigs.SupplyCurrentLimitEnable = true;
        currentConfigs.SupplyCurrentLimit = Constants.CurrentLimits.intakeContinuousCurrentLimit;
        currentConfigs.SupplyCurrentThreshold = Constants.CurrentLimits.intakePeakCurrentLimit;
        motor.setNeutralMode(NeutralModeValue.Coast);

        configurator.apply(currentConfigs);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
 
    /** Determines the speed of the Intake motors */
    public enum IntakeState {
        ON(0.6),
        OFF(0),
        REV(-0.6);

        private double speed;

        IntakeState(double speed) {
            this.speed = speed;
        }
    }

    /* Static Instance Handling */
    private static Intake instance;

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }
}
