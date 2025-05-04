//Importing the essential Java libraries for the program.
import java.util.Arrays;//For displaying the reference string
import java.util.Queue;//For the queue that will be utilized by the algorithm
import java.util.LinkedList;//Specialized type of queue utilized in the program
import java.text.DecimalFormat;//For Hit Ratio and Page-Fault Ratio
import java.util.Scanner;//For user prompt specifically for a number (Frame size)
import java.util.Random;//For generating a random string of numbers

// The Java class for the page-replacement algorithm.
class FIFO_algorithm{
    //Declaration of the decimal format class for the Hit Ratio and Page Fault Ratio.
    private static final DecimalFormat df = new DecimalFormat("0.000");

    //The public method that implemented the FIFO page-replacement algorithm.
    public static void implementation(int[] reference_string, int frame_size) {

        //Displaying the whole reference string and user's input frame size.
        //General Notice: Dashed lines inserted in the print method is only for aesthetics purposes.
        System.out.println("------------------------------------------------------------------------------");
        //The Arrays.toString() method is used to display the reference string properly.
        System.out.println("Reference String: " + Arrays.toString(reference_string));
        System.out.println("Frame Size: "+ frame_size);
        System.out.println("------------------------------------------------------------------------------");

         /* Declaration of a queue for the Algorithm. There are specific types of queue in Java
         but Linked List is utilized for this particular program. */
        Queue<Integer> integerQueue = new LinkedList<>();

        // Declaration of initial values for Page Faults and Hits.
       int pageFaults= 0;
       int hits= 0;

       /*Declaration of the "for each loop" to simplify the execution of the integerQueue.
       Take note: For each loop is specialized for iterating elements in an array or collection.*/
       for(int page :reference_string){
             /*The if condition checks, if the current reference string index is visible within the queue.
           The contains() method of queue accomplish the job itself. After verifying the condition, ...*/
          if(integerQueue.contains(page)){
              //...incrementation of the hits variable will be initialized,...
              hits++;
              //...print the current queue and indicates the execution is a hit.
              System.out.print("Reference Page: " + page + " " + integerQueue);
              System.out.println(" -> (This execution is a hit.)");
          }
            /*The else condition is where the real process execute. In the program, an if condition
          is located inside it. It verifies if the queue size is equal to the frame_size value. After
          verification, it will... */
          else {
              if (integerQueue.size() == frame_size) {
                  //...remove the first element within the queue,...
                  integerQueue.poll();
              }
              //...add the current index value (page) of the reference string in queue's last position,...
              integerQueue.offer(page);
              //... increase the value of pageFaults variable and...
              pageFaults++;
              //...print the current queue status (indicating that the new page is added to the queue).
              System.out.println("Reference Page: " +  page + " " + integerQueue);
          }
       }

        System.out.println("-------------------------");
           /* Displaying the number of Page Faults and Hits.
      For your information, the algorithm is more efficient,
      if page faults occurred less in the program. */
        System.out.println("Number of Page Faults: "+pageFaults);
        System.out.println("Number of Hits: "+hits);

        //Declaration and initialization of total variable.
        int total= pageFaults + hits;
          /* Displaying the total value. Take note: The total value must be equal to
         the size of the reference string.*/
        System.out.println("Total: "+ total);

        //Converting the value of pageFaults into double.
        Double conPF = (double)pageFaults;
        //Converting the value of total into double.
        Double conTotal = (double)total;
        //Converting the value of Hits into double.
        Double conHits = (double) hits;

        //Calculating the fault ratio and hit ratio of the algorithm.
         double fault_ratio= conPF/conTotal;
        double hit_ratio= conHits/conTotal;

        //Displaying the value of Fault Ratio and Hit Ratio.
        System.out.println("Fault ratio: " + df.format(fault_ratio));
        System.out.println("Hit ratio: " + df.format(hit_ratio));
        System.out.println("-------------------------");
    }
}

//Main class of the Java program.
public class FIFO_Page_Replacement_Algorithm {
    //Main method of the Java Program.
    public static void main (String[] args){
        //This block is just the header of the program, indicating the program's name.
        System.out.println("---------------------------------------------");
        System.out.println("VIRTUAL MEMORY MACHINE MANAGER - FIFO edition");
        System.out.println("---------------------------------------------");

        //Creating an object instantiation to access the method within the class "FIFO_algorithm".
        FIFO_algorithm page = new FIFO_algorithm();

        //Declaration of Scanner class for user prompt in frame size:
        Scanner scan = new Scanner(System.in);

        //Declaration of Random class to generate a random from 0 to 9
        Random num = new Random();

        //Declaration of Integer Values
        int attempts = 0;
        final int m_attempt = 2;
        int frame = -1;

        //If the attempt is less than 2, it will execute the following:
        while (attempts < m_attempt){
            //Indication that the user must enter their preferred frame size for the queue.
            System.out.print("Enter your preferred frame size (3 to 5 only): ");
            frame = scan.nextInt();

            if (frame<=5 && frame>=3) {
                break; // If the frame size number is valid, the program will exit the loop immediately.
            }
            else {
                attempts ++; // If not, the attempt value will be incremented and...
                if(attempts < m_attempt) {
                    // will ask the user to input a number again.
                    System.out.println("Invalid input, try again.");
                }
            }

        }

        // If the frame size does not meet the qualification, it will display the following: "
         if (frame < 3 || frame > 5){
            System.out.println("--------------------------");
            System.out.println("FRAME SIZE - OUT OF BOUNDS");
            System.out.println("--------------------------");

            //Indicating that the program is ended.
            System.out.println("------------------");
            System.out.println("PROGRAM TERMINATED");
            System.out.println("------------------");
            return;
        }

        //Declaration of the reference string size.
        int ref_size = 20;

        //Declaration of the array of Reference String and indicates the size of the array is ref_size.
        int[] ref_page = new int[ref_size];

        //For loop is initialized in this block to generate an array automatically with random numbers.
        for (int i = 0; i < ref_size; i++) {
             /*Random generation numbers (from 0 to 9) will be
            processed within this particular line of code. */
            ref_page[i] = num.nextInt(9);
        }
         /*Accessing the method with parameters within the class "FIFO_algorithm"
        by using object instantiation. */
        page.implementation(ref_page, frame);
        //Indicating that the program is ended.
        System.out.println("------------------");
        System.out.println("PROGRAM TERMINATED");
        System.out.println("------------------");
        return;
    }
}
