package org.usfirst.frc4909.Bionics2016.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VerticalAim extends Command {
	private double kP = 1.0/640;
	private double error=0;
    public VerticalAim() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(error)<10;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
