package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoSetShooterAngle extends Command {
	private double angle=0;
    public autoSetShooterAngle(double a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pivot);
    	angle=a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pivot.setAngle(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
