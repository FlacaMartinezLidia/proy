package pppplidd;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class SegTerp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        File definicion =new File("definicion.txt");
        
        if(!definicion.exists()){
            try{
            FileWriter escribir=new FileWriter(definicion,true);

            
            String Entidad;
            String nombre;
            String tDat;
            int longitud;
            System.out.println("Ingrese nombre para la entidad");
            Entidad =sc.nextLine();
            
            escribir.write(Entidad+"\r\n");
            
            Boolean seguir=false;
            
            while(seguir==false){
            sc=new Scanner(System.in);
            nombre=null;
            tDat=null;
            longitud=0;
            System.out.println("Ingrese nombre del atributo");
            nombre=sc.nextLine();
            
            System.out.println("seleccione tipo de dato");
            System.out.println("1)String");
            System.out.println("2)Date");
            System.out.println("3)Long");
            System.out.println("4)Double");
            System.out.println("5)Integer");
            
            int dec=0;
            dec=sc.nextInt();
            switch(dec){
                case 1:
                    tDat="String";
                break;
                case 2:
                    tDat="Date";
                break;
                case 3:
                    tDat="Long";
                break;
                case 4:
                    tDat="Double";
                break;
                case 5:
                    tDat="Integer";
                break;
                default:
                    tDat=null;
                break;
            }
            
            System.out.println("Ingrese longitud deseada, si el tipo de dato no lo necesita ingrese 0");
            longitud=sc.nextInt();
            
            escribir.write(nombre+"\r\n");
            escribir.write(tDat+"\r\n");
            escribir.write(longitud+"\r\n");
            
            System.out.println("desea ingresar otra caracteristica?");
            System.out.println("1)si");
            System.out.println("cualquie numero)no");
            int q=sc.nextInt();
            
            if(q!=1){
                seguir=true;
                escribir.close();
            }
            
            }
            
        }catch(IOException e){
                        System.out.println("Error de Archivo");
        }catch(Exception e){
                        System.out.println("Error de tipo de dato o nulo");
        }
        }
        
        
        
    sc=new Scanner(System.in);
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();

    Boolean terminar=false;
    while(terminar==false){
    System.out.println("Entidad creada");
    System.out.println("Que accion desea realizar?");
    System.out.println("Consultar base de datos");
    System.out.println("1)agregar registro");
    System.out.println("2)eliminar registro");
    System.out.println("3)modificar registro");
    int elecion=0;
    elecion=sc.nextInt();
    
    String texto="";
    String nEntidad="";
    String nNombre="";
    String tDato="";
    
    //datos para el .dat
    
    int longi;
   
    
    if(elecion==1){
    sc = new Scanner(System.in);
    
    try{
    FileReader lector=new FileReader("definicion.txt");
    BufferedReader contenido=new BufferedReader(lector);
    FileOutputStream fos = new FileOutputStream("db.dat");;
    DataOutputStream salida = new DataOutputStream(fos);    
    
    nEntidad=contenido.readLine();
    
    
    
    while((texto=contenido.readLine())!=null){
        
        try{
        
        sc=new Scanner(System.in);
        
    nNombre=texto;
        
    tDato=contenido.readLine();
    
    longi=Integer.parseInt(contenido.readLine());

    
    System.out.println("ingrese el "+nNombre+" ("+tDato+")");
        
    if(tDato.compareToIgnoreCase("String")==0){
            String sname=sc.nextLine();
            if(sname.length()>longi){
                System.out.println("Dato excede el tamano predefinido de "+longi+" caracteres.");
                
            }
            salida.writeUTF(sname);
        }
        if(tDato.compareToIgnoreCase("Integer")==0){
            int sname=sc.nextInt();
            salida.writeInt(sname);
        }
        if(tDato.compareToIgnoreCase("Date")==0){
            System.out.println("Ingrese anio");
            int anio=sc.nextInt();
            System.out.println("Ingrese mes");
            int mes=sc.nextInt();
            System.out.println("Ingrese dia");
            int dia=sc.nextInt();
            
            salida.writeInt(anio);
            salida.writeInt(mes);
            salida.writeInt(dia);
        } 
        if(tDato.compareToIgnoreCase("Long")==0){
            long dato=sc.nextLong();
            salida.writeLong(dato);
        }
        if(tDato.compareToIgnoreCase("Double")==0){
            double dato=sc.nextDouble();
            salida.writeDouble(dato);
        }
        
        
    
    //System.out.println(nNombre+" "+tDato+" "+longi);
        }catch(Exception e){
            System.out.println("Error con los datos intente otra vez");

        }
        
    }
    contenido.close();
    salida.close();
    }catch(IOException e){
    System.out.println("Error al leer");
    e.printStackTrace();
    }
    
        
    }//FIN IF
    if(elecion ==2){        
        try{
        FileInputStream fis = new FileInputStream("db.dat");
        DataInputStream entrada = new DataInputStream(fis); 
        FileReader lector=new FileReader("definicion.txt");
        BufferedReader contenido=new BufferedReader(lector);
        
        String nn=contenido.readLine();
        System.out.println(nn);

        while((texto=contenido.readLine())!=null){
        String nm=texto;
        String tipo=contenido.readLine();
        contenido.readLine();
        
        if(tipo.compareToIgnoreCase("String")==0){
            String n1=entrada.readUTF();
            System.out.println(n1);
        }
        if(tipo.compareToIgnoreCase("Integer")==0){
            int n2=entrada.readInt();
            System.out.println(n2);
        }
        if(tipo.compareToIgnoreCase("Date")==0){
            int anio=entrada.readInt();
            int mes=entrada.readInt();
            int dia=entrada.readInt();
                        System.out.println(dia+"/"+mes+"/"+anio);

           
        } 
        if(tipo.compareToIgnoreCase("Long")==0){
            long dato=entrada.readLong();
            System.out.println(dato);
        }
        if(tipo.compareToIgnoreCase("Double")==0){
            double dato=entrada.readDouble();
            System.out.println(dato);
        }
      
        //fin
        }

        
        }catch(Exception e){
        System.out.println("error con archivos");
        e.printStackTrace();
        }
        
        
    }//fin 2 if
        
    System.out.println("desea salir del programa?");
    System.out.println("1)si");
    System.out.println("0)no");

    int f =sc.nextInt();
        
    if(f!=1){
        terminar = true;
    }
    }//fin while
    
    
        
        
        
        
        
    }
    
}

	


