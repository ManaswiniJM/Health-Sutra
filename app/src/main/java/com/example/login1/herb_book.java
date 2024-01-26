package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;

import java.util.ArrayList;
import java.util.List;

public class herb_book extends AppCompatActivity {

    RecyclerView myrecycler_view;
    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herb_book);

        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(herb_book.this, R.color.black));

//action bar color d4f0b6   0d6941
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#176639"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'><b><i>HealthSutra</i></b> </font>"));
       /* searchView= findViewById(R.id.search_view);*/


        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });*/
        Data_Model_herb[]my_data=new Data_Model_herb[]{
                new Data_Model_herb("Alovera","Sanskrit name: कुमारी (Kumari)\n"+"\n"+
                        "Vernacular Names: Indian Aloe, Alovera, ಲೋಳೆಸರ, Gheekuvar(घिकुवर)\n"+"\n"+
                        "Botanical Name: Aloe barbadensis miller\n"+"\n"+
                        "Family:  Liliaceae\n"+"\n"+
                        "Part Used:  Leaves,pulp\n"+"\n"+
                        "Therapeutic Uses: \n" +
                        "\t\t\t\t-\t\tPulp applied locally cures burning sensation, cures wounds.\n"+
                        "\t\t\t\t-\t\tDrinking Alovera juice increases strength.\n"+
                        "\t\t\t\t-\t\tDrinking Alovera jucie cures abdomen pain.\n"
                        ,R.drawable.aloe_vera),

                new Data_Model_herb("Ashoka Tree","Sanskrit name: अशोक (Ashoka)\n"+"\n"+
                        "Vernacular Names: ಅಶೋಕ,Ashoka\n"+"\n"+
                        "Botanical Name: Saraca indica\n"+"\n"+
                        "Family: Caesalpinacea \n"+"\n"+
                        "Part Used: Stem bark, Seeds, Flowers \n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tIts parts are used in pain for local applications.\n"+
                        "\t\t\t\t-\t\tIt is usefull in diarrhoea(taken its stem bark juice).\n"+
                        "\t\t\t\t-\t\tIt is useful in strengthning uterus muscels,Its delocation decreases the pain in mensus.\n",R.drawable.ashoka_tree),

                new Data_Model_herb("Black Pepper","Sanskrit name: मरीच (Maricha)\n"+"\n"+
                        "Vernacular Names: ಕಾಳುಮೆಣಸು(ಕರಿಮೆಣಸು,ಒಳ್ಳೆಮೆಣಸು),Balck pepper,काली मिर्ची(Kali mirch)\n"+"\n"+
                        "Botanical Name: Piper nigrum\n"+"\n"+
                        "Family: Piperaceae \n"+"\n"+
                        "Part Used: Fruit \n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tApplication of pepper paste reduces pain.\n"+
                        "\t\t\t\t-\t\tStimulates digestive juice, increase appetite.\n"+
                        "\t\t\t\t-\t\tPepper given with honey and ghee cures cough.\n",R.drawable.balck_pepper),

                new Data_Model_herb("Cardamom","Sanskrit name: एला (Ela)\n"+"\n"+
                        "Vernacular Names: ಏಲಕ್ಕಿ,Cardamom,इलायची\n"+"\n"+
                        "Botanical Name:  Elettaria cardamomum\n"+"\n"+
                        "Family: Scitaminea \n"+"\n"+
                        "Part Used:  Seeds\n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tOil of cardamom seeds used in tooth ache.\n"+
                        "\t\t\t\t-\t\tActs as mouth freshner.\n"+
                        "\t\t\t\t-\t\tWhole cardamom burnt in charcol- Increases appetite.\n"
                        ,R.drawable.cardamom),

                new Data_Model_herb("Garlic","Sanskrit name: लशुन (Leshuna)\n"+"\n"+
                        "Vernacular Names: ಬೆಳ್ಳುಳ್ಳಿ,Garlic,लहसुन(Lahsun)\n"+"\n"+
                        "Botanical Name:  Allium satium\n"+"\n"+
                        "Family:  Liliaceae\n"+"\n"+
                        "Part Used:  Rhizome\n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tGarlic paste applied cures swelling.\n"+
                        "\t\t\t\t-\t\tIntake og garlic increases digestion.\n"+
                        "\t\t\t\t-\t\tIntake of garlic reduces fever.\n",R.drawable.garlic),

                new Data_Model_herb("Ginger","Sanskrit name: शुण्ठी /आर्द्रक  (Shunti/Ardraka)\n"+"\n"+
                        "Vernacular Names: Ginger, ಶುಂಠಿ, सोंठ(Sonth)\n"+"\n"+
                        "Botanical Name: Zinziber officinale\n"+"\n"+
                        "Family:  Zinziberaceae\n"+"\n"+
                        "Part Used:  Rhizome\n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tGinger with milk cures rhinitis.\n"+
                        "\t\t\t\t-\t\tExtract of ginger given with honey cures cold.\n"+
                        "\t\t\t\t-\t\tPaste of ginger if applied to the forehead cures headache.\n",R.drawable.ginger),

                new Data_Model_herb("Indian Basil","Sanskrit name: तुलसी (Tulasi)\n"+"\n"+
                        "Vernacular Names: ತುಳಸಿ,Holy Basil,Indian Basil\n"+"\n"+
                        "Botanical Name: Ocimum sanctum\n"+"\n"+
                        "Family:  Lamiaceae \n"+"\n"+
                        "Part Used:  Leaf, Seed, Whole palnt\n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tPaste of leaves application cures Chronic ulcers.\n"+
                        "\t\t\t\t-\t\tJuice intake increase appetite.\n"+
                        "\t\t\t\t-\t\tGood home remedy for fever, cold, cough.\n",R.drawable.indian_basil),

                new Data_Model_herb("Indian Gooseberry","Sanskrit name: आमलकी (Amalaki)\n"+"\n"+
                        "Vernacular Names: ನೆಲ್ಲಿಕಾಯಿ, Indian Gooseberry, आंवला(Avala)\n"+"\n"+
                        "Botanical Name: Phyllanthus emblica/Emblica officinalis\n"+"\n"+
                        "Family:  Euphorbiaceae\n"+"\n"+
                        "Part Used:  Fruit/Fruit pulp\n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tJuice of Indian Gooseberry increases immunity.\n"+
                        "\t\t\t\t-\t\tIts powder boiled with milk and taken with ghee cures cough.\n"+
                        "\t\t\t\t-\t\tIts paste when applied locally cures burning sensation.\n",R.drawable.indian_gooseberry),

                new Data_Model_herb("Indian Pennywort","Sanskrit name:मण्डूकपर्णी (Mandukaparni )\n"+"\n"+
                        "Vernacular Names:ಒಂದೆಲಗ ,Indian pennywoetब्राह्मी (Brahmi)\n"+"\n"+
                        "Botanical Name:Centella asiatica \n"+"\n"+
                        "Family:Umbelliferae  \n"+"\n"+
                        "Part Used: Whole plant \n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tIt is beneficial to intellect ,enhances memory\n"+
                        "\t\t\t\t-\t\t It increases appetite\n"+
                        "\t\t\t\t-\t\tIt has wound cleaning and healing property and it's part can be applied externally in wound\n",R.drawable.indian_pennywort),

                new Data_Model_herb("Long Pepper","Sanskrit name:पिप्पलि(pippali)\n"+"\n"+
                        "Vernacular Names:ಹಿಪ್ಪಲಿ,long pepper,पिपली(pipali)\n"+"\n"+
                        "Botanical Name:Piper Longum\n"+"\n"+
                        "Family:Piperaceae   \n"+"\n"+
                        "Part Used:Fruit,root \n \n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tCures swelling and pain when applied externally\n\n"+
                        "\t\t\t\t-\t\tIncreases appetite and cures anemia \n"+
                        "\t\t\t\t-\t\tCures fever when given with jaggery\n",R.drawable.long_pepper),

                new Data_Model_herb("Maragosa Tree","Sanskrit name:निम्बा(Nimba)\n\n"+"\n"+
                        "Vernacular Names:ಕಹಿಬೇವು,Margosa ,नीम्(Neem)\n"+"\n"+
                        "Botanical Name:Azardichta indica\n"+"\n"+
                        "Family:Meliaceae \n"+"\n"+
                        "Part Used:Leaves ,bark ,seed,oil \n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tExternal application of paste of leaves cures burning sensation and itchy \n"+
                        "\t\t\t\t-\t\tIntake of leaves - cures intestinal worms \n"+
                        "\t\t\t\t-\t\tBark decoction with honey cures vomiting,anorexio\n",R.drawable.margosa_tree),

                new Data_Model_herb("Pomogranet","Sanskrit name:दाडिम(Dadima)\n"+"\n"+
                        "Vernacular Names:ದಾಳಿಂಬೆ, pomogranate,अनार(Anār)\n"+"\n"+
                        "Botanical Name:Punica granatum\n"+"\n"+
                        "Family:Punicaceae\n"+"\n"+
                        "Part Used:Fruit,fruit rind,root,bark ,floral bud\n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tDecoction of bark used to gargle in oral diseases and ulcers\n"+
                        "\t\t\t\t-\t\tFruit cures anemia\n"+
                        "\t\t\t\t-\t\tFruit decreases mental debility \n"+
                        "\t\t\t\t-\t\tFruit increases appetite , decreases thirst \n"+
                        "\t\t\t\t-\t\tRoot bark decoction can be used to cure diarrhoea\n",R.drawable.pomegranate),

                new Data_Model_herb("Sandalwood","Sanskrit name:चन्दन (chandana)\n"+"\n"+
                        "Vernacular Names:ಗಂಧ, sandalwood,सफ़ेद हल्दी (safed haldi)\n"+"\n"+
                        "Botanical Name:Sanralum album \n"+"\n"+
                        "Family:Santalaceae\n"+"\n"+
                        "Part Used:Heart wood ,volatile oil\n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tIts paste application enhances complexion\n"+
                        "\t\t\t\t-\t\tBurning sensation decreases by application of its paste \n"+
                        "\t\t\t\t-\t\tIts oil can be used for application in fever associated with burning sensation\n",R.drawable.sandalwood),

                new Data_Model_herb("Sweet Flag","Sanskrit name:वाच (Vacha)\n"+"\n"+
                        "Vernacular Names:ಬಜೆ,sweet flag ,वाच(vacha)\n"+"\n"+
                        "Botanical Name:Acorns calamus \n"+"\n"+
                        "Family:Araceae\n"+"\n"+
                        "Part Used:Leaves, rhizomes and stems\n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tApplication of its paste decreases pain\n"+
                        "\t\t\t\t-\t\tReduces abdominal pain - if internally given\n"+
                        "\t\t\t\t-\t\tExtensively used in cough ,asthama\n",R.drawable.sweet_flag),

                new Data_Model_herb("Turmeric","Sanskrit name:हरिद्रा(Haridara)\n"+"\n"+
                        "Vernacular Names:ಅರಿಶಿನ,Turmeric ,हल्दी(haldi)\n"+"\n"+
                        "Botanical Name:Curcuma longa\n"+"\n"+
                        "Family:Zinzeberaceae\n"+"\n"+
                        "Part Used:Rhizome \n"+"\n"+
                        "Therapeutic Uses:  \n" +
                        "\t\t\t\t-\t\tTurmeric with milk is applied to face , enhances complexion\n"+
                        "\t\t\t\t-\t\tTurmeric paste when applied to insect bite area act as antidote\n"+
                        "\t\t\t\t-\t\tTurmeric with milk cures cough throat infection\n",R.drawable.turmeric),


        };
        myrecycler_view=findViewById(R.id.recycler_view);
        Data_Adapter_herb myadapter=new Data_Adapter_herb(my_data);

        myrecycler_view.setLayoutManager(new LinearLayoutManager(this));
        myrecycler_view.setAdapter(myadapter);
    }

    private void filter(String newText) {
        List<Data_Model_herb> filteredList=new ArrayList<>();
    }

}