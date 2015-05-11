public class CourseOffering {
   private Lecture lecture;
   private int expectedStNo;
   private Course course;
   private int year;
   private int semester;
		
   public CourseOffering(int expectedStNo, int year, int sem)
   {
      this.expectedStNo = expectedStNo;	
      String key = ""+year+":"+sem;
      this.year = year;
      this.semester = sem;
      lecture = null;
   }
   public void setCourse(Course course)
   {
	   this.course = course;	   
   }
   
   public void assignLecture(int lectureDay, double lectureStartHr, double lectureDur, Venue venue) throws ClashException, PreExistException
   {	   
	  if (lecture != null) throw new PreExistException("Lecuture already exist");
	  lecture = new Lecture(lectureDay, lectureStartHr, lectureDur, venue);
   }
   
   public Lecture getLecture()
   {
	   return lecture;
   }
   
   public String toString()
   {      
	   String s = "";
	   if (course != null)
	   {   s = "Id = " + course.getId();
	       s += "\nName = " + course.getName();
	   }
       s += "\nYear = : " + year + " Semester : " + semester;
	   s += "\nExpected student number " + expectedStNo;
	   if (lecture != null)
		   s += "\n" + lecture.toString();
	   return s;
   }     
}