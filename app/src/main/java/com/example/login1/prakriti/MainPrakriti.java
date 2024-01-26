package com.example.login1.prakriti;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.login1.R;
import com.example.login1.home_page;

import java.util.HashMap;
import java.util.Map;

public class MainPrakriti extends AppCompatActivity {


    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;

    private int currentQuestionIndex = 0;
    private int kaphaCount = 0;
    private int pittaCount = 0;
    private int vataCount = 0;

    private String[] questions = {"1.How is your built?",
            "2.How would you describe your hair?",
            "3.How would you describe your eyes?",
            "4.Which type of food do you like the most?",
            "5.Towards what do you have intolerance?",
            "6.How much does hunger and thirst bother you?",
            "7.How is your speech, talk and anger?",
            "8.Usually what do you see in your dreams?",
            "9.How is your memory and knowledge?",
            "10.How would you describe your nature?"
    };
    private String[][] options = {
            {"Well built, Smooth body", "Moderately built, Yellowish", "Lean or ruin body, Prominant viens"},
            {"Thick and bluish black", "Less hair and brownish", "Less hair and rough"},
            {"Wide, long, more eyelashes", "Brown, few eyelashes, becomes red easily by anger, sunlight", "Rough, lustreless, round"},
            {"Bitter,astringent, spicy, hot, dry food", "Sweet,astringent,bitter, cold food", "Sweet, sour, salty, hot food"},
            {"Intolerance towards sweet, sour taste", "Intolerance towards hot things, prefers cold", "Intolerance towards dryness like dry weather or dry food"},
            {"Not much", "Excellent thirst and hunger", "Like to have light food"},
            {"Less speech and less anger", "Clear speech and more anger", "Talkative and speaks irrelevantly"},
            {"Water reservoirs full of water,lotus, birds, clouds", "Flowers which are red in color, forest fire, lightening, meteroids", "As if you are moving in sky, running in mountain, dwelling on trees"},
            {"Slow understanding, intelligent", "Intelligent, defeats others in talk, brilliant, clever", "Quick in understanding, forgets things easily, unsteady memory"},
            {"Dullness in action and initiation with less activities, obedient to teachers and elders, forgives easily", "Brave and proud, console to who are obedient to you, affectionate to be dependent", "Quick in initiating actions, get irritated easily, quick in likes and dislikes"}
    };

    private Map<Character, String> natureMap = new HashMap<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_prakriti);

        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(MainPrakriti.this, R.color.black));
        //action bar color d4f0b6   0d6941   #2A712D
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EDF3E6"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'><b><i>Prakruthi</i></b> </font>"));

        questionTextView = findViewById(R.id.questionprakriti);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        nextButton = findViewById(R.id.nextprakriti);

        setQuestion(currentQuestionIndex);

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
                if (selectedOptionId == -1) {
                    Toast.makeText(MainPrakriti.this, "Please select an option", Toast.LENGTH_LONG).show();
                    return;
                }
                    switch(optionsRadioGroup.getCheckedRadioButtonId()) {
                        case R.id.prakritiA:
                            kaphaCount++;
                            break;
                        case R.id.prakritiB:
                            pittaCount++;
                            break;
                        case R.id.prakritiC:
                            vataCount++;
                            break;
                    }


                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        setQuestion(currentQuestionIndex);
                        optionsRadioGroup.clearCheck();
                    } else {
                        showNatureDialog();
                    }

            }
        });
    }

    private void setQuestion(int index) {
        questionTextView.setText(questions[index]);

        String[] currentOptions = options[index];

        for(int i = 0; i < currentOptions.length; i++) {
            RadioButton radioButton = (RadioButton)optionsRadioGroup.getChildAt(i);
            radioButton.setText(currentOptions[i]);
        }

    }

    private String displayNature() {
        String nature = "";
        if (kaphaCount >= 9) {
            nature = "Kapha prakruthi\n\n" +
                    "It is a type of prakruthi in which a person is have smooth, luster, nourished and well built body.\n" +
                    "\n\nThough you are not so fast in doing work, you are intelligent, routious and endored with good health." +
                    "\n\nYou are not having enemies, you have much faith in God and elders.";
        } else if (kaphaCount >= 5) {
            if (pittaCount < 5 && vataCount < 3) {
                nature = "Kapha-Pitta prakruthi\n\n" +
                        "It is a type of prakruthi in which a person is have smooth, luster, nourished and well built body." +
                        "\nThough you are not so fast in doing work, you are intelligent, routious and endored with good helath." +
                        "\nYou are not having enemies, you have much faith in God and elders." +
                        "\n\nIt is a type of prakruthi in which a person have yellowish moderately built body, copperish eye and nails." +
                        "\nEven though you have much anger, you will be calm down easily.\nYou are clever and undefeatable in talk.";
            } else if (vataCount < 5 && pittaCount < 3) {
                nature = "Kapha-Vata prakruthi" +
                        "\n\nIt is a type of prakruthi in which a person is have smooth, luster, nourished and well built body." +
                        "\nThough you are not so fast in doing work, you are intelligent, routious and endored with good helath." +
                        "\nYou are not having enemies, you have much faith in God and elders." +
                        "\n\n\nIt is a type of prakruthi in which a person having lean, dry and prominent veins over the body." +
                        "\nYour actions are quick, unstable in likes and dislikes." +
                        "\nYou have less sleep, you talk more, more activeness, quick in understanding and easily forgetable memory.";
            }
        }

        if (pittaCount >= 9) {
            nature = "Pitta prakruti" +
                    "\n\nIt is a type of prakruthi in which a person have yellowish moderately built body, copperish eye and nails." +
                    "\n\nEven though you have much anger, you will be calm down easily." +
                    "\n\nYou are clever and undefeatable in talk.";
        } else if (pittaCount >= 5) {
            if (kaphaCount < 5 &&vataCount < 3) {
                nature = "Pitta-Kapha prakruthi" +
                        "\n\nIt is a type of prakruthi in which a person have yellowish moderately built body, copperish eye and nails." +
                        "\nEven though you have much anger, you will be calm down easily." +
                        "\nYou are clever and undefeatable in talk." +
                        "\n\nIt is a type of prakruthi in which a person is have smooth, luster, nourished and well built body." +
                        "\nThough you are not so fast in doing work, you are intelligent, routious and endored with good helath." +
                        "\nYou are not having enemies, you have much faith in God and elders.";
            } else if (vataCount < 5 && kaphaCount < 3) {
                nature = "Pitta-Vata prakruti" +
                        "\n\nIt is a type of prakruthi in which a person have yellowish moderately built body, copperish eye and nails." +
                        "\nEven though you have much anger, you will be calm down easily.\nYou are clever and undefeatable in talk." +
                        "\n\nIt is a type of prakruthi in which a person having lean, dry and prominent veins over the body." +
                        "\nYour actions are quick, unstable in likes and dislikes." +
                        "\nYou have less sleep, you talk more, more activeness, quick in understanding and easily forgetable memory.";
            }
        }

        if (vataCount >= 9) {
            nature = "Vata prakruthi" +
                    "\n\nIt is a type of prakruthi in which a person having lean, dry and prominent veins over the body." +
                    "\n\nYour actions are quick, unstable in likes and dislikes." +
                    "\n\nYou have less sleep, you talk more, more activeness, quick in understanding and easily forgetable memory.";
        } else if (vataCount >= 5) {
            if (kaphaCount < 5 && pittaCount < 3) {
                nature = "Vata-Kapha prakruthi" +
                        "\n\nIt is a type of prakruthi in which a person having lean, dry and prominent veins over the body." +
                        "\nYour actions are quick, unstable in likes and dislikes." +
                        "\nYou have less sleep, you talk more, more activeness, quick in understanding and easily forgetable memory." +
                        "\n\nIt is a type of prakruthi in which a person is have smooth, luster, nourished and well built body." +
                        "\nThough you are not so fast in doing work, you are intelligent, routious and endored with good helath." +
                        "\nYou are not having enemies, you have much faith in God and elders.";
            } else if (pittaCount < 5 && kaphaCount < 3) {
                nature = "Vata-Pitta prakruti\n\nIt is a type of prakruthi in which a person having lean, dry and prominent veins over the body.\nYour actions are quick, unstable in likes and dislikes.\nYou have less sleep, you talk more, more activeness, quick in understanding and easily forgetable memory.\nIt is a type of prakruthi in which a person have yellowish moderately built body, copperish eye and nails.\nEven though you have much anger, you will be calm down easily.\nYou are clever and undefeatable in talk.\n";
            }
        }

        if (kaphaCount == pittaCount || pittaCount == kaphaCount) {
            nature = "Kapha-Pitta prakruthi\n\nIt is a type of prakruthi in which a person is have smooth, luster, nourished and well built body.\nThough you are not so fast in doing work, you are intelligent, routious and endored with good helath.\nYou are not having enemies, you have much faith in God and elders.\n\nIt is a type of prakruthi in which a person have yellowish moderately built body, copperish eye and nails.\nEven though you have much anger, you will be calm down easily.\nYou are clever and undefeatable in talk.";
        }

        if (kaphaCount == vataCount || vataCount == kaphaCount) {
            nature = "Vata-Kapha prakruthi\n\nIt is a type of prakruthi in which a person having lean, dry and prominent veins over the body.\nYour actions are quick, unstable in likes and dislikes.\nYou have less sleep, you talk more, more activeness, quick in understanding and easily forgetable memory.\n\nIt is a type of prakruthi in which a person is have smooth, luster, nourished and well built body.\nThough you are not so fast in doing work, you are intelligent, routious and endored with good helath.\nYou are not having enemies, you have much faith in God and elders.";
        }

        if (pittaCount == vataCount || vataCount == pittaCount) {
            nature = "Pitta-Vata prakruti\n\nIt is a type of prakruthi in which a person have yellowish moderately built body, copperish eye and nails.\nEven though you have much anger, you will be calm down easily.\nYou are clever and undefeatable in talk.\n\nIt is a type of prakruthi in which a person having lean, dry and prominent veins over the body.\nYour actions are quick, unstable in likes and dislikes.\nYou have less sleep, you talk more, more activeness, quick in understanding and easily forgetable memory.";
        }

        return nature;
    }

    private void showNatureDialog() {
        String rnature = displayNature();

        Intent intent = new Intent(MainPrakriti.this, ResultActivity.class);
        intent.putExtra("nature", rnature);
        startActivity(intent);
    }
}
