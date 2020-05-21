package io.bhagat.paint;

import java.util.List;

import io.bhagat.paint.items.DrawableItem;
import io.bhagat.paint.items.Stroke;
import io.bhagat.paint.modes.PaintMode;
import io.bhagat.paint.transforms.Rotation;
import io.bhagat.paint.transforms.Transform;

public enum MenuDefinition {

    FileMenu(null, new String[] {"Save", "Save As", "Open", "Export Image", "Load Image"}, new MenuSelectCallback() {

        @Override
        public void call(String val) {
            switch(val) {
                case "Save": 
                    PaintProgram.instance.save();
                    break;
                case "Save As":
                    PaintProgram.instance.saveas();
                    break;
                case "Open":
                    PaintProgram.instance.open();
                    break;
                case "Export Image":
                    PaintProgram.instance.export();
                    break;
                case "Load Image":
                    PaintProgram.instance.importImage();
                    break;
            }
        }

    }),
    ColorMenu("WHITE", new String[] {"BLUE", "RED", "BLACK", "GREEN", "YELLOW", "WHITE"}, "java.awt.Color", "{val}"),
    ModeMenu("FreeStroke", PaintMode.modes, "io.bhagat.paint.modes.{val}Mode", "instance"),
    BackgroundColorMenu("BLACK", new String[] {"BLUE", "RED", "BLACK", "GREEN", "YELLOW", "WHITE"}, "java.awt.Color", "{val}"),
    FillMenu("FALSE", new String[] {"FALSE", "TRUE"}, "java.lang.Boolean", "{val}"),
    RotateMenu(null, Util.strRange(-90, 91, 15), new MenuSelectCallback() {

        @Override
        public void call(String val) {
            double theta = Double.parseDouble(val);
            Rotation rotation = Rotation.fromDegrees(theta);
            for(DrawableItem item: PaintManager.instance.getItems()) {
                if(item instanceof Stroke) {
                    List<Point> points = ((Stroke) item).getPoints();
                    Transform.transformList(points, rotation);
                }
            }
        }

    }),
    ConfigurationsMenu("Play Animations", new String[] {"Play Animations", "Pause Animations", "Toggle Instructions", "Undo", "Redo", "Clear"}, new MenuSelectCallback() {

        @Override
        public void call(String val) {
            switch(val) {
                case "Play Animations":
                    PaintManager.instance.setParam("playing", true);
                    break;
                case "Pause Animations":
                    PaintManager.instance.setParam("playing", false);
                    break;
                case "Toggle Instructions":
                    PaintManager.instance.setParam("showinstructions", !((boolean) PaintManager.instance.getParam("showinstructions")));
                    break;
                case "Undo":
                    PaintManager.instance.undo();
                    break;
                case "Redo":
                    PaintManager.instance.redo();
                    break;
                case "Clear":
                    PaintManager.instance.clear();
                    break;
            }
        }

    });
    
    private String name;
    private String[] items;
    private int size;
    private MenuSelectCallback callback;

    private MenuDefinition(String defaultValue, String[] items, String classDefinition, String fieldDefinition) {
        name = name().substring(0, name().length() - 4);
        this.items = items;
        this.size = items.length;
        this.callback =  new MenuSelectCallback(){
        
            @Override
            public void call(String val) {
                Object obj = Util.staticCallFromString(classDefinition.replaceAll("\\{val\\}", val),
                            fieldDefinition.replaceAll("\\{val\\}", val));
                PaintManager.instance.setParam(getName().toLowerCase(), obj);
            }

        };
        if(defaultValue != null)
            callback(defaultValue);
    }

    private MenuDefinition(String defaultValue, String[] items, MenuSelectCallback callback) {
        name = name().substring(0, name().length() - 4);
        this.items = items;
        this.size = items.length;
        this.callback = callback;
        if(defaultValue != null)
            callback(defaultValue);
    }

    public void callback(String val) {
        callback.call(val);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getItem(int index) {
        return items[index];
    }

    public static interface MenuSelectCallback {
        public void call(String val);
    }

}