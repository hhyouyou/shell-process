package com.uos.shell.process.controller;

import com.uos.shell.process.model.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author dongjingxi
 * @date 2021/9/27 上午10:03
 */
@Controller
@RequestMapping()
public class ExecController {


    @GetMapping("/index")
    public String exec() {
        return "index";
    }


    @ResponseBody
    @PostMapping("/command")
    public ServerResponse<String> exec(@RequestParam("command") String command) {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            int exitVal = process.waitFor();
            stringBuilder.append("process exit value is ").append(exitVal).append("<br />").append("<br />");
            System.out.println("OUTPUT");
            while ((line = stdoutReader.readLine()) != null) {
                System.out.println(line);
                stringBuilder.append(line).append("<br />");
            }
            System.out.println("ERROR");
            while ((line = stderrReader.readLine()) != null) {
                System.out.println(line);
                stringBuilder.append(line).append("<br />");
            }
            return ServerResponse.ok(stringBuilder.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("执行错误");
        }
    }






    @PostMapping("/command/array")
    public String exec(@RequestParam("command") String[] command) {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("OUTPUT");
            while ((line = stdoutReader.readLine()) != null) {
                System.out.println(line);
                stringBuilder.append(line).append("\r\n");
            }
            System.out.println("ERROR");
            while ((line = stderrReader.readLine()) != null) {
                System.out.println(line);
            }
            int exitVal = process.waitFor();
            System.out.println("process exit value is " + exitVal);
            return stringBuilder.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("执行错误");
        }


    }
}
