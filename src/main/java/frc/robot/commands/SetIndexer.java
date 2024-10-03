package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;

public class SetIndexer extends Command {
    private final Indexer m_Indexer;
    private double percentPower;
    
    private Indexer.IndexerStates state;

    public SetIndexer(Indexer.IndexerStates state) {
        m_Indexer = Indexer.getInstance();
        
        this.state = state;
        
        addRequirements(m_Indexer);
    }

    @Override
    public void initialize() {
        m_Indexer.setState(state);
    }

    @Override
    public void end(boolean interrupted) {
        m_Indexer.setState(Indexer.IndexerStates.OFF);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
