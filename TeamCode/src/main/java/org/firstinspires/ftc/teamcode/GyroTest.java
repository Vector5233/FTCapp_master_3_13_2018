package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by CCA on 1/10/2018.
 */
@Autonomous(name="GyroTest")

public class GyroTest extends LinearOpMode {
    DcMotor frontLeft, liftMotor, frontRight, backLeft, backRight;
    Servo topLeftGrab, topRightGrab, bottomLeftGrab, bottomRightGrab, jewelKnocker, jewelRaiser;
    ModernRoboticsI2cGyro gyro;
    ColorSensor colorSensor;
    float red, green, blue;

    Drive drive;
    RaymondAutonomousOpMode ray;


    @Override
    public void runOpMode() {
        initialization();
        waitForStart();
        sleep(100);
        //drive.TurnToAngle(90);
        drive.StopDriving();

}

    public void initialization () {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get ("liftMotor");
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        drive = new Drive(frontLeft,frontRight,backLeft,backRight, liftMotor, gyro, topLeftGrab, topRightGrab, bottomLeftGrab, bottomRightGrab, this);
        ray = new RaymondAutonomousOpMode(drive, jewelKnocker, jewelRaiser, colorSensor, this);

        gyro.calibrate();

        while (gyro.isCalibrating() && opModeIsActive()){
            idle();
        }
    }
}


