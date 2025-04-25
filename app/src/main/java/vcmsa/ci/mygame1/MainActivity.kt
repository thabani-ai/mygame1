package vcmsa.ci.mygame1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var userInput: EditText
    private lateinit var guessButton: Button
    private lateinit var resultText: TextView
    private lateinit var attemptsText: TextView

    private var secretNumber = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        userInput = findViewById(R.id.userInput)
        guessButton = findViewById(R.id.guessButton)
        resultText = findViewById(R.id.resultText)
        attemptsText = findViewById(R.id.attemptsText)

        // Generate a secret number between 1 and 100
        secretNumber = Random().nextInt(100) + 1

        // Set click listener on the button
        guessButton.setOnClickListener { checkGuess() }
    }

    @SuppressLint("SetTextI18n")
    private fun checkGuess() {
        val guess = userInput.text.toString().toIntOrNull()

        // Validate the input
        if (guess == null || guess < 1 || guess > 100) {
            resultText.text = "Please enter a number between 1 and 100."
            return
        }

        attempts++
        attemptsText.text = "Attempts: $attempts"

        // Check if the guess is correct
        when {
            guess < secretNumber -> resultText.text = "Too low! Try again."
            guess > secretNumber -> resultText.text = "Too high! Try again."
            else -> {
                resultText.text = "Congratulations! You guessed the number."
                // Disable the input and button after correct guess
                userInput.isEnabled = false
                guessButton.isEnabled = false
            }
        }
    }
}