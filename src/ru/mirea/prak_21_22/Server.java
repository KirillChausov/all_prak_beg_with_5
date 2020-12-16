package ru.mirea.prak_21_22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server {
    // порт, который будет прослушивать наш сервер
    static final int PORT = 3443;
    // список клиентов, которые будут подключаться к серверу
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public Server() throws IOException {
        Date date = new Date();
        File myFile = new File("D://history.txt");
        FileWriter fileWriter = new FileWriter("D://history.txt", true);
        try {
            fileWriter.write("Сервер запущен. Дата и время: " + date.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка");
        } finally {
            fileWriter.close();
        }
        // сокет клиента, это некий поток, который будет подключаться к серверу
        // по адресу и порту
        Socket clientSocket = null;
        // серверный сокет
        ServerSocket serverSocket = null;
        try {
            // создаём серверный сокет на определенном порту
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");
            // запускаем бесконечный цикл
            while (true) {
                // таким образом ждём подключений от сервера
                clientSocket = serverSocket.accept();
                // создаём обработчик клиента, который подключился к серверу
                // this - это наш сервер
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                // каждое подключение клиента обрабатываем в новом потоке
                new Thread(client).start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                // закрываем подключение
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // отправляем сообщение всем клиентам
    public void sendMessageToAllClients(String msg) throws IOException {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            System.out.println(format.format(date) + " | " + msg);
            FileWriter fileWriter = new FileWriter("D://history.txt", true);
            fileWriter = new FileWriter("D://history.txt", true);
            try {
                fileWriter.write(format.format(date) + " | " + msg + "\n");
            } catch (IOException e) {
                System.out.println("Ошибка");
            } finally {
                fileWriter.close();
            }
        }

    // удаляем клиента из коллекции при выходе из чата
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
