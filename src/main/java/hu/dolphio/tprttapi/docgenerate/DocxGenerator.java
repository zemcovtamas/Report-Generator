/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.dolphio.tprttapi.docgenerate;


import hu.dolphio.tprttapi.model.tp.UserStory;
import java.math.BigInteger;
import java.util.ArrayList;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;

/**
 *
 * @author admin
 */
public class DocxGenerator {
    
     private static WordprocessingMLPackage  wordMLPackage;
     private static ObjectFactory factory;
    
    public static void generateDocFromTp(ArrayList<UserStory> us) throws Docx4JException
    {
        ArrayList<UserStory> userStories = us;
        
    // Create the package
    wordMLPackage = WordprocessingMLPackage.createPackage();
    
    // Title
    wordMLPackage.getMainDocumentPart().addParagraphOfText("HETI JELENTÉS");
    wordMLPackage.getMainDocumentPart().addParagraphOfText("PROJECTNÉV");
    
        
    //
    Tbl table;
    Tr tableRow;
    
     // Content 1
    wordMLPackage.getMainDocumentPart().addParagraphOfText("1. Projekt adatai");
    
    //add table
    factory = Context.getWmlObjectFactory();
 
    //addRegularTableCell(tableRow, "Normal text");
        //addStyledTableCell(tableRow, "Bold text", true, null);
        //addStyledTableCell(tableRow, "Bold large text", true, "40");    
    
    
        table = factory.createTbl();
        
        
        
       
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Vonatkozó időszak:", true, null);
        addStyledTableCell(tableRow, "", false, null);
      
        table.getContent().add(tableRow);
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Ütemezés:", true, null);
        addStyledTableCell(tableRow, "", false, null);
        
        table.getContent().add(tableRow);
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Projekt tagok", true, null);
        
        table.getContent().add(tableRow);
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Név:", true, null);
        addStyledTableCell(tableRow, "Szerepkör", false, null);
        
        table.getContent().add(tableRow);
        
        for (UserStory userStory : userStories) {
       
        }
        
        addBorders(table);
 
        wordMLPackage.getMainDocumentPart().addObject(table);
 
    //contnet 1 end
        
         // Content 2
    wordMLPackage.getMainDocumentPart().addParagraphOfText("2.Vonatkozó időszak általános szöveges összefoglalása");
    
   
 
    //contnet 2 end
        
         // Content 3
    wordMLPackage.getMainDocumentPart().addParagraphOfText("3.Nyitott kérdések, problémák");
    
    //add table
    factory = Context.getWmlObjectFactory();
 
    //addRegularTableCell(tableRow, "Normal text");
        //addStyledTableCell(tableRow, "Bold text", true, null);
        //addStyledTableCell(tableRow, "Bold large text", true, "40");    
    
    
    table = factory.createTbl();
        
        
        
       
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Kérdés, probléma", true, null);
        addStyledTableCell(tableRow, "Tervezett kezelés", true, null);
        addStyledTableCell(tableRow, "Felelős", true, null);
        addStyledTableCell(tableRow, "Határidő", true, null);
     
        
        table.getContent().add(tableRow);
        
        for (UserStory userStory : userStories) {
       
        }
        
        addBorders(table);
 
        wordMLPackage.getMainDocumentPart().addObject(table);
 
    //contnet 3 end
        
         // Content 4
    wordMLPackage.getMainDocumentPart().addParagraphOfText("4.Projekthez kapcsolódó egyéb feladatlista");
    
    //add table
    factory = Context.getWmlObjectFactory();
 
    //addRegularTableCell(tableRow, "Normal text");
        //addStyledTableCell(tableRow, "Bold text", true, null);
        //addStyledTableCell(tableRow, "Bold large text", true, "40");    
    
    
    table = factory.createTbl();
        
       
        
       
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Feladat:", true, null);
        addStyledTableCell(tableRow, "Felelős:", true, null);
        addStyledTableCell(tableRow, "Határidő", true, null);
     
        
        table.getContent().add(tableRow);
        
        for (UserStory userStory : userStories) {
            
        }
        
        addBorders(table);
 
        wordMLPackage.getMainDocumentPart().addObject(table);
 
    //contnet 4 end
        
         // Content 5
    wordMLPackage.getMainDocumentPart().addParagraphOfText("5.Az aktuális időszakra tervezett feladatok");
    
    //add table
    factory = Context.getWmlObjectFactory();
 
    //addRegularTableCell(tableRow, "Normal text");
        //addStyledTableCell(tableRow, "Bold text", true, null);
        //addStyledTableCell(tableRow, "Bold large text", true, "40");    
    
    
    table = factory.createTbl();
        
        
        
       
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Feladat:", true, null);
        addStyledTableCell(tableRow, "Felelős:", true, null);
        addStyledTableCell(tableRow, "Státusz:", true, null);
     
        
        table.getContent().add(tableRow);
        
        for (UserStory userStory : userStories) {
       
            
         if (userStory.getEntityState().equals("Planned") || userStory.getEntityState().equals("In Progress") || userStory.getEntityState().equals("In Testing") || userStory.getEntityState().equals("Done")){  
        tableRow = factory.createTr();
        addRegularTableCell(tableRow, userStory.getName() );
        addRegularTableCell(tableRow, userStory.getResponsible() );
        addRegularTableCell(tableRow, userStory.getStatus() );
        
        
        table.getContent().add(tableRow);
         }
        
        }
        
        addBorders(table);
 
        wordMLPackage.getMainDocumentPart().addObject(table);
 
    //contnet 5 end
        
         // Content 6
    wordMLPackage.getMainDocumentPart().addParagraphOfText("6.A következő időszakra tervezett feladatok");
    
    //add table
    factory = Context.getWmlObjectFactory();
 
    //addRegularTableCell(tableRow, "Normal text");
        //addStyledTableCell(tableRow, "Bold text", true, null);
        //addStyledTableCell(tableRow, "Bold large text", true, "40");    
    
    
    table = factory.createTbl();
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Feladat:", true, null);
        addStyledTableCell(tableRow, "Felelős:", true, null);
        addStyledTableCell(tableRow, "Státusz:", true, null);
     
        
        table.getContent().add(tableRow);
        
        for (UserStory userStory : userStories) {
         if (userStory.getEntityState().equals("Estimated") || userStory.getEntityState().equals("Ac Done") || userStory.getEntityState().equals("In Iteration") || userStory.getEntityState().equals("To Do (Open)")){  
        tableRow = factory.createTr();
        addRegularTableCell(tableRow, userStory.getName() );
        addRegularTableCell(tableRow, userStory.getResponsible() );
        addRegularTableCell(tableRow, userStory.getStatus() );
        
        
        table.getContent().add(tableRow);
         }
        }
        
        addBorders(table);
 
        wordMLPackage.getMainDocumentPart().addObject(table);
 
    //contnet 6 end
    
    
    // Content 7
    wordMLPackage.getMainDocumentPart().addParagraphOfText("7.Ciklusra tervezett eredménytermékek");
    
    //add table
    factory = Context.getWmlObjectFactory();
 
    //addRegularTableCell(tableRow, "Normal text");
        //addStyledTableCell(tableRow, "Bold text", true, null);
        //addStyledTableCell(tableRow, "Bold large text", true, "40");    
    
    
    table = factory.createTbl();
        
      
        
       
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Feladat / termékek", true, null);
        addStyledTableCell(tableRow, "Felelős", true, null);
        addStyledTableCell(tableRow, "Határidő", true, null);
        addStyledTableCell(tableRow, "Státusz", true, null);
     
        
        table.getContent().add(tableRow);
        
        for (UserStory userStory : userStories) {
       
        }
        
        addBorders(table);
 
        wordMLPackage.getMainDocumentPart().addObject(table);
 
    //contnet 7 end
    
     // Content 8
    wordMLPackage.getMainDocumentPart().addParagraphOfText("8.Kockázatok, kritikus tényezők");
    
    //add table
    factory = Context.getWmlObjectFactory();
 
    //addRegularTableCell(tableRow, "Normal text");
        //addStyledTableCell(tableRow, "Bold text", true, null);
        //addStyledTableCell(tableRow, "Bold large text", true, "40");    
    
    
    table = factory.createTbl();
        
      
        
       
        
        tableRow = factory.createTr();
 
        addStyledTableCell(tableRow, "Kockázat", true, null);
        addStyledTableCell(tableRow, "Valószínűség", true, null);
        addStyledTableCell(tableRow, "Hatás", true, null);
        addStyledTableCell(tableRow, "Kockázat kezelése", true, null);
        addStyledTableCell(tableRow, "Felelős", true, null);
        addStyledTableCell(tableRow, "Státusz", true, null);
     
        
        table.getContent().add(tableRow);
        
        for (UserStory userStory : userStories) {
       
        }
        
        addBorders(table);
 
        wordMLPackage.getMainDocumentPart().addObject(table);
 
    //contnet 8 end
    
    
    
     // Save it
    wordMLPackage.save(new java.io.File("C:\\Users\\zemcov.tamas\\Desktop\\test\\docx4jtest.docx") );

    
    
    
    }
    
    
    
    

    private static void addTableCell(Tr tableRow, String content) {
    Tc tableCell = factory.createTc();
    tableCell.getContent().add(
        wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
    tableRow.getContent().add(tableCell);
    }
    
    private static void addStyledTableCell(Tr tableRow, String content,
                        boolean bold, String fontSize) {
        Tc tableCell = factory.createTc();
        addStyling(tableCell, content, bold, fontSize);
        tableRow.getContent().add(tableCell);
    }
    
     private static void addStyling(Tc tableCell, String content,
                    boolean bold, String fontSize) {
        P paragraph = factory.createP();
 
        Text text = factory.createText();
        text.setValue(content);
 
        R run = factory.createR();
        run.getContent().add(text);
 
        paragraph.getContent().add(run);
 
        RPr runProperties = factory.createRPr();
        if (bold) {
            addBoldStyle(runProperties);
        }
 
        if (fontSize != null && !fontSize.isEmpty()) {
            setFontSize(runProperties, fontSize);
        }
 
        run.setRPr(runProperties);
 
        tableCell.getContent().add(paragraph);
    }
     
     private static void setFontSize(RPr runProperties, String fontSize) {
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(fontSize));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
    }
    
      private static void addBoldStyle(RPr runProperties) {
        BooleanDefaultTrue b = new BooleanDefaultTrue();
        b.setVal(true);
        runProperties.setB(b);
    }
      
       private static void addRegularTableCell(Tr tableRow, String content) {
        Tc tableCell = factory.createTc();
        tableCell.getContent().add(
            wordMLPackage.getMainDocumentPart().createParagraphOfText(
                content));
        tableRow.getContent().add(tableCell);
    }
    
       private static void addBorders(Tbl table) {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);
 
        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);
    }
}
