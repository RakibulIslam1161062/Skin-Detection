import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {
	
	public static void main(String[] args) throws IOException
	{
		//readMask();
	}
	
	public static void readMask() throws IOException
	{
		int h,w;
		for(int t=0;t<556;t++)
			{
				String picName=null,no,maskPath="C:\\Users\\Rakib bhai\\Downloads\\datafile\\ibtd\\Mask\\",mainPicPath="C:\\Users\\Rakib bhai\\Downloads\\datafile\\ibtd\\";
				no=Integer.toString(t);
				String bmp=null,jpg=null;
				
				if(no.length()==1) picName="000"+no;
				else if(no.length()==2) picName="00"+no;
				else if(no.length()==3) picName="0"+no;
				bmp=picName+".bmp";
				jpg=picName+".jpg";
				maskPath+=bmp;
				mainPicPath+=jpg;
				BufferedImage img=null;
				BufferedImage mainImage=null;
				System.out.println(bmp);
				img=ImageIO.read(new File(maskPath));
				mainImage=ImageIO.read(new File(mainPicPath));
				
				h=img.getHeight();
				w=img.getWidth();
				System.out.println("height "+h+" wid "+w);
				for(int i=0;i<w;i++)
					for(int j=0;j<h;j++)
					{
						int pixel= img.getRGB(i, j);
						printPixel(pixel,i,j,mainImage);
					}
			}
		
	}
	
	public static int redValue(int pixel ) 
	{
		return (pixel >> 16) & 0xff;
	}
	public static int greenValue(int pixel ) 
	{
		return (pixel >> 8) & 0xff;
	}
	public static int blueValue(int pixel ) 
	{
		return (pixel) & 0xff;
	}
	
	public static void printPixel(int pixel,int width,int height,BufferedImage mainImage) throws IOException
	{
		int red,green,blue,red2,green2,blue2;
		String s = null;
		red=redValue(pixel);
		green=redValue(pixel);
		blue=blueValue(pixel);
		
		if((red > 220) && (green > 220) && (blue > 220))
		{
			int pixel2=mainImage.getRGB(width, height);
			red2=redValue(pixel2);
			green2=greenValue(pixel2);
			blue2=blueValue(pixel2);
			s=red2+","+green2+","+blue2+","+"2"+System.lineSeparator();
			fileWrite(s);
		}
			
		else
			{
				s=red+","+green+","+blue+","+"1"+System.lineSeparator();
				//System.out.println(s);
				fileWrite(s);
			}
	}
	
	public static void fileWrite(String s) throws IOException
	{
		String fileName="skinRGB.txt";
		FileWriter fw= new FileWriter(fileName,true);
		fw.write(s);
		fw.close();
	}

}
