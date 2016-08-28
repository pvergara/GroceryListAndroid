package org.ecos.android.infrastructure.mvvm.binding;

public interface OnChangeListener {
    <T> void onPropertyChange(Property elementName, T sourceElementValue);
}
