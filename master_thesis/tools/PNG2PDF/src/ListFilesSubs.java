// -----------------------------------------------------------------------------
// ListFilesSubs.java
// -----------------------------------------------------------------------------

/*
 * =============================================================================
 * Copyright (c) 1998-2005 Jeffrey M. Hunter. All rights reserved.
 *
 * All source code and material located at the Internet address of
 * http://www.idevelopment.info is the copyright of Jeffrey M. Hunter, 2005 and
 * is protected under copyright laws of the United States. This source code may
 * not be hosted on any other site without my express, prior, written
 * permission. Application to host any of the material elsewhere can be made by
 * contacting me at jhunter@idevelopment.info.
 *
 * I have made every effort and taken great care in making sure that the source
 * code and other content included on my web site is technically accurate, but I
 * disclaim any and all responsibility for any loss, damage or destruction of
 * data or any other property which may arise from relying on it. I will in no
 * case be liable for any monetary damages arising from such loss, damage or
 * destruction.
 *
 * As with any code, ensure to test this code in a development environment
 * before attempting to run it in production.
 * =============================================================================
 */

   import java.io.File;
   import java.io.FilenameFilter;
   import java.io.FileFilter;

   /**
* -----------------------------------------------------------------------------
* This program demonstrates how to list the file or subdirectories in a
* given directory
*
* @version 1.0
* @author  Jeffrey M. Hunter  (jhunter@idevelopment.info)
* @author  http://www.idevelopment.info
* -----------------------------------------------------------------------------
*/

   public class ListFilesSubs {


   /**
    * Used to list the files / subdirectories in a given directory.
    * @param dirName Directory to start listing from
    */
   private static void doSimpleFileListing(String dirName) {

       System.out.println();
       System.out.println("Simple file listing...");
       System.out.println("----------------------");

       File dir = new File(dirName);

       String[] children = dir.list();

       printFiles(children, dirName);

   }


   /**
    * Used to list the files / subdirectories in a given directory and also
    * provide a filter class.
    * @param dirName Directory to start listing from
    * @param ff  A string that can be used to filter out files from the
    *            returned list of files. In this example, the String
    *            values is used to only return those values that start
    *            with the given String.
    */
   private static void doFileFilterListing(String dirName, String ff) {

       System.out.println("Filter file listing...");
       System.out.println("----------------------");

       final String fileFilter = ff;

       File           dir     = new File(dirName);
       FilenameFilter filter  = null;

       if (fileFilter != null) {

           // It is also possible to filter the list of returned files.
           // This example uses the passed in String value (if any) to only
           // list those files that start with the given String.
           filter = new FilenameFilter() {
               public boolean accept(File dir, String name) {
                   return name.startsWith(fileFilter);
               }
           };
       }

       String[] children = dir.list(filter);

       printFiles(children, dirName);

   }


   /**
    * Used to list the files / subdirectories in a given directory and also
    * provide a filter class that only lists the directories.
    * @param dirName Directory to start listing from
    */
   private static void doFileFilterDirectoryListing(String dirName) {

       System.out.println("Filter Directory listing...");
       System.out.println("---------------------------");

       File dir = new File(dirName);

       // The list of files can also be retrieved as File objects. In this
       // case, we are just listing all files in the directory. For the sake
       // of this example, we will not print them out here.
       File[] files = (new File(dirName)).listFiles();

       // This filter only returns directories
       FileFilter dirFilter = new FileFilter() {
           public boolean accept(File dir) {
               return dir.isDirectory();
           }
       };

       files = dir.listFiles(dirFilter);

       for (int i=0; i<files.length; i++) {
           System.out.println("[D] : " + files[i]);
       }
       System.out.println();


   }


   /**
    * Utility method to print the list of files to the terminal
    * @param children A String array of the file names to print out
    * @param dirName  The given directory to start the listing at.
    */
   private static void printFiles(String[] children, String dirName) {

       if (children == null) {
           System.out.println("Error with " + dirName);
           System.out.println("Either directory does not exist or is not a directory");
       } else {
           for (int i=0; i<children.length; i++) {
               // Get filename of file or directory
               String filename = children[i];
               if ((new File(dirName + File.separatorChar + filename)).isDirectory()) {
                   System.out.print("[D] : ");
               } else {
                   System.out.print("[F] : ");
               }
               System.out.println(dirName + File.separatorChar + filename);
           }
       }
       System.out.println();

   }

   /**
    * Sole entry point to the class and application.
    * @param args Array of String arguments.
    */
   public static void main(String[] args) {

       // Call the simple file listing method
       doSimpleFileListing("new_dir");

       // Now do the file listing but pass in a String to filter the file list
       if (args.length == 0) {
           doFileFilterListing("new_dir", null);
       } else {
           doFileFilterListing("new_dir", args[0]);
       }

       // Now do another example that only prints out the directories
       doFileFilterDirectoryListing("new_dir");

   }
   }
