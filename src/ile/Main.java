package ile;

import ile.model.Model;
import ile.view.StartView;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            /** Voici le contenu qui nous intéresse. */
            Model model = new Model();
            try {
                StartView view = new StartView(model);
                model.notifyObservers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
