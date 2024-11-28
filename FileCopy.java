/**
 * This program take two arguments that are passed into the commandline or through idea's parameters
 *  and copies the items from the source (1st argument) to the copy (2nd argument).
 *
 *  Author: Makenna Worley
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        if (args.length != 2) { //checks if there are only two arguments passed
            System.out.println("Please provide the Source file and Copy file");
            return;
        }

        //Saves the two arguments into variables
        String source = args[0];
        String copy = args[1];

        if (source.equals(copy)) { //checks if the files are the same
            System.out.println("Source and Copy file are the same file, please use different names");
            return;
        }

        try { //tries to create streams from the files
            FileInputStream sourceStream = new FileInputStream(source);
            FileOutputStream copyStream = new FileOutputStream(copy);

            long startTime = System.currentTimeMillis();

            //data array made and data is collected
            byte[] data = new byte[256];
            int dataString = sourceStream.read(data);

            while(dataString != -1) { //until no data was grabbed from the source
                copyStream.write(data, 0, dataString);
                dataString = sourceStream.read(data);
            }

            //close the streams
            sourceStream.close();
            copyStream.close();

            //Success message
            System.out.println("File copied");
            System.out.println("Time: " + (System.currentTimeMillis() - startTime) + " ms.");

        //possible thrown errors
        } catch (FileNotFoundException e) { //cannot find ____ file
            System.out.println("Cannot find: " + e.getMessage());
        } catch (IOException e) { //java.io error occurred
            System.out.println("Error while copying " + source + " to " + copy);
        }

    }
}
