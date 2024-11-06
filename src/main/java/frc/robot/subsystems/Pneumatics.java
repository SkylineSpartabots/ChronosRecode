package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Pneumatics extends SubsystemBase { 
    //note: might be possible to get rid of compressor object, should activate automatically when we use the solenoid
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
        
    }
    
    public void setSolenoid(boolean state) {
        solenoid.set(state);
    }

    public void enableCompressor(){
        compressor.enableDigital();
    }

    public void disableCompressor(){
        compressor.disable();
    }

    //solenoid gets disabled until next power cycle if shorted
    public boolean solenoidDisabled(){
        return solenoid.isDisabled();
    }

    public boolean compressorEnabled() {
        return compressor.isEnabled();
    }


    //according to documentation returns true if system NOT full, false if system full
    public boolean getCompressorSwitchState(){
        return compressor.getPressureSwitchValue();
    }

    public double getCompressorCurrent(){
        return compressor.getCurrent();
    }

    public double getPressure(){
        return compressor.getPressure();
    }

    @Override
    public void periodic() {
        if(getCompressorSwitchState()){
            enableCompressor();
        } else{
            disableCompressor();
        }

        SmartDashboard.putNumber("available pressure", getPressure());
    }

}

