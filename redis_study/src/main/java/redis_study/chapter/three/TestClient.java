package redis_study.chapter.three;

public class TestClient {
    public void execute(Command command){
        try{
            /*Connects to server*/
            command.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String... args) {
        TestClient testclient = new TestClient();
        SetCommand set = new SetCommand("MSG","Hello world : simple test client");
        testclient.execute(set);
        GetCommand get = new GetCommand("MSG");
        testclient.execute(get);
    }
}