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


public class Indexer extends SubsystemBase {
    private final TalonFX m_Indexer;

    private IndexerStates currentState = IndexerStates.OFF;
    
    /**
     * Creates a new Indexer.
     */
    public Indexer() {
        m_Indexer = new TalonFX(Constants.HardwarePorts.indexerM); //TODO set the correct ID

        m_Indexer.setInverted(false); // no idea
        configMotor(m_Indexer);
    }

    private void configMotor(TalonFX motor) {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.withKP(0.4);

        motor.getConfigurator().apply(config);
        motor.getConfigurator().apply(slot0Configs);
    }

    public enum IndexerStates {
        INTAKE(0.75),
        INDEX(0.7),
        OFF(0),
        REV(-0.5);

        final private double speed;

        public double getValue() {
            return speed;
        }

        IndexerStates(double speed) {
            this.speed = speed;
        }
    }


    public IndexerStates getCurrentState() {
        return currentState;
    }

    public double getSpeed() {
        return m_Indexer.get();
    }

    public double getVoltage() {
        return m_Indexer.getMotorVoltage().getValueAsDouble();
    }

    public void setCurrentState(IndexerStates state) {
        currentState = state;
        m_Indexer.set(state.speed);
    }

    public void setSpeed(double speed) {
        m_Indexer.set(speed);
    }

    public void setVoltage(double voltage) {
        m_Indexer.setVoltage(voltage);
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
