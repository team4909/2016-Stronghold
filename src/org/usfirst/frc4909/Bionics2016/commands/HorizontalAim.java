package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HorizontalAim extends Command {
	private double kP = 1.0/640;
	private double error;
    public HorizontalAim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double targetX = Robot.table.getNumber("centerX", 0);
        error = targetX-320;
        Robot.drivetrain.moveRobot(-error * kP+.1*-Math.signum(error), error * kP+.1*Math.signum(error));
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
