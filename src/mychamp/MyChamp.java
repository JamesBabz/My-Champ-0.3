package mychamp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mychamp.bll.TeamManager;
import mychamp.gui.model.ChampModel;

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class MyChamp extends Application {
    
    private TeamManager teamManager;

    @Override
    public void start(Stage stage) throws Exception
    {
        teamManager = new TeamManager();
        
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/TeamManagerView.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Team Manager");
        stage.setScene(scene);
        stage.show();
       stage.setOnCloseRequest((final WindowEvent args) ->
        {
            try
            {
                teamManager.saveTeamData();
            }
            catch (Exception ex)
            {
                System.out.println("Data couldn't be saved.");
            }
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
