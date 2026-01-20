BMI Calculator Android App

A sleek, modern Body Mass Index (BMI) Calculator built for Android. This application allows users to input their weight (kg) and height (m) to instantly determine their BMI category with a clean, card-based user interface.

1. App Design and Layout
    The application follows modern Material Design principles to ensure a user-friendly and aesthetically pleasing experience.
    UI Components:
        ConstraintLayout & CardView: The main container uses a ConstraintLayout to center a CardView. The card provides elevation and rounded corners, creating a distinct "floating" container for the input fields.
        Gradient Header: A custom drawable (bg_gradient_header) is used to provide a professional look to the title section.
        Custom Inputs: EditText fields utilize a custom background (bg_input) with soft rounded corners and internal padding for a modern feel.
        Dynamic Results Section: The result elements are grouped inside a vertical LinearLayout (resultLayout). This container is hidden (visibility="gone") until the calculation is triggered, maintaining a clean initial view.

2. Logic for BMI Calculation
    The core functionality is handled in MainActivity.java and follows a strict validation and calculation pipeline.

    Mathematical Formula:
    The app calculates BMI using the standard metric formula:

    BMI = Weight/Height*2

    Category Logic:
    Based on the resulting value, the app assigns one of four categories:

        i). Underweight: BMI < 18.5
        ii). Normal: 18.5 ≤ BMI < 25 
        iii). Overweight: 25 ≤ BMI < 30 
        iv). Obese: BMI ≥ 30

    Validation Logic:
    Before processing, the app performs the following checks:

        1. Empty Check: Ensures both fields are filled.
        2. Zero/Negative Check: Prevents calculation if inputs are less than zero.
        3. Sanity Check: Warns the user if the height is entered in centimeters instead of meters (e.g., if height > 3.0).
        4. Format Check: Catches NumberFormatException to ensure only valid decimals are processed.





