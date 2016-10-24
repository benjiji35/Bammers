package com.bam.GESTIBANKBAM.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bam.GESTIBANKBAM.model.FileModel;
import com.bam.GESTIBANKBAM.model.MultiFileModel;
import com.bam.GESTIBANKBAM.service.ClientService;
import com.bam.GESTIBANKBAM.util.FileValidator;
import com.bam.GESTIBANKBAM.utils.BAMTools;

import net.sf.jnetparse.security.PasswordGenerator;

@RestController
public class FileUploadController {

	@Autowired
	private ClientService clientService;

	private static Properties config;

	//private static String UPLOAD_LOCATION = "D:/bambank/";
	private static final String UPLOAD_CONFIG = "/bambank/upload.properties";

	private static final String UPLOAD_LOCATION_BASE;
	private static final String UPLOAD_LOCATION_TEMP;

	private static final String FILE_SEPARATOR;

	private HashMap<String, String> sessionToDirectory;

	public FileUploadController() {
		super();
		sessionToDirectory = new HashMap<String, String>();
	}

	static {
		config = new Properties();
		try {
			config.load(FileUploadController.class.getResourceAsStream(UPLOAD_CONFIG));
			config.list(System.out);
			String base = config.getProperty("upload.location");
			FILE_SEPARATOR = getFileSeparator();
			UPLOAD_LOCATION_BASE = (base.endsWith(FILE_SEPARATOR)? base: base+FILE_SEPARATOR);
			UPLOAD_LOCATION_TEMP = UPLOAD_LOCATION_BASE + "tmp"+FILE_SEPARATOR;
			prepareUploadDirectory();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			throw new ExceptionInInitializerError(e);
		}
	};

	private static String getFileSeparator() {
		String fs = System.getProperty("file.separator");
		String os = System.getProperty("os.version");

		if (fs == null) {
			if (os != null) {
				os = os.toLowerCase().trim();
				if (os.indexOf("windows") == -1) {
					fs = "/";
				} else {
					fs = "\\";
				}
			}
		} else {
			fs = fs.trim();
		}
		return fs;
	}

	private static boolean prepareUploadDirectory() throws IOException {
		File dir = new File(UPLOAD_LOCATION_BASE);
		File tmp = new File(UPLOAD_LOCATION_TEMP);
		boolean created = false;

		if (dir.exists()) {
			if (dir.isDirectory() == false) {
				throw new IOException(UPLOAD_LOCATION_BASE + 
						": is not a directory");
			} else if (tmp.exists()) {
				if (tmp.isDirectory() == false) {
					throw new IOException(UPLOAD_LOCATION_TEMP + 
							": is not a directory");
				} else if (tmp.list().length != 0) {
					throw new IOException(UPLOAD_LOCATION_TEMP +
							": must be empty");
				}
			} else {
				// need only to create the child
				created = tmp.mkdir();
			}
		} else {
			// need to create the full arborescence
			created = tmp.mkdirs();
		}
		return created;
	}

	@Autowired
	FileValidator fileValidator;

//	@Autowired
//    MultiFileValidator multiFileValidator;

	@InitBinder("fileModel")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

//	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
//	public String getHomePage(ModelMap model) {
//		return "welcome";
//	}

	@RequestMapping(value = "/singleUpload/", method = RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model) {
		System.out.println("Essai Upload en GET Impossible");
		FileModel fileModel = new FileModel();
		model.addAttribute("fileModel", fileModel);
		return "UserManagement";
	}

	//@RequestMapping(value = "/singleUpload/s-{sid}/k-{key}", method = RequestMethod.POST)
	@RequestMapping (value= "/singleUpload/s-{sid}", method= RequestMethod.POST)
	public ResponseEntity<Void> singleFileUpload(@Valid FileModel fileModel, BindingResult result, ModelMap model,
			@PathVariable ("sid") String sid
			)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");
			//return "UserManagement";
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = fileModel.getFile();
			String base = UPLOAD_LOCATION_TEMP;
			File ud = null;
			String dir = sessionToDirectory.get(sid);
			String s   = fileModel.getFile().getOriginalFilename();

			if (dir == null) {
				// generate a random directory name
				dir = BAMTools.genName(20);
				sessionToDirectory.put(sid, dir);
			}

			if (base.endsWith(FILE_SEPARATOR) == false) {
				base += FILE_SEPARATOR;
			}
			base += dir + FILE_SEPARATOR;
			ud = new File(base);
			ud.mkdirs();

			// Now do something with file...
			FileCopyUtils.copy(fileModel.getFile().getBytes(),
					new File(base + s));

			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			//return "success";
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}

	@RequestMapping(value="/multiUpload/", method = RequestMethod.POST)
    public String multiFileUpload(@Valid MultiFileModel multiFileModel, BindingResult result, ModelMap model) throws IOException {
		PasswordGenerator pg = new PasswordGenerator(false, 20);
		String gen           = pg.next().get(0);
         
        if (result.hasErrors()) {
            System.out.println("validation errors in multi upload");
            return "multiFileUploader";
        } else {            
            System.out.println("Fetching files");
            List<String> fileNames= new ArrayList<String>();
             
            //Now do something with file...
            for(FileModel file : multiFileModel.getFiles()){
                FileCopyUtils.copy(file.getFile().getBytes(), 
                		new File(UPLOAD_LOCATION_TEMP + file.getFile()
                			.getOriginalFilename()));
                fileNames.add(file.getFile().getOriginalFilename());
            }
             
            model.addAttribute("fileNames", fileNames);
            //return "multiSuccess";
            return gen;
        }
    }

}
