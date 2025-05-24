package org.general;

import org.general.telas.TelaInicial;
import org.general.util.SpringContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.awt.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        var context = new SpringApplicationBuilder(Main.class)
                .headless(false)
                .run(args);
        SpringContext.setApplicationContext(context);

        EventQueue.invokeLater(() -> {
            context.getBean(TelaInicial.class).setVisible(true);
        });
    }
}

