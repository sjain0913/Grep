/* Collaboration Statement: In order to help learn course concepts,
I worked on the homework with Joshua Donegal, discussed homework topics
and issues with Joshua Donegal, and/or consulted related material that
can be found at N/A. */


/**
 * This class represents the class Exception. It extends RuntimeException.
 * @author Saumya Jain
 * @version 1.0
 */

public class InvalidSearchStringException extends RuntimeException {

/**
 * This creates an InvalidSearchStringException,
    while calling on the constructor for Exception
 * @param error This is the error message to be displayed.
 */
    public InvalidSearchStringException(String error) {
        super(error);
    }
}