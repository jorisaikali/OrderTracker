/*
    BUG REPORT:
        - When adding a customer to a time slot with "30", get ArrayIndexOutOfBoundsException on OrderTrackerModel.checkMax() on line 240
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
