/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentadores;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JFileChooser;
import otros.conexionBD;

/**
 *
 * @author sic
 */
public class EstadoCapital {
    
    
    
   public void generarEstadoCapital(Date inicio,Date fin) throws FileNotFoundException, DocumentException, SQLException
           {
          //conectarme a la base
        conexionBD conexionCuentas= new conexionBD();
        // Se crea el documento
         Document documento = new Document();

        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
         FileOutputStream ficheroPdf = null;
         
         JFileChooser guardarcomo= new JFileChooser();
         guardarcomo.setApproveButtonText("Guardar");
         guardarcomo.showSaveDialog(null);
         
        ficheroPdf = new FileOutputStream(guardarcomo.getSelectedFile()+".pdf");
        
        

        // Se asocia el documento al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
        PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

        //tamaño del pdf
        Rectangle two = new Rectangle(900,650);
        documento.setPageSize(two); //enviar tamaño al documento
        documento.setMargins(2,2,2,2); 
        documento.open(); //abrir para editar  
        //AGREGAR LA IMAGEN 
        try
        {
                Image foto = Image.getInstance("logo.png");
                foto.scaleToFit(150, 150);
                foto.setAlignment(Chunk.ALIGN_MIDDLE);
                documento.add(foto);
        }
            catch ( Exception e )
            {
                    e.printStackTrace();
            }
        
        //-----  Obteniendo la fecha   fechafin
        String fecha="1 de enero de 2016";
        ResultSet resultado0=null;
                
                ResultSet res=null; 
                PreparedStatement declaracion0= null;
                //ABAJO ESTA TODA LA CONSULTA QUE RETORNA LA FECHA DE INICIO Y FINAL
                String sqlFECHA="SELECT fechainicio\n" +
                                "  FROM public.librodiario\n" +
                                "  order by fechainicio desc;";

                declaracion0= conexionCuentas.getConexion().prepareStatement(sqlFECHA);
                res= declaracion0.executeQuery();
                res.next();
                fecha="Inicio de periodo:"+res.getString(1);
               System.out.println(fecha);
               
               
               String sqlFECHAFIN="SELECT fechafin\n" +
                                "  FROM public.librodiario\n" +
                                "  order by fechainicio desc;";

                declaracion0= conexionCuentas.getConexion().prepareStatement(sqlFECHAFIN);
                res= declaracion0.executeQuery();
                res.next();
                fecha=fecha +" hasta: " +res.getString(1);
   

       //_______________________ABAJO CREO 3 INSTANCIAS QUE LE DAN ESTILO A LAS LETRAS Y CONTIENEN LOS PARRAFOS_______________________________________________
     
        Paragraph parrafo = new Paragraph("Estado de capital",
                FontFactory.getFont("arial",   // fuente
                        14,                            // tamaño
                        Font.ITALIC                   ));
     
        parrafo.setAlignment(Element.ALIGN_CENTER);
        //______________________________________________________________________    
        Paragraph parrafo2 = new Paragraph(" "+fecha,
                FontFactory.getFont("arial",   // fuente
                        14,                            // tamaño
                        Font.ITALIC                   ));
        parrafo2.setAlignment(Element.ALIGN_CENTER);
        //______________________________________________________________________
        Paragraph parrafo3 = new Paragraph("                                                    ",
                FontFactory.getFont("arial",   // fuente
                        14,                            // tamaño
                        Font.ITALIC                   ));
        parrafo3.setAlignment(Element.ALIGN_CENTER);
        
        //AGREGO LOS PARRAFOS
        documento.add(parrafo);    // estilo
        documento.add(parrafo2);    // estilo
        documento.add(parrafo3);    // estilo                
        //DECLARO VARIABLES QUE ME RECIBIRAN LAS CONSULTAS CON LA BASE DE DATOS
                ResultSet resultado1=null;                  
                PreparedStatement declaracion1= null;
                String sqltemporal=null;
                
                ///////_________________________________________________SENTENCIAS SQL DE LAS PARTES DEL ESTADO DE CAPITAL__________________________________________________________________
                
                float capitalInicial=0; float utilidadoDeficit=0;float gastosNoOperacionales=0;
                
                sqltemporal="SELECT SUM(haber_cuenta-debe_cuenta)\n" +
                                    "FROM public.cuenta WHERE idcuenta=3101;"; 
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqltemporal);resultado1= declaracion1.executeQuery();resultado1.next();
                capitalInicial=resultado1.getFloat(1);
                
                
                
                
 
                sqltemporal="SELECT sum(debe_cuenta-haber_cuenta)\n" +
                            "FROM public.cuenta WHERE idcuenta=4201;";
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqltemporal);resultado1= declaracion1.executeQuery();resultado1.next();
                gastosNoOperacionales=resultado1.getFloat(1);
                resultado1=null;
                
                sqltemporal="SELECT sum(haber_cuenta-debe_cuenta)\n" +
                            "FROM public.cuenta WHERE idcuenta=3201;";
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqltemporal);resultado1= declaracion1.executeQuery();resultado1.next();
                utilidadoDeficit=resultado1.getFloat(1);
                resultado1=null;
                
               //_________EXTRAJE LA DIFERENCIA DEBE - HABER_____CALCULOS___________________________________________________________________________________________________________________
                float capitalSocialFinal=capitalInicial+(1*utilidadoDeficit)-gastosNoOperacionales;//______________________________CALCULO DEL CAPITAL FINAL
                System.out.println("/"+capitalInicial+"/"+"/"+"/"+utilidadoDeficit+"/"+gastosNoOperacionales+"//"+capitalSocialFinal);
               //________________________________________________________________________________________________________________________________________________________________________________________
                
               //DESCOMENTAR PARA QUE SE REALICE LA UPDATE
                
                sqltemporal="UPDATE public.cuenta SET haber_cuenta=? WHERE idcuenta=3101;";       //actualizar la utilidad en caso negativo        
                conexionCuentas.getConexion().prepareStatement(sqltemporal);
                declaracion1= conexionCuentas.getConexion().prepareCall(sqltemporal);
                declaracion1.setDouble(1,capitalSocialFinal);
                declaracion1.executeUpdate();
             
                
                  //________________QUITAR

                ///////______________________________________________________________FIN DE LAS SENTENCIAS ________________________________________________________________________________________
                

                PdfPTable tabla = new PdfPTable(3); //cantidad de columnas
                tabla.setTotalWidth (new float [] {400,150,150});   //DEFINO EL TAMAÑO SE CULUMNAS
                tabla.setLockedWidth (true);
                
                PdfPTable tabla2 = new PdfPTable(3);
                tabla2.setTotalWidth (new float [] {400,150,150});
                tabla2.setLockedWidth (true); 
                tabla2.addCell("NOMBRE"); tabla2.addCell("DEBE");tabla2.addCell("HABER");
                //____________________________________________________________________INICIO DEL WHILE PARA FORMAR EL PDF_______________________________________________________________________________
              
                int x=0;
             
                while(x!=40) //para generar 400 celdas, este valor 400 solo lo meti son suficientes iteraciones
                {
                                    if(x==0){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                    //PRIMERA LINEA DE SEGUNDOS ENCABEZADOS
                                     tabla.addCell("INVERSIONES"); tabla.addCell(" ");tabla.addCell(" ");
                                    
                                    pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                  tabla.addCell("CAPITAL INICIAL");tabla.addCell("$ "+capitalInicial);tabla.addCell(" ");
                                    //segunda linea impresa
   
                                    }
                                    

                                    
                                    //lina 4 impresa
                                        if(utilidadoDeficit>=0 && x==4){
                                             tabla.addCell("UTILIDAD 100%");tabla.addCell(" "); tabla.addCell("$ "+utilidadoDeficit);
                                        }
                                        
                                        if(x==5){
                                           tabla.addCell("DESINVERSIONES"); tabla.addCell(" ");tabla.addCell(" ");
                                        //linea 5 impresa
                                        }
                                        
                                        if(utilidadoDeficit<0 && x==6){                                            
                                           tabla.addCell("DEFICIT (Desinversion)");tabla.addCell("$ ("+-1*utilidadoDeficit+")");tabla.addCell(" ");
                                        }
                                       
                                        
                                       if(x==7){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                     
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("GASTOS NO OPERACIONALES")));tabla.addCell(pdfCellHeader);
                                        PdfPCell pdfCellHeader2 = new PdfPCell();                                                                                
                                        pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+gastosNoOperacionales)));tabla.addCell(pdfCellHeader2);
                                        tabla.addCell(" ");
                                        //linea 6 impresa
                                    }  
                                    
                                    if(x==8){
                                        PdfPCell pdfCellHeader = new PdfPCell();                                                                           
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("CAPITAL SOCIAL")));tabla.addCell(pdfCellHeader);
                                        PdfPCell pdfCellHeader2 = new PdfPCell();                                            
                                        if(capitalSocialFinal<0){ // solo por si se da el extraño caso de ser negativo XD                                            
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("($ "+(capitalSocialFinal*-1)+")")));tabla.addCell(pdfCellHeader2);
                                             tabla.addCell("");
                                        }
                                        else{
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+capitalSocialFinal)));tabla.addCell(pdfCellHeader2);
                                            tabla.addCell("");
                                            break;
                                        }                           
                                    }

                    x++;
                }

            documento.add(tabla2);//ingreso las tablas al documento
            documento.add(tabla);
            //cierro el documento pdf
           documento.close();   
    }
           
    
}
