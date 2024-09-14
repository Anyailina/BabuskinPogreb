package org.example.node.sevice;

import org.telegram.telegrambots.meta.api.objects.Update;


public interface ServiceConsumer {
    void consumeTextMessage(String update);

    void consumeDocumentMessage(Update update);

    void consumePhotoMessage(Update update);
}
