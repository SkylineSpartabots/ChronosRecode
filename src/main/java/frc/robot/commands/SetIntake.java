
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class SetIntake extends Command {
    private final Intake s_Intake;
    
    Intake.IntakeState state;

    public SetIntake(Intake.IntakeState state) {
        s_Intake = Intake.getInstance();

        addRequirements(s_Intake);
    }

    @Override
    public void initialize() {
        s_Intake.setState(state);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}