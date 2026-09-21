/**
 * The main part of the calculator performing the arithmetic logic 
 * of the calculations.
 * 
 * @author Hacker T. Largebrain 
 * @version 1.0
 */
public class CalcEngine
{
    // The value in the display.
    private int displayValue;
    // The previous operator typed (+ or -).
    private char previousOperator;
    // The left operand to previousOperator.
    private int leftOperand;

    /**
     * Create a CalcEngine instance.
     */
    public CalcEngine()
    {
        displayValue = 0;
        previousOperator = ' ';
        leftOperand = 0;
    }

    /**
     * Return the value of the calculator that should appear in the display right now.
     * @return The value to be shown on the calculator display.
     */
    public int getDisplayValue()
    {
        return displayValue;
    }

    /**
     * A number button was pressed. Process it.
     * @param number The single digit.
     */
    public void numberPressed(int number)
    {
        displayValue = displayValue * 10 + number;
    }

    /**
     * This method should be called when the '+' button was pressed. 
     */
    public void plus()
    {
        applyPreviousOperator();
        previousOperator = '+';
        displayValue = 0;
    }

    /**
     * This method should be called when the '-' button was pressed.
     */
    public void minus()
    {
        applyPreviousOperator();
        previousOperator = '-';
        displayValue = 0;
    }
    
    /**
     * This method should be called when the '=' button was pressed.
     */
    public void equals()
    {
        if(previousOperator == '+') {
            displayValue = leftOperand + displayValue;
        }
        else {
            displayValue = leftOperand - displayValue;
        }
        leftOperand = 0;
    }

    /**
     * This method should be called when the 'C' (clear) button was pressed.
     */
    public void clear()
    {
        displayValue = 0;
    }

    /**
     * Return the name of this calculator to be shown as the title of the application.
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Super Calculator";
    }

    /**
     * Return the name of the author of this application.
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "Hacker T. Largebrain";
    }

    /**
     * Return the version number of this application.
     * @return The version number of this engine.
     */
    public String getVersion()
    {
        return "version 0.2";
    }
    
    /**
     * An operator button has been pressed. Apply the immediately preceding operator to
     * calculate an intermediate result. This will form the left operand of the new operator.
     */
    private void applyPreviousOperator()
    {
        if(previousOperator == '+') {
            leftOperand += displayValue;
        }
        else if(previousOperator == '-') {
            leftOperand -= displayValue;
        }
        else {
            // There was no preceding operator.
            leftOperand = displayValue;
        }
    }
}
