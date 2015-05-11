public abstract class Lesson 
{
	private double startHour;
	private double endHour;
	private int day;
	private Staff staff;
	private Venue venue;

	public Lesson(int lecDay, double lecstartHour, double lecDur, Venue venue) throws ClashException
	{
	   this.day = lecDay;
	   this.startHour = lecstartHour;
	   this.endHour = lecstartHour + lecDur;
       venue.checkClash(day, startHour, endHour);	    
	   this.venue = venue;
	   venue.addLesson(this);
	   staff = null;
	}
	
	public void setStaff(Staff staff)
	{
		this.staff = staff;		   
	}
	public Staff getStaff()
	{
		return staff;
	}
	public double getStart()
	{
		return startHour;
	}
	public double getEnd()
	{
		return endHour;
	}
	public Venue getVenue()
	{  
		return venue;
	}   
	public int getDay()
	{  
		return day;
	}
	public String toString()
	{   
		String s = "Venue = " + venue.getLocation();  
	    s += " Day = " + day;
	    s += " Start time = " + startHour;
	    s += " End time = " + endHour;
	    if (staff != null)
			   s +=" Staff " + staff.getName();
	    return s;
	}  
}