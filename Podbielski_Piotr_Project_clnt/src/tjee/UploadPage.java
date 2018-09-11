package tjee;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIGraphic;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@SessionScoped
public class UploadPage implements Serializable {

	/**
	 * 
	 */
	
	private UIGraphic graphicImageAfter;
	private UIGraphic graphicImageBefore;
	
	private static final long serialVersionUID = -886796539727046288L;

	public static String tempDir;
	
	private String filter;
	private Part uploadedFile; 
	
	private static final List<String> allowedMimeTypes = Arrays.asList("image/jpeg", "image/png", "image/gif"); 
	
	@PostConstruct
    public void init() {
		filter = "erozja";
		
		try {
			Path path = Files.createTempDirectory("UploadPage_");
			tempDir = path.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part)value;
        if (!allowedMimeTypes.contains(file.getContentType())) {
        	msgs.add(new FacesMessage("plik nie jest obrazkiem"));
        }
        
        if (!msgs.isEmpty()) {
        	throw new ValidatorException(msgs);
	    }
    }
	
	public void upload() {
		if (uploadedFile == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "nie wgrano obrazu",
                    null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		else if (!(new String("erozja").equals(filter)) && !(new String("dylatacja").equals(filter))) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "nie wybranu filtru",
                    null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		
		try {
			File folder = new File(tempDir);
			String filename = FilenameUtils.getBaseName(uploadedFile.getSubmittedFileName()); 
			String extension = FilenameUtils.getExtension(uploadedFile.getSubmittedFileName());

			File file = File.createTempFile(filename + "-", "." + extension, folder);
			Path path = file.toPath();
			
			InputStream input = uploadedFile.getInputStream();
			Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
			
			graphicImageBefore.setUrl("/image/" + FilenameUtils.getBaseName(path.getFileName().toString()) + "." + FilenameUtils.getExtension(path.getFileName().toString()));
			graphicImageBefore.setRendered(true);
			
			file.deleteOnExit();
			
			/* GET FROM WEBSERVICE */
			ProcessImageProxy imageProxy = new ProcessImageProxy();
			byte[] outBytes = imageProxy.filter(Files.readAllBytes(path), filter, extension);
			input = new ByteArrayInputStream(outBytes);
			
			file = File.createTempFile("processed_" + filename + "-", "." + extension, folder);
			path = file.toPath();
			
			Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
			
			graphicImageAfter.setUrl("/image/" + FilenameUtils.getBaseName(path.getFileName().toString()) + "." + FilenameUtils.getExtension(path.getFileName().toString()));
			graphicImageAfter.setRendered(true);
			
			file.deleteOnExit();
		}
		catch (IOException e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(),
                    null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	

	public String getFilter() {
		return filter;
	}



	public void setFilter(String filter) {
		this.filter = filter;
	}



	public Part getUploadedFile() {
		return uploadedFile;
	}



	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public UIGraphic getGraphicImageAfter() {
		return graphicImageAfter;
	}

	public void setGraphicImageAfter(UIGraphic graphicImageAfter) {
		this.graphicImageAfter = graphicImageAfter;
	}

	public UIGraphic getGraphicImageBefore() {
		return graphicImageBefore;
	}

	public void setGraphicImageBefore(UIGraphic graphicImageBefore) {
		this.graphicImageBefore = graphicImageBefore;
	}
	
}
