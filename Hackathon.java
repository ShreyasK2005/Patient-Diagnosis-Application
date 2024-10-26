import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Hackathon
{
  static String qu = "";
  static String an = "";
  static String name = "";
  static String gender = "";
  static String race = "";
  static double lH = 0;
  static int age = 0;
  static String y = "";
  static String risk = "";
  static String f = "";
  static String pF = "";
  static double bmi = 0;
  static double weight = 0;
  static String pK = "";
  static double height = 0;
  public static void main(String[] args) throws IOException
    {
       
      Hackathon g = new Hackathon("D.txt");
      String a = "";
      Scanner input = new Scanner(System.in);
      
      System.out.println("Will you be submitting a patient file?");
      f = input.nextLine();
      if(f.contains("yes"))
      {
        System.out.println("Type the name of the file");
        pF = input.nextLine();
        readFile(pF);  
      }
      else
      {
      System.out.println("What is your name?");
      name = input.nextLine();
      
      System.out.println("What is your age?");
      age = input.nextInt();
      
      input.nextLine();
      
      System.out.println("What is your gender?");
      gender = input.nextLine();
      
      
      System.out.println("Have you or any relative had any previous experience with a specific cancer? (Such as Multiple Myeloma)");
      y = input.nextLine();
      
      if(y.contains("yes"))
      {
        lH+=0.25;
      }
      
      if(age >= 60)
      {
       lH+=0.25;
      }
      
      if(gender.contains("male") || gender.contains("boy"))
      {
        lH+=0.25;
      }
      
      
      
       while(g.getCurrent().contains("?") == true)
       {
         addQ(g.getCurrent());
         System.out.println(g.getCurrent());
         a = input.next();
          if(a.equals("yes"))
          {
           addA("yes");
           g.pateintSelected(a);
           lH++;
          }
          else if(a.equals("no"))
          {
           addA("no");
           g.pateintSelected(a);
          }
        }
      }  
         
       if(lH >= 3)
         {
           System.out.println("You are at a very high risk of having Multiple Myeloma, please contact a local doctor immediately!");
           Info();
           risk = "Likely to have Multiple Myeloma";
         }
         else if(lH < 3 && lH > 1)
         {
           System.out.println("You have a low chance of obtaining a Multiple Myeloma, however, if these problems continue to occur please contact a local doctor immediately!");
           Info();
           risk = "Low chance of having Multiple Myeloma";
         }
         else if(lH <= 1)
         {
           System.out.println("You are not likely to be prone to Multiple Myeloma, however, if problems do arise please take the questionare again, as well as look at the information below");
           Info();
           risk = "Not likelky,seems to be in good health";
         }
       patientFile();
    }



  private BSTNode root;
    private BSTNode current;
    private String fileName;
  private class BSTNode 
  {
    public String val;
    public BSTNode yes, no;
   
    public BSTNode(String val) 
    {
      this.val = val;
      yes = no = null;
    }
   
    @Override
    public String toString() 
    { 
      return "" + this.val;
    }
  }

	public Hackathon(String fileName)
	{
      lH = 0;
      this.fileName = fileName;
	   Scanner i = new Scanner(fileName);
      try
      {
        i = new Scanner(new File(fileName));
        root = new BSTNode (i.nextLine());
        current = root;
        bT(current, i);
         
      }
      catch(FileNotFoundException E)
      {
        System.out.println("Error");
      }

	}
   

   
   public void bT(BSTNode s, Scanner a)
   {
     if(s.val.contains("?"))
     {
       s.yes = new BSTNode(a.nextLine());
       bT(s.yes, a);
       s.no = new BSTNode(a.nextLine());
       bT(s.no, a);
     }
   }
   

   
   public static void Info()
   {
     System.out.println("Helpful information about Multiple Myeloma treatments below!");
     System.out.println();
     System.out.println("How is it treated?");
     System.out.println("Multiple Myeloma is typically treated with a combination of therapies, including chemotherapy, radiation therapy, and immunotherapy.");
     System.out.println("The goal of each treatment is to destroy the cancer cells and prevent them from growing and spreading.");
     System.out.println();
     System.out.println("These treatments could include: ");
     System.out.println("Using drugs to kill the cancer cells, boosting the immune system to help fight infections, or even the use of x-ray beams to kill or shrink the cancer cells.");
     System.out.println("However, in some cases, a stem cell transplant or bone marrow transplant may be recommended to replace the damaged bone marrow with healthy cells");
     System.out.println("This can help the body produce healthy plasma cells and improve the chances of long-term remission.");
     System.out.println("Though a patient can also try lighter approaches such as medications to manage pain, nutritional support, and physical therapy to maintain strength and mobility.");
   }
   
   

	public String getCurrent()
	{

		return current.val; //replace
	}

	public void pateintSelected(String yesOrNo)
	{
		if(yesOrNo.toLowerCase().equals("yes"))
      {
        current = current.yes;
      }
      if(yesOrNo.toLowerCase().equals("no"))
      {
        current = current.no;
      }
	}
 
 
    
    public static void addQ (String q)
    {
      qu = qu + "\n" + q;
    }
    
    public static void addA (String a)
    {
      an = an + "\n" + a;
    }
    
    public static void patientFile()
    {
        String sPath = "/Users/shreyas/Desktop/";
        try 
        {
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           File p1 = new File(sPath + "" + name + ".txt");
           p1.createNewFile();
           FileWriter wr = new FileWriter(p1);
           wr.write(name + "\n" + age + "\n" + qu + "\n" + an + "\n" + risk);
           wr.flush();
           wr.close();
         }
        catch (Exception ex1) {
        }
    }
    
    public static void readFile(String fileName)
    {
      Scanner i = new Scanner(fileName);
      try
      {
        i = new Scanner(new File(fileName));
        name = i.nextLine();
        race = i.nextLine();
        age = Integer.parseInt(i.nextLine());
        String w = i.nextLine();
        if(w.contains("lbs"))
        {
         w = w.replace("lbs", "");   
         weight = Double.parseDouble(w) * 0.453592;
        }
        else
        {
         weight = Double.parseDouble(w);
        }
        height = Double.parseDouble(i.nextLine());
        personal(race, age, weight, height);
        while(i.hasNextLine())
        {
          String S = i.nextLine();
          familyHistory(S); 
          String d = i.nextLine();
          previousHistory(d); 
          if(S.contains("Has") || S.contains("has"))
          {
            lH++;
          }
        }
         
      }
      catch(FileNotFoundException E)
      {
        System.out.println("Error");
      }
    }
    
    public static void familyHistory(String s)
    {
     if(s.contains("Father"))
     {
      if(s.contains("MGUS") || s.contains("Monoclonal Gammopathy of Undetermined Significance") || s.contains("monoclonal gammopathy of undetermined significance"))
      {
        lH++;
      }
      if(s.contains("Multiple Myeloma") || s.contains("Solitary Plasmacytoma"))
      {
        lH++;
      }
     }
     else
     {
       if(s.contains("MGUS") || s.contains("Monoclonal Gammopathy of Undetermined Significance") || s.contains("monoclonal gammopathy of undetermined significance"))
      {
        lH+=0.25;
      }
      if(s.contains("Multiple Myeloma") || s.contains("Solitary Plasmacytoma"))
      {
        lH+=0.25;
      }
     }
    } 
    
    public static void personal(String race, int age, double weight, double height)
    {
      if(race.contains("African American"))
      {
        lH+=0.20;
      }
       bmi = weight/ ((height*.0254)*(height*0.0254));
       if(bmi >= 25)
       {
        lH+=0.25;
       }
       if(age >= 65)
       {
        lH+=0.25;
       }

    }
    
    public static void previousHistory(String s)
    {
      if(s.contains("MGUS") || s.contains("Monoclonal Gammopathy of Undetermined Significance") || s.contains("monoclonal gammopathy of undetermined significance"))
      {
        lH+=0.25;
      }
      if(s.contains("Multiple Myeloma") || s.contains("Solitary Plasmacytoma"))
      {
        lH+=0.25;
      }
    }
}