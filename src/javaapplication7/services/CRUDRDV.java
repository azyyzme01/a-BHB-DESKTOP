package javaapplication7.services;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javaapplication7.entites.rdv;
import javaapplication7.entites.service;
import javaapplication7.utils.connection;
import javax.swing.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CRUDRDV {
    public void ajouterRDV(rdv r,List<rdv> rdvs, Date date) {
        int count = 0;
        try {
            String req2 = "INSERT INTO `rdv`( `services_id`, `date`, `raison`, `heure`) VALUES (?,?,?,?)";
            PreparedStatement pst = new connection().getcnx().prepareStatement(req2);
            pst.setInt(1, r.getS().getId());
            pst.setDate(2, r.getDate());
            pst.setString(3, r.getRaison());
            pst.setString(4, r.getHeure());
            pst.executeUpdate();
            System.out.println("RDV ajouté\n");
            pst.close();
           
    for (rdv rd : rdvs) {
        if (rd.getDate().equals(date)) {
            count++;
        }
    }
    System.out.println("Il y a " + count + " rendez-vous qui vous precede pour la date " + date);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<rdv> afficherRDV(){
    List<rdv> rdvs = new ArrayList<>();

    try {
        String req = "SELECT * FROM `rdv`";
        PreparedStatement pst = new connection().getcnx().prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            Date date = rs.getDate("date");
            String raison = rs.getString("raison");
            int service_id = rs.getInt("services_id");
            String heure = rs.getString("heure");

            // Récupération du service associé au rdv
            CRUD p= new CRUD();
            service s = p.chercherServiceParId(service_id);

            rdv r = new rdv(id, date, raison, s, heure);
            rdvs.add(r);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    for (rdv r : rdvs) {
        System.out.println(r.getId() + " " + r.getDate() + " " + r.getRaison() + " " + r.getS().getType() + " " + r.getHeure());
    }

    return rdvs;
}
    public void modifierRDV(int id, rdv r) {
    try {
     
     String req = "UPDATE `rdv` SET `services_id` = ?, `date` = ?, `raison` = ?, `heure` = ? WHERE `id` = ?";
PreparedStatement pst = new connection().getcnx().prepareStatement(req);
pst.setInt(1, r.getS().getId());
pst.setDate(2, r.getDate());
pst.setString(3, r.getRaison());
pst.setString(4, r.getHeure());
pst.setInt(5, id);
pst.executeUpdate();
      
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    public void supprimerRDV(int rdv_id) {
    try {
        String req = "DELETE FROM `rdv` WHERE `id`=?";
        PreparedStatement pst = new connection().getcnx().prepareStatement(req);
        pst.setInt(1, rdv_id);
        pst.executeUpdate();
        System.out.println("Le RDV avec l'ID " + rdv_id + " a été supprimé avec succès.");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
   public List<rdv> chercherRdvParDate(List<rdv> rdvs, String heure) {
      List<rdv> resultat = new ArrayList<>();
    for (rdv r : rdvs) {
        if (r.getHeure().equals(heure)) {
            resultat.add(r);
        }
    }
    for (rdv r : resultat) {
        System.out.println(r.getId() + " - " + r.getDate() + " - " + r.getRaison() + " - " + r.getS().getType() + " - " + r.getHeure());
    }
    return resultat;
    
} 
  public List<rdv> trierRdvParDate(List<rdv> rdvs, boolean croissant) {
    Comparator<rdv> comparator = Comparator.comparing(rdv::getDate);
    if (!croissant) {
        comparator = comparator.reversed();
    }
    List<rdv> resultat = new ArrayList<>(rdvs);
    resultat.sort(comparator);
    return resultat;
}
  public static List<String> getTypesDeServices() {
        List<String> typesDeServices = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Connectez-vous à votre base de données ici en utilisant le driver JDBC approprié et vos informations de connexion
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            
            // Exécutez une requête SQL pour récupérer les types de services à partir de la table "service"
            String query = "SELECT DISTINCT type FROM service WHERE dispo = true";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            // Parcourez les résultats de la requête et ajoutez les types de services à la liste de typesDeServices
            while (rs.next()) {
                typesDeServices.add(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Fermez les objets ResultSet, Statement et Connection
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Retournez la liste de types de services récupérés à partir de la base de données
        return typesDeServices;
    }
  public Map<String, Integer> getStatistiquesRdvParTypeService() {
   Map<String, Integer> statistiques = new HashMap<>();

    try {
        String req = "SELECT s.type, COUNT(*) AS nb_rdv FROM rdv r JOIN service s ON r.services_id = s.id GROUP BY s.type";
        PreparedStatement pst = new connection().getcnx().prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String typeService = rs.getString("type");
            int nbRdv = rs.getInt("nb_rdv");
            statistiques.put(typeService, nbRdv);
        }

        pst.close();
        rs.close();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return statistiques;
}

public void genererPDF(List<rdv> rdvs) {
    try {
        // Création du document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        String path = "C:\\Users\\USER\\Downloads\\Liste_RDV.pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
        
        // Définition de la zone de la page
        Rectangle rect = new Rectangle(30, 40, 580, 840);
        writer.setBoxSize("art", rect);
        // Ouverture du document et ajout du contenu
        document.open();

        // Ajout de l'image
        Image logo = Image.getInstance("C:\\Users\\USER\\Desktop\\javaa\\JavaApplication7\\build\\src\\gui\\logo.PNG");
        logo.scaleAbsolute(100, 100);
        document.add(logo);

        // Ajout du titre
        
       Paragraph x = new Paragraph("Liste des rendez-vous");
x.setAlignment(Element.ALIGN_CENTER);
x.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.DARK_GRAY));
document.add(x);

        // Ajout des rendez-vous dans le document
       for (rdv r : rdvs) {
    Paragraph p = new Paragraph(r.getDate() + "    " + r.getRaison() + "     " + r.getS().getType() + "    " + r.getHeure()+"\n");
    p.setAlignment(Element.ALIGN_CENTER);
    p.setFont(FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
    document.add(p);
}
        // Dessin de la bordure
        PdfContentByte cb = writer.getDirectContent();
        cb.setColorStroke(BaseColor.LIGHT_GRAY);
        cb.setLineWidth(30);
        cb.rectangle(rect.getLeft()-10, rect.getBottom(), rect.getWidth(), rect.getHeight());
        cb.stroke();
Image FIN = Image.getInstance("C:\\Users\\USER\\Desktop\\javaa\\JavaApplication7\\build\\src\\gui\\BAE.PNG");
FIN.scaleAbsolute(200, 80);
PdfGState gs1 = new PdfGState();
gs1.setFillOpacity(0.2f); // Set transparency level (0.0 - 1.0)
PdfContentByte canvas = writer.getDirectContent();
canvas.saveState();
canvas.setGState(gs1);
FIN.setAlignment(Element.ALIGN_RIGHT);
document.add(FIN);
canvas.restoreState();
Image s = Image.getInstance("C:\\Users\\USER\\Desktop\\javaa\\JavaApplication7\\build\\src\\gui\\sing.jpg");
s.scaleAbsolute(60, 60);

s.setAlignment(Element.ALIGN_RIGHT);
document.add(s);

        // Fermeture du document
        document.close();
        System.out.println("Le fichier PDF a été généré avec succès.");
    } catch (FileNotFoundException ex) {
        System.err.println("Le fichier de sortie n'a pas été trouvé.");
        System.err.println(ex.getMessage());
    } catch (DocumentException ex) {
        System.err.println("Une erreur s'est produite lors de la création du document PDF.");
        System.err.println(ex.getMessage());
    } catch (IOException ex) {
        System.err.println("Une erreur s'est produite lors de la lecture de l'image.");
        System.err.println(ex.getMessage());
    }
}

public void generateStatistiquesRdvParTypeServicePDF() throws IOException, DocumentException {
    // Get the statistics data
    Map<String, Integer> statistiques = getStatistiquesRdvParTypeService();

    // Create a new PDF document in the Downloads folder
    String downloadDir = System.getProperty("user.home") + "\\Downloads\\";
    String fileName = downloadDir + "statistiques.pdf";
    com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 50, 50, 50, 50);
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
    document.open();

    // Create a new chart using JFreeChart
    PieDataset dataset = createDataset(statistiques);
    JFreeChart chart = ChartFactory.createPieChart("Statistiques des rendez-vous par type de service", dataset, false, true, false);

    // Set some properties of the chart
    chart.setBackgroundPaint(Color.white);
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setBackgroundPaint(Color.white);
    plot.setOutlinePaint(null);

    // Draw the chart onto the PDF document
    PdfContentByte contentByte = writer.getDirectContent();
    Graphics2D graphics2d = contentByte.createGraphics(PageSize.A4.getWidth(), PageSize.A4.getHeight());
    Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, PageSize.A4.getWidth(), PageSize.A4.getHeight());
    chart.draw(graphics2d, rectangle2d);

    // Close the document and save it
    graphics2d.dispose();
    document.close();
}

private PieDataset createDataset(Map<String, Integer> statistiques) {
    DefaultPieDataset dataset = new DefaultPieDataset();

    for (Entry<String, Integer> entry : statistiques.entrySet()) {
        String typeService = entry.getKey();
        int nbRdv = entry.getValue();
        dataset.setValue(typeService, nbRdv);
    }

    return dataset;
}


}