
package org.firstinspires.ftc.teamcode.Testes.teste_gamepad;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name="gamepad3", group="TeleOp")
//@Disabled

public class gamepad3 extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motor_esquerdo = null;
    private DcMotor motor_direito = null;
    private CRServo servoD = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motor_esquerdo = hardwareMap.get(DcMotor.class, "motor_esquerdo");
        motor_direito = hardwareMap.get(DcMotor.class, "motor_direito");
        servoD = hardwareMap.crservo.get("servoD");

        motor_esquerdo.setDirection(DcMotor.Direction.FORWARD);
        motor_direito.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            if (gamepad1.right_bumper) {
                girar_servo(1);
            }
        }
    }
    public void girar_servo ( double f){
        servoD.setPower(f);
    }
}



