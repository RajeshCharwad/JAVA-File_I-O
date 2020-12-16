import java.lang.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

class fileHandling
{
   public String fileName;
   public BufferedWriter fout;
   public FileInputStream fin;
   public String fData;
   public File fName;
   public fileHandling(String name, String data)
   {
      fileName = name;
      fData = data;
      fout = null;
      fin = null;
      fName = new File(fileName);
   }

   void fileAppend() throws IOException
   {
      fout = new BufferedWriter(new FileWriter(fileName,true));
      fout.write(" "+fData);
      fout.close();
      fin = new FileInputStream(fileName);
      String str = new String(fin.readAllBytes());
      System.out.println("Data of modified file " + fName.getName() + ": " + str);
      System.out.println("Size of modified file "+ fName.getName() + ": " + str.length() + " Bytes");
   }

   void fileOverride() throws IOException
   {
      fout = new BufferedWriter(new FileWriter(fileName));
      fout.write(fData);
      fout.close();
      fin = new FileInputStream(fileName);
      String str = new String(fin.readAllBytes());
      System.out.println("Data of override file " + fName.getName() + ": " + str);
      System.out.println("Size of override file "+ fName.getName() + ": " + str.length() + " Bytes");
   }

   void newFile() throws IOException
   {
      fout = new BufferedWriter(new FileWriter(fileName));
      fout.write(fData);
      fout.close();
      fin = new FileInputStream(fileName);
      String Data = new String(fin.readAllBytes());
      System.out.println("Data of new file " + fName.getName() + ": " + Data);
      System.out.println("Size of new file " + fName.getName() + ": " + Data.length() + " Bytes");
   }
}

class Main
{
   public static void main(String arg[])
   {
      Scanner sobj = new Scanner(System.in);
      System.out.println("Enter file name");
      String name = sobj.nextLine();
      System.out.println("Enter data to the file");
      String data = sobj.nextLine();
      File fName = new File(name);
      int choice;
      fileHandling fobj = new fileHandling(name,data);
      try{
         if(fName.exists())
         {
            System.out.println("File already exist");
            System.out.println("Please enter your choice");
            System.out.println("1 : Append" + "\n" + "2 : Override");
            choice = sobj.nextInt();
            
            if(choice == 1)
            {
               fobj.fileAppend();
            }
            else
            {
               fobj.fileOverride();
            }
         }
         else
         {
            if(fName.createNewFile())
            {
               System.out.println("New file created successfully");
               fobj.newFile();
            }
            else
            {
               System.out.println("Unable to create file");
            }
         }
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
   }
}