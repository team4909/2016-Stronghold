package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class autoTurnRobot extends Command {
	private double angle = 0;
	private boolean turn_left = false;
	private int dir=1;
	private double kP=.008;
	private double kI=.0001;
	private double total = 0;
	private double error;
	private double time;
    public autoTurnRobot(double a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	angle=a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	time=Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error=Robot.drivetrain.getGyroAngle()-angle;
    	total+=error;
    	if(Math.signum(total)!=Math.signum(error))
    		total=0;
    	Robot.drivetrain.turnRobot(error*kP + total*kI);
    	SmartDashboard.putNumber("Gyro Angle", Robot.drivetrain.getGyroAngle());
    	SmartDashboard.putNumber("Gyro P", error*kP);

    	if(!(Math.abs(error)<10)){
        	time=Timer.getFPGATimestamp();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Math.abs(Robot.drivetrain.getGyroAngle())>=angle;
    	return Timer.getFPGATimestamp()-time>.2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.moveRobot(0,0);
    	double time2=Timer.getFPGATimestamp();
    	while(Timer.getFPGATimestamp()-time2<.3){}

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
