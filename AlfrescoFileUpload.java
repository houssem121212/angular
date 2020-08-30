package mayappstage.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service

public class AlfrescoFileUpload {
	
	
	
	public void addfolder(String folderName) {
		// default factory implementation
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();
		// user credentials
		parameter.put(SessionParameter.USER, "admin");
		parameter.put(SessionParameter.PASSWORD, "admin");
		// connection settings
		parameter.put(SessionParameter.ATOMPUB_URL,
				"http://127.0.0.1:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		// create session
		Session session = factory.getRepositories(parameter).get(0).createSession();
		Folder root = session.getRootFolder();
		// properties
		Map<String, Object> properties = new HashMap<String, Object>();
		
		
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
		properties.put(PropertyIds.NAME, folderName); // folder name
		// create the folder
		Folder parent = root.createFolder(properties);
		String name = "NewTextFile.txt";
		// properties
		Map<String, Object> properties2 = new HashMap<String, Object>();
		properties2.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		properties2.put(PropertyIds.NAME, name);
		// content
		byte[] content = "Hello Code Factory...!".getBytes();
		InputStream stream = new ByteArrayInputStream(content);
	//	ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain",				stream);
		// create a major version
//		Document newDoc = parent.createDocument(properties2, contentStream, VersioningState.MAJOR);
		
		
		
		
		
		


		
		
		
		
		
		System.out.println("DONE.");
	}
	
	public  void getFolder(String folderName,MultipartFile file){
		
		
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();
		// user credentials
		parameter.put(SessionParameter.USER, "admin");
		parameter.put(SessionParameter.PASSWORD, "admin");
		// connection settings
		parameter.put(SessionParameter.ATOMPUB_URL,
				"http://127.0.0.1:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		// create session
		Session session = factory.getRepositories(parameter).get(0).createSession();
		Folder root = session.getRootFolder();
		// properties
		Map<String, Object> properties = new HashMap<String, Object>();
		
	//System.err.println(root.getChildren());
	for (CmisObject child: root.getChildren()) {
		
		
		
	   // System.err.println(child.getName());
	    
if(	    child.getName().equals(folderName)){
	
	
	
	
	

	
	CmisObject cmisObject = session.getObject(child.getId());

	if (cmisObject instanceof Document) {
	    Document document = (Document) cmisObject;
	}  if (cmisObject instanceof Folder) {
	    Folder folder = (Folder) cmisObject;
	    
	    for (CmisObject child2: folder.getChildren()) {
	    	
	    	
	    	System.err.println(child2.getName());
	    	
	    	
	    	
	    }
	   
	    
	    

		try {
		    //create a document
		    Map<String, Object> docProps = new HashMap<String, Object>();
		    docProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		    
		  
		   
		    File convFile = new File(file.getOriginalFilename());
	        convFile.createNewFile();
	        FileOutputStream fos = new FileOutputStream(convFile);
	        fos.write(file.getBytes());
	        fos.close();
	      
		    InputStream isFile = new FileInputStream(convFile);
		    docProps.put(PropertyIds.NAME, convFile.getName());
		    ContentStream contentStream = session.getObjectFactory().createContentStream(convFile.getName(), convFile.length(), "application/ms-word", isFile);
		            
		    Document d = folder.createDocument(docProps, contentStream, VersioningState.MAJOR);
		            System.err.println(convFile.getName());
		    System.out.println(d.getId());
		    isFile.close();
		} catch (Exception ex) {
		    System.err.println("Something has gone horribly wrong.");
		    ex.printStackTrace();
		}
	    
	    
	    
	    
	}
	
	
	
	
	
	
	
	
	
	
	
}
	    
	    
	    
	}
		
	}
	
	
	
	

	
	
	 
	 
	 
	 public void deletefile(mayappstage.entitie.File filename ){
	
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
			SessionFactory factory = SessionFactoryImpl.newInstance();
			Map<String, String> parameter = new HashMap<String, String>();
			// user credentials
			parameter.put(SessionParameter.USER, "admin");
			parameter.put(SessionParameter.PASSWORD, "admin");
			// connection settings
			parameter.put(SessionParameter.ATOMPUB_URL,
					"http://127.0.0.1:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
			parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			// create session
			Session session = factory.getRepositories(parameter).get(0).createSession();
			Folder root = session.getRootFolder();
			// properties
			Map<String, Object> properties = new HashMap<String, Object>();
			
		//System.err.println(root.getChildren());
		for (CmisObject child: root.getChildren()) {
			
			
			
		   // System.err.println(child.getName());
		    
	if(	    child.getName().equals(	 filename.getFolder().getName())){
		
		
		
		
		

		
		CmisObject cmisObject = session.getObject(child.getId());

		if (cmisObject instanceof Document) {
		    Document document = (Document) cmisObject;
		    
		    
		    
		    
		    
		    
		}  if (cmisObject instanceof Folder) {
		    Folder folder = (Folder) cmisObject;
		    System.err.println(folder.getName());
		    
		    for (CmisObject child2: folder.getChildren()) {
		    	
		    	
		    	System.err.println(child2.getName());
		    	
		    	
		    	if (child2.getName().equals(filename.getName()))
		    	
		    	{
		    		child2.delete();
		    		
		    	}
		    	
		    }
			   
			   
			   
			   
		   
		   
		    

		
		    
		    
		    
		}
		
		
		
		
		
		
		
		
		
		
		
	}
		    
		    
		    
		}
			
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	 
	 
	 
	 
	
}