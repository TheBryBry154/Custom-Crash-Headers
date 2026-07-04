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
               "# Thanks for using Custom Crash Headers!                 \n" +
               "# this mod was really made in mind for people who might want to make a modpack, (for smps/servers or the sort)\n" +
               "# and want to add some info if a user's game crashes. (contact info/ where to ask for help)\n" +
               "# \n" +
               "# any bugs, questions, etc. should probably go to the github - https://github.com/TheBryBry154/Custom-Crash-Headers\n" +
               "# or message me on discord at thebrybrybrybrybrybrybrybry154 ig, idk i dont really have a discord server tbh\n" +
               "# anyways have fun, -Bry\n" +
               "# \n" +
               "# HOW TO USE: \n" +
               "# - anything with a pound (hashtag?) symbol in front of it is counted as a comment and will not be displayed in the crash message\n" +
               "# the *>Random setting below can be used to make a set of headers that will be picked at random.\n" +
               "# In this mode every line is counted as a separate entry\n" +
               "# -------------------------------------------------------\n" +
               "*>Random = false                                         \n\n" +
               "ruh roh\n" +
               " ";


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
