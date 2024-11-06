package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;

public class SetSolenoid extends Command {
    private final Pneumatics s_Pneumatics;
    private boolean state;
    
    
    public SetSolenoid(boolean state) {
        s_Pneumatics = Pneumatics.getInstance();
        this.state = state;
        addRequirements(s_Pneumatics);
        
    }
    
    @Override
    public void initialize() {
        s_Pneumatics.setSolenoid(state);
    }
    @Override
    public void end(boolean interrupted) {
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
