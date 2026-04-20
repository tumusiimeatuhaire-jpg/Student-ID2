package com.example.studentid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentid.ui.theme.StudentIDTheme

// This class is like the "Main Room" of a house. It's the first thing that opens when we tap the app icon
class MainActivity : ComponentActivity() {
    // 'onCreate' is a special instruction that tells the phone, "When this screen ready, do these things"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This makes the app content fill the whole screen, even behind the battery and clock icons
        enableEdgeToEdge()

        // 'setContent' is like saying "Here is the drawing I want you to put on the screen"
        setContent {
            // This applies the app's "Style Guide" (like picking a set of matching colors and fonts).
            StudentIDTheme {
                // 'Scaffold' is a helper that gives us a clean structure to build our UI on.
                // 'Modifier.fillMaxSize()' tells it to take up every bit of space available.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // A 'Box' is a container where things can be stacked on top of each other.
                    // We added 'innerPadding' so our content doesn't get cut off by the phone's notch or corners.
                    Box(modifier = Modifier.padding(innerPadding)) {
                        // This calls our "StudentCard" design which we defined further down.
                        StudentCard()
                    }
                }
            }
        }
    }
}

// This function is like a "Blueprint" for our Student Card.
// Whenever we call a function, 'StudentCard()', Android follows these instructions to draw it.
@Composable
fun StudentCard() {
    // We create a specific "Maroon" color by giving it a hex code (a digital paint ID).
    val nduMaroon = Color(0xFF6C171C)

    /* --- THE CARD CONTAINER ---
       ElevatedCard creates a physical-looking card that has slightly rounded corners and a shadow.
    */
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()        // Make the card as wide as the screen allows.
            .padding(16.dp)        // Put a bit of space around the outside of the card.
            .aspectRatio(1.58f),   // This specific number (1.58) makes it shaped exactly like a real plastic ID card.
        shape = RoundedCornerShape(16.dp), // This softens the corners of the card.
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp), // This adds a shadow to make it look 3D.
        colors = CardDefaults.elevatedCardColors(
            // We give the card a very faint gray/white background.
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        /* --- The Major Box ---
           Inside the card, we use a Box so we can layer things (like putting a background under the text).
        */
        Box(
            modifier = Modifier
                .fillMaxSize()       // Make this container fill the entire card.
                .background(Color.White) // Paint the card's face solid white.
        ) {

            // --- Decorating backgrounds ---
            // This creates the maroon header at the top of the card.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f) // This tells it to cover exactly 25% (one quarter) of the card's height.
                    .background(nduMaroon)
            )

            // This creates the thin maroon strip at the very bottom.
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Push this block to the very bottom.
                    .fillMaxWidth()
                    .height(12.dp)
                    .background(nduMaroon)
            )

            // --- The Watermarks ---
            // We use a 'Row' to place two logos in the background.
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween // Push them to the far left and right.
            ) {
                // Left faint logo
                Image(
                    painter = painterResource(id = R.drawable.ndejje_badge),
                    contentDescription = null, // "null" means it's just for looks, not a button.
                    modifier = Modifier
                        .size(200.dp)
                        .offset(x = (-80).dp, y = 30.dp), // Move it slightly off the edge for a "pro" look.
                    alpha = 0.10f // This makes it 90% see-through (a watermark effect).
                )
                // Right faint logo
                Image(
                    painter = painterResource(id = R.drawable.ndejje_badge),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .offset(x = 80.dp, y = 30.dp),
                    alpha = 0.10f
                )
            }

            // --- Top Logos ---
            // The University Badge (Top Left)
            Image(
                painter = painterResource(id = R.drawable.ndejje_badge),
                contentDescription = "University Logo",
                modifier = Modifier
                    .align(Alignment.TopStart) // Stick it to the top-left corner.
                    .padding(start = 12.dp, top = 10.dp)
                    .size(78.dp)
                    .clip(CircleShape)        // Crop the image into a circle.
                    .background(Color.White)  // Put a white circle behind it so it pops against the maroon.
            )

            // The Uganda Flag (Top Right)
            Image(
                painter = painterResource(id = R.drawable.ug_flag),
                contentDescription = "Uganda Flag",
                contentScale = ContentScale.Crop, // This ensures the flag fits perfectly without stretching.
                modifier = Modifier
                    .align(Alignment.TopEnd) // Stick it to the top-right corner.
                    .padding(end = 12.dp, top = 12.dp)
                    .width(75.dp)
                    .height(45.dp)
            )

            // --- The Student Info ---
            // A 'Column' organizes items in a vertical list (one below another).
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally // Center all text and pictures in the middle.
            ) {
                // This creates empty space at the top so the student photo sits below the maroon header.
                Spacer(modifier = Modifier.fillMaxHeight(0.12f))

                // THE STUDENT PHOTO
                Box(
                    modifier = Modifier.size(95.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.older),
                        contentDescription = "Student Photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(2.dp, nduMaroon, CircleShape) // Add a maroon outer ring.
                            .padding(2.dp)                         // Add a tiny gap.
                            .border(3.dp, Color.White, CircleShape)  // Add a white inner ring.
                            .padding(3.dp)
                            .clip(CircleShape)                    // Finally, crop the photo itself into a circle.
                    )
                }

                // Student Name
                Text(
                    // Fetch the actual name from the 'strings.xml' file.
                    text = stringResource(id = R.string.student_name),
                    style = MaterialTheme.typography.headlineSmall, // Use a "Large" text style.
                    fontWeight = FontWeight.Bold,                  // Make the text bold.
                    color = Color.Black
                )

                // The programme/course
                Row {
                    Text(text = "Programme: ", style = MaterialTheme.typography.labelMedium, color = Color.Black)
                    Text(
                        text = stringResource(id = R.string.programme),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Reg number
                Row {
                    Text(text = "Registration Number: ", style = MaterialTheme.typography.labelMedium, color = Color.Black)
                    Text(
                        text = stringResource(id = R.string.reg_number),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // A thin decorative line to separate student info from dates.
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color.LightGray.copy(alpha = 0.5f)
                )

                // The dates
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Date Issued
                    Row {
                        Text(text = "Issue: ", style = MaterialTheme.typography.labelSmall, color = Color.Black)
                        Text(text = stringResource(id = R.string.issue_date), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                    // A horizontal gap between the two dates.
                    Spacer(modifier = Modifier.width(32.dp))
                    // Expiry Date
                    Row {
                        Text(text = "Expiry: ", style = MaterialTheme.typography.labelSmall, color = Color.Black)
                        Text(text = stringResource(id = R.string.expiry_date), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }

                // This "weight(1f)" is a trick that says: "Take up all remaining empty space."
                // This pushes the barcode below it to the very bottom of the card.
                Spacer(modifier = Modifier.weight(1f))

                // Barcode
                Image(
                    painter = painterResource(id = R.drawable.barcode),
                    contentDescription = "Barcode",
                    modifier = Modifier
                        .width(250.dp)
                        .height(28.dp),
                    contentScale = ContentScale.Crop
                )

                // A tiny bit of breathing room at the very bottom.
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

// This special block allows you to see what you're building in the "Design" tab of Android Studio
// without having to start a real phone or emulator.
@Preview(showBackground = true, name = "University ID Card")
@Composable
fun StudentCardPreview() {
    StudentIDTheme {
        StudentCard()
    }
}