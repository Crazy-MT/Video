package org.androidpn.server.service;

import java.util.List;

import org.androidpn.server.model.Notification;

public interface NotificationService {

	void saveNotification(Notification notification);

	List<Notification> findNotificationsByUsername(String username);

	void deleteNotifition(Notification notification);

	void deleteNotifitionByUUID(String uuid);

}
