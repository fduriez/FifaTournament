import java.io.File;

public class Main {

    public static void main(String[] args) {

        //Création du dossier de sauvegarde
        File dir = new File("saves");
        if(!dir.exists()) {
            dir.mkdir();
        }

        Param.initIconTeam();
        Param.initFondList();

        /*** Test full APP ***/
        FirstWindow window = new FirstWindow();
    }
}