import java.util.ArrayList;
import java.util.Scanner;

class FlightPlanner {
	
  private ArrayList<FUNCTION> ARRAYLIST;
  
  public FlightPlanner() {
    //creating arraylist
    this.ARRAYLIST = new ArrayList<>();
  }
  
   // needed for case 2 listing all bookings
   public FUNCTION[] getLIST() {
    //Creating duplicate empty array
    FUNCTION[] LIST = new FUNCTION[this.ARRAYLIST.size()];
    //copying arraylist values into new array
    for (int i = 0; i < this.ARRAYLIST.size(); i++) {
      LIST[i] = this.ARRAYLIST.get(i);
    }
    return LIST;
  }
  
   // needed for case 6 sorting the bookings by date
   public FUNCTION[] getSORTEDLIST() {
    //Creating duplicate empty array
    FUNCTION[] SORTEDLIST = new FUNCTION[this.ARRAYLIST.size()];
	//copying arraylist values into new array
    for (int i = 0; i < this.ARRAYLIST.size(); i++) {
      SORTEDLIST[i] = this.ARRAYLIST.get(i);
    }
	// sorting the bookings by date (earliest first)
    FUNCTION TEMP;
    for (int i = 0; i < SORTEDLIST.length; i++) {
      for (int j = 1; j < (SORTEDLIST.length - i); j++) {
        if (SORTEDLIST[j].isEarlier(SORTEDLIST[j - 1])) {
          TEMP = SORTEDLIST[j - 1];
          SORTEDLIST[j - 1] = SORTEDLIST[j];
          SORTEDLIST[j] = TEMP;
        }
      }
    }
    return SORTEDLIST;
  }


// called upon to add bookings
  public void addFUNCTION(FUNCTION task) {
    this.ARRAYLIST.add(task);
  }


//called upon to delete bookings by entering the ID of the booking to be deleted
  public void deleteFUNCTION(int FLIGHT_ID) {
    for (int i = 0; i < this.ARRAYLIST.size(); i++) {
      if (this.ARRAYLIST.get(i).getFUNCTIONID() == FLIGHT_ID) {
        this.ARRAYLIST.remove(i);
      }
    }
  }



// simply clears list deleting all bookings
  public void deleteAllFUNCTIONS() {
    this.ARRAYLIST.clear();
  }


// assigns an ID starting from 1 to each booking
  public int getFUNCTIONNumber() {
    return this.ARRAYLIST.size();
  }


// formating for the task printout
  public void printList(FUNCTION[] tasks) {
    System.out.println("-----------------------------------------------------------------------------------------");
    for (FUNCTION task : tasks) {
      task.printFUNCTION();
    }
    System.out.println("-----------------------------------------------------------------------------------------");
  }


// formating for the task display menu
  public static void displayMenu() {
    System.out.println("\n########################");
    System.out.println("1: Book a Flight");
    System.out.println("2: Delete a Booking");
    System.out.println("3: Delete all Bookings");
	System.out.println("4: List all Bookings");
	System.out.println("5: Order flights by date"); 
    System.out.println("6: Exit the program"); 
    System.out.println("########################");
    System.out.print("Please select an action from above: ");
  }


   //Defines the instance
    public static void main(String[] args) {
    Scanner SC = new Scanner(System.in);
	
	//makes the program run continuously until the user exits
    FlightPlanner toDoList = new FlightPlanner();
    boolean running = true;

    
    //shows user options to pick then collects values according to user selection
    while (running) {
      displayMenu();
      int choice = SC.nextInt();
      
	  switch (choice) {

        case 1:

         

          SC.nextLine();
          System.out.print("Please input Flight number (ABC123): ");

          String FlightNumber = SC.nextLine();
		  

		  
          System.out.print("Please input flight date (DD MM YYYY): ");


          int FlightDay = SC.nextInt();
          int FlightMonth = SC.nextInt();
          int FlightYear = SC.nextInt();

          SC.nextLine();

          System.out.print("Please input flight time (HH MM): ");


          int FlightHour = SC.nextInt();
          int FlightMin = SC.nextInt();

          SC.nextLine();
          System.out.print("Please input departure location: ");
          String DepartureLocation = SC.nextLine();
		  

		  System.out.print("Please input arrival location: ");
          String ArrivalLocation = SC.nextLine();
		  

		  System.out.print("Please input seat number (123): ");
          int SeatNumber = SC.nextInt();
		  
		  SC.nextLine();
          System.out.print("Please input in flight services if any (None, Beverage, Light Meal, Full Meal, Entertainment): ");
          String FlightService = SC.nextLine();

          //creates new booking with all values entered previoulsy aswell as assigning a booking ID
          FUNCTION newFUNCTION = new FUNCTION(new TIME(FlightHour, FlightMin), new DATE(FlightDay, FlightMonth, FlightYear), FlightNumber, DepartureLocation, ArrivalLocation, SeatNumber, FlightService, toDoList.getFUNCTIONNumber() + 1);

          //adding booking to the list

          toDoList.addFUNCTION(newFUNCTION);

          //Displaying all bookings ordered by booking ID 1-2-3...

          System.out.printf("Task %d is added. The To-Do list is as follows:\n", newFUNCTION.getFUNCTIONID());
          toDoList.printList(toDoList.getLIST());
          break;


        case 2:

          //Deletes a booking by booking ID
          toDoList.printList(toDoList.getLIST());	  
          SC.nextLine();

          System.out.print("Please input the Flight ID to be deleted: ");
          int FLIGHT_ID = SC.nextInt();
		  
          toDoList.deleteFUNCTION(FLIGHT_ID);
          System.out.printf("\nFlight %d is deleted. The FlightPlanner is as follow:\n", FLIGHT_ID);
		  
          toDoList.printList(toDoList.getLIST());

          break;

        case 3:

          //Deletes all bookings

          toDoList.deleteAllFUNCTIONS();
          System.out.println("All Bookings deleted. The FlightPlanner is empty");

          break;
		  
        case 4:

          //List all bookings ordered by booking ID 1-2-3...
          toDoList.printList(toDoList.getLIST());
          break;
		  
		case 5:

          //lists all bookings by date ascending (1999-2000-2001 etc)
          toDoList.printList(toDoList.getSORTEDLIST());

          break;

        case 6:

          //Exits the program

          System.out.println("quiting program");
          running = false;

          break;
		
		
		  

      }
    }
  }

  

}




class FUNCTION {

  private TIME time;
  private DATE date;
  private String FlightNumber;
  private String DepartureLocation;
  private String ArrivalLocation;
  private int SeatNumber;
  private int FLIGHT_ID;
  private String FlightService;
  
  FUNCTION() {

//leaving attributes null until entered

    this(null, null, null, null, null, 0, null, 0);

  }

  public FUNCTION(TIME time, DATE date, String FlightNumber, String DepartureLocation, String ArrivalLocation, int SeatNumber, String FlightService, int FLIGHT_ID) {

    this.time = time;
    this.date = date;
    this.FlightNumber = FlightNumber;
    this.DepartureLocation = DepartureLocation;
	this.ArrivalLocation = ArrivalLocation;
	this.SeatNumber = SeatNumber;
    this.FLIGHT_ID = FLIGHT_ID;
	this.FlightService = FlightService;

  }

//Accessors and mutator methods for all flight values

  public TIME getTIME() {
    return this.time;
  }
  public DATE getDATE() {
    return this.date;
  }
  public String getTitle() {
    return this.FlightNumber;
  }
  public String getLocation() {
    return this.DepartureLocation;
  }
    public String getNotes() {
    return this.ArrivalLocation;
  }
  public int getFUNCTIONID() {
    return this.FLIGHT_ID;
  }
  public void setTIME(TIME time) {
    this.time = time;
  }
  public void setDATE(DATE date) {
    this.date = date;
  }
  public void setTitle(String FlightNumber) {
    this.FlightNumber = FlightNumber;
  }
  public void setLocation(String DepartureLocation) {
    this.DepartureLocation = DepartureLocation;
  }
  public void setNotes(String ArrivalLocation) {
    this.ArrivalLocation = ArrivalLocation;
  }
    public void settest(String FlightService) {
    this.FlightService = FlightService;
  }
    public int gethrs() {
    return this.SeatNumber;
  }

//final print out for all flight values

  public void printFUNCTION() {

    System.out.printf("* Flight %d: Flight Number: %s, Departure Date: %s, Departure Time: %s, From: %s, To: %s, Seat Number: %d, In flight services: %s\n", this.FLIGHT_ID, this.FlightNumber, this.date.toString(), this.time.toString(), this.DepartureLocation, this.ArrivalLocation, this.SeatNumber, this.FlightService);

  }


  public boolean isEarlier(FUNCTION task) {

    if (this.date.isEarlier(task.getDATE())) {
      return true;


    } else if (!this.date.isEarlier(task.getDATE()) && !task.getDATE().isEarlier(this.date)) {
      return this.time.isEarlier(task.time);
    }

    return false;

  }
}




class DATE {
   
   private int DAY;
   private int MONTH;
   private int YEAR;

   public DATE(int D, int M, int Y){
       DAY = D;
       MONTH = M;
       YEAR = Y;
   }
   
   //Accessors and mutator methods for class date
   
   public int getDay(){
       return this.DAY;
   }
   public int getMonth(){
       return this.MONTH;
   }
   public int getYear(){
       return this.YEAR;
   }
   
   public void setDay(int DAY) {
    this.DAY = DAY;
  }

  public void setMonth(int MONTH) {
    this.MONTH = MONTH;
  }

  public void setYear(int YEAR) {
    this.YEAR = YEAR;
  }
  
 //Returns date as DD/MM/YYYY 


  public String toString() {
    return String.format("%d/%d/%d", this.DAY, this.MONTH, this.YEAR);
}


// compares dates to determine which is earlier by years months and days
  public boolean isEarlier(DATE date) {


    if (this.YEAR == date.YEAR) {
      if (this.MONTH == date.MONTH) {
        if (this.DAY == date.DAY) {
          return false;

        } else if (this.DAY < date.DAY) {
          return true;
        } else {
          return false;
        }
		
      } else if (this.MONTH < date.MONTH) {
        return true;
      } else {
        return false;
      }


    } else if (this.YEAR < date.YEAR) {
      return true;

    } else {
      return false;

    }

  }
}








class TIME {

  private int HR;
  private int MIN;

  //constructor for time

  public TIME() {
    this(0, 0);
  }

  
  public TIME(int HR, int MIN) {
    this.HR = HR;
    this.MIN = MIN;
  }

  //Accessors and mutator methods for class time

  public int getHour() {
    return this.HR;
  }

  public int getMin() {
    return this.MIN;
  }
  
  public void setHour(int HR) {
    this.HR = HR;
  }

  public void setMin(int MIN) {
    this.MIN = MIN;
  }
  
  //Returns time as HH:MM 
  public String toString() {

    return String.format("%d:%d", this.HR, this.MIN);
  }

  // compares times between tasks to determine which is earlier

  public boolean isEarlier(TIME time) {
    if (this.HR == time.HR) {
      if (this.MIN == time.MIN) {
        return false;
      } else if (this.MIN < time.MIN) {
        return true;
      } else {
        return false;
      }
    } else if (this.HR < time.HR) {
      return true;
    } else {
      return false;

    }
  }
}






































