package org.example;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.OutputStream;

public class SerialExampleJava {
    public static void main(String[] args) {
        String message = "Hola desde Java, para la entrevista de CSTI";

        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM5");
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Puerto en uso");
            } else {
                CommPort commPort = portIdentifier.open(SerialExampleJava.class.getName(), 2000);

                if (commPort instanceof SerialPort) {
                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                    OutputStream outputStream = serialPort.getOutputStream();
                    outputStream.write(message.getBytes());

                    System.out.println("Mensaje enviado correctamente");

                    outputStream.close();
                    serialPort.close();
                } else {
                    System.out.println("Error: Solo se pueden utilizar puertos seriales");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

