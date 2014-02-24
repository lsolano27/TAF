package com.ts.commons;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class TSFireFoxProfile {
	ProfilesIni allProfiles = new ProfilesIni();
	private FirefoxProfile profile;
	private String profileName = "seleniumTesting";
	private File firebug;
	private File firePath;
	private String fireBugVersion;	
	
	public TSFireFoxProfile(){
		File[] resources = new File("src/main/resources").listFiles();
		
		if(hasFirefoxItsOwnProfileForTesting()){
			profile = allProfiles.getProfile(profileName);
		}else if(existExtensions(resources)){
			createProfile();
		}else{
			profile = null;
		}
	}
	
	private void createProfile() {		
		try {
			profile = new FirefoxProfile();
			profile.addExtension(firebug);
			profile.addExtension(firePath);
			fireBugVersion = firebug.getName().split("-")[1];
			profile.setPreference("extensions.firebug.currentVersion", fireBugVersion); 
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private boolean existExtensions(File[] resourcesFiles){
		for (File file : resourcesFiles) {
			if(file.getName().toLowerCase().contains("firebug")){
				firebug = file;
			}else if(file.getName().toLowerCase().contains("firepath")){
				firePath = file;
			}
		}		
		
		if(firebug != null && firePath != null){
			return true;
		}else{
			return false;	
		}		
	}
	
	private boolean hasFirefoxItsOwnProfileForTesting(){		
		if(allProfiles.getProfile(profileName) != null){			
			return true;
		}else {
			return false;
		}		
	}
	
	public FirefoxProfile getProfile() {
		return profile;
	}
}
