package org.example;

import lombok.extern.log4j.Log4j2;

import javax.swing.*;


@Log4j2
public class Main {
            public static void main(String[] args) {
                System.out.println("Bienvenido al simulador de experimentos");
                int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion que desea realizar: \n 1. ingresar manualmente el numero de experimentos \n 2. generar un número de experimentos al azar \n 3. salir"));




                switch (opcion) {
                    case 1: {

                        int num = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de experimentos"));
                        int numExperimentos [] = new int [num];
                        int aciertostotales = 0;
                        int falladostotales = 0;


                        for (int j = 0; j < numExperimentos.length; j++) {
                            int aciertos = 0;
                            int fallados = 0;

                            for (int i = 0; i < 10 ; i++) {


                                int numerogenerado1 = (int) (Math.random() * 3);
                                int numerogenerado2 = (int) (Math.random() * 3);

                                if (numerogenerado1 == numerogenerado2) {
                                    aciertos++;
                                    aciertostotales++;

                                } else {
                                    fallados++;
                                    falladostotales ++;
                                }
                            }
                            double frecuenciaRelativa = (double)aciertos/10;
                            double c = 0.6666;
                            double probablidad_de_ninguno = Math.pow(c,10);
                            double probablidad_almenos_uno = 1 - probablidad_de_ninguno;

                            log.info((j+1) + " Frecuencia Relativa : " + frecuenciaRelativa +
                                    " Probabilidad de sacar 1 o más : " + probablidad_almenos_uno );


                        }
                        double c = 0.6666;
                        double probablidad_de_ninguno = Math.pow(c,10);
                        double probablidad_almenos_uno = 1 - probablidad_de_ninguno;

                        double frecuenciaRelativaTotal = (double)aciertostotales/(num*10);



                        JOptionPane.showMessageDialog(null,"Experimentos acertados, las 2 cartas son iguales : " + aciertostotales + "\n" + "Experimentos fallados, las 2 cartas son diferentes : " + falladostotales );
                        JOptionPane.showMessageDialog(null,"Frecuencia relativa Total de casos : " + frecuenciaRelativaTotal );




                        break;

                    }

                    case 2: {

                    int numeroSimulaciones = 30000;
                    int aciertos = 0;
                    int fallos = 0;

                    String[] cartas = {"Mago", "Oraculo", "Estrella"};

                    for (int simulacion = 1; simulacion <= numeroSimulaciones; simulacion++){

                        int aciertosEnLos10 = 0;

                        for (int intento = 0; intento < 10; intento++){

                            int cartaHermana1 = (int) (Math.random() * 3);
                            int cartaHermana2 = (int) (Math.random() * 3);

                            if (cartaHermana1 == cartaHermana2) {
                                aciertosEnLos10++;
                            }
                        }

                        if (aciertosEnLos10 > 0) {
                            aciertos++;
                        }else{
                            fallos++;
                        }
                    }

                    double frecuenciaRelativa = (double) aciertos / numeroSimulaciones;

                    JOptionPane.showMessageDialog(null, "\n======== MODO AUTOMATICO ========");
                    JOptionPane.showMessageDialog(null, "Numero de simulaciones: " + numeroSimulaciones);
                    JOptionPane.showMessageDialog(null, "Cada simulación tiene 10 shows");
                    JOptionPane.showMessageDialog(null, String.format("%-20s %-10d", "Veces en las que acierta al menos 1: ", aciertos));
                    JOptionPane.showMessageDialog(null, String.format("%-20s %-10d", "Veces en las que no acierta ninguna: ", fallos));
                    JOptionPane.showMessageDialog(null, String.format("Frecuencia relativa: %.4f", frecuenciaRelativa));
                    JOptionPane.showMessageDialog(null, String.format("Probabilidad experimental: %.2f%%", frecuenciaRelativa * 100));

                    break;

                    }

                    case 3: {
                        JOptionPane.showMessageDialog(null, "Gracias por usar el programa!");
                        break;
                    }

                    default:
                        JOptionPane.showMessageDialog(null, "Opcion no valida, vuelva a intentarlo");
                        break;

                }
            }
}