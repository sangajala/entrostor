package voyanta.ui.webdriver.core.elements;


import voyanta.ui.webdriver.core.elements.impl.TextInputImpl;
import voyanta.ui.webdriver.core.elements.impl.internal.ImplementedBy;

/**
 * Text field functionality.
 */
@ImplementedBy(TextInputImpl.class)
public interface TextInput extends Element {
    /**
     * @param text The text to type into the field.
     */
    void set(String text);
}
