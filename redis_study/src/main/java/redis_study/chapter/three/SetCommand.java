package redis_study.chapter.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SetCommand extends Command {
    private String key;
    private String value;

    public SetCommand(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String createPayload() {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("SET");
        messageList.add(key);
        messageList.add(value);
        return super.createPayload(messageList);
    }

    @Override
    public void execute() throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(super.socket.getOutputStream(), true);
            String str = this.createPayload();
            System.out.println(str);
            out.println(str);
            //Reads from Redis server
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // This is going to be a single line reply..
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.flush();
            out.close();
            socket.close();
        }
    }
}