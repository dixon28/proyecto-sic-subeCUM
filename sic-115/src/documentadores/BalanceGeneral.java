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
public class BalanceGeneral {
    
    
    
   public void generarBalanceGeneral(Date inicio,Date fin) throws FileNotFoundException, DocumentException, SQLException
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
        Rectangle two = new Rectangle(900,600);
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
               
               
        // obteniendo saldo de activos
                float saldoactivo=0;
                String sqlsaldoactivo="SELECT SUM(debe_cuenta-haber_cuenta)\n" +
                                    "FROM public.cuenta \n" +
                                    "where cast(idcuenta AS text) SIMILAR TO  '1%';";
               declaracion0= conexionCuentas.getConexion().prepareStatement(sqlsaldoactivo);
               res= declaracion0.executeQuery();
               
               res.next();
               saldoactivo= res.getFloat(1); //ALMACENO EL SALDO DE ACTIVO
                System.out.println(saldoactivo);
        //  obteniendo saldo de pasivos
               float saldopasivo=0;
                String sqlsaldopyc="SELECT SUM(debe_cuenta-haber_cuenta)\n" +
                                    "FROM public.cuenta \n" +
                                    "where cast(idcuenta AS text) SIMILAR TO  '2%' or cast(idcuenta AS text) SIMILAR TO  '3%';";
               declaracion0= conexionCuentas.getConexion().prepareStatement(sqlsaldopyc);
               res= declaracion0.executeQuery();
               res.next();
               saldopasivo=res.getFloat(1);//ALMACENO EL SALDO DE PASIVO Y CAPITAL
               System.out.println(saldopasivo);

       //_______________________ABAJO CREO 3 INSTANCIAS QUE LE DA ESTILO A LAS LETRAS Y CONTIENEN LOS PARRAFOS_______________________________________________
     
        Paragraph parrafo = new Paragraph("Balance General",
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
        
        
        
                ResultSet resultado1=null; 
                ResultSet resultado2=null; 
                PreparedStatement declaracion1= null;
                PreparedStatement declaracion2= null;
                String sqlGeneral="SELECT idcuenta, nombrecuenta, debe_cuenta, haber_cuenta\n" + //BUSCANDO ACTIVO
                "  FROM public.cuenta\n" +
                "  where cast(idcuenta AS text) SIMILAR TO  '1%' and not (debe_cuenta=0 and haber_cuenta=0) order by idcuenta;"; //OBTENGO CUENTAS ACTIVOS
                
                String sqlGeneral2="SELECT idcuenta,nombrecuenta, debe_cuenta, haber_cuenta\n" + //BISCANDO PASIVO Y CAPITAL
                "  FROM public.cuenta\n" +
                "  where (cast(idcuenta AS text) SIMILAR TO  '2%' or cast(idcuenta AS text) SIMILAR TO  '3%') and not (debe_cuenta=0 and haber_cuenta=0) order by idcuenta;"; //OBTENGO CUENTAS PASIVAS Y CAPITAL
                
                declaracion1= conexionCuentas.getConexion().prepareStatement(sqlGeneral);
                declaracion2= conexionCuentas.getConexion().prepareStatement(sqlGeneral2);
                resultado1= declaracion1.executeQuery();
                resultado2= declaracion2.executeQuery();

                PdfPTable tabla = new PdfPTable(8); //cantidad de columnas
                tabla.setTotalWidth (new float [] {60,200,75,75,60,200,75,75});   //DEFINO EL TAMAÑO SE CULUMNAS
                tabla.setLockedWidth (true);
                
                PdfPTable tabla2 = new PdfPTable(8);
                tabla2.setTotalWidth (new float [] {60,200,75,75,60,200,75,75});
                tabla2.setLockedWidth (true); tabla2.addCell("COD"); tabla2.addCell("NOMBRE"); tabla2.addCell("DEBE"); tabla2.addCell("HABER");  tabla2.addCell("COD"); tabla2.addCell("NOMBRE"); tabla2.addCell("DEBE"); tabla2.addCell("HABER");
                
                PdfPTable tabla3 = new PdfPTable(8);
                tabla3.setTotalWidth (new float [] {60,200,75,75,60,200,75,75});
                tabla3.setLockedWidth (true); 
                tabla3.addCell("SALDO"); tabla3.addCell("- - -");
                
                if(saldoactivo<0){
                tabla3.addCell("- - -"); tabla3.addCell(Float.toString(saldoactivo*-1));   //agrego una celda a la tabla
                
                }
                else{
                tabla3.addCell(Float.toString(saldoactivo));  tabla3.addCell("- - -"); 
                }
                
                tabla3.addCell("SALDO"); tabla3.addCell("- - -"); 
                
                if(saldopasivo<0){
                tabla3.addCell("- - -");tabla3.addCell(Float.toString(saldopasivo*-1));
                
                }
                else{
                tabla3.addCell(Float.toString(saldopasivo));tabla3.addCell("- - -"); 
                }

                //________________________________________________________________________________________________________________________________________________________
                int finactivos=0;
                int finpasivos=0; //para saber cuando parar de agregar celdas
                int x=0;
                
                while(x!=400) //para generar 400 celdas, este valor 400 solo lo meti son suficientes iteraciones
                {
                    if(finactivos==0){
                            
                            if(resultado1.next()){
                                
                                
                                
                                for(int d=1; d<5;d++){                           //agrego 4 celdas para los activos
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        pdfCellHeader.addElement(new Paragraph(new Phrase(resultado1.getString(d))));
                                        tabla.addCell(pdfCellHeader);
                                }

                            } 
                                        else
                                            finactivos=1;

                     }
                    
                    
                    if(finpasivos==0){
                        if(finactivos==1){
                            tabla.addCell("-");
                            tabla.addCell("-");
                            tabla.addCell("-");
                            tabla.addCell("-");
                        }
                        if(resultado2.next()){                        
                          for(int d=1; d<5;d++){                                //agrego 4 celdas para pasivos y capital
                                    PdfPCell pdfCellHeader = new PdfPCell();
                                    pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    pdfCellHeader.addElement(new Paragraph(new Phrase(resultado2.getString(d))));
                                    tabla.addCell(pdfCellHeader);
                            }
                           
                        }
                        else 
                      
                            finpasivos=1;     
                    }
                    
                    if(finpasivos==1){
                            tabla.addCell("-");
                            tabla.addCell("-");
                            tabla.addCell("-");
                            tabla.addCell("-");
                            if(finactivos==1)
                                break;
                    }
                        
 
                    x++;
                }
                

            documento.add(tabla2);//ingreso las tablas al documento
            documento.add(tabla);
            documento.add(tabla3);
            
           documento.close();
           }
    
    
}
