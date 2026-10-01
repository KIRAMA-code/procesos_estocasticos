package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import javax.swing.*;




public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
            public static void main(String[] args) {
                System.out.println("Bienvenido al simulador de experimentos");
                int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion que desea realizar: \n 1. ingresar manualmente el numero de experimentos \n 2. generar un número de experimentos al azar \n 3. salir"));




                switch (opcion) {
                    case 1: {
                    String input = JOptionPane.showInputDialog(null, 
                "¿Cuántas iteraciones (bloques de 10 shows) deseas simular?", 
                "Configuración de Iteraciones", 
                JOptionPane.QUESTION_MESSAGE);
                
                if (input == null || input.trim().isEmpty()) {
                logger.warn("El usuario canceló la ejecución.");
                System.exit(0);
                }

                int totalIteraciones;
                try {
                totalIteraciones = Integer.parseInt(input);
                if (totalIteraciones <= 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, 
                        "Entrada inválida. Se simulará 1 iteración por defecto.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    totalIteraciones = 1;
                }

                String[] cartas = {"Mago", "Oráculo", "Estrella"};
                Random random = new Random();
        
                // Contadores para las iteraciones de 10 shows
                int iteracionesConAlMenosUnAcierto = 0;
                    int iteracionesConCeroAciertos = 0;

                    logger.info("=== INICIANDO SIMULACIÓN DE " + totalIteraciones + " ITERACIONES ===");

                    for (int i = 1; i <= totalIteraciones; i++) {
                    logger.info("--- Iniciando Iteración {} (Bloque de 10 Shows) ---", i);
                    int aciertosEnEstaIteracion = 0;

                        // Bucle interno: los 10 shows de ESTA iteración de forma aleatoria
                        for (int j = 1; j <= 10; j++) {
                            String cartaHermana1 = cartas[random.nextInt(3)];
                            String cartaHermana2 = cartas[random.nextInt(3)];

                            if (cartaHermana1.equals(cartaHermana2)) {
                            aciertosEnEstaIteracion++;
                            logger.info("  Show {}: H1 vio [{}] | H2 dibujó [{}] -> ¡ACIERTO!", j, cartaHermana1, cartaHermana2);
                            } else {
                            logger.info("  Show {}: H1 vio [{}] | H2 dibujó [{}] -> FALLO", j, cartaHermana1, cartaHermana2);
                            }
                            }

                            // Evaluación del bloque de 10 shows
                            if (aciertosEnEstaIteracion > 0) {
                            iteracionesConAlMenosUnAcierto++;
                            logger.info("-> Resultado Iteración {}: Acertaron al menos uno (Total aciertos: {})", i, aciertosEnEstaIteracion);
                            } else {
                            iteracionesConCeroAciertos++;
                            logger.info("-> Resultado Iteración {}: Fallaron TODOS los 10 shows.", i);
                        }
                    }

                    logger.info("=== SIMULACIÓN FINALIZADA ===");

                    // Cálculos Teóricos vs Empíricos
                    double probTeoricaFallarTodos = Math.pow(2.0 / 3.0, 10);
                    double probTeoricaAlMenosUno = 1.0 - probTeoricaFallarTodos;

                    double probEmpiricaFallarTodos = (double) iteracionesConCeroAciertos / totalIteraciones;
                    double probEmpiricaAlMenosUno = (double) iteracionesConAlMenosUnAcierto / totalIteraciones;

                    // Formateo de los resultados para la pantalla
                    String mensajeFinal = String.format(
                        "Resultados de simular %d iteraciones (cada una de 10 shows):\n\n" +
                        "► Probabilidad Empírica (basada en resultados aleatorios):\n" +
                        "  - De fallar TODOS los shows: %.2f%%\n" +
                        "  - De acertar AL MENOS UNO: %.2f%%\n\n" +
                        "► Probabilidad Teórica Matemática:\n" +
                        "  - De fallar TODOS los shows: %.2f%%\n" +
                        "  - De acertar AL MENOS UNO: %.2f%%\n\n" +
                        "Resumen de Iteraciones:\n" +
                        "  - Iteraciones fallidas completas: %d\n" +
                        "  - Iteraciones con éxito (≥1 acierto): %d\n\n" +
                        "Los registros detallados de cada show están en el archivo log.",
                        totalIteraciones,
                        probEmpiricaFallarTodos * 100, probEmpiricaAlMenosUno * 100,
                        probTeoricaFallarTodos * 100, probTeoricaAlMenosUno * 100,
                        iteracionesConCeroAciertos, iteracionesConAlMenosUnAcierto
                    );

                    JOptionPane.showMessageDialog(null, 
                    mensajeFinal, 
                    "Resultados Aleatorios de Telepatía", 
                    JOptionPane.INFORMATION_MESSAGE);
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