package io.bhagat.paint;

public enum SliderDefinition {
    
    ThicknessSlider(5, 1, 100, 1), 
    ASlider(1, -10, 10, 1);

    private String name;

    private double defaultValue;
    private double start;
    private double end;
    private int precision;

    private SliderChangedCallback callback;

    private SliderDefinition(double defaultValue, double start, double end, int precision) {
        this.defaultValue = defaultValue;
        this.start = start;
        this.end = end;
        this.precision = precision;
        name = name().substring(0, name().length() - 6);
        callback = new SliderChangedCallback() {

            @Override
            public void call(double val) {
                Object value = val;
                if(precision == 1) value = (int) (double) val;
                PaintManager.instance.setParam(getName().toLowerCase(), value);
            }

        };
        callback(defaultValue);
    }

    private SliderDefinition(double defaultValue, double start, double end, int precision, SliderChangedCallback callback) {
        this.defaultValue = defaultValue;
        this.start = start;
        this.end = end;
        this.precision = precision;
        name = name().substring(0, name().length() - 6);
        this.callback = callback;
        callback(defaultValue);
    }

    public void callback(double val) {
        callback.call(val);
    }

    public String getName() {
        return name;
    }

    public double getDefaultValue() {
        return defaultValue;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public int getPrecision() {
        return precision;
    }

    public static interface SliderChangedCallback {
        public void call(double val);
    }

}