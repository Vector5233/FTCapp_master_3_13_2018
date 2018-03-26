
package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@Autonomous(name="BlueLeft", group ="Concept")

public class BlueLeft extends LinearOpMode {
    public static final String TAG = "Vuforia VuMark Sample";

    DcMotor frontLeft, liftMotor, frontRight, backLeft, backRight;
    Servo topLeftGrab, topRightGrab, bottomLeftGrab, bottomRightGrab, jewelKnocker, jewelRaiser;
    ModernRoboticsI2cGyro gyro;
    ColorSensor colorSensor;
    float red, green, blue;

    Drive drive;
    RaymondAutonomousOpMode ray;

    float Lt, Rt;

    final double RIGHTGrab_COMPLETEOPEN = 0.8;
    final double RIGHTGrab_CLOSE = 0.33;
    final double LEFTGrab_COMPLETEOPEN = 0.2;
    final double LEFTGrab_CLOSE = 0.67;
    final double RIGHTGrab_OPEN = 0.5;
    final double LEFTGrab_OPEN = 0.5;
    final double RIGHTBottom_CLOSE = 0.3;
    final double LEFTBottom_CLOSE = 0.7;

    final double SPROCKET_RATIO = 2.0/3.0;
    final double TICKS_PER_INCH = SPROCKET_RATIO*(1120.0/(2*2*3.14159));

    // Right, left, and center are facing the back of the bot
    final double JEWEL_UP = 0.94;
    final double JEWEL_DOWN = 0.38;
    final double JEWEL_RIGHT = 0.25;
    final double JEWEL_CENTER = 0.15;
    final double JEWEL_LEFT = 0.05;
    final double JEWEL_RETRY = 0.12;

    final double DRIVE_FORWARD1 = 25;
    final double DRIVE_FORWARD2 = 23; //was 22
    final double STRAFE_LEFT_LEFT = 20; //Was 31
    final double STRAFE_LEFT_RIGHT = 2; //Was 11
    final double STRAFE_LEFT_CENTER = 11; //Was 20

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    double start;

    @Override public void runOpMode() {

        initialization();
        waitForStart();
        RelicRecoveryVuMark vuMark = ReadPictograph();
        sleep(1000);
        topRightGrab.setPosition(RIGHTGrab_CLOSE);
        topLeftGrab.setPosition(LEFTGrab_CLOSE);
        bottomRightGrab.setPosition(LEFTBottom_CLOSE);
        bottomLeftGrab.setPosition(RIGHTBottom_CLOSE);
        sleep(500);
        liftMotor.setPower(1.0);
        sleep(250);
        liftMotor.setPower(0.05);
        ray.BlueKnocker();
        start = gyro.getIntegratedZValue();
        drive. DriveForwardDistance(0.5,DRIVE_FORWARD1);
        sleep(500);
        drive. NewTurnDegrees(0.5,90,start);
        sleep(500);
        drive. DriveForwardDistance(0.5,DRIVE_FORWARD2);
        sleep(500);

        switch (vuMark) {
            case LEFT: {
                // Case Left is Case Right in red right
                drive.StrafeLeftDistance(0.3, STRAFE_LEFT_LEFT);
                sleep(500);
                //drive. DeliverGlyph();
                break;
            }
            case RIGHT: {
                // Case Right is Case Left in red right
                drive.StrafeLeftDistance(0.3, STRAFE_LEFT_RIGHT);
                sleep(500);
                //drive. DeliverGlyph();
                break;
            }
            case CENTER: {
                drive.StrafeLeftDistance(0.3, STRAFE_LEFT_CENTER);
                sleep(500);
                //drive. DeliverGlyph();
                break;
            }
            default: {
                drive.StrafeLeftDistance(0.3, STRAFE_LEFT_CENTER);
                sleep(500);
                //drive. DeliverGlyph();
                break;
            }

        }
        drive.DeliverBlue();
    }

    public void initialization () {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get ("liftMotor");

        colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        jewelRaiser = hardwareMap.servo.get("raise");
        jewelRaiser.setPosition(JEWEL_UP);
        jewelKnocker.setPosition(JEWEL_CENTER);
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");

        topRightGrab = hardwareMap.servo.get("topRightGrab");
        topLeftGrab = hardwareMap.servo.get("topLeftGrab");
        bottomLeftGrab = hardwareMap.servo.get("bottomLeftGrab");
        bottomRightGrab = hardwareMap.servo.get("bottomRightGrab");
        topRightGrab.setPosition(RIGHTGrab_COMPLETEOPEN);
        topLeftGrab.setPosition(LEFTGrab_COMPLETEOPEN);
        bottomRightGrab.setPosition(LEFTGrab_COMPLETEOPEN);
        bottomLeftGrab.setPosition(RIGHTGrab_COMPLETEOPEN);

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

}
