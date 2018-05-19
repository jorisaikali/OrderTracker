/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
        this.view.addShiftButtonListener(new ShiftListener());
        
        this.view.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        this.model.openPersistantData();
    }
    
    public void runGFX() {
        this.view.runGFX();
    }
    
    class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String customerName = view.getAddUserCN();
            String todayOrTomorrow = view.getAddUserTT();
            String time = view.getAddUserTS();
            
            if (customerName.contains("<html>")) {
                view.clearAddUserCN();
                view.displayError("Customer name contains html code. This is not allowed.");
                return;
            }
            
            if (customerName.equals("")) {
                view.clearAddUserCN();
                view.displayError("Customer name is empty. Please enter the customer's name.");
                return;
            }
            
            if (!model.checkOrderMax(time, todayOrTomorrow)) {
                model.addCustomer(customerName, todayOrTomorrow, time);
                view.displaySuccess();
                view.clearAddUserCN();
            }
            else {
                view.displayError("This time slot is already at it's maximum capacity.");
            }     
        }
    }
    
    class CompleteOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
            String customerName = view.getCompleteOrderCN();
            String time = view.getCompleteOrderTS();
            
            if (customerName.contains("<html>")) {
                view.clearCompleteOrderCN();
                view.displayError("Customer name contains html code. This is not allowed.");
                return;
            }
            
            if (customerName.equals("")) {
                view.clearCompleteOrderCN();
                view.displayError("Customer name is empty. Please enter the customer's name.");
                return;
            }
            
            if (model.removeCustomer(customerName)) {
                if (view.isRowVisible()) {
                    view.updateRow();
                }
                
                view.displaySuccess();
                view.clearCompleteOrderCN();
                
            } else {
                view.displayError("Customer was not found.");
            }         
        }
    }
    
    class CalendarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {            
            if (view.getCalendarTT().equals("Today")) {
                view.updateCalendarTable(model.getTodaysOrders());
            }
            else {
                view.updateCalendarTable(model.getTomorrowsOrders());
            }
        } 
    }
    
    class ShareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.writePersistantData();
                view.displaySuccess();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                view.displayError(ex.toString());
            } catch (IOException ex) {
                view.displayError(ex.toString());
            }
        }
    }
    
    class ShiftListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = view.confirmDialogBox("Are you sure you would like to shift the time slot data? This means today's orders will be deleted. This cannot be undone.");
            if (response == 0) {
                model.shiftTimeSlots();
                view.displaySuccess();
            } else {
                view.displayMessage("You have cancelled shifting time slot data.");
            }
        }
    }
}
