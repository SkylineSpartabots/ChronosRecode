package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Pneumatics extends SubsystemBase { 
    private static Pneumatics instance;
    private Compressor compressor;
    private Solenoid solenoid;

    public static Pneumatics getInstance() {
        if (instance == null) {
            instance = new Pneumatics();
        }
        return instance;
    }

    public Pneumatics() {
        compressor = new Compressor(Constants.PneumaticIDs.pneumaticsHub, PneumaticsModuleType.REVPH);
        solenoid = new Solenoid(Constants.PneumaticIDs.pneumaticsHub, PneumaticsModuleType.REVPH, Constants.PneumaticIDs.solenoid);

        compressor.enableDigital();
        
    }
    
    public void setSolenoid(boolean state) {
        solenoid.set(state);
    }

}

