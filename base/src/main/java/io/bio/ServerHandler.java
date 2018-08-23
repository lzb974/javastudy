package io.bio;

import io.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ServerHandler 处理
 */
public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result;
            //该循环不释放连接
            while (true) {
                //空字符串直接跳出
                if ((expression = in.readLine()) == null) break;
                System.out.println("数据异常" + expression);
                try {
                    result = Calculator.Instance.cal(expression).toString();
                } catch ( Exception e ) {
                    result = "数据异常" + e.getMessage();
                }
                out.println(result);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}