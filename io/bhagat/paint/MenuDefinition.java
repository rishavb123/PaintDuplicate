package io.bhagat.paint;

import java.util.List;

import io.bhagat.paint.items.DrawableItem;
import io.bhagat.paint.items.Stroke;
import io.bhagat.paint.modes.PaintMode;
import io.bhagat.paint.transforms.Rotation;
import io.bhagat.paint.transforms.Transform;

public enum MenuDefinition {

    ColorMenu(new String[] {"BLUE", "RED", "BLACK", "GREEN", "YELLOW", "WHITE"}, "java.awt.Color", "{val}"),
    ModeMenu(PaintMode.modes, "io.bhagat.paint.modes.{val}Mode", "instance"),
    ThicknessMenu(new String[] {"1", "5", "10", "20", "50", "100"}, "java.lang.Integer", "parseInt({val})"),
    BackgroundColorMenu(new String[] {"BLUE", "RED", "BLACK", "GREEN", "YELLOW", "WHITE"}, "java.awt.Color", "{val}"),
    AMenu(Util.strRange(-10, 11, 1), "java.lang.Integer", "parseInt({val})"),
    RotationsMenu(Util.strRange(-90, 91, 15), new MenuSelectCallback() {

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
    ConfigurationsMenu(new String[] {"Play Animations", "Pause Animations", "Clear"}, new MenuSelectCallback() {

        @Override
        public void call(String val) {
            switch(val) {
                case "Play Animations":
                    PaintManager.instance.setParam("playing", true);
                    break;
                case "Pause Animations":
                    PaintManager.instance.setParam("playing", false);
                    break;
                case "Clear":
                    PaintManager.instance.getItems().clear();
                    break;
            }
        }

    });
    
    private String name;
    private String[] items;
    private int size;
    private MenuSelectCallback callback;

    MenuDefinition(String[] items, String classDefinition, String fieldDefinition) {
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
    }

    MenuDefinition(String[] items, MenuSelectCallback callback) {
        name = name().substring(0, name().length() - 4);
        this.items = items;
        this.size = items.length;
        this.callback = callback;
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