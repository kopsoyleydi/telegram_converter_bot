package com.example.telerambot.service;


import com.example.telerambot.config.BotConfig;
import com.example.telerambot.entities.TelegramDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class SpringDemoConverterBot extends TelegramLongPollingBot {

    final BotConfig config;

    public SpringDemoConverterBot(BotConfig config){
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            boolean check = true;
            if (messageText == "/start") {
                try {
                    startCommandReceived(chatId, update.getMessage().getChat().getUserName());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (messageText != "/start") {
                try {
                    convert(chatId, update.getMessage().getText());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                sendMessage(chatId, "Чем я могу вам помочь");
            }
        }
    }

    private void startCommandReceived(long chatId, String name) throws TelegramApiException{
        String answer = "Привет" + name + ", я бот, конвертер валют";
        sendMessage(chatId, answer);
    }
    private void convert(long chatId, String value) throws TelegramApiException{
        String answer = "";
        double GRes = 0;
        for(int i = 0;i<value.length();i++){
            if (value.charAt(i) == '$'){
                double res = Double.parseDouble(stringCutSym(value));
                GRes = res * 449;
                answer = String.format("%.3f",GRes);
            }
            else if(value.charAt(i) == 'Т' || value.contains("тенге") || value.charAt(i) == 'т'){
                double res = Double.parseDouble(stringCutSym(value));
                GRes = res / 449;
                answer = String.format("%.3f",GRes);
            }
            else {
                answer = "Не правильное значение";
            }
        }
        sendMessage(chatId, answer);
        RequestServiceImp re = new RequestServiceImp();
        re.addMessages(new TelegramDatas(null, chatId, value,answer));
    }
    public String stringCutSym(String sym){
        sym = sym.replaceAll("[^\\w+]", "");
        return sym;
    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        try {
            execute(message);
        }catch (TelegramApiException e){
            e.getStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken(){
        return config.getToken();
    }
}
