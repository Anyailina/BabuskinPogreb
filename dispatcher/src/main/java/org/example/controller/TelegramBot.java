package org.example.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramBot extends TelegramController {
    private UpdateController updateController;

    public TelegramBot(UpdateController updateController){
        this.updateController = updateController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateController.processUpdate(update);

    }


}
