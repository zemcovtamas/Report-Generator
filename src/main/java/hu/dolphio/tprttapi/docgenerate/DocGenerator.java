/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.dolphio.tprttapi.docgenerate;

import hu.dolphio.tprttapi.model.tp.UserStory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.style.Font;

/**
 *
 * @author admin
 */
public class DocGenerator {
    
    private static final Font NUMBERED_SUBTITLE_FONT = Font.ARIAL_ROUNDED_MT_BOLD;
    
    public static void generateDocFromTp(ArrayList<UserStory> us)
    {
        
        IDocument myDoc = new Document2004();
        myDoc.encoding(Document2004.Encoding.UTF_8);
        
        //title 5 
        myDoc.addEle(Paragraph
                .withPieces(
                        ParagraphPiece
                                .with(" 5.Az aktuális időszakra tervezett feladatok")
                                .withStyle().font(NUMBERED_SUBTITLE_FONT).bold().fontSize("16"). create()).create());
                                
        myDoc.addEle(new BreakLine(1));
        
        myDoc.addEle(Paragraph
                .withPieces(
                        ParagraphPiece
                                .with("(Itt az adott, riportolt időszakra tervezett feladatokat és azok státuszát kell követni.)")
                                .withStyle().fontSize("11"). create()).create());
                                
        myDoc.addEle(new BreakLine(1));
        
        //table 5
        Table tb1 = new Table();
        tb1.addTableEle(TableEle.TH, "Feladat", "Felelős", "Státusz");

        for (UserStory userStory : us) {
            
            //tb1.addTableEle(TableEle.TD, userStory.getName(),userStory.getResponsible(),userStory.getStatus());
  
            
        }
        myDoc.addEle(tb1);
        myDoc.addEle(new BreakLine(1));
        
        //fájl kiírása
        File fileObj = new File("C:\\Users\\zemcov.tamas\\Desktop\\test\\WordUserstory.doc");
        
        
        PrintWriter writer = null;
         try {
         writer = new PrintWriter(fileObj);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
         }
    
         String myWord = myDoc.getContent();
         
         
         writer.println(myWord);
         writer.close();     
    }
    
}
