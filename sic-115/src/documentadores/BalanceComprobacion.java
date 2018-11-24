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
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import contGeneral.ListaLibroDiario;
import contGeneral.libroDiario;
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
public class BalanceComprobacion {
    ListaLibroDiario list =new ListaLibroDiario();
    
    
    
    public void generarBalance(Date inicio, Date fin) throws FileNotFoundException, DocumentException, SQLException
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
        Rectangle two = new Rectangle(600,900);
        documento.setPageSize(two); //enviar tamaño al documento
        documento.setMargins(2,2,2,2); 
        documento.open(); //abrir para editar  
        //AGREGAR LA IMAGEN 
        try
        {
                Image foto = Image.getInstance("pingu.png");
                foto.scaleToFit(1150, 150);
                foto.setAlignment(Chunk.ALIGN_MIDDLE);
                documento.add(foto);
        }
            catch ( Exception e )
            {
                    e.printStackTrace();
            }
        
        //-----  Obteniendo la fecha   fechafin
        String fecha="";
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
               
               
        // obteniendo saldo de activos
                float saldoactivo=0;
                String sqlsaldoactivo="SELECT SUM(haber_cuenta)\n" +
                                    "FROM public.cuenta;";
               declaracion0= conexionCuentas.getConexion().prepareStatement(sqlsaldoactivo);
               res= declaracion0.executeQuery();
               
               res.next();
               saldoactivo= res.getFloat(1); //ALMACENO EL SALDO DE ACTIVO
                System.out.println(saldoactivo);
        //  obteniendo saldo de pasivos
               float saldopasivo=0;
                String sqlsaldopyc="SELECT SUM(debe_cuenta)\n" +
                                    "FROM public.cuenta;";
               declaracion0= conexionCuentas.getConexion().prepareStatement(sqlsaldopyc);
               res= declaracion0.executeQuery();
               res.next();
               saldopasivo=res.getFloat(1);//ALMACENO EL SALDO DEBE
               System.out.println(saldopasivo);

       //_______________________ABAJO CREO 3 INSTANCIAS QUE LE DA ESTILO A LAS LETRAS Y CONTIENEN LOS PARRAFOS_______________________________________________
     
        Paragraph parrafo = new Paragraph("                                                                                    ESTADO DE COMPROBACION",
                FontFactory.getFont("arial",   // fuente
                        10,                            // tamaño
                        Font.ITALIC                   ));
        
        //______________________________________________________________________    
        Paragraph parrafo2 = new Paragraph("                                                                                "+fecha,
                FontFactory.getFont("arial",   // fuente
                        9,                            // tamaño
                        Font.ITALIC                   ));
        //______________________________________________________________________
        Paragraph parrafo3 = new Paragraph("                                                    ",
                FontFactory.getFont("arial",   // fuente
                        9,                            // tamaño
                        Font.ITALIC                   ));
        
        //AGREGO LOS PARRAFOS
        documento.add(parrafo);    // estilo
        documento.add(parrafo2);    // estilo        
        documento.add(parrafo3);    // estilo
        

                ResultSet resultado1=null; 
                 
                PreparedStatement declaracion1= null;
              
                String sqlGeneral="SELECT idcuenta, nombrecuenta, debe_cuenta, haber_cuenta\n" + //BUSCANDO ACTIVO
                "  FROM public.cuenta where not (debe_cuenta=0 and haber_cuenta=0) order by idcuenta;"; //OBTENGO CUENTAS ACTIVOS

                declaracion1= conexionCuentas.getConexion().prepareStatement(sqlGeneral);
                resultado1= declaracion1.executeQuery();

                    
               
                        
                PdfPTable tabla = new PdfPTable(4); //cantidad de columnas
                tabla.setTotalWidth (new float [] {50,300,60,60});   //DEFINO EL TAMAÑO SE CULUMNAS
                tabla.setLockedWidth (true);
                
                PdfPTable tabla2 = new PdfPTable(4);
                tabla2.setTotalWidth (new float [] {50,300,60,60});
                tabla2.setLockedWidth (true); tabla2.addCell("COD"); tabla2.addCell("NOMBRE"); tabla2.addCell("DEBE"); tabla2.addCell("HABER"); 
                
                

                                        
                PdfPTable tabla3 = new PdfPTable(4);
                tabla3.setTotalWidth (new float [] {50,300,60,60});
                tabla3.setLockedWidth (true); 
                tabla3.addCell("TOTAL"); tabla3.addCell("- - -");
                
                
                tabla3.addCell(Float.toString(saldopasivo)); tabla3.addCell(Float.toString( saldoactivo)); 
                
               

                //________________________________________________________________________________________________________________________________________________________
                
                int x=0;
                
                while(x!=600) //para generar 400 celdas, este valor 400 solo lo meti son suficientes iteraciones
                {
                            if(resultado1.next()){

                                for(int d=1; d<5;d++){                           //agrego 4 celdas para los activos
                                        PdfPCell pdfCellHeader = new PdfPCell();
                                        pdfCellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                        pdfCellHeader.setUseVariableBorders(true);
                                        Phrase frase = new Phrase(resultado1.getString(d));
                                        Font fuente= new Font();
                                        fuente.setSize(8);
                                        frase.setFont(fuente);
                                        pdfCellHeader.addElement(new Paragraph(frase));
                                        tabla.addCell(pdfCellHeader);
                                }
                            } 
                    x++;
                }
                
           
//____________________________________________________________________________________________________________________________
//____________________________________________________________________________________________________________________________              

            documento.add(tabla2);//ingreso las tablas al documento
            documento.add(tabla);
            documento.add(tabla3);
            
           documento.close();
                
                
                
            }
            
    
}
