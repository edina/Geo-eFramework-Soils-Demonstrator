package edina.eframework.gefcdemo.portrayal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * Generates a Mapserver .map file which allows the portrayal of the result of
 * the soil erosion algorithm for a specific user.
 * 
 * @author Andrew Seales
 */
public class MapserverGenerator implements WmsGenerator {
  
  // Velocity object generated by Spring
  private VelocityEngine velocityEngine;
  
  public void setVelocityEngine( VelocityEngine velocityEngine ) {
    this.velocityEngine = velocityEngine;
  }
  
  // Classpath location of the WPS request template for processing the model
  private String templateLocation;
  
  public void setTemplateLocation( String templateLocation ) {
    this.templateLocation = templateLocation;
  }
  
  // Classpath location of the WPS request template for previewing landcover
  private String growTemplateLocation;
  
  public void setGrowTemplateLocation( String growTemplateLocation ) {
    this.growTemplateLocation = growTemplateLocation;
  }
  
  // Base output folder where the data from the WPS is saved
  private String wpsOutputDir;
  
  public void setWpsOutputDir( String wpsOutputDir ) {
    this.wpsOutputDir = wpsOutputDir;
  }
  
  // Base WMS url to the result server
  private String resultUrl;
  
  public void setResultUrl( String resultUrl ) {
    this.resultUrl = resultUrl;
  }

  /**
   * @see edina.eframework.gefcdemo.portrayal.WmsGenerator#generateResultConfiguration(java.lang.String)
   */
  @Override
  public void generateResultConfiguration( String user ) throws IOException {
    generateConfiguration( user, "result.map", templateLocation );
  }
  
  /**
   * @see edina.eframework.gefcdemo.portrayal.WmsGenerator#generateLandcoverConfiguration(java.lang.String)
   */
  @Override
  public void generateLandcoverConfiguration( String user ) throws IOException {
    generateConfiguration( user, "landcoverPreview.map", growTemplateLocation );
  }
  
  /**
   * Generates a Mapserver Map WMS file from a particular template.
   * 
   * @param user the user id who made the request
   * @param mapName the name of the Mapserver mapfile
   * @param template the name of the VM template to generate the mapfile from
   * @throws IOException
   */
  private void generateConfiguration( String user, String mapName, String template )
    throws IOException {
    
    File wmsTemplateFile = new File( wpsOutputDir + user + "/" + mapName );
    wmsTemplateFile.getParentFile().mkdirs();
    
    Writer wpsRequest = null;
    try {
      wpsRequest = new FileWriter( wmsTemplateFile );
      Map<String, Object> velocityMap = new HashMap<String,Object>();
      
      velocityMap.put( "dataFolder", wpsOutputDir );
      velocityMap.put( "user", user );
      velocityMap.put( "resultUrl", resultUrl );
      
      VelocityEngineUtils.mergeTemplate( velocityEngine, template, velocityMap, wpsRequest );
    }
    finally {
      try { wpsRequest.close(); } catch ( Exception e ) {}
    };
  }
}
