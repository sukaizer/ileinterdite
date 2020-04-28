package ile;

import ile.model.Model;
import ile.view.View;

import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. */
            Model model = new Model();
            try {
                View view = new View(model);
                model.notifyObservers();
                //view.updateContentFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
