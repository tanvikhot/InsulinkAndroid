package com.aegis.insulink;

import android.os.Environment;
import android.content.res.AssetManager;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;
import org.alicebot.ab.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ChatBot {
    private static final boolean TRACE_MODE = false;
    static String botName = "super";
    private Chat chatSession;
    private Bot bot;

    public ChatBot() {
        init();
    }

    private void init() {
        MagicStrings.root_path = Environment.getExternalStorageDirectory().toString() + "/aegis";
        System.out.println("Working Directory = " + MagicStrings.root_path);
//        AIMLProcessor.extension =  new PCAIMLProcessorExtension();
        bot = new Bot("Super", MagicStrings.root_path, "chat");
        chatSession = new Chat(bot);
//        bot.brain.nodeStats();
    }

    public String chat(String textLine) {
        try {
            if ((textLine == null) || (textLine.length() < 1)) {
                textLine = MagicStrings.null_input;
            }
            String request = textLine;
            String response = chatSession.multisentenceRespond(request);
            while (response.contains("&lt;"))
                response = response.replace("&lt;", "<");
            while (response.contains("&gt;"))
                response = response.replace("&gt;", ">");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
}
