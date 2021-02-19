package org.firstinspires.ftc.teamcode.Testes.testeEncoder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="EncoderTest", group="Linear Opmode")
//@Disabled

public class encoderTest extends LinearOpMode {
    private ElapsedTime relogio = new ElapsedTime();

    double COUNTS_PER_MOTOR_REV = 2240;
    double DRIVE_GEAR_REDUCTION = 1;
    double rodaDiametro_IN = 3.54331;
    double voltas_IN = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (rodaDiametro_IN * 3.1415);

    DcMotor leftFront;
    DcMotor rightFront;

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.dcMotor.get("motor_esquerdo");
        rightFront = hardwareMap.dcMotor.get("motor_direito");

        rightFront.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        //Método Encoder em centímetros
        encoder(1, 120, 120, 10);
        
    }
    //Criando o método e seus parâmetros.
    public void encoder(double veloc, double leftCM, double rightCM, double timeoutS){

        //Variáveis do Target
        int rightPos;
        int leftPos;

        //Resetando o Encoder dos motores para evitar dados acumulados.
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Iniciando o encoder.
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Transformando quantidade em centímetros para polegadas.
        rightPos = (int) (rightCM / 2.54  * voltas_IN);
        leftPos = (int) (leftCM / 2.54 * voltas_IN);

        //Definido o Target com o valor obtido na conversão CM para Inch.
        leftFront.setTargetPosition(leftPos);
        rightFront.setTargetPosition(rightPos);

        //Iniciando o alvo.
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        relogio.reset();
        //Os motores se movem enquanto estiverem ocupados (Até encontrar o alvo)
        while (opModeIsActive() && (relogio.seconds() < timeoutS) &&
                (leftFront.isBusy() && rightFront.isBusy())) {
            //Potência em que irá se mover para achar o alvo.
            leftFront.setPower(Math.abs(veloc));
            rightFront.setPower(Math.abs(veloc));
        }
        //Após chegar a posição desejada o loop se encerra e o movimento para.
        leftFront.setPower(0);
        rightFront.setPower(0);

        //Encerrando o código.
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}