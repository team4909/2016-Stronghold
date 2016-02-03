package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class PivotUp extends Command {
	
	public PivotUp() {
		requires(Robot.pivot);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.pivot.movePivot(-0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.pivot.movePivot(0.0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
