// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Indexer extends SubsystemBase {
    private final TalonFX m_leaderIndexer;
    private final TalonFX m_followerIndexer;

    private IndexerStates currentState = IndexerStates.OFF;
    
    private static Indexer instance;
    
    public static Indexer getInstance() {
        if (instance == null) {
            instance = new Indexer();
        }
        return instance;
    }
    
    /**
     * Creates a new Indexer.
     */
    public Indexer() {
        m_leaderIndexer = new TalonFX(Constants.HardwarePorts.leftIndexer); //TODO set the correct ID
        m_followerIndexer = new TalonFX(Constants.HardwarePorts.rightIndexer); //TODO set the correct ID

        m_followerIndexer.setControl(new Follower(m_leaderIndexer.getDeviceID(), false)); // TODO not sure which direction


        configMotor(m_leaderIndexer, false); // no idea
        configMotor(m_followerIndexer, false); // no idea
    }

    private void configMotor(TalonFX motor, boolean inverted) {
        motor.setInverted(inverted);
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.withKP(0.4);

        motor.getConfigurator().apply(config);
        motor.getConfigurator().apply(slot0Configs);
    }

    public enum IndexerStates {
        INDEX(0.7),
        OFF(0),
        REV(-0.4);

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
        return m_leaderIndexer.get();
    }

    public double getVoltage() {
        return m_leaderIndexer.getMotorVoltage().getValueAsDouble();
    }

    public void setState(IndexerStates state) {
        currentState = state;
        m_leaderIndexer.set(state.speed);
    }

    public void setSpeed(double speed) {
        m_leaderIndexer.set(speed);
    }

    public void setVoltage(double voltage) {
        m_leaderIndexer.setVoltage(voltage);
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
