package axl.ide;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import java.awt.*;
import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import static axl.ide.fileList.listFiles;

public class MainController extends AnchorPane implements Initializable {

    int tree;
    @FXML
    private MenuItem selectDirectory;

    @FXML
    private TreeView treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TreeItem<String> rootItem = new TreeItem<>("Here will be your project");
        TreeItem<String> branchItem1 = new TreeItem<>("Folder1");
        TreeItem<String> branchItem2 = new TreeItem<>("Folder2");
        TreeItem<String> branchItem3 = new TreeItem<>("Folder3");
        TreeItem<String> leafItem1 = new TreeItem<>("Subfolder1");
        TreeItem<String> leafItem2 = new TreeItem<>("Subfolder2");
        TreeItem<String> leafItem3 = new TreeItem<>("Subfolder3");

        rootItem.getChildren().setAll(branchItem1,branchItem2,branchItem3);
        branchItem1.getChildren().setAll(leafItem1);
        branchItem2.getChildren().setAll(leafItem2);
        branchItem3.getChildren().setAll(leafItem3);
        treeView.setRoot(rootItem);
    }

    public void selectDirectory(ActionEvent event) throws IOException {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        Launcher launcher = new Launcher();

        File selectedDirectory = directoryChooser.showDialog(launcher.primaryStage);
        Path path = Paths.get(selectedDirectory.getAbsolutePath());
        List<Path> paths = null;
        try {
            paths = listFiles(path);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        paths.forEach(x -> System.out.println(x));

    }

    public void selectItem(){
        //double click -> move working screen to clicked class or open file

    }

}
