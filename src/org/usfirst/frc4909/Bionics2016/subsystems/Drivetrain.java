// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4909.Bionics2016.subsystems;

import org.usfirst.frc4909.Bionics2016.Robot;
import org.usfirst.frc4909.Bionics2016.RobotMap;
import org.usfirst.frc4909.Bionics2016.commands.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    public static final double STRAIGHT_THRESH = .05;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController frontLeft = RobotMap.drivetrainfrontLeft;
    private final SpeedController backLeft = RobotMap.drivetrainbackLeft;
    private final SpeedController frontRight = RobotMap.drivetrainfrontRight;
    private final SpeedController backRight = RobotMap.drivetrainbackRight;
    private final RobotDrive driveControl = RobotMap.drivetraindriveControl;
    private final Encoder leftDriveEncoder = RobotMap.drivetrainleftDriveEncoder;
    private final Encoder rightDriveEncoder = RobotMap.drivetrainrightDriveEncoder;
    private final ADXRS450_Gyro gyro = RobotMap.drivetraingyro;
	private final BuiltInAccelerometer A = new BuiltInAccelerometer();
	private final AnalogInput leftUltra = RobotMap.leftUltra;
	private final AnalogInput rightUltra = RobotMap.rightUltra;
	boolean straightMode = false;
	double straightAngle = 0;
	public Timer roboTimer;
	final double K = 0.01;
	final double conv = ((2.0/(4.9/1000))/2.54)/(4.75/5);
	
	
	private double WHEEL_DIAMETER = 8;
	private double ENCODER_RES = 2048;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    
    public void moveRobot(double left, double right)
    {
		/*double pow = (left + right)/2;
		
    	if(Math.abs(left-right) < STRAIGHT_THRESH && !straightMode)
    	{
    		straightAngle = gyro.getAngle();
    		straightMode = true;
        	driveControl.tankDrive(pow, pow);
    	}
    	
    	if(straightMode)
    	{
    		if(Math.abs(left-right) < STRAIGHT_THRESH)
    		{
    			double error = gyro.getAngle() - straightAngle;
			
    			double rotCorrection = -K * error;
    			driveControl.tankDrive(pow, pow-rotCorrection);
    		}
    	}*/
    	//driveControl.arcadeDrive(left,Robot.oi.controlDrive.getX()*.75);
	
    	driveControl.tankDrive(left, right);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public double getDistTraveledLeft()
    {
    	double dist=0;
    	double revs=leftDriveEncoder.getRaw()/(ENCODER_RES*4);
    	dist=revs*WHEEL_DIAMETER *Math.PI;
    	
    	return dist;
    }
    
    public double getDistTraveledRight()
    {
    	double dist=0;
    	double revs=rightDriveEncoder.getRaw()/(ENCODER_RES*4);
    	dist=revs*WHEEL_DIAMETER *Math.PI;
    	
    	return dist;
    }
    
    public void resetEncoders()
    {
    	rightDriveEncoder.reset();
    	leftDriveEncoder.reset();
    }
    
    public void autoDrive(double magnitude, double curve)
    {
    	driveControl.drive(magnitude, curve);
    	Robot.pivot.pivotUp();
    }
    
    public void resetGyro()
    {
    	gyro.reset();
    }
    
    public double getGyroAngle()
    {
    	return gyro.getAngle();
    }
    
    public double getAccelX()
    {
    	return A.getX();
    }
    
    public double getAccelY()
    {
    	return A.getY();
    }
    
    public double getAccelZ()
    {
    	return A.getZ();
    }
    
    public boolean accelFlat()
    {
    	return getAccelZ() > 0.9 && getAccelZ() < 1.1;
    	
    }
    
    public double getLeftDistanceFromTarget(){
    	return leftUltra.getVoltage()*conv;
    }
    public double getRightDistanceFromTarget(){
    	return rightUltra.getVoltage()*conv;
    }
    
    public void turnRobotLeft()
    {
    	frontLeft.set(0.5);
    	backLeft.set(0.5);
    	
    	frontRight.set(-0.5);
    	backRight.set(-0.5);
    }
    
    public void turnRobotRight()
    {
    	frontLeft.set(-0.5);
    	backLeft.set(-0.5);
    	
    	frontRight.set(0.5);
    	backRight.set(0.5);    	
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive()); //this was added
    }
}

