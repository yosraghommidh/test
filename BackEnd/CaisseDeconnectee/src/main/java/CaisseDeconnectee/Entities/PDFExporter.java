package CaisseDeconnectee.Entities;

import java.awt.Color;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PDFExporter {
	private HrGenDebt hr ;
	
	private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Facture", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("_________", font));
        table.addCell(cell);

        //table.addCell("reference");
        //table.addCell(hr.getDeb_refe());
       // table.addCell("adresse");
        //table.addCell(hr.getAdresse());
	}
	   private void writeTableData(PdfPTable table) {
		 table.addCell("reference");
	       table.addCell(hr.getDebrefe());
	        table.addCell("adresse");
	        table.addCell(hr.getAdresse());
	    }
        public void export(HttpServletResponse response) 
        		throws DocumentException, IOException
        {
        	//String url = "C:\\Users\\User\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\CaisseDeconnectee\\src\\main\\java\\CaisseDeconnectee\\Img" ;
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
             
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            font.setColor(Color.BLUE);
            Image img = Image.getInstance(getClass().getClassLoader().getResource("logoarabsoft.png"));
            Paragraph p = new Paragraph("Facture", font);
            p.setAlignment(Paragraph.ALIGN_CENTER);
             //Image a = Image.getInstance(url);
             document.add(img);
            document.add(p);
             
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100f);
            table.setWidths(new float[] {1.5f, 3.5f});
            table.setSpacingBefore(10);
             
            writeTableHeader(table);
            writeTableData(table);
            document.add(table);
             
            document.close();
             
        }
         
	}
     


