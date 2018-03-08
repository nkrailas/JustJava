package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        //If user inputs name
        EditText nameField = (EditText) findViewById(R.id.person_name);
        String hasName = nameField.getText().toString();

        //If user wants whipped cream topping
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCream.isChecked();

        //If user wants chocolate topping
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();

        //Calculates price
        int price = calculatePrice(hasWhippedCream, hasChocolate);


        //Displays order summary on screen
        displayMessage(createOrderSummary(hasName, price, hasWhippedCream, hasChocolate));
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate is whether or not the user wants chocolate topping
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        // Price of 1 cup of coffee
        int basePrice = 5;

        // Add $1 if user wants whipped cream topping
        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }

        // Add $2 if user wants chocolate topping
        if (hasChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculate the total order price by multiplying by quantity
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     * @param hasName is the user's name
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate    is whether or not the user wants chocolate topping
     * @param price           of the order
     * @return text summary
     */

    private String createOrderSummary(String hasName, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String priceMessage = "Name: " + hasName;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;

    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity >= 100) {
            //Display toast when quantity is over 100
            Toast.makeText(this, "You cannot order more than 100 coffees.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //Display toast when quantity is under 1
            Toast.makeText(this, "You cannot order less than 1 coffee.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
            quantity--;    //(same thing as quantity = quantity - 1;)
            displayQuantity(quantity);
        }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}


