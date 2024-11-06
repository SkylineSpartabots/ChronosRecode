package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Pneumatics extends SubsystemBase { 
    private static Pneumatics instance;
    private Compressor compressor;

    public static Pneumatics getInstance() {
        if (instance == null) {
            instance = new Pneumatics();
        }
        return instance;
    }

    public Pneumatics() {
        compressor = new Compressor(Constants.HardwarePorts.pneumaticsHub, PneumaticsModuleType.REVPH);
        compressor.enableDigital();
    }

}

