package org.firstinspires.ftc.teamcode;/*package org.firstinspires.ftc.teamcode;

/* ToDO 2018.01.16
 * Hardware map jewelRaiser
 * jewelRaiser in Config file
 *
 */
/**
 * Created by CCA on 10/27/2017.
 */

/*import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by CCA on 10/26/2017.
 */

/*@Disabled
@Autonomous(name = "RevAuto")

public class RevAuto extends LinearOpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight, liftMotor;
    ColorSensor colorSensor;
    float red, green, blue;
    Servo leftGrab, rightGrab, jewelKnocker, jewelRaiser;
    ModernRoboticsI2cGyro gyro;
    Drive drive;
    RaymondAutonomousOpMode ray;

    final double SPROCKET_RATIO = 2.0/3.0;
    final double TICKS_PER_INCH = SPROCKET_RATIO*(1120.0/(2*2*3.14159));

    float Lt, Rt;
    final double RIGHTGrab_OPEN = 0.8;
    final double RIGHTGrab_CLOSE = 0.4; //used to be 0.46
    final double LEFTGrab_OPEN = 0.2;
    final double LEFTGrab_CLOSE = 0.6; //used to be 0.54

    final double JEWEL_UP = 0;
    final double JEWEL_DOWN = 0+0.091;

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() throws InterruptedException {

        initialization();
        waitForStart();
        /*drive.StrafeLeftDistance(0.5,12);
        sleep(5000);
        drive.StrafeRightDistance(0.5,12);
        sleep(5000);
        drive.StopDriving(); //redundant
        drive.DriveForwardDistance(0.5,12);
        sleep(5000);
        drive.DriveBackwardDistance(0.5,12);
        sleep(5000);
        drive.TurnLeftDegree(0.5,90);
        sleep(5000);
        drive.TurnRightDegree(0.5,90);*/
        //drive.DeliverGlyph();
    //}
/*Time magic number: 1000 = 1 second*/


/*    public void initialization () {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get ("liftMotor");


        colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        gyro=hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        //jewelKnocker.setPosition(jewelKnocker_Raised);
        RaiseJewelKnocker();

        rightGrab = hardwareMap.servo.get("rightGrab");
        leftGrab = hardwareMap.servo.get("leftGrab");
        rightGrab.setPosition(RIGHTGrab_OPEN);
        leftGrab.setPosition(LEFTGrab_OPEN);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gyro.calibrate();
        while (opModeIsActive() && gyro.isCalibrating()) {
            idle();
        }

        drive = new Drive(frontLeft,frontRight,backLeft,backRight, liftMotor, gyro, leftGrab, rightGrab, this);
        ray = new RaymondAutonomousOpMode (drive, jewelKnocker, jewelRaiser, colorSensor, this);


    }

    public void LowerJewelKnocker(){
        jewelKnocker.setPosition(JEWEL_DOWN);
    }

    public void RaiseJewelKnocker(){
        jewelKnocker.setPosition(JEWEL_UP);

    }
    public RelicRecoveryVuMark ReadPictograph(){
        RelicRecoveryVuMark picto;

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "ASC4bMD/////AAAAGQo793RFLUVDpV1hb5ufNBh/AXAtpjorAyvu24vcZ2AdlmilEYdD61K3xjN4XxdZmMc6NVCEdYQsF1bQxSyFUeUQ/ZzBvYYZnq4JTuLnGXGm1zhjNgRwNE0hWWY0IhipNoz+2ZUjGzWOxGq4hBB8LsVvQnaQR0Z/09iQ9p9zQ9eOD85Com5dXlxef6whuD/BRXyZSBeibi/zel9RKT9VCcCIsn7i0h62cApztPMq6NzBDFibiNsWDVoE83nw5utIPOGY4MsAyPHh27AhThKp83FAvlBE/RCDSrgUYRg2TOOFEu3uG7DVKjHrngLSRccN5eorXXVG7PdPoiHWTpSyVMaQSu/boDk6XgjgxwqGU/tB";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();
        sleep(1000);
        picto = RelicRecoveryVuMark.from(relicTemplate);



        switch (picto) {
            case LEFT: {
                telemetry.addData("VuMark", "is Left");
                break;
            }
            case RIGHT: {
                telemetry.addData("VuMark", "is Right");
                break;
            }
            case CENTER: {
                telemetry.addData("VuMark", "is Center");
                break;
            }
            case UNKNOWN: {
                telemetry.addData("VuMark", "not visible");
                break;
            }
        }
        telemetry.update();
        return picto;
    }
}*/


