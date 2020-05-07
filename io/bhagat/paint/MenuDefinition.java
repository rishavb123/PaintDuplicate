package io.bhagat.paint;

import io.bhagat.paint.modes.PaintMode;

public enum MenuDefinition {

    ColorMenu(new String[] {"BLUE", "RED", "BLACK", "GREEN", "YELLOW", "WHITE"}, "java.awt.Color", "{val}"),
    ModeMenu(PaintMode.modes, "io.bhagat.paint.modes.{val}Mode", "instance"),
    ThicknessMenu(new String[] {"1", "5", "10", "20", "50", "100"}, "java.lang.Integer", "parseInt({val})"),
    BackgroundColorMenu(new String[] {"BLUE", "RED", "BLACK", "GREEN", "YELLOW", "WHITE"}, "java.awt.Color", "{val}");
    
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