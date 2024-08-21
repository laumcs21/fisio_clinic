package www.uniquindio.edu.poo;

import java.io.*;

public class Contador {

    private static final String FILE_NAME = "contador.txt";
    private static int contador;

    static {
        // Lee el contador desde el archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            contador = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            // Si ocurre un error, empieza desde 1
            contador = 1;
        }
    }

    public synchronized static String generarCodigo() {
        // Incrementa el contador y guarda el nuevo valor en el archivo
        String codigo = Integer.toString(contador);
        contador++;
        guardarContador();
        return codigo;
    }

    private static void guardarContador() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(Integer.toString(contador));
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error de forma adecuada
        }
    }
}
