/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javafx.util.Duration;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Asus
 */
public class NotificationService {
     public void Notification(String title,String text) {
         Notifications notificationBuilder = Notifications.create()
                 .title(title)
                 .text(text)
                 .graphic(null)
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.TOP_RIGHT);
        notificationBuilder.showInformation();

    
    
}
}
