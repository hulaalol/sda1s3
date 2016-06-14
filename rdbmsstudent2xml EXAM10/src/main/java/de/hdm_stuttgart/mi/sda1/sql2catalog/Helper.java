package de.hdm_stuttgart.mi.sda1.sql2catalog;

/**
 * Project wide helper methods
 *
 */
public class Helper {
   /**
    * Write message to stderr and exit with given error code
    * @param errMsg
    * @param errorCode
    */
   public static void exitWithErrorMessage(final String errMsg, int errorCode) {
      System.err.println(errMsg);
      System.exit(errorCode);
   }

}
