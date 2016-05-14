package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VerticalAim extends Command {
	private double kP = 1.0/(240/4);
	private double kI = .0001;///.00001
	private double error;
	double[] x={0.0};
	int total=0;
	double correctionUp=0;
	double timeSinceVisible;
	double timeSinceInRange;
	double targetY=0;
	double maxWidth=0;
	int i=0;
	int index=0;
	final double idealY=123;

    public VerticalAim() {
    	requires(Robot.pivot);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Done Y", "No");

    	timeSinceVisible=Timer.getFPGATimestamp();
    	timeSinceInRange=Timer.getFPGATimestamp();

    	total=0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	maxWidth=0;
    	i=0;
    	index=0;
    	if(Robot.table.getNumberArray("centerY",x).length>0){
	    	for(double d: Robot.table.getNumberArray("width",x) ){
	    		if(d>maxWidth){
	    			maxWidth=d;
	    			index=i;
	    		}
	    		i++;
	    	}
	    	
	    	timeSinceVisible=Timer.getFPGATimestamp();
	        targetY = Robot.table.getNumberArray("centerY", x)[index];
	        error = targetY-idealY;
	        if(!(Math.abs(error)<6))
	        	timeSinceInRange=Timer.getFPGATimestamp();

	        if((total>0)!=(error>0))
	        	total=0;
	        total+=error;
	        SmartDashboard.putNumber("errorY",error);
	        SmartDashboard.putNumber("calcY",error * kP);
	        SmartDashboard.putNumber("cY", Robot.table.getNumberArray("centerY", x)[index]);
	        SmartDashboard.putNumber("kI Y", total*kI);
	        correctionUp=-error * kP+(-total*kI);
	        if(Math.abs(correctionUp)>.5){
	        	correctionUp=Math.signum(correctionUp)*.5;
	        }
	        Robot.pivot.movePivot(correctionUp);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {//Timer.getFPGATimestamp()-timeSinceInRange>.15 ||
        return  Timer.getFPGATimestamp()-timeSinceInRange>.25 ||Timer.getFPGATimestamp()-timeSinceVisible>.2;//Math.abs(error)<3
    }

    // Called once after isFinished returns true
    protected void end() {
    	 SmartDashboard.putString("Done Y", "Yes");
         Robot.pivot.movePivot(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
