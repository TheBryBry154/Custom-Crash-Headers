package net.bry.cch.newheaders;

import net.bry.cch.CustomCrashHeaders;
import net.bry.cch.parser.CustomHeaderParser;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

public class NewCrashReportHeader {
    public static final Path yapperTextPath = Path.of(FMLPaths.GAMEDIR.get() + "\\cch.txt");
    public static boolean fileExists = false;

    public static File getOrCreateYapperFile() throws IOException {
        if(!Files.exists(yapperTextPath)){

            CustomCrashHeaders.LOGGER.info("No cch file found, creating one at: {} with name: {}",yapperTextPath,customHeaderFileAttributes.name());
            Files.writeString(yapperTextPath, CustomHeaderParser.defaultFileContents());
            fileExists = true;
            return yapperTextPath.toFile();
        }
        else { fileExists = true;
             return yapperTextPath.toFile();
        }
    }

    public static void newCrashReportHeader(StringBuilder stringBuilder) throws IOException {
        if (CustomHeaderParser.isRandom) stringBuilder.append(CustomHeaderParser.randomYapper());
        else stringBuilder.append(CustomHeaderParser.staticYapper());
    }

    static FileAttribute customHeaderFileAttributes = new FileAttribute() {
        @Override
        public String name() {
            return "cch.txt";
        }

        @Override
        public Object value() {
            return null;
        }
    };
}
