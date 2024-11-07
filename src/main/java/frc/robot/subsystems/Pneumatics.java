package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import org.littletonrobotics.junction.AutoLogOutput;

public class Pneumatics extends SubsystemBase { 
    //note: might be possible to get rid of compressor object, should activate automatically when we use the solenoid
    private static Pneumatics instance;
    private Compressor compressor;
    private Solenoid leftSolenoid;
    private Solenoid rigthSolenoid;

    public static Pneumatics getInstance() {
        if (instance == null) {
            instance = new Pneumatics();
        }
        return instance;
    }

    public Pneumatics() {
        compressor = new Compressor(Constants.PneumaticIDs.pneumaticsHub, PneumaticsModuleType.REVPH);
        leftSolenoid = new Solenoid(Constants.PneumaticIDs.pneumaticsHub, PneumaticsModuleType.REVPH, Constants.PneumaticIDs.solenoid);
        rigthSolenoid = new Solenoid(Constants.PneumaticIDs.pneumaticsHub, PneumaticsModuleType.REVPH, Constants.PneumaticIDs.solenoid);
        
    }
    
    public void setSolenoid(boolean state) {
        leftSolenoid.set(state);
        rigthSolenoid.set(state);
    }

    public void enableCompressor(){
        compressor.enableDigital();
    }

    public void disableCompressor(){
        compressor.disable();
    }

    /**
     * Checks if both solenoids are not disabled listed. If a solenoid shorts it is disabled.
     * @return True if both solenoids are not disabled. False if either or both are disabled.
     */
    public boolean solenoidEnabled(){
        return !leftSolenoid.isDisabled() && !rigthSolenoid.isDisabled();
    }

    @AutoLogOutput(key = "Pneumatics/Compressor/Enabled")
    public boolean compressorEnabled() {
        return compressor.isEnabled();
    }


    /**
     * State of the compressor pressure switch.
     * @return False if system is full. True if NOT full.
     */
    @AutoLogOutput(key = "Pneumatics/Compressor/SwitchState")
    public boolean getCompressorSwitchState(){
        return compressor.getPressureSwitchValue();
    }
    @AutoLogOutput(key = "Pneumatics/Compressor/CurrentDraw")
    public double getCompressorCurrent(){
        return compressor.getCurrent();
    }
    @AutoLogOutput(key = "Pneumatics/Compressor/PressurePSI")
    public double getPressure(){
        return compressor.getPressure(); 
    }

    @Override
    public void periodic() {
        if(getCompressorSwitchState()){
            System.out.println("Enabling Compressor");
            enableCompressor();
        } else{
            System.out.println("Disabling Compressor");
            disableCompressor();
        }

        SmartDashboard.putNumber("Available Pressure", getPressure());
        SmartDashboard.putBoolean("Compressor Full", !getCompressorSwitchState());
        SmartDashboard.putBoolean("Compressor Enabled", compressorEnabled());
    }

}

