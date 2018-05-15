/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jori El-Saikali
 */
public class OrderTrackerController {
    
    private OrderTrackerView view;
    private OrderTrackerModel model;
    
    public OrderTrackerController(OrderTrackerView otv, OrderTrackerModel otm) {
        this.view = otv;
        this.model = otm;
        
        this.view.addAddCustomerButtonListener(new AddCustomerListener());
        this.view.addCompleteOrderButtonListener(new CompleteOrderListener());
        this.view.addShareButtonListener(new ShareListener());
    }
    
    public void runGFX() {
        this.view.runGFX();
    }
    
    class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add Customer Submitted");
        }
    }
    
    class CompleteOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Complete Submitted");
        }
    }
    
    class ShareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Share Submitted");
        }
    }
}
