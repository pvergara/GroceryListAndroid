package org.ecos.mvvm;

interface NotificationHandler {
    void notify(String string, Object value);

    void manageOnChangeFor(String propertyName, BindingAction bindingAction, Activity activity);
}
