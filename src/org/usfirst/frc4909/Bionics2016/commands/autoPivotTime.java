package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoPivotTime extends Command {
	private int direction;
	double startTime;
	private double time;
    public autoPivotTime(double t, int dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	time=t;
    	direction=dir;
    	requires(Robot.pivot);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.pivot.movePivot(direction*.7);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp()-startTime>time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pivot.movePivot(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
