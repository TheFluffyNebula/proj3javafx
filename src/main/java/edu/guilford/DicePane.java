package edu.guilford;

import java.io.File;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/* 
 * Two main parts to the GUI:
 * 1. rolling
 * 2. displaying the image/making it rotate
 * steps for the rolling will have Step [stepNumber]:
 * steps with the image will have image.[stepNumber]:
 * steps with the slider will have slider.[stepNumber]:
 */
public class DicePane extends GridPane{
    // dice attribute
    private Dice dice;

    // add textFields for numSides, numDice
    private TextField numSidesField;
    private TextField numDiceField;
    
    // three label attributes for numSides, numDice, diceValues
    private Label numSidesLabel;
    private Label numDiceLabel;
    private Label diceValuesLabel;

    // Step 1: Declare a roll button (add a button to roll the dice)
    private Button rollButton;

    // add a button to shift image
    private Button imageShiftButton;

    // Image.1: Declare an ImageView attribute
    private ImageView imageView;

    // Declare a Slider attribute that changes color of background
    private Slider numDiceSlider;

    // Constructor
    public DicePane(Dice dice) {
        // Set the individual attribute to the individual parameter
        this.dice = dice;

        // instantiate the imageShift button
        imageShiftButton = new Button("Image Shift");

        // Step 2: convert it into something we can add to the pane
        rollButton = new Button("Roll");

        // Image.2: Instantiate the ImageView attribute with the image we want to display
        // Get the file that contains the image
        File diceImage = new File(this.getClass().getResource("diceImage.JPG").getPath());
        // System.out.println(diceImage.toURI().toString());
        imageView = new ImageView(diceImage.toURI().toString());

        // Slider.2
        numDiceSlider = new Slider(1, 360, 1);
        numDiceSlider.setShowTickMarks(true);
        //my best guess is start, end, step so this should be integers from 1 to 10 inclusive

        // instantiate textfield attributes
        numSidesField = new TextField();
        numDiceField = new TextField();

        // instantiate label attributes
        numSidesLabel = new Label("Number of Sides: " + dice.getNumSides());
        numDiceLabel = new Label("Number of Dice: " + dice.getNumDice());
        dice.rollDice();
        diceValuesLabel = new Label("Dice Values: " + dice.toString());

        // Add a label to the pane
        this.add(numSidesLabel, 0, 0);
        this.add(numDiceLabel, 0, 1);
        this.add(diceValuesLabel, 0, 2);
        // Add numSidesField to the pane next to the label
        this.add(numSidesField, 1, 0);
        this.add(numDiceField, 1, 1);
        // add the imageShift button to the pane
        this.add(imageShiftButton, 0, 3);

        // Step 3: add the component to the pane to the right of the textfields
        this.add(rollButton, 1, 3);

        // Slider.3: add the slider to the pane
        this.add(numDiceSlider, 0, 4, 2, 1);

        // Steps 4 and 5: Write an event listener and connect it to the component
        rollButton.setOnAction(e -> {
            System.out.println("Roll Button Pressed");
            // setup and roll the new dice with the new parameters
            dice.setNumSides(Integer.parseInt(numSidesField.getText()));
            dice.setNumDice(Integer.parseInt(numDiceField.getText()));
            dice.setDiceRolls(new int[dice.getNumDice()]);
            // System.out.println(dice.getDiceRolls());
            dice.rollDice();
            // update the label
            numSidesLabel.setText("Number of Sides: " + Integer.toString(dice.getNumSides()));
            numDiceLabel.setText("Number of Dice: " + Integer.toString(dice.getNumDice()));
            diceValuesLabel.setText("Dice Values: " + dice.toString());
        });

        // Image.3: Add the ImageView to the pane to the right of the textfields
        this.add(imageView, 2, 0, 1, 4);
        // We can change the image to be of a different size
        imageView.setFitHeight(100);
        // And preserve the aspect ratio
        imageView.setPreserveRatio(true);
        // Rotate it 45 degrees
        imageView.setRotate(45);
        // Image.4 and Image.5: Add a listener for the button that changes the labels
        imageShiftButton.setOnAction(e -> {
            Random rand = new Random();
            System.out.println("imageShift Button Pressed");
            imageView.setRotate(rand.nextInt(360));
        });

        // Slider.4 and Slider.5: Add a listener for the slider that changes the labels
        numDiceSlider.setOnMouseReleased(e -> {
            System.out.println("Slider Released");
            // update the color of the background
            this.setStyle("-fx-background-color: hsb(" + numDiceSlider.getValue() + ", 100%, 100%)" );
        });

        // Give the pane a border
        this.setStyle("-fx-border-color: black");
        // make the border thicker
        this.setStyle("-fx-border-width: 2px");
        // and a background color
        this.setStyle("-fx-background-color: pink");
    }

}
