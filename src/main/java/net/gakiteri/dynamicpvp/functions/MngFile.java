package net.gakiteri.dynamicpvp.functions;

import java.io.File;

import static org.bukkit.Bukkit.getLogger;

public class MngFile {

    /** DIRECTORY CREATION **/
    public static File Path(File path) {
        try {
            // Checks if file/dir exists
            if (!path.exists()) {
                // Checks whether its a file of not
                if (path.toString().contains(".")) {
                    path.createNewFile();
                    getLogger().info("File created -> " + path.getName());
                } else {
                    path.mkdir();
                    getLogger().info("Directory created -> " + path.getName());
                }
            }
            return path;
        } catch (Exception e) {
            getLogger().info("Couldn't create directory or file");
        }
        return null;
    }

}
