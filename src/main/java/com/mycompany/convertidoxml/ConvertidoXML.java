/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.convertidoxml;
import backend.archivo.ControladorArchivo;
import backend.xml.XMLModification;
import frontend.Panel1;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;



public class ConvertidoXML {
    
    public static void main(String[] args) throws Exception {
        String directorioActual = System.getProperty("user.dir");

        // Ruta de la carpeta "result" en el directorio actual
        String rutaCarpeta = directorioActual + File.separator + "result";

        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            boolean exito = carpeta.mkdir();
            if (exito) {
                System.out.println("La carpeta se ha creado correctamente.");
            } else {
                System.out.println("No se pudo crear la carpeta.");
            }
        } else {
            System.out.println("La carpeta ya existe.");
        }
     JFrame frame = new JFrame("Mi Aplicación"); // Cambia el título a tu preferencia
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel1 panel = new Panel1();
//      panel.onIniciar(direccionIP, contrasena, rutaArchivo);
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        

    }
    public static void realizarInicio(String direccionIP, String contrasena, String rutaArchivo) throws IOException {
        ControladorArchivo archivo = new ControladorArchivo(rutaArchivo);
        List<String> contenido = archivo.obtenerArchivo();
        System.out.println(contenido.size());
        for (int i = 0; i < contenido.size(); i++) {
            String res = contenido.get(i);
            String[] parts = res.split(",",-1);
       String nombreArchivo = parts[1];
            String name = parts[2];
            String modelo = parts[41];
            String extension = parts[140];
            String modeloXml;
            int  tipo;
            
            if (modelo.equalsIgnoreCase("Cisco 3905")) {
                modeloXml = "modelosXML\\CP-3905\\SEPF07816A2C103.cnf.xml_22205";
                tipo = 1;
            }
            else{
                if (modelo.equalsIgnoreCase("Cisco 7821")) {
                    modeloXml = "modelosXML\\CP-7861\\SEPF07816D0A358.cnf.xml_22200";
                    tipo = 2;
                }
                else{
                      if (modelo.equalsIgnoreCase("Cisco 7861")) {
                        modeloXml="modelosXML\\CP-7861\\SEPF07816D0A358.cnf.xml_22200";
                        tipo = 2;
                      }
                      else {
                        modeloXml ="modelosXML\\CP-8945\\SEP204C9E6CB941.cnf.xml_22202";
                        tipo = 2;
                      }
                    }
            }
           
            XMLModification modification = new XMLModification(nombreArchivo,name,extension,modeloXml,tipo,direccionIP,contrasena);
            modification.construirArchivos();
        
        
        }
      
    }
}
