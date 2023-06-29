package backend.archivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ControladorArchivo {
    private List<String> archivo;
    private final String PATH;

    public ControladorArchivo(String path){
        this.PATH = path;
        this.archivo = leerArchivo();  
    }
    private List<String> leerArchivo(){
        List<String> res = new ArrayList<String>();
        BufferedReader lectorCsv = null;
        try {
            lectorCsv = new BufferedReader(new FileReader(PATH, StandardCharsets.UTF_8));
            lectorCsv.readLine();
            lectorCsv.readLine();
            lectorCsv.readLine();
            String linea = lectorCsv.readLine(); 
            while (linea != null && !linea.equalsIgnoreCase("")) {
                res.add(linea);
                linea = lectorCsv.readLine();
            }
            lectorCsv.close();
        } catch (IOException e) {
                System.out.println("Archivo no encontrado");
        }
        return res; 
    }

    public void escribirArchivo(String contenido){
       FileWriter escritor = null;
        try {
         escritor = new FileWriter(PATH , StandardCharsets.UTF_8);
            for (String filas : this.archivo) {
            escritor.append(filas);
            escritor.append("\r\n");
        }
        escritor.append(contenido);
         escritor.flush();
         escritor.close();
         this.archivo.add(contenido);
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
    }
    public List<String> obtnerArchivoReverse(){
        List<String> res = new ArrayList<String>();
        for (int i = archivo.size() - 1; i >=0 ; i--) {
            res.add(archivo.get(i)); 
        }
        return res;
    }
    public void actualizarArchivo(List<String> contenido){
        FileWriter escritor = null;
         try {
          escritor = new FileWriter(PATH, StandardCharsets.UTF_8);
             for (String filas : contenido) {
             escritor.append(filas);
             escritor.append("\r\n");
         }
         escritor.flush();
         escritor.close();
         this.archivo = contenido;
         } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        
         }
     }
     public List<String> obtenerArchivo(){
        return this.archivo; 
    }
    public int buscar(String contenido,int columna){
        int res = -1;
        for (int i = 0; i < archivo.size(); i++) {
            String[] partes = archivo.get(i).split("\\,");
            if (partes[columna].replace("\"", "").equalsIgnoreCase(contenido)) {
                res = i;
            }
        }
        return res;
    }
    public void actualizar(int index,String contenido){
        List<String> publicaciones = archivo;
        publicaciones.set(index, contenido);
        this.actualizarArchivo(publicaciones);
    }
    public String getFila(int index){
        return this.archivo.get(index);
    }
    public List<String> getFilas(String contenido,int columna){

        List<String> filas = new ArrayList<>();
        for (String fila : archivo) {
            String[] partes = fila.split("\\,");
            if (partes[columna].replace("\"", "").equalsIgnoreCase(contenido)){
                filas.add(fila);
            }
        }
        return filas;
    }
}
