package redis_study.chapter.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetCommand extends Command {
    private String key;

    public GetCommand(String key) {
        this.key = key;
    }

    @Override
    public String createPayload() {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("GET");
        messageList.add(key);
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
            in = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            String msg = in.readLine();
            if (!msg.contains("-1")) {
                System.out.println(msg);
                System.out.println(in.readLine());
            } else {
                // This will show the error message since the
                // server has returned '-1'
                System.out.println("This Key does not exist !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            in.close();
            socket.close();
        }
    }
}