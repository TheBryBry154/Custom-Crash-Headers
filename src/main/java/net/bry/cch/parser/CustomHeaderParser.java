package net.bry.cch.parser;

import net.bry.cch.newheaders.NewCrashReportHeader;
import net.minecraft.Util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class CustomHeaderParser {

   public static final Path headerPath = NewCrashReportHeader.yapperTextPath;
   public static boolean isRandom;


    public static String defaultFileContents() {
       return  "# -------------------------------------------------------\n" +
               "# MMMSoDNONAFOINOINOEIOIONFAONDOifnAOFNPDPNIFOIJINOINOI  \n" +
               "# -------------------------------------------------------\n" +
               "*>Random = false                                         \n" +
               "oh dear";
    }

    public static List<String> parseUsableFromFile() throws IOException {
        List<String> usableLines = new ArrayList<>();

        for(String line : CustomHeaderParser.allReadLines()){
         if(!line.startsWith("*>"))  usableLines.add(line + "\n");
         if(line.startsWith("*>Randomize")){
             if (line.contains("True"))CustomHeaderParser.isRandom = true;
             else CustomHeaderParser.isRandom = false;
            }
        }
        return usableLines;
    }

    public static String staticYapper() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String line : parseUsableFromFile()){
            if(!line.startsWith("#")) builder.append(line);
        }
        return builder.toString();
    }

    public static String randomYapper() throws IOException {
        return CustomHeaderParser.parseUsableFromFile().get((((int) (Util.getMillis() % CustomHeaderParser.allReadLines().size()))));
    }

    public static final List<String> allReadLines() throws IOException{

        return readAllLines(CustomHeaderParser.headerPath);

    }

}
