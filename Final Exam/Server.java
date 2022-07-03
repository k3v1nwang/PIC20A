package q10;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {

    private static class Task implements Callable<String>{
        private String key;
        Task(String key){ this.key = key;}

        @Override
        public String call() throws Exception{
            //establish the connection
            String cin;
            String s = "https://pic20a-api-server.vercel.app/api?key=" + this.key;
            URL url = new URL(s);
            HttpURLConnection link = (HttpURLConnection) url.openConnection();
            //set up the readers
            BufferedReader reader = new BufferedReader((new InputStreamReader(link.getInputStream())));
            StringBuffer input_stream = new StringBuffer();
            //gather the data collected
            while((cin = reader.readLine()) != null){
                input_stream.append(cin);
            }
            reader.close();
            //return the response
            System.out.println("Key:" + key + " -> "+ input_stream);
            return input_stream.toString();
        }
    }

    public static void main (String[] args){
        int sum = 0;
        int val;
        String[] keys = {"32", "26", "100", "0", "4"};  //, "13", "16", "50", "200", "31", "17", "2", "1", "8", "00", "25"
        List<Future<?>> results = new ArrayList<>();
        ThreadPoolExecutor exe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for(int k = 0; k < keys.length; k++){
            Task t = new Task(keys[k]);
            Future<String> f = exe.submit(t);
            results.add(f);
        }
        for(Future<?> i : results){
            try{
                val = Integer.parseInt(i.get().toString());
                sum += val;
            }
            catch (InterruptedException | ExecutionException e){}
        }
        System.out.println("Sum = " + sum);
        exe.shutdown();
    }

}
