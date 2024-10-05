
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeState;

public class SetIntake extends Command {
    private final Intake m_Intake;
    
    Intake.IntakeState state;

    public SetIntake(Intake.IntakeState state) {
        m_Intake = Intake.getInstance();
        this.state = state; 

        addRequirements(m_Intake);
    }

    @Override
    public void initialize() {
        m_Intake.setState(state);
    }

    @Override
    public void end(boolean interrupted) {
        m_Intake.setState(IntakeState.OFF);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}