package com.dfortch.messagesapp;

import com.dfortch.messagesapp.dto.CreateMessageDTO;
import com.dfortch.messagesapp.dto.UpdateMessageDTO;
import com.dfortch.messagesapp.persistence.entity.Message;
import com.dfortch.messagesapp.persistence.repository.MessageRepository;
import com.dfortch.messagesapp.persistence.repository.impl.MessageRepositoryImpl;
import com.dfortch.messagesapp.service.MessageService;
import com.dfortch.messagesapp.service.impl.MessageServiceImpl;
import de.vandermeer.asciitable.AsciiTable;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static MessageService messageService;

    public static void main(String[] args) {
        MessageRepository messageRepository = new MessageRepositoryImpl();

        messageService = new MessageServiceImpl(messageRepository);

         try (Scanner sc = new Scanner(System.in)) {
             while (true) {
                 showMainMenu(sc);
             }
         }
    }

    private static void showMainMenu(Scanner sc) {
        printSeparator();

        System.out.println("¿Qué quieres hacer?");
        System.out.println("1. Listar mensajes");
        System.out.println("2. Crear mensaje");
        System.out.println("3. Actualizar un mensaje");
        System.out.println("4. Eliminar un mensaje");
        System.out.println("5. Salir");
        System.out.print("> ");

        int option = sc.nextInt();
        sc.nextLine();

        printSeparator();

        switch (option) {
            case 1 -> {
                showListMessages();
            }
            case 2 -> {
                showCreateMessage(sc);
            }
            case 3 -> {
                showUpdateMessage(sc);
            }
            case 4 -> {
                showDeleteMessage(sc);
            }
            case 5 -> {
                System.out.println("Saliendo...");
                System.exit(0);
            }
            default -> {
                System.out.println("Opción invalida");
            }
        }

        printSeparator();
    }

    private static void showListMessages() {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Message", "Author", "Date");
        table.addRule();

        List<Message> messages = messageService.getAllMessages();

        for (Message message : messages) {
            table.addRow(message.getId(), message.getMessage(), message.getMessageAuthor(), message.getMessageDate());
            table.addRule();
        }

        System.out.println(table.render());
    }

    private static void showCreateMessage(Scanner scanner) {
        System.out.print("Ingresa el mensaje: ");
        String messageContent = scanner.nextLine();

        System.out.print("Ingresa el autor: ");
        String author = scanner.nextLine();

        CreateMessageDTO dto = new CreateMessageDTO(messageContent, author);
        Message message = messageService.createMessage(dto);

        if (message != null) {
            System.out.println("Mensaje creado: " + message);
        }
    }

    private static void showUpdateMessage(Scanner scanner) {
        System.out.print("Ingresa el id del mensaje a actualizar: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        if (!messageService.messageExists(id)) {
            System.out.println("El mensaje no existe");
            return;
        }

        System.out.print("Ingresa el nuevo mensaje: ");
        String messageContent = scanner.nextLine();

        System.out.print("Ingresa el nuevo autor: ");
        String author = scanner.nextLine();

        UpdateMessageDTO dto = new UpdateMessageDTO(id, messageContent, author);
        Message message = messageService.updateMessage(dto);

        if (message != null) {
            System.out.println("Mensaje actualizado: " + message);
        }
    }

    private static void showDeleteMessage(Scanner scanner) {
        System.out.print("Ingresa el id del mensaje a eliminar: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        if (!messageService.messageExists(id)) {
            System.out.println("El mensaje no existe");
            return;
        }

        messageService.deleteMessage(id);
        System.out.println("Mensaje eliminado");
    }

    private static void printSeparator() {
        System.out.println("--------------------------------------------------------------------");
    }
}