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
public class EstadoResultado {
    public void generarEstadodeResultado(Date inicio,Date fin) throws FileNotFoundException, DocumentException, SQLException
            {
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
        Rectangle two = new Rectangle(750,500);
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
                fecha="Inicio de periodo:"+inicio.toString();
               System.out.println(fecha);
               
               
               String sqlFECHAFIN="SELECT fechafin\n" +
                                "  FROM public.librodiario\n" +
                                "  order by fechainicio desc;";

                declaracion0= conexionCuentas.getConexion().prepareStatement(sqlFECHAFIN);
                res= declaracion0.executeQuery();
                res.next();
                fecha=fecha +" hasta: " +fin.toString();
   

       //_______________________ABAJO CREO 3 INSTANCIAS QUE LE DAN ESTILO A LAS LETRAS Y CONTIENEN LOS PARRAFOS_______________________________________________
     
        Paragraph parrafo = new Paragraph("Estado de resultados",
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
                ResultSet resultado2=null; 
                PreparedStatement declaracion1= null;
                
                ///////_________________________________________________SENTENCIAS SQL DE LAS PARTES DEL ESTADO DE RESULTADO__________________________________________________________________
                
                float ventasNetas=0; float costoDLVendido=0;float gastosDeOperacion=0;float gastosFinancieros=0;float otrosIngresosNetos=0;float impuestos=0;
                
                float utilidadBruta=0; float utilidadDeOperacion=0;  float utilidadAntDeImpuestos=0;
                float utilidadNeta=0; 
                String sqltemporal="SELECT haber_cuenta\n" +
                                    "FROM public.cuenta WHERE idcuenta=5101;"; 
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqltemporal);
                resultado1= declaracion1.executeQuery();
                resultado1.next();
                ventasNetas=resultado1.getFloat(1);
                
                resultado1=null;
                sqltemporal="SELECT debe_cuenta FROM public.cuenta WHERE idcuenta=4103;";
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqltemporal);
                resultado1= declaracion1.executeQuery();
                resultado1.next();
                costoDLVendido=resultado1.getFloat(1);//CAPTURO EL MONTO PARA CADA SENTENCIA 
                resultado1=null;
                //________________________________________
                
                   
                sqltemporal="SELECT debe_cuenta\n" +
                            "FROM public.cuenta WHERE idcuenta=4110;";
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqltemporal);
                resultado1= declaracion1.executeQuery();
                resultado1.next();
                gastosFinancieros=resultado1.getFloat(1);
                resultado1=null;
               
                 sqltemporal="SELECT SUM(debe_cuenta)\n" +
                            "  FROM public.cuenta\n" +
                            "  WHERE idcuenta=4107 OR idcuenta=4108 OR idcuenta=4109;";
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqltemporal);
                resultado1= declaracion1.executeQuery();
                resultado1.next();
                gastosDeOperacion=resultado1.getFloat(1);
                 //________________________________________

                 utilidadBruta=ventasNetas-costoDLVendido;
                utilidadDeOperacion=utilidadBruta-gastosDeOperacion;
                utilidadAntDeImpuestos=utilidadDeOperacion-gastosFinancieros;
                
                PreparedStatement enviarRenta= conexionCuentas.getConexion().prepareStatement("update cuenta set haber_cuenta=haber_cuenta+? where idcuenta=1101");
                float renta=0;
                if(utilidadAntDeImpuestos<100000 && utilidadAntDeImpuestos>0){
                
                renta=(float) (0.25*(utilidadAntDeImpuestos));
               
                }else{
                
                    renta=(float) (utilidadAntDeImpuestos*0.3); 
                    
                    
                    
                }
                 enviarRenta.setDouble(1, renta);
                 enviarRenta.execute();
                float utilidadneta=utilidadAntDeImpuestos-renta; //___________________________________________:-----------------------
                
                
                
                resultado1=null;
               
                sqltemporal="SELECT haber_cuenta  FROM public.cuenta where idcuenta=3201;"; //obtener la utilidad 
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqltemporal);
                resultado1= declaracion1.executeQuery();
                resultado1.next();
                float UtilidadesNoDist=resultado1.getFloat(1);
                float NuevasUtilidades=UtilidadesNoDist+utilidadneta;
                
                if(NuevasUtilidades<0){
                sqltemporal="UPDATE public.cuenta SET debe_cuenta=debe_cuenta+? WHERE idcuenta=3201;";       //actualizar la utilidad en caso negativo        
                conexionCuentas.getConexion().prepareStatement(sqltemporal);
                declaracion1= conexionCuentas.getConexion().prepareCall(sqltemporal);
                declaracion1.setDouble(1,utilidadneta*-1);
                declaracion1.executeUpdate();
             
                }
                else{
                    sqltemporal="UPDATE public.cuenta SET haber_cuenta=haber_cuenta+? WHERE idcuenta=3201;";       //actualizar la utilidad en caso negativo        
                conexionCuentas.getConexion().prepareStatement(sqltemporal);
                declaracion1= conexionCuentas.getConexion().prepareCall(sqltemporal);
                declaracion1.setDouble(1,utilidadneta);
                declaracion1.executeUpdate();
              
                }
                
                
                

                ///////______________________________________________________________FIN DE LAS SENTENCIAS ________________________________________________________________________________________
                

                PdfPTable tabla = new PdfPTable(3); //cantidad de columnas
                tabla.setTotalWidth (new float [] {25,400,150});   //DEFINO EL TAMAÑO SE CULUMNAS
                tabla.setLockedWidth (true);
                
                PdfPTable tabla2 = new PdfPTable(3);
                tabla2.setTotalWidth (new float [] {25,400,150});
                tabla2.setLockedWidth (true); tabla2.addCell("S/R"); tabla2.addCell("NOMBRE"); tabla2.addCell("MONTO");

                //________________________________________________________________________________________________________________________________________________________
              
                int x=0;
                
                while(x!=40) //para generar 400 celdas, este valor 400 solo lo meti son suficientes iteraciones
                {
                      

                                                          //agrego 4 celdas para los activos
                                                      
                                    if(x==0){
                                    PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("+");
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("Ventas netas")));
                                        tabla.addCell(pdfCellHeader);
                                    PdfPCell pdfCellHeader2 = new PdfPCell();    
                                        
                                        if(ventasNetas<0){
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("( $"+(ventasNetas*-1)+")")));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        else{
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+ventasNetas)));
                                             tabla.addCell(pdfCellHeader2);
                                        }
  
                                    }

                                    if(x==1){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("-");
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("Costo de lo vendido")));
                                        tabla.addCell(pdfCellHeader);
                                        PdfPCell pdfCellHeader2 = new PdfPCell();    
                                        if(costoDLVendido<0){ // solo por si se da el extraño caso de ser negativo XD                                            
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("($ "+(costoDLVendido*-1)+")")));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        else{
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+costoDLVendido)));
                                             tabla.addCell(pdfCellHeader2);
                                        }                           
                                    }
                                    
                                    if(x==2){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("=");
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("Utilidad Bruta")));
                                        tabla.addCell(pdfCellHeader);
                                        PdfPCell pdfCellHeader2 = new PdfPCell();
                                        if(utilidadBruta<0){ // solo por si se da el extraño caso de ser negativo XD                                            
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("( $"+(utilidadBruta*-1)+")")));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        else{
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+utilidadBruta)));
                                             tabla.addCell(pdfCellHeader2);
                                        }                           
                                    }  
                                    
                                    if(x==3){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("-");
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("Gatos de operación")));
                                        tabla.addCell(pdfCellHeader);
                                        PdfPCell pdfCellHeader2 = new PdfPCell();    
                                        if(gastosDeOperacion<0){ // solo por si se da el extraño caso de ser negativo XD                                            
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("($ "+(gastosDeOperacion*-1)+")")));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        else{
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+gastosDeOperacion)));
                                             tabla.addCell(pdfCellHeader2);
                                        }                           
                                    }
                                    
                                    if(x==4){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("=");
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("Utilidad de la operación")));
                                        tabla.addCell(pdfCellHeader);
                                        PdfPCell pdfCellHeader2 = new PdfPCell();    
                                        if(utilidadDeOperacion<0){ // solo por si se da el extraño caso de ser negativo XD                                            
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("($ "+(utilidadDeOperacion*-1)+")")));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        else{
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+utilidadDeOperacion)));
                                             tabla.addCell(pdfCellHeader2);
                                        }                           
                                    }
                                    
                                     if(x==5){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("-");
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("Gastos financieros")));
                                        tabla.addCell(pdfCellHeader);
                                        PdfPCell pdfCellHeader2 = new PdfPCell();    
                                        if(gastosFinancieros<0){ // solo por si se da el extraño caso de ser negativo XD                                            
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("($ "+(gastosFinancieros*-1)+")")));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        else{
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+gastosFinancieros)));
                                             tabla.addCell(pdfCellHeader2);
                                        }                           
                                    }

                                      if(x==6){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("=");
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("Utilidad antes de impuesto")));
                                        tabla.addCell(pdfCellHeader);
                                        PdfPCell pdfCellHeader2 = new PdfPCell();    
                                        if(utilidadAntDeImpuestos<0){ // solo por si se da el extraño caso de ser negativo XD                                            
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("($ "+(utilidadAntDeImpuestos*-1)+")")));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        else{
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+utilidadAntDeImpuestos)));
                                             tabla.addCell(pdfCellHeader2);
                                        }                           
                                    }
                                     
                                     if(x==7){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("-");
                                        pdfCellHeader.addElement(new Paragraph(new Phrase("Impuesto (renta)")));
                                        tabla.addCell(pdfCellHeader);      
                                        PdfPCell pdfCellHeader2 = new PdfPCell();    
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+renta)));
                                             tabla.addCell(pdfCellHeader2);                                                                
                                    } 
                                      
                                    if(x==8){
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        tabla.addCell("=");
                                           
                                        if(utilidadneta<0){ // solo por si se da el extraño caso de ser negativo XD
                                            pdfCellHeader.addElement(new Paragraph(new Phrase("Deficit")));
                                            tabla.addCell(pdfCellHeader);
                                            PdfPCell pdfCellHeader2 = new PdfPCell(); 
                                             pdfCellHeader2.addElement(new Paragraph(new Phrase("($ "+(utilidadneta*-1)+")")));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        else{
                                            pdfCellHeader.addElement(new Paragraph(new Phrase("Utilidad neta")));
                                            tabla.addCell(pdfCellHeader);
                                            PdfPCell pdfCellHeader2 = new PdfPCell(); 
                                            pdfCellHeader2.addElement(new Paragraph(new Phrase("$ "+utilidadneta)));
                                             tabla.addCell(pdfCellHeader2);
                                        }
                                        break; //salgo del while, ya todo el trabajo esta hecho
                                    }    
                                
                    x++;
                }

            documento.add(tabla2);//ingreso las tablas al documento
            documento.add(tabla);
            //cierro el documento pdf
           documento.close();
}
  }  



