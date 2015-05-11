import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;
public class AppTest {
    App app;
    Course course1;
    Course course2;
    Course course3;
    Course course4;
	
	@Before
	public void setUp()
	{
		app = new App();
		course1 = app.courses.get("P101");
		course2 = app.courses.get("P102");        
		course3 = app.courses.get("UI1");
		course4 = app.courses.get("WP1");        
		
	}
	
	@After
	public void tearDown()
	{

	}

	
	// Checking there can be no two offerings for the same course in the same year and semester 	
    @Test (expected=PreExistException.class)
    public void testNoDuplicateOfferings() throws PreExistException,ClashException
    {
		assertEquals("P101",course1.getId());
		app.createOffering(course1,200,2015,1);
		app.createOffering(course1,200,2015,1);
    }	
		
	
	// Checking offerings created can be accessed via getCourseOffering 
	@Test  
	public void testGetCourseOffering() throws PreExistException 
	{		
		app.createOffering(course1,200,2015,1);
		assertNotEquals("Offering for 2015 semester 1 exists",app.getCourseOffering("P101",2015,1),null);
		assertEquals("Offering for 2016 does not exist",app.getCourseOffering("P101",2016,1),null);
	}

	// Checking venue  clashes are detected
	@Test (expected=ClashException.class)
	public void testVenueClashes() throws PreExistException,ClashException 
	{
		CourseOffering offering1 = app.createOffering(course1,200,2015,1);
		CourseOffering offering2 = app.createOffering(course2,200,2015,1);			
		Venue venue = app.getVenue("12.10.02");
		app.assignLecture(offering1, 3, 10.0, 2.0, venue);  
		app.assignLecture(offering2, 3, 11.5, 2.0, venue);  			
	}
	
    // Checking lecture clashes are detected	
	@Test (expected=ClashException.class)
	public void testLectureClashes() throws PreExistException,ClashException 
	{
		
		CourseOffering offering1 = app.createOffering(course1,200,2015,1);
		CourseOffering offering2 = app.createOffering(course2,200,2015,1);			
		Venue venue1 = app.getVenue("12.10.02");
		Venue venue2 = app.getVenue("12.10.03");	    
		app.assignLecture(offering1, 3, 10.0, 2.0, venue1);  
		app.assignLecture(offering2, 3, 11.5, 2.0, venue2);
        Lecturer lecturer = app.getLecturer("e44556");
        Lecture lecture1 = offering1.getLecture();
        Lecture lecture2 = offering2.getLecture();
        app.assignLecturer(lecture1, lecturer);
        app.assignLecturer(lecture2, lecturer);            
	}

	// Checks when adding a lectures Venue assignments are correctly made 
	@Test
	public void testVenueAssignments() throws PreExistException,ClashException
	{
		CourseOffering offering1 = app.createOffering(course1,200,2015,1);
		CourseOffering offering2 = app.createOffering(course2,200,2015,1);			
		CourseOffering offering3 = app.createOffering(course3,200,2015,1);			
		CourseOffering offering4 = app.createOffering(course4,200,2015,1);			
		Venue venue1 = app.getVenue("12.10.02");
		Venue venue2 = app.getVenue("12.10.03");	    
		app.assignLecture(offering1, 3, 10.0, 2.0, venue1);  
		app.assignLecture(offering2, 3, 14.5, 2.0, venue2);
   	    app.assignLecture(offering3, 4, 9.5, 2.0, venue2);
		app.assignLecture(offering4, 5, 18.5, 2.0, venue2);
		assertEquals(app.getLessons(venue1).size(),1);
		assertEquals(app.getLessons(venue2).size(),3);	
	}	
}
