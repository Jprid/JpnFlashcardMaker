package com.cmdline;

public class Controller {
    private static void printUsage(){
        System.out.println("please enter a filename");
        System.out.println("in this format: ");
        System.out.println("</PATH/TO/PRGM/PGRM> [arg]");
    }

    public static void main(String[] args) {
        if(args.length == 0){
            printUsage();
        }
        else {
            String file = args[0];
//            System.out.println(test);
            // pass the file path
            // read in from file
            // use file's contents to generate list of vocab words and their readings and definitions
            //
        }
    }
}
