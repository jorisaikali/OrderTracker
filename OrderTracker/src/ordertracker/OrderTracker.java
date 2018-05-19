/*
    BUG REPORT:
        - When completing an order, if the time slot is not set to null (i.e. showing the row in the table), it does not allow the user to press the checkmark
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertracker;

/**
 *
 * @author Jori El-Saikali
 */
public class OrderTracker {
    
    public static void main(String[] args) {
        OrderTrackerModel otModel = new OrderTrackerModel();
        OrderTrackerView otView = new OrderTrackerView(otModel);
        OrderTrackerController otController = new OrderTrackerController(otView, otModel);

        otController.runGFX();
    }
}
