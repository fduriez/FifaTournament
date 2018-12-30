import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Création du dossier de sauvegarde
        File dir = new File("saves");
        if(!dir.exists()) {
            dir.mkdir();
        }

        Param.initIconTeam();

        /*** Test full APP ***/
        FirstWindow window = new FirstWindow();
    }
}