package com.spring.gear.beans.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

import com.spring.gear.utils.NumberUtil;
import com.spring.gear.utils.StringUtil;

/**
 * Created by xiang.wei on 2018/6/27
 *
 * @author xiang.wei
 */
public class CustomNumberEditor extends PropertyEditorSupport {
    private final Class<? extends Number> numberClass;
    private final boolean allowEmpty;
    private final NumberFormat numberFormat;

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) {
        this(numberClass, allowEmpty,null);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty, NumberFormat numberFormat) {
        if (numberClass == null || !Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException("Property class must be a subclass of Number");

        }

        this.numberClass = numberClass;
        this.allowEmpty = allowEmpty;
        this.numberFormat = numberFormat;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtil.hasText(text)) {
            setValue(null);
        } else if (this.numberFormat != null) {
            setValue(NumberUtil.parseNumber(text, this.numberClass, this.numberFormat));
        } else {
            setValue(NumberUtil.parseNumber(text, this.numberClass));

        }
    }

    public void setValue(Object value) {
        if (value instanceof Number) {
            super.setValue(NumberUtil.convertNumberToTargetClass((Number) value, this.numberClass));

        } else {
            super.setValue(value);
        }
    }
    public String getAsText() {
        Object value = getValue();
        if (value == null) {
            return "";
        } else if (this.numberFormat != null) {
            return this.numberFormat.format(value);
        } else {
            return value.toString();
        }

    }
}
