/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Jori El-Saikali
 */
public class OrderTrackerController {
    
    private final OrderTrackerView view;
    private final OrderTrackerModel model;
    
    public OrderTrackerController(OrderTrackerView otv, OrderTrackerModel otm) {
        this.view = otv;
        this.model = otm;
        
        this.view.addAddCustomerButtonListener(new AddCustomerListener());
        this.view.addCompleteOrderButtonListener(new CompleteOrderListener());
        this.view.addCalendarToolbarButtonListener(new CalendarListener());
        this.view.addCalendarTTActionListener(new CalendarListener());
        this.view.addShareButtonListener(new ShareListener());
    }
    
    public void runGFX() {
        this.view.runGFX();
    }
    
    class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add Customer Submitted");
            
            String customerName = view.getAddUserCN();
            String todayOrTomorrow = view.getAddUserTT();
            String time = view.getAddUserTS();
            
            if (model.checkOrderMax(time, todayOrTomorrow)) {
                model.addCustomer(customerName, todayOrTomorrow, time);
                view.displaySuccess();
            }
            else {
                view.displayError();
            }     
        }
    }
    
    class CompleteOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Complete Submitted");
            
            String customerName = view.getCompleteOrderCN();
            String time = view.getCompleteOrderTS();
            
            if (time.equals("None")) {
                if (model.removeCustomer(customerName)) {
                    view.displaySuccess();
                } else {
                    view.displayError();
                }
            }
            else {
                // TODO: Display customers names when searching by time
            }
        }
    }
    
    class CalendarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Updating table");
            
            if (view.getCalendarTT().equals("Today")) {
                view.updateCalendarTable(model.getTodaysOrders());
            }
            else {
                view.updateCalendarTable(model.getTomorrowsOrders());
            }
        } 
    }
    
    /*
    class CalendarTTListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getCalendarTT().equals("Today")) {
                view.updateCalendarTable(model.getTodaysOrders());
            }
            else {
                
            }
        }
    }
    */
    
    class ShareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Share Submitted");
            
            try {
                model.writePersistantData();
                view.displaySuccess();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                //Logger.getLogger(OrderTrackerController.class.getName()).log(Level.SEVERE, null, ex);
                view.displayError();
            }
        }
    }
}
