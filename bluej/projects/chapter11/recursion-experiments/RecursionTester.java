
/**
 * This class comtains some simple test methods to do initial experiments
 * with recursive methods. These methods may be tested, examined and 
 * modified to gain experience with and understanding of recursive method calls
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class RecursionTester
{
    /** 
     * Recursive 'hello' method.
     */  
    public void sayHello()
    {   
        System.out.println("Hello!");
        sayHello();
    }
    
    /** 
     * Recursive 'hello' method with parameter.
     * @param numberOfTimes How many times to print 'hello'.
     */  
    public void sayHello(int numberOfTimes)
    {   
        if(numberOfTimes > 0) {
            System.out.println("Hello!");
            sayHello(numberOfTimes - 1);
        }
    }
}
