/**
 * The LiveData class represents live data related to carbon intensity and zone information.
 * It provides methods to set and get the carbon intensity, zone, and updated time.
 * 
 * This class is used to store and manage live data fetched from an external source.
 * 
 * @version 1.0
 */
public class LiveData
{
    private String zone; 
    private int carbonIntensity; 
    private String updatedAt;

    /**
     * Constructor for objects of class LiveData.
     * Initializes the zone, carbonIntensity, and updatedAt fields to default values.
     */
    public LiveData()
    {
        zone = "";
        carbonIntensity = 0;
        updatedAt = "";
    }
    
    /**
     * Sets the carbon intensity value.
     * 
     * @param newCarbon: The new carbon intensity value to set.
     */
    public void setCarbonIntensity(int newCarbon){
        carbonIntensity = newCarbon; 
    }
    
    /**
     * Sets the zone value.
     * 
     * @param newZone: The new zone value to set.
     */
    public void setZone(String newZone){
        zone = newZone; 
    }

    /**
     * Sets the updated time value.
     * 
     * @param newUpdated: The new updated time value to set.
     */
    public void setUpdated(String newUpdated){
        updatedAt = newUpdated; 
    }
    
    /**
     * Gets the carbon intensity value.
     * 
     * @return The current carbon intensity value.
     */
    public int getCarbonIntensity(){
        return carbonIntensity;
    }
    
    /**
     * Gets the zone value.
     * 
     * @return The current zone value.
     */
    public String getZone(){
        return zone; 
    }
    
    /**
     * Gets the updated time value.
     * 
     * @return The current updated time value.
     */
    public String getUpdated(){
        return updatedAt;
    }

}
