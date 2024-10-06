package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class SetShooter extends Command {
    private final Shooter m_Shooter;

    private double velocity;

    public SetShooter(double velocity) {
        m_Shooter = Shooter.getInstance();

        this.velocity = velocity;
        addRequirements(m_Shooter);
    }

    @Override
    public void initialize() {
        m_Shooter.setVelocity(velocity);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
