package com.example.application.views.chat;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouteAlias;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.vaadin.artur.Avataaar;

@Route(value = "chat", layout = MainView.class)
@PageTitle("Chat")
@RouteAlias(value = "", layout = MainView.class)
public class ChatView extends Div {
    private final UI ui;
    private final MessageList messageList = new MessageList();
    private final TextField message = new TextField();
    private final Chat chatSession;
    private final ScheduledExecutorService executorService;
    public ChatView(Bot alice, ScheduledExecutorService executorService) {
    this.executorService = executorService;
    ui = UI.getCurrent();
    chatSession = new Chat(alice);
    message.setPlaceholder("Enter a message...");
    message.setSizeFull();
    Button send = new Button(VaadinIcon.ENTER.create(), event -> sendMessage());
    send.addClickShortcut(Key.ENTER);
    HorizontalLayout inputLayout = new HorizontalLayout(message, send);
    inputLayout.addClassName("inputLayout");
    add(messageList, inputLayout);
    inputLayout.expand();
    setSizeFull();
}

    private void sendMessage() {
    String text = message.getValue();
    messageList.addMessage("You", new Avataaar("Name"), text, true);
    message.clear();
        executorService.schedule(() -> {
            String answer = chatSession.multisentenceRespond(text);
            ui.access(() -> messageList.addMessage(
                "Alice", new Avataaar("Alice2"), answer, false));
        },new Random().ints(1000, 3000).findFirst().getAsInt(),
        TimeUnit.MILLISECONDS);
}}

