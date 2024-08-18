package ftn.devops.user.messaging.listener;

import ftn.devops.user.messaging.messages.BaseMessage;

public interface IMessageListener<T extends BaseMessage> {

    void receiveMessage(T message);
}