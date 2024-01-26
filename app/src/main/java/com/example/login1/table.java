package com.example.login1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class table extends AppCompatActivity {

    RecyclerView myrecycler_view;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(table.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Herb HandBook");

        setContentView(R.layout.herb_book);

        //status bar


//action bar color d4f0b6   0d6941
        //model
        table_model[]my_data=new table_model[]{
                new table_model("Ashoka Tree","Its parts are used in pain for local applications.\nIt is usefull in diarrhoea(taken its stem bark juice).\nIt is useful in strengthning uterus muscels,Its delocation decreases the pain in mensus.\n"
                        ,"अशोक (Ashoka)","ಅಶೋಕ,Ashoka","Saraca indica",
                        "Caesalpinacea","Stem bark, Seeds, Flowers",R.drawable.ashoka_tree),

                new table_model("Black Pepper","Application of pepper paste reduces pain.\nStimulates digestive juice, increase appetite.\nPepper given with honey and ghee cures cough\n",
                        "मरीच (Maricha)","ಕಾಳುಮೆಣಸು(ಕರಿಮೆಣಸು,ಒಳ್ಳೆಮೆಣಸು),Balck pepper,काली मिर्ची(Kali mirch)",
                        " Piper nigrum","Piperaceae","Fruit ",R.drawable.balck_pepper),

                new table_model("Cardamom","Oil of cardamom seeds used in tooth ache.\nActs as mouth freshner.\nWhole cardamom burnt in charcol- Increases appetite.\n",
                        "एला (Ela)","ಏಲಕ್ಕಿ,Cardamom,इलायची"," Elettaria cardamomum",
                        "Scitaminea","Seeds ",R.drawable.cardamom),

                new table_model("Garlic","Garlic paste applied cures swelling.\n" +
                        "Intake og garlic increases digestion." +
                        "\nIntake of garlic reduces fever.\n",
                        "लशुन (Leshuna)","ಬೆಳ್ಳುಳ್ಳಿ,Garlic,लहसुन(Lahsun)"," Allium satium",
                        "Liliaceae"," Rhizome ",R.drawable.garlic),

                new table_model("Ginger","Ginger with milk cures rhinitis.\n" +
                        "Extract of ginger given with honey cures cold." +
                        "\nPaste of ginger if applied to the forehead cures headache.\n",
                        " शुण्ठी /आर्द्रक  (Shunti/Ardraka)","Ginger, ಶುಂಠಿ, सोंठ(Sonth)","Zinziber officinale",
                        "Zinziberaceae"," Rhizome ",R.drawable.ginger),

                new table_model("Indian Basil","Paste of leaves application cures Chronic ulcers.\n" +
                        "Juice intake increase appetite." +
                        "\nGood home remedy for fever, cold, cough.\n",
                        " तुलसी (Tulasi)","ತುಳಸಿ,Holy Basil,Indian Basil"," Ocimum sanctum",
                        "Ocimum sanctum ","Leaf, Seed, Whole palnt ",R.drawable.indian_basil),

                new table_model("Indian Gooseberry","Juice of Indian Gooseberry increases immunity.\n" +
                        "Its powder boiled with milk and taken with ghee cures cough." +
                        "\nIts paste when applied locally cures burning sensation.\n",
                        " आमलकी (Amalaki)","ನೆಲ್ಲಿಕಾಯಿ, Indian Gooseberry, आंवला(Avala)"," Phyllanthus emblica/Emblica officinalis",
                        "Euphorbiaceae ","Fruit/Fruit pulp ",R.drawable.indian_gooseberry),

                new table_model("Indian Pennywort","It is beneficial to intellect ,enhances memory.\n" +
                        "It increases appetite" +
                        "\nIt has wound cleaning and healing property and it's part can be applied externally in wound\n",
                        " मण्डूकपर्णी (Mandukaparni )","ಒಂದೆಲಗ ,Indian pennywoetब्राह्मी (Brahmi)","Centella asiatica",
                        "Umbelliferae ","Whole plant",R.drawable.indian_pennywort),

                new table_model("Long Pepper","Cures swelling and pain when applied externally.\n" +
                        "Increases appetite and cures anemia" +
                        "\nCures fever when given with jaggery\n",
                        "िप्पलि(pippali)","ಹಿಪ್ಪಲಿ,long pepper,पिपली(pipali)","Piper Longum",
                        "Piperaceae","Fruit,root",R.drawable.long_pepper),

                new table_model("Maragosa Tree","External application of paste of leaves cures burning sensation and itchy.\n" +
                        "Intake of leaves - cures intestinal worms Increases appetite and cures anemia" +
                        "\nBark decoction with honey cures vomiting,anorexia\n",
                       "निम्बा(Nimba)","ಕಹಿಬೇವು,Margosa ,नीम्(Neem)","Azardichta indica",
                        "Meliaceae","Leaves ,bark ,seed,oil",R.drawable.margosa_tree),

                new table_model("Pomogranet","Decoction of bark used to gargle in oral diseases and ulcers" +
                        "Fruit cures anemia and decreases mental debility" +"Fruit increases appetite , decreases thirst"+
                        "\nRoot bark decoction can be used to cure diarrhoea\n",
                        "दाडिम(Dadima)","ದಾಳಿಂಬೆ,pomogranate,अनार(Anār)","Punica granatum",
                        "Punicaceae","Fruit,fruit-rind,root,bark ,floral bud",R.drawable.pomegranate),

                new table_model("Sandalwood","Its paste application enhances complexion" +
                        "Burning sensation decreases by application of its paste" +
                        "\nIts oil can be used for application in fever associated with burning sensation\n",
                        "चन्दन (chandana)","ಗಂಧ, sandalwood,सफ़ेद हल्दी (safed haldi)","Sanralum album",
                        "Santalaceae","Heart wood ,volatile oil",R.drawable.sandalwood),


                new table_model("Sweet Flag","Application of its paste decreases pain" +
                        "Reduces abdominal pain - if internally given" +
                        "\nExtensively used in cough ,asthama\n",
                        "वाच (Vacha)","ಬಜೆ,sweet flag ,वाच(vacha)","Acorns calamus",
                        "Araceae","Leaves, rhizomes and stems",R.drawable.sweet_flag),

                new table_model("Turmeric","Turmeric with milk is applied to face , enhances complexion" +
                        "Turmeric paste when applied to insect bite area act as antidote" +
                        "\nTurmeric with milk cures cough throat infection\n",
                        "हरिद्रा(Haridara)","ಅರಿಶಿನ,Turmeric ,हल्दी(haldi)","Curcuma longa",
                        "Zinzeberaceae","Rhizome",R.drawable.turmeric),

        };
        myrecycler_view=findViewById(R.id.recycler_view);
        table_Adapter myadapter=new table_Adapter(my_data);

        myrecycler_view.setLayoutManager(new LinearLayoutManager(this));
        myrecycler_view.setAdapter(myadapter);
    }

    private void filter(String newText) {
        List<table_model> filteredList=new ArrayList<>();
    }




}
