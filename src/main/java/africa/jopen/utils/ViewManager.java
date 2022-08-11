package africa.jopen.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.HashMap;

public class ViewManager {

    private static ViewManager instance;
    private static final HashMap<String, Node> SCREENS = new HashMap<>();
    private static String nameView;

    private ViewManager(){}

    public static ViewManager getInstance() {
        if(instance == null){
            instance =  new ViewManager();
        }
        return instance;
    }

    public void put(String name, Node node){
        nameView = name;
        SCREENS.put(name, node);
    }

    public Node get(String view){
        return SCREENS.get(view);
    }

    public int getSize(){
        return SCREENS.size();
    }

    Node getCurrentView(){
        return SCREENS.get(nameView);
    }

    public ObservableList<Node> getAll(){
        return FXCollections.observableArrayList(SCREENS.values());
    }
}
