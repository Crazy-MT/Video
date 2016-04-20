package org.androidpn.server.dao;

import java.util.List;

import org.androidpn.server.model.Notification;

public interface NotificationDao {
	
	void saveNotification(Notification notification);
	
	List<Notification> findNotificationsByUsername(String userName);
	
	void deleteNotification(Notification notification);

}
