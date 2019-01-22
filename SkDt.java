import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SkDt {
		public static int [][][] skin= new int[256][256][256];
		public static int [][][] nskin= new int[256][256][256];
		public static double [][][] prob= new double[256][256][256];
		
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		
		for(int i=0;i<256;i++)
			for(int j=0;j<256;j++)
				for(int k=0;k<256;k++)
				{
					skin[i][j][k]=0;
					nskin[i][j][k]=0;
				}
		//dataReader();
		/*for(int i=0;i<256;i++)
			for(int j=0;j<256;j++)
				for(int k=0;k<256;k++)
				{
					System.out.println(i+" "+j+" "+k+" "+skin[i][j][k]+" " +nskin[i][j][k]);	
				}
*/
		//addProbability();
		maskImage();
					
	}
	public static void setPixel( int red,int green,int blue,int w,int h,BufferedImage outImage)
	{
		Color myWhite = new Color(255, 255, 255); // Color white
		int rgb = myWhite.getRGB();
		System.out.println(red+" "+green+" "+blue+" "+prob[red][green][blue]);
		
		if(prob[red][green][blue]<=.3) outImage.setRGB(w, h, rgb);
			
		
	}
	public static void maskImage() throws IOException
	{

		File file= new File("C:\\Users\\Rakib bhai\\eclipse-workspace\\SkinDetection\\ratio2.txt");
		BufferedReader br =new BufferedReader(new FileReader(file));
		String st;
		
		while((st=br.readLine())!= null)
		{
			
			String[] s=st.split(" ");
			//System.out.println(j+"  "+s);
			String s1 = null,s2 = null,s3 = null,s4 = null;
			
			int attr1 =Integer.parseInt(s[0]);
			int attr2 =Integer.parseInt(s[1]);
			int attr3 =Integer.parseInt(s[2]);
			double classs =Double.parseDouble(s[3]);
			prob[attr1][attr2][attr3]=classs;
			//System.out.println(classs);
			/*
			if(classs==1) skin[attr1][attr2][attr3]++;
			else nskin[attr1][attr2][attr3]++;*/
			
		}
		
		BufferedImage inpImage=null;
		inpImage=ImageIO.read(new File("F:\\WEB 503\\HTML\\pic2.jpg"));
		BufferedImage outImage=null;
		/*outImage=ImageIO.read(new File("F:\\WEB 503\\HTML\\new.bmp"));
		if(inpImage!=null) {
			System.out.println("lol");
		}
		*/
		int h,w,red,gree,blue;
		h=inpImage.getHeight();
		w=inpImage.getWidth();
		
		for(int i=0;i<w;i++)
			for(int j=0;j<h;j++)
			{
				int pix=inpImage.getRGB(i, j);
				red=(pix >> 16)& 0xff;
				gree=(pix >> 8)& 0xff;
				blue=(pix)& 0xff;
				Color myWhite = new Color(255, 255, 255); // Color white
				int rgb = myWhite.getRGB();
				if(prob[red][gree][blue]<=.34) inpImage.setRGB(i, j, rgb);
				//setPixel(red, gree, blue, i, j,outImage);
			}
		
		ImageIO.write(inpImage, "bmp", new File("new.bmp"));
		
	}
	
	public static void addProbability() throws IOException
	{
		String fileName="ratio2.txt";
		FileWriter fw= new FileWriter(fileName,true);
		//fw.write(s);
		//fw.close();
		String s=null;
		for(int i=0;i<256;i++)
		for(int j=0;j<256;j++)
			for(int k=0;k<256;k++)
			{
				if(nskin[i][j][k]==0) nskin[i][j][k]=1;
				
				prob[i][j][k]=(double)skin[i][j][k]/nskin[i][j][k];
				s=i+" "+j+" "+k+" "+prob[i][j][k]+System.lineSeparator();
				fw.write(s);
				//System.out.println(prob[i][j][k]);
			}
		fw.close();
	}
	
	public static void  dataReader() throws NumberFormatException, IOException
	{
		
		File file= new File("F:\\db\\skinRGB.txt");
		BufferedReader br =new BufferedReader(new FileReader(file));
		String st;
		
		while((st=br.readLine())!= null)
		{
			
			String[] s=st.split(" ");
			//System.out.println(j+"  "+s);
			String s1 = null,s2 = null,s3 = null,s4 = null;
			
			int attr1 =Integer.parseInt(s[0]);
			int attr2 =Integer.parseInt(s[1]);
			int attr3 =Integer.parseInt(s[2]);
			int classs =Integer.parseInt(s[3]);
			//System.out.println(classs);
			
			if(classs==1) skin[attr1][attr2][attr3]++;
			else nskin[attr1][attr2][attr3]++;
			
		}
		
		
	}

}
