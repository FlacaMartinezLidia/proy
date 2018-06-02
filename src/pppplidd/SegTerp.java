package pppplidd;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
if(elecion ==3){
        try {
            sc=new Scanner(System.in);
            //archivo antigup
            FileInputStream fis = new FileInputStream("db.dat");
            DataInputStream entrada = new DataInputStream(fis);
            
            //nuevo archivo
            FileOutputStream fisN = new FileOutputStream("x.dat");
            DataOutputStream salida = new DataOutputStream(fisN); 
            
            System.out.println("Ingrese el contenido del primer atributo");
            System.out.println("del registro a eliminar");
            
            String key=sc.nextLine();
            

            Boolean x =true;
            
            while(x==true){
                try{
                    String tipo=entrada.readUTF();    
                    String nm=entrada.readUTF();    
                    
                    if(tipo.compareToIgnoreCase("String")==0){
                         String n1=entrada.readUTF();
                         
                         
                         if(key.compareToIgnoreCase(n1)==0){
                            
                             try{
                                
                            FileReader lector=new FileReader("definicion.txt");
                            BufferedReader contenido=new BufferedReader(lector);
                            contenido.readLine();
                            contenido.readLine();
                            contenido.readLine();
                            contenido.readLine();
                            System.out.println("registro con el atributo "+n1+" eliminado.");
                            while(contenido.readLine()!=null){
                                
                            String tipe=contenido.readLine();
                            contenido.readLine();
                                if(tipe.compareToIgnoreCase("String")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readUTF();
                                }
                                if(tipe.compareToIgnoreCase("Integer")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readInt();
                                }
                                if(tipe.compareToIgnoreCase("Date")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readInt();
                                    entrada.readInt();
                                    entrada.readInt();
                                }
                                if(tipe.compareToIgnoreCase("Long")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readLong();
                                }
                                if(tipe.compareToIgnoreCase("Double")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readDouble();
                                }
                            }
                            
                            
                            
                            
                            //lector.close();
                            //contenido.close();
                            
                            }catch(Exception e){
                                 
                                System.out.println("error ciclo");
                            }
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeUTF(n1);
                         }
                         
                         
                    }
        
                    if(tipo.compareToIgnoreCase("Integer")==0){
                        int n2=entrada.readInt();
                         
                         
                         if(key.compareToIgnoreCase(nm)==0){
                             System.out.println("registro con el atributo "+nm+" eliminado.");
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeInt(n2);
                         }
                         
                         
                    }
        
                    if(tipo.compareToIgnoreCase("Date")==0){
                        int anio=entrada.readInt();
                        int mes=entrada.readInt();
                        int dia=entrada.readInt();
                         
                         
                         if(key.compareToIgnoreCase(nm)==0){
                             System.out.println("registro con el atributo "+nm+" eliminado.");
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeInt(anio);
                             salida.writeInt(mes);
                             salida.writeInt(dia);
                         }
                         
                         

           
                    } 
                    if(tipo.compareToIgnoreCase("Long")==0){
                        long dato=entrada.readLong();
                         
                         
                         if(key.compareToIgnoreCase(nm)==0){
                             System.out.println("registro con el atributo "+nm+" eliminado.");
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeLong(dato);
                         }
                         
                         
                    }
                    if(tipo.compareToIgnoreCase("Double")==0){
                        double dato=entrada.readDouble();
                        
                         
                         if(key.compareToIgnoreCase(nm)==0){
                             System.out.println("registro con el atributo "+nm+" eliminado.");
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeDouble(dato);
                         }
                         
                         
                    }
                    
                
                }catch(EOFException e){
                    e.printStackTrace();
                    x=false;
                }
            
            }
            
            
        fis.close();
                entrada.close();
                fisN.close();
                salida.close();
                
                File nuevo=new File("x.dat");
                File viejo=new File("db.dat");
                
                if(viejo.delete()==true){
                    System.out.println("eliminado");
                }else{
                    System.out.println("NO eliminado");
                }
                
                nuevo.renameTo(viejo);
                
                
                    
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(SegTerp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SegTerp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//fin if 3       
    


    if(elecion ==4){
        try {
            sc=new Scanner(System.in);
            //archivo antigup
            FileInputStream fis = new FileInputStream("db.dat");
            DataInputStream entrada = new DataInputStream(fis);
            
            //nuevo archivo
            FileOutputStream fisN = new FileOutputStream("x.dat");
            DataOutputStream salida = new DataOutputStream(fisN); 
            
            System.out.println("Ingrese el contenido del primer atributo");
            System.out.println("del registro a modificar");
            
            String key=sc.nextLine();
            

            Boolean x =true;
            
            while(x==true){
                try{
                    String tipo=entrada.readUTF();    
                    String nm=entrada.readUTF();    
                    
                    if(tipo.compareToIgnoreCase("String")==0){
                         String n1=entrada.readUTF();
                         
                         
                         if(key.compareToIgnoreCase(n1)==0){
                             
                             /////////////////////////////////////////////////
                             
                              try{
                                
                            FileReader lector=new FileReader("definicion.txt");
                            BufferedReader contenido=new BufferedReader(lector);
                            contenido.readLine();
                            contenido.readLine();
                            contenido.readLine();
                            contenido.readLine();
                            salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeUTF(n1);
                             
                            //System.out.println("registro con el atributo "+n1+" eliminado.");
                            String nombreD="";
                            while((nombreD=contenido.readLine())!=null){
                                
                            String tipe=contenido.readLine();
                            
                            String lOng=contenido.readLine();
                                if(tipe.compareToIgnoreCase("String")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    
                                    System.out.println("ingrese nuevo "+nombreD+" del tipo "+tipe);
                                    String data=sc.nextLine();
                                    
                                    salida.writeUTF(nombreD);
                                    salida.writeUTF(tipe);
                                    salida.writeUTF(data);
                                }
                                if(tipe.compareToIgnoreCase("Integer")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readInt();
                                    
                                    
                                    System.out.println("ingrese nuevo "+nombreD+" del tipo "+tipe);
                                    int data=sc.nextInt();
                                    
                                    salida.writeUTF(nombreD);
                                    salida.writeUTF(tipe);
                                    salida.writeInt(data);
                                }
                                if(tipe.compareToIgnoreCase("Date")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readInt();
                                    entrada.readInt();
                                    entrada.readInt();
                                    
                                    
                                    System.out.println("ingrese nuevo "+nombreD+" del tipo "+tipe);
                                    System.out.println("ingrese anio");
                                    int anio=sc.nextInt();
                                    System.out.println("ingrese mes");
                                    int mes=sc.nextInt();
                                    System.out.println("ingrese dia");
                                    int dia=sc.nextInt();
                                    
                                    salida.writeUTF(nombreD);
                                    salida.writeUTF(tipe);
                                    salida.writeInt(anio);
                                    salida.writeInt(mes);
                                    salida.writeInt(dia);
                                    
                                }
                                if(tipe.compareToIgnoreCase("Long")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readLong();
                                    
                                     
                                    System.out.println("ingrese nuevo "+nombreD+" del tipo "+tipe);
                                    Long data=sc.nextLong();
                                    
                                    salida.writeUTF(nombreD);
                                    salida.writeUTF(tipe);
                                    salida.writeLong(data);
                                }
                                if(tipe.compareToIgnoreCase("Double")==0){
                                    entrada.readUTF();
                                    entrada.readUTF();
                                    entrada.readDouble();
                                    
                                     
                                    System.out.println("ingrese nuevo "+nombreD+" del tipo "+tipe);
                                    double data=sc.nextDouble();
                                    
                                    salida.writeUTF(nombreD);
                                    salida.writeUTF(tipe);
                                    salida.writeDouble(data);
                                }
                            }
                            
                            
                            
                            
                            lector.close();
                            contenido.close();
                            
                            }catch(Exception e){
                                 
                                System.out.println("error ciclo");
                            }
                            //fin modificar
                             
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeUTF(n1);
                         }
                         
                         
                    }
        
                    if(tipo.compareToIgnoreCase("Integer")==0){
                        int n2=entrada.readInt();
                         
                         
                         if(key.compareToIgnoreCase(nm)==0){
                             System.out.println("registro con el atributo "+nm+" eliminado.");
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeInt(n2);
                         }
                         
                         
                    }
        
                    if(tipo.compareToIgnoreCase("Date")==0){
                        int anio=entrada.readInt();
                        int mes=entrada.readInt();
                        int dia=entrada.readInt();
                         
                         
                         if(key.compareToIgnoreCase(nm)==0){
                             System.out.println("registro con el atributo "+nm+" eliminado.");
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeInt(anio);
                             salida.writeInt(mes);
                             salida.writeInt(dia);
                         }
                         
                         

           
                    } 
                    if(tipo.compareToIgnoreCase("Long")==0){
                        long dato=entrada.readLong();
                         
                         
                         if(key.compareToIgnoreCase(nm)==0){
                             System.out.println("registro con el atributo "+nm+" eliminado.");
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeLong(dato);
                         }
                         
                         
                    }
                    if(tipo.compareToIgnoreCase("Double")==0){
                        double dato=entrada.readDouble();
                        
                         
                         if(key.compareToIgnoreCase(nm)==0){
                             System.out.println("registro con el atributo "+nm+" eliminado.");
                         }else{
                             salida.writeUTF(tipo);
                             salida.writeUTF(nm);
                             salida.writeDouble(dato);
                         }
                         
                         
                    }
                    
                
                }catch(EOFException e){
                    e.printStackTrace();
                    x=false;
                }
            
            }
            
            
        fis.close();
                entrada.close();
                fisN.close();
                salida.close();
                
                File nuevo=new File("x.dat");
                File viejo=new File("db.dat");
                
                if(viejo.delete()==true){
                    System.out.println("eliminado");
                }else{
                    System.out.println("NO eliminado");
                }
                
                nuevo.renameTo(viejo);
                
                
                    
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(SegTerp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SegTerp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //fin if 4

        
    System.out.println("desea salir del programa?");
    System.out.println("1)si");
    System.out.println("2)no");

    int f =sc.nextInt();
        
    if(f!=1){
        terminar = true;
    }
    }//fin while
    
    
        
        
        
        
        
    }
    
}

	


