package main;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppTest extends ApplicationTest{

    private Button button;
    
	static {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
    }

    @Override
    public void start(Stage stage) {
    
        button = new Button("click me!");
        button.setOnAction(actionEvent -> button.setText("clicked!"));
        stage.setScene(new Scene(new StackPane(button), 100, 100));
        stage.show();
    }

    @Test
    public void should_contain_button_with_text() {
        Assertions.assertThat(button).hasText("click me!");              
    }

    @Test
    public void when_button_is_clicked_text_changes() {
        // when:
        clickOn(".button");        
        // then:
        Assertions.assertThat(button).hasText("clicked!");        
        
        Image img = capture(button).getImage();
                
        
        File outputfile = new File("saved.png");
        try {
			ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}