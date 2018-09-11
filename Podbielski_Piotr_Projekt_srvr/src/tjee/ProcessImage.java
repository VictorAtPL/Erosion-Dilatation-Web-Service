package tjee;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.io.IOUtils;

@WebService
public class ProcessImage {

	@WebMethod
	public byte[] filter(byte[] inputBytes, String filter, String ext) {
		InputStream input = new ByteArrayInputStream(inputBytes); 
		
		try {
			BufferedImage imBuff = ImageIO.read(input);
			
			imBuff = doFilter(filter, imBuff);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(imBuff, ext, baos);
			
			return baos.toByteArray();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		byte fail[] = { 0x0 };
        try {
			return IOUtils.toByteArray(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return fail;
	}

	private BufferedImage doFilter(String filter, BufferedImage imBuff) {
		int imgWidth = imBuff.getWidth();
		int imgHeight = imBuff.getHeight();
		
		BufferedImage newImBuff = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_BGR);
		
		for (int x = 0; x < imgWidth; x = x + 1) {
			for (int y = 0; y < imgHeight; y = y+1) {
				ArrayList<Integer> border_r = new ArrayList<>();
				ArrayList<Integer> border_g = new ArrayList<>();
				ArrayList<Integer> border_b = new ArrayList<>();
				
				for (int i = -1; i < 2; i++) {
					for (int j = -1 ; j < 2; j++) {
						if (i == 0 && j == 0) {
							continue;
						}
						
						int pixelX = i + x;
						int pixelY = j + y;
						
						if (pixelX >= 0 && pixelX < imgWidth && pixelY >= 0 && pixelY < imgHeight) {
							int rgb = imBuff.getRGB(pixelX, pixelY);
							Color color = new Color(rgb, true);
							border_r.add(color.getRed());
							border_g.add(color.getGreen());
							border_b.add(color.getBlue());
						}
					}
				}
				
				int r, g, b;
				Collections.sort(border_r);
				Collections.sort(border_g);
				Collections.sort(border_b);
				if (new String("erozja").equals(filter)) {
					// min
					r = border_r.get(0);
					g = border_g.get(0);
					b = border_b.get(0);
				}
				else {
					// max
					r = border_r.get(border_r.size()-1);
					g = border_g.get(border_g.size()-1);
					b = border_b.get(border_b.size()-1);
				}
				
				int alpha = new Color(imBuff.getRGB(x, y)).getAlpha();
				newImBuff.setRGB(x, y, new Color(r, g, b, alpha).getRGB());
			}
		}
		
		return newImBuff;
	}
}
