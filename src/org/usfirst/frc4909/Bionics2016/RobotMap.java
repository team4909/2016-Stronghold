// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4909.Bionics2016;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController drivetrainfrontLeft;
    public static SpeedController drivetrainbackLeft;
    public static SpeedController drivetrainfrontRight;
    public static SpeedController drivetrainbackRight;
    public static RobotDrive drivetraindriveControl;
    public static Encoder drivetrainleftDriveEncoder;
    public static Encoder drivetrainrightDriveEncoder;
    public static ADXRS450_Gyro drivetraingyro;
    public static ADXL362 drivetrainaccelerometer;
    public static SpeedController shootershooterLeftWheel;
    public static SpeedController shootershooterRightWheel;
    public static Encoder shooterleftShootEncoder;
    public static Encoder shooterrightShootEncoder;
    public static SpeedController feederfeedAxle;
    public static DigitalInput feederfeedSwitch;
    public static SpeedController pivotpivotControl;
    public static Encoder pivotpivotEncoder;
    public static SpeedController climberclimbMotor;
    public static Encoder climberclimbEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DigitalInput pivotTopSwitch;
    public static DigitalInput pivotBottomSwitch;

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    	
    	/*Current Port Configuration (if this is changed, update below!):
    	 * DIO:
    	 * 0-
    	 * 1-Pivot Top Limit Switch (pivotTopSwitch)
    	 * 2-Pivot Bottom Limit Switch (pivotBottomSwitch)
    	 * 3+4- Pivot Encoder (pivotpivotEncoder)
    	 * 5+6- Right Side Drivetrain Encoder (drivetrainrightDriveEncoder)
    	 * 7+8- Left Shooter Wheel Encoder (shooterleftShootEncoder)
    	 * 9+10- Right Shooter Wheel Encoder (shooterrightShootEncoder)
    	 * 11+12- Left Side Drivetrain Encoder (drivetrainleftDriveEncoder)
    	 * 13-Feeder Limit Switch (boulder is in the robot) (feederfeedSwitch)
    	 * 14+15- Climber Encoder (climberclimbEncoder)
    	 * 
    	 * PWM:
    	 * 0-Front Left Drivetrain Motor (drivetrainfrontLeft)
    	 * 1-Back Left Drivetrain Motor (drivetrainbackLeft)
    	 * 2-Front Right Drivetrain Motor (drivetrainfrontRight)
    	 * 3-Back Right Drivetrain Motor (drivetrainbackRight)
    	 * 4-
    	 * 5-Feeder Axle Motor (feederfeedAxle)
    	 * 6-Left Shooter Wheel Motor (shootershooterLeftWheel)
    	 * 7-Climber Motor (climberclimbMotor)
    	 * 8-Right Shooter Wheel Motor (shootershooterRightWheel)
    	 * 9-Pivot Motor (pivotpivotControl)

    	 * 
    	 * Serial Peripheral Interface:
    	 * Drivetrain Gyro
    	 * Drivetrain Accelerometer
    	 */
    	
    	//Drivetrain
    	//Drivetrain Motors
        drivetrainfrontLeft = new VictorSP(0);
        drivetrainfrontLeft.setInverted(true);
        LiveWindow.addActuator("Drivetrain", "frontLeft", (VictorSP) drivetrainfrontLeft);
        
        drivetrainbackLeft = new VictorSP(1);
        drivetrainbackLeft.setInverted(true);
        LiveWindow.addActuator("Drivetrain", "backLeft", (VictorSP) drivetrainbackLeft);
        
        drivetrainfrontRight = new VictorSP(2);
        drivetrainfrontRight.setInverted(true);
        LiveWindow.addActuator("Drivetrain", "frontRight", (VictorSP) drivetrainfrontRight);
        
        drivetrainbackRight = new VictorSP(3);
        drivetrainbackRight.setInverted(true);
        LiveWindow.addActuator("Drivetrain", "backRight", (VictorSP) drivetrainbackRight);
        
        //Drivetrain Drive
        drivetraindriveControl = new RobotDrive(drivetrainfrontLeft, drivetrainbackLeft,
              drivetrainfrontRight, drivetrainbackRight);
        
        drivetraindriveControl.setSafetyEnabled(true);
        drivetraindriveControl.setExpiration(0.1);
        drivetraindriveControl.setSensitivity(0.5);
        drivetraindriveControl.setMaxOutput(1.0);

        //Drivetrain Sensors
        drivetrainleftDriveEncoder = new Encoder(11, 12, false, EncodingType.k4X);
        LiveWindow.addSensor("Drivetrain", "leftDriveEncoder", drivetrainleftDriveEncoder);
        drivetrainleftDriveEncoder.setDistancePerPulse(1.0);
        drivetrainleftDriveEncoder.setPIDSourceType(PIDSourceType.kRate);
        
        drivetrainrightDriveEncoder = new Encoder(5, 6, false, EncodingType.k4X);
        LiveWindow.addSensor("Drivetrain", "rightDriveEncoder", drivetrainrightDriveEncoder);
        drivetrainrightDriveEncoder.setDistancePerPulse(1.0);
        drivetrainrightDriveEncoder.setPIDSourceType(PIDSourceType.kRate);
        
        drivetraingyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
        LiveWindow.addSensor("Drivetrain", "gyro", drivetraingyro);
        //drivetraingyro.setSensitivity(0.007);
        
        drivetrainaccelerometer = new ADXL362(Range.k8G); //add port number
        LiveWindow.addSensor("Drivetrain", "accelerometer", drivetrainaccelerometer);
        
        //Shooter
        //Shooter Motors
        shootershooterLeftWheel = new Spark(6);
        LiveWindow.addActuator("Shooter", "shooterLeftWheel", (Spark) shootershooterLeftWheel);
        
        shootershooterRightWheel = new Spark(8);
        LiveWindow.addActuator("Shooter", "shooterRightWheel", (Spark) shootershooterRightWheel);
        
        //Shooter Sensors
        shooterleftShootEncoder = new Encoder(7, 8, false, EncodingType.k4X);
        LiveWindow.addSensor("Shooter", "leftShootEncoder", shooterleftShootEncoder);
        shooterleftShootEncoder.setDistancePerPulse(1/8192);
        shooterleftShootEncoder.setPIDSourceType(PIDSourceType.kRate);
        
        shooterrightShootEncoder = new Encoder(9, 10, true, EncodingType.k4X);
        LiveWindow.addSensor("Shooter", "rightShootEncoder", shooterrightShootEncoder);
        shooterrightShootEncoder.setDistancePerPulse(1/8192);
        shooterrightShootEncoder.setPIDSourceType(PIDSourceType.kRate);
        
        
        //Feeder
        //Feeder Motor
        feederfeedAxle = new Spark(5);
        LiveWindow.addActuator("Feeder", "feedAxle", (Spark) feederfeedAxle);
        
        //Feeder Sensor
        feederfeedSwitch = new DigitalInput(13);
        LiveWindow.addSensor("Feeder", "feedSwitch", feederfeedSwitch);
        
        
        //Pivot
        //Pivot Motor
        pivotpivotControl = new Spark(9);
        LiveWindow.addActuator("Pivot", "pivotControl", (Spark) pivotpivotControl);
        
        //Pivot Sensors
        pivotTopSwitch= new DigitalInput(1);
        LiveWindow.addSensor("Feeder", "feedSwitch", feederfeedSwitch);
        
        pivotBottomSwitch = new DigitalInput(2);
        LiveWindow.addSensor("Feeder", "feedSwitch", feederfeedSwitch);
        
        pivotpivotEncoder = new Encoder(3, 4, true, EncodingType.k4X);
        LiveWindow.addSensor("Pivot", "pivotEncoder", pivotpivotEncoder);
        pivotpivotEncoder.setDistancePerPulse(360.0/8192);
        pivotpivotEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        
        
        //Climber
        //Climber Motor
        climberclimbMotor = new Spark(7);
        LiveWindow.addActuator("Climber", "climbMotor", (Spark) climberclimbMotor);
        
        //Climber Sensors
        climberclimbEncoder = new Encoder(14, 15, false, EncodingType.k4X);
        LiveWindow.addSensor("Climber", "climbEncoder", climberclimbEncoder);
        climberclimbEncoder.setDistancePerPulse(1.0);
        climberclimbEncoder.setPIDSourceType(PIDSourceType.kRate);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
