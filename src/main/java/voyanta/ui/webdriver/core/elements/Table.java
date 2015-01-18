package voyanta.ui.webdriver.core.elements;

import org.openqa.selenium.WebElement;
import voyanta.ui.webdriver.core.elements.impl.TableImpl;
import voyanta.ui.webdriver.core.elements.impl.internal.ImplementedBy;


/**
 * Table functionality.
 */
@ImplementedBy(TableImpl.class)
public interface Table extends Element {

	/**
     * Gets the number of rows in the table
     * @return int equal to the number of rows in the table
     */
    int getRowCount();

    /**
     * Gets the number of columns in the table
     * @return int equal to the number of rows in the table
     */
    int getColumnCount();

    /**
     * Gets the WebElement of the cell at the specified index
     * @param rowIdx The zero based index of the row
     * @param colIdx The zero based index of the column
     * @return the WebElement of the cell at the specified index
     */
    WebElement getCellAtIndex(int rowIdx, int colIdx);
}
